package com.purpleshade.pms.fragment.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.purpleshade.pms.R
import com.purpleshade.pms.utils.RecordList

/**
 * Created by pawan on 02,November,2020
 */
class PasswordsAdapter(val passwordList: ArrayList<RecordList>) : RecyclerView.Adapter<PasswordsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.password_viewholder, parent, false))
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

        private val v = view

        fun onBind(pos: Int) {
            val data = passwordList[pos]
            title.text = data.title
            edit.setOnClickListener {
                v.findNavController().navigate(R.id.action_homeFragment_to_createRecordFragment)
            }

        }
    }

}