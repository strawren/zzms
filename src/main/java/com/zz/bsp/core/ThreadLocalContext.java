/**
 * Copyright : http://www.orientpay.com , 2007-2012
 * Project : oecs-g2-common-framework-trunk
 * $Id$
 * $Revision$
 * Last Changed by ZhouXushun at 2011-8-4 下午04:53:49
 * $URL$
 *
 * Change Log
 * Author Change Date Comments
 * -------------------------------------------------------------
 * ZhouXushun 2011-8-4 Initailized
 */

package com.zz.bsp.core;

import java.util.HashMap;

/**
 * ThreadLocal上下文
 * 用于存储一些需要在一个线程里面用到的变量
 * 比如remoteIp, username, password
 */
public class ThreadLocalContext {

    private static final ThreadLocal<ThreadLocalContext> localContext = new ThreadLocal<ThreadLocalContext>();

    private int count;
    private final HashMap<String, Object> values = new HashMap<String, Object>();

    private ThreadLocalContext() {

    }

    /**
     * Sets the request serviceContext.
     *
     *
     */
    public static synchronized ThreadLocalContext begin() {
        ThreadLocalContext context = localContext.get();
        if (context == null) {
            context = new ThreadLocalContext();
            localContext.set(context);
        }
        context.count++;
        return context;
    }

    /**
     * Returns the service request.
     */
    public static ThreadLocalContext getContext() {
        return localContext.get();
    }

    /**
     * Add a object.
     */
    public void addValue(String key, Object value) {
        values.put(key, value);
    }

    /**
     * Gets a object.
     */
    public Object getValue(String key) {
        return values.get(key);
    }

    /**
     * Gets a header from the context.
     */
    public static Object getContextValue(String header) {
        ThreadLocalContext context = localContext.get();

        if (context != null) {
            return context.getValue(header);
        }
        return null;
    }

    /**
     * Cleanup at the end of a request.
     */
    public static synchronized void end() {
        ThreadLocalContext context = localContext.get();

        if (context != null && --context.count == 0) {
            context.values.clear();
            localContext.remove();
        }
    }
}
