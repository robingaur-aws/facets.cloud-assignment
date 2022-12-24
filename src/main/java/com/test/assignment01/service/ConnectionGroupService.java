package com.test.assignment01.service;

import com.test.assignment01.entry.ConnectionGroupEntry;
import com.test.assignment01.handlers.exceptions.RestException;

public interface ConnectionGroupService {

    void create(ConnectionGroupEntry entry) throws RestException;

    ConnectionGroupEntry findByVirtualNodeId(String nodeId) throws RestException;
}
