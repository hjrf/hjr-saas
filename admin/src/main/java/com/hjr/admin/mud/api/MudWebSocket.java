package com.hjr.admin.mud.api;

import com.alibaba.fastjson.JSONObject;
import com.hjr.admin.mud.JoinChaAttr.model.JoinChaAttrModel;
import com.hjr.admin.mud.JoinChaIte.model.JoinChaIteMapper;
import com.hjr.admin.mud.JoinChaIte.model.JoinChaIteModel;
import com.hjr.admin.mud.JoinChaSki.model.JoinChaSkiMapper;
import com.hjr.admin.mud.JoinChaSki.model.JoinChaSkiModel;
import com.hjr.admin.mud.JoinRoomCha.model.JoinRoomChaModel;
import com.hjr.admin.mud.JoinRoomIte.model.JoinRoomIteModel;
import com.hjr.admin.mud.attr.model.AttrModel;
import com.hjr.admin.mud.character.model.CharacterModel;
import com.hjr.admin.mud.items.model.ItemsModel;
import com.hjr.admin.mud.room.model.MapModel;
import com.hjr.admin.mud.room.model.RoomMapper;
import com.hjr.admin.mud.room.model.RoomModel;
import com.hjr.admin.mud.setting.model.SettingModel;
import com.hjr.admin.mud.skill.model.SkillModel;
import com.hjr.admin.system.user.model.UserModel;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import util.BaseController;
import util.RandomCreate;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by Admin on 2017/11/27.
 */
@ServerEndpoint(value = "/mudWebsocket/{username}/{paassword}")
@Component
public class MudWebSocket extends BaseController {
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;

    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteArraySet<MudWebSocket> webSocketSet = new CopyOnWriteArraySet<MudWebSocket>();

    //用户id
    private String username;
    private String password;

    private String chaId;

    Map <String,String> userMap = new HashMap<>();

    //表示与某个用户的连接会话，通过它给客户端发送数据
    private Session session;
    //用户id和websocket的session绑定的路由表
    private static Map routeTable = new HashMap<>();
    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(@PathParam("username")String username,@PathParam("password")String password, Session session) {
        this.session = session;
        //获取当前登录用户的id
        this.username = username;
        this.password = password;
//        if(!"hjr".equals(username)){
//            Map<String, Object> jsonStr = new HashMap<>();
//            jsonStr.put("msg", "用户名或密码错误！");
//            try {
//                sendMessage(JSONObject.toJSONString(jsonStr));
//            } catch (IOException e) {
//
//            }
//            return;
//        }
        //将用户id和session绑定到路由表
        //绑定之后就可以在其它地方根据id来获取session，这时两个用户私聊就可以实现了
//        routeTable.put(username, session);

        webSocketSet.add(this);     //加入set中
        addOnlineCount();           //在线数加1
        System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());

