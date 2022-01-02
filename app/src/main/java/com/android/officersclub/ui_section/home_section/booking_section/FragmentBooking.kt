package com.android.officersclub.ui_section.home_section.booking_section

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.officersclub.R
import com.android.officersclub.ui_section.home_section.booking_section.adapter.AdapterBooking
import com.android.officersclub.ui_section.home_section.booking_section.model.BookingModel

class FragmentBooking : Fragment() {
    private var mHistoryList: MutableList<BookingModel> = mutableListOf()
    private lateinit var mAdapter: AdapterBooking
    private lateinit var rvHistory:RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val tempView = inflater.inflate(R.layout.fragment_booking, container, false)
        sampleData()
        mAdapter= AdapterBooking(mHistoryList,requireContext())
        val mLayoutManager = androidx.recyclerview.widget.LinearLayoutManager(requireContext())
        rvHistory= tempView.findViewById(R.id.rv_history)
        rvHistory.layoutManager = mLayoutManager
        rvHistory.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        rvHistory.adapter = mAdapter

        return tempView
    }

    private fun sampleData(){
        mHistoryList.clear()
        mHistoryList.add(0, BookingModel(1,"","Rest House","A great rest house"))
        mHistoryList.add(1,BookingModel(2,"","Function Hall","A great function Hall"))
        mHistoryList.add(2,BookingModel(3,"","Reception Hall","A great reception Hall"))
        mHistoryList.add(3,BookingModel(4,"","Party Lawn","A great party Lawn"))
        mHistoryList.add(0, BookingModel(5,"","Rest House","A great rest house"))
        mHistoryList.add(1,BookingModel(6,"","Function Hall","A great function Hall"))
        mHistoryList.add(2,BookingModel(7,"","Reception Hall","A great reception Hall"))
        mHistoryList.add(3,BookingModel(8,"","Party Lawn","A great party Lawn"))
    }

}