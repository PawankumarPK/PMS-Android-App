package com.purpleshade.pms.fragment.createLock

import android.view.View
import android.widget.EditText
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Created by pawan on 06,March,2021
 */
@Suppress("UNCHECKED_CAST")
class CreateLockViewModelFactory(val view: View, private val box1: EditText, private val box2: EditText, private val box3: EditText, private val box4: EditText) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CreateLockViewModel(view,box1, box2, box3, box4) as T
    }
}