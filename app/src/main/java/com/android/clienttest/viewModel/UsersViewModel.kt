package com.android.clienttest.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.clienttest.model.RandomUserList
import com.android.clienttest.network.NetworkRepository
import kotlinx.coroutines.*

class UsersViewModel constructor(private val mainRepository: NetworkRepository) : ViewModel() {
    val errorMessage = MutableLiveData<String>()
    val usersList = MutableLiveData<RandomUserList>()
    var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }
    val loading = MutableLiveData<Boolean>()

    fun getAllUsers() {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            try  {
                val response = mainRepository.getAllUsers()
                withContext(Dispatchers.Main) {
                    if (response.results.isNotEmpty()) {
                        usersList.postValue(response)
                        loading.value = false
                    } else {
                        onError("Error")
                    }
                }
            } catch (ce: CancellationException) {
            throw ce
        } catch (e: Exception) {
            println("Error")
            }
        }
    }

    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}