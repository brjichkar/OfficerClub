package com.android.officersclub.ui_section.profile_section.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.lang.StringBuilder

class ProfileUpdateRequest {
    @SerializedName("jsondata")
    @Expose
    var jsondata: ProfileData? = null
    override fun toString(): String {
        val sb = StringBuilder()
        sb.append(ProfileRequest::class.java.name).append('@')
            .append(Integer.toHexString(System.identityHashCode(this))).append('[')
        sb.append("jsondata")
        sb.append('=')
        sb.append(if (jsondata == null) "<null>" else jsondata)
        sb.append(',')
        if (sb[sb.length - 1] == ',') {
            sb.setCharAt(sb.length - 1, ']')
        } else {
            sb.append(']')
        }
        return sb.toString()
    }
    inner class ProfileData {
        @SerializedName("user_id")
        @Expose
        var userId: String? = null

        @SerializedName("fname")
        @Expose
        var fname: String? = null

        @SerializedName("mname")
        @Expose
        var mname: String? = null

        @SerializedName("lname")
        @Expose
        var lname: String? = null

        @SerializedName("mobile")
        @Expose
        var mobile: String? = null

        @SerializedName("email")
        @Expose
        var email: String? = null

        @SerializedName("dob")
        @Expose
        var dob: String? = null

        @SerializedName("married")
        @Expose
        var married: String? = null

        @SerializedName("anniversary_date")
        @Expose
        var anniversaryDate: String? = null

        @SerializedName("gender")
        @Expose
        var gender: String? = null

        @SerializedName("business")
        @Expose
        var business: String? = null

        @SerializedName("designation")
        @Expose
        var designation: String? = null

        @SerializedName("department")
        @Expose
        var department: String? = null
    }
}