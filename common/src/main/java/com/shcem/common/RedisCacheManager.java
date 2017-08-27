package com.shcem.common;

import com.shcem.constants.SystemDefine;
import redis.clients.jedis.HostAndPort;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by judysen on 2017/2/26.
 */
public class RedisCacheManager {

    private RedisCacheManager() {

    }
    /**获取RedisCache的实例化
     *
     * */
    public synchronized static IRedisCache GetRedisCache() {
        IRedisCache cache = null;
        Integer mode=0;
        String redismodel=YamlConfiguration.instance().getString(SystemDefine.RedisMode);
        if(!redismodel.equals("")){
            mode=Integer.parseInt(redismodel);
        }

        String hosts = YamlConfiguration.instance().getString(SystemDefine.RedisHost);

        if (hosts == null || hosts.equals("")){
            System.out.println("没有找到redis.host配置");
        }
        try{
            String[] hostes = hosts.split(",");
            if (hostes.length == 1&&mode==0) {
                HostAndPort hostAndPort=GetHostAndPort(hostes[0]);
                cache=new SingleRedisCache(hostAndPort.getHost(),hostAndPort.getPort());
            } else if (hostes.length >= 3&&mode==1) {
                Set<HostAndPort> hostAndPorts=new HashSet<HostAndPort>();
                for (String item:hostes){
                    HostAndPort hostAndPort=GetHostAndPort(item);
                    hostAndPorts.add(hostAndPort);
                }
                cache=new ClusterRedisCache(hostAndPorts);
            } else {
                System.out.println("redis.host配置有问题");
            }
        }catch (Exception ex){
            System.out.println("RedisCacheManager--GetRedisCache"+ex.toString());
        }

        return cache;
    }
    /**将Host字符串转为HostAndPort
     * @param host
     * */
    private static HostAndPort GetHostAndPort(String host) throws Exception
    {
        HostAndPort hostAndPort=null;
        String[] hostPorts=host.split(":");
        if(hostPorts.length==1)
        {
            hostAndPort=new HostAndPort(hostPorts[0],6379);
        }else if(hostPorts.length==2){
            hostAndPort=new HostAndPort(hostPorts[0],Integer.parseInt(hostPorts[1]));
        }else {
            throw new Exception("GetHostAndPort--Exception");
        }
        return hostAndPort;
    }
}
