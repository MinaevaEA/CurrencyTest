package com.example.currencytest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.currencytest.databinding.FragmentMenuBinding


class MenuFragment : Fragment() {
    private lateinit var binding: FragmentMenuBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentMenuBinding.inflate(inflater, container, false).also {
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
        fun newInstance(): MenuFragment = MenuFragment()
    }
}







