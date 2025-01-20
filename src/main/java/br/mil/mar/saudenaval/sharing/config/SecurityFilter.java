package br.mil.mar.saudenaval.sharing.config;

import br.mil.mar.saudenaval.sharing.repositories.PlanoDoDiaRepository;
import br.mil.mar.saudenaval.sharing.repositories.UserRepository;
import br.mil.mar.saudenaval.sharing.services.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository repository;

    @Autowired
    PlanoDoDiaRepository planoDoDiaRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recoverToken(request);

        if (token != null){
            var subject = tokenService.validateToken(token);
            if(!subject.isEmpty()){
                UserDetails user = repository.findByUsername(subject);
                var authentication = new UsernamePasswordAuthenticationToken(user, null,user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }


        }
        filterChain.doFilter(request,response);
    }

    private String recoverToken(HttpServletRequest request) {

       var authHeader = request.getHeader("Authorization");

        if (authHeader == null){
            return null;
        }else{
            return authHeader;
        }

    }



}
