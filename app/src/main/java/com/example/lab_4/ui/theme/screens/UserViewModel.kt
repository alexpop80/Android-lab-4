package com.example.lab_4.ui.theme.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.lab_4.UserApplication
import com.example.lab_4.data.UserDirectoryrep
import com.example.lab_4.model.Picture
import com.example.lab_4.model.Urlinfo
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

/**
 * UI state for the Home screen
 */
sealed interface UserUiState {
    data class Success(val photos: List<Urlinfo>?) : UserUiState
    object Error : UserUiState
    object Loading : UserUiState
}

class UserViewModel(private val userDirectoryrep: UserDirectoryrep) : ViewModel() {
    // The mutable State that stores the status of the most recent request
    var userUiState: UserUiState by mutableStateOf(UserUiState.Loading)
        private set


    init {
        getUserInfo()
    }


    fun getUserInfo() {
        viewModelScope.launch {
            userUiState = UserUiState.Loading
            userUiState = try {
                UserUiState.Success(userDirectoryrep.getUserInfo())
            } catch (e: IOException) {
                UserUiState.Error
            } catch (e: HttpException) {
                UserUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as UserApplication)
                val userDirectoryrep = application.container.userDirectoryrep
                UserViewModel(userDirectoryrep = userDirectoryrep)
            }
        }
    }
}
