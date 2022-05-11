package com.example.currencytest.currency_buy


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.currencytest.databinding.FragmentBuyCurrencyListBinding
import com.example.currencytest.SubApplication


class BuyCurrencyListFragment : Fragment() {
    private lateinit var binding: FragmentBuyCurrencyListBinding
    private lateinit var adapter: AdapterBuyCurrency
    private lateinit var buyCurrencyViewModel: BuyCurrencyListViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentBuyCurrencyListBinding.inflate(inflater, container, false).also {
        binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dataBase =
            (requireContext().applicationContext as SubApplication).provideDataBase()
        val storageDataBuyCurrency = BuyCurrencyListInteractor(dataBase)
        val viewModelFactory = BuyCurrencyListViewModelFactory(storageDataBuyCurrency)
        buyCurrencyViewModel =
            ViewModelProvider(this, viewModelFactory)[BuyCurrencyListViewModel::class.java]
        adapter = AdapterBuyCurrency()
        buyCurrencyViewModel.onViewCreatedBuy()
        binding.recyclerViewBuy.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewBuy.adapter = adapter
        initObsBuy()
    }

    private fun initObsBuy() {
        buyCurrencyViewModel.loadingListBuyCurrency.observe(requireActivity()) {
            adapter.setDataCurrency(it)
        }
    }

    companion object {
        fun newInstance(): BuyCurrencyListFragment = BuyCurrencyListFragment()
    }

}