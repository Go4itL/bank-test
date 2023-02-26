package org.softparadigm.bankbackend.controller;

import org.softparadigm.bankbackend.dto.AgentDto;
import org.softparadigm.bankbackend.dto.AgentPayload;
import org.softparadigm.bankbackend.model.Agent;
import org.softparadigm.bankbackend.service.IAgentService;
import org.softparadigm.bankbackend.service.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/agents")
public class AgentController
        extends BaseController<Agent, String, AgentDto, AgentPayload> {
    @Autowired
    private IAgentService agentService;

    @Override
    public IBaseService<Agent, String, AgentDto, AgentPayload> getService() {
        return agentService;
    }
}
