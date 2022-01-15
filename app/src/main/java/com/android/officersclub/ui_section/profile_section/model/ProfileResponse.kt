package com.android.officersclub.ui_section.profile_section.model

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose
import com.android.officersclub.ui_section.profile_section.model.ProfileResponse
import java.lang.StringBuilder

class ProfileResponse {
    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("data")
    @Expose
    var data: Data? = null
    override fun toString(): String {
        val sb = StringBuilder()
        sb.append(ProfileResponse::class.java.name).append('@')
            .append(Integer.toHexString(System.identityHashCode(this))).append('[')
        sb.append("status")
        sb.append('=')
        sb.append(if (status == null) "<null>" else status)
        sb.append(',')
        sb.append("data")
        sb.append('=')
        sb.append(if (data == null) "<null>" else data)
        sb.append(',')
        if (sb[sb.length - 1] == ',') {
            sb.setCharAt(sb.length - 1, ']')
        } else {
            sb.append(']')
        }
        return sb.toString()
    }

    inner class Data {
        @SerializedName("data")
        @Expose
        var data: ProfileData? = null
        override fun toString(): String {
            val sb = StringBuilder()
            sb.append(Data::class.java.name).append('@')
                .append(Integer.toHexString(System.identityHashCode(this))).append('[')
            sb.append("data")
            sb.append('=')
            sb.append(if (this.data == null) "<null>" else this.data)
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

            @SerializedName("image_path")
            @Expose
            var imagePath: String? = null
        }
    }
}