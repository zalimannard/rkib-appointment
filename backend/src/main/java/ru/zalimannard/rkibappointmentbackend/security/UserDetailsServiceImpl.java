package ru.zalimannard.rkibappointmentbackend.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.zalimannard.rkibappointmentbackend.schema.person.Person;
import ru.zalimannard.rkibappointmentbackend.schema.person.PersonService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final PersonService personService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = personService.readEntityByUsername(username);
        if (person != null) {
            List<SimpleGrantedAuthority> authorities = new ArrayList<>(List.of(new SimpleGrantedAuthority(person.getId())));
            person.getRoles().forEach(personRole -> authorities.add(new SimpleGrantedAuthority(personRole.toString())));
            return new User(person.getUsername(), person.getPassword(), authorities);
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }

}