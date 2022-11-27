package com.choota.opencurrency.presentation.commmon

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.RelativeLayout
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.choota.opencurrency.R
import com.choota.opencurrency.domain.model.Currency
import com.google.android.material.button.MaterialButton
import me.abhinay.input.CurrencyEditText

object CurrencyListDialog {
    lateinit var dialog: Dialog
    lateinit var selection: String

    fun show(context: Context, currencies: List<Currency>, selectedCode: String, completion: (Double, String) -> Unit){
        selection = selectedCode
        dialog = Dialog(context, R.style.AppCompatAlertDialogStyle)
        dialog.setContentView(R.layout.dialog_currency_selector)

        ((dialog).findViewById<RecyclerView>(R.id.recyclerViewCurrency)).apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = CurrencyAdapter(context, currencies, selection){
                selection = it
            }
        }

        ((dialog).findViewById<MaterialButton>(R.id.btnClose)).setOnClickListener { dialog.dismiss() }
        ((dialog).findViewById<MaterialButton>(R.id.btnSet)).setOnClickListener {
            val amount = ((dialog).findViewById<CurrencyEditText>(R.id.edtAmount)).cleanDoubleValue
            completion(amount, selection)
            dialog.dismiss()
        }

        dialog.setCanceledOnTouchOutside(false)
        dialog.setCancelable(false)
        dialog.show()
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.setLayout(
            RelativeLayout.LayoutParams.WRAP_CONTENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        )
    }
}