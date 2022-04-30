package com.example.currencytest.currency


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.currencytest.MainFragment
import com.example.currencytest.R
import com.example.currencytest.databinding.FragmentAboutCurrencyBinding
import com.example.currencytest.SubApplication
import com.example.currencytest.currency_list.CurrencyDetail
import com.example.currencytest.retrofit.RetrofitServices
import com.google.android.material.snackbar.Snackbar
import retrofit2.create

class CurrencyFragment : Fragment() {
    private lateinit var binding: FragmentAboutCurrencyBinding
    private lateinit var currencyViewModel: CurrencyViewModel
    private lateinit var currency: String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentAboutCurrencyBinding.inflate(inflater, container, false).also {
        binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dataBase =
            (requireContext().applicationContext as SubApplication).provideDataBase()
        currency = arguments?.getString(TAG_FOR_CURRENCY, "") ?: ""
        val dataConcreteCurrency: RetrofitServices =
            (requireContext().applicationContext as SubApplication).provideDataFromNetwork()
                .create()
        val storageDataCurrency = CurrencyInteract(dataConcreteCurrency, dataBase)
        val viewModelFactory = CurrencyViewModelFactory(storageDataCurrency, currency)
        currencyViewModel = ViewModelProvider(this, viewModelFactory)[CurrencyViewModel::class.java]
        currencyViewModel.onCreate()
        binding.title.text = currency
        initObs()
        binding.buyButton.setOnClickListener {

            val title = binding.title.text
            val numberOfBuy = binding.numberOfBuy.text.toString().toIntOrNull() ?: 0
            val price = binding.price.text.toString().toDoubleOrNull() ?: 0.0
            val date = binding.date
            currencyViewModel.onClickedBuyCurrency(
                title = title.toString(),
                price = price.toString(),
                date = date.toString(),
                number = numberOfBuy
            )
        }
    }

    fun openSnackbar() {
        Snackbar.make(binding.root, "Покупка произведена!", Snackbar.LENGTH_LONG)
            .setAction("Вернуться в меню", OnClickListener {
                currencyViewModel.onClickedSnackBar()
            }).show()
    }

    private fun goToMain() {
        requireActivity().supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.activity_main, MainFragment.newInstance())
            .commit()
    }

    private fun setVisibility(isVisible: Boolean, view: View) {
        val visibility = if (isVisible) View.VISIBLE else View.INVISIBLE
        view.visibility = visibility
    }

    private fun showCurrency(currency: CurrencyDetail) {
        binding.price.text = currency.rub.toString()
        binding.date.text = currency.date
    }

    private fun initObs() {
        currencyViewModel.dataCurrency.observe(requireActivity()) {
            showCurrency(it)
        }
        currencyViewModel.errorTextViewVisibility.observe(requireActivity()) {
            setVisibility(it, binding.errorMsg)
        }
        currencyViewModel.progressBarVisibility.observe(requireActivity()) {
            setVisibility(it, binding.progressBar)
        }
        currencyViewModel.priceVisibility.observe(requireActivity()) {
            setVisibility(it, binding.price)
        }
        currencyViewModel.dateVisibility.observe(requireActivity()) {
            setVisibility(it, binding.date)
        }
        currencyViewModel.titleVisibility.observe(requireActivity()) {
            setVisibility(it, binding.title)
        }
        currencyViewModel.numberOfBuyVisibility.observe(requireActivity()) {
            setVisibility(it, binding.numberOfBuy)
        }
        currencyViewModel.buyButtonVisibility.observe(requireActivity()) {
            setVisibility(it, binding.buyButton)
        }
        currencyViewModel.showToast.observe(requireActivity()) {
            Toast.makeText(requireContext(), "$it!", Toast.LENGTH_SHORT)
                .show()
        }
        currencyViewModel.showSnackBar.observe(requireActivity()) {
            openSnackbar()
        }
        currencyViewModel.backToMain.observe(requireActivity()) {
            goToMain()
        }
    }

    companion object {
        const val TAG_FOR_CURRENCY = "TAG_FOR_CURRENCY"
        fun newInstance(currency: String): CurrencyFragment = CurrencyFragment().apply {
            arguments = Bundle().apply { putString(TAG_FOR_CURRENCY, currency) }
        }

/*        fun newInsta2(currency: String): CurrencyFragment {
            val i = CurrencyFragment()
            val b = Bundle()
            b.putString(TAG_FOR_CURRENCY, currency)
            i.arguments = b

            return i
        }*/
    }
}
