package com.example.challengeme.screens.game

import android.icu.lang.UCharacter.GraphemeClusterBreak.V
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.challengeme.R
import com.example.challengeme.databinding.FragmentGameBinding

class GameFragment : Fragment() {
    lateinit var binding: FragmentGameBinding
    lateinit var viewModel: GameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        binding = FragmentGameBinding.inflate(inflater)
        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.btCheckWord.setOnClickListener{
            getWord()
        }

        binding.btNext.setOnClickListener {
            binding.constrainLayout.visibility = View.VISIBLE
            binding.btNext.visibility = View.GONE
            viewModel.nextWord()
        }

        binding.btSkip.setOnClickListener{
            viewModel.skip()
            setUpUi()
        }

        viewModel.isGameFinish.observe(viewLifecycleOwner, Observer { hasWord ->
            if(hasWord){
                gameFinished()
                viewModel.doneNavigating()
            }
        })



        return binding.root
    }

    private fun getWord(){
        var str = ""
        if (binding.edSetWord.isFocused){
            str = binding.edSetWord.text.toString()
            if (str.isEmpty()){
                Toast.makeText(this.context,"Set Your Word",Toast.LENGTH_SHORT).show()
            }else{
                viewModel.checkWord(str)
                 setUpUi()
            }

        }else{
            Toast.makeText(this.context,"Set Your Word",Toast.LENGTH_SHORT).show()
        }

    }

    private fun setUpUi() {
        binding.constrainLayout.visibility = View.GONE
        binding.tvchallengeWord.text = viewModel.correctWord.value
        binding.btNext.visibility = View.VISIBLE
        binding.edSetWord.text.clear()
    }


    private fun gameFinished (){
        val currentScore = viewModel.score.value ?:0
        val currenttime = viewModel.currentTimeString.value ?:""
        findNavController().navigate(
            GameFragmentDirections.actionGameFragmentToScoreFragment(
               currentScore ,currenttime))
    }


}