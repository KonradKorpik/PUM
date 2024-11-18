package com.example.lista20

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.navigation.fragment.findNavController
import com.example.lista20.databinding.FragmentBlankDBinding

class BlankFragmentD : Fragment() {
    private lateinit var binding: FragmentBlankDBinding
    private val args: BlankFragmentDArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBlankDBinding.inflate(layoutInflater)

        val username = args.username
        binding.greetingTextView.text = "Witaj $username"

        binding.logoutButton.setOnClickListener {
            val action = BlankFragmentDDirections.actionBlankFragmentDToBlankFragmentB()
            findNavController().navigate(action)
        }
        return binding.root
    }
}