package com.namph.mytinder.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.namph.mytinder.MainActivity
import com.namph.mytinder.R
import com.namph.mytinder.base.BaseBindingAdapter
import com.namph.mytinder.databinding.ItemUserBinding
import com.namph.mytinder.model.UserItem
import com.namph.mytinder.presenter.feature.user.*

class CardStackAdapter(
    val context: Context
) : BaseBindingAdapter<ItemUserBinding, UserItem>(context, R.layout.item_user) {
    var bottomView: BottomNavigationView? = null

    override fun onBindViewHolder(holder: ViewHolder<ItemUserBinding>, position: Int) {
        super.onBindViewHolder(holder, position)
        val item = getCurrentItem()
        if (item == null) {
            return
        }
        handleView()
    }

    private fun handleView() {
        val user = getCurrentItem()
//        Glide.with(binding.itemImage)
//            .load(user?.picture)
//            .into(binding.itemImage)
        bottomView = binding.navigation
        bottomView?.selectedItemId = R.id.navigation_location
        openFragment(LocationFragment.newInstance("${user?.street} ${user?.city} ${user?.state}"))
        bottomView?.setOnNavigationItemSelectedListener {

            when (it.itemId) {
                R.id.navigation_phone -> {
                    openFragment(PhoneFragment.newInstance(user?.phone))
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_location -> {
                    openFragment(LocationFragment.newInstance("${user?.street} ${user?.city} ${user?.state}"))
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.navigation_user -> {
                    openFragment(UserInforFragment.newInstance("${user?.firstName} ${user?.lastName}"))
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.navigation_lock -> {
                    openFragment(LockFragment.newInstance())
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.navigation_todo -> {
                    openFragment(TodoFragment.newInstance())
                    return@setOnNavigationItemSelectedListener true
                }

            }
            false
        }
    }


    private fun openFragment(fr: Fragment) {
        val frManager = (context as MainActivity).supportFragmentManager
        val transaction = frManager.beginTransaction()
        transaction.replace(R.id.container, fr)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}