package com.test.assignment01.service.impl;

import com.test.assignment01.domain.ConnectionGroupDomain;
import com.test.assignment01.domain.VirtualNodeDomain;
import com.test.assignment01.entry.ConnectionGroupEntry;
import com.test.assignment01.entry.VirtualNodeEntry;
import com.test.assignment01.handlers.exceptions.RestException;
import com.test.assignment01.repository.VirtualNodeRepository;
import com.test.assignment01.service.ConnectionGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Service
public class ConnectionGroupServiceImpl implements ConnectionGroupService {

    @Autowired
    private VirtualNodeRepository repository;

    @Override
    public void create(ConnectionGroupEntry entry) throws RestException {
        validateConnectionGroupObject(entry);
        String groupId = createConnectionGroup(entry.getName());
        addNodes(groupId, entry.getVirtualNode());
    }

    @Override
    public ConnectionGroupEntry findByVirtualNodeId(String nodeId) throws RestException {
        VirtualNodeDomain domain = repository.findByNodeId(nodeId);
        if (domain == null) {
            throw new RestException(HttpStatus.NOT_FOUND, "Virtual Node does not Exists");
        }

        ConnectionGroupDomain groupDomain = repository.findByConnectionGroupId(domain.getConnectionGroupId());
        return new ConnectionGroupEntry(groupDomain);
    }

    private String createConnectionGroup(String connectionGroupName) throws RestException {
        return repository.createConnectionGroup(connectionGroupName);
    }

    private void addNodes(String groupId, VirtualNodeEntry nodes) throws RestException {
        if (nodes == null) {
            return;
        }

        List<String> nodesAdded = new LinkedList<>();
        Queue<VirtualNodeEntry> nodesToBeProcessed = new LinkedList<>();

        try {
            validateVirtualNodeObject(nodes);
            nodesToBeProcessed.add(nodes);
            repository.addNode(groupId, nodes.getNodeId(), null);
            nodesAdded.add(nodes.getNodeId());

            while (!nodesToBeProcessed.isEmpty()) {
                VirtualNodeEntry parentNode = nodesToBeProcessed.remove();

                for (VirtualNodeEntry node : parentNode.getChildren()) {
                    validateVirtualNodeObject(node);
                    nodesToBeProcessed.add(node);
                    repository.addNode(groupId, node.getNodeId(), parentNode.getNodeId());
                    nodesAdded.add(node.getNodeId());
                }
            }
        } catch (Exception exception) {
            for (String nodeId : nodesAdded) {
                repository.removeNode(nodeId);
            }
            repository.deleteConnectionGroup(groupId);
            throw exception;
        }
    }

    private void validateConnectionGroupObject(ConnectionGroupEntry entry) throws RestException {
        if (entry.getName() == null || entry.getName().isEmpty()) {
            throw new RestException(HttpStatus.UNPROCESSABLE_ENTITY, "Connection Group Name not found");
        }
    }

    private void validateVirtualNodeObject(VirtualNodeEntry entry) throws RestException {
        if (entry.getNodeId() == null || entry.getNodeId().isEmpty()) {
            throw new RestException(HttpStatus.UNPROCESSABLE_ENTITY, "Virtual Node Id not found");
        }
    }
}
