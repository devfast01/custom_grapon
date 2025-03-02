/*
 *     Copyright (C) 2023  Akane Foundation
 *
 *     Gramophone is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     Gramophone is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.example.graponbest.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.media3.common.MediaItem
import com.example.graponbest.logic.utils.MediaStoreUtils

/**
 * LibraryViewModel:
 *   A ViewModel that contains library information.
 * Used across the application.
 *
 * @author AkaneTan, nift4
 */
class LibraryViewModel : ViewModel() {
    val mediaItemList: MutableLiveData<MutableList<MediaItem>> = MutableLiveData(mutableListOf())
    val albumItemList: MutableLiveData<MutableList<MediaStoreUtils.Album>> =
        MutableLiveData(mutableListOf())
    val albumArtistItemList: MutableLiveData<MutableList<MediaStoreUtils.Artist>> =
        MutableLiveData(
            mutableListOf(),
        )
    val artistItemList: MutableLiveData<MutableList<MediaStoreUtils.Artist>> =
        MutableLiveData(
            mutableListOf(),
        )
    val genreItemList: MutableLiveData<MutableList<MediaStoreUtils.Genre>> =
        MutableLiveData(
            mutableListOf(),
        )
    val dateItemList: MutableLiveData<MutableList<MediaStoreUtils.Date>> =
        MutableLiveData(
            mutableListOf(),
        )
    val playlistList: MutableLiveData<MutableList<MediaStoreUtils.Playlist>> =
        MutableLiveData(
            mutableListOf(),
        )
    val folderStructure: MutableLiveData<MediaStoreUtils.FileNode> =
        MutableLiveData(
            MediaStoreUtils.FileNode("storage", mutableListOf(), mutableListOf())
        )
    val shallowFolderStructure: MutableLiveData<MediaStoreUtils.FileNode> =
        MutableLiveData(
            MediaStoreUtils.FileNode("shallowFolder", mutableListOf(), mutableListOf())
        )
}
