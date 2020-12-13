package com.namph.mytinder.data.mapper

import com.namph.mytinder.data.db.user.UserData
import com.namph.mytinder.domain.model.User

fun User.map() = UserData.User(
    ssn = ssn,
    titleName = titleName,
    firstName = firstName,
    lastName = lastName,
    street = street,
    city = city,
    state = state,
    zip = zip,
    email = email,
    username = username,
    phone = phone,
    cell = cell,
    picture = picture
)