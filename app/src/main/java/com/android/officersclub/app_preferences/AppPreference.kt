
package com.android.officersclub.app_preferences

import android.content.Context
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey


class AppPreference(context: Context)  {

    private var spec = KeyGenParameterSpec.Builder(
        MasterKey.DEFAULT_MASTER_KEY_ALIAS,
        KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT)
        .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
        .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
        .setKeySize(256)
        .build()

    private var masterKey = MasterKey.Builder(context)
        .setKeyGenParameterSpec(spec)
        .build()

    private var mSharedPreferences = EncryptedSharedPreferences.create(context, "OfficeClub", masterKey, EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM)


    // use the shared preferences and editor as you normally would
    private var mEditor = mSharedPreferences.edit()


    /**
     *  @Function : clearAllPreferences()
     *  @params   : Void
     *  @Return   : Void
     * 	@Usage	  : Reset Shared Preferences
     *  @Author   : 1276
     */
    fun clearAllPreferences() {
        mEditor.clear()
        mEditor.commit()
    }

    var isUserLoggedIn: Boolean
        get() =  mSharedPreferences.getBoolean("isUserLoggedIn", false)
        set(value) {
            mEditor.putBoolean("isUserLoggedIn", value)
            mEditor.commit()
        }


    var usersId: String
        get() = mSharedPreferences.getString("usersId", "")!!
        set(value) {
            mEditor.putString("usersId", value)
            mEditor.commit()
        }

    var userMobile: String
        get() =  mSharedPreferences.getString("userMobile", "")!!
        set(value) {
            mEditor.putString("userMobile", value)
            mEditor.commit()
        }


    var userProfile: String
        get() =  mSharedPreferences.getString("userProfile", "")!!
        set(value) {
            mEditor.putString("userProfile", value)
            mEditor.commit()
        }

    var userName: String
        get() =  mSharedPreferences.getString("userName", "")!!
        set(value) {
            mEditor.putString("userName", value)
            mEditor.commit()
        }


    var userEmail: String
        get() =  mSharedPreferences.getString("userEmail", "")!!
        set(value) {
            mEditor.putString("userEmail", value)
            mEditor.commit()
        }


    var totalMaleChildren: Int
        get() =  mSharedPreferences.getInt("totalMaleChildren", 0)
        set(value) {
            mEditor.putInt("totalMaleChildren", value)
            mEditor.commit()
        }



}
