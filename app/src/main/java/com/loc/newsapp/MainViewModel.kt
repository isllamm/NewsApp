package com.loc.newsapp

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loc.newsapp.domain.usecases.AppEntryUseCases
import com.loc.newsapp.presentation.navgraph.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val appEntryUseCases: AppEntryUseCases
): ViewModel() {
     var splash by mutableStateOf(true)
         private set

    var startDestination by mutableStateOf(Route.AppStartNavigation.route)
        private set

    init {
      appEntryUseCases.readAppEntry().onEach {shouldStartFromHomeScreen->
          if (shouldStartFromHomeScreen){
              startDestination =Route.NewsNavigation.route
          }else{
              startDestination = Route.AppStartNavigation.route
          }
          delay(300)
          Log.d("test", ": here1")
          splash=false
          Log.d("test", ": here2")
          Log.d("test", ": $startDestination")
      }.launchIn(viewModelScope)
    }
}