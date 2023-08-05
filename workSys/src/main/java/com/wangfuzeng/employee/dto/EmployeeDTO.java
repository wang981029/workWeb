package com.wangfuzeng.employee.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Getter;
import lombok.Setter;

/**
 *  数据传输对象
 *
 * @author 王福增
 * @since 2023-08-04
 */
@Getter
@Setter
public class EmployeeDTO extends Page<EmployeeDTO> {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String empName;

    private String sex;

    private Integer age;

    private String deptName;

    private String empDegreeName;

    private String possword;

    private String salt;


}
