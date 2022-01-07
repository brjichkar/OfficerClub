package com.android.officersclub.ui_section.home_section.home_section

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.officersclub.R
import com.android.officersclub.ui_section.home_section.booking_section.adapter.AdapterBooking
import com.android.officersclub.ui_section.home_section.booking_section.model.BookingModel
import com.android.officersclub.ui_section.home_section.home_section.adapter.AdapterNews
import com.android.officersclub.ui_section.home_section.home_section.model.NewsModel
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_home.*


class FragmentHome : Fragment() {
    private lateinit var mAdapter: AdapterNews
    private lateinit var rvNews: RecyclerView
    private lateinit var rvFacility: RecyclerView
    private var mNewsList: MutableList<NewsModel> = mutableListOf()
    private var mFacilitiesList: MutableList<NewsModel> = mutableListOf()
    private lateinit var mFacilityAdapter: AdapterNews

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val tempView = inflater.inflate(R.layout.fragment_home, container, false)
        sampleData()
        mAdapter= AdapterNews(mNewsList,requireContext())
        mFacilityAdapter= AdapterNews(mFacilitiesList,requireContext())

        val mLayoutManager = LinearLayoutManager(requireContext())
        mLayoutManager.orientation = LinearLayoutManager.HORIZONTAL

        rvNews= tempView.findViewById(R.id.rv_news)

        rvNews.layoutManager = mLayoutManager
        rvNews.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        rvNews.adapter = mAdapter

        val mLayoutManager1 = LinearLayoutManager(requireContext())
        mLayoutManager1.orientation = LinearLayoutManager.HORIZONTAL
        rvFacility = tempView.findViewById(R.id.rv_facility)
        rvFacility.layoutManager = mLayoutManager1
        rvFacility.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        rvFacility.adapter = mFacilityAdapter

        return tempView
    }

    private fun sampleData(){
        mNewsList.clear()
        mNewsList.add(0,NewsModel("","Rest House A great rest house"))
        mNewsList.add(1,NewsModel("","Function Hall A great function Hall"))
        mNewsList.add(2,NewsModel("","Reception Hall A great reception Hall"))
        mNewsList.add(3,NewsModel("","Party Lawn A great party Lawn"))
        mNewsList.add(0,NewsModel("","Rest House A great rest house"))
        mNewsList.add(1,NewsModel("","Function Hall A great function Hall"))
        mNewsList.add(2,NewsModel("","Reception Hall A great reception Hall"))
        mNewsList.add(3,NewsModel("","Party Lawn A great party Lawn"))

        mFacilitiesList.clear()
        mFacilitiesList.add(0,NewsModel("","Tennis"))
        mFacilitiesList.add(1,NewsModel("","Cricket"))
        mFacilitiesList.add(2,NewsModel("","Swimming"))
        mFacilitiesList.add(3,NewsModel("","Golf"))
        mFacilitiesList.add(0,NewsModel("","Carrom"))

    }
}