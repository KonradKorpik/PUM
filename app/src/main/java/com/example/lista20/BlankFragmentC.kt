package com.example.lista20

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.lista20.databinding.FragmentBlankCBinding

class BlankFragmentC : Fragment() {
    private lateinit var binding: FragmentBlankCBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBlankCBinding.inflate(layoutInflater)
        binding.registerButton.setOnClickListener {
            val username = binding.loginEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            val repeatPassword = binding.repeatPasswordEditText.text.toString()
            if (username.isEmpty() || password.isEmpty() || repeatPassword.isEmpty()) {
                Toast.makeText(context, "Pole/Pola nie moga byc puste", Toast.LENGTH_SHORT).show()
            } else if (password == repeatPassword) {
                UserRepository.addUser(User(username, password))
                Toast.makeText(context, "Uzytkownik zostal pomyslnie zarejestrowany", Toast.LENGTH_SHORT).show()
                val action = BlankFragmentCDirections.actionBlankFragmentCToBlankFragmentB()
                Navigation.findNavController(requireView()).navigate(action)
            } else {
                Toast.makeText(context, "Hasla nie sa identyczne", Toast.LENGTH_SHORT).show()
            }
        }
        return binding.root
    }
}