package com.st.wte.koin

import com.st.wte.ui.base.viewmodel.BaseViewModel
import com.st.wte.ui.main.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModels = module {
    viewModel { BaseViewModel() }
    viewModel { MainViewModel() }
}