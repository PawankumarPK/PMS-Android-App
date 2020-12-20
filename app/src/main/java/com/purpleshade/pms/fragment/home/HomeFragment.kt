package com.purpleshade.pms.fragment.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.purpleshade.pms.R
import com.purpleshade.pms.fragment.BaseFragment
import com.purpleshade.pms.fragment.home.adapter.PasswordsAdapter
import com.purpleshade.pms.network.standardObjects.RetrofitClient
import com.purpleshade.pms.utils.JWTUtils
import com.purpleshade.pms.utils.RecordList
import com.purpleshade.pms.utils.Records
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.function.LongFunction

class HomeFragment : BaseFragment() {

    private lateinit var viewModel: HomeViewModel
    lateinit var adapter: PasswordsAdapter
    lateinit var recyclerView: RecyclerView
    lateinit var fabButton: FloatingActionButton
    private var passwordList: ArrayList<RecordList> = ArrayList()

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
        adapter = PasswordsAdapter(baseActivity,passwordList)
    //    adapter.notifyDataSetChanged()
        loadRecordList()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(baseActivity)

    }

    private fun loadRecordList() {
        val api = RetrofitClient.apiService
        val call = api.allRecords(JWTUtils.userId)

        call.enqueue(object : Callback<Records> {
            override fun onFailure(call: Call<Records>, t: Throwable) {
                Toast.makeText(baseActivity, "Something went wrong", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<Records>, response: Response<Records>) {
                Toast.makeText(baseActivity, "Record Load Successfully", Toast.LENGTH_SHORT).show()
                if (response.isSuccessful) {
                    val record = response.body()!!.recordDetail
                    for (i in record) {
                        passwordList.add(i)
                        adapter.notifyDataSetChanged()
                    }
                }
            }

        })
    }
}