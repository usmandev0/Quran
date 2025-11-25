package com.kmpstarter.feature_quran.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.kmpstarter.core.events.navigator.interfaces.Navigator
import com.kmpstarter.feature_quran.data.data_source.dtos.Quran
import com.kmpstarter.feature_quran.presentation.states.QuranState
import com.kmpstarter.feature_quran.presentation.ui_main.navigation.MainScreens
import kmpstarter.composeapp.generated.resources.Res
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

class QuranViewModel(
    private val navigator: Navigator,
) : ViewModel() {


    private val _state = MutableStateFlow(QuranState())
    val state = _state.asStateFlow()

    companion object {
        const val TAG = "QuranViewModel"
    }

    private var getQuranJob: Job? = null
    private var navigateToSurahScreenJob: Job? = null


    fun getQuran() {
        if (state.value.quran.isNotEmpty()) return
        getQuranJob?.cancel()
        getQuranJob = CoroutineScope(Dispatchers.IO).launch {
            _state.update {
                it.copy(
                    loadingMessage = "Loading"
                )
            }

            val jsonString = Res.readBytes("files/quran_ur.json").decodeToString()
            val list = Json.decodeFromString<List<Quran>>(jsonString)
            _state.update {
                it.copy(
                    isLoading = false,
                    quran = list
                )
            }


        }


    }


    fun navigateToSurahScreen(surah: Quran) {
        navigateToSurahScreenJob?.cancel()
        navigateToSurahScreenJob = CoroutineScope(Dispatchers.IO).launch {
            _state.update {
                it.copy(
                    currentSurah = surah
                )
            }

            navigator.navigateTo(
                route = MainScreens.Surah,
            )
        }

    }

    fun navigateToNextORPreviousSurah(id: Int){
        val surah = state.value.quran.find { it.id == id }
        if(surah != null){
            _state.update {
                it.copy(
                    currentSurah = surah
                )
            }

        }
    }
}

