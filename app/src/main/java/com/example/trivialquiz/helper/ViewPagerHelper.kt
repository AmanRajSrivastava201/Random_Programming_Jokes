package com.example.trivialquiz.helper

import androidx.viewpager2.widget.ViewPager2
import kotlinx.coroutines.*

class ViewPagerHelper {
    private var timerJob: Job? = null

    fun autoSwipeViewPager(vp_banner: ViewPager2) {
        timerJob = CoroutineScope(Dispatchers.IO).launch {
            delay(3000L)
            withContext(Dispatchers.Main) {
                val adapterSize = vp_banner.adapter?.itemCount ?: 0
                var currentItem = vp_banner.currentItem
                if (currentItem >= adapterSize.minus(1)) {
                    currentItem = 0
                } else {
                    currentItem += 1
                }
                vp_banner.currentItem = currentItem

                autoSwipeViewPager(vp_banner)
            }
        }
    }

}