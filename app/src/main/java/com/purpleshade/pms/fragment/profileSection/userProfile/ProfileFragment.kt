package com.purpleshade.pms.fragment.profileSection.userProfile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.purpleshade.pms.R
import com.purpleshade.pms.databinding.ProfileFragmentBinding
import com.purpleshade.pms.db.RoomUser
import com.purpleshade.pms.fragment.BaseFragment
import com.purpleshade.pms.utils.gone
import com.purpleshade.pms.utils.show
import kotlinx.android.synthetic.main.activity_base.*
import kotlinx.android.synthetic.main.profile_fragment.*

class ProfileFragment : BaseFragment() {

    private lateinit var viewModel: ProfileViewModel
    lateinit var binding: ProfileFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.profile_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val user = RoomUser()

        val factory = ProfileViewModelFactory(baseActivity, view, user)
        viewModel = ViewModelProvider(this, factory).get(ProfileViewModel::class.java)
       // viewModel.authListener = this
        binding.viewModel = viewModel

        baseActivity.mToolbar.show()
        baseActivity.mFragmentTitle.text = getString(R.string.user_profile)
        baseActivity.mBackButton.show()
        baseActivity.mProfileImageView.gone()
        baseActivity.mBackButton.setOnClickListener {
            findNavController().navigate(R.id.homeFragment)
        }
        baseActivity.mEditProfile.setOnClickListener {
            viewModel.onEditButtonClick()
        }
        viewModel.profileDetail()
    }

    /*override fun onSuccess(responseSuccess: LiveData<String>) {
        responseSuccess.observe(this, Observer {

        })
    }*/

}