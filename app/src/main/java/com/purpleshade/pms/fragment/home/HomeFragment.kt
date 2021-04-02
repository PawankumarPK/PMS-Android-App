package com.purpleshade.pms.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.purpleshade.pms.R
import com.purpleshade.pms.activity.BaseActivity
import com.purpleshade.pms.databinding.HomeFragmentBinding
import com.purpleshade.pms.db.RoomUser
import com.purpleshade.pms.fragment.BaseFragment
import com.purpleshade.pms.repository.HomeRepository
import com.purpleshade.pms.utils.customInterface.AuthListener
import com.purpleshade.pms.utils.customObject.RoomRecordDetail
import com.purpleshade.pms.utils.customObject.Flag
import com.purpleshade.pms.utils.gone
import com.purpleshade.pms.utils.show
import com.purpleshade.pms.utils.toast
import kotlinx.android.synthetic.main.activity_base.*
import kotlinx.android.synthetic.main.home_fragment.*
import kotlinx.android.synthetic.main.password_viewholder.*
import java.lang.Exception

class HomeFragment : BaseFragment(), AuthListener {

    lateinit var fabButton: FloatingActionButton

    private lateinit var viewModel: HomeViewModel
    lateinit var binding: HomeFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.home_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Flag.backPressCount = 0
        val user = RoomUser()
        try {
            val roomuserId = BaseActivity.INSTANCE!!.myDao().user
            RoomRecordDetail.userId = roomuserId.userId.toString()
        } catch (e: Exception) {
            baseActivity.toast("Exception Occurred")
        }

        val repository = HomeRepository()
        val factory = HomeViewModelFactory(baseActivity, mBlankPageMsg, baseActivity, repository, baseActivity.mProgressBar, user)
        viewModel = ViewModelProvider(this, factory).get(HomeViewModel::class.java)
        viewModel.authListener = this
        binding.viewModel = viewModel

        baseActivity.mToolbar.show()
        baseActivity.mFragmentTitle.text = getString(R.string.allPassword)
        baseActivity.mBackButton.gone()
        baseActivity.mProfileImageView.show()
        baseActivity.mProfileImageView.setOnClickListener {
            viewModel.profileImageClick(view)
        }

        fabButton = view.findViewById(R.id.mFabButton)

        viewModel.loadAdapterList(mRecyclerView)

    }

    override fun onSuccess(responseSuccess: LiveData<String>) {
        responseSuccess.observe(this, Observer {

        })
    }

    override fun onPause() {
        super.onPause()
        Flag.backPressCount = 1
    }

}