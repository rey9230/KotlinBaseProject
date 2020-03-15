package n7.myperfectemptyproject.ui

import android.app.Dialog
import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.parcel.Parcelize
import n7.myperfectemptyproject.R
import n7.myperfectemptyproject.ui.main.MainFragment
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

interface ErrorDialogListener : Serializable {
    fun onPositiveButtonClick()
}
