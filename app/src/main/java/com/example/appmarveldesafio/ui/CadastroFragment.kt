package com.example.appmarveldesafio.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.appmarveldesafio.R
import kotlinx.android.synthetic.main.fragment_cadastro.view.*


class CadastroFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater!!.inflate(R.layout.fragment_cadastro, container, false)
        view.tbCadastro.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_cadastroFragment_to_loginFragment)
        }
        view.btnCad.setOnClickListener {
            findNavController().navigate(R.id.action_cadastroFragment_to_quadrinhosFragment)
        }
        return view
    }


}