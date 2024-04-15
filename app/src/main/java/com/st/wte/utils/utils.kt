package com.st.wte.utils

import androidx.annotation.StringRes
import com.st.wte.application.ThisApplication


fun getStringResource(@StringRes res: Int) = ThisApplication.getContext().getString(res)