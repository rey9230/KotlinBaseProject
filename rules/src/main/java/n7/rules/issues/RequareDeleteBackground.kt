package n7.rules.issues

import com.android.tools.lint.detector.api.*
import org.w3c.dom.Attr
import java.util.*

class RequareDeleteBackground : LayoutDetector() {

    override fun getApplicableAttributes(): Collection<String>? {
        return Arrays.asList("background")
    }

    override fun visitAttribute(context: XmlContext, attribute: Attr) {
        super.visitAttribute(context, attribute)

        context.report(
            ISSUE,
            context.getLocation(attribute.ownerElement),
            "ISSUE"
        )
    }

    companion object {
        private val IMPLEMENTATION = Implementation(
            RequareDeleteBackground::class.java,
            Scope.RESOURCE_FILE_SCOPE
        )

        val ISSUE: Issue = Issue
            .create(
                id = "WrongMethodUseDetector",
                briefDescription = "The android Log should not be used",
                explanation = """
                For amazing showcasing purposes we should not use the Android Log. We should the
                AmazingLog instead.
            """.trimIndent(),
                category = Category.CORRECTNESS,
                priority = 9,
                severity = Severity.ERROR,
                androidSpecific = true,
                implementation = IMPLEMENTATION
            )
    }
}