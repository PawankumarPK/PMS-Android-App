package com.purpleshade.pms.fragment.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.purpleshade.pms.R
import com.purpleshade.pms.model.SignUpModel
import com.purpleshade.pms.network.RetrofitClient
import com.purpleshade.pms.model.RecordList
import com.purpleshade.pms.utils.customObject.RecordDetail
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by pawan on 02,November,2020
 */
class PasswordsAdapter(val context : Context,val passwordList: ArrayList<RecordList>) : RecyclerView.Adapter<PasswordsAdapter.ViewHolder>() {

    var recordId = ""
    var onEventListener : OnEventListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.password_viewholder, parent, false))
    }

    override fun getItemCount(): Int {
        return passwordList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(position)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title = itemView.findViewById<TextView>(R.id.mTitle)
        val edit = itemView.findViewById<ImageView>(R.id.mEdit)
        val delete = itemView.findViewById<ImageView>(R.id.mDelete)
        val passwordView  = itemView.findViewById<ImageView>(R.id.mPasswordView)

        private val v = view

        fun onBind(pos: Int) {
            val data = passwordList[pos]
            title.text = data.title
            edit.setOnClickListener {
                RecordDetail.recordId = data._id
                v.findNavController().navigate(R.id.action_homeFragment_to_updateRecordFragment)
            }

            delete.setOnClickListener {
                loadRecordList(data._id)
                passwordList.removeAt(pos)
                notifyItemRemoved(pos)
            }

            passwordView.setOnClickListener {
                RecordDetail.recordId = data._id
                onEventListener!!.viewRecordDetails()
            }
        }

        private fun loadRecordList(id : String) {
            val api = RetrofitClient.apiService
            val call = api.deleteRecord(id)

            call.enqueue(object : Callback<SignUpModel> {
                override fun onFailure(call: Call<SignUpModel>, t: Throwable) {
                    Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<SignUpModel>, response: Response<SignUpModel>) {
                    Toast.makeText(context, "Delete Record Successfully", Toast.LENGTH_SHORT).show()
                }

            })
        }
    }

    interface OnEventListener{
        fun viewRecordDetails()
    }

}