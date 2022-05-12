package com.example.currencytest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.currencytest.currency_buy.BuyCurrencyListFragment
import com.example.currencytest.databinding.FragmentMainBinding
import com.example.currencytest.currency_list.CurrencyListFragment


class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentMainBinding.inflate(inflater, container, false).also {
        binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnCurrency.setOnClickListener {
            findNavController().navigate(R.id.currencyListFragment)
            /*requireActivity().supportFragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.activity_main, CurrencyListFragment.newInstance())
                .commit()*/
        }
        binding.buy.setOnClickListener {
            findNavController().navigate(R.id.buyCurrencyListFragment)
            /*requireActivity().supportFragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.activity_main, BuyCurrencyListFragment.newInstance())
                .commit()*/
        }

    }

    companion object {
        fun newInstance(): MainFragment = MainFragment()
    }
}







