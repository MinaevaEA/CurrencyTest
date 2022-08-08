package com.example.currencytest.currency_list


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.currencytest.R
import com.example.currencytest.currency.CurrencyFragment
import com.example.currencytest.databinding.FragmentCurrencyListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrencyListFragment : Fragment(), CurrencyViewListener {
    private lateinit var binding: FragmentCurrencyListBinding
    private lateinit var adapter: CurrencyListAdapter
    private val currencyListViewModel by viewModels<CurrencyListViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentCurrencyListBinding.inflate(inflater, container, false).also {
        binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = CurrencyListAdapter(this)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
        initObservers()
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                currencyListViewModel.searchNotes(newText)
                return true
            }
        })
    }

    override fun onCurrencyClicked(currencyPosition: String) {
        currencyListViewModel.onCurrencyClicked(currencyPosition)
    }

    private fun setVisibility(isVisible: Boolean, view: View) {
        val visibility = if (isVisible) View.VISIBLE else View.INVISIBLE
        view.visibility = visibility
    }

    private fun openAboutCurrency(currency: String) {
        val bundle = CurrencyFragment.newInstanceBundle(currency)
        findNavController().navigate(R.id.action_currency_list_to_about, bundle)
    }
//TODO уменьшить количество
    private fun initObservers() {
        currencyListViewModel.loadingListCurrency.observe(requireActivity()) {
            Log.d("listDebug", "view: ${it.size}")
            adapter.setData(it)
        }
        currencyListViewModel.progressBarVisibility.observe(requireActivity()) {
            setVisibility(it, binding.progressBar)
        }
        currencyListViewModel.errorTextViewVisibility.observe(requireActivity()) {
            setVisibility(it, binding.errorMsg)
        }
        currencyListViewModel.currencyListAdapterVisibility.observe(requireActivity()) {
            setVisibility(it, binding.recyclerView)
        }
        currencyListViewModel.onCurrencyClickedEvent.observe(requireActivity()) {
            openAboutCurrency(it)
        }

    }
    //TODO на удаление
/*
    companion object {
        fun newInstance(): CurrencyListFragment = CurrencyListFragment()
    }*/
}