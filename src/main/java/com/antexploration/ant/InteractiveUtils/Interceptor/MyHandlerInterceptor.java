package com.antexploration.ant.InteractiveUtils.Interceptor;

import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.antexploration.ant.InteractiveUtils.RedisUtil.RedisService;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;

@Configuration
public class MyHandlerInterceptor implements HandlerInterceptor {

    @Resource
    RedisService redisService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        try {
            //拿到headertoken
            final String token = httpServletRequest.getHeader("token");
            //验证token
            final Map<String, Object> map = TokenHS256.ValidToken(token);
            //根据校结果果进处理理
            Integer status = Integer.parseInt(map.get("status").toString());
            String uid = redisService.get(1, "REDIS_TOKEN_" + map.get("uid"));
            if (uid != null) {
                if (!token.equals(uid)) {
                    httpServletResponse.setCharacterEncoding("UTF-8");//设置编码格式
                    httpServletResponse.setContentType("application/json; charset=utf-8");
                    ResponseResult<String> rstResult = ResponseResult.e(ResponseCode.NoToken("身份已失效"));
                    String jsonString = JSON.toJSONString(rstResult);
                    httpServletResponse.getWriter().print(jsonString);
                    return false;
                }
            } else {
                httpServletResponse.setCharacterEncoding("UTF-8");//设置编码格式
                httpServletResponse.setContentType("application/json; charset=utf-8");
                ResponseResult<String> rstResult = ResponseResult.e(ResponseCode.NoToken("RToken失效"));
                String jsonString = JSON.toJSONString(rstResult);
                httpServletResponse.getWriter().print(jsonString);
                return false;
            }

            if (status == 0) {
                return true;
            } else if (status == 1) {
                httpServletResponse.setCharacterEncoding("UTF-8");//设置编码格式
                httpServletResponse.setContentType("application/json; charset=utf-8");
                ResponseResult<String> rstResult = ResponseResult.e(ResponseCode.NoToken("token过期"));
                String jsonString = JSON.toJSONString(rstResult);
                httpServletResponse.getWriter().print(jsonString);
                return false;
            } else {
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.setContentType("application/json; charset=utf-8");
                ResponseResult<String> rstResult = ResponseResult.e(ResponseCode.NoToken("非法签名"));
                String jsonString = JSON.toJSONString(rstResult);
                httpServletResponse.getWriter().print(jsonString);
                return false;
            }
        } catch (Exception e) {
            // TODO: handle exception
            httpServletResponse.setCharacterEncoding("UTF-8");//设置编码格式
            httpServletResponse.setContentType("application/json; charset=utf-8");
            ResponseResult<String> rstResult = ResponseResult.e(ResponseCode.NoToken("token失效"));
            String jsonString = JSON.toJSONString(rstResult);
            httpServletResponse.getWriter().print(jsonString);
            return false;
        }

    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
                           ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {

    }
}
