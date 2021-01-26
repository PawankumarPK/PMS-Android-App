package com.purpleshade.pms.fragment.createRecord

import android.content.Context
import android.widget.ProgressBar
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.purpleshade.pms.repository.CreateRecordRepository

/**
 * Created by pawan on 17,January,2021
 */
@Suppress("UNCHECKED_CAST")
class CreateRecordViewModelFactory(private var context: Context, private var repository: CreateRecordRepository,val progressBar: ProgressBar) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CreateRecordViewModel(context, repository,progressBar) as T
    }
}