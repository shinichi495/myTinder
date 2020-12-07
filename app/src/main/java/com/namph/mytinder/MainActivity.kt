package com.namph.mytinder

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DiffUtil
import com.namph.mytinder.adapter.CardStackAdapter
import com.namph.mytinder.databinding.ActivityMainBinding
import com.namph.mytinder.domain.model.User
import com.namph.mytinder.presenter.feature.user.UserDetailViewModel
import com.yuyakaido.android.cardstackview.*
import kotlinx.coroutines.ObsoleteCoroutinesApi
import org.koin.android.viewmodel.ext.android.viewModel

@ObsoleteCoroutinesApi
class MainActivity : AppCompatActivity() , CardStackListener {
    private val vm : UserDetailViewModel by viewModel()
    private lateinit var activityBinding : ActivityMainBinding
    private val cardStackView : CardStackView by lazy { findViewById(R.id.card_stack_view) }
    private val manager : CardStackLayoutManager by lazy { CardStackLayoutManager(this,this) }
    private lateinit var adapter : CardStackAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        activityBinding.apply {
            viewmodel = vm
        }
        activityBinding.lifecycleOwner = this
        adapter = CardStackAdapter(context = this)
        adapter.setItems(emptyList())
        initialize()
        getCard()
    }

    override fun onCardDragging(direction: Direction?, ratio: Float) {
    }

    override fun onCardSwiped(direction: Direction?) {
        getCard()
    }

    override fun onCardRewound() {
    }

    override fun onCardCanceled() {
    }

    override fun onCardAppeared(view: View?, position: Int) {
    }

    override fun onCardDisappeared(view: View?, position: Int) {
    }

    private fun getCard () {
        vm.requestUser()
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
        vm.item.observe(this, Observer {
            if (it != null) {
                paginate(it)
            }
        })
    }

    private fun paginate(users : List<User>) {
        val old = adapter.getItems()
        val new = old.plus(users)
        val callback = UserDiffCallback(old, new)
        val result = DiffUtil.calculateDiff(callback)
        adapter.setItems(new)
        result.dispatchUpdatesTo(adapter)
    }
}