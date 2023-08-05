package com.wangfuzeng.common.base;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangfuzeng.common.model.Result;


import java.util.List;

/**
 * @author 王福增
 * @datetime 2022/4/15 17:15
 * @description 接口对象
 */
public class BaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> implements BaseService<T> {
    @Override
    public Result<Void> add(T t) {
        this.baseMapper.insert(t);
        return Result.ok();
    }

    @Override
    public Result<Void> delById(Long id) {
        this.baseMapper.deleteById(id);
        return Result.ok();
    }

    @Override
    public Result<Void> delByIdList(List<Long> idList) {
        this.baseMapper.deleteBatchIds(idList);
        return Result.ok();
    }

    @Override
    public Result<Void> update(T t) {
        this.baseMapper.updateById(t);
        return Result.ok();
    }

    @Override
    public Result<T> getById(Long id) {
        return Result.ok(this.baseMapper.selectById(id));
    }

    @Override
    public Result<Void> enable(Long id) {
        this.update().set("status","1").eq("id",id).update();
        return Result.ok();
    }

    @Override
    public Result<Void> unable(Long id) {
        this.update().set("status","0").eq("id",id).update();
        return Result.ok();
    }
}
