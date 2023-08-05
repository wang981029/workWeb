package com.wangfuzeng.employee.service.impl;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.SecureUtil;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.wangfuzeng.common.model.Result;
import com.wangfuzeng.common.util.LocalDateUtil;
import com.wangfuzeng.common.util.ResponseJsonUtil;
import com.wangfuzeng.employee.po.EmployeePO;
import com.wangfuzeng.employee.dao.EmployeeDao;
import com.wangfuzeng.common.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.wangfuzeng.employee.service.EmployeeService;
import com.wangfuzeng.employee.dto.EmployeeDTO;

import java.time.LocalDate;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 *  业务实现类
 *
 * @author 王福增
 * @since 2023-08-04
 */
@Log4j2
@Service
public class EmployeeServiceImpl extends BaseServiceImpl<EmployeeDao, EmployeePO> implements EmployeeService{
    @Resource
    private HttpServletResponse httpServletResponse;
    @Value("${default.password}")
    private String defaultPassword;
    @Override
    public Result<Void> add(EmployeePO employeePO) {
        QueryWrapper<EmployeePO> wrapper = new QueryWrapper<>();
        wrapper.eq("emp_name",employeePO.getEmpName());
        Long count = this.baseMapper.selectCount(wrapper);
        if (count != 0) {
            return Result.fail(402, "用户名重复");
        }
        String salt = RandomUtil.randomString(5);
        employeePO.setSalt(salt);
        String pwd=SecureUtil.md5( defaultPassword+ salt);
        employeePO.setDeleted(0);
        employeePO.setPassword(pwd);
        this.baseMapper.insert(employeePO);
        return Result.ok();
    }


    @Override
    public Result<List<EmployeePO>> getList(EmployeeDTO employeeDTO) {
        //1.封装请求参数
        QueryWrapper<EmployeePO> wrapper = new QueryWrapper<>();
        //wrapper.eq(StringUtils.hasText(userDTO.getName()),"name",userDTO.getName());
        return Result.ok(this.baseMapper.selectList(wrapper));
    }

    @Override
    public Result<Page<EmployeePO>> getPage(EmployeeDTO employeeDTO) {
        //1.封装分页数据
        Page<EmployeePO> page = new Page<>(employeeDTO.getCurrent(),employeeDTO.getSize());

        //2.封装请求参数
        QueryWrapper<EmployeePO> wrapper = new QueryWrapper<>();
        wrapper.like(StringUtils.hasText(employeeDTO.getEmpName()),"emp_name",employeeDTO.getEmpName());
        wrapper.like(StringUtils.hasText(employeeDTO.getDeptName()),"dept_name",employeeDTO.getDeptName());
        wrapper.like(StringUtils.hasText(employeeDTO.getEmpDegreeName()),"emp_degree_name",employeeDTO.getEmpDegreeName());
        return Result.ok(this.baseMapper.selectPage( page,wrapper));
    }

    @Override
    public Result<String> login(String empName, String password) {

        //使用用户名查询用户
        List<EmployeePO> empList = this.query().eq("emp_name", empName).list();
        if (CollectionUtils.isEmpty(empList)){
            return Result.fail(500,"暂无用户");
        }
        if (empList.size()>1){
            return Result.fail(500,"用户信息错误");
        }
        //用户信息
        EmployeePO employeePO = empList.get(0);
        //输入的密码+加盐后再加密
        String pwd = SecureUtil.md5(password + employeePO.getSalt());
        log.info("密码：" + pwd);

        //判断密码
        if (!pwd.equals(employeePO.getPassword())) {
            return Result.fail(500, "密码错误");
        }

        return Result.ok("登录成功");
    }

    @Override
    public Result<Void> export(EmployeeDTO employeeDTO) {
        //1.封装分页数据
        Page<EmployeePO> page = new Page<>(employeeDTO.getCurrent(),employeeDTO.getSize());

        //2.封装请求参数
        QueryWrapper<EmployeePO> wrapper = new QueryWrapper<>();
        wrapper.like(StringUtils.hasText(employeeDTO.getEmpName()),"emp_name",employeeDTO.getEmpName());
        wrapper.like(StringUtils.hasText(employeeDTO.getDeptName()),"dept_name",employeeDTO.getDeptName());
        wrapper.like(StringUtils.hasText(employeeDTO.getEmpDegreeName()),"emp_degree_name",employeeDTO.getEmpDegreeName());
        List<EmployeePO> records = this.baseMapper.selectPage(page, wrapper).getRecords();

        //4.当前日期字符串
        String now = LocalDateUtil.parseLocalDate(LocalDate.now());

        try {
            // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
            httpServletResponse.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            httpServletResponse.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            //String fileName = URLEncoder.encode("考试记录", "UTF-8").replaceAll("\\+", "%20");
            httpServletResponse.setHeader("Content-disposition", now + ".xlsx");
            EasyExcel.write(httpServletResponse.getOutputStream(), EmployeePO.class).sheet(now).doWrite(records);
        } catch (Exception e) {
            ResponseJsonUtil.responseJson(httpServletResponse, 201, "下载失败！请重试！");
            e.printStackTrace();
        }

        return Result.ok();
    }


}