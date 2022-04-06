package com.example.currencytest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.*
import com.example.currencytest.databinding.FragmentRegistrationBinding

class RegistrationFragment : Fragment() {
    private lateinit var binding: FragmentRegistrationBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentRegistrationBinding.inflate(inflater, container, false).also {
        binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonLogin.setOnClickListener {
            checkInputData()
        }
    }

    private fun checkInputData() {
        if (binding.editUser.text.toString() == "admin" &&
            binding.editPassword.text.toString() == "admin"
        ) {
            Toast.makeText(requireContext(), "Вход выполнен!", Toast.LENGTH_SHORT).show()
            requireActivity().supportFragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.activity_main, MainFragment.newInstance())
                .commit()
        } else {
            Toast.makeText(requireContext(), "Неправильные данные!", Toast.LENGTH_SHORT)
                .show()
        }
    }

    companion object {
        fun newInstance(): RegistrationFragment = RegistrationFragment()
    }
}