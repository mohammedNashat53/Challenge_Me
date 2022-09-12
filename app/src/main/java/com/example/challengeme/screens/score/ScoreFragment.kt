package com.example.challengeme.screens.score

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

import com.example.challengeme.databinding.FragmentScoreBinding

class ScoreFragment : Fragment() {
    lateinit var binding: FragmentScoreBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentScoreBinding.inflate(inflater)
        binding.btPlayAgain.setOnClickListener{
            findNavController().navigate(ScoreFragmentDirections.actionScoreFragmentToGameFragment())
        }

        val scoreFragmentArgs by navArgs<ScoreFragmentArgs>()
        binding.tvScore.text = scoreFragmentArgs.score.toString()
        binding.tvtime.text = scoreFragmentArgs.time
        return binding.root
    }

}