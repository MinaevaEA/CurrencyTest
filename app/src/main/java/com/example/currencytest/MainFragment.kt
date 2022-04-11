package com.example.currencytest

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.currencytest.currency.BuyCurrencyListFragment
import com.example.currencytest.databinding.FragmentMainBinding
import com.example.currencytest.list.CurrencyListFragment
import com.example.currencytest.retrofit.Currency
import com.example.currencytest.retrofit.RecponseTest2
import com.example.currencytest.retrofit.RetrofitServices
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private lateinit var mService: RetrofitServices
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentMainBinding.inflate(inflater, container, false).also {
        binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("main", "onViewCreated-main")
        mService = Currency.retrofitService
        binding.btnCurrency.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.activity_main, CurrencyListFragment.newInstance())
                .commit()
        }
        binding.buy.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.activity_main, BuyCurrencyListFragment.newInstance())
                .commit()
        }
            //TODO переносим в CurrencyListFragment
        binding.button3.setOnClickListener {
            mService.getAll().enqueue(object : Callback<JsonObject> {

                override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                    Log.d("main", "onResponse")
                   // Log.d("main", "${response.body()}")

                    val gson = Gson()
                    val i = object : TypeToken<Map<String, String>>() {}.type
                    val list: Map<String, String> = gson.fromJson(response.body(), i)
                    val list2 = list.map { i-> RecponseTest2(i.key,i.value)}
                    Log.d("main", "${list2}")
                }

                override fun onFailure(call: Call<JsonObject>, t: Throwable) {

                    Log.d("main", "onFailure")
                }
            })
        }
    }

    companion object {
        fun newInstance(): MainFragment = MainFragment()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("main", "onAttach-main")
    }

    override fun onStart() {
        super.onStart()
        Log.d("main", "onStart-main")
    }

    override fun onResume() {
        super.onResume()
        Log.d("main", "onResume-main")
    }

    override fun onPause() {
        super.onPause()
        Log.d("main", "onPause-main")
    }

    override fun onStop() {
        super.onStop()
        Log.d("main", "onStop-main")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("main", "onDestroyView-main")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("main", "onDestroy-main")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("main", "onDetach-main")
    }
}







