package com.test.assignment01.entry;

import com.test.assignment01.handlers.exceptions.RestException;

import java.util.ArrayList;
import java.util.List;

public class VirtualNodeEntry {

    private String nodeId;
    private List<VirtualNodeEntry> children;

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) throws RestException {
        this.nodeId = nodeId;
    }

    public List<VirtualNodeEntry> getChildren() {
        if (children == null) {
            return new ArrayList<>();
        }
        return children;
    }

    public void setChildren(List<VirtualNodeEntry> children) {
        this.children = children;
    }
}
