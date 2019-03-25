package xrb.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jedis.util.JedisPoolUtils;
import redis.clients.jedis.Jedis;
import xrb.dao.ProvinceDao;
import xrb.dao.ProvinceDaoImpl;
import xrb.entry.Province;

import java.util.List;

/**
 * @author xieren8iao
 * @create 2019/3/25 - 9:58
 */
public class ProvinceServiveImpl implements ProvinceService {
        private ProvinceDao dao= new ProvinceDaoImpl();
    @Override
    public List<Province> findAll() {
        return dao.findAll();
    }


    //！！！注意使用redis缓存，但有增删改的操作时，需要把缓存全部清除重新添加，不然数据无法更新！！！！
    @Override
    public String findProvinceJson() {
        Jedis jedis = JedisPoolUtils.getJedis();
        String json_province = jedis.get("province");

        //判断缓存中是否有province数据
        if(json_province==null||json_province.length()==0){
            System.out.println("缓存中没有数据...查询数据库");
            List<Province> list = dao.findAll();
            ObjectMapper mapper=new ObjectMapper();
            try {
                json_province = mapper.writeValueAsString(list);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            jedis.set("province", json_province);
        }else {
            System.out.println("缓存中有数据");
        }
        return json_province;
    }
}
