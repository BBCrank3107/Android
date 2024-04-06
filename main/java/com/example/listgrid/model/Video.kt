package com.example.listgrid.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Video (
    @DrawableRes val imageVideo: Int,
    @DrawableRes val imageAvt: Int,
    @StringRes val videoName: Int,
    @StringRes val chanelName: Int,
    val view: Int,
    val time: Int
)
