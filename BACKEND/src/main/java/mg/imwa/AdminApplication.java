package mg.imwa;
import mg.imwa.admin.model.Entity.*;
import mg.imwa.admin.model.Enum.CompanyStatus;
import mg.imwa.admin.model.Enum.DatabaseType;
import mg.imwa.admin.model.Enum.UserType;
import mg.imwa.admin.repository.AdminUserRepository;
import mg.imwa.admin.repository.CompanyRepository;
import mg.imwa.admin.service.CompanyService;
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
	public static void main(String[] args) {
		SpringApplication.run(AdminApplication.class, args);
	}
	@Override public void run(String... args) throws Exception{
		initAppAdministrator();
		initDefaultCompanydb();
	}

	private void initAppAdministrator(){
		String adminUsername = "armand_judicael";
		Optional<Admin> byUserName = adminUserRepository.findByUserName(adminUsername);
		if (byUserName.isEmpty()){
			Admin admin = new Admin();
			admin.setUserName(adminUsername);
			admin.setPassword("Aj!30071999");
//			String roles[] = {"SIMPLE_ADMIN","SUPER_ADMIN","MANAGER","ANALYST"};
//			List<Role> roleList = new ArrayList<>();
//			Arrays.stream(roles).forEach(s -> roleList.add(new Role(s)));
//			admin.setRoles(roleList);
			adminUserRepository.save(admin);
		}
	}

	private void initDefaultCompanydb(){

		TenantUser defaultDbAdmin = new TenantUser();
		defaultDbAdmin.setUserName(bCryptPasswordEncoder.encode("nirina"));
		defaultDbAdmin.setPassword(bCryptPasswordEncoder.encode("nirina"));
		defaultDbAdmin.setKey("default_company_db");
		defaultDbAdmin.setUserType(UserType.COMPANY_ADMIN);

		CompanyDataSourceConfig defaultcdc = new CompanyDataSourceConfig();
		defaultcdc.setPassword("root");
		defaultcdc.setUsername("postgres");
		defaultcdc.setHost("localhost");
		defaultcdc.setDatabaseName("default_company");
		defaultcdc.setPort("5432");
		defaultcdc.setDatabaseType(DatabaseType.POSTGRESQL);
		defaultcdc.setDriverClassName("org.postgresql.Driver");

		Company company = new Company();
		company.setAdmin(defaultDbAdmin);
		company.setCompanyStatus(CompanyStatus.ENABLED);
		company.setSlogan("MATIO : 7 : 7");
		company.setVerset("MATIO : 7 : 7");
		company.setNumTel("0340588519");
		company.setEmail("judicael.ratombotiana@gmail.com");
		company.setNom("default_company");
		company.setValidated(true);
		company.setCompanyDataSourceConfig(defaultcdc);

		companyService.create(company);
	}


	@Bean
	public BCryptPasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	public void setCompanyService(CompanyService companyService) {
		this.companyService = companyService;
	}

	private CompanyService companyService;
	private AdminUserRepository adminUserRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public void setAdminUserRepository(AdminUserRepository adminUserRepository) {
		this.adminUserRepository = adminUserRepository;
	}

	public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

}