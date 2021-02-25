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
import com.purpleshade.pms.databinding.PasswordViewholderBinding
import com.purpleshade.pms.db.RoomRecord
import com.purpleshade.pms.model.RecordList
import com.purpleshade.pms.utils.customObject.RoomRecordDetail
import com.purpleshade.pms.utils.customObject.ViewVisibility
import com.purpleshade.pms.utils.toast


/**
 * Created by pawan on 02,November,2020
 */
class PasswordsAdapter(val view: View, val context: Context, var passwordList: ArrayList<RecordList>, var roomPasswordList: ArrayList<RoomRecord>) : RecyclerView.Adapter<PasswordsAdapter.ViewHolder>() {

    var onEventListener: OnEventListener? = null
    var title: String? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: PasswordViewholderBinding = inflate(LayoutInflater.from(parent.context), R.layout.password_viewholder, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return if (ViewVisibility.networkProblem)
            roomPasswordList.size
        else
            passwordList.size

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (ViewVisibility.networkProblem)
            holder.onBindRoom(roomPasswordList, position)
        else if (!ViewVisibility.networkProblem)
            holder.onBind(passwordList, position)
    }

    inner class ViewHolder(val binding: PasswordViewholderBinding) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(list: ArrayList<RecordList>, pos: Int) {
            binding.viewModelAdapter = list[pos]
            binding.executePendingBindings()

            binding.mTitle.text = binding.viewModelAdapter!!.title

            binding.mEdit.setOnClickListener {
                RoomRecordDetail.recordId = binding.viewModelAdapter!!._id
                view.findNavController().navigate(R.id.action_homeFragment_to_updateRecordFragment)
            }

            binding.mPasswordView.setOnClickListener {
                RoomRecordDetail.recordId = binding.viewModelAdapter!!._id
                onEventListener!!.viewRecordDetails()
            }

            binding.mDelete.setOnClickListener {
                onEventListener!!.deleteRecord(binding.viewModelAdapter!!._id, pos)
            }
        }

        fun onBindRoom(passwordListRoomDb: ArrayList<RoomRecord>, pos: Int) {
            binding.viewModelAdapterRoom = passwordListRoomDb[pos]
            binding.executePendingBindings()

            binding.mTitle.text = binding.viewModelAdapterRoom!!.title

            binding.mPasswordView.setOnClickListener {
                RoomRecordDetail.recordId = roomPasswordList[pos].recordId!!
                onEventListener!!.viewRecordDetailsUsingRoom()
            }


            binding.mEdit.setOnClickListener {
                if (ViewVisibility.networkProblem) {
                    context.toast("No internet connection")
                } else {
                    RoomRecordDetail.recordId = roomPasswordList[pos].recordId!!
                    view.findNavController().navigate(R.id.action_homeFragment_to_updateRecordFragment)
                }
            }

            binding.mDelete.setOnClickListener {
                if (ViewVisibility.networkProblem) {
                    context.toast("No internet connection")
                } else {
                    onEventListener!!.deleteRecord(roomPasswordList[pos].recordId!!, pos)
                }
            }

        }
    }

    interface OnEventListener {
        fun viewRecordDetails()
        fun viewRecordDetailsUsingRoom()
        fun deleteRecord(id: String, pos: Int)

    }

}