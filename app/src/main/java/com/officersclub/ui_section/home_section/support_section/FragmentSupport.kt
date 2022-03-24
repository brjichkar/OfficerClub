package com.officersclub.ui_section.home_section.support_section

import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.officersclub.R
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import androidx.annotation.Nullable
import com.officersclub.app_preferences.AppPreference
import com.officersclub.ui_section.base_section.BaseFragment
import com.officersclub.ui_section.home_section.support_section.model.req.Jsondata
import com.officersclub.ui_section.home_section.support_section.model.req.RatingRequest
import com.officersclub.ui_section.home_section.support_section.model.resps.RatingResponses
import com.officersclub.ui_section.home_section.support_section.mvp.SupportsMVP
import com.officersclub.ui_section.home_section.support_section.mvp.SupportsPresenterImplementer
import com.hsalf.smileyrating.SmileyRating
import kotlinx.android.synthetic.main.fragment_support.*
import com.karumi.dexter.PermissionToken

import com.karumi.dexter.listener.PermissionDeniedResponse

import com.karumi.dexter.listener.PermissionGrantedResponse

import com.karumi.dexter.listener.single.PermissionListener

import com.karumi.dexter.Dexter
import com.karumi.dexter.listener.PermissionRequest
import com.google.zxing.integration.android.IntentIntegrator
import android.content.ActivityNotFoundException





class FragmentSupport : BaseFragment(), SupportsMVP.SupportsView {
    private lateinit var mAppPreference: AppPreference
    private lateinit var mPresenter: SupportsMVP.SupportsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mAppPreference= AppPreference(requireContext())
        mPresenter=SupportsPresenterImplementer(this)
        return inflater.inflate(R.layout.fragment_support, container, false)
    }

    override fun onResume() {
        super.onResume()
        mPresenter.attachView(this)
    }

    override fun onDestroy() {
        mPresenter.destroyView()
        super.onDestroy()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rl_feedback.setOnClickListener {
            showAlertDialog()
        }

        rl_review.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("market://details?id=com.officersclub")
            startActivity(intent)
        }

        rl_scan_feedback.setOnClickListener {
            Dexter.withContext(requireContext())
                .withPermission(Manifest.permission.CAMERA)
                .withListener(object : PermissionListener {
                    override fun onPermissionGranted(response: PermissionGrantedResponse) {
                        val intentIntegrator = IntentIntegrator(requireActivity())
                        intentIntegrator.setPrompt("Scan a barcode or QR Code")
                        intentIntegrator.setOrientationLocked(true)
                        intentIntegrator.initiateScan()
                    }

                    override fun onPermissionDenied(response: PermissionDeniedResponse) {
                        onError("Please enable call permission.")
                    }

                    override fun onPermissionRationaleShouldBeShown(
                        permission: PermissionRequest?,
                        token: PermissionToken?
                    ) {
                    }
                }).check()
        }


        rl_contact.setOnClickListener {

            Dexter.withContext(requireContext())
                .withPermission(Manifest.permission.CALL_PHONE)
                .withListener(object : PermissionListener {
                    override fun onPermissionGranted(response: PermissionGrantedResponse) {
                        val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "8087027127"))
                        startActivity(intent)
                    }

                    override fun onPermissionDenied(response: PermissionDeniedResponse) {
                        onError("Please enable call permission.")
                    }

                    override fun onPermissionRationaleShouldBeShown(
                        permission: PermissionRequest?,
                        token: PermissionToken?
                    ) { /* ... */
                    }
                }).check()
        }
    }


    private fun showAlertDialog() {
        val alertDialog: AlertDialog.Builder = AlertDialog.Builder(requireActivity())
        val customLayout: View = layoutInflater.inflate(R.layout.latout_feedback, null)
        alertDialog.setView(customLayout)
        alertDialog.setPositiveButton("OK",
            DialogInterface.OnClickListener { dialog, which -> // send data from the AlertDialog to the Activity
                dialog.cancel()
                val cleanRating : SmileyRating =customLayout.findViewById(R.id.tv_clean)
                val trainingRating: SmileyRating =customLayout.findViewById(R.id.tv_training)
                //onError("Cleanliness - "+cleanRating.selectedSmiley.rating+" and Training - "+trainingRating.selectedSmiley.rating)

                var cleans=""
                var trains=""

                if(cleanRating.selectedSmiley.rating==-1){
                    cleans="0"
                }
                else{
                    cleans=""+cleanRating.selectedSmiley.rating
                }

                if(trainingRating.selectedSmiley.rating==-1){
                    trains="0"
                }
                else{
                    trains=""+trainingRating.selectedSmiley.rating
                }

                val req = RatingRequest(Jsondata(cleans,trains,mAppPreference.usersId))
                mPresenter.onSupportsRequest(req)
            })

        val alert: AlertDialog = alertDialog.create()
        alert.setCanceledOnTouchOutside(false)
        alert.show()
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        @Nullable data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        val intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        // if the intentResult is null then
        // toast a message as "cancelled"
        if (intentResult != null) {
            if (intentResult.contents == null) {

            } else {
                // if the intentResult is not null we'll set
                // the content and format of scan message
               //onError(intentResult.contents)
                try {
                    val webpage = Uri.parse(intentResult.contents)
                    val myIntent = Intent(Intent.ACTION_VIEW, webpage)
                    startActivity(myIntent)
                } catch (e: ActivityNotFoundException) {
                    onError("Error in scanning")
                    e.printStackTrace()
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun onSupportsSuccessful(tempResponse: RatingResponses) {
        onError("Rating submitted successfully.")
    }

}