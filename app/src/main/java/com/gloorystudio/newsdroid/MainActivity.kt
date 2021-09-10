package com.gloorystudio.newsdroid

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ScrollView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.gloorystudio.newsdroid.databinding.ActivityMainBinding
import com.gloorystudio.newsdroid.model.News
import com.gloorystudio.newsdroid.model.NewsSize
import com.gloorystudio.newsdroid.model.getNewsDummyData
import com.gloorystudio.newsdroid.news.*


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val fragmentList = arrayListOf<Fragment>()
    private var isDetailOpen = false
    private var detailFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        instance = this
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        prepareView()
    }

    private fun prepareView() {
        for (news in getNewsDummyData()) {
            val fragment = when (news.size) {
                NewsSize.BIG -> newInstance<BigNewFragment>(news)
                NewsSize.MID -> newInstance<MidNewFragment>(news)
                NewsSize.SMALL -> newInstance<SmallNewFragment>(news)
            }
            when (news.size) {
                NewsSize.BIG -> addFragment(fragment, binding.containerBigNews.id)
                NewsSize.MID -> addFragment(fragment, binding.containerMidNews.id)
                NewsSize.SMALL -> addFragment(fragment, binding.containerSmallNews.id)
            }
            fragmentList.add(fragment)
        }
    }

    fun onFragmentOnClick(news: News) {
        removeAllFragment()
        detailFragment = newInstance<DetailNewsFragment>(news)
        replaceFragment(detailFragment!!, binding.containerDetailNews.id)
        isDetailOpen = true
        binding.svMain.smoothScrollTo(0,0)
    }

    private fun removeAllFragment() {
        for (fragment in fragmentList) {
            if (fragment.isAdded) {
                supportFragmentManager.inTransaction {
                    remove(fragment)
                }
            }
        }
        fragmentList.clear()
    }

    override fun onBackPressed() {
        if (isDetailOpen) {
            supportFragmentManager.inTransaction {
                remove(detailFragment!!)
            }
            isDetailOpen = false
            prepareView()
            binding.svMain.smoothScrollTo(0,0)
        } else super.onBackPressed()
    }

    companion object {
        lateinit var instance: MainActivity
    }
}
