package com.xmcc.dao;

import java.util.List;

/**
 * 执行批量操作接口
 */
public interface BatchDao<T> {

    /**
     * 批量插入
     * @param list
     */
    public void batchInsert(List<T> list);

}
