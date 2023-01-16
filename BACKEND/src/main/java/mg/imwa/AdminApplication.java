package mg.imwa;
import mg.imwa.admin.model.Entity.Admin;
import mg.imwa.admin.model.Entity.Role;
import mg.imwa.admin.repository.AdminUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootApplication(scanBasePackages ={
		"mg.imwa.config",

		"mg.imwa.admin.model",
		"mg.imwa.admin.repository",
		"mg.imwa.admin.service",
		"mg.imwa.admin.controller",

		"mg.imwa.tenant.model",
		"mg.imwa.tenant.repository",
		"mg.imwa.tenant.controller",
		"mg.imwa.tenant.service"
},exclude = {DataSourceAutoConfiguration.class})
public class AdminApplication implements CommandLineRunner{
	@Autowired private AdminUserRepository adminUserRepository;
	public static void main(String[] args) {
		SpringApplication.run(AdminApplication.class, args);
	}
	@Override public void run(String... args) throws Exception{
		String adminUsername = "armand_judicael";
		Optional<Admin> byUserName = adminUserRepository.findByUserName(adminUsername);
		if (byUserName.isEmpty()){
			Admin admin = new Admin();
			admin.setUserName(adminUsername);
			admin.setPassword("Aj!30071999");
			String roles[] = {"SIMPLE_ADMIN","SUPER_ADMIN","MANAGER","ANALYST"};
			List<Role> roleList = new ArrayList<>();
			Arrays.stream(roles).forEach(s -> roleList.add(new Role(s)));
			admin.setRoles(roleList);
			adminUserRepository.save(admin);
		}
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
}