package com.purpleshade.pms.fragment.home.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.inflate
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.purpleshade.pms.R
import com.purpleshade.pms.databinding.RoomPasswordViewholderBinding
import com.purpleshade.pms.db.RoomRecord
import com.purpleshade.pms.utils.customObject.RoomRecordDetail


/**
 * Created by pawan on 02,November,2020
 */
class RoomPasswordsAdapter(val view: View, val context: Context, val passwordList: ArrayList<RoomRecord>) : RecyclerView.Adapter<RoomPasswordsAdapter.ViewHolder>() {

    var onEventListener: RoomOnEventListener? = null
    var title: String? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: RoomPasswordViewholderBinding = inflate(LayoutInflater.from(parent.context), R.layout.room_password_viewholder, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return passwordList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(passwordList, position)
    }

    inner class ViewHolder(val binding: RoomPasswordViewholderBinding) : RecyclerView.ViewHolder(binding.root) {


        fun onBind(list: ArrayList<RoomRecord>, pos: Int) {
            binding.roomViewModelAdapter = list[pos]
            binding.executePendingBindings()

            binding.mEdit.setOnClickListener {
                RoomRecordDetail.recordId = passwordList[pos].recordId!!
                Log.d("---->>RecordId",RoomRecordDetail.recordId)
                view.findNavController().navigate(R.id.action_homeFragment_to_updateRecordFragment)
            }

            /*binding.mPasswordView.setOnClickListener {
                RoomRecordDetail.recordId = binding.viewModelAdapter!!._id
                onEventListener!!.viewRecordDetails()
            }

            binding.mDelete.setOnClickListener {
                onEventListener!!.deleteRecord(binding.viewModelAdapter!!._id)
                passwordList.removeAt(pos)
                notifyItemRemoved(pos)
            }*/
        }
    }

    interface RoomOnEventListener {
        fun viewRecordDetails()
        fun deleteRecord(id: String)
    }

}