package com.example.currencytest.list

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.currencytest.R
import com.example.currencytest.databinding.FragmentCurrencyListBinding


class CurrencyListFragment : Fragment(), CurrencyViewListener {
    private lateinit var binding: FragmentCurrencyListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentCurrencyListBinding.inflate(inflater, container, false).also {
        binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("curr","onViewCreated-curr")
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter =
            AdapterCurrency(
                (requireContext().applicationContext as SubApplication).provideDataSource()
                    .getCurrencyList(), this
            )
        Log.d("1", "onCreateList")
    }

    override fun openCurrency(currency: String) {

        requireActivity().supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.activity_main, CurrencyFragment.newInstance(currency))
            .commit()
    }

    companion object {
        fun newInstance(): CurrencyListFragment = CurrencyListFragment()
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("curr","onAttach-curr")
    }

    override fun onStart() {
        super.onStart()
        Log.d("curr","onStart-curr")
    }

    override fun onResume() {
        super.onResume()
        Log.d("curr","onResume-curr")
    }

    override fun onPause() {
        super.onPause()
        Log.d("curr","onPause-curr")
    }

    override fun onStop() {
        super.onStop()
        Log.d("curr","onStop-curr")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("curr","onDestroyView-curr")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("curr","onDestroy-curr")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("curr","onDetach-curr")
    }
}