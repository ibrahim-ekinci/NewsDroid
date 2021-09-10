package com.gloorystudio.newsdroid.news

import android.os.Bundle
import android.view.View
import android.widget.ScrollView
import com.gloorystudio.newsdroid.R
import com.gloorystudio.newsdroid.databinding.FragmentDetailNewsBinding

class DetailNewsFragment : BaseNewFragment<FragmentDetailNewsBinding>(R.layout.fragment_detail_news) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.news = news
    }
}