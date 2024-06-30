package com.filnik.goosegamekata

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner

class HiltTestRunner : AndroidJUnitRunner() {
    override fun newApplication(cl: ClassLoader?, className: String?, context: Context?): Application = super.newApplication(cl, MyApplication::class.java.name, context)
}
