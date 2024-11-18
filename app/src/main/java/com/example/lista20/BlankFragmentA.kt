package com.example.lista20

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.lista20.databinding.FragmentBlankABinding

class BlankFragmentA : Fragment() {
    private lateinit var binding: FragmentBlankABinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBlankABinding.inflate(layoutInflater)
        binding.fabA.setOnClickListener {
            val action = BlankFragmentADirections.actionBlankFragmentAToBlankFragmentB()
            Navigation.findNavController(requireView()).navigate(action)
        }
        binding.fabB.setOnClickListener {
            val action = BlankFragmentADirections.actionBlankFragmentAToBlankFragmentC()
            Navigation.findNavController(requireView()).navigate(action)
        }
        return binding.root
    }
}