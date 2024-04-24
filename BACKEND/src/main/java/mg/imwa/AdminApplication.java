package mg.imwa;
import lombok.extern.slf4j.Slf4j;
import mg.imwa.admin.model.Entity.*;
import mg.imwa.admin.model.Enum.CompanyStatus;
import mg.imwa.admin.model.Enum.DatabaseType;
import mg.imwa.admin.model.Enum.UserType;
import mg.imwa.admin.service.MainUserService;
import mg.imwa.admin.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

@SpringBootApplication(scanBasePackages ={
		"mg.imwa.config.dbConfig",
		"mg.imwa.config.securityConfig",

		"mg.imwa.admin.model",
		"mg.imwa.admin.repository",
		"mg.imwa.admin.service",
		"mg.imwa.admin.controller",

		"mg.imwa.tenant.model",
		"mg.imwa.tenant.repository",
		"mg.imwa.tenant.controller",
		"mg.imwa.tenant.service"
},exclude = {DataSourceAutoConfiguration.class})
@Slf4j
public class AdminApplication implements CommandLineRunner{
	public static void main(String[] args) {
		SpringApplication.run(AdminApplication.class, args);
	}
	@Override public void run(String... args) throws Exception{
		initErp();
	}
	private void initErp(){
		String adminUsername = "armand_judicael";
		Optional<Admin> byUserName = mainUserService.findByUserName(adminUsername);
		if (byUserName.isEmpty()){
		    Admin admin = Admin.builder().password("Aj!30071999").userType(UserType.SUPER_ADMIN).userName(adminUsername).build();
			mainUserService.create(admin);
			initDefaultCompanydb();
		}
	}

	private void initDefaultCompanydb(){

		TenantUser defaultCompanyDb = TenantUser.builder().key("default_company_db").userName("nirina").password("nirina").build();

		CompanyDataSourceConfig defaultcdc = CompanyDataSourceConfig.builder()
				.password("Aj!30071999")
				.username("postgres")
				.host("localhost")
				.port("5432").databaseType(DatabaseType.POSTGRESQL)
				.driverClassName("org.postgresql.Driver")
				.build();

		Company company = Company.builder()
				.admin(defaultCompanyDb)
				.companyStatus(CompanyStatus.ENABLED)
				.slogan("MATIO : 7 : 7")
				.verset("MATIO : 7 : 7")
				.numTel("0340588519")
				.email("judicael.ratombotiana@gmail.com")
				.nom("default")
				.isValidated(true)
				.companyDataSourceConfig(defaultcdc)
				.build();

		log.info("  ====== INITIALIZE DEFAULT COMPANY DATABASE =======  ");
		companyService.initializeAndSave(company);
	}
	@Autowired
	private CompanyService companyService;
	@Autowired
	private MainUserService mainUserService;

}