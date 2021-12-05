package com.shahin.githubprofile.ui.profile

import androidx.lifecycle.*
import com.shahin.data.model.DataResult
import com.shahin.data.model.Profile
import com.shahin.data.repository.GithubRepository
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val githubRepo: GithubRepository
) : ViewModel() {

    private val profileData: LiveData<DataResult<Profile>> =
        githubRepo.getProfile().asLiveData(viewModelScope.coroutineContext)
    val profile = MediatorLiveData<Profile>()
    val error = MediatorLiveData<Throwable>()

    init {
        profile.addSource(profileData) {
            if (it is DataResult.Success) {
                it.data?.let { data -> profile.postValue(data) }
            }
        }
        error.addSource(profileData) {
            if (it is DataResult.Error) {
                error.postValue(it.message)
            }
        }
    }


}