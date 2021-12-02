package com.shahin.githubprofile.di

import com.shahin.githubprofile.ui.profile.ProfileFragment
import com.shahin.githubprofile.ui.repolist.RepositoryListFragment
import dagger.Subcomponent

@Subcomponent
interface MainActivitySubComponent {

    fun inject(profileFragment: ProfileFragment)
    fun inject(repositoryListFragment: RepositoryListFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): MainActivitySubComponent
    }

}