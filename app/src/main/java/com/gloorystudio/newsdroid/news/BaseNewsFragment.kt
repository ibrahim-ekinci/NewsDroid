package com.gloorystudio.newsdroid.news

import android.os.Bundle
import android.transition.Slide
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.gloorystudio.newsdroid.model.News

const val ARG_NEWS = "news"

open class BaseNewFragment<DB : ViewDataBinding>(@LayoutRes private val layoutResId: Int) :
    Fragment() {

    var news: News? = null
    private var _binding: DB? = null
    val binding: DB get() = _binding!!

    open fun DB.initialize() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            news = it.getParcelable(ARG_NEWS)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, layoutResId, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.initialize()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

inline fun <reified F : Fragment> newInstance(news: News): F =
    F::class.java.newInstance().apply {
        arguments = Bundle().apply {
            putParcelable(ARG_NEWS, news)
        }
        enterTransition = Slide(Gravity.BOTTOM)
    }