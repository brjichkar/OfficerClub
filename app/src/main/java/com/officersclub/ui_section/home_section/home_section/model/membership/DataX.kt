package com.officersclub.ui_section.home_section.home_section.model.membership

data class DataX(
    val end_date: String,
    val enrolled: Int,
    val enrolled_facilities: List<EnrolledFacility>,
    val members: String,
    val membership_date: String,
    val user_id: String
)