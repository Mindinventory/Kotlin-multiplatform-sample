package com.mindinventory.kmm.shared.cache

import com.mindinventory.kmm.shared.entity.EmployeeDataItem

internal class Database(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = AppDatabase(databaseDriverFactory.createDriver())
    private val dbQuery = database.appDatabaseQueries

    internal fun clearDatabase() {
        dbQuery.transaction {
            dbQuery.deleteAllEmployee()
        }
    }

    internal fun getAllEmployee(): List<EmployeeDataItem> {
        return dbQuery.selectAllEmployee(::mapEmployeeSelecting).executeAsList()
    }

    private fun mapEmployeeSelecting(
        id: Long,
        employee_name: String,
        employee_salary: Int,
        employee_age: String,
        profile_image: String
    ): EmployeeDataItem {
        return EmployeeDataItem(
            profileImage = profile_image,
            employeeName = employee_name,
            employeeSalary = employee_salary,
            id = id.toInt(),
            employeeAge = employee_age
        )
    }

    internal fun createEmployee(employee: List<EmployeeDataItem>) {
        dbQuery.transaction {
            repeat(employee.size) { insertEmployee(employee[it]) }
        }
    }

    private fun insertEmployee(emp: EmployeeDataItem) {
        dbQuery.insertLaunch(
            emp.id.toLong(),
            emp.employeeName,
            emp.employeeSalary,
            emp.employeeAge,
            emp.profileImage
        )
    }
}