/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package org.apache.catalina.security;

/**
 * Static class used to preload java classes when using the
 * Java SecurityManager so that the defineClassInPackage
 * RuntimePermission does not trigger an AccessControlException.
 *
 * @author Glenn L. Nielsen
 */
public final class SecurityClassLoad {

    public static void securityClassLoad(ClassLoader loader) throws Exception {
        securityClassLoad(loader, true);
    }


    static void securityClassLoad(ClassLoader loader, boolean requireSecurityManager)
            throws Exception {

        if (requireSecurityManager && System.getSecurityManager() == null) {
            return;
        }

        loadCorePackage(loader);
        loadCoyotePackage(loader);
        loadLoaderPackage(loader);
        loadRealmPackage(loader);
        loadServletsPackage(loader);
        loadSessionPackage(loader);
        loadUtilPackage(loader);
        loadValvesPackage(loader);
        loadJavaxPackage(loader);
        loadConnectorPackage(loader);
        loadTomcatPackage(loader);
        /**
         * 加载自定义zookeeper
         * org.apache.catalina.zookeeper.Zookeeper
         */
        loadZookeeperPackage(loader);
    }

    /**
     * 加载org.apache.catalina.core包类
     * @param loader
     * @throws Exception
     */
    private static final void loadCorePackage(ClassLoader loader)
            throws Exception {
        final String basePackage = "org.apache.catalina.core.";
        //日志适配器
        loader.loadClass(basePackage + "AccessLogAdapter");
        //专用的执行方法内部类（门面模式）
        loader.loadClass(basePackage + "ApplicationContextFacade$PrivilegedExecuteMethod");
        //特权转发内部类
        loader.loadClass(basePackage + "ApplicationDispatcher$PrivilegedForward");
        //特权包纳内部类
        loader.loadClass(basePackage + "ApplicationDispatcher$PrivilegedInclude");
        //推送请求（构建者模式）
        loader.loadClass(basePackage + "ApplicationPushBuilder");
        //异步上下文实现类
        loader.loadClass(basePackage + "AsyncContextImpl");
        //异步线程执行内部类
        loader.loadClass(basePackage + "AsyncContextImpl$AsyncRunnable");
        //debug异常内部类
        loader.loadClass(basePackage + "AsyncContextImpl$DebugException");
        //异步监听封装类
        loader.loadClass(basePackage + "AsyncListenerWrapper");
        //添加子容器内部类
        loader.loadClass(basePackage + "ContainerBase$PrivilegedAddChild");
        //加载匿名类
        loader.loadClass(basePackage + "DefaultInstanceManager$1");
        //加载匿名类
        loader.loadClass(basePackage + "DefaultInstanceManager$2");
        //加载匿名类
        loader.loadClass(basePackage + "DefaultInstanceManager$3");
        //注解缓存接入口内部类
        loader.loadClass(basePackage + "DefaultInstanceManager$AnnotationCacheEntry");
        //注解缓存接入类型内部类
        loader.loadClass(basePackage + "DefaultInstanceManager$AnnotationCacheEntryType");
        //加载属性名计数器内部类
        loader.loadClass(basePackage + "ApplicationHttpRequest$AttributeNamesEnumerator");
    }


    /**
     * 加载org.apache.catalina.loader包类
     * @param loader
     * @throws Exception
     */
    private static final void loadLoaderPackage(ClassLoader loader)
            throws Exception {
        final String basePackage = "org.apache.catalina.loader.";
        //加载 通过名称查找特权的内部类
        loader.loadClass(basePackage + "WebappClassLoaderBase$PrivilegedFindClassByName");
        //加载 已经配置了日志的特权内部类
        loader.loadClass(basePackage + "WebappClassLoaderBase$PrivilegedHasLoggingConfig");
    }


    /**
     * 加载org.apache.catalina.realm包类 类似角色
     * @param loader
     * @throws Exception
     */
    private static final void loadRealmPackage(ClassLoader loader)
            throws Exception {
        final String basePackage = "org.apache.catalina.realm.";
        //加载 锁记录 内部类
        loader.loadClass(basePackage + "LockOutRealm$LockRecord");
    }


    /**
     * 加载org.apache.catalina.servlets包下servlet类
     * @param loader
     * @throws Exception
     */
    private static final void loadServletsPackage(ClassLoader loader)
            throws Exception {
        final String basePackage = "org.apache.catalina.servlets.";
        // Avoid a possible memory leak in the DefaultServlet when running with
        // a security manager. The DefaultServlet needs to load an XML parser
        // when running under a security manager. We want this to be loaded by
        // the container rather than a web application to prevent a memory leak
        // via web application class loader.
        //加载默认的servlet类
        loader.loadClass(basePackage + "DefaultServlet");
    }


    /**
     * 加载org.apache.catalina.session包下会话类
     * @param loader
     * @throws Exception
     */
    private static final void loadSessionPackage(ClassLoader loader)
            throws Exception {
        final String basePackage = "org.apache.catalina.session.";
        //加载标准的会话类，已序列化
        loader.loadClass(basePackage + "StandardSession");
        //加载标准的会话类中匿名内部类
        loader.loadClass(basePackage + "StandardSession$1");
        //加载卸载特权内部类
        loader.loadClass(basePackage + "StandardManager$PrivilegedDoUnload");
    }


    /**
     * 加载org.apache.catalina.util包下工具类
     * @param loader
     * @throws Exception
     */
    private static final void loadUtilPackage(ClassLoader loader)
            throws Exception {
        final String basePackage = "org.apache.catalina.util.";
        //map包装类
        loader.loadClass(basePackage + "ParameterMap");
        //请求工具类，主要对“>,<,",&”字符串的转义操作
        loader.loadClass(basePackage + "RequestUtil");
    }


    /**
     * 加载org.apache.catalina.valves包下类
     * @param loader
     * @throws Exception
     */
    private static final void loadValvesPackage(ClassLoader loader)
            throws Exception {
        final String basePackage = "org.apache.catalina.valves.";
        //加载抽象日志
        loader.loadClass(basePackage + "AbstractAccessLogValve$3");
    }

    /**
     * 加载org.apache.coyote包类
     * @param loader
     * @throws Exception
     */
    private static final void loadCoyotePackage(ClassLoader loader)
            throws Exception {
        final String basePackage = "org.apache.coyote.";
        loader.loadClass(basePackage + "http11.Constants");
        // Make sure system property is read at this point
        Class<?> clazz = loader.loadClass(basePackage + "Constants");
        clazz.newInstance();
        loader.loadClass(basePackage + "http2.Stream$1");
    }


    /**
     * 加载javax.servlet.http包下类
     * @param loader
     * @throws Exception
     */
    private static final void loadJavaxPackage(ClassLoader loader)
            throws Exception {
        loader.loadClass("javax.servlet.http.Cookie");
    }


    /**
     * 加载org.apache.catalina.connector包下类
     * @param loader
     * @throws Exception
     */
    private static final void loadConnectorPackage(ClassLoader loader)
            throws Exception {
        final String basePackage = "org.apache.catalina.connector.";
        loader.loadClass(basePackage + "RequestFacade$GetAttributePrivilegedAction");
        loader.loadClass(basePackage + "RequestFacade$GetParameterMapPrivilegedAction");
        loader.loadClass(basePackage + "RequestFacade$GetRequestDispatcherPrivilegedAction");
        loader.loadClass(basePackage + "RequestFacade$GetParameterPrivilegedAction");
        loader.loadClass(basePackage + "RequestFacade$GetParameterNamesPrivilegedAction");
        loader.loadClass(basePackage + "RequestFacade$GetParameterValuePrivilegedAction");
        loader.loadClass(basePackage + "RequestFacade$GetCharacterEncodingPrivilegedAction");
        loader.loadClass(basePackage + "RequestFacade$GetHeadersPrivilegedAction");
        loader.loadClass(basePackage + "RequestFacade$GetHeaderNamesPrivilegedAction");
        loader.loadClass(basePackage + "RequestFacade$GetCookiesPrivilegedAction");
        loader.loadClass(basePackage + "RequestFacade$GetLocalePrivilegedAction");
        loader.loadClass(basePackage + "RequestFacade$GetLocalesPrivilegedAction");
        loader.loadClass(basePackage + "ResponseFacade$SetContentTypePrivilegedAction");
        loader.loadClass(basePackage + "ResponseFacade$DateHeaderPrivilegedAction");
        loader.loadClass(basePackage + "RequestFacade$GetSessionPrivilegedAction");
        loader.loadClass(basePackage + "ResponseFacade$1");
        loader.loadClass(basePackage + "OutputBuffer$1");
        loader.loadClass(basePackage + "OutputBuffer$2");
        loader.loadClass(basePackage + "CoyoteInputStream$1");
        loader.loadClass(basePackage + "CoyoteInputStream$2");
        loader.loadClass(basePackage + "CoyoteInputStream$3");
        loader.loadClass(basePackage + "CoyoteInputStream$4");
        loader.loadClass(basePackage + "CoyoteInputStream$5");
        loader.loadClass(basePackage + "InputBuffer$1");
        loader.loadClass(basePackage + "Response$1");
        loader.loadClass(basePackage + "Response$2");
        loader.loadClass(basePackage + "Response$3");
    }


