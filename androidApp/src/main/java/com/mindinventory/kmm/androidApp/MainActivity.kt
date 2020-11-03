package com.mindinventory.kmm.androidApp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import com.mindinventory.kmm.androidApp.databinding.ActivityMainBinding
import com.mindinventory.kmm.shared.EmployeeSDK
import com.mindinventory.kmm.shared.cache.DatabaseDriverFactory
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private val mainScope = MainScope()
    private val sdk = EmployeeSDK(DatabaseDriverFactory(this))
    private val adapter = EmployeeAdapter(listOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mBinding.swlRefresh.setOnRefreshListener { displayLaunches(true) }
        mBinding.rvList.adapter = adapter
        displayLaunches(false)
    }

    override fun onDestroy() {
        super.onDestroy()
        mainScope.cancel()
    }

    private fun displayLaunches(needReload: Boolean) {
        if (!needReload)
            mBinding.progressBar.isVisible = true
        mainScope.launch {
            kotlin.runCatching {
                sdk.getLaunches(needReload)
            }.onSuccess {
                adapter.launches = it
                adapter.notifyDataSetChanged()
            }.onFailure {
                Toast.makeText(this@MainActivity, it.localizedMessage, Toast.LENGTH_SHORT).show()
            }
            mBinding.progressBar.isVisible = false
            mBinding.swlRefresh.isRefreshing = false
        }
    }
}
