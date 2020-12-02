package com.example.appmarveldesafio.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.activity.viewModels
import androidx.core.os.bundleOf
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


class QuadrinhosFragment : Fragment(), AdapterQuadrinhos.OnClickQuadrinhoListener {



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
        adapterQuadrinhos = AdapterQuadrinhos(this)
        gridLayoutManager = GridLayoutManager(view.context, 3)
        view.rvQuadrinhos.adapter = adapterQuadrinhos
        view.rvQuadrinhos.layoutManager = gridLayoutManager
        view.rvQuadrinhos.hasFixedSize()


        return view
    }

    override fun onClickQuadrinho(position: Int) {
        val imageCapa : String = when(viewModel.listResults.value?.data!!.results[position].images.size > 1){
            false -> viewModel.listResults.value?.data!!.results[position].thumbnail.path + "." + viewModel.listResults.value?.data!!.results[position].thumbnail.extension
            true -> viewModel.listResults.value?.data!!.results[position].images[1].path + "." + viewModel.listResults.value?.data!!.results[position].images[1].extension
        }

        val bundle = bundleOf(Pair("title", viewModel.listResults.value?.data!!.results[position].title),
            Pair("thumbnail", viewModel.listResults.value?.data!!.results[position].thumbnail.path + "." + viewModel.listResults.value?.data!!.results[position].thumbnail.extension),
            Pair("desc", viewModel.listResults.value?.data!!.results[position].description),
            Pair("pages", viewModel.listResults.value?.data!!.results[position].pageCount.toString()),
            Pair("price", "$"+viewModel.listResults.value?.data!!.results[position].prices[0].price.toString()),
                Pair("date", viewModel.listResults.value?.data!!.results[position].dates[0].date),
            Pair("imageCapa", imageCapa)
        )
        findNavController().navigate(R.id.action_quadrinhosFragment_to_detailQuadrinhoFragment, bundle)
    }


}