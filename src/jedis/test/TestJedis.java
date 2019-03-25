package jedis.test;

import jedis.util.JedisPoolUtils;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Map;
import java.util.Set;

/**
 * @author xieren8iao
 * @create 2019/3/25 - 8:07
 */
public class TestJedis {
    @Test//空参构造默认主机为localhost，端口号为6379
    public void test1(){
        //1.获取连接
        Jedis jedis=new Jedis("localhost",6379);
        //2.操作
        jedis.set("username", "zhangsan");
        jedis.hset("myhash", "username", "lisi");
        //3.关闭资源
        jedis.close();


    }
    //操作字符串的
    @Test
    public void test2(){
        //1.获取连接
        Jedis jedis=new Jedis("localhost",6379);
        //2.操作
        String username = jedis.get("username");
        System.out.println(username);

        //通过setex设置在指定时间内删除键值
        jedis.setex("password", 15, "123");
        //3.关闭资源
        jedis.close();


    }
    //操作hash结构
    @Test
    public void test3(){
        //1.获取连接
        Jedis jedis=new Jedis("localhost",6379);
        //2.操作
        //存储hash
        jedis.hset("myhash", "name", "wangwy");
        jedis.hset("myhash", "password", "123");
        jedis.hset("myhash", "gender", "男");

        //取值
//        String password = jedis.hget("myhash", "password");
//        System.out.println(password);
        Map<String, String> myhash = jedis.hgetAll("myhash");
        Set<String> keySet = myhash.keySet();
        for (String key : keySet) {
            String value = myhash.get(key);
            System.out.println(key+": "+value);
        }


        //3.关闭资源
        jedis.close();
    }
    //操作list结构
    @Test
    public void test4(){
        //1.获取连接
        Jedis jedis=new Jedis("localhost",6379);
        //2.操作
//        jedis.lpush("mylist", "a","b", "c");
//        jedis.rpush("mylist", "a", "b", "c");
//        范围获取
//        System.out.println(jedis.lrange("mylist", 0, -1));

        //list弹出
        jedis.lpop( "mylist");
        jedis.rpop("mylist");
        System.out.println(jedis.lrange("mylist", 0, -1));

        //list删除
//        jedis.del("mylist");
        //3.关闭资源
        jedis.close();


    }
    //操作set结构
    @Test
    public void test5(){
        //1.获取连接
        Jedis jedis=new Jedis("localhost",6379);
        //2.操作
        //添加
        jedis.sadd("language", "java", "php", "javascript");

        //获取
        Set<String> smembers = jedis.smembers("language");
        System.out.println(smembers);
        //3.关闭资源
        jedis.close();


    }
    //操作sortedset结构
    @Test
    public void test6(){
        //1.获取连接
        Jedis jedis=new Jedis("localhost",6379);
        //2.操作
        //添加
        jedis.zadd("mysort",  100, "java");
        jedis.zadd("mysort",  200, "c");
        jedis.zadd("mysort",  50, "js");
        jedis.zadd("mysort",  40, "java");

        //获取
        Set<String> mysort = jedis.zrange("mysort", 0, -1);
        System.out.println(mysort);
        //3.关闭资源
        jedis.close();


    }
    //jedis连接池
    @Test
    public void test7(){
        //1.创建Jedis的连接池对象
//        JedisPool jedisPool=new JedisPool();

        //配置连接池
        JedisPoolConfig jedisPoolConfig=new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(50);//最大连接数
        jedisPoolConfig.setMaxIdle(10);//空闲连接数

        JedisPool jedisPool=new JedisPool(jedisPoolConfig,"localhost",6379);
        //2.获取连接
        Jedis jedis=jedisPool.getResource();

        jedis.set("qq", "754885314");
        String qq = jedis.get("qq");
        System.out.println(qq);

        //关闭资源，将资源返还到连接池
        jedis.close();


    }
    //通过连接池工具类获取
    @Test
    public void test8(){
        //获取jedis
        Jedis jedis = JedisPoolUtils.getJedis();

        //操作
        jedis.hset("myset", "email", "xieren8iao@163.com");
        jedis.hset("myset", "qq", "754885314");
        Map<String, String> myset = jedis.hgetAll("myset");
        System.out.println(myset);

        //关闭资源，将资源返还到连接池
        jedis.close();


    }
}
