package com.wangfuzeng.common.base;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wangfuzeng.common.model.Result;


import java.util.List;

/**
 * @author 王福增
 * @datetime 2022/4/15 17:15
 * @description 接口对象
 */
public interface BaseService<T> extends IService<T> {
    //新增
    Result<Void> add(T t);

    //根据ID删除
    Result<Void> delById(Long id);

    //根据ID批量删除
    Result<Void> delByIdList(List<Long> idList);

    //修改
    Result<Void> update(T t);

    //根据ID查询
    Result<T> getById(Long id);

    //启用
    Result<Void> enable(Long id);

    //停用
    Result<Void> unable(Long id);
}
