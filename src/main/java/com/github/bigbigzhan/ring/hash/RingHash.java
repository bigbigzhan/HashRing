package com.github.bigbigzhan.ring.hash;

import com.github.bigbigzhan.domain.Cluster;
import com.github.bigbigzhan.domain.Node;

import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.IntStream;

public class RingHash  extends Cluster {

    private SortedMap<Integer,Node> virNodes = new TreeMap<>();

    private static  final  int VIR_NODE_COUNT = 512;

    private static final String SPLIT = "#";

    public RingHash(){
        super();
    }

    @Override
    public void addNode(Node node) {
        this.nodes.add(node);
        IntStream.range(0,VIR_NODE_COUNT).forEach(index->{
            Integer hash =getHash(node.getIp() + SPLIT + index);
            virNodes.put(hash,node);
        });
    }

    @Override
    public void remove(Node node) {
        nodes.removeIf(o->node.getIp().equals(o.getIp()));
        IntStream.range(0,VIR_NODE_COUNT).forEach(index->{
            int hash = getHash(node.getIp() + SPLIT + index);
            virNodes.remove(hash);
        });
    }

    @Override
    public Node get(String key) {
        int hash = getHash(key);
        //得到大于该Hash值的所有Map
        SortedMap<Integer, Node> subMap = virNodes.tailMap(hash);
        if(subMap.isEmpty()){
            //如果没有比该key的hash值大的，则从第一个node开始
            Integer i = virNodes.firstKey();
            //返回对应的服务器
            return virNodes.get(i);
        }else{
            //第一个Key就是顺时针过去离node最近的那个结点
            Integer i = subMap.firstKey();
            //返回对应的服务器
            return subMap.get(i);
        }

//
//        SortedMap<Integer,Node> subMap = hash <= virNodes.lastKey() ? virNodes.tailMap(0) : virNodes.tailMap(hash);
//        if(subMap.isEmpty()){
//            return null;
//        }
//
//        return subMap.get(subMap.firstKey());
    }

    private static int getHash(String str){
        final int p = 16777619;
        int hash = (int)2166136261L;
        for (int i = 0; i < str.length(); i++)
            hash = (hash ^ str.charAt(i)) * p;
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;

        // 如果算出来的值为负数则取其绝对值
        if (hash < 0)
            hash = Math.abs(hash);
        return hash;
    }

}
