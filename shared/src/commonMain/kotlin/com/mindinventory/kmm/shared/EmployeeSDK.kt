package com.mindinventory.kmm.shared

import com.mindinventory.kmm.shared.cache.Database
import com.mindinventory.kmm.shared.cache.DatabaseDriverFactory
import com.mindinventory.kmm.shared.entity.EmployeeDataItem
import com.mindinventory.kmm.shared.network.EmployeeApi

class EmployeeSDK(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = Database(databaseDriverFactory)
    private val api = EmployeeApi()

    @Throws(Exception::class)
    suspend fun getLaunches(forceReload: Boolean): List<EmployeeDataItem> {
        val cachedEmployee = database.getAllEmployee()
        return if (cachedEmployee.isNotEmpty() && !forceReload) {
            cachedEmployee
        } else {
            api.getAllEmployee().data.also {
                database.clearDatabase()
                database.createEmployee(it)
            }
        }
    }
}