/**
 * Copyright : http://www.orientpay.com , 2007-2012
 * Project : oecs-g2-common-framework-trunk
 * $Id$
 * $Revision$
 * Last Changed by ZhouXushun at 2011-8-26 下午02:40:45
 * $URL$
 * 
 * Change Log
 * Author      Change Date    Comments
 *-------------------------------------------------------------
 * ZhouXushun     2011-8-26        Initailized
 */

package com.zz.bsp.orm.dialect;

/**
 * MYSQL本地语言
 * 
 */
public class MySQLDialect extends Dialect {
    
    public boolean supportsLimitOffset() {
        return true;
    }
    
    public boolean supportsLimit() {
        return true;
    }
    
    public String getLimitString(String sql,
                                 int offset,
                                 String offsetPlaceholder,
                                 int limit,
                                 String limitPlaceholder) {
        if (offset > 0) {
            return sql + " limit " + offsetPlaceholder + "," + limitPlaceholder;
        }
        return sql + " limit " + limitPlaceholder;
    }
}
