package com.example.vineyard_iot

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class PageAdapter(var context: Context, fm: FragmentManager, var totalTabs: Int):
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> { VineyardFragment() }
            1 -> { WineCellarFragment() }
            2 -> { WineBarrelFragment() }
            else ->  getItem(position)
        }
    }

    override fun getCount(): Int {
        return totalTabs
    }
}