package com.zlamanyuk_telyatnikova.unscramblegame.ui_model

import com.zlamanyuk_telyatnikova.unscramblegame.data.GameUiState
import androidx.lifecycle.ViewModel
import com.zlamanyuk_telyatnikova.unscramblegame.data.allWords
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class GameViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(GameUiState())
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()
    private lateinit var currentWord: String
    private var usedWords: MutableSet<String> = mutableSetOf()
    init {
        resetGame()
    }

    fun resetGame() {
        usedWords.clear()
        _uiState.value = GameUiState(
            currentScrambledWord = pickRandomWordAndShuffle()
        )
    }
    private fun shuffleCurrentWord(word: String): String{
        val tempWord = word.toCharArray()
        tempWord.shuffle()
        while (String(tempWord)==word){
            tempWord.shuffle()
        }
        return String(tempWord)
    }
    private fun pickRandomWordAndShuffle(): String{
        currentWord = allWords.random()
        while (usedWords.contains(currentWord)){
            currentWord = allWords.random()
        }
        usedWords.add(currentWord)
        return shuffleCurrentWord(currentWord)
    }
}