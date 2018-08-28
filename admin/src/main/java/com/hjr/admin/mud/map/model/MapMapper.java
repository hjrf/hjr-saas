package com.hjr.admin.mud.map.model;


import com.hjr.admin.mud.attr.model.AttrModel;
import com.hjr.admin.mud.room.model.MapModel;
import com.hjr.admin.mud.room.model.RoomModel;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import util.BaseMapper;

import java.util.List;

public class MapMapper extends BaseMapper{

    public MapMapper() {
        super(MapModel.class);
    }



    public List<MapModel> queryForListByCountryName(String countryName) {
        try {
            Query query = new Query();
            query.addCriteria(Criteria.where("countryName").is(countryName));
            return mongoTemplate.find(query,MapModel.class);
        }
        catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}