        try {
//            sendAllMessage("有新连接加入！当前在线人数为" + getOnlineCount());
            List<Map> list = new ArrayList<>();
            Map map = new HashMap<>();
            map.put("msg","连接到服务器成功");
            list.add(map);
            Map<String, Object> jsonStr = new HashMap<>();
            jsonStr.put("conn", list);
            sendMessage(JSONObject.toJSONString(jsonStr));
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
        try{
//            sendAllMessage("有一连接关闭！当前在线人数为" + getOnlineCount());
        }catch (Exception e){}
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("来自客户端的消息:" + message);
        try {
            JSONObject jsonObject = JSONObject.parseObject(message);
            JSONObject  jsonObjectKey ;
            for (String key : jsonObject.keySet()) {
                jsonObjectKey = (JSONObject) jsonObject.get(key);
                switch (key) {
                    case "login":
                        sendMessage(JSONObject.toJSONString(login(jsonObjectKey)));
                        break;
                    case "chat":
                        jsonObject = (JSONObject) jsonObject.get(key);
                        Date now = new Date();
                        SimpleDateFormat ft = new SimpleDateFormat("hh:mm:ss");
                        jsonObject.put("chatTime", ft.format(now));
                        jsonObject.put("chat", jsonObject);
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
                        Map<String, Object> playersMap = new HashMap<>();
                        playersMap.put("curRoomChaList", players);
                        try {
                            sendMessage(JSONObject.toJSONString(playersMap));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "guaji":
                        jsonObject = (JSONObject) jsonObject.get(key);
                        now = new Date();
                        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        jsonObject.put("startTime", formatter.format(now));
                        mongoTemplate.save(jsonObject, "mud_guaji");
                        break;
                    case "exeSkill":
                        sendMessage(JSONObject.toJSONString(exeSkill(jsonObjectKey)));
                        break;
                    case "exeExchange":
                        sendMessage(JSONObject.toJSONString(exeExchange(jsonObjectKey)));
                        break;
                    case "attr":
                        sendMessage(JSONObject.toJSONString(attr(jsonObjectKey)));
                        break;
                    case "equip":
                        sendMessage(JSONObject.toJSONString(equip(jsonObjectKey)));
                        break;
                    case "operate":
                        sendMessage(JSONObject.toJSONString(operate(jsonObjectKey)));
                        break;
                    case "equipAttr":
                        sendMessage(JSONObject.toJSONString(equipAttr(jsonObjectKey)));
                        break;
                    case "skill":
                        sendMessage(JSONObject.toJSONString(skill(jsonObjectKey)));
                        break;
                    case "function":
                        sendMessage(JSONObject.toJSONString(function(jsonObjectKey)));
                        break;
                    case "largeMap":
                        sendMessage(JSONObject.toJSONString(largeMap(jsonObjectKey)));
                        break;
                    case "smallMap":
                        sendMessage(JSONObject.toJSONString(smallMap(jsonObjectKey)));
                        break;
                    case "skillAttr":
                        sendMessage(JSONObject.toJSONString(skillAttr(jsonObjectKey)));
                        break;
                    case "player":
                        sendMessage(JSONObject.toJSONString(player(jsonObjectKey)));
                        break;
                    case "buff":
                        sendMessage(JSONObject.toJSONString(buff(jsonObjectKey)));
                        break;
                    case "move":
                        sendMessage(JSONObject.toJSONString(move(jsonObjectKey)));
                        break;
                    case "character":
                        sendMessage(JSONObject.toJSONString(character(jsonObjectKey)));
                        break;
                    case "roomCha":
                        sendMessage(JSONObject.toJSONString(roomCha(jsonObjectKey)));
                        break;
                    case "roomIte":
                        sendMessage(JSONObject.toJSONString(roomIte(jsonObjectKey)));
                        break;
                    case "item":
                        sendMessage(JSONObject.toJSONString(item(jsonObjectKey)));
                        break;
                    case "chaItem":
                        sendMessage(JSONObject.toJSONString(chaItem(jsonObjectKey)));
                        break;
                    case "otherItem":
                        sendMessage(JSONObject.toJSONString(otherItem(jsonObjectKey)));
                        break;
                    case "chaSkill":
                        sendMessage(JSONObject.toJSONString(chaSkill(jsonObjectKey)));
                        break;
                    case "showItem":
                        sendMessage(JSONObject.toJSONString(showItem(jsonObjectKey)));
                        break;
                    case "showSkill":
                        sendMessage(JSONObject.toJSONString(showSkill(jsonObjectKey)));
                        break;
                    case "room":
                        sendMessage(JSONObject.toJSONString(room(jsonObjectKey)));
                        break;
                    case "enemy":
                        sendMessage(JSONObject.toJSONString(enemy(jsonObjectKey)));
                        break;
                    case "our":

                        sendMessage(JSONObject.toJSONString(our(jsonObjectKey)));
                        break;
                    case "fight":
                        sendMessage(JSONObject.toJSONString(fight(jsonObjectKey)));
                        break;
                    case "talkWithOne":
                        sendMessage(JSONObject.toJSONString(talkWithOne(jsonObjectKey)));
                        break;
                    case "talkWithAll":
                        sendAllMessage(JSONObject.toJSONString(talkWithAll(jsonObjectKey)));
                        break;
                    case "action":
                        sendMessage(JSONObject.toJSONString(action(jsonObjectKey)));
                        break;
                    case "doAction":
                        sendMessage(JSONObject.toJSONString(doAction(jsonObjectKey)));
                        sendMessage(JSONObject.toJSONString(our(jsonObjectKey)));
                        sendMessage(JSONObject.toJSONString(enemy(jsonObjectKey)));
                        break;
                    default:
                        break;
                }
            }
        }catch (Exception e){
            System.out.println("来自客户端格式不符合的消息:" + message);
            e.printStackTrace();
        }

    }

    /**
     * 发生错误时调用
     @OnError
      * */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("客户端强制关闭连接，发生错误");
        error.printStackTrace();
    }




    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
        //this.session.getAsyncRemote().sendText(message);
    }


    /**
     * 群发自定义消息
     * */
    public static void sendAllMessage(String message) throws IOException {
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

    List<Map> list = new ArrayList<>();
    Map<String, Object> map = new HashMap<>();
    Map<String, Object> jsonStr = new HashMap<>();

    //处理
    Map<String, Object> attr(JSONObject jsonObject){
        CharacterModel characterModel = characterMapper.queryForOneById(chaId);
        AttrModel attrModel = characterModel.getAttrModel();
        AttrModel attrModel_zb = characterModel.getAttrModel_zb();


        map.put("name","基本属性");
        map.put("desc","自身+内功+装备+buff");
        list.add(map);
        map = new HashMap<>();
        list.add(map);
        map = new HashMap<>();
        map.put("name","姓名："+characterModel.getName());
        map.put("desc","臂力的详细描述<br/>臂力：1032<font color='0x00e500'>+2</font><font color='#ff2121'>-3</font>");
        list.add(map);
        map = new HashMap<>();
        map.put("name","生命："+attrModel.getHP()+"/"+attrModel.getHpMax());
        map.put("desc","内息的详细描述");
        list.add(map);
        map = new HashMap<>();
        map.put("name","内力 ："+attrModel.getMP()+"/"+attrModel.getMpMax());
        map.put("desc","悟性的详细描述");
        list.add(map);
        map = new HashMap<>();
        map.put("name","身法：0");
        map.put("desc","身法的详细描述");
        list.add(map);

        jsonStr.put("attr", list);
        return jsonStr;
    }

    Map<String, Object> exeExchange(JSONObject jsonObject){
        List<Map> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> jsonStr = new HashMap<>();
        String id = (String) jsonObject.get("name");
        map.put("status","1");
        map.put("msg","你存入了xxx成功");
        list.add(map);
        jsonStr.put("exeExchange", list);
        return jsonStr;
    }

    Map<String, Object> exeSkill(JSONObject jsonObject){
        List<Map> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> jsonStr = new HashMap<>();

        String id = (String) jsonObject.get("name");

        map.put("status","1");
        map.put("msg","设置技能成功");
        list.add(map);

        jsonStr.put("exeSkill", list);
        return jsonStr;
    }

    Map<String, Object> login(JSONObject jsonObject){
        List<Map> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> jsonStr = new HashMap<>();
        username = String.valueOf(jsonObject.get("username"));
        password = String.valueOf(jsonObject.get("password"));

        UserModel userModel = userMapper.queryForOneByUsername(username);
        if(userModel != null){
            userModel = userMapper.queryForOneByUsernameAndPassword(username,password);
            if(userModel != null){map.put("code", "0");
                map.put("msg", "登陆成功");
                CharacterModel characterModel = characterMapper.queryForOneByUserId(userModel.getId());
                if(characterModel == null){
                    characterModel = new CharacterModel();
                    characterModel.setName("小虾米"+ RandomCreate.getNum(1,1000));
                    characterModel.setUserId(userModel.getId());
                    characterMapper.create(characterModel);
                    chaId = characterModel.getId();
                }else{
                    chaId = characterModel.getId();
                }
                routeTable.put(characterModel.getId(), session);
            }
            else {map.put("code", "1");map.put("msg", "密码错误");}
        }else{map.put("code", "1");map.put("msg", "该用户不存在");}

        list.add(map);
        jsonStr.put("login", list);
        return jsonStr;
    }

    Map<String, Object> equip(JSONObject jsonObject){
        List<Map> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> jsonStr = new HashMap<>();
        JoinChaIteMapper joinChaIteMapper = new JoinChaIteMapper();
        List<JoinChaIteModel> joinChaIteModelList = joinChaIteMapper.queryForAlreadyEquipByChaId(chaId);
        for(JoinChaIteModel joinChaIteModel : joinChaIteModelList) {
            ItemsModel itemsModel = (ItemsModel) itemsMapper.queryForOneByChaId(joinChaIteModel.getIteId());
            map = new HashMap<>();
            map.put("name", itemsModel.getName());
            map.put("desc", itemsModel.getContent());
            list.add(map);
        }
        jsonStr.put("equip", list);
        return jsonStr;
    }

    Map<String, Object> operate(JSONObject jsonObject){
        List<Map> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> jsonStr = new HashMap<>();
        String id = String.valueOf(jsonObject.get("id"));
        JoinChaIteMapper joinChaIteMapper = new JoinChaIteMapper();
        List<JoinChaIteModel> joinChaIteModelList = joinChaIteMapper.queryForAlreadyEquipByChaId(id);
        for(JoinChaIteModel joinChaIteModel : joinChaIteModelList) {
            map.put("name", "装备");
            map.put("desc", "装备---");
            list.add(map);
        }
        map.put("name", "装备");
        map.put("desc", "装备---");
        list.add(map);
        list.add(map);
        jsonStr.put("operate", list);
        return jsonStr;
    }

    Map<String, Object> equipAttr(JSONObject jsonObject){
        List<Map> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> jsonStr = new HashMap<>();
        CharacterModel characterModel = characterMapper.queryForOneById(chaId);
        AttrModel attrModel = characterModel.getAttrModel_zb();
        map = new HashMap<>();
        map.put("name","生命："+attrModel.getHP()+"/"+attrModel.getHpMax());
        map.put("desc","内息的详细描述");
        list.add(map);
        map = new HashMap<>();
        map.put("name","内力 ："+attrModel.getMP()+"/"+attrModel.getMpMax());
        map.put("desc","悟性的详细描述");
        list.add(map);
        list.add(map);
        list.add(map);
        jsonStr.put("equipAttr", list);
        return jsonStr;
    }

    Map<String, Object> skill(JSONObject jsonObject){
        List<Map> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> jsonStr = new HashMap<>();
        String id = String.valueOf(jsonObject.get("id"));
        JoinChaIteMapper joinChaIteMapper = new JoinChaIteMapper();
        List<JoinChaSkiModel> joinChaSkiModelList = joinChaSkiMapper.queryForAlreadyEquipByChaId(id);
        for(JoinChaSkiModel joinChaSkiModel : joinChaSkiModelList) {
            SkillModel skillModel = (SkillModel) skillMapper.queryForOneByChaId(joinChaSkiModel.getSkiId());
            map = new HashMap<>();
            map.put("name","基本拳脚：普通攻击");
            map.put("desc","基本拳脚");
            map.put("type",skillModel.getType());
            list.add(map);
        }
        jsonStr.put("skill", list);
        return jsonStr;
    }

    Map<String, Object> function(JSONObject jsonObject){
        List<Map<String,Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> jsonStr = new HashMap<>();
        SettingModel settingModel = (SettingModel) settingMapper.queryForOneByChaId(chaId);
        if(settingModel == null){
            settingModel = new SettingModel();
            settingModel.setChaId(chaId);
            map.put("name","地图");
            map.put("function","地图");
            list.add(map);
            settingModel.setFunction(list);
            settingMapper.create(settingModel);
        }
        list = settingModel.getFunction();
        jsonStr.put("function", list);
        return jsonStr;
    }


    Map<String, Object> largeMap(JSONObject jsonObject){
        List<Map> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> jsonStr = new HashMap<>();
        String countryName = String.valueOf(jsonObject.get("countryName"));
        List<MapModel> mapModelList = mapMapper.queryForListByCountryName(countryName);
        for(MapModel mapModel : mapModelList) {
            map = new HashMap<>();
            map.put("name",mapModel.getName());
            map.put("xy",mapModel.getMapXY());
            list.add(map);
        }
        jsonStr.put("largeMap", list);
        return jsonStr;
    }


    Map<String, Object> smallMap(JSONObject jsonObject){
        List<Map> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> jsonStr = new HashMap<>();
        String mapName = String.valueOf(jsonObject.get("mapName"));
        List<RoomModel> roomModelList = roomMapper.queryForListByMapMame(mapName);
        for(RoomModel roomModel : roomModelList) {
            map = new HashMap<>();
            map.put("name",roomModel.getName());
            map.put("xy",roomModel.getRoomXY());
            list.add(map);
        }
        jsonStr.put("smallMap", list);
        return jsonStr;
    }


    Map<String, Object> skillAttr(JSONObject jsonObject){
        List<Map> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> jsonStr = new HashMap<>();
        String id = String.valueOf(jsonObject.get("id"));
        JoinChaIteMapper joinChaIteMapper = new JoinChaIteMapper();
        List<JoinChaIteModel> joinChaIteModelList = joinChaIteMapper.queryForAlreadyEquipByChaId(id);
        for(JoinChaIteModel joinChaIteModel : joinChaIteModelList) {
            map.put("name","基本内功：100");
            map.put("desc","基本内功");
            list.add(map);
        }
        map.put("name","基本内功：100");
        map.put("desc","基本内功");
        list.add(map);
        list.add(map);
        list.add(map);
        jsonStr.put("skillAttr", list);
        return jsonStr;
    }

    Map<String, Object> buff(JSONObject jsonObject){
        List<Map> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> jsonStr = new HashMap<>();
        CharacterModel characterModel = characterMapper.queryForOneById(chaId);
        List<JoinChaAttrModel> joinChaAttrModelList = joinChaAttrMapper.queryForListByChaId(characterModel.getId());
        for(JoinChaAttrModel joinChaAttrModel : joinChaAttrModelList){
            map = new HashMap<>();
            map.put("name",joinChaAttrModel.getName());
            map.put("from",joinChaAttrModel.getFromType());
            list.add(map);
        }
        jsonStr.put("buff", list);
        return jsonStr;
    }

    Map<String, Object> player(JSONObject jsonObject){
        List<Map> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> jsonStr = new HashMap<>();

        CharacterModel characterModel = characterMapper.queryForOneById(chaId);

        AttrModel attrModel = characterModel.getAttrModel();

        if(attrModel == null){
            attrModel = new AttrModel();
            attrModel.setChaId(characterModel.getId());
            attrModel.setAttrType("人物");
            attrModel.setName(characterModel.getName());
            attrMapper.create(attrModel);
        }
        map.put("chaId",characterModel.getId());
        map.put("name",characterModel.getName());
        map.put("HP",attrModel.getHP());
        map.put("HPMax",attrModel.getHpMax());
        map.put("MP",attrModel.getHP());
        map.put("MPMax",attrModel.getHpMax());
        map.put("PP",attrModel.getHP());
        map.put("PPMax",attrModel.getHpMax());
        list.add(map);

        jsonStr.put("player", list);
        return jsonStr;
    }


    Map<String, Object> move(JSONObject jsonObject){
        List<Map> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> jsonStr = new HashMap<>();
        String mapName = jsonObject.getString("mapName");
        String roomXY = jsonObject.getString("roomXY");
        String fromRoomId = jsonObject.getString("fromRoomId");
        int x = Integer.parseInt(roomXY.split(",")[0]);
        int y = Integer.parseInt(roomXY.split(",")[1]);

        //当前
        RoomModel roomModel = roomMapper.queryForOneByMapNameAndXY(mapName,roomXY);
        CharacterModel characterModel = characterMapper.queryForOneById(chaId);
        //有人移动全体通知
        list = new ArrayList<>();
        map = new HashMap<>();
        jsonStr = new HashMap<>();
        map.put("fromRoomId","0".equals(fromRoomId)?roomModel.getId():fromRoomId);
        map.put("toRoomId",roomModel.getId());
        map.put("chaId",chaId);
        map.put("chaName",characterModel.getName());
        map.put("roomName",roomModel.getName());
        list.add(map);
        jsonStr.put("oneMove",list);
        try {
            sendAllMessage(JSONObject.toJSONString(jsonStr));
        } catch (IOException e) {

        }


        list = new ArrayList<>();
        map = new HashMap<>();
        jsonStr = new HashMap<>();

        JoinRoomChaModel joinRoomChaModel = (JoinRoomChaModel) joinRoomChaMapper.queryForOneByChaId(chaId);
        if(joinRoomChaModel == null) {
            joinRoomChaModel = new JoinRoomChaModel();
            joinRoomChaModel.setRoomId(roomModel.getId());
            joinRoomChaModel.setChaId(chaId);
            joinRoomChaMapper.create(joinRoomChaModel);
        }else{
            joinRoomChaModel.setRoomId(roomModel.getId());
            joinRoomChaMapper.updateModel(joinRoomChaModel);
        }

        //左上
        map = new HashMap<>();
        list.add(map);

        //上
        roomXY = x+","+(y-1);
        roomModel = roomMapper.queryForOneByMapNameAndXY(mapName,roomXY);
        map = new HashMap<>();
        if(roomModel != null) {
            map.put("mapName", roomModel.getMapName());
            map.put("id", roomModel.getId());
            map.put("name", "<font color='#ff2121'>"+roomModel.getName()+"</font>");
            map.put("desc", "<font color='#ff2121'>"+roomModel.getRoomDesc()+"</font>");
            map.put("roomXY", roomModel.getRoomXY());
            map.put("border", "FF3300");
        }
        list.add(map);

        //右上
        map = new HashMap<>();
        list.add(map);

        //左
        roomXY = (x-1)+","+y;
         roomModel = roomMapper.queryForOneByMapNameAndXY(mapName,roomXY);
        map = new HashMap<>();
        if(roomModel != null) {
            map.put("mapName", roomModel.getMapName());
            map.put("id", roomModel.getId());
            map.put("name", "<font color='#ff2121'>"+roomModel.getName()+"</font>");
            map.put("desc", "<font color='#ff2121'>"+roomModel.getRoomDesc()+"</font>");
            map.put("roomXY", roomModel.getRoomXY());
            map.put("border", "FF3300");
        }
        list.add(map);

        //中
        roomXY = x+","+y;
         roomModel = roomMapper.queryForOneByMapNameAndXY(mapName,roomXY);
        map = new HashMap<>();
        if(roomModel != null) {
            map.put("mapName", roomModel.getMapName());
            map.put("id", roomModel.getId());
            map.put("name", "<font color='#ff2121'>"+roomModel.getName()+"</font>");
            map.put("desc", "<font color='#ff2121'>"+roomModel.getRoomDesc()+"</font>");
            map.put("roomXY", roomModel.getRoomXY());
            map.put("border", "FF3300");
        }
        list.add(map);

        //右
        roomXY = (x+1)+","+y;
         roomModel = roomMapper.queryForOneByMapNameAndXY(mapName,roomXY);
        map = new HashMap<>();
        if(roomModel != null) {
            map.put("mapName", roomModel.getMapName());
            map.put("id", roomModel.getId());
            map.put("name", "<font color='#ff2121'>"+roomModel.getName()+"</font>");
            map.put("desc", "<font color='#ff2121'>"+roomModel.getRoomDesc()+"</font>");
            map.put("roomXY", roomModel.getRoomXY());
            map.put("border", "FF3300");
        }
        list.add(map);

        //下左
        map = new HashMap<>();
        list.add(map);

        //下
        roomXY = x+","+(y+1);
        roomModel = roomMapper.queryForOneByMapNameAndXY(mapName,roomXY);
        map = new HashMap<>();
        if(roomModel != null) {
            map.put("mapName", roomModel.getMapName());
            map.put("id", roomModel.getId());
            map.put("name", "<font color='#ff2121'>"+roomModel.getName()+"</font>");
            map.put("desc", "<font color='#ff2121'>"+roomModel.getRoomDesc()+"</font>");
            map.put("roomXY", roomModel.getRoomXY());
            map.put("border", "FF3300");
        }
        list.add(map);


        //下右
        map = new HashMap<>();
        list.add(map);

        list.add(map);
        jsonStr = new HashMap<>();
        jsonStr.put("move", list);





        return jsonStr;
    }

    Map<String, Object> character(JSONObject jsonObject) {
        List<Map> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> jsonStr = new HashMap<>();
        String roomId = (String) jsonObject.get("roomId");
        List<JoinRoomChaModel> joinRoomChaModelList = joinRoomChaMapper.queryForListByRoomId(roomId);
        for(JoinRoomChaModel joinRoomChaModel : joinRoomChaModelList){
            list = new ArrayList<>();
            map = new HashMap<>();
            map.put("name","<font color='#ff2121'>生物</font>");
            map.put("border","FF3300");
            list.add(map);

        }
        map = new HashMap<>();
        map.put("name","<font color='#ff2121'>生物</font>");
        map.put("border","FF3300");
        list.add(map);
        list.add(map);
        list.add(map);
        jsonStr = new HashMap<>();
        jsonStr.put("character", list);
        return jsonStr;
    }

    Map<String, Object> item(JSONObject jsonObject) {
        List<Map> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> jsonStr = new HashMap<>();
        String roomId = (String) jsonObject.get("roomId");
        List<JoinChaIteModel> joinChaIteModelList = joinChaIteMapper.queryForListByChaId(chaId);
        for(JoinChaIteModel joinChaIteModel : joinChaIteModelList){
            ItemsModel itemsModel = (ItemsModel) itemsMapper.queryForOneByChaId(joinChaIteModel.getIteId());
            map = new HashMap<>();
            map.put("name","<font color='#ff2121'>"+itemsModel.getName()+"</font>");
            map.put("type",itemsModel.getType());
            map.put("border","FF3300");
            map.put("desc","描述");
            list.add(map);
        }
        jsonStr = new HashMap<>();
        jsonStr.put("item", list);
        return jsonStr;
    }


    Map<String, Object> roomCha(JSONObject jsonObject) {
        List<Map> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> jsonStr = new HashMap<>();
       JoinRoomChaModel joinRoomChaModelCur = (JoinRoomChaModel) joinRoomChaMapper.queryForOneByChaId(chaId);
        List<JoinRoomChaModel> joinRoomChaModelList = joinRoomChaMapper.queryForListByRoomId(joinRoomChaModelCur.getRoomId());
        for(JoinRoomChaModel joinRoomChaModel : joinRoomChaModelList){
            CharacterModel characterModel =  characterMapper.queryForOneById(joinRoomChaModel.getChaId());
            map = new HashMap<>();
            map.put("name","<font color='#ff2121'>"+characterModel.getName()+"</font>");
            map.put("type",characterModel.getType());
            map.put("border","FF3300");
            map.put("desc","描述");
            map.put("chaId",characterModel.getId());
            list.add(map);
        }
        jsonStr = new HashMap<>();
        jsonStr.put("roomCha", list);
        return jsonStr;
    }

    Map<String, Object> roomIte(JSONObject jsonObject) {
        List<Map> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> jsonStr = new HashMap<>();
        JoinRoomIteModel joinRoomIteModelCur = (JoinRoomIteModel) joinRoomIteMapper.queryForOneByChaId(chaId);
        List<JoinRoomIteModel> joinRoomIteModelList = joinRoomIteMapper.queryForListByRoomId(joinRoomIteModelCur.getRoomId());
        for(JoinRoomIteModel joinRoomIteModel : joinRoomIteModelList){
            ItemsModel itemsModel = (ItemsModel) itemsMapper.queryForOneByChaId(joinRoomIteModel.getIteId());
            map = new HashMap<>();
            map.put("name","<font color='#ff2121'>"+itemsModel.getName()+"</font>");
            map.put("type",itemsModel.getType());
            map.put("border","FF3300");
            map.put("desc","描述");
            map.put("iteId",itemsModel.getId());
            list.add(map);
        }
        jsonStr = new HashMap<>();
        jsonStr.put("roomIte", list);
        return jsonStr;
    }

    Map<String, Object> chaItem(JSONObject jsonObject) {
        List<Map> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> jsonStr = new HashMap<>();
        String chaId = (String) jsonObject.get("chaId");
        JoinChaIteMapper joinChaIteMapper = new JoinChaIteMapper();
        List<JoinChaIteModel> joinChaIteModelList = joinChaIteMapper.queryForListByChaId(chaId);
        for(JoinChaIteModel joinChaIteModel : joinChaIteModelList){
            ItemsModel itemsModel = (ItemsModel) itemsMapper.queryForOneByChaId(joinChaIteModel.getIteId());
            map = new HashMap<>();
            map.put("name","<font color='#ff2121'>"+ itemsModel.getName() +"</font>");
            map.put("border","FF3300");
            list.add(map);
        }

        jsonStr = new HashMap<>();
        jsonStr.put("chaItem", list);
        return jsonStr;
    }

    Map<String, Object> otherItem(JSONObject jsonObject) {
        List<Map> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> jsonStr = new HashMap<>();
        String chaId = (String) jsonObject.get("chaId");
        JoinChaIteMapper joinChaIteMapper = new JoinChaIteMapper();
        List<JoinChaIteModel> joinChaIteModelList = joinChaIteMapper.queryForListByFiled("chaId",chaId);
        for(JoinChaIteModel joinChaIteModel : joinChaIteModelList){
            list = new ArrayList<>();
            map = new HashMap<>();
            map.put("name","<font color='#ff2121'>物品</font>");
            map.put("border","FF3300");
            list.add(map);
        }
        list = new ArrayList<>();
        map = new HashMap<>();
        map.put("name","<font color='#ff2121'>物品</font>");
        map.put("border","FF3300");
        list.add(map);
        list.add(map);
        list.add(map);
        list.add(map);
        list.add(map);
        list.add(map);
        list.add(map);list.add(map);list.add(map);list.add(map);list.add(map);list.add(map);list.add(map);list.add(map);list.add(map);list.add(map);list.add(map);list.add(map);list.add(map);list.add(map);list.add(map);list.add(map);list.add(map);list.add(map);list.add(map);list.add(map);list.add(map);list.add(map);list.add(map);list.add(map);list.add(map);list.add(map);list.add(map);list.add(map);list.add(map);list.add(map);list.add(map);

        jsonStr = new HashMap<>();
        jsonStr.put("otherItem", list);
        return jsonStr;
    }


    Map<String, Object> chaSkill(JSONObject jsonObject) {
        List<Map> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> jsonStr = new HashMap<>();
        String chaId = (String) jsonObject.get("chaId");
        JoinChaSkiMapper joinChaSkiMapper = new JoinChaSkiMapper();
        List<JoinChaSkiModel> joinChaSkiModelList = joinChaSkiMapper.queryForListByChaId(chaId);
        for(JoinChaSkiModel joinChaSkiModel : joinChaSkiModelList){
            SkillModel skillModel = (SkillModel) skillMapper.queryForOneByChaId(joinChaSkiModel.getSkiId());
            map = new HashMap<>();
            map.put("name","<font color='#ff2121'>"+ skillModel.getName()+"</font>");
            map.put("border","FF3300");
            list.add(map);
        }
        jsonStr = new HashMap<>();
        jsonStr.put("chaSkill", list);
        return jsonStr;
    }


    Map<String, Object> showItem(JSONObject jsonObject) {
        List<Map> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> jsonStr = new HashMap<>();

        list = new ArrayList<>();
        map = new HashMap<>();
        map.put("name","<font color='#ff2121'>物品</font>");
        map.put("border","FF3300");
        list.add(map);

        list.add(map);
        jsonStr = new HashMap<>();
        jsonStr.put("showItem", list);
        return jsonStr;
    }


    Map<String, Object> showSkill(JSONObject jsonObject) {
        List<Map> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> jsonStr = new HashMap<>();

        list = new ArrayList<>();
        map = new HashMap<>();
        map.put("name","<font color='#ff2121'>技能</font>");
        map.put("border","FF3300");
        list.add(map);

        list.add(map);
        jsonStr = new HashMap<>();
        jsonStr.put("showItem", list);
        return jsonStr;
    }



    Map<String, Object> room(JSONObject jsonObject) {
        List<Map> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> jsonStr = new HashMap<>();
        String mapName = jsonObject.getString("mapName");
        String roomXY = jsonObject.getString("roomXY");

        RoomMapper roomMapper = new RoomMapper();
        RoomModel roomModel = roomMapper.queryForOneByMapNameAndXY(mapName,roomXY);
        map = new HashMap<>();
        if(roomModel !=  null) {
            map.put("id", roomModel.getId());
            map.put("mapName", roomModel.getMapName());
            map.put("roomXY", roomModel.getRoomXY());
            map.put("name", "<font color='#ff2121'>" + roomModel.getName() + "</font>");
            map.put("desc", "<font color='#ff2121'>" + roomModel.getRoomDesc() + "</font>");
            map.put("border", "FF3300");
        }
        list.add(map);

        jsonStr.put("room", list);
        return jsonStr;
    }

    Map<String, Object> enemy(JSONObject jsonObject) {
        List<Map> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> jsonStr = new HashMap<>();
        list = new ArrayList<>();
        map = new HashMap<>();
        String id = String.valueOf(jsonObject.get("chaId"));
        CharacterModel characterModel = characterMapper.queryForOneById(id);
        AttrModel attrModel = characterModel.getAttrModel();
        map.put("name",characterModel.getName());
        map.put("HP",attrModel.getHP());
        map.put("MP",attrModel.getMP());
        map.put("HPMax",attrModel.getHpMax());
        map.put("MPMax",attrModel.getMpMax());
        map.put("speed",attrModel.getAttackSpeed());
        list.add(map);
        jsonStr = new HashMap<>();
        jsonStr.put("enemy", list);


        jsonStr.put("enemy", list);
        return jsonStr;
    }

    Map<String, Object> our(JSONObject jsonObject) {
        List<Map> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> jsonStr = new HashMap<>();
        list = new ArrayList<>();
        map = new HashMap<>();
        String id = String.valueOf(jsonObject.get("chaId"));
        CharacterModel characterModel = characterMapper.queryForOneById(id);
        AttrModel attrModel = characterModel.getAttrModel();
        map.put("name",characterModel.getName());
        map.put("HP",attrModel.getHP());
        map.put("MP",attrModel.getMP());
        map.put("HPMax",attrModel.getHpMax());
        map.put("MPMax",attrModel.getMpMax());
        map.put("speed",attrModel.getAttackSpeed());
        list.add(map);

        jsonStr = new HashMap<>();
        jsonStr.put("our", list);
        return jsonStr;
    }


    Map<String, Object> doAction(JSONObject jsonObject) throws IOException {
        List<Map> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> jsonStr = new HashMap<>();
        //参数：操作人 动作 被操作人
        //返回 操作人全体状态 被操作人全体状态  动作描述
        list = new ArrayList<>();
        map = new HashMap<>();
        map.put("name","小虾米：受死吧！");
        map.put("align","left");
        list.add(map);
        map = new HashMap<>();
        map.put("name","哈哈哈！！：山贼");
        map.put("align","right");
        list.add(map);
        map = new HashMap<>();
        map.put("name","小虾米打了山贼一下");
        list.add(map);
        map = new HashMap<>();
        map.put("name","山贼气喘吁吁");
        list.add(map);
        map = new HashMap<>();
        map.put("name","山贼躲避了");
        list.add(map);

        jsonStr = new HashMap<>();
        jsonStr.put("doAction", list);
        jsonStr.put("doAction", list);
        return jsonStr;
    }



    public  Map<String, Object> talkWithAll(JSONObject jsonObject) throws IOException {
        Map<String, Object> map = new HashMap<>();
        List list = new ArrayList<>();
        Map<String, Object> jsonStr = new HashMap<>();
        String msg = String.valueOf(jsonObject.get("msg"));
        map = new HashMap<>();
        list = new ArrayList<>();
        CharacterModel me = characterMapper.queryForOneById(chaId);
        map.put("code", "0");
        map.put("msg",msg);
        map.put("sendChaId",me.getId());
        map.put("sendChaName",me.getName());
        list.add(map);
        jsonStr.put("talkWithAll", list);
        return jsonStr;
    }

    public  Map<String, Object> talkWithOne(JSONObject jsonObject) throws IOException {
        Map<String, Object> map = new HashMap<>();
        List list = new ArrayList<>();
        Map<String, Object> jsonStr = new HashMap<>();
        CharacterModel me = characterMapper.queryForOneById(chaId);
        String chaId = String.valueOf(jsonObject.get("chaId"));
        String msg = String.valueOf(jsonObject.get("msg"));
        Session session = (Session) routeTable.get(chaId);
        if(session != null && session.isOpen()){
            map = new HashMap<>();
            list = new ArrayList<>();
            CharacterModel characterModel = characterMapper.queryForOneById(chaId);
            map.put("code", "0");
            map.put("msg",msg);
            map.put("sendChaId",me.getId());
            map.put("sendChaName",me.getName());
            map.put("receiveChaId",characterModel.getId());
            map.put("receiveChaName",characterModel.getName());
            list.add(map);
            jsonStr.put("oneWithTalk", list);
            session.getBasicRemote().sendText(JSONObject.toJSONString(jsonStr));
        }else{
            map = new HashMap<>();
            list = new ArrayList<>();
            map.put("code", "1");
            map.put("msg", "对方不在线");
            list.add(map);
        }
        jsonStr = new HashMap<>();
        jsonStr.put("talkWithOne", list);
        return jsonStr;
    }

    Map<String, Object> fight(JSONObject jsonObject) throws IOException {
        List<Map> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> jsonStr = new HashMap<>();

        list = new ArrayList<>();
        map = new HashMap<>();
        map.put("name","普通攻击");
        list.add(map);
        map = new HashMap<>();
        map.put("name","普通闪避");
        list.add(map);
        jsonStr = new HashMap<>();

        jsonStr.put("fight", list);
        return jsonStr;
    }


    Map<String, Object> action(JSONObject jsonObject) throws IOException {
        List<Map> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> jsonStr = new HashMap<>();

        list = new ArrayList<>();
        map = new HashMap<>();
        map.put("type","交易");
        map.put("name","交易");
        list.add(map);
        map = new HashMap<>();
        map.put("type","抢劫");
        map.put("name","抢劫");
        list.add(map);
        map = new HashMap<>();
        map.put("type","存取");
        map.put("name","存取");
        list.add(map);
        jsonStr = new HashMap<>();

        jsonStr.put("action", list);
        return jsonStr;
    }



}
