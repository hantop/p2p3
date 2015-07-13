package com.fenlibao.p2p.common.persistence;

/**
 * Created by Administrator on 2015/5/23.
 */



import com.fenlibao.p2p.common.page.Page;

import java.util.List;

/**
 * Created by Administrator on 2015/5/23.
 */
public interface PersistenceTemplate<K,T> {

    //    @ResultMap(value="abc")
//    @Select("select * from t_popularize_rule")

    /**
     * 分页查询数据
     * @param page
     * @return
     */
    List<T> findPage(Page<T> page);

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
     * 根据主键查询数据
     * @param id
     * @return
     */
    T selectByPrimaryKey(K id);

    /**T
     * 获取所有的数据
     * @return
     */
    List<T> findAll();

    /**
     * 跟新或保存
     *
     * @param record
     * @return
     */
    int saveOrUpdate(T record);
}

