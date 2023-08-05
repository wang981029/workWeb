package com.wangfuzeng.employee.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wangfuzeng.common.base.BaseService;
import com.wangfuzeng.common.model.Result;
import com.wangfuzeng.employee.po.EmployeePO;
import com.wangfuzeng.employee.dto.EmployeeDTO;
import com.wangfuzeng.common.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

import com.wangfuzeng.employee.dto.EmployeeDTO;
import java.util.List;

/**
 *  业务接口
 *
 * @author 王福增
 * @since 2023-08-04
 */
public interface EmployeeService extends BaseService<EmployeePO> {
    Result<Void> add(EmployeePO employeePO);


    /**
     * 条件查询所有
     *
     * @param employeeDTO
     * @return
     */
    Result<List<EmployeePO>> getList(EmployeeDTO employeeDTO);

    /**
     * 分页查询所有
     *
     * @param employeeDTO
     * @return
     */
    Result<Page<EmployeePO>> getPage(EmployeeDTO employeeDTO);

    Result<String> login(String empName, String password);

    Result<Void> export(EmployeeDTO employeeDTO);
}