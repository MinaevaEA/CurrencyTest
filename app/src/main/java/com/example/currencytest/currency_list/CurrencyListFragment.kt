package com.example.currencytest.currency_list


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.currencytest.R
import com.example.currencytest.SubApplication
import com.example.currencytest.currency.CurrencyFragment
import com.example.currencytest.databinding.FragmentCurrencyListBinding
import com.example.currencytest.retrofit.RetrofitServices
import retrofit2.*


class CurrencyListFragment : Fragment(), CurrencyViewListener {
    private lateinit var binding: FragmentCurrencyListBinding
    private lateinit var currencyListViewModel: CurrencyListViewModel
    private lateinit var adapter: CurrencyListAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentCurrencyListBinding.inflate(inflater, container, false).also {
        binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dataFromNetwork: RetrofitServices =
            (requireContext().applicationContext as SubApplication).provideDataFromNetwork()
                .create()
        val storageDataNetwork = DataNetworkInteract(dataFromNetwork)
        val viewModelFactory = CurrencyListViewModelFactory(storageDataNetwork)
        currencyListViewModel =
            ViewModelProvider(this, viewModelFactory)[CurrencyListViewModel::class.java]
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
                //TODO не работает поиск
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
        val bundle = CurrencyFragment.newInst3(currency)
       // this.findNavController().navigate
        findNavController().navigate(R.id.action_currency_list_to_about, bundle)
        //TODO неккоректная работа отображения конкретной валюты
        /*requireActivity().supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.activity_main, CurrencyFragment.newInstance(currency))
            .commit()*/
    }

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

    companion object {
        fun newInstance(): CurrencyListFragment = CurrencyListFragment()
    }
}