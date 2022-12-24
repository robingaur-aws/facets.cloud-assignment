package com.test.assignment01.entry;

import com.test.assignment01.domain.ConnectionGroupDomain;
import com.test.assignment01.handlers.exceptions.RestException;

public class ConnectionGroupEntry {

    private String id;
    private String name;
    private VirtualNodeEntry virtualNode;

    public ConnectionGroupEntry() {
    }

    public ConnectionGroupEntry(ConnectionGroupDomain domain) {
        id = domain.getId();
        name = domain.getName();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws RestException {
        this.name = name;
    }

    public VirtualNodeEntry getVirtualNode() {
        return virtualNode;
    }

    public void setVirtualNode(VirtualNodeEntry virtualNode) {
        this.virtualNode = virtualNode;
    }
}
