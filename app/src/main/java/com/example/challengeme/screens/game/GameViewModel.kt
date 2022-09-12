package com.example.challengeme.screens.game

import android.content.Context
import android.os.CountDownTimer
import android.text.format.DateUtils
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

const val ONE_SECOND = 1000L
const val COUNTDOWN_TIME = 120000L

class GameViewModel: ViewModel() {


    lateinit var wordList: MutableList<String>
    lateinit var timer: CountDownTimer

    private val _isGameFinished = MutableLiveData<Boolean>()
    val isGameFinish : LiveData<Boolean>
        get() = _isGameFinished

    private val _score = MutableLiveData<Int>()
    val score: LiveData <Int>
        get() = _score

    val scoreString = Transformations.map(_score) {
        it.toString()
    }

    private val _currentTime = MutableLiveData<Long>()
    val currentTime : LiveData<Long>
        get() = _currentTime


    val currentTimeString = Transformations.map(currentTime) { time ->
        DateUtils.formatElapsedTime(time).toString()
    }


    private val _challangeWord= MutableLiveData<String>()
    val challangeWord: LiveData<String>
        get () = _challangeWord

    private val _correctWord = MutableLiveData<String>()
    val correctWord : LiveData<String>
        get() = _correctWord




   init {
      _challangeWord.value = ""
       _correctWord.value = ""
       _score.value = 0
       _isGameFinished.value = false
       resetList()
       nextWord()
       startTimer()
   }

    private fun resetList(){
        wordList= mutableListOf(
//            "queen",
//            "hospital",
//            "basketball",
//            "cat",
//            "change",
//            "snail",
//            "soup",
//            "calendar",
//            "sad",
//            "desk",
//            "guitar",
//            "home",
//            "railway",
//            "zebra",
//            "jelly",
//            "car",
//            "crow",
//            "trade",
            "bag",
            "roll",
            "bubble"
        )
    wordList.shuffle()
    }

    private fun convertWord(word: String): String{
        val str = word.toList().shuffled()
        return String(str.toCharArray())
    }

     fun nextWord(){
        if (wordList.isEmpty()){
            _isGameFinished.value = true
        }else{
            _correctWord.value = wordList.removeAt(0)
            _challangeWord.value = convertWord(_correctWord.value ?:"")
        }
    }

    fun skip(){
        _score.value = (_score.value)?.minus(1)

    }

    fun checkWord(word: String) {
              if (word.equals(_correctWord.value,true)) {
                  _score.value = (_score.value)?.plus(1)

              } else {
                  _score.value = (_score.value)?.minus(1)
              }
    }

    private fun startTimer() {
        timer = object : CountDownTimer(COUNTDOWN_TIME, ONE_SECOND) {
            override fun onTick(millisUntilFinished: Long) {
                _currentTime.value = millisUntilFinished / 1000
            }

            override fun onFinish() {
                _isGameFinished.value = true
            }

        }.start()
    }

    fun doneNavigating() {
        _isGameFinished.value = false
    }


}