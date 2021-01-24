package com.purpleshade.pms.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.purpleshade.pms.R
import com.purpleshade.pms.databinding.HomeFragmentBinding
import com.purpleshade.pms.fragment.BaseFragment
import com.purpleshade.pms.fragment.home.adapter.PasswordsAdapter
import com.purpleshade.pms.model.RecordList
import com.purpleshade.pms.repository.HomeRepository
import com.purpleshade.pms.utils.customInterface.AuthListener
import kotlinx.android.synthetic.main.activity_base.*
import kotlinx.android.synthetic.main.home_fragment.*

class HomeFragment : BaseFragment(),AuthListener {

    lateinit var fabButton: FloatingActionButton

    private lateinit var viewModel: HomeViewModel
    lateinit var binding : HomeFragmentBinding
    lateinit var adapter: PasswordsAdapter
    var passwordList: ArrayList<RecordList> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.home_fragment,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repository = HomeRepository()
        val factory = HomeViewModelFactory(baseActivity,baseActivity,repository)
        viewModel = ViewModelProvider(this,factory).get(HomeViewModel::class.java)
        viewModel.authListener = this
        binding.viewModel = viewModel

        baseActivity.mToolbar.visibility = View.VISIBLE
        baseActivity.mFragmentTitle.text = getString(R.string.allPassword)
        baseActivity.mBackButton.visibility = View.GONE
        fabButton = view.findViewById(R.id.mFabButton)

        viewModel.loadAdapterList(mRecyclerView)

        fabButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_createRecordFragment)
        }
    }

    override fun onSuccess(responseSuccess: LiveData<String>) {
        responseSuccess.observe(this, Observer {

        })
    }

}