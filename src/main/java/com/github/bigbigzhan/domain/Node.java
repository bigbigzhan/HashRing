package com.github.bigbigzhan.domain;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Node {
    //域名
    private String domainName;

    //ip
    private String ip;

    //数据
    private Map<String,Object> data;

    public Node(String domainName,String ip){
        this.ip = ip;
        this.domainName = domainName;
        this.data = new HashMap<>();
    }

    public <T> void put(String key,T value){
        data.put(key,value);
    }

    public <T> T remove(String key){
        return (T) data.remove(key);
    }

    public <T> T get(String key){
        return (T) data.get(key);
    }
}


