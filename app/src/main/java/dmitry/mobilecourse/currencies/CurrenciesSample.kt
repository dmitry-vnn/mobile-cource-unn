package dmitry.mobilecourse.currencies

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.util.Xml
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.gridlayout.widget.GridLayout
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import dmitry.mobilecourse.databinding.ActivityCurrenciesBinding
import dmitry.mobilecourse.databinding.ItemCurrencyBinding
import org.xmlpull.v1.XmlPullParser
import java.io.ByteArrayInputStream
import java.nio.charset.Charset

class CurrenciesActivity : AppCompatActivity(),
    CurrenciesModel.Observer {

    private lateinit var column: GridLayout
    private lateinit var currenciesModel: CurrenciesModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityCurrenciesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        column = binding.column
        currenciesModel = CurrenciesModel(this, this)
        currenciesModel.loadAsyncFromCentralBank()
    }

    override fun onCurrencyAdd(currency: Currency) {
        val currencyView = loadCurrencyView(currency)
        column.addView(currencyView)
    }

    private fun loadCurrencyView(currency: Currency): View {
        val binding = ItemCurrencyBinding.inflate(layoutInflater)

        binding.currencyNameText.text = currency.name
        binding.currencyValueText.text = currency.roubleValue.toString().replace(".", ",")

        return binding.root
    }

}

data class Currency(val name: String, val roubleValue: Double)

class CurrenciesModel(
    private val observer: Observer,
    private val context: Context
) {

    fun loadAsyncFromCentralBank() {
        val stringRequest = StringRequest(
            Request.Method.GET, "https://www.cbr.ru/scripts/XML_daily.asp",
            { response ->
                Log.i("Currencies", "Loaded data: $response")
                val parsedCurrencies = CurrenciesParser(response).parse()
                Log.i("Currencies", "Parsed: $parsedCurrencies")
                parsedCurrencies.forEach(::addCurrency)
            },
            { Log.e("Currencies", "Error while loading currencies from cbr") }
        )

        val queue = Volley.newRequestQueue(context)
        queue.add(stringRequest)
    }

    interface Observer {
        fun onCurrencyAdd(currency: Currency)
    }

    private val currencies = mutableListOf<Currency>()

    fun addCurrency(currency: Currency) {
        currencies += currency
        observer.onCurrencyAdd(currency)
    }

}

private class CurrenciesParser(private val xml: String) {

    fun parse(): List<Currency> {
        val currencies = mutableListOf<Currency>()

        val inputStream = ByteArrayInputStream(xml.toByteArray(Charset.forName("Cp1251")))

        inputStream.use { input ->
            val parser: XmlPullParser = Xml.newPullParser()
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false)
            parser.setInput(input, null)

            var eventType = parser.eventType
            var name: String? = null
            var value: Double? = null

            try {
                while (eventType != XmlPullParser.END_DOCUMENT) {
                    when (eventType) {
                        XmlPullParser.START_TAG -> {
                            when (parser.name) {
                                "Valute" -> {
                                    name = null
                                    value = null
                                    // Получаем атрибуты, например, ID валюты
                                    val id = parser.getAttributeValue(null, "ID")
                                    // Можно использовать этот ID, если нужно
                                }
                                "Name" -> name = parser.nextText()
                                "Value" -> value = parser.nextText().replace(",", ".").toDouble()
                            }
                        }
                        XmlPullParser.END_TAG -> {
                            if (parser.name == "Valute") {
                                if (name != null && value != null) {
                                    currencies.add(Currency(name, value))
                                }
                            }
                        }
                    }
                    eventType = parser.next()
                }
            } catch (e: Exception) {
                Log.e("Currencies", "Error while parsing currencies xml")
                e.printStackTrace()
            }

        }

        return currencies
    }

}
