package com.miu.housing

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class CustomDialog(val layout: Int): DialogFragment() {
    private lateinit var listener: CustomDialogListener
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
//        return inflater.inflate(layout, container, false)
//    }
    interface CustomDialogListener {
        fun onDialogPositiveClick(dialog: DialogFragment)
        fun onDialogNegativeClick(dialog: DialogFragment)
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            listener = context as CustomDialogListener
        } catch (e: ClassCastException) {
            // The activity doesn't implement the interface, throw exception
            throw ClassCastException((context.toString() +
                    " must implement CustomDialogListener"))
        }
    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        //var dialog = super.onCreateDialog(savedInstanceState)
        //dialog.requestWindowFeature(Window.FEATURE_ACTION_MODE_OVERLAY)
        //return dialog
        return activity?.let {
            var builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            builder.setView(inflater.inflate(layout, null))
             .setPositiveButton(R.string.dialog_pos_btn,
                    DialogInterface.OnClickListener{dialog, id ->
                        listener.onDialogPositiveClick(this)
                    })
                .setNegativeButton(R.string.dialog_nev_btn,
                DialogInterface.OnClickListener{dialog, id ->
                    //getDialog()?.cancel()
                    listener.onDialogNegativeClick(this)
                })
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null!")
    }
}