package com.fenlibao.p2p.common.service;


import com.fenlibao.p2p.common.page.Page;

import java.util.List;

/**
 * Created by Administrator on 2015/5/23.
 */
public interface ServiceTemplate<K,T> {


    /**
     * 分页查询数据
     * @param page
     * @return
     */
    Page<T> findPage(Page<T> page);

    int insert(T record);

    /**
     * 根据主键修改数据
     * 注意：没有的值会被赋值为null或0
     * @param record
     * @return
     */
    int updateByPrimaryKey(T record);

    /**
     * 根据主键修改数据，
     * 注意：只修改以后的值，没有传入的值不会变化
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(T record);

    /**
     * 根据主键删除数据
     * @param id
     * @return
     */
    int deleteByPrimaryKey(K id);

    /**
     * 插入数据，如没有值得属性不会插入数据
     * @param record
     * @return
     */
    int insertSelective(T record);

    /**
     * 更新或保存
     * 根据id进行判断
     * @param record
     * @return
     */
    int saveOrUpdate(T record);

    /**
     * 根据主键查询数据
     * @param id
     * @return
     */
    T selectByPrimaryKey(K id);

    /**
     * 获取所有数据
     * @return
     */
    List<T> findAll();
}
