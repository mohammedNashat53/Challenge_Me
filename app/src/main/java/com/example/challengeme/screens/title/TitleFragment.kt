package com.example.challengeme.screens.title


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

import com.example.challengeme.databinding.FragmentTitleBinding

class TitleFragment : Fragment() {
    lateinit var binding: FragmentTitleBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragmentkk
        binding = FragmentTitleBinding.inflate(inflater)
        binding.btGoToChallenge.setOnClickListener{
            findNavController().navigate(TitleFragmentDirections.actionTitleFragmentToGameFragment())
        }
        return binding.root

    }

}