package com.example.currencytest.currency


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.currencytest.R
import com.example.currencytest.databinding.FragmentAboutCurrencyBinding
import com.example.currencytest.currency_list.CurrencyDetail
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrencyFragment : Fragment() {
    private lateinit var binding: FragmentAboutCurrencyBinding
    private val currencyViewModel by viewModels<CurrencyViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentAboutCurrencyBinding.inflate(inflater, container, false).also {
        binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        currencyViewModel.onCreate()
        initObs()
        binding.buyButton.setOnClickListener {
            val title = binding.title.text
            val numberOfBuy = binding.numberOfBuy.text.toString().toIntOrNull() ?: 0
            val price = binding.country.text.toString().toDoubleOrNull() ?: 0.0
            val date = binding.date.text.toString()
            currencyViewModel.onClickedBuyCurrency(
                title = title.toString(),
                price = price.toString(),
                date = date,
                number = numberOfBuy
            )
        }
    }

    private fun openSnackBar() {
        Snackbar.make(binding.root, "Покупка произведена!", Snackbar.LENGTH_LONG)
            .setAction("Перейти к покупкам") {
                currencyViewModel.onClickedSnackBar()
            }.show()
    }

    private fun goToBuyList() {
        findNavController().navigate(R.id.buyCurrencyListFragment)
    }
    private fun goToMain() {
        findNavController().navigate(R.id.currencyListFragment)
    }

    private fun setVisibility(isVisible: Boolean, view: View) {
        val visibility = if (isVisible) View.VISIBLE else View.INVISIBLE
        view.visibility = visibility
    }

    private fun showCurrency(currency: CurrencyDetail) {
        binding.country.text = currency.rub.toString()
        binding.date.text = currency.date
    }

    //TODO сократить подписки
    private fun initObs() {
        currencyViewModel.argumentMain.observe(requireActivity()) {
            binding.title.text = it
        }
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
            setVisibility(it, binding.country)
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
            openSnackBar()
        }
        currencyViewModel.clinkedToBuyList.observe(requireActivity()) {
            goToBuyList()
        }
        currencyViewModel.clinkedToMain.observe(requireActivity()) {
            goToMain()
        }
    }

    companion object {
        const val TAG_FOR_CURRENCY = "TAG_FOR_CURRENCY"
        fun newInstance(currency: String): CurrencyFragment = CurrencyFragment().apply {
            arguments = Bundle().apply { putString(TAG_FOR_CURRENCY, currency) }
        }

        fun newInstanceBundle(currency: String): Bundle {
            val b = Bundle()
            b.putString(TAG_FOR_CURRENCY, currency)
            return b
        }
    }
}
