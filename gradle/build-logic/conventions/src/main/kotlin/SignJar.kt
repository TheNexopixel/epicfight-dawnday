import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.TaskAction
import java.awt.GraphicsEnvironment
import javax.swing.ImageIcon
import javax.swing.JOptionPane
import javax.swing.JPasswordField
import javax.swing.SwingUtilities

abstract class SignJar : DefaultTask()
{
    @get:InputFile
    abstract val jarFile: RegularFileProperty

    @get:InputFile
    abstract val configFile: RegularFileProperty

    @get:Input
    abstract val aliasName: Property<String>

    @get:InputFile
    abstract val iconFile: RegularFileProperty

    init {
        group = "build"
        description = "Signs the mod JAR securely using a YubiKey GUI prompt."
    }

    @TaskAction
    fun sign() {
        val targetJar = jarFile.get().asFile
        val configPath = configFile.get().asFile.absolutePath
        val alias = aliasName.get()

        if (GraphicsEnvironment.isHeadless()) {
            throw GradleException("Headless environment detected. A GUI is required to prompt for the PIN.")
        }

        val pf = JPasswordField()
        var result = JOptionPane.CANCEL_OPTION

        SwingUtilities.invokeAndWait {
            val icon = iconFile.orNull?.asFile
            val customIcon = if (icon != null && icon.exists()) ImageIcon(icon.absolutePath) else null

            val pane = JOptionPane(pf, JOptionPane.PLAIN_MESSAGE, JOptionPane.OK_CANCEL_OPTION)
            val dialog = pane.createDialog("Enter YubiKey PIN")

            if (customIcon != null) dialog.setIconImage(customIcon.image)

            dialog.isAlwaysOnTop = true
            dialog.isVisible = true
            result = (pane.value as? Int) ?: JOptionPane.CANCEL_OPTION
        }

        if (result != JOptionPane.OK_OPTION) {
            logger.lifecycle("JAR signing skipped")
            return
        }

        val pin = String(pf.password)
        if (pin.isEmpty()) {
            throw GradleException("PIN cannot be empty.")
        }

        logger.lifecycle("--------------------------------------------------")
        logger.lifecycle("Signing JAR: ${targetJar.name}")
        logger.lifecycle("Please TOUCH the gold contact when your YubiKey flashes.")
        logger.lifecycle("--------------------------------------------------")

        val process = ProcessBuilder(
            "jarsigner",
            "-keystore", "NONE",
            "-storetype", "PKCS11",
            "-providerClass", "sun.security.pkcs11.SunPKCS11",
            "-providerArg", configPath,
            "-storepass", pin,
            "-tsa", "http://timestamp.digicert.com",
            targetJar.absolutePath,
            alias
        ).inheritIO().start()

        val exitCode = process.waitFor()
        if (exitCode != 0) {
            throw GradleException("jarsigner failed with exit code $exitCode")
        }

        logger.lifecycle("Success! JAR signed.")
    }
}