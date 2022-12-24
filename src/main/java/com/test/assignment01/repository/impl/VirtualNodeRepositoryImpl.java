package com.test.assignment01.repository.impl;

import com.test.assignment01.domain.ConnectionGroupDomain;
import com.test.assignment01.domain.VirtualNodeDomain;
import com.test.assignment01.handlers.exceptions.RestException;
import com.test.assignment01.repository.VirtualNodeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class VirtualNodeRepositoryImpl implements VirtualNodeRepository {

    private static Map<String, ConnectionGroupDomain> connectionGroups;
    private static Map<String, VirtualNodeDomain> virtualNodes;

    static {
        connectionGroups = new HashMap<>();
        virtualNodes = new HashMap<>();
    }

    @Override
    public String createConnectionGroup(String connectionGroupName) throws RestException {
        ConnectionGroupDomain domain = new ConnectionGroupDomain(connectionGroupName);
        if (connectionGroups.containsKey(domain.getId())) {
            throw new RestException(HttpStatus.UNPROCESSABLE_ENTITY, "Connection Group Already Exists");
        }
        connectionGroups.put(domain.getId(), domain);
        return domain.getId();
    }

    @Override
    public void addNode(String connectionGroupId, String nodeId, String parentNodeId) throws RestException {
        validateAddNode(connectionGroupId, nodeId, parentNodeId);
        VirtualNodeDomain domain = new VirtualNodeDomain(connectionGroupId, nodeId, parentNodeId);
        virtualNodes.put(domain.getNodeId(), domain);
    }

    @Override
    public ConnectionGroupDomain findByConnectionGroupId(String connectionGroupId) {
        return connectionGroups.getOrDefault(connectionGroupId, null);
    }

    @Override
    public VirtualNodeDomain findByNodeId(String nodeId) {
        return virtualNodes.getOrDefault(nodeId, null);
    }

    @Override
    public void deleteConnectionGroup(String connectionGroupId) {
        // check if any nodes exist in connection group
        connectionGroups.remove(connectionGroupId);
    }

    @Override
    public void removeNode(String nodeId) {
        virtualNodes.remove(nodeId);
    }

    private void validateAddNode(String connectionGroupId, String nodeId, String parentNodeId) throws RestException {
        if (!connectionGroups.containsKey(connectionGroupId)) {
            throw new RestException(HttpStatus.UNPROCESSABLE_ENTITY, "Connection Group Does Not Exists");
        } else if (virtualNodes.containsKey(nodeId)) {
            throw new RestException(HttpStatus.UNPROCESSABLE_ENTITY, "Virtual Node Already Exists");
        } else if (parentNodeId != null && !virtualNodes.containsKey(parentNodeId)) {
            throw new RestException(HttpStatus.UNPROCESSABLE_ENTITY, "Parent Virtual Node Does Not Exists");
        }
    }
}
