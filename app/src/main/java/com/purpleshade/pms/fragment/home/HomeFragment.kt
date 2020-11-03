package com.purpleshade.pms.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.purpleshade.pms.R
import com.purpleshade.pms.fragment.BaseFragment
import com.purpleshade.pms.fragment.home.adapter.PasswordsAdapter

class HomeFragment : BaseFragment() {

    private lateinit var viewModel: HomeViewModel
    lateinit var adapter: PasswordsAdapter
    lateinit var recyclerView: RecyclerView
    lateinit var fabButton: FloatingActionButton
    private var passwordList: ArrayList<String> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        baseActivity.toolbar.visibility = View.VISIBLE
        baseActivity.fragmentTitle.text = getString(R.string.allPassword)
        baseActivity.backButton.visibility = View.GONE

        recyclerView = view.findViewById(R.id.mRecyclerView)
        fabButton = view.findViewById(R.id.mFabButton)

        loadAdapter()
        fabButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_createRecordFragment)
        }
    }

    private fun loadAdapter() {
        loadList()
        adapter = PasswordsAdapter(passwordList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(baseActivity)
    }

    private fun loadList() {
        passwordList.add("Google")
        passwordList.add("Facebook")
        passwordList.add("WhatsApp")
        passwordList.add("Gmail")
        passwordList.add("Instagram")
        passwordList.add("Linkdin")

    }
}