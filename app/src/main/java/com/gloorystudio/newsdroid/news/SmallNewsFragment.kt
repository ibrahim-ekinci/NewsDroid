package com.gloorystudio.newsdroid.news

import android.os.Bundle
import android.view.View
import com.gloorystudio.newsdroid.R
import com.gloorystudio.newsdroid.databinding.FragmentSmallNewsBinding


class SmallNewFragment : BaseNewFragment<FragmentSmallNewsBinding>(R.layout.fragment_small_news) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.news = news
    }
}