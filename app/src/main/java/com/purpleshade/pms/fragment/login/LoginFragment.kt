package com.purpleshade.pms.fragment.login

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.purpleshade.pms.R
import com.purpleshade.pms.activity.BaseActivity
import com.purpleshade.pms.fragment.BaseFragment
import com.purpleshade.pms.network.signupModel.SignUpModel
import com.purpleshade.pms.network.standardObjects.RetrofitClient
import com.purpleshade.pms.utils.Records
import kotlinx.android.synthetic.main.sign_up_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginFragment : BaseFragment() {

    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        baseActivity.toolbar.visibility = View.GONE

       // signUp()

        val login = view.findViewById<Button>(R.id.mLogin)
        val register = view.findViewById<TextView>(R.id.mRegister)

        login.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
        }
        register.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }
        //baseActivity.mToolbar.visibility = View.VISIBLE
    }


    //Removable call

    private fun signUp() {
        val api = RetrofitClient.rosService
        val call = api.allRecords()

        call.enqueue(object : Callback<Records> {
            override fun onFailure(call: Call<Records>?, t: Throwable?) {
                Toast.makeText(baseActivity, "Something went wrong", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<Records>, response: Response<Records>) {
                Toast.makeText(baseActivity, "Successfully", Toast.LENGTH_SHORT).show()
                if (response.isSuccessful){
                    val data = response.body()!!.recordDetail
                    for(i in data.indices){
                        val title = data[i].title
                        Log.d("Data===>>",title)
                    }
                }

                // floors = response.body().floors!!
                // addFloorButtons()
            }
        })
    }
}
