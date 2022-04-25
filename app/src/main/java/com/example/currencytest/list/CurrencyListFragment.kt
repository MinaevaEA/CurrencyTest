package com.example.currencytest.list


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.currencytest.R
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
        val retrofit: RetrofitServices =
            (requireContext().applicationContext as SubApplication).provideDataFromNetwork()
                .create()
        val interactor = CurrencyListInteract(retrofit)
        val viewModelFactory = CurrencyViewModelFactory(interactor)
        currencyListViewModel =
            ViewModelProvider(this, viewModelFactory)[CurrencyListViewModel::class.java]
        currencyListViewModel.onViewCreated()
        val adapter = AdapterCurrency(this)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
        currencyListViewModel.loadingListCurrency.observe(requireActivity()) {
            adapter.setData(it)
            Log.d("4444444", "currencies.observe")
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
        Log.d("1", "onCreateList")


        /* retrofit.getAll().enqueue(object : Callback<JsonObject> {

        override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
            Log.d("main", "onResponse")

            val gson = Gson()
            val i = object :
                TypeToken<Map<String, String>>() {}.type // указание формата парсинга данных
            val list: Map<String, String> = gson.fromJson(
                response.body(),
                i
            ) // с помощью вспомогательного объекта gson конвертируем тело ответа сервера в нужном формате парсинга данных
            val list2 = list.map { i -> DataCurrency(i.key, i.value) }
            Log.d("main", "$list2")
            adapter.setData(list2)
            if (response.code() == 200) {
                successLoadingListCurrency()
            } else {
                errorLoadingListCurrency()
            }
        }

        override fun onFailure(call: Call<JsonObject>, t: Throwable) {
            Log.d("main", "onFailure")
            errorLoadingListCurrency()
        }
    })*/
    }
    //TODO 1. Создать фрагмент и вью модель в конструктор которой передается интерактор. Интерактор пустой. 2. Прокинуть в итерактор объект ретрофита полученный из аппликейшн.
    //TODO 3. вызвать метод ретрофит объекта, который вернет JsonObject во вьюмодель без объекта Call

    fun setProgressBarVisibility(isVisible: Boolean) {
        val visibility = if (isVisible)  View.VISIBLE else View.INVISIBLE
        binding.progressBar.visibility = visibility
    }

    fun setErrorTextViewVisibility(isVisible: Boolean) {
        val visibility = if (isVisible)  View.VISIBLE else View.INVISIBLE
        binding.errorMsg.visibility = visibility
    }

    fun setListCurrencyVisibility(isVisible: Boolean) {
        val visibility = if (isVisible)  View.VISIBLE else View.INVISIBLE
        binding.recyclerView.visibility = visibility
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
}