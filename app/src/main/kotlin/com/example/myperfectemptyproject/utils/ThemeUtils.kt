package com.example.myperfectemptyproject.utils

import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.BindingAdapter
import com.google.android.material.button.MaterialButtonToggleGroup

enum class Theme { LIGHT, DARK }

@BindingAdapter("changeTheme", "idFirstButton", "idSecondButton", requireAll = true)
fun MaterialButtonToggleGroup.changeTheme(theme: Theme?, idFirstButton: Int, idSecondButton: Int) {
    when (theme) {
        Theme.LIGHT -> {
            this.check(idFirstButton)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
        Theme.DARK -> {
            this.check(idSecondButton)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
    }
}
