/**
 * Copyright : http://www.orientpay.com , 2007-2012
 * Project : oecs-g2-common-framework-trunk
 * $Id$
 * $Revision$
 * Last Changed by ZhouXushun at 2011-9-6 下午04:26:39
 * $URL$
 * 
 * Change Log
 * Author      Change Date    Comments
 *-------------------------------------------------------------
 * ZhouXushun     2011-9-6        Initailized
 */

package com.zz.bsp.dao;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.zz.bsp.orm.query.OecsCriterion;


/**
 * DAO的基接口
 * 其他基类都可以实现该或者基础该接口
 */
public interface Dao<T, PK extends Serializable>{
    /**
     * 保存新增的对象.
     */
    public void save(final T entity);
       
    /**
     * 保存修改的对象.
     */
    public void update(final T entity);
    
    /**
     * 按id删除对象.
     */
    public void delete(final PK id);
    
    /**
     * 按id获取对象.
     */
    public T get(final PK id);
    
    /**
     *  根据分页获取数据, rowBounds如果为空，则没有分页
     */
    List<T> find(OecsCriterion criterion, RowBounds rowBounds);
    
    /**
     * 计算总记录数
     * @param criterion
     * @return
     */
    public long count(OecsCriterion criterion);
}
