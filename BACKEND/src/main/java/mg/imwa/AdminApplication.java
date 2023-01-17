package mg.imwa;
import com.zaxxer.hikari.HikariDataSource;
import mg.imwa.admin.model.Entity.*;
import mg.imwa.admin.model.Enum.CompanyStatus;
import mg.imwa.admin.model.Enum.DatabaseType;
import mg.imwa.admin.model.Enum.UserType;
import mg.imwa.admin.service.AdminUserService;
import mg.imwa.admin.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
		initErp();
	}

	private void initErp(){
		String adminUsername = "armand_judicael";
		Optional<Admin> byUserName = adminUserService.findByUserName(adminUsername);
		if (byUserName.isEmpty()){
			Admin admin = new Admin();
			admin.setUserName(adminUsername);
			admin.setPassword("Aj!30071999");
			adminUserService.create(admin);
			initDefaultCompanydb();
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
		defaultcdc.setDatabaseName("default_company_db");
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

		companyService.initializeAndSave(company);
	}



	@Autowired
	private CompanyService companyService;
	@Autowired
	private AdminUserService adminUserService;


	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

}