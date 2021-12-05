package com.shahin.data.repository

import com.shahin.LaunchProfileQuery
import com.shahin.LaunchRepoListQuery
import com.shahin.data.local.model.ProfileEntity
import com.shahin.data.local.model.RepoEntity
import com.shahin.data.model.Profile
import com.shahin.data.model.RepoItem

fun LaunchProfileQuery.Data.toEntity(): ProfileEntity =
    ProfileEntity(
        login = repositoryOwner?.login,
        avatarUrl = repositoryOwner?.avatarUrl.toString(),
        url = repositoryOwner?.url.toString()
    )

fun ProfileEntity.toDomain(): Profile =
    Profile(id = id, login = login, avatarUrl = avatarUrl, url = url)


fun LaunchRepoListQuery.Node.toEntity() : RepoEntity =
    RepoEntity(
        id = id,
        name = name,
        createdAt = createdAt.toString(),
        description = description
    )

fun RepoEntity.toDomain() : RepoItem =
    RepoItem(
        id = id,
        name = name,
        createdAt = createdAt,
        description = description
    )