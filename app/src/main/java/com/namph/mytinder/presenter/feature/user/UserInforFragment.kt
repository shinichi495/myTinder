package com.namph.mytinder.presenter.feature.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.namph.mytinder.databinding.FragmentUserInforBinding

class UserInforFragment : Fragment () {
    lateinit var userFragmentBinding : FragmentUserInforBinding
    private var name : String? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        userFragmentBinding = FragmentUserInforBinding.inflate(inflater,container,false)
        return userFragmentBinding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        name = arguments?.getString("name")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (name != null ) {
            userFragmentBinding.txtName.text = name
        } else {
            userFragmentBinding.txtName.text = "Error"
        }

    }

    companion object {
        fun newInstance (value : String) : UserInforFragment {
            val fragment = UserInforFragment()
            val args = Bundle()
            args.putString("name", value)
            fragment.arguments = args
            return fragment
        }
    }
}