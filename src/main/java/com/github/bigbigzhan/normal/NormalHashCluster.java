package com.github.bigbigzhan.normal;

import com.github.bigbigzhan.domain.Cluster;
import com.github.bigbigzhan.domain.Node;

import static java.util.Objects.hash;

public class NormalHashCluster extends Cluster {

    @Override
    public void addNode(Node node) {
        this.nodes.add(node);
    }

    @Override
    public void remove(final Node node) {
        this.nodes.removeIf(r -> r.getIp().equals(node.getIp()) || r.getDomainName().equals(node.getDomainName()));
    }

    @Override
    public Node get(String key) {
        int hash = hash(key);
        int index = hash % nodes.size();
        if(index < 0){
            index = Math.abs(index);
        }
        return nodes.get(index);
    }
}
