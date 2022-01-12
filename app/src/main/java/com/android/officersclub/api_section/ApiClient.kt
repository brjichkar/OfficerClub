/**
 * Copyright (C) 2019 GoVida
 * @Class :  ApiClient
 * @Usage : This class is used for providing retrofit api functionality to application
 * @Author : 1276
 */

package com.android.officersclub.api_section

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okio.Buffer
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.io.InputStream
import java.security.GeneralSecurityException
import java.security.KeyStore
import java.security.cert.CertificateException
import java.security.cert.CertificateFactory
import java.util.*
import java.util.concurrent.TimeUnit
import javax.net.ssl.*


class ApiClient {
    private val baseUrl = "https://officersclubsolapur.com/api/"
    private lateinit var retrofit: Retrofit

    /**
     *  @Function : okHttpClient()
     *  @params   : void
     *  @Return   : void
     * 	@Usage	  : Get okHttpClient client to trigger api call
     *  @Author   : 1276
     */

    private val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(15, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()

    /**
     *  @Function : getClient()
     *  @params   : void
     *  @Return   : void
     * 	@Usage	  : Get retrofit client to trigger api call
     *  @Author   : 1276
     */
    fun getClient(): Retrofit {
        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit
    }


}
