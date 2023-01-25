package com.example.lyricsformegha.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Song(
    @StringRes val title: Int,
    @StringRes val artist: Int,
    @StringRes val lyric: Int,
    @DrawableRes val albumCover: Int

)
