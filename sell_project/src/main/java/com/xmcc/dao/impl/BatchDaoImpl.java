package com.xmcc.dao.impl;

import com.xmcc.dao.BatchDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * 批量操作实现类
 * @param <T>
 */
public class BatchDaoImpl<T> implements BatchDao<T> {

    @PersistenceContext
    protected EntityManager em;

    /**
     * 批量插入
     * @param list
     */
    @Transactional
    public void batchInsert(List<T> list) {
        long length = list.size();
        for (int i = 0; i <length; i++) {
            em.persist(list.get(i)); //循环存入缓存区
            if (i % 100 == 0||i==length-1) { //每100条，或不足100遍历完后，执行一次写入数据库操作
                em.flush();
                em.clear();
            }
        }

    }
}
