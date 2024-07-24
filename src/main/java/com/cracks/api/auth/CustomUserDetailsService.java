package com.cracks.api.auth;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cracks.api.modelos.Session;
import com.cracks.api.modelos.User;
import com.cracks.api.repos.RepoSession;
import com.cracks.api.repos.RepoUser;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

        private RepoSession repoSession;
        private RepoUser repoUser;

        @Override
        public UserDetails loadUserByUsername(String nombre) {// } throws UsernameNotFoundException {

                User user = repoUser.findByName(nombre);
                Session session = repoSession.getPasswrdById(user.getId());
                String psw = session.getPasswrd();
                // .orElseThrow(() -> new UsernameNotFoundException("User not exists by Username
                // or Email"));

                // Set<GrantedAuthority> authorities = user.getRoles().stream()
                // .map((role) -> new SimpleGrantedAuthority(role.getName()))
                // .collect(Collectors.toSet());

                Set<GrantedAuthority> authorities = new HashSet<>();

                // Añadir manualmente las autoridades deseadas
                // if (nombre.equals("diego")){
                String role="ROLE_"+repoSession.getRole(user.getId());

                authorities.add(new SimpleGrantedAuthority(role));
                // }else{

                // authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
                // }

                return new org.springframework.security.core.userdetails.User(
                                nombre,
                                psw,
                                authorities);
        }
}