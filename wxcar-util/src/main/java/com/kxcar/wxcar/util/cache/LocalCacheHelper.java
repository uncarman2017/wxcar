package com.kxcar.wxcar.util.cache;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Created by jiaweiyu on 2016/10/3.
 */
public class LocalCacheHelper<K,V>
{
    private static final ConcurrentHashMap<Object,Object> map = new ConcurrentHashMap<Object,Object>();

    private LocalCacheHelper(){}

    private static class SingletonHolder
    {
        private static final LocalCacheHelper instance = new LocalCacheHelper();
    }

    public static final LocalCacheHelper getInstance()
    {
        return SingletonHolder.instance;
    }

    public V get(K key)
    {
        return (V)map.get(key);

    }
    public V getAndAdd(K key,V entity)
    {
        return (V)map.putIfAbsent(key,entity);

    }

    public void add(K key,V entity)
    {
        map.put(key,entity);
    }

    public void remove(K key)
    {
        map.remove(key);
    }


    public void update(K key,V entity)
    {
        map.replace(key, entity);
    }

    public int getItemCount()
    {
        return map.size();
    }




}
