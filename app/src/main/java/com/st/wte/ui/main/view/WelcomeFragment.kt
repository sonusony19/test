package com.st.wte.ui.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.st.wte.adapters.PostAdapter
import com.st.wte.callbacks.ApiListener
import com.st.wte.databinding.FragmentWelcomeBinding
import com.st.wte.ui.base.model.ErrorBody
import com.st.wte.ui.base.view.BaseFragment
import com.st.wte.ui.main.model.Post
import com.st.wte.ui.main.viewmodel.MainViewModel
import com.st.wte.utils.showShortToast

class WelcomeFragment : BaseFragment<MainViewModel>(MainViewModel::class), ApiListener<List<Post>> {

    private lateinit var adapter: PostAdapter
    private var binding: FragmentWelcomeBinding? = null
    private var next = false
    private var loading = false
    private var page = 1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        init()
        getPosts()
        return binding?.root
    }

    private fun init() {
        adapter = PostAdapter(requireContext(), arrayListOf())
        binding?.apply {
            posts.adapter = adapter
            posts.addOnScrollListener(scroller)
        }
    }

    private val scroller = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            if (adapter.itemCount <= 5 || loading || !next) return
            val layoutManager = binding?.posts?.layoutManager as LinearLayoutManager
            if (layoutManager.findLastCompletelyVisibleItemPosition() >= adapter.itemCount - 5) {
                page++
                next = false
                getPosts()
            }
        }
    }

    private fun getPosts() {
        showShortToast("Loading")
        loading = true
        viewModel.getPosts(page, this)
    }

    override fun onSuccess(response: List<Post>) {
        next = response.isNotEmpty()
        loading = false
        adapter.addItems(response, page)
    }

    override fun onError(error: ErrorBody) {
        loading = false
        showShortToast(error.message ?: "")
    }

    override fun onFailure(errorMessage: String) {
        loading = false
        showShortToast(errorMessage)
    }
}