package com.purpleshade.pms.fragment.signup.verification

import android.content.Context
import android.widget.EditText
import android.widget.ProgressBar
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.purpleshade.pms.repository.SignupVerificationRepository

/**
 * Created by pawan on 27,March,2021
 */
@Suppress("UNCHECKED_CAST")
class VerificationViewModelFactory(
    val context:Context,
    val box1: EditText,
    val box2: EditText,
    val box3: EditText,
    val box4: EditText,
    val box5: EditText,
    val box6: EditText,
    var progressBar: ProgressBar,
    val repository: SignupVerificationRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return VerificationViewModel(context,box1,box2,box3,box4,box5,box6,progressBar,repository) as T
    }
}