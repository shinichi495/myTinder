package com.namph.mytinder.presenter.feature.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.namph.mytinder.databinding.FragmentTodoBinding

class TodoFragment : Fragment() {
    lateinit var todoFragmentBinding : FragmentTodoBinding
    private var todo : String = "No Idea"
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        todoFragmentBinding = FragmentTodoBinding.inflate(inflater,container,false)
        return todoFragmentBinding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        todoFragmentBinding.txtTodo.text = todo
    }

    companion object {
        fun newInstance () : TodoFragment {
            val fragment = TodoFragment()
            return fragment
        }
    }
}