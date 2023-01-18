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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional(transactionManager = "adminTransactionManager")
public class MainUserService implements UserDetailsService,BasicServiceMethod<Admin>{

    @Override public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        log.info("  LOAD USER WITH THE USERNAME  "+username);
        Optional<Admin> byUserName = adminUserRepository.findByUserName(username);
        if (byUserName.isPresent()){
            Admin admin = byUserName.get();
            String userName = admin.getUserName();
            String password = admin.getPassword();
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(admin.getUserType().name()));
            return new User(userName,password,authorities);
        }
        return null;
    }

    @Override
    public Admin create(Admin object) {
        return adminUserRepository.save(object);
    }

    @Override
    public Admin updateById(Long id) {
        return null;
    }

    @Override
    public Admin update(Admin object,Long id){
        Optional<Admin> byId = adminUserRepository.findById(id);
        if (byId.isPresent()){
            object.setId(id);
           return adminUserRepository.save(object);
        }
        return object;
    }

    @Override
    public Boolean deleteById(Long id){
        adminUserRepository.deleteById(id);
        return true;
    }

    @Override
    public Boolean delete(Admin obejct) {
        adminUserRepository.delete(obejct);
        return true;
    }
    public Optional<Admin> findByUserName(String username){
        return adminUserRepository.findByUserName(username);
    }
    @Override
    public List<Admin> findAll(){
        return adminUserRepository.findAll();
    }
    @Override
    public Admin findById(Long id) {
        Admin admin = adminUserRepository.findById(id).get();
        return admin;
    }

    @Autowired
    private AdminUserRepository adminUserRepository;
}
