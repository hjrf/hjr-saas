package com.hjr.admin.mud.room.model;


import com.hjr.admin.mud.attr.model.AttrModel;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import util.BaseMapper;

import java.util.List;

public class RoomMapper extends BaseMapper{

    public RoomMapper() {
        super(RoomModel.class);
    }

    public RoomModel queryForOneByMapNameAndXY(String mapName, String roomXY) {
        try {
            Query query = new Query();
            query.addCriteria(Criteria.where("mapName").is(mapName));
            query.addCriteria(Criteria.where("roomXY").is(roomXY));
            return mongoTemplate.findOne(query, RoomModel.class);
        }
        catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public List<RoomModel> queryForListByMapMame(String mapName) {
        try {
            Query query = new Query();
            query.addCriteria(Criteria.where("mapName").is(mapName));
            return mongoTemplate.find(query,RoomModel.class);
        }
        catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}