package n7.myperfectemptyproject.ui

import android.app.Dialog
import android.os.Bundle
import androidx.annotation.Keep
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import n7.myperfectemptyproject.R
import java.io.Serializable

// global type dialog can communicate only through listener that passing as parameter
// but in perfect world it should be done with `callback = (Callback) getParentFragment()`
// but... maybe... i... am... wrong...
class ErrorDialog : DialogFragment() {

    // "className + Args" help to retrieve values from Bundle
    private val args: ErrorDialogArgs by navArgs()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // show dialog with animation
        dialog?.window?.setWindowAnimations(R.style.MyDialogAnimations)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialogBuilder = MaterialAlertDialogBuilder(context)
            .setMessage(args.message)
            .setPositiveButton(android.R.string.ok) { _, _ -> args.listener?.onPositiveButtonClick() }

        return dialogBuilder.create()
    }
}

// use Annotation @Keep will prevent proguard to shrink this class
// also you can use this article to automize it https://android.jlelse.eu/how-to-generate-proguard-r8-rules-for-navigation-component-arguments-466e72e75ca7
@Keep interface ErrorDialogListener : Serializable {
    fun onPositiveButtonClick()
}
