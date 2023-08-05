package com.wangfuzeng.employee.po;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 持久层对象，对应数据库
 *
 * @author 王福增
 * @since 2023-08-04
 */
@Getter
@Setter
@TableName("employee")
@ApiModel("员工")
public class EmployeePO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "id", required = true)
    @ExcelProperty("编号")
    private Long id;
    @ApiModelProperty(value = "员工姓名", required = true)
    @ExcelProperty("员工姓名")
    private String empName;
    @ApiModelProperty(value = "员工性别", required = true)
    @ExcelProperty("员工性别")
    private String sex;
    @ApiModelProperty(value = "年龄", required = true)
    @ExcelProperty("年龄")
    private Integer age;
    @ApiModelProperty(value = "部门名称", required = true)
    @ExcelProperty("部门名称")
    private String deptName;
    @ApiModelProperty(value = "学历", required = true)
    @ExcelProperty("学历")
    private String empDegreeName;
    @ApiModelProperty(value = "密码", required = true)
    @ExcelIgnore
    private String password;
    @ApiModelProperty(value = "盐", required = true)
    @ExcelIgnore
    private String salt;

    @TableLogic
    @ApiModelProperty(hidden = true)
    @ExcelIgnore
    private Integer deleted;

}
