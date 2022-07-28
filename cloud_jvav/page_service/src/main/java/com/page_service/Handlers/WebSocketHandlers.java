package com.page_service.Handlers;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

//@Component
//@ServerEndpoint("/webSocket")
public class WebSocketHandlers extends TextWebSocketHandler {
    /**
     * 静态变量，用来记录当前在线连接数
     */
    private static final AtomicInteger onlineNum = new AtomicInteger(0);

    /**
     * 存放每个客户端的连接对象
     */
    private static final ConcurrentHashMap<Object,WebSocketSession> sessionPools = new ConcurrentHashMap<>();

    /**
     * 在线人数加一
     */
    public static void addOnlineCount() {
        onlineNum.incrementAndGet();
    }

    /**
     * 在线人数减一
     */
    public static void subOnlineCount() {
        onlineNum.decrementAndGet();
    }

    /**
     * 接受客户端消息
     * 相当于@OnMessage
     * @param session
     * @param message
     * @throws Exception
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        session.sendMessage(new TextMessage(String.format("收到用户：【%s】发来的【%s】",
                session.getAttributes().get("id"),
                message.getPayload())));
    }


    /**
     * 建立连接
     * 相当于@OnOpen
     * @param session
     * @throws Exception
     *
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        addOnlineCount();
        if (sessionPools.get(session.getAttributes().get("id"))==null){
            sessionPools.put(session.getAttributes().get("id"),session);
        }
        //发送消息
        session.sendMessage(new TextMessage("Hello world"));
    }

    /**
     * 连接关闭后
     * 相当于@OnClose
     * @param session
     * @param status
     * @throws Exception
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        //在线人数递减
        subOnlineCount();
    }

    /**
     * 发送广播信息
     * @param message
     */
    public static void sendTopic(String message){
        //如果连接池为空，则直接返回
        if(sessionPools.isEmpty()){
            return;
        }
        for(Map.Entry<Object,WebSocketSession> entry:sessionPools.entrySet()){
            try {
                entry.getValue().sendMessage(new TextMessage(message));
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    /**
     * 点对点发送消息
     * @param user_id 用户
     * @param message 消息
     */
    public static void sendToUser(Integer user_id,String message){
        WebSocketSession socketSession = sessionPools.get(user_id);
        //如果连接池中没有该用户,直接返回空
        if(socketSession == null){
            return;
        }
        try {
            socketSession.sendMessage(new TextMessage(message));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
