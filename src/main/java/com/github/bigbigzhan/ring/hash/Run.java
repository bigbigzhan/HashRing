package com.github.bigbigzhan.ring.hash;

import com.github.bigbigzhan.domain.Cluster;
import com.github.bigbigzhan.domain.Node;

import java.util.stream.IntStream;

/**
 * 普通hash测试方法
 */
public class Run {

    private static int DATA_COUNT = 1000;
    private  static String PRE_KEY = "RING";

    public static void main(String[] args) {
        Cluster cluster = new RingHash();
        cluster.addNode(new Node("mobile1.abcash.in","172.138.0.1"));
        cluster.addNode(new Node("mobile2.abcash.in","172.138.0.2"));
        cluster.addNode(new Node("mobile3.abcash.in","172.138.0.3"));
        cluster.addNode(new Node("mobile4.abcash.in","172.138.0.4"));

        IntStream.range(0,DATA_COUNT).forEach(index->{
            String preKey = PRE_KEY + index;
            Node node = cluster.get(preKey);
            node.put(preKey,"Test");
        });

        //数据分布情况
        cluster.nodes.forEach(node ->{
            System.out.println("IP:" + node.getIp() + "数据量：" + node.getData().size());
        });

        NotAddAndRemoveTest(cluster, "无增删情况下命中率统计：");

      //  addOndNodeTest(cluster, "增加一个节点情况下命中率统计：");

        removeOndNodeTest(cluster, "删除一个节点情况下命中率统计：");


    }

    private static void NotAddAndRemoveTest(Cluster cluster, String s) {
        long count1 = IntStream.range(0, DATA_COUNT).filter(index -> cluster.get(PRE_KEY + index).get(PRE_KEY + index) != null).count();
        System.out.println(s + count1 * 1f / DATA_COUNT);
    }

    private static void addOndNodeTest(Cluster cluster, String s) {
        cluster.addNode(new Node("mobile5.abcash.in","172.138.0.5"));
        long count1 = IntStream.range(0, DATA_COUNT).filter(index -> cluster.get(PRE_KEY + index).get(PRE_KEY + index) != null).count();
        System.out.println(s + count1 * 1f / DATA_COUNT);
    }
    private static void removeOndNodeTest(Cluster cluster, String s) {
        cluster.remove(new Node("mobile4.abcash.in","172.138.0.4"));
        long count1 = IntStream.range(0, DATA_COUNT).filter(index -> cluster.get(PRE_KEY + index).get(PRE_KEY + index) != null).count();
        System.out.println(s + count1 * 1f / DATA_COUNT);
    }
}
