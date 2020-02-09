package n7.myperfectemptyproject.ui.main

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import n7.myperfectemptyproject.R

class MainDialog : DialogFragment() {

    private val args: MainDialogArgs by navArgs()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        dialog?.window?.setWindowAnimations(R.style.DialogAnimations)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialogBuilder = MaterialAlertDialogBuilder(context)
            .setMessage(args.message)
            .setPositiveButton("OK",null)

        return dialogBuilder.create()
    }

}