package com.vs.notino.extensions

import android.app.Activity
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar

val Any.TAG: String
    get() {
        return if (!javaClass.isAnonymousClass) {
            val name = javaClass.simpleName
            if (name.length <= 23) name else name.substring(0, 23)
        } else {
            val name = javaClass.name
            if (name.length <= 23) name else name.substring(name.length - 23, name.length)
        }
    }

fun Activity.showSnackbar(message: String) {
    Snackbar.make(
        this,
        findViewById<ViewGroup>(android.R.id.content),
        message,
        Snackbar.LENGTH_SHORT
    ).show()
}