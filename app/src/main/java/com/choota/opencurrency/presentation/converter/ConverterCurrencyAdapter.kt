package com.choota.opencurrency.presentation.converter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.blongho.country_data.Country
import com.choota.opencurrency.R
import com.choota.opencurrency.domain.model.Currency
import com.choota.opencurrency.utils.round2Decimal
import de.hdodenhof.circleimageview.CircleImageView
import me.abhinay.input.CurrencyEditText

class ConverterCurrencyAdapter(
    context: Context,
    data: List<Currency>,
    initialLoad: Boolean = true
) :
    RecyclerView.Adapter<ConverterCurrencyAdapter.ViewHolder>() {

    var _initialLoad = initialLoad
    var _items: List<Currency> = data
    val _context = context

    @SuppressLint("NotifyDataSetChanged")
    fun regenerateList(data: List<Currency>, initialLoad: Boolean = false) {
        _initialLoad = false
        _items = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(_context).inflate(R.layout.cell_converted_currency, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = _items[position]
        holder.amount.setCurrency(item.symbol)

        if (_initialLoad)
            holder.amount.setText(item.rate.round2Decimal())
        else
            holder.amount.setText(item.amount.round2Decimal())

        holder.code.text = item.code
        holder.name.text = item.name

        if (item.flag != null) {
            holder.flag.setImageResource(item.flag!!)
        }
    }

    override fun getItemCount(): Int {
        return _items.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var flag: CircleImageView = view.findViewById(R.id.imgFlag)
        var amount: CurrencyEditText = view.findViewById(R.id.edtAmount)
        var code: TextView = view.findViewById(R.id.txtCode)
        var name: TextView = view.findViewById(R.id.txtName)
    }
}