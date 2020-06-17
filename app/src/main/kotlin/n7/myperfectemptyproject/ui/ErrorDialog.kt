package n7.myperfectemptyproject.ui

import android.app.Dialog
import android.os.Bundle
import androidx.annotation.Keep
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.io.Serializable

// global type dialog can communicate only through listener that passing as parameter
// but in perfect world it should be done with `callback = (Callback) getParentFragment()`
// but... maybe... i... am... wrong...
class ErrorDialog : DialogFragment() {

    // "className + Args" help to retrieve values from Bundle
    private val args: ErrorDialogArgs by navArgs()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialogBuilder = MaterialAlertDialogBuilder(context)
            .setMessage(args.message)
            .setPositiveButton(android.R.string.ok) { _, _ ->
                // args.listener?.onPositiveButtonClick()
                parentFragmentManager.setFragmentResult("key", bundleOf("key" to "hello"))
            }
        doNewSomething(counter = 4)
        return dialogBuilder.create()
    }

    @Deprecated(
        message = "use doNewSomething instead this",
        replaceWith = ReplaceWith(
            expression = "doNewSomething(counter = count)",
            imports = ["n7.myperfectemptyproject.base.MarginItemDecorator"]
        ),
        level = DeprecationLevel.ERROR
    )
    private fun doSomething(count: Int) = Unit

    private fun doNewSomething(counter: Int) = Unit
}

// use Annotation @Keep will prevent proguard to shrink this class
// also you can use this article to automize it https://android.jlelse.eu/how-to-generate-proguard-r8-rules-for-navigation-component-arguments-466e72e75ca7
@Keep
interface ErrorDialogListener : Serializable {
    fun onPositiveButtonClick()
}
