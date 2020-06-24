package com.github.bigbigzhan.domain;

import java.util.ArrayList;
import java.util.List;

public abstract class Cluster {

    public List<Node> nodes;

    public Cluster(){
        this.nodes = new ArrayList<>();
    }

    public abstract void addNode(Node node);

    public abstract void remove(Node node);

    public abstract Node get(String key);
}
