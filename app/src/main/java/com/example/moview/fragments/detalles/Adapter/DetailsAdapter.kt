package com.example.moview.Fragments.detalles.Adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.moview.Fragments.detalles.Fragments.AboutFragment
import com.example.moview.Fragments.detalles.Fragments.CommentsFragment
import com.example.moview.Fragments.detalles.Fragments.CrewFragment

internal class DetailsAdapter(var context: Context, fm: FragmentManager, var totalTabs: Int): FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return totalTabs
    }

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> {
                AboutFragment()
            }
            1 -> {
                CrewFragment()
            }
            2 -> {
                CommentsFragment()
            }
            else ->{
                getItem(position)
            }
        }
    }

}