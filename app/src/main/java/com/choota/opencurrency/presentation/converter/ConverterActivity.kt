package com.choota.opencurrency.presentation.converter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.blongho.country_data.Country
import com.blongho.country_data.World
import com.choota.opencurrency.databinding.ActivityConverterBinding
import com.choota.opencurrency.domain.model.Currency
import com.choota.opencurrency.presentation.commmon.CurrencyListDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import me.abhinay.input.CurrencySymbols
import me.abhinay.input.CurrencySymbols.USA

@AndroidEntryPoint
class ConverterActivity : AppCompatActivity() {

    private var _binding: ActivityConverterBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ConverterViewModel by viewModels()

    private var selectedCurrency: String = "usd"
    private var selectedAmount: Double = 1.00

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityConverterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()
    }

    private fun initUI() {
        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.currentCountryState.collect {
                binding.imgFlag.setImageResource(it.flagResource)
                binding.edtAmount.setCurrency(it.currency?.symbol)
                binding.edtAmount.setText("1.00")
            }
        }

        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.currencyState.collect {
                if (it.isLoading) {
                    //Toast.makeText(this@ConverterActivity, "Loading", Toast.LENGTH_LONG).show()
                } else {
                    binding.recyclerViewCurrency.apply {
                        setHasFixedSize(true)
                        layoutManager = LinearLayoutManager(this@ConverterActivity)
                        adapter = ConverterCurrencyAdapter(this@ConverterActivity, it.data)
                    }
                }
            }
        }

        binding.edtAmount.setOnClickListener {
            CurrencyListDialog.show(this, viewModel.currencies, selectedCurrency, selectedAmount) { amount, code ->
                selectedCurrency = code
                selectedAmount = amount
            }
        }
    }
}