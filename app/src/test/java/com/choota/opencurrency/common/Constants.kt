package com.choota.opencurrency.common

import com.choota.opencurrency.data.remote.dto.CurrencyDto
import com.choota.opencurrency.data.remote.dto.RateDto
import com.choota.opencurrency.data.remote.dto.asList
import com.choota.opencurrency.data.remote.dto.asRateList
import com.choota.opencurrency.domain.model.Currency
import com.choota.opencurrency.domain.model.Rate
import com.google.gson.Gson

object Constants {
    const val rates = "{\n" +
            "\"disclaimer\": \"Usage subject to terms: https://openexchangerates.org/terms\",\n" +
            "\"license\": \"https://openexchangerates.org/license\",\n" +
            "\"timestamp\": 1669525224,\n" +
            "\"base\": \"USD\",\n" +
            "\"rates\": {\n" +
            "\"AED\": 3.673,\n" +
            "\"AFN\": 87.499995,\n" +
            "\"ALL\": 112.36927,\n" +
            "\"AMD\": 395.341739,\n" +
            "\"ANG\": 1.801564,\n" +
            "\"AOA\": 511.877,\n" +
            "\"ARS\": 165.306735,\n" +
            "\"AUD\": 1.481262,\n" +
            "\"AWG\": 1.8,\n" +
            "\"AZN\": 1.7,\n" +
            "\"BAM\": 1.881637,\n" +
            "\"BBD\": 2,\n" +
            "\"BDT\": 101.810327,\n" +
            "\"BGN\": 1.879,\n" +
            "\"BHD\": 0.377104,\n" +
            "\"BIF\": 2058,\n" +
            "\"BMD\": 1,\n" +
            "\"BND\": 1.375382,\n" +
            "\"BOB\": 6.907247,\n" +
            "\"BRL\": 5.41,\n" +
            "\"BSD\": 1,\n" +
            "\"BTC\": 0.000060417357,\n" +
            "\"BTN\": 81.594545,\n" +
            "\"BWP\": 12.898251,\n" +
            "\"BYN\": 2.524076,\n" +
            "\"BZD\": 2.014854,\n" +
            "\"CAD\": 1.339675,\n" +
            "\"CDF\": 2053,\n" +
            "\"CHF\": 0.945993,\n" +
            "\"CLF\": 0.033423,\n" +
            "\"CLP\": 922.23,\n" +
            "\"CNH\": 7.19404,\n" +
            "\"CNY\": 7.17445,\n" +
            "\"COP\": 4897.991686,\n" +
            "\"CRC\": 606.793721,\n" +
            "\"CUC\": 1,\n" +
            "\"CUP\": 25.75,\n" +
            "\"CVE\": 106.375,\n" +
            "\"CZK\": 23.4259,\n" +
            "\"DJF\": 177.5,\n" +
            "\"DKK\": 7.1532,\n" +
            "\"DOP\": 54.5,\n" +
            "\"DZD\": 138.25696,\n" +
            "\"EGP\": 24.5459,\n" +
            "\"ERN\": 15,\n" +
            "\"ETB\": 53,\n" +
            "\"EUR\": 0.9602,\n" +
            "\"FJD\": 2.21395,\n" +
            "\"FKP\": 0.827062,\n" +
            "\"GBP\": 0.827062,\n" +
            "\"GEL\": 2.725,\n" +
            "\"GGP\": 0.827062,\n" +
            "\"GHS\": 14.5,\n" +
            "\"GIP\": 0.827062,\n" +
            "\"GMD\": 61.5,\n" +
            "\"GNF\": 8800,\n" +
            "\"GTQ\": 7.806875,\n" +
            "\"GYD\": 209.133901,\n" +
            "\"HKD\": 7.81415,\n" +
            "\"HNL\": 24.824999,\n" +
            "\"HRK\": 7.2523,\n" +
            "\"HTG\": 137.425697,\n" +
            "\"HUF\": 392.79,\n" +
            "\"IDR\": 15693.5,\n" +
            "\"ILS\": 3.422195,\n" +
            "\"IMP\": 0.827062,\n" +
            "\"INR\": 81.6699,\n" +
            "\"IQD\": 1460.5,\n" +
            "\"IRR\": 42400,\n" +
            "\"ISK\": 140.84,\n" +
            "\"JEP\": 0.827062,\n" +
            "\"JMD\": 154.02538,\n" +
            "\"JOD\": 0.709,\n" +
            "\"JPY\": 139.12501514,\n" +
            "\"KES\": 122.3,\n" +
            "\"KGS\": 84.073223,\n" +
            "\"KHR\": 4137.5,\n" +
            "\"KMF\": 473.599212,\n" +
            "\"KPW\": 900,\n" +
            "\"KRW\": 1335.56,\n" +
            "\"KWD\": 0.307253,\n" +
            "\"KYD\": 0.832965,\n" +
            "\"KZT\": 465.275868,\n" +
            "\"LAK\": 16935,\n" +
            "\"LBP\": 1515.037738,\n" +
            "\"LKR\": 367.357091,\n" +
            "\"LRD\": 154.000004,\n" +
            "\"LSL\": 17.135641,\n" +
            "\"LYD\": 4.915,\n" +
            "\"MAD\": 10.693,\n" +
            "\"MDL\": 19.236397,\n" +
            "\"MGA\": 4340,\n" +
            "\"MKD\": 59.209321,\n" +
            "\"MMK\": 2099.180867,\n" +
            "\"MNT\": 3406.965265,\n" +
            "\"MOP\": 8.04641,\n" +
            "\"MRU\": 38.08,\n" +
            "\"MUR\": 43.66,\n" +
            "\"MVR\": 15.38,\n" +
            "\"MWK\": 1025,\n" +
            "\"MXN\": 19.3406,\n" +
            "\"MYR\": 4.48,\n" +
            "\"MZN\": 63.850001,\n" +
            "\"NAD\": 16.977929,\n" +
            "\"NGN\": 443.26,\n" +
            "\"NIO\": 36,\n" +
            "\"NOK\": 9.8788,\n" +
            "\"NPR\": 130.55166,\n" +
            "\"NZD\": 1.600769,\n" +
            "\"OMR\": 0.38507,\n" +
            "\"PAB\": 1,\n" +
            "\"PEN\": 3.852881,\n" +
            "\"PGK\": 3.52,\n" +
            "\"PHP\": 56.7,\n" +
            "\"PKR\": 223.5,\n" +
            "\"PLN\": 4.511498,\n" +
            "\"PYG\": 7205.102535,\n" +
            "\"QAR\": 3.641,\n" +
            "\"RON\": 4.7312,\n" +
            "\"RSD\": 112.640041,\n" +
            "\"RUB\": 60.749905,\n" +
            "\"RWF\": 1055,\n" +
            "\"SAR\": 3.757682,\n" +
            "\"SBD\": 8.230592,\n" +
            "\"SCR\": 13.601923,\n" +
            "\"SDG\": 569.5,\n" +
            "\"SEK\": 10.4347,\n" +
            "\"SGD\": 1.375703,\n" +
            "\"SHP\": 0.827062,\n" +
            "\"SLL\": 17665,\n" +
            "\"SOS\": 568.5,\n" +
            "\"SRD\": 30.883261,\n" +
            "\"SSP\": 130.26,\n" +
            "\"STD\": 22823.990504,\n" +
            "\"STN\": 23.75,\n" +
            "\"SVC\": 8.746224,\n" +
            "\"SYP\": 2512.53,\n" +
            "\"SZL\": 17.134844,\n" +
            "\"THB\": 35.690623,\n" +
            "\"TJS\": 10.046228,\n" +
            "\"TMT\": 3.5,\n" +
            "\"TND\": 3.2425,\n" +
            "\"TOP\": 2.355614,\n" +
            "\"TRY\": 18.6168,\n" +
            "\"TTD\": 6.787181,\n" +
            "\"TWD\": 30.9435,\n" +
            "\"TZS\": 2332,\n" +
            "\"UAH\": 36.917445,\n" +
            "\"UGX\": 3738.612882,\n" +
            "\"USD\": 1,\n" +
            "\"UYU\": 39.18363,\n" +
            "\"UZS\": 11222,\n" +
            "\"VES\": 10.222,\n" +
            "\"VND\": 24798.515521,\n" +
            "\"VUV\": 122.25878,\n" +
            "\"WST\": 2.793855,\n" +
            "\"XAF\": 629.849742,\n" +
            "\"XAG\": 0.04629639,\n" +
            "\"XAU\": 0.00056993,\n" +
            "\"XCD\": 2.70255,\n" +
            "\"XDR\": 0.743991,\n" +
            "\"XOF\": 629.849742,\n" +
            "\"XPD\": 0.00053864,\n" +
            "\"XPF\": 114.582308,\n" +
            "\"XPT\": 0.00101627,\n" +
            "\"YER\": 250.200087,\n" +
            "\"ZAR\": 17.08777,\n" +
            "\"ZMW\": 16.918785,\n" +
            "\"ZWL\": 322\n" +
            "}\n" +
            "}"

