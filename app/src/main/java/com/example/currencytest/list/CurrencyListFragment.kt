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
import com.example.currencytest.retrofit.Currency
import com.example.currencytest.retrofit.RetrofitServices
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class CurrencyListFragment : Fragment(), CurrencyViewListener {
    private lateinit var binding: FragmentCurrencyListBinding
    private lateinit var mService: RetrofitServices
    private lateinit var adapter: AdapterCurrency
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentCurrencyListBinding.inflate(inflater, container, false).also {
        binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("curr", "onViewCreated-curr")
        mService = Currency.retrofitService
        adapter = AdapterCurrency(this)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
        Log.d("1", "onCreateList")
        mService.getAll().enqueue(object : Callback<JsonObject> {

            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                Log.d("main", "onResponse")
                // Log.d("main", "${response.body()}")
                val gson = Gson()
                val i = object : TypeToken<Map<String, String>>() {}.type // указание формата парсинга данных
                val list: Map<String, String> = gson.fromJson(response.body(), i) // с помощью вспомогательного объекта gson конвертируем тело ответа сервера в нужном формате парсинга данных
                val list2 = list.map { i -> DataCurrency(i.key, i.value) }
                Log.d("main", "$list2")
                adapter.setData(list2)
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {

                Log.d("main", "onFailure")
            }
        })

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
        Log.d("curr", "onAttach-curr")
    }

    override fun onStart() {
        super.onStart()
        Log.d("curr", "onStart-curr")
    }

    override fun onResume() {
        super.onResume()
        Log.d("curr", "onResume-curr")
    }

    override fun onPause() {
        super.onPause()
        Log.d("curr", "onPause-curr")
    }

    override fun onStop() {
        super.onStop()
        Log.d("curr", "onStop-curr")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("curr", "onDestroyView-curr")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("curr", "onDestroy-curr")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("curr", "onDetach-curr")
    }
}