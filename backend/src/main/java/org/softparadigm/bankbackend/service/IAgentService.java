package org.softparadigm.bankbackend.service;

import org.softparadigm.bankbackend.dto.AgentDto;
import org.softparadigm.bankbackend.dto.AgentPayload;
import org.softparadigm.bankbackend.model.Agent;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IAgentService extends IBaseService<Agent, String, AgentDto, AgentPayload>, UserDetailsService {
}
