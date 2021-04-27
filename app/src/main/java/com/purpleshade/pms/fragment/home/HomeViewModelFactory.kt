package com.purpleshade.pms.fragment.home

import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.purpleshade.pms.db.RoomUser
import com.purpleshade.pms.repository.HomeRepository

/**
 * Created by pawan on 04,June,2020
 */
@Suppress("UNCHECKED_CAST")
class HomeViewModelFactory(var context: Context, val view: View, val textView: TextView, val activity: Activity, private val repository: HomeRepository, val progressBar: ProgressBar, val user: RoomUser) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(context, view, textView, activity, repository, progressBar, user) as T
    }
}