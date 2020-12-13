package com.namph.mytinder.model

import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.makeramen.roundedimageview.RoundedImageView

data class UserItem(
    val ssn : String,
    val gender: String,
    val titleName : String,
    val firstName : String,
    val lastName : String,
    val street: String,
    val city: String,
    val state: String,
    val zip: String,
    val email: String,
    val username: String,
    val phone : String,
    val cell : String,
    val picture : String
) {
    companion object {
        @JvmStatic @BindingAdapter("imagine")
        fun loadImagine (view : RoundedImageView, link : String) {
            Glide.with(view.context).load(link).into(view)
        }
    }

    fun toList () : List<UserItem> {
        val users = ArrayList<UserItem>()
        users.add(this)
        return users.toList()
    }

}