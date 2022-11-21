package com.example.moview.Fragments.detalles

import android.media.Image
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import coil.load
import coil.request.CachePolicy
import coil.size.Scale
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import com.example.moview.Fragments.detalles.Adapter.DetailsAdapter
import com.example.moview.R
import com.google.android.material.tabs.TabLayout


class DetallesFragment : Fragment(R.layout.fragment_detalles) {
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager
    private lateinit var bannerView: ImageView
    private lateinit var mainImage: ImageView
    private lateinit var title: TextView
    private lateinit var data: TextView
    private lateinit var liked: TextView


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tabLayout = view.findViewById(R.id.tab_layout)
        viewPager = view.findViewById(R.id.details_view_pager)
        bannerView = view.findViewById(R.id.cartelPeliculaDetalles)
        mainImage = view.findViewById(R.id.posterPeliculaDetalles)
        bannerView.scaleType = ImageView.ScaleType.FIT_XY
        title = view.findViewById(R.id.nombrePelicula)
        data = view.findViewById(R.id.datosPelicula)
        liked = view.findViewById(R.id.porcentajeDeLikesDetalle)
        tabLayout.tabGravity = TabLayout.GRAVITY_CENTER

        initializer()


        val adapter = DetailsAdapter(requireContext(), requireActivity().supportFragmentManager, tabLayout.tabCount)
        viewPager.adapter = adapter

        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewPager.currentItem = tab!!.position            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })
    }

    private fun initializer() {
        bannerView.load("https://cdn.watchmode.com/backdrops/01295258_bd_w780.jpg"){
            memoryCachePolicy(CachePolicy.DISABLED)
            diskCachePolicy(CachePolicy.DISABLED)
            crossfade(enable = true)
            crossfade(450)
            placeholder(R.drawable.downloading_icon)
            error(R.drawable.error_icon)
        }
        mainImage.load("https://cdn.watchmode.com/posters/01295258_poster_w185.jpg"){
            memoryCachePolicy(CachePolicy.DISABLED)
            diskCachePolicy(CachePolicy.DISABLED)
            crossfade(enable = true)
            crossfade(450)
            placeholder(R.drawable.downloading_icon)
            error(R.drawable.error_icon)
            transformations(RoundedCornersTransformation(25.0F))
            scale(Scale.FILL)
        }
        title.text = "Breaking Bad"
        data.text = "2003 - Director"
        liked.text = "56" + "%"
    }

}