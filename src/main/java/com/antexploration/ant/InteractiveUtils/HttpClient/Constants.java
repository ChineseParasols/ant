package com.antexploration.ant.InteractiveUtils.HttpClient;

/**
 * @author duyunjie
 * @date 2020年7月7日21:06:11
 */
public class Constants {

    // 此处解释下MaxtTotal和DefaultMaxPerRoute的区别：
    // 1、MaxtTotal是整个池子的大小；
    // 2、DefaultMaxPerRoute是根据连接到的主机对MaxTotal的一个细分；比如：
    // MaxtTotal=400 DefaultMaxPerRoute=200
    // 而我只连接到http://www.abc.com时，到这个主机的并发最多只有200；而不是400；
    // 而我连接到http://www.bac.com 和
    // http://www.ccd.com时，到每个主机的并发最多只有200；即加起来是400（但不能超过400）；所以起作用的设置是DefaultMaxPerRoute

    //系统变量，控制HttpClientUtil中连接池的最大连接数
    public static final String SYSTEM_PROPERTY_KEY_HTTP_MAX_TOTAL = "640";

    //系统变量，控制HttpClientUtil中连接池的每个路由的最大连接数
    public static final String SYSTEM_PROPERTY_KEY_HTTP_MAX_PER_ROUTE = "320";
}
