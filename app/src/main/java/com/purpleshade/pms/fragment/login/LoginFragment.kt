package com.purpleshade.pms.fragment.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.purpleshade.pms.R
import com.purpleshade.pms.activity.BaseActivity
import com.purpleshade.pms.databinding.LoginFragmentBinding
import com.purpleshade.pms.db.RoomUser
import com.purpleshade.pms.fragment.BaseFragment
import com.purpleshade.pms.repository.LoginRepository
import com.purpleshade.pms.utils.customInterface.AuthListener
import com.purpleshade.pms.utils.customObject.Flag
import kotlinx.android.synthetic.main.activity_base.*
import kotlinx.android.synthetic.main.login_fragment.*
import java.lang.Exception


class LoginFragment : BaseFragment(), AuthListener {
    private lateinit var viewModel: LoginViewModel
    lateinit var binding: LoginFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.login_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Flag.backPressString = "login"
        val user = RoomUser()
        try {
            val listData = BaseActivity.INSTANCE!!.myDao().user
            if (listData.userId != null && listData.lockAppStatus == "off")
                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
            else if (listData.userId != null && listData.lockAppStatus == "on")
                findNavController().navigate(R.id.action_loginFragment_to_appPasswordFragment)
        } catch (e: Exception) {
            Log.d("exception", e.message.toString())
        }

        val repository = LoginRepository()
        binding.lifecycleOwner = this
        val factory = AuthViewModelFactory(baseActivity, repository, baseActivity.mProgressBar, user, mPasswordVisiblity, mUserPassword)

        viewModel = ViewModelProvider(this, factory).get(LoginViewModel::class.java)


        viewModel.authListener = this
        binding.viewModel = viewModel
        baseActivity.mToolbar.visibility = View.GONE


    }

    override fun onSuccess(responseSuccess: LiveData<String>) {
        responseSuccess.observe(this, Observer {

        })
    }

}