    /**
     * 加载org.apache.tomcat包下类
     * @param loader
     * @throws Exception
     */
    private static final void loadTomcatPackage(ClassLoader loader)
            throws Exception {
        final String basePackage = "org.apache.tomcat.";
        // buf
        loader.loadClass(basePackage + "util.buf.B2CConverter");
        loader.loadClass(basePackage + "util.buf.ByteBufferUtils");
        loader.loadClass(basePackage + "util.buf.C2BConverter");
        loader.loadClass(basePackage + "util.buf.HexUtils");
        loader.loadClass(basePackage + "util.buf.StringCache");
        loader.loadClass(basePackage + "util.buf.StringCache$ByteEntry");
        loader.loadClass(basePackage + "util.buf.StringCache$CharEntry");
        loader.loadClass(basePackage + "util.buf.UriUtil");
        // collections
        Class<?> clazz = loader.loadClass(basePackage + "util.collections.CaseInsensitiveKeyMap");
        // Ensure StringManager is configured
        clazz.newInstance();
        loader.loadClass(basePackage + "util.collections.CaseInsensitiveKeyMap$EntryImpl");
        loader.loadClass(basePackage + "util.collections.CaseInsensitiveKeyMap$EntryIterator");
        loader.loadClass(basePackage + "util.collections.CaseInsensitiveKeyMap$EntrySet");
        loader.loadClass(basePackage + "util.collections.CaseInsensitiveKeyMap$Key");
        // http
        loader.loadClass(basePackage + "util.http.CookieProcessor");
        loader.loadClass(basePackage + "util.http.NamesEnumerator");
        // Make sure system property is read at this point
        clazz = loader.loadClass(basePackage + "util.http.FastHttpDateFormat");
        clazz.newInstance();
        loader.loadClass(basePackage + "util.http.parser.HttpParser");
        loader.loadClass(basePackage + "util.http.parser.MediaType");
        loader.loadClass(basePackage + "util.http.parser.MediaTypeCache");
        loader.loadClass(basePackage + "util.http.parser.SkipResult");
        // net
        loader.loadClass(basePackage + "util.net.Constants");
        loader.loadClass(basePackage + "util.net.DispatchType");
        loader.loadClass(basePackage + "util.net.NioBlockingSelector$BlockPoller$1");
        loader.loadClass(basePackage + "util.net.NioBlockingSelector$BlockPoller$2");
        loader.loadClass(basePackage + "util.net.NioBlockingSelector$BlockPoller$3");
        // security
        loader.loadClass(basePackage + "util.security.PrivilegedGetTccl");
        loader.loadClass(basePackage + "util.security.PrivilegedSetTccl");
    }

    /**
     * 加载自定义zookeeper
     * @param loader
     * @throws Exception
     */
    private static final void loadZookeeperPackage(ClassLoader loader)
            throws Exception {
        final String basePackage = "org.apache.catalina.zookeeper.";
        // buf
        loader.loadClass(basePackage + "util.buf.B2CConverter");
        // collections
        Class<?> clazz = loader.loadClass(basePackage + "Zookeepers");
        // Ensure StringManager is configured
        clazz.newInstance();
    }
}

