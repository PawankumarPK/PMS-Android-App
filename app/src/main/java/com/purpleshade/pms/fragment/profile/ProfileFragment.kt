package com.purpleshade.pms.fragment.profile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.purpleshade.pms.R
import com.purpleshade.pms.fragment.BaseFragment
import com.purpleshade.pms.utils.gone
import com.purpleshade.pms.utils.show
import kotlinx.android.synthetic.main.activity_base.*

class ProfileFragment : BaseFragment() {

    companion object {
        fun newInstance() = ProfileFragment()
    }

    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.profile_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)

        baseActivity.mToolbar.show()
        baseActivity.mFragmentTitle.text = getString(R.string.user_profile)
        baseActivity.mBackButton.show()
        baseActivity.mProfileImageView.gone()
        baseActivity.mBackButton.setOnClickListener {
            findNavController().navigate(R.id.homeFragment)
        }
    }

}