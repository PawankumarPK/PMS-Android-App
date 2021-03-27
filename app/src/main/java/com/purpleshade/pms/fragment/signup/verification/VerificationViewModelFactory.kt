package com.purpleshade.pms.fragment.signup.verification

import android.widget.EditText
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Created by pawan on 27,March,2021
 */
@Suppress("UNCHECKED_CAST")
class VerificationViewModelFactory(
    val box1: EditText,
    val box2: EditText,
    val box3: EditText,
    val box4: EditText,
    val box5: EditText,
    val box6: EditText
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return VerificationViewModel(box1,box2,box3,box4,box5,box6) as T
    }
}