package com.tubetoast.giffy.presentation.fragments

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.tubetoast.giffy.R

open class RecyclerFragment : Fragment(R.layout.recycler_vertical) {
    protected val recycler: RecyclerView?
        get() = view?.findViewById(R.id.recycler)
}