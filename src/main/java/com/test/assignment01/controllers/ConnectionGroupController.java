package com.test.assignment01.controllers;

import com.test.assignment01.entry.ConnectionGroupEntry;
import com.test.assignment01.handlers.exceptions.RestException;
import com.test.assignment01.service.ConnectionGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/connection-group")
public class ConnectionGroupController {

    @Autowired
    private ConnectionGroupService service;

    @PostMapping
    public void create(@RequestBody ConnectionGroupEntry entry) throws RestException {
        service.create(entry);
    }

    @GetMapping("/node/{nodeId}")
    public ConnectionGroupEntry findByVirtualNodeId(@PathVariable String nodeId) throws RestException {
        return service.findByVirtualNodeId(nodeId);
    }
}
