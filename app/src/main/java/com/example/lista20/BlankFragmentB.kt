package com.example.lista20

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.lista20.databinding.FragmentBlankBBinding

class BlankFragmentB : Fragment() {
    private lateinit var binding: FragmentBlankBBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBlankBBinding.inflate(layoutInflater)
        binding.loginButton.setOnClickListener {
            val username = binding.loginEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(context, "Pole/Pola nie moga byc puste", Toast.LENGTH_SHORT).show()
            } else if (UserRepository.authenticate(username, password)) {
                val action = BlankFragmentBDirections.actionBlankFragmentBToBlankFragmentD(username)
                Navigation.findNavController(requireView()).navigate(action)
            } else {
                Toast.makeText(context, "Bledny login lub haslo", Toast.LENGTH_SHORT).show()
            }
        }
        binding.registerButton.setOnClickListener {
            val action = BlankFragmentBDirections.actionBlankFragmentBToBlankFragmentC()
            Navigation.findNavController(requireView()).navigate(action)
        }
        return binding.root
    }
}