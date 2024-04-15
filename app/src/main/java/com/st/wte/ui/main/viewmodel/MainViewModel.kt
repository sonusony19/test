package com.st.wte.ui.main.viewmodel

import com.st.wte.callbacks.ApiListener
import com.st.wte.ui.base.viewmodel.BaseViewModel
import com.st.wte.ui.main.model.Post

class MainViewModel : BaseViewModel() {

    fun getPosts(page: Int, apiListener: ApiListener<List<Post>>) {
        var start: Int
        val limit: Int
        if (page == 1) {
            start = 0
            limit = 20
        } else {
            start = (page - 1) * 20
            limit = start + 20
            start += 1
        }
        repository.makeRequest(apiService.getPosts(start, limit), apiListener)
    }
}