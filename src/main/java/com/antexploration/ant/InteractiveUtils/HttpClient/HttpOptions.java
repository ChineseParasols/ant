package com.antexploration.ant.InteractiveUtils.HttpClient;

/**
 * Http客户端的配置选项
 *
 * @date 2020年7月7日21:06:03
 */
public class HttpOptions {
    public static final HttpOptions DEFAULT_HTTP_OPTION = new HttpOptions();

    private Integer retryCount;//重试次数，默认null表示不重试。
    private Integer timeoutMs;//每次请求的超时时间，毫秒。

    public Integer getRetryCount() {
        return retryCount;
    }

    public HttpOptions setRetryCount(Integer retryCount) {
        this.retryCount = retryCount;
        return this;
    }

    public Integer getTimeoutMs() {
        return timeoutMs;
    }

    public HttpOptions setTimeoutMs(Integer timeoutMs) {
        this.timeoutMs = timeoutMs;
        return this;
    }

    public HttpOptions(Integer retryCount, Integer timeoutMs) {
        this.retryCount = retryCount;
        this.timeoutMs = timeoutMs;
    }

    public HttpOptions() {
        super();
    }

    @Override
    public String toString() {
        return "HttpOptions{" +
                "retryCount=" + retryCount +
                ", timeoutMs=" + timeoutMs +
                '}';
    }
}
