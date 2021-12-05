package com.shahin.data.repository

import com.shahin.LaunchProfileQuery
import com.shahin.data.local.model.ProfileEntity
import com.shahin.data.model.Profile

fun LaunchProfileQuery.Data.toEntity(): ProfileEntity =
    ProfileEntity(
        login = repositoryOwner?.login,
        avatarUrl = repositoryOwner?.avatarUrl.toString(),
        url = repositoryOwner?.url.toString()
    )

fun ProfileEntity.toDomain(): Profile =
    Profile(id = id, login = login, avatarUrl = avatarUrl, url = url)