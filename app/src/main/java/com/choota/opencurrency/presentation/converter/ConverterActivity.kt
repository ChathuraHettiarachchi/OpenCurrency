package com.choota.opencurrency.presentation.converter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.blongho.country_data.Country
import com.blongho.country_data.World
import com.choota.opencurrency.databinding.ActivityConverterBinding
import com.choota.opencurrency.domain.model.Currency
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
    private lateinit var countries: List<Country>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityConverterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()
        lifecycleScope.launch(Dispatchers.Main){
            viewModel.currencyState.collectLatest{

            }
        }

        binding.edtAmount.setOnClickListener {
            // trigger popup
        }
    }

    private fun initUI() {
        countries = World.getAllCountries()
        val defCountry = countries.last { it.currency.code.lowercase() == "usd" }

        defCountry.flagResource.let {
            binding.imgFlag.setImageResource(it)
        }
        binding.edtAmount.setCurrency(defCountry?.currency?.symbol)
        binding.edtAmount.setText("0")
    }
}