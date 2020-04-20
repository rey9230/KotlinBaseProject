package n7.rules.issues

import com.android.resources.ResourceFolderType
import com.android.tools.lint.detector.api.Category.Companion.CORRECTNESS
import com.android.tools.lint.detector.api.Context
import com.android.tools.lint.detector.api.Implementation
import com.android.tools.lint.detector.api.Issue
import com.android.tools.lint.detector.api.Location
import com.android.tools.lint.detector.api.ResourceXmlDetector
import com.android.tools.lint.detector.api.Scope
import com.android.tools.lint.detector.api.Severity
import com.android.tools.lint.detector.api.TextFormat
import com.android.tools.lint.detector.api.XmlContext
import org.w3c.dom.Attr

@Suppress("UnstableApiUsage")
class MissingNightColorDetector : ResourceXmlDetector() {

    private val nightModeColors = mutableListOf<String>()
    private val regularColors = mutableMapOf<String, Location>()

    override fun appliesTo(folderType: ResourceFolderType): Boolean {
        return folderType == ResourceFolderType.VALUES
    }

    override fun getApplicableElements(): Collection<String>? {
        return listOf("color")
    }

    override fun afterCheckEachProject(context: Context) {
        regularColors.forEach { color, location ->
            if (!nightModeColors.contains(color))
                context.report(
                    ISSUE,
                    location,
                    ISSUE.getExplanation(TextFormat.RAW)
                )
        }
    }

    override fun visitElement(context: XmlContext, element: org.w3c.dom.Element) {
        if (context.getFolderConfiguration()!!.isDefault)
            regularColors[element.getAttribute("name")] = context.getLocation(element)
        else if (context.getFolderConfiguration()!!.nightModeQualifier.isValid)
            nightModeColors.add(element.getAttribute("name"))
    }

    class NonSemanticColorDetector : ResourceXmlDetector() {

        override fun getApplicableAttributes(): Collection<String>? = listOf(
            "background", "foreground", "src", "textColor", "tint", "color",
            "textColorHighlight", "textColorHint", "textColorLink",
            "shadowColor", "srcCompat"
        )

        override fun visitAttribute(context: XmlContext, attribute: Attr) {
            if (checkName(attribute.value)) {
                context.report(
                    ISSUE,
                    context.getLocation(attribute),
                    ISSUE.getExplanation(TextFormat.RAW)
                )
            }
        }

        private fun checkName(input: String): Boolean {
            return listOf(
                "black", "blue", "green", "orange",
                "teal", "white", "orange", "red"
            ).any {
                input.contains(it)
            }
        }
    }

    companion object {
        val ISSUE = Issue.create(
            "NonSemanticColorUse",
            "Non semantic color used",
            "Avoid non semantic use of colors in XML files. This will cause issues with different theme (eg. night) support. " +
                "For example, use primary instead of black.",
            CORRECTNESS,
            6,
            Severity.ERROR,
            Implementation(NonSemanticColorDetector::class.java, Scope.RESOURCE_FILE_SCOPE)
        )
    }
}

