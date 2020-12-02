package com.example.appmarveldesafio.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.appmarveldesafio.R
import com.example.appmarveldesafio.entities.Quadrinho
import kotlinx.android.synthetic.main.fragment_detail_quadrinho.*
import kotlinx.android.synthetic.main.fragment_detail_quadrinho.view.*


class DetailQuadrinhoFragment : Fragment() {


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
        Glide.with(view.context).asBitmap()
                .load(arguments?.getString("thumbnail"))
                .into(view.ivThumdDesc)
        Glide.with(view.context).asBitmap()
                .load(arguments?.getString("imageCapa"))
                .into(view.ivCapaQuad)
        view.tvTitleQuad.text = arguments?.getString("title")
        view.tvDescQuad.text = arguments?.getString("desc")
        view.tvPubliQuad.text = arguments?.getString("date")
        view.tvPagesQuad.text = arguments?.getString("pages")
        view.tvPriceQuad.text = arguments?.getString("price")
        return view
    }


}