package xrb.dao;

import jedis.util.JedisPoolUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import redis.clients.jedis.Jedis;
import xrb.entry.Province;
import xrb.util.JDBCUtils;

import java.util.List;

/**
 * @author xieren8iao
 * @create 2019/3/25 - 9:57
 */
public class ProvinceDaoImpl implements ProvinceDao {
    private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public List<Province> findAll() {
        String sql="select * from province";
        List<Province> list = template.query(sql, new BeanPropertyRowMapper<Province>(Province.class));
        return list;
    }



}
