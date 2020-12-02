package com.example.appmarveldesafio.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.appmarveldesafio.R
import com.example.appmarveldesafio.entities.Quadrinho
import kotlinx.android.synthetic.main.fragment_capa_h_q.view.*
import kotlinx.android.synthetic.main.fragment_detail_quadrinho.view.*

class CapaHQFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val callback = requireActivity().onBackPressedDispatcher.addCallback(this){
            findNavController().navigate(R.id.action_capaHQFragment_to_detailQuadrinhoFragment, arguments)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_capa_h_q, container, false)
        view.btnClose.setOnClickListener {
            findNavController().navigate(R.id.action_capaHQFragment_to_detailQuadrinhoFragment, arguments)
        }
        val hq = arguments?.getSerializable("hq") as Quadrinho
        Glide.with(view.context).asBitmap()
            .load(hq.thumbnail.path+"."+hq.thumbnail.extension)
            .into(view.ivCapa)
        return view
    }


}