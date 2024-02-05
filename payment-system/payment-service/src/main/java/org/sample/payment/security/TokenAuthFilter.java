package org.sample.payment.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.sample.payment.dao.entity.TokenEntity;
import org.sample.payment.exception.InvalidTokenException;
import org.sample.payment.message.LocaleMessageResource;
import org.sample.payment.service.auth.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

/**
 * @author Atousa Mirhosseini
 * @since 05 Feb, 2024
 */
@RequiredArgsConstructor
public class TokenAuthFilter extends OncePerRequestFilter {

    public static final String BEARER_TOKEN = "Bearer ";
    @Autowired
    private TokenService tokenService;

    @Autowired
    private LocaleMessageResource messageResource;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String token = null;
        boolean emptyAuth = emptyAuth(authHeader);
        boolean requiredAuth = !isUnathorizedPath(requestPath(request));
        if (requiredAuth) {
            if (emptyAuth) {
                throw new InvalidTokenException("21", messageResource.getMessage("auth.token.invalid"));
            } else {
                token = authHeader.substring(BEARER_TOKEN.length());
                Optional<TokenEntity> tokenEntity = tokenService.findTokenEntity(token);
                if (tokenEntity.isPresent()) {
                    SecurityUserDetails userDetails = new SecurityUserDetails(tokenEntity.get().getUser().getUsername(), tokenEntity.get().getUser().getPassword());
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                } else {
                    throw new InvalidTokenException("21", messageResource.getMessage("auth.token.invalid"));
                }
            }
        }
        filterChain.doFilter(request, response);
    }

    private String requestPath(HttpServletRequest request) {
        return request.getRequestURI().substring(request.getContextPath().length());
    }

    private boolean emptyAuth(String authHeader) {
        return authHeader == null || !authHeader.startsWith(BEARER_TOKEN);
    }

    private boolean isUnathorizedPath(String requestPath) {

        return Arrays.stream(SecurityConfig.unauthorizedPaths).anyMatch(path -> (new AntPathMatcher()).match(path, requestPath));
    }
}
