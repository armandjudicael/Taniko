package mg.imwa.admin.service;
import lombok.extern.slf4j.Slf4j;
import mg.imwa.admin.model.Entity.Admin;
import mg.imwa.admin.repository.AdminUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
@Slf4j
public class AdminUserService implements UserDetailsService{
    @Autowired
    private AdminUserRepository adminUserRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Optional<Admin> byUserName = adminUserRepository.findByUserName(username);
        if (byUserName.isPresent()){
            Admin admin = byUserName.get();
            String userName = admin.getUserName();
            String password = admin.getPassword();
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            admin.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
            return new User(userName,password,authorities);
        }
        return null;
    }
}
