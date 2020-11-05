//
//  ViewController.swift
//  iosApp
//
//  Created by mac-0009 on 04/11/20.
//  Copyright © 2020 orgName. All rights reserved.
//

import UIKit
import shared

class ViewController: UIViewController {

    // IBOutlets
    
    @IBOutlet weak private var tblvEmployee: UITableView! {
        didSet {
            tblvEmployee.contentInset = UIEdgeInsets(top: 15, left: 0, bottom: 0, right: 0)
        }
    }
    @IBOutlet weak private var activityIndicator: UIActivityIndicatorView!
    @IBOutlet weak private var lblNoDataFound: UILabel!
    
    // Variables
    
    private var arrEmployee = [EmployeeDataItem]()
    private let refreshContol = UIRefreshControl()
    
    // ViewController Life Cycle
    
    override func viewDidLoad() {
        super.viewDidLoad()
        initialize()
    }
    
    deinit {
        
    }
}


// MARK: -
// MARK: - General Methods

extension ViewController {
    
    private func initialize() {
        
        self.title = "KMM"
        
        setupTableView()
        setupRefreshControl()
        
        loadEmployees()
    }
    
    private func setupTableView() {
        tblvEmployee.register(UINib(nibName: "EmployeeTblVCell", bundle: nil), forCellReuseIdentifier: "EmployeeTblVCell")
    }
    
    private func setupRefreshControl() {
        
        refreshContol.tintColor = color6200EA
        refreshContol.addTarget(self, action: #selector(pullToRefresh), for: .valueChanged)
        tblvEmployee.refreshControl = refreshContol
    }
}


// MARK: -
// MARK: - TableView Delegate & DataSource

extension ViewController: UITableViewDelegate, UITableViewDataSource {

    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        arrEmployee.count
    }

    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        
        if let cell = tableView.dequeueReusableCell(withIdentifier: "EmployeeTblVCell") as? EmployeeTblVCell {
            
            let employee = arrEmployee[indexPath.row]
            cell.configureCellData(with: employee)
            return cell
        }
        
        return UITableViewCell()
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        print(arrEmployee[indexPath.row])
    }
}


// MARK: -
// MARK: - API Activity

extension ViewController {
    
    @objc
    private func pullToRefresh() {
        self.loadEmployees(isLoaderRequire: false, forceReload: true)
    }

    private func loadEmployees(isLoaderRequire: Bool = true, forceReload: Bool = false) {
        
        let sdk = EmployeeSDK(databaseDriverFactory: DatabaseDriverFactory())
        
        if isLoaderRequire {
            activityIndicator.startAnimating()
        }
        
        sdk.getLaunches(forceReload: forceReload) { (employees, error) in       
            
            self.activityIndicator.stopAnimating()
            self.refreshContol.endRefreshing()
            
            if let employees = employees {
                
                self.tblvEmployee.isHidden = false
                
                self.arrEmployee.removeAll()
                self.arrEmployee = employees
                self.tblvEmployee.reloadData()
                
            } else {
                
                self.lblNoDataFound.isHidden = false
                print(error?.localizedDescription ?? "error")
            }
        }
    }

}
