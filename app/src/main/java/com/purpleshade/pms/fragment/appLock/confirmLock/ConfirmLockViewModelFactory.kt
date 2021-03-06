package com.purpleshade.pms.fragment.appLock.confirmLock

import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class ConfirmLockViewModelFactory(val view: View, private val box1: EditText, private val box2: EditText, private val box3: EditText, private val box4: EditText,val button:Button) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ConfirmLockViewModel(view, box1, box2, box3, box4,button) as T
    }

}