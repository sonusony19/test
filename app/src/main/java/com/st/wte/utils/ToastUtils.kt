package com.st.wte.utils

import android.widget.Toast
import androidx.annotation.StringRes
import com.st.wte.application.ThisApplication

fun showShortToast(message: String) {
    show(message, Toast.LENGTH_SHORT)
}

fun showShortToast(@StringRes resource: Int) {
    show(getStringResource(resource), Toast.LENGTH_SHORT)
}

fun showLongToast(message: String) {
    show(message, Toast.LENGTH_LONG)
}

fun showLongToast(@StringRes resource: Int) {
    show(getStringResource(resource), Toast.LENGTH_LONG)
}

private fun show(message: String, length: Int) {
    if (message.isEmpty()) return
    Toast.makeText(ThisApplication.getContext(), message, if (length == Toast.LENGTH_LONG) Toast.LENGTH_LONG else Toast.LENGTH_SHORT).show()
}