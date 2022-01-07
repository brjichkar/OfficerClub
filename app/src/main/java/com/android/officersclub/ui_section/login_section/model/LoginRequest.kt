package com.android.officersclub.ui_section.login_section.model

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose
import com.android.officersclub.ui_section.login_section.model.LoginRequest.Jsondata
import com.android.officersclub.ui_section.login_section.model.LoginRequest
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

        @SerializedName("auth_code")
        @Expose
        private var authCode: String? = null
        fun getAuthCode(): String? {
            return authCode
        }

        fun setAuthCode(authCode: String?) {
            this.authCode = "auth_code"
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
            sb.append(if (authCode == null) "<null>" else authCode)
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