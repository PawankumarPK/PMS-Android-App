package com.purpleshade.pms.fragment.update

import android.content.Context
import android.widget.ProgressBar
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.purpleshade.pms.repository.LoginRepository
import com.purpleshade.pms.repository.UpdateRecordRepository

/**
 * Created by pawan on 04,June,2020
 */
@Suppress("UNCHECKED_CAST")
class UpdateViewModelFactory(private val context: Context, private val repository: UpdateRecordRepository, val progressBar: ProgressBar) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return UpdateRecordViewModel(context, repository, progressBar) as T
    }
}