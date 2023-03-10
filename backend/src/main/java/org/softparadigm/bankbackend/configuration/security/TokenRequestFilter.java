package org.softparadigm.bankbackend.configuration.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.softparadigm.bankbackend.configuration.jwt.JwtUtils;
import org.softparadigm.bankbackend.service.IAgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.softparadigm.bankbackend.configuration.jwt.SecurityConstants.AUTH_HEADER;

@Configuration
public class TokenRequestFilter extends OncePerRequestFilter {

    @Autowired
    private IAgentService agentService;

    @Autowired
    private JwtUtils jwtUtils;

    private static final Logger LOGGER = LoggerFactory.getLogger(TokenRequestFilter.class);

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        // Read token from header
        String token = request.getHeader(AUTH_HEADER);
        String username = null;

        if (token != null && token.trim().length() > 0) {
            token = token.replace("Bearer ", ""); // delete constants

            try {
                // Check token validity
                // Get username
                username = jwtUtils.parseUsername(token);

                // inject to security context
                UserDetails userDetails = agentService.loadUserByUsername(username);
                Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (Exception e) {
                LOGGER.debug(String.format("Parse token with errors: %s", e.getLocalizedMessage()));
            }
        }

        // continue
        filterChain.doFilter(request, response);
    }
}
