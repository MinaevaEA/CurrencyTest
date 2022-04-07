package com.example.currencytest.currency


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.currencytest.databinding.FragmentBuyCurrencyListBinding
import com.example.currencytest.list.SubApplication
import kotlinx.coroutines.launch


class BuyCurrencyListFragment : Fragment() {
    private lateinit var binding: FragmentBuyCurrencyListBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentBuyCurrencyListBinding.inflate(inflater, container, false).also {
        binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView2.layoutManager = LinearLayoutManager(requireContext())
        lifecycleScope.launch {
            binding.recyclerView2.adapter = AdapterBuyCurrency(
                (requireContext().applicationContext as SubApplication).provideDataBase()
                    .currencyDao()
                    .getAll()
            )
        }
        Log.d("1", "onCreateList")
    }

    companion object {
        fun newInstance(): BuyCurrencyListFragment = BuyCurrencyListFragment()
    }

}