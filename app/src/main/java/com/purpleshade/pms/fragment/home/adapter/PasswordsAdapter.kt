package com.purpleshade.pms.fragment.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.inflate
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.purpleshade.pms.R
import com.purpleshade.pms.databinding.PasswordViewholderBinding
import com.purpleshade.pms.model.RecordList
import com.purpleshade.pms.utils.customObject.RoomRecordDetail


/**
 * Created by pawan on 02,November,2020
 */
class PasswordsAdapter(val view: View, val context: Context, val passwordList: ArrayList<RecordList>) : RecyclerView.Adapter<PasswordsAdapter.ViewHolder>() {

    var onEventListener: OnEventListener? = null
    var title: String? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: PasswordViewholderBinding = inflate(LayoutInflater.from(parent.context), R.layout.password_viewholder, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return passwordList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(passwordList, position)
    }

    inner class ViewHolder(val binding: PasswordViewholderBinding) : RecyclerView.ViewHolder(binding.root) {


        fun onBind(list: ArrayList<RecordList>, pos: Int) {
            binding.viewModelAdapter = list[pos]
            binding.executePendingBindings()

            binding.mEdit.setOnClickListener {
                RoomRecordDetail.recordId = binding.viewModelAdapter!!._id
                view.findNavController().navigate(R.id.action_homeFragment_to_updateRecordFragment)
            }

            binding.mPasswordView.setOnClickListener {
                RoomRecordDetail.recordId = binding.viewModelAdapter!!._id
                onEventListener!!.viewRecordDetails()
            }

            binding.mDelete.setOnClickListener {
                onEventListener!!.deleteRecord(binding.viewModelAdapter!!._id,pos)
               /* passwordList.removeAt(pos)
                notifyItemRemoved(pos)*/
            }
        }
    }

    interface OnEventListener {
        fun viewRecordDetails()
        fun deleteRecord(id: String,pos:Int)
    }

}