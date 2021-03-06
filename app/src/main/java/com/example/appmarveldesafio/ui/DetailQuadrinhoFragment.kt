package com.example.appmarveldesafio.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.appmarveldesafio.R
import com.example.appmarveldesafio.entities.Quadrinho
import com.example.appmarveldesafio.service.repository
import kotlinx.android.synthetic.main.fragment_detail_quadrinho.*
import kotlinx.android.synthetic.main.fragment_detail_quadrinho.view.*
import java.util.*
import kotlin.properties.Delegates


class DetailQuadrinhoFragment : Fragment() {


    private val viewModel by activityViewModels<MainViewModel>{
        object : ViewModelProvider.Factory{
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return MainViewModel(repository) as T
            }
        }
    }
    var hq : Quadrinho? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val callback = requireActivity().onBackPressedDispatcher.addCallback(this){
            findNavController().navigate(R.id.action_detailQuadrinhoFragment_to_quadrinhosFragment)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fragment_detail_quadrinho, container, false)
        view.btnVoltar.setBackgroundColor(resources.getColor(R.color.white))
        view.btnVoltar.setOnClickListener {
            findNavController().navigate(R.id.action_detailQuadrinhoFragment_to_quadrinhosFragment)
        }

        hq = viewModel.quadDetail.value

        Glide.with(view.context).asBitmap()
                .load(hq!!.thumbnail.path+"."+hq!!.thumbnail.extension)
                .into(view.ivThumdDesc)

        when(hq?.images!!.size > 1){
            false -> {
                Glide.with(view.context).asBitmap()
                    .load(hq!!.images[0].path+"."+hq!!.images[0].extension)
                    .into(view.ivCapaQuad)
            }
            true -> {
                Glide.with(view.context).asBitmap()
                    .load(hq!!.images[1].path+"."+hq!!.images[1].extension)
                    .into(view.ivCapaQuad)
            }
        }


        view.tvTitleQuad.text = hq!!.title
        view.tvDescQuad.text = hq!!.description
        view.tvPubliQuad.text = hq!!.dates[0].date
        view.tvPagesQuad.text = hq!!.pageCount.toString()
        view.tvPriceQuad.text = "$"+hq!!.prices[0].price.toString()

        view.ivThumdDesc.setOnClickListener {
            findNavController().navigate(R.id.action_detailQuadrinhoFragment_to_capaHQFragment)
        }
        return view
    }



}

