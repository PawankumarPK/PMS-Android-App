package com.purpleshade.pms.fragment.login.forgotPassword.verification

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.purpleshade.pms.R

class FpVerificationViewModel : ViewModel() {


    fun verifyOnClick(view: View){
        view.findNavController().navigate(R.id.action_fpVerificationFragment_to_createNewPassFragment)

    }
}