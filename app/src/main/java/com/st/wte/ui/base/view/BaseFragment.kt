package com.st.wte.ui.base.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatDialog
import androidx.fragment.app.Fragment
import com.st.wte.ui.base.viewmodel.BaseViewModel
import org.koin.androidx.viewmodel.ext.android.viewModelForClass
import kotlin.reflect.KClass

const val ARG_PARAM1 = "ARG_PARAM1"
const val ARG_PARAM2 = "ARG_PARAM2"

open class BaseFragment<VM : BaseViewModel>(private val viewModelClass: KClass<VM>) : Fragment() {

    protected val TAG = "LOGGER"
    protected lateinit var viewModel: VM
    private var loader: AppCompatDialog? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        baseInit()
    }

    private fun baseInit() {
        viewModel = viewModelForClass(clazz = viewModelClass).value
    }


    open fun observeActivityResult(result: ActivityResult) {}

    protected var activityLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        observeActivityResult(it)
    }

    override fun onDestroy() {
        loader?.cancel()
        super.onDestroy()
    }

}