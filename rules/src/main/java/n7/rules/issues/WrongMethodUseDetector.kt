package n7.rules.issues

import com.android.tools.lint.detector.api.*
import com.intellij.psi.PsiMethod
import org.jetbrains.uast.UCallExpression

// class that is able to find one/more specific issues
class WrongMethodUseDetector : Detector(), SourceCodeScanner {

    // filters only the method signatures that exist in android.util.Log
    override fun getApplicableMethodNames(): List<String> =
        listOf("callDima")

    // uses the evaluator to guarantee that the method is being from difine classes and not by any other class
    override fun visitMethodCall(context: JavaContext, node: UCallExpression, method: PsiMethod) {
        super.visitMethodCall(context, node, method)
        val evaluator = context.evaluator
        reportUsage(context, node)
//        if (evaluator.isMemberInClass(method, "android.util.Log")) {
//            reportUsage(context, node)
//        }
    }

    // used to report an issue when found
    private fun reportUsage(context: JavaContext, node: UCallExpression) {
        context.report(
            issue = ISSUE,
            scope = node,
            location = context.getCallLocation(
                call = node,
                includeReceiver = true,
                includeArguments = true
            ),
            message = "Delete This Shiiiit!!"
        )
    }

    // an Issue is a potential bug in an Android application
    companion object {
        private const val id = "WrongMethod"
        private const val briefDescription = "You should not use this method in my project"
        private const val explanation = """
            Interesting Why?
            Find me and Ask!
        """

        val ISSUE: Issue = Issue
            .create(
                id = id,
                briefDescription = briefDescription,
                explanation = explanation,
                category = Category.CORRECTNESS,
                priority = 9,
                severity = Severity.ERROR,
                androidSpecific = true,
                implementation = Implementation(
                    WrongMethodUseDetector::class.java,
                    Scope.JAVA_FILE_SCOPE
                )
            )
    }
}
