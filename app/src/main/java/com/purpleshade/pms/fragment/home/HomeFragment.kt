package com.purpleshade.pms.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.purpleshade.pms.R
import com.purpleshade.pms.databinding.HomeFragmentBinding
import com.purpleshade.pms.fragment.BaseFragment
import com.purpleshade.pms.fragment.home.adapter.PasswordsAdapter
import com.purpleshade.pms.network.RetrofitClient
import com.purpleshade.pms.utils.JWTUtils
import com.purpleshade.pms.model.RecordList
import com.purpleshade.pms.model.Records
import com.purpleshade.pms.repository.HomeRepository
import com.purpleshade.pms.utils.customInterface.AuthListener
import com.purpleshade.pms.utils.customObject.RecordDetail
import kotlinx.android.synthetic.main.activity_base.*
import kotlinx.android.synthetic.main.home_fragment.*
import kotlinx.android.synthetic.main.password_detail_bottomsheet.*
import kotlinx.android.synthetic.main.password_detail_bottomsheet.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : BaseFragment(),AuthListener {

    lateinit var fabButton: FloatingActionButton
    private var passwordList: ArrayList<RecordList> = ArrayList()

    var recordId = ""
    var title = ""
    var webAddress = ""
    var email = ""
    var password = ""
    var addNote = ""

    lateinit var bottomSheetDialog : BottomSheetDialog

    private lateinit var viewModel: HomeViewModel
    lateinit var binding : HomeFragmentBinding
    lateinit var adapter: PasswordsAdapter


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


    /*private fun loadRecordList() {
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
                        //   var title = i.title
                        //  Log.d("---->>>",title)
                        passwordList.add(i)
                        adapter.notifyDataSetChanged()
                    }
                }
            }

        })
    }
*/
    /*private fun getRecordDetails(id: String) {
        val api = RetrofitClient.apiService
        val call = api.recordDetail(id)

        call.enqueue(object : Callback<Records> {
            override fun onFailure(call: Call<Records>, t: Throwable) {
                Toast.makeText(baseActivity, "Something went wrong", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<Records>, response: Response<Records>) {
                if (response.isSuccessful) {
                    Toast.makeText(baseActivity, "Get Record Successfully", Toast.LENGTH_SHORT).show()
                    val msg = response.body()!!.message
                    val recordDetail = response.body()!!.recordDetail

                    for (i in recordDetail) {
                        title = i.title
                        webAddress = i.websiteAddress.toString()
                        email = i.email
                        password = i.password
                        addNote = i.addNote

                        bottomSheetDialog.mTitle.text = title
                        bottomSheetDialog.mWebAddress.text = webAddress
                        bottomSheetDialog.mEmail.text = email
                        bottomSheetDialog.mPassword.text = password
                        bottomSheetDialog.mAddNote.text = addNote

                    }

                }
            }

        })
    }*/

    private fun bottomSheetVisible() {
         bottomSheetDialog = BottomSheetDialog(baseActivity)
        val bottomSheetView = LayoutInflater.from(baseActivity).inflate(
            R.layout.password_detail_bottomsheet, baseActivity.findViewById<View>(R.id.bottomSheetContainer) as LinearLayout?
        )

        bottomSheetView.mClose.setOnClickListener {
            bottomSheetDialog.dismiss()
        }

        bottomSheetDialog.setContentView(bottomSheetView)
        bottomSheetDialog.show()
    }

    /*override fun viewRecordDetails() {
        getRecordDetails(RecordDetail.recordId)
        bottomSheetVisible()
    }*/



}