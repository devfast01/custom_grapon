package com.example.graponbest.logic.utils

import com.example.graponbest.ui.adapters.AlbumAdapter
import com.example.graponbest.ui.adapters.ArtistAdapter
import com.example.graponbest.ui.adapters.BaseAdapter
import com.example.graponbest.ui.adapters.DateAdapter
import com.example.graponbest.ui.adapters.GenreAdapter
import com.example.graponbest.ui.adapters.PlaylistAdapter
import com.example.graponbest.ui.adapters.SongAdapter

object FileOpUtils {
    fun getAdapterType(adapter: BaseAdapter<*>) =
        when (adapter) {
            is AlbumAdapter -> {
                0
            }

            is ArtistAdapter -> {
                1
            }

            is DateAdapter -> {
                2
            }

            is GenreAdapter -> {
                3
            }

            is PlaylistAdapter -> {
                4
            }

            is SongAdapter -> {
                5
            }

            else -> {
                throw IllegalArgumentException()
            }
        }
}