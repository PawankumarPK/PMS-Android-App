package com.purpleshade.pms.fragment.profileSection.editProfile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.purpleshade.pms.R
import com.purpleshade.pms.activity.BaseActivity
import com.purpleshade.pms.databinding.EditProfileFragmentBinding
import com.purpleshade.pms.db.RoomUser
import com.purpleshade.pms.fragment.BaseFragment
import com.purpleshade.pms.repository.EditProfileRepository
import com.purpleshade.pms.utils.customInterface.AuthListener
import com.purpleshade.pms.utils.gone
import com.purpleshade.pms.utils.show
import kotlinx.android.synthetic.main.activity_base.*
import kotlinx.android.synthetic.main.edit_profile_fragment.*

class EditProfileFragment : BaseFragment(), AuthListener {

    private lateinit var viewModel: EditProfileViewModel
    lateinit var binding: EditProfileFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.edit_profile_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val user = RoomUser()

        val repository = EditProfileRepository()
        val factory = EditProfileViewModelFactory(baseActivity, user, view, mUserName, mEmailAddress, repository)
        viewModel = ViewModelProvider(this, factory).get(EditProfileViewModel::class.java)
        viewModel.authListener = this
        binding.viewModel = viewModel

        baseActivity.mToolbar.show()
        baseActivity.mFragmentTitle.text = getString(R.string.edit_profile)
        baseActivity.mBackButton.show()
        baseActivity.mProfileImageView.gone()
        baseActivity.mBackButton.setOnClickListener {
            findNavController().navigate(R.id.profileFragment)
        }

        viewModel.getUserDetailForUpdate()
    }


    override fun onSuccess(responseSuccess: LiveData<String>) {
        responseSuccess.observe(this, Observer {

        })
    }

}