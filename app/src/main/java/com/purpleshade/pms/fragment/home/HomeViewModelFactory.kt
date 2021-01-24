package com.purpleshade.pms.fragment.home

import android.app.Activity
import android.content.Context
import androidx.lifecycle.GeneratedAdapter
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.purpleshade.pms.fragment.home.adapter.PasswordsAdapter
import com.purpleshade.pms.repository.HomeRepository
import com.purpleshade.pms.repository.LoginRepository

/**
 * Created by pawan on 04,June,2020
 */
@Suppress("UNCHECKED_CAST")
class HomeViewModelFactory(private val context: Context, val activity: Activity,private val repository: HomeRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(context,activity, repository) as T
    }
}