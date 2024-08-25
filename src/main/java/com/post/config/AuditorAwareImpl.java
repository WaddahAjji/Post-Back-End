package com.post.config;

import com.post.security.UserDetailsImpl;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        String username="system";
        if(SecurityContextHolder.getContext().getAuthentication()!=null)
        {
            Object principle = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principle.toString() != "anonymousUser") {
                username = ((UserDetailsImpl) principle).getUsername();

            }
        }

        return Optional.of(username);
    }
}
