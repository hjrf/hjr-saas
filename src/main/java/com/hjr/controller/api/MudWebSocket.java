package com.hjr.controller.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hjr.controller.BaseController;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by Admin on 2017/11/27.
 */
@ServerEndpoint(value = "/mudWebsocket")
@Component
public class MudWebSocket extends BaseController {
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;

    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteArraySet<MudWebSocket> webSocketSet = new CopyOnWriteArraySet<MudWebSocket>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSocketSet.add(this);     //加入set中
        addOnlineCount();           //在线数加1
        System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());
        try {
            sendMessage("连接到服务器成功");
        } catch (IOException e) {
            System.out.println("IO异常");
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);  //从set中删除
        subOnlineCount();           //在线数减1
        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("来自客户端的消息:" + message);

        JSONObject jsonObject = JSONObject.parseObject(message);
        for(String key:jsonObject.keySet()){
            switch (key)
            {
                case "chat":
                    jsonObject = (JSONObject) jsonObject.get(key);
                    Date now = new Date();
                    SimpleDateFormat ft = new SimpleDateFormat("hh:mm:ss");
                    jsonObject.put("chatTime",ft.format(now));
                    jsonObject.put("chat",jsonObject);
                    //群发消息
                    for (MudWebSocket item : webSocketSet) {
                        try {
                            item.sendMessage(jsonObject.toString());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case "location":
                    mongoTemplate.save(jsonObject.get(key), "player");
                    //群发消息
                    for (MudWebSocket item : webSocketSet) {
                        try {
                            item.sendMessage(message);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    //单发消息
                    JSONObject jsonObjectTemp = (JSONObject) jsonObject.get(key);
                    int chaCurRoomId = (int) jsonObjectTemp.get("chaCurRoomId");
                    Query query = new Query();
                    query.addCriteria(Criteria.where("chaCurRoomId").is(chaCurRoomId));
                    List<Map> players = mongoTemplate.find(query, Map.class, "player");
                    Map<String,Object> playersMap = new HashMap<>();
                    playersMap.put("curRoomChaList",players);
                    try {
                        sendMessage(JSONObject.toJSONString(playersMap));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                default:break;
            }


        }

    }

    /**
     * 发生错误时调用
     @OnError
      * */
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }



     public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
     //this.session.getAsyncRemote().sendText(message);
     }


     /**
      * 群发自定义消息
      * */
    public static void sendInfo(String message) throws IOException {
        for (MudWebSocket item : webSocketSet) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                continue;
            }
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        MudWebSocket.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        MudWebSocket.onlineCount--;
    }
}