    const val currencies = "{\n" +
            "  \"AED\": \"United Arab Emirates Dirham\",\n" +
            "  \"AFN\": \"Afghan Afghani\",\n" +
            "  \"ALL\": \"Albanian Lek\",\n" +
            "  \"AMD\": \"Armenian Dram\",\n" +
            "  \"ANG\": \"Netherlands Antillean Guilder\",\n" +
            "  \"AOA\": \"Angolan Kwanza\",\n" +
            "  \"ARS\": \"Argentine Peso\",\n" +
            "  \"AUD\": \"Australian Dollar\",\n" +
            "  \"AWG\": \"Aruban Florin\",\n" +
            "  \"AZN\": \"Azerbaijani Manat\",\n" +
            "  \"BAM\": \"Bosnia-Herzegovina Convertible Mark\",\n" +
            "  \"BBD\": \"Barbadian Dollar\",\n" +
            "  \"BDT\": \"Bangladeshi Taka\",\n" +
            "  \"BGN\": \"Bulgarian Lev\",\n" +
            "  \"BHD\": \"Bahraini Dinar\",\n" +
            "  \"BIF\": \"Burundian Franc\",\n" +
            "  \"BMD\": \"Bermudan Dollar\",\n" +
            "  \"BND\": \"Brunei Dollar\",\n" +
            "  \"BOB\": \"Bolivian Boliviano\",\n" +
            "  \"BRL\": \"Brazilian Real\",\n" +
            "  \"BSD\": \"Bahamian Dollar\",\n" +
            "  \"BTC\": \"Bitcoin\",\n" +
            "  \"BTN\": \"Bhutanese Ngultrum\",\n" +
            "  \"BWP\": \"Botswanan Pula\",\n" +
            "  \"BYN\": \"Belarusian Ruble\",\n" +
            "  \"BZD\": \"Belize Dollar\",\n" +
            "  \"CAD\": \"Canadian Dollar\",\n" +
            "  \"CDF\": \"Congolese Franc\",\n" +
            "  \"CHF\": \"Swiss Franc\",\n" +
            "  \"CLF\": \"Chilean Unit of Account (UF)\",\n" +
            "  \"CLP\": \"Chilean Peso\",\n" +
            "  \"CNH\": \"Chinese Yuan (Offshore)\",\n" +
            "  \"CNY\": \"Chinese Yuan\",\n" +
            "  \"COP\": \"Colombian Peso\",\n" +
            "  \"CRC\": \"Costa Rican Colón\",\n" +
            "  \"CUC\": \"Cuban Convertible Peso\",\n" +
            "  \"CUP\": \"Cuban Peso\",\n" +
            "  \"CVE\": \"Cape Verdean Escudo\",\n" +
            "  \"CZK\": \"Czech Republic Koruna\",\n" +
            "  \"DJF\": \"Djiboutian Franc\",\n" +
            "  \"DKK\": \"Danish Krone\",\n" +
            "  \"DOP\": \"Dominican Peso\",\n" +
            "  \"DZD\": \"Algerian Dinar\",\n" +
            "  \"EGP\": \"Egyptian Pound\",\n" +
            "  \"ERN\": \"Eritrean Nakfa\",\n" +
            "  \"ETB\": \"Ethiopian Birr\",\n" +
            "  \"EUR\": \"Euro\",\n" +
            "  \"FJD\": \"Fijian Dollar\",\n" +
            "  \"FKP\": \"Falkland Islands Pound\",\n" +
            "  \"GBP\": \"British Pound Sterling\",\n" +
            "  \"GEL\": \"Georgian Lari\",\n" +
            "  \"GGP\": \"Guernsey Pound\",\n" +
            "  \"GHS\": \"Ghanaian Cedi\",\n" +
            "  \"GIP\": \"Gibraltar Pound\",\n" +
            "  \"GMD\": \"Gambian Dalasi\",\n" +
            "  \"GNF\": \"Guinean Franc\",\n" +
            "  \"GTQ\": \"Guatemalan Quetzal\",\n" +
            "  \"GYD\": \"Guyanaese Dollar\",\n" +
            "  \"HKD\": \"Hong Kong Dollar\",\n" +
            "  \"HNL\": \"Honduran Lempira\",\n" +
            "  \"HRK\": \"Croatian Kuna\",\n" +
            "  \"HTG\": \"Haitian Gourde\",\n" +
            "  \"HUF\": \"Hungarian Forint\",\n" +
            "  \"IDR\": \"Indonesian Rupiah\",\n" +
            "  \"ILS\": \"Israeli New Sheqel\",\n" +
            "  \"IMP\": \"Manx pound\",\n" +
            "  \"INR\": \"Indian Rupee\",\n" +
            "  \"IQD\": \"Iraqi Dinar\",\n" +
            "  \"IRR\": \"Iranian Rial\",\n" +
            "  \"ISK\": \"Icelandic Króna\",\n" +
            "  \"JEP\": \"Jersey Pound\",\n" +
            "  \"JMD\": \"Jamaican Dollar\",\n" +
            "  \"JOD\": \"Jordanian Dinar\",\n" +
            "  \"JPY\": \"Japanese Yen\",\n" +
            "  \"KES\": \"Kenyan Shilling\",\n" +
            "  \"KGS\": \"Kyrgystani Som\",\n" +
            "  \"KHR\": \"Cambodian Riel\",\n" +
            "  \"KMF\": \"Comorian Franc\",\n" +
            "  \"KPW\": \"North Korean Won\",\n" +
            "  \"KRW\": \"South Korean Won\",\n" +
            "  \"KWD\": \"Kuwaiti Dinar\",\n" +
            "  \"KYD\": \"Cayman Islands Dollar\",\n" +
            "  \"KZT\": \"Kazakhstani Tenge\",\n" +
            "  \"LAK\": \"Laotian Kip\",\n" +
            "  \"LBP\": \"Lebanese Pound\",\n" +
            "  \"LKR\": \"Sri Lankan Rupee\",\n" +
            "  \"LRD\": \"Liberian Dollar\",\n" +
            "  \"LSL\": \"Lesotho Loti\",\n" +
            "  \"LYD\": \"Libyan Dinar\",\n" +
            "  \"MAD\": \"Moroccan Dirham\",\n" +
            "  \"MDL\": \"Moldovan Leu\",\n" +
            "  \"MGA\": \"Malagasy Ariary\",\n" +
            "  \"MKD\": \"Macedonian Denar\",\n" +
            "  \"MMK\": \"Myanma Kyat\",\n" +
            "  \"MNT\": \"Mongolian Tugrik\",\n" +
            "  \"MOP\": \"Macanese Pataca\",\n" +
            "  \"MRU\": \"Mauritanian Ouguiya\",\n" +
            "  \"MUR\": \"Mauritian Rupee\",\n" +
            "  \"MVR\": \"Maldivian Rufiyaa\",\n" +
            "  \"MWK\": \"Malawian Kwacha\",\n" +
            "  \"MXN\": \"Mexican Peso\",\n" +
            "  \"MYR\": \"Malaysian Ringgit\",\n" +
            "  \"MZN\": \"Mozambican Metical\",\n" +
            "  \"NAD\": \"Namibian Dollar\",\n" +
            "  \"NGN\": \"Nigerian Naira\",\n" +
            "  \"NIO\": \"Nicaraguan Córdoba\",\n" +
            "  \"NOK\": \"Norwegian Krone\",\n" +
            "  \"NPR\": \"Nepalese Rupee\",\n" +
            "  \"NZD\": \"New Zealand Dollar\",\n" +
            "  \"OMR\": \"Omani Rial\",\n" +
            "  \"PAB\": \"Panamanian Balboa\",\n" +
            "  \"PEN\": \"Peruvian Nuevo Sol\",\n" +
            "  \"PGK\": \"Papua New Guinean Kina\",\n" +
            "  \"PHP\": \"Philippine Peso\",\n" +
            "  \"PKR\": \"Pakistani Rupee\",\n" +
            "  \"PLN\": \"Polish Zloty\",\n" +
            "  \"PYG\": \"Paraguayan Guarani\",\n" +
            "  \"QAR\": \"Qatari Rial\",\n" +
            "  \"RON\": \"Romanian Leu\",\n" +
            "  \"RSD\": \"Serbian Dinar\",\n" +
            "  \"RUB\": \"Russian Ruble\",\n" +
            "  \"RWF\": \"Rwandan Franc\",\n" +
            "  \"SAR\": \"Saudi Riyal\",\n" +
            "  \"SBD\": \"Solomon Islands Dollar\",\n" +
            "  \"SCR\": \"Seychellois Rupee\",\n" +
            "  \"SDG\": \"Sudanese Pound\",\n" +
            "  \"SEK\": \"Swedish Krona\",\n" +
            "  \"SGD\": \"Singapore Dollar\",\n" +
            "  \"SHP\": \"Saint Helena Pound\",\n" +
            "  \"SLL\": \"Sierra Leonean Leone\",\n" +
            "  \"SOS\": \"Somali Shilling\",\n" +
            "  \"SRD\": \"Surinamese Dollar\",\n" +
            "  \"SSP\": \"South Sudanese Pound\",\n" +
            "  \"STD\": \"São Tomé and Príncipe Dobra (pre-2018)\",\n" +
            "  \"STN\": \"São Tomé and Príncipe Dobra\",\n" +
            "  \"SVC\": \"Salvadoran Colón\",\n" +
            "  \"SYP\": \"Syrian Pound\",\n" +
            "  \"SZL\": \"Swazi Lilangeni\",\n" +
            "  \"THB\": \"Thai Baht\",\n" +
            "  \"TJS\": \"Tajikistani Somoni\",\n" +
            "  \"TMT\": \"Turkmenistani Manat\",\n" +
            "  \"TND\": \"Tunisian Dinar\",\n" +
            "  \"TOP\": \"Tongan Pa'anga\",\n" +
            "  \"TRY\": \"Turkish Lira\",\n" +
            "  \"TTD\": \"Trinidad and Tobago Dollar\",\n" +
            "  \"TWD\": \"New Taiwan Dollar\",\n" +
            "  \"TZS\": \"Tanzanian Shilling\",\n" +
            "  \"UAH\": \"Ukrainian Hryvnia\",\n" +
            "  \"UGX\": \"Ugandan Shilling\",\n" +
            "  \"USD\": \"United States Dollar\",\n" +
            "  \"UYU\": \"Uruguayan Peso\",\n" +
            "  \"UZS\": \"Uzbekistan Som\",\n" +
            "  \"VEF\": \"Venezuelan Bolívar Fuerte (Old)\",\n" +
            "  \"VES\": \"Venezuelan Bolívar Soberano\",\n" +
            "  \"VND\": \"Vietnamese Dong\",\n" +
            "  \"VUV\": \"Vanuatu Vatu\",\n" +
            "  \"WST\": \"Samoan Tala\",\n" +
            "  \"XAF\": \"CFA Franc BEAC\",\n" +
            "  \"XAG\": \"Silver Ounce\",\n" +
            "  \"XAU\": \"Gold Ounce\",\n" +
            "  \"XCD\": \"East Caribbean Dollar\",\n" +
            "  \"XDR\": \"Special Drawing Rights\",\n" +
            "  \"XOF\": \"CFA Franc BCEAO\",\n" +
            "  \"XPD\": \"Palladium Ounce\",\n" +
            "  \"XPF\": \"CFP Franc\",\n" +
            "  \"XPT\": \"Platinum Ounce\",\n" +
            "  \"YER\": \"Yemeni Rial\",\n" +
            "  \"ZAR\": \"South African Rand\",\n" +
            "  \"ZMW\": \"Zambian Kwacha\",\n" +
            "  \"ZWL\": \"Zimbabwean Dollar\"\n" +
            "}"

    fun currencyList(): List<Currency> {
        val _currencies = Gson().fromJson(Constants.currencies, CurrencyDto::class.java)
        val _rates = Gson().fromJson(Constants.rates, RateDto::class.java)
        val _result: MutableList<Currency> = mutableListOf()

        _rates.asRateList().forEachIndexed { i, rate ->
            val filtered = _currencies.asList().filter { currency -> currency.code == rate.code }
            _result.add(
                Currency(
                    i.toLong(),
                    filtered.first().code,
                    filtered.first().name,
                    rate.rate,
                )
            )
        }

        return _result
    }

    fun isTestMode(): Boolean {
        val result: Boolean = try {
            Class.forName("com.choota.opencurrency.presentation.converter.ConverterViewModelTest")
            true
        } catch (e: Exception) {
            false
        }
        return result
    }
}