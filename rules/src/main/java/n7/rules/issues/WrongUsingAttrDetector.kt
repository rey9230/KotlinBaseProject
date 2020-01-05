package n7.rules.issues

import com.android.tools.lint.detector.api.*
import java.util.*
import org.w3c.dom.Attr

class WrongUsingAttrDetector : LayoutDetector() {

    override fun getApplicableAttributes(): Collection<String>? {
        return Arrays.asList("background")
    }

    override fun visitAttribute(context: XmlContext, attribute: Attr) {
        super.visitAttribute(context, attribute)
        if (attribute.value.startsWith("?")) {
            return
        }
        context.report(
            ISSUE,
            context.getLocation(attribute.ownerElement),
            "use theme attribute that starts with ? mark"
        )
    }

    companion object {

        val ISSUE: Issue = Issue
            .create(
                id = "WrongUsingBackgroundAttr",
                briefDescription = " with dynamic themes you should use color from current theme",
                explanation = """
                -----?-----
            """.trimIndent(),
                category = Category.CORRECTNESS,
                priority = 9,
                severity = Severity.ERROR,
                androidSpecific = true,
                implementation = Implementation(
                    WrongUsingAttrDetector::class.java,
                    Scope.RESOURCE_FILE_SCOPE
                )
            )
    }
}
