package com.example.appmarveldesafio.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appmarveldesafio.R
import com.example.appmarveldesafio.service.repository
import com.example.appmarveldesafio.ui.adapters.AdapterQuadrinhos
import kotlinx.android.synthetic.main.fragment_quadrinhos.*
import kotlinx.android.synthetic.main.fragment_quadrinhos.view.*


class QuadrinhosFragment : Fragment() {



    lateinit var adapterQuadrinhos: AdapterQuadrinhos
    lateinit var gridLayoutManager: GridLayoutManager

    val viewModel by viewModels<MainViewModel>{
        object : ViewModelProvider.Factory{
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return MainViewModel(repository) as T
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val callback = requireActivity().onBackPressedDispatcher.addCallback(this){
            findNavController().navigate(R.id.action_quadrinhosFragment_to_loginFragment)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fragment_quadrinhos, container, false)

        viewModel.popListQuadrinhos()
        viewModel.listResults.observe(viewLifecycleOwner, {
            adapterQuadrinhos.addList(it.data.results)
        })
        adapterQuadrinhos = AdapterQuadrinhos()
        gridLayoutManager = GridLayoutManager(view.context, 3)
        view.rvQuadrinhos.adapter = adapterQuadrinhos
        view.rvQuadrinhos.layoutManager = gridLayoutManager
        view.rvQuadrinhos.hasFixedSize()


        return view
    }




}