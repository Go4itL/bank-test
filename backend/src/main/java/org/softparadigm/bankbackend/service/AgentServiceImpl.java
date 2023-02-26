package org.softparadigm.bankbackend.service;

import org.hibernate.Hibernate;
import org.softparadigm.bankbackend.dto.AgentDto;
import org.softparadigm.bankbackend.dto.AgentPayload;
import org.softparadigm.bankbackend.model.Agent;
import org.softparadigm.bankbackend.repository.IAgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class AgentServiceImpl
        extends BaseService<Agent, String, AgentDto, AgentPayload>
        implements IAgentService {
    @Autowired
    private IAgentRepository agentRepository;

    @Override
    public JpaRepository<Agent, String> getRepository() {
        return agentRepository;
    }

    @Override
    public AgentDto getDto(@NonNull Agent model) {
        AgentDto agentDto = new AgentDto();
        agentDto.setLastName(model.getLastName());
        agentDto.setFirstName(model.getFirstName());
        agentDto.setUsername(model.getUsername());

        return agentDto;
    }

    @Override
    public Agent mapEntity(@NonNull AgentPayload payload) {
        Agent agent = new Agent();
        agent.setUsername(payload.getUsername());
        agent.setLastName(payload.getLastName());
        agent.setFirstName(payload.getFirstName());

        return agent;
    }

    @Override
    public Agent buildCriteria(@NonNull Map<String, String> filters) {
        Agent agent = new Agent();
        if (!filters.isEmpty()) {
            String firstNameFilter = filters.remove("firstName");
            if (firstNameFilter != null) {
                agent.setFirstName(firstNameFilter);
            }
            String lastNameFilter = filters.remove("lastName");
            if (lastNameFilter != null) {
                agent.setLastName(lastNameFilter);
            }
            String usernameFilter = filters.remove("username");
            if (usernameFilter != null) {
                agent.setUsername(usernameFilter);
            }
        }

        return agent;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(@NonNull String username) throws UsernameNotFoundException {
        Agent agent = agentRepository.findById(username).orElse(null);
        if (agent == null) {
            throw new UsernameNotFoundException(String.format("Agent not found [username= %s]", username));
        }

        // initialize roles
        Hibernate.initialize(agent.getRoles());
        return agent;
    }
}
