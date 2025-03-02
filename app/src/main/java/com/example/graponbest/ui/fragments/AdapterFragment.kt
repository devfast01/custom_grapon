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

package com.example.graponbest.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import me.zhanghai.android.fastscroll.FastScrollerBuilder
import me.zhanghai.android.fastscroll.PopupTextProvider
import com.example.graponbest.R
import com.example.graponbest.ui.MainActivity
import com.example.graponbest.ui.adapters.AlbumAdapter
import com.example.graponbest.ui.adapters.ArtistAdapter
import com.example.graponbest.ui.adapters.DateAdapter
import com.example.graponbest.ui.adapters.DetailedFolderAdapter
import com.example.graponbest.ui.adapters.FolderAdapter
import com.example.graponbest.ui.adapters.GenreAdapter
import com.example.graponbest.ui.adapters.PlaylistAdapter
import com.example.graponbest.ui.adapters.SongAdapter
import com.example.graponbest.ui.viewmodels.LibraryViewModel
import org.akanework.gramophone.ui.fragments.BaseFragment

/**
 * AdapterFragment:
 *   This fragment is the container for any list that contains
 * recyclerview in the program.
 *
 * @author nift4
 */
class AdapterFragment : BaseFragment(null) {
    private val libraryViewModel: LibraryViewModel by activityViewModels()

    private lateinit var adapter: BaseInterface<*>
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_recyclerview, container, false)
        recyclerView = rootView.findViewById(R.id.recyclerview)
        adapter = createAdapter(requireActivity() as MainActivity, libraryViewModel)
        recyclerView.adapter = adapter.concatAdapter

        FastScrollerBuilder(recyclerView).apply {
            setPopupTextProvider(adapter)
            useMd2Style()
            setTrackDrawable(
                AppCompatResources.getDrawable(
                    requireContext(),
                    R.drawable.ic_transparent
                )!!
            )
            build()
        }
        return rootView
    }

    private fun createAdapter(m: MainActivity, v: LibraryViewModel): BaseInterface<*> {
        return when (arguments?.getInt("ID", -1)) {
            R.id.songs -> SongAdapter(m, v.mediaItemList, true, null, true)
            R.id.albums -> AlbumAdapter(m, v.albumItemList)
            R.id.artists -> ArtistAdapter(m, v.artistItemList, v.albumArtistItemList)
            R.id.genres -> GenreAdapter(m, v.genreItemList)
            R.id.dates -> DateAdapter(m, v.dateItemList)
            R.id.folders -> FolderAdapter(m, v.folderStructure)
            R.id.detailed_folders -> DetailedFolderAdapter(m, v.shallowFolderStructure)
            R.id.playlists -> PlaylistAdapter(m, v.playlistList)
            -1, null -> throw IllegalArgumentException("unset ID value")
            else -> throw IllegalArgumentException("invalid ID value")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        adapter.concatAdapter.adapters.forEach {
            it.onDetachedFromRecyclerView(recyclerView)
        }
    }

    abstract class BaseInterface<T : RecyclerView.ViewHolder>
        : RecyclerView.Adapter<T>(), PopupTextProvider {
        abstract val concatAdapter: ConcatAdapter
    }
}
