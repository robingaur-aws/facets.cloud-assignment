package com.test.assignment01.domain;

public class VirtualNodeDomain {

    private String connectionGroupId;
    private String nodeId;
    private String parentNodeId;

    public VirtualNodeDomain(String connectionGroupId, String nodeId, String parentNodeId) {
        this.connectionGroupId = connectionGroupId;
        this.nodeId = nodeId;
        this.parentNodeId = parentNodeId;
    }

    public String getConnectionGroupId() {
        return connectionGroupId;
    }

    public void setConnectionGroupId(String connectionGroupId) {
        this.connectionGroupId = connectionGroupId;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getParentNodeId() {
        return parentNodeId;
    }

    public void setParentNodeId(String parentNodeId) {
        this.parentNodeId = parentNodeId;
    }
}
