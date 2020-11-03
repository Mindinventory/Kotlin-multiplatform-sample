package com.mindinventory.kmm.shared.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Employee(

	@SerialName("data")
	val data: List<EmployeeDataItem>,

	@SerialName("status")
	val status: String? = null
)

@Serializable
data class EmployeeDataItem(

	@SerialName("profile_image")
	val profileImage: String,

	@SerialName("employee_name")
	val employeeName: String,

	@SerialName("employee_salary")
	val employeeSalary: Int,

	@SerialName("id")
	val id: Int,

	@SerialName("employee_age")
	val employeeAge: String
){
	fun getSalaryInString() = "$ $employeeSalary"
}
