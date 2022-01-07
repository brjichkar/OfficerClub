package com.android.officersclub.ui_section.login_section.model

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose
import com.android.officersclub.ui_section.login_section.model.LoginResponse
import com.android.officersclub.ui_section.login_section.model.LoginResponse.Data.ResponseData
import java.lang.StringBuilder

class LoginResponse {
    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("data")
    @Expose
    var data: Data? = null
    override fun toString(): String {
        val sb = StringBuilder()
        sb.append(LoginResponse::class.java.name).append('@')
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
        @SerializedName("status")
        @Expose
        var status: String? = null

        @SerializedName("data")
        @Expose
        var data: ResponseData? = null
        override fun toString(): String {
            val sb = StringBuilder()
            sb.append(Data::class.java.name).append('@')
                .append(Integer.toHexString(System.identityHashCode(this))).append('[')
            sb.append("status")
            sb.append('=')
            sb.append(if (this.status == null) "<null>" else this.status)
            sb.append(',')
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

        inner class ResponseData {
            @SerializedName("user_id")
            @Expose
            var userId: String? = null

            @SerializedName("mobile")
            @Expose
            var mobile: String? = null

            @SerializedName("profile")
            @Expose
            var profile: String? = null
            override fun toString(): String {
                val sb = StringBuilder()
                sb.append(ResponseData::class.java.name).append('@').append(
                    Integer.toHexString(
                        System.identityHashCode(this)
                    )
                ).append('[')
                sb.append("userId")
                sb.append('=')
                sb.append(if (userId == null) "<null>" else userId)
                sb.append(',')
                sb.append("mobile")
                sb.append('=')
                sb.append(if (mobile == null) "<null>" else mobile)
                sb.append(',')
                sb.append("profile")
                sb.append('=')
                sb.append(if (profile == null) "<null>" else profile)
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
}