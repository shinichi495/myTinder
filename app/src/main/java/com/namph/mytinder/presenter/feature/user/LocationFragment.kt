package com.namph.mytinder.presenter.feature.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.namph.mytinder.databinding.FragmentLocationBinding

class LocationFragment : Fragment() {
    lateinit var locationFragmentBinding : FragmentLocationBinding
    private var location : String? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        locationFragmentBinding = FragmentLocationBinding.inflate(inflater,container,false)
        return locationFragmentBinding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        location = arguments?.getString("location")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (location != null ) {
            locationFragmentBinding.txtLocation.text = location
        } else {
            locationFragmentBinding.txtLocation.text = "Error"
        }

    }

    companion object {
        fun newInstance (value : String) : LocationFragment {
            val fragment = LocationFragment()
            val args = Bundle()
            args.putString("location", value)
            fragment.arguments = args
            return fragment
        }
    }
}