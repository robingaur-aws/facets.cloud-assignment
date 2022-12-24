package com.test.assignment01.repository;

import com.test.assignment01.domain.ConnectionGroupDomain;
import com.test.assignment01.domain.VirtualNodeDomain;
import com.test.assignment01.handlers.exceptions.RestException;

public interface VirtualNodeRepository {

    String createConnectionGroup(String connectionGroupName) throws RestException;

    void addNode(String connectionGroupId, String nodeId, String parentNodeId) throws RestException;

    ConnectionGroupDomain findByConnectionGroupId(String connectionGroupId);

    VirtualNodeDomain findByNodeId(String nodeId);

    void deleteConnectionGroup(String connectionGroupId);

    void removeNode(String nodeId);
}
