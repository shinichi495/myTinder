package com.namph.mytinder.presenter.feature.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.namph.mytinder.databinding.FragmentLockBinding

class LockFragment : Fragment() {
    lateinit var lockFragmentBinding : FragmentLockBinding
    private var lock : String = "No Idea"
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        lockFragmentBinding = FragmentLockBinding.inflate(inflater,container,false)
        return lockFragmentBinding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        lockFragmentBinding.txtLock.text = lock
    }

    companion object {
        fun newInstance () : LockFragment {
            val fragment = LockFragment()
            return fragment
        }
    }
}