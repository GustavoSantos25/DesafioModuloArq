package com.example.appmarveldesafio.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.activity.viewModels
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appmarveldesafio.R
import com.example.appmarveldesafio.entities.Quadrinho
import com.example.appmarveldesafio.service.repository
import com.example.appmarveldesafio.ui.adapters.AdapterQuadrinhos
import kotlinx.android.synthetic.main.fragment_quadrinhos.*
import kotlinx.android.synthetic.main.fragment_quadrinhos.view.*
import kotlin.properties.Delegates


class QuadrinhosFragment : Fragment(), AdapterQuadrinhos.OnClickQuadrinhoListener{

    private lateinit var adapterQuadrinhos: AdapterQuadrinhos
    lateinit var gridLayoutManager: GridLayoutManager
    private var offset = 0
    var lastFirstVisiblePosition by Delegates.notNull<Int>()

    private val viewModel by activityViewModels<MainViewModel>{
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


        viewModel.listQuadrinho.observe(viewLifecycleOwner, {
            adapterQuadrinhos.addList(it)
        })

        adapterQuadrinhos = AdapterQuadrinhos(this)
        gridLayoutManager = GridLayoutManager(view.context, 3)
        lastFirstVisiblePosition = gridLayoutManager.findFirstCompletelyVisibleItemPosition()
        gridLayoutManager.scrollToPosition(lastFirstVisiblePosition)
        view.rvQuadrinhos.adapter = adapterQuadrinhos
        view.rvQuadrinhos.layoutManager = gridLayoutManager
        view.rvQuadrinhos.hasFixedSize()
        setScroller(view)

        return view
    }

    override fun onClickQuadrinho(position: Int) {
        viewModel.updatePositionQuadDetail(position)
        findNavController().navigate(R.id.action_quadrinhosFragment_to_detailQuadrinhoFragment)
    }

    fun setScroller(view: View){
        view.rvQuadrinhos.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if(dy > 0){
                    val litem = gridLayoutManager.itemCount
                    val vItem  = gridLayoutManager.findFirstCompletelyVisibleItemPosition()
                    val itens = adapterQuadrinhos.itemCount
                    if(litem + vItem >= itens && offset < 20){
                        viewModel.popListQuadrinhos(++offset)
                    }
                }
            }
        })
    }




}