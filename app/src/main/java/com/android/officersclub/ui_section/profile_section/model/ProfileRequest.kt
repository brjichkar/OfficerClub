package com.android.officersclub.ui_section.profile_section.model

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose
import java.lang.StringBuilder

class ProfileRequest {
    @SerializedName("jsondata")
    @Expose
    var jsondata: ProfileRequest.Jsondata? = null
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

    inner class Jsondata {
        @SerializedName("user_id")
        @Expose
        var userId: String? = null
        override fun toString(): String {
            val sb = StringBuilder()
            sb.append(Jsondata::class.java.name).append('@')
                .append(Integer.toHexString(System.identityHashCode(this))).append('[')
            sb.append("userId")
            sb.append('=')
            sb.append(if (userId == null) "<null>" else userId)
            sb.append(',')
            if (sb[sb.length - 1] == ',') {
                sb.setCharAt(sb.length - 1, ']')
            } else {
                sb.append(']')
            }
            return sb.toString()
        }
    }
}