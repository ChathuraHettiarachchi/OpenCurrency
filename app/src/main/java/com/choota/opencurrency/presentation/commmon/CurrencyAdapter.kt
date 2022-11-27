package com.choota.opencurrency.presentation.commmon

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.blongho.country_data.Country
import com.choota.opencurrency.R
import com.choota.opencurrency.domain.model.Currency

class CurrencyAdapter(
    context: Context,
    data: List<Currency>,
    selection: String = "usd",
    completion: (String) -> Unit
) :
    RecyclerView.Adapter<CurrencyAdapter.ViewHolder>() {

    var _items: List<Currency> = data
    val _context = context
    var _selection = selection
    var _completion = completion

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(_context).inflate(R.layout.cell_dialog_currency, parent, false)
        )
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = _items[position]

        holder.radio.isChecked = _selection.lowercase() == item.code.lowercase()
        holder.code.text = item.code
        holder.name.text = item.name
        holder.layMain.setOnClickListener {
            _selection = item.code
            _completion(item.code)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return _items.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var radio: CheckBox = view.findViewById(R.id.radio)
        var code: TextView = view.findViewById(R.id.txtCode)
        var name: TextView = view.findViewById(R.id.txtName)
        var layMain: RelativeLayout = view.findViewById(R.id.layMain)
    }
}