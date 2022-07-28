package com.page_service.Config;

import com.page_service.Handlers.WebSocketHandlers;
import com.page_service.Interceptor.WebSocketInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfigs implements WebSocketConfigurer {
    /**
     * 注入拦截器
     */
    @Autowired
    private WebSocketInterceptor webSocketInterceptor;

//    /**
//     * 注入处理器
//     * @param webSocketHandlerRegistry
//     */
//    @Resource
//    private WebSocketHandlers webSocketHandlers;


//    @Bean
//    public ServerEndpointExporter serverEndpointExporter(){
//        return new ServerEndpointExporter();
//    }
    @Bean
    public WebSocketHandlers webSocketHandlers(){
        return new WebSocketHandlers();
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry
                //添加Handler消息处理对象，和websocket访问地址
                .addHandler(webSocketHandlers(),"/webSocket")
                //设置允许跨越访问
                .setAllowedOrigins("*")
                //添加拦截器可实现用户链接前进行权限校验等操作
                .addInterceptors(webSocketInterceptor);
    }
}
