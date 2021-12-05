package com.shahin.githubprofile.ui.repolist

import androidx.lifecycle.*
import com.shahin.data.model.DataResult
import com.shahin.data.model.RepoItem
import com.shahin.data.repository.GithubRepository
import javax.inject.Inject

class RepoListViewModel @Inject constructor(
    private val githubRepo: GithubRepository
) : ViewModel() {

    private val repoListData: LiveData<DataResult<List<RepoItem>>> =
        githubRepo.getRepoList().asLiveData(viewModelScope.coroutineContext)
    val repoList = MediatorLiveData<List<RepoItem>>()
    val loading = MediatorLiveData<Boolean>()

    init {
        repoList.addSource(repoListData) {
            if (it is DataResult.Success) {
                it.data?.let { data -> repoList.postValue(it.data) }
            }
        }
        loading.addSource(repoListData) {
            if (it is DataResult.Loading) {
                loading.postValue(it.data == null)
            }
        }
    }

}