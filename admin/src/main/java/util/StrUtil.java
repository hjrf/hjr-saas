package util;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hjr.admin.mud.JoinChaAttr.mapper.JoinChaAttrMapper;
import com.hjr.admin.mud.character.mapper.CharacterMapper;
import com.hjr.admin.system.user.model.UserModel;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class StrUtil {

	public static boolean checkNotNullAndEmpty(String str)
	{
		if(str == null || str.isEmpty() ||"".equals(str) || "null".equals(str))
		{
			return false;
		}
		else{
			return true;
		}
	}

}
