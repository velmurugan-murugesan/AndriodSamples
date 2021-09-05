package com.example.velm.iot.model;

/**
 * Created by velmmuru on 8/21/2017.
 */

public class Favorites {

    private String nodeName;

    private String controllerName;

    private int nodeNumber;

    private String graphType;


    public Favorites(String nodeName, String controllerName, int nodeNumber, String graphType) {
        this.nodeName = nodeName;
        this.controllerName = controllerName;
        this.nodeNumber = nodeNumber;
        this.graphType = graphType;
    }

    public String getGraphType() {
        return graphType;
    }

    public void setGraphType(String graphType) {
        this.graphType = graphType;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getControllerName() {
        return controllerName;
    }

    public void setControllerName(String controllerName) {
        this.controllerName = controllerName;
    }

    public int getNodeNumber() {
        return nodeNumber;
    }

    public void setNodeNumber(int nodeNumber) {
        this.nodeNumber = nodeNumber;
    }


    @Override
    public String toString() {
        return "Favorites{" +
                "nodeName='" + nodeName + '\'' +
                ", controllerName='" + controllerName + '\'' +
                ", nodeNumber=" + nodeNumber +
                ", graphType='" + graphType + '\'' +
                '}';
    }
}
