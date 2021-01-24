package com.purpleshade.pms.fragment.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil.inflate
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.purpleshade.pms.R
import com.purpleshade.pms.databinding.PasswordViewholderBinding
import com.purpleshade.pms.model.RecordList
import com.purpleshade.pms.utils.customObject.RecordDetail


/**
 * Created by pawan on 02,November,2020
 */
class PasswordsAdapter(val view: View, val context: Context, val passwordList: ArrayList<RecordList>) : RecyclerView.Adapter<PasswordsAdapter.ViewHolder>() {

    var recordId = ""
    var onEventListener: OnEventListener? = null
    var title: String? = null
    lateinit var viewModel: PasswordViewModel

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: PasswordViewholderBinding = inflate(LayoutInflater.from(parent.context), R.layout.password_viewholder, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return passwordList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(passwordList,position)
    }

    inner class ViewHolder(val binding: PasswordViewholderBinding) : RecyclerView.ViewHolder(binding.root) {

        val edit = itemView.findViewById<ImageView>(R.id.mEdit);
        val title = itemView.findViewById<TextView>(R.id.mTitle);

        fun onBind(list: ArrayList<RecordList>, pos:Int) {
            binding.viewModelAdapter = list[pos]
            binding.executePendingBindings()

            binding.mEdit.setOnClickListener {
                RecordDetail.recordId = binding.viewModelAdapter!!._id
                view.findNavController().navigate(R.id.action_homeFragment_to_updateRecordFragment)
            }

            binding.mPasswordView.setOnClickListener {
                RecordDetail.recordId = binding.viewModelAdapter!!._id
                onEventListener!!.viewRecordDetails()
            }

           /* binding.mDelete.setOnClickListener {
              //  loadRecordList(data._id)
                passwordList.removeAt(pos)
                notifyItemRemoved(pos)
                Log.d("----pos",list[pos].toString())
            }
*/
/*
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
*/
        }

        /*private fun loadRecordList(id: String) {
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
        }*/
    }

    interface OnEventListener {
        fun viewRecordDetails()
    }

}