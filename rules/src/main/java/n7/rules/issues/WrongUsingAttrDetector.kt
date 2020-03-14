package n7.rules.issues

import com.android.tools.lint.detector.api.Category
import com.android.tools.lint.detector.api.Implementation
import com.android.tools.lint.detector.api.Issue
import com.android.tools.lint.detector.api.LayoutDetector
import com.android.tools.lint.detector.api.Scope
import com.android.tools.lint.detector.api.Severity
import com.android.tools.lint.detector.api.XmlContext
import org.w3c.dom.Attr
import java.util.Arrays

// class that is able to find background attribute in xml files that not start with "?" mark
class WrongUsingAttrDetector : LayoutDetector() {

    override fun getApplicableAttributes(): Collection<String>? {
        return Arrays.asList("background")
    }

    override fun visitAttribute(context: XmlContext, attribute: Attr) {
        super.visitAttribute(context, attribute)
        if (attribute.value.startsWith("?") || attribute.value.startsWith("@drawable/")) {
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
