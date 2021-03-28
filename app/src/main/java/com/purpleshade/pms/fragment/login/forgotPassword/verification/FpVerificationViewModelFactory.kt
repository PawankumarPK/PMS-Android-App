package com.purpleshade.pms.fragment.login.forgotPassword.verification

import android.content.Context
import android.widget.EditText
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.purpleshade.pms.repository.FpVerificationRepository
import com.purpleshade.pms.repository.SignupVerificationRepository

@Suppress("UNCHECKED_CAST")
class FpVerificationViewModelFactory(
    val context: Context,
    val box1: EditText,
    val box2: EditText,
    val box3: EditText,
    val box4: EditText,
    val box5: EditText,
    val box6: EditText,
    val repository: FpVerificationRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FpVerificationViewModel(context,box1,box2,box3,box4,box5,box6,repository) as T
    }
}