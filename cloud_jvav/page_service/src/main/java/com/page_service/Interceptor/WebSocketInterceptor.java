package com.page_service.Interceptor;


import com.page_service.Cache.WebSocketCache;
import com.page_service.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.Cookie;
import java.util.Map;

@Component
public class WebSocketInterceptor implements HandshakeInterceptor {
    @Autowired
    private WebSocketCache webSocketCache;
    @Autowired
    private TokenUtils tokenUtils;

    /**
     * 握手之前触发
     * @param request
     * @param response
     * @param webSocketHandler
     * @param attributes
     * @return  是否握手成功：true-成功，false-失败
     * @throws Exception
     */
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler webSocketHandler, Map<String, Object> attributes) throws Exception {
        ServletServerHttpRequest servletServerHttpRequest = (ServletServerHttpRequest) request;
        ServletServerHttpResponse serverHttpResponse = (ServletServerHttpResponse) response;
        String authorization = servletServerHttpRequest.getServletRequest().getHeader("Sec-WebSocket-Protocol");

        Cookie[] cookies = servletServerHttpRequest.getServletRequest().getCookies();
        String token = null;
        for (Cookie cookie:cookies){
            if (cookie.getName().equals("token")){
                token = cookie.getValue();
            }
        }

        // 身份效验
        try {
            //设置请求头（Sec-WebSocket-Protocol）,不设置会报错导致建立连接成功后立即被关闭
            serverHttpResponse.getServletResponse().setHeader("Sec-WebSocket-Protocol", authorization);
            //获取用户id
            String user_id = String.valueOf(tokenUtils.getUserId(token));
//            String userId = TokenUtils.getUserId(authorization);

            // 逻辑处理
            attributes.put("id",user_id);

            // 已存在连接,关闭请求
            if (webSocketCache.Has(user_id)) return true;

            // 缓存记录存在该用户,缓存时间1小时
            webSocketCache.put(user_id,3600L);
        }catch (Exception e){
            // 无法解析效验Token
            return false;
        }

        return true;
    }

    /**
     * 握手之后
     * @param serverHttpRequest
     * @param serverHttpResponse
     * @param webSocketHandler
     * @param e
     */
    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {
        System.out.println("handshake success!");
    }
}
