package com.example.report.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.report.R
import com.example.report.adapters.ViewPagerAdapter
import com.example.report.viewmodels.ComentariosViewModel
import com.example.report.viewmodels.DetailsViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

@Suppress("DEPRECATION")
class Details : Fragment() {

    lateinit var v: View
    lateinit var viewPager: ViewPager2
    lateinit var tabLayout: TabLayout
    lateinit var viewModelDetails: DetailsViewModel
    lateinit var viewModelComentarios: ComentariosViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        v = inflater.inflate(R.layout.fragment_details, container, false)
        tabLayout = v.findViewById(R.id.tabLayout)
        viewPager = v.findViewById(R.id.viewPager)

        viewPager.setAdapter(ViewPagerAdapter(requireActivity()))

        TabLayoutMediator(tabLayout, viewPager, TabLayoutMediator.TabConfigurationStrategy { tab, position ->
            when (position) {
                0 -> tab.text = "Tema"
                1 -> tab.text = "Comentarios"
                else -> tab.text = "undefined"
            }
        }).attach()

        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModelDetails = ViewModelProvider(requireActivity()).get(DetailsViewModel::class.java)
        viewModelComentarios = ViewModelProvider(requireActivity()).get(ComentariosViewModel::class.java)
    }

    override fun onStart() {
        super.onStart()
        viewModelDetails.temaActual = DetailsArgs.fromBundle(requireArguments()).tema
        viewModelComentarios.initComentarios(viewModelDetails.posicionTema)
    }
}