package com.namph.mytinder

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DiffUtil
import com.namph.mytinder.adapter.CardStackAdapter
import com.namph.mytinder.databinding.ActivityMainBinding
import com.namph.mytinder.domain.model.User
import com.namph.mytinder.mapper.map
import com.namph.mytinder.model.UserItem
import com.namph.mytinder.presenter.feature.user.UserDetailViewModel
import com.yuyakaido.android.cardstackview.*
import kotlinx.coroutines.ObsoleteCoroutinesApi
import org.koin.android.viewmodel.ext.android.viewModel

@ObsoleteCoroutinesApi
class MainActivity : AppCompatActivity(), CardStackListener {
    private val vm: UserDetailViewModel by viewModel()
    private lateinit var activityBinding: ActivityMainBinding
    private val cardStackView: CardStackView by lazy { findViewById(R.id.card_stack_view) }
    private val manager: CardStackLayoutManager by lazy { CardStackLayoutManager(this, this) }
    private lateinit var adapter: CardStackAdapter
    private lateinit var offlineCard: ArrayList<UserItem>
    private var selectIndex = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        activityBinding.apply {
            viewmodel = vm
        }
        activityBinding.lifecycleOwner = this
        adapter = CardStackAdapter(context = this)
        adapter.setItems(emptyList())
        initialize()
        initVm()
        getCard()
    }

    override fun onCardDragging(direction: Direction?, ratio: Float) {
    }

    override fun onCardSwiped(direction: Direction?) {
        if (!checkNetwork()) {
            if (!this::offlineCard.isInitialized) {
                getCard()
                selectIndex = 0
                return
            }
            if (selectIndex == offlineCard!!.size - 1) {
                selectIndex = 0
                offlineCard?.get(selectIndex)?.toList()?.let { reload(it) }
            } else {
                selectIndex++
                offlineCard?.get(selectIndex)?.toList()?.let { loadCard(it) }
            }
            return
        } else if (direction == Direction.Right) {
            save((adapter.getCurrentItem() as UserItem).map())
        }
        getCardFromServer()

    }

    override fun onCardRewound() {}

    override fun onCardCanceled() {}

    override fun onCardAppeared(view: View?, position: Int) {}

    override fun onCardDisappeared(view: View?, position: Int) {}

    private fun getCard() {
        if (checkNetwork()) {
            getCardFromServer()
        } else {
            getCardFromLocal()
        }
    }

    private fun getCardFromServer() {
        vm.requestUserFromServer()
    }

    private fun getCardFromLocal() {
        vm.requestUserFromLocal()
    }

    private fun save(user: User) {
        vm.saveUser(user)
    }

    private fun initialize() {
        manager.setStackFrom(StackFrom.None)
        manager.setVisibleCount(3)
        manager.setTranslationInterval(8.0f)
        manager.setScaleInterval(0.95f)
        manager.setSwipeThreshold(0.3f)
        manager.setMaxDegree(20.0f)
        manager.setDirections(Direction.HORIZONTAL)
        manager.setCanScrollHorizontal(true)
        manager.setCanScrollVertical(true)
        manager.setSwipeableMethod(SwipeableMethod.AutomaticAndManual)
        manager.setOverlayInterpolator(LinearInterpolator())
        cardStackView.layoutManager = manager
        cardStackView.adapter = adapter
        cardStackView.itemAnimator.apply {
            if (this is DefaultItemAnimator) {
                supportsChangeAnimations = false
            }
        }

    }

    private fun initVm() {
        vm.item.observe(this, Observer {
            if (it != null) {
                if (!checkNetwork()) {
                    offlineCard = ArrayList(it)
                    loadCard(offlineCard!![selectIndex].toList())
                } else {
                    loadCard(users = it)
                }
            }
        })

        vm.err.observe(this, Observer {
            if (it == true) {
                val toast = Toast.makeText(this,"Error please restart app", Toast.LENGTH_LONG)
                toast.show()
            }
        })
    }

    private fun loadCard(users: List<UserItem>) {
        val old = adapter.getItems()
        val new = old.plus(users)
        val callback = UserDiffCallback(old, new)
        val result = DiffUtil.calculateDiff(callback)
        adapter.setItems(new)
        result.dispatchUpdatesTo(adapter)
    }

    private fun reload(withUsers: List<UserItem>) {
        val old = adapter.getItems()
        val new = withUsers
        val callback = UserDiffCallback(old, new)
        val result = DiffUtil.calculateDiff(callback)
        adapter.setItems(new)
        result.dispatchUpdatesTo(adapter)
    }

    private fun checkNetwork(): Boolean {
        return Util.isNetWorkConnect(context = this)
    }

}