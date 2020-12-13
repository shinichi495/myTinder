package com.namph.mytinder.mapper

import com.namph.mytinder.domain.model.User
import com.namph.mytinder.model.UserItem

fun User.map() = UserItem(
    ssn,
    gender,
    titleName,
    firstName,
    lastName,
    street,
    city,
    state,
    zip,
    email,
    username,
    phone,
    cell,
    picture
)

fun UserItem.map() = User(
    ssn,
    gender,
    titleName,
    firstName,
    lastName,
    street,
    city,
    state,
    zip,
    email,
    username,
    phone,
    cell,
    picture
)