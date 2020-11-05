//
//  EmployeeTblVCell.swift
//  iosApp
//
//  Created by mac-0009 on 04/11/20.
//  Copyright © 2020 orgName. All rights reserved.
//

import UIKit
import shared

class EmployeeTblVCell: UITableViewCell {

    @IBOutlet weak private var lblEmplyeeName: UILabel!
    @IBOutlet weak private var lblEmployeeAge: UILabel!
    @IBOutlet weak private var lblEmployeeSalary: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
    }
    
}


// MARK: -
// MARK: - Configure Cell

extension EmployeeTblVCell {
    
    func configureCellData(with employee: EmployeeDataItem) {
        
        lblEmplyeeName.text = "Name: " + employee.employeeName
        lblEmployeeAge.text = "Age: " + employee.employeeAge
        lblEmployeeSalary.text = "Salary: $ \(employee.employeeSalary)"
    }
}
