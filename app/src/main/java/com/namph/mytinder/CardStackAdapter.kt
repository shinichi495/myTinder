package com.namph.mytinder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.namph.mytinder.domain.model.User

class CardStackAdapter(
    private var users: List<User>
) : RecyclerView.Adapter<CardStackAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.item_user, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = users.get(position)
        val bottom = holder.bottom
        bottom.selectedItemId = R.id.navigation_location
        holder.value1.text = "Location is"
        holder.value2.text = "${user.street} ${user.city} ${user.state}"
        Glide.with(holder.image)
            .load(user.picture)
            .into(holder.image)

        bottom.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_phone -> {
                    holder.value1.text = "Phone Number is "
                    holder.value2.text = user.phone
                    it.setChecked(true)
                }
                R.id.navigation_location -> {
                    holder.value1.text = "Location is"
                    holder.value2.text = "${user.street} ${user.city} ${user.state}"
                    it.setChecked(true)
                }

                R.id.navigation_user -> {
                    holder.value1.text = "User Information"
                    holder.value2.text = "${user.firstName} ${user.lastName}"
                    it.setChecked(true)
                }

                R.id.navigation_lock -> {
                    holder.value1.text = "Nothing to show"
                    holder.value2.text = "Nothing to show"
                    it.setChecked(true)
                }

                R.id.navigation_todo -> {
                    holder.value1.text = "No idea"
                    holder.value2.text = "No idea"
                    it.setChecked(true)
                }

            }
            false
        }
    }

    override fun getItemCount(): Int {
        return users.size
    }

    fun setUser(users: List<User>) {
        this.users = users
    }

    fun getUser(): List<User> {
        return users
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val value1: TextView = view.findViewById(R.id.value1)
        var value2: TextView = view.findViewById(R.id.value2)
        var image: ImageView = view.findViewById(R.id.item_image)
        val bottom: BottomNavigationView = view.findViewById(R.id.navigation)
    }

}