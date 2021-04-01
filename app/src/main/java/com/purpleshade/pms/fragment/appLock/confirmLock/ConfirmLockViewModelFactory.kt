package com.purpleshade.pms.fragment.appLock.confirmLock

import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.purpleshade.pms.repository.ConfirmLockRepository

@Suppress("UNCHECKED_CAST")
class ConfirmLockViewModelFactory(
    val context: Context,
    val view: View,
    private val box1: EditText,
    private val box2: EditText,
    private val box3: EditText,
    private val box4: EditText,
    val button: Button,
    var progressBar: ProgressBar,
    var repository: ConfirmLockRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ConfirmLockViewModel(context, view, box1, box2, box3, box4, button, progressBar, repository) as T
    }

}