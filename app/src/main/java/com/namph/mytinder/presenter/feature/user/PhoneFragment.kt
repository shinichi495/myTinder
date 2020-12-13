package com.namph.mytinder.presenter.feature.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.namph.mytinder.databinding.FragmentPhoneBinding

class PhoneFragment : Fragment() {
    lateinit var phoneFragmentBinding : FragmentPhoneBinding
    private var phone : String? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        phoneFragmentBinding = FragmentPhoneBinding.inflate(inflater,container,false)
        return phoneFragmentBinding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        phone = arguments?.getString("phone")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (phone != null ) {
            phoneFragmentBinding.txtPhone.text = phone
        } else {
            phoneFragmentBinding.txtPhone.text = "Error"
        }

    }

    companion object {
        fun newInstance (value : String?) : PhoneFragment {
            val fragment = PhoneFragment()
            val args = Bundle()
            args.putString("phone", value)
            fragment.arguments = args
            return fragment
        }
    }
}