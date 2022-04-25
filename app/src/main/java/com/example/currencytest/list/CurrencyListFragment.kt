package com.example.currencytest.list


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.currencytest.R
import com.example.currencytest.currency.CurrencyFragment
import com.example.currencytest.databinding.FragmentCurrencyListBinding
import com.example.currencytest.retrofit.RetrofitServices
import retrofit2.*


class CurrencyListFragment : Fragment(), CurrencyViewListener {
    private lateinit var binding: FragmentCurrencyListBinding
    private lateinit var currencyListViewModel: CurrencyListViewModel

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
        val viewModelFactory = CurrencyViewModelFactory(storageDataNetwork)
        currencyListViewModel =
            ViewModelProvider(this, viewModelFactory)[CurrencyListViewModel::class.java]
        currencyListViewModel.onViewCreated()
        val adapter = CurrencyListAdapter(this)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
        currencyListViewModel.loadingListCurrency.observe(requireActivity()) {
            adapter.setData(it)
        }
        currencyListViewModel.progressBarVisibility.observe(requireActivity()) {
            setProgressBarVisibility(it)
        }
        currencyListViewModel.errorTextViewVisibility.observe(requireActivity()){
            setErrorTextViewVisibility(it)
        }
        currencyListViewModel.listCurrencyVisibility.observe(requireActivity()){
            setListCurrencyVisibility(it)
        }
        currencyListViewModel.onCurrencyClicked.observe(requireActivity()){
            requireActivity().supportFragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.activity_main, CurrencyFragment.newInstance(it))
                .commit()
        }
    }

    private fun setProgressBarVisibility(isVisible: Boolean) {
        val visibility = if (isVisible)  View.VISIBLE else View.INVISIBLE
        binding.progressBar.visibility = visibility
    }

    private fun setErrorTextViewVisibility(isVisible: Boolean) {
        val visibility = if (isVisible)  View.VISIBLE else View.INVISIBLE
        binding.errorMsg.visibility = visibility
    }

    private fun setListCurrencyVisibility(isVisible: Boolean) {
        val visibility = if (isVisible)  View.VISIBLE else View.INVISIBLE
        binding.recyclerView.visibility = visibility
    }

    override fun onCurrencyClicked(currencyPosition: String) {
        currencyListViewModel.onCurrencyClicked(currencyPosition)
    }

    companion object {
        fun newInstance(): CurrencyListFragment = CurrencyListFragment()
    }
}