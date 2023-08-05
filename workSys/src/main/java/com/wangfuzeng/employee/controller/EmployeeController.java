package com.wangfuzeng.employee.controller;

import com.wangfuzeng.common.model.IdModel;
import com.wangfuzeng.common.model.IdsModel;
import com.wangfuzeng.common.model.Result;
import com.wangfuzeng.employee.po.EmployeePO;
import com.wangfuzeng.employee.dto.EmployeeDTO;
import com.wangfuzeng.employee.service.EmployeeService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import javax.annotation.Resource;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 控制层
 *
 * @author 王福增
 * @since 2023-08-04
 */
@Log4j2
@RestController
@Api(tags = "员工管理")
@RequestMapping("employee")
public class EmployeeController {
    @Resource
    private EmployeeService employeeService;

    /**
    * 添加数据
    *
    * @param po
    * @return
    */
    @PostMapping("add")
    @ApiOperation("添加数据")
    public Result<Void> add(@RequestBody EmployeePO po){
        return employeeService.add(po);
    }

    /**
    * 根据ID删除数据
    *
    * @param idModel
    * @return
    */
    @PostMapping("delById")
    @ApiOperation("根据ID删除数据")
    public Result<Void> delById(@RequestBody IdModel idModel){
        return employeeService.delById(idModel.getId());
    }

    /**
    * 根据ID批量删除数据
    *
    * @param idsModel
    * @return
    */
    @PostMapping("delByIdList")
    @ApiOperation("根据ID批量删除数据")
    public Result<Void> delByIdList(@RequestBody IdsModel idsModel){
        return employeeService.delByIdList(idsModel.getIds());
    }

    /**
    * 修改数据（空字段不更新）
    *
    * @param po
    * @return
    */
    @PostMapping("update")
    @ApiOperation("修改数据")
    public Result<Void> update(@RequestBody EmployeePO po){
        return employeeService.update(po);
    }

    /**
    * 根据ID查询数据
    *
    * @param idModel
    * @return
    */
    @PostMapping("getById")
    @ApiOperation("根据ID查询数据")
    public Result<EmployeePO> getById(@RequestBody IdModel idModel){
        return employeeService.getById(idModel.getId());
    }

    /**
     * 分页查询
     *
     * @param employeeDTO
     * @return
     */
    @PostMapping("getPage")
    @ApiOperation("分页查询")
    public Result<Page<EmployeePO>> getPage(@RequestBody EmployeeDTO employeeDTO) {
        return employeeService.getPage(employeeDTO);
    }

    /**
     * 查询列表
     *
     * @param employeeDTO
     * @return
     */
    @PostMapping("getList")
    @ApiOperation("查询列表")
    public Result<List<EmployeePO>> getList(@RequestBody EmployeeDTO employeeDTO) {
        return employeeService.getList(employeeDTO);
    }
    /**
     * 登录
     *
     * @param employeePO
     * @return
     */
    @PostMapping("login")
    @ApiOperation("登录")
    public Result<String> login(@RequestBody EmployeePO employeePO) {
        return employeeService.login(employeePO.getEmpName(), employeePO.getPassword());
    }


    /**
     * 导出
     *
     */
    @PostMapping("export")
    @ApiOperation("导出当前列表")
    public Result<Void> export(@RequestBody EmployeeDTO employeeDTO){
        return employeeService.export(employeeDTO);
    }



}