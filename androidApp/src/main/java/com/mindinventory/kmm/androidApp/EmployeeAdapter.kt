package com.mindinventory.kmm.androidApp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mindinventory.kmm.androidApp.databinding.RowEmployeeBinding
import com.mindinventory.kmm.shared.entity.EmployeeDataItem

class EmployeeAdapter(var launches: List<EmployeeDataItem>) :
    RecyclerView.Adapter<EmployeeAdapter.LaunchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaunchViewHolder {
        return LayoutInflater.from(parent.context)
            .inflate(R.layout.row_employee, parent, false)
            .run(::LaunchViewHolder)
    }

    override fun getItemCount(): Int = launches.count()

    override fun onBindViewHolder(holder: LaunchViewHolder, position: Int) {
        holder.bindData(launches[position])
    }

    inner class LaunchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val mBinding = DataBindingUtil.bind<RowEmployeeBinding>(itemView)

        fun bindData(employee: EmployeeDataItem) {
            mBinding?.let {
                it.item = employee
                it.executePendingBindings()
            }
        }
    }
}