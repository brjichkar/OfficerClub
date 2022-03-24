package com.officersclub.ui_section.login_section.model

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose
import java.lang.StringBuilder

class LoginRequest {
    @SerializedName("jsondata")
    @Expose
    var jsondata: Jsondata? = null
    override fun toString(): String {
        val sb = StringBuilder()
        sb.append(LoginRequest::class.java.name).append('@')
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
        @SerializedName("mobile")
        @Expose
        var mobile: String? = null

        @SerializedName("password")
        @Expose
        private var password: String? = null
        fun getAuthCode(): String? {
            return password
        }

        fun setAuthCode(authCode: String?) {
            this.password = authCode
        }

        override fun toString(): String {
            val sb = StringBuilder()
            sb.append(Jsondata::class.java.name).append('@')
                .append(Integer.toHexString(System.identityHashCode(this))).append('[')
            sb.append("mobile")
            sb.append('=')
            sb.append(if (mobile == null) "<null>" else mobile)
            sb.append(',')
            sb.append("authCode")
            sb.append('=')
            sb.append(if (password == null) "<null>" else password)
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