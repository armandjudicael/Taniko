package mg.imwa.admin.service;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import mg.imwa.admin.model.Entity.Company;
import mg.imwa.admin.model.Entity.CompanyDataSourceConfig;
import mg.imwa.admin.repository.CompanyDatasourceConfigRepo;
import mg.imwa.admin.repository.CompanyRepository;
import mg.imwa.config.MapMultiTenantConnectionProvider;
import org.apache.commons.lang3.RandomStringUtils;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.output.MigrateResult;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.sql.*;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
@Transactional(transactionManager = "adminTransactionManager")
public class CompanyService implements BasicServiceMethod<Company> {
    private final String[] emailTab = {
            "dinokamisy@gmail.com"};
    @Override
    public Company create(Company company){
        String dbName = company.getCompanyDataSourceConfig().getDatabaseName();
        if(databaseDontExistOnPgServer(dbName) && databaseDontExist(dbName)){
                try {
                    executeNativeQuery("CREATE DATABASE "+dbName+";");
                } catch (SQLException e){
                    throw new RuntimeException(e);
                }
                return null;
        }
        return initializeAndSave(company);
    }

    public Company initializeAndSave(Company company){
        CompanyDataSourceConfig cdc = company.getCompanyDataSourceConfig();
        HikariDataSource hikariDataSource = cdc.initDatasource();
        executeFlywayMigration(hikariDataSource);
        String validationKey = generateValidationKey(company.getNom());
        company.setValidationKey(validationKey);
        return companyRepository.save(company);
    }

    public Company initializeAndSave(Company company,HikariDataSource hikariDataSource) {
        executeFlywayMigration(hikariDataSource);
        String validationKey = generateValidationKey(company.getNom());
        company.setValidationKey(validationKey);
        return companyRepository.save(company);
    }

    @Override
    public Company updateById(Long id) {
        return null;
    }
    @Override
    public Boolean deleteById(Long id) {
        Optional<Company> byId = companyRepository.findById(id);
       if (byId.isPresent()){
           Company company = byId.get();
           String companyName = company.getNom();
           //  GET THE CONNECTION PROVIDER RELATED TO THE DATABASE
           ConnectionProvider connectionProvider = mapMultiTenantConnectionProvider.getConnectionProviderMap().get(companyName);
           if (connectionProvider!=null){
               try {
                   Connection dbConnection = connectionProvider.getConnection();
                   if (!dbConnection.isClosed()){
                       // CLOSE THE CONNECTION
                       dbConnection.close();
                   }
               } catch (SQLException e) {
                   throw new RuntimeException(e);
               }
           }
           String databaseName = company.getCompanyDataSourceConfig().getDatabaseName();
           try {
               executeNativeQuery("DROP DATABASE "+databaseName);
           } catch (SQLException e) {
               throw new RuntimeException(e);
           }
           // DELETE THE COMPANY ON THE DB
           companyRepository.delete(company);

           return databaseDontExist(databaseName);
        }
        return false;
    }

    @Override
    public Boolean delete(Company obejct) {
        return deleteById(obejct.getId());
    }

    @Override
    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    @Override
    public Optional<Company> findById(Long id) {
        return companyRepository.findById(id);
    }
    @Override
    public Company update(Company company,Long id){
        Optional<Company> byId = companyRepository.findById(id);
        if (byId.isPresent()){
            company.setId(id);
            return companyRepository.save(company);
        }
        return company;
    }

    private String generateValidationKey(String companyName){
        String key = RandomStringUtils.randomAlphanumeric(30);
        String kaelKey = key.substring(0,9);
        String brianKey = key.substring(10,19);
        String ddsKey = key.substring(20,30);
        sendEmail(new String[]{kaelKey,brianKey,ddsKey},companyName);
        return key;
    }

    private void sendEmail(String[] keyTab , String companyName){
        for (int i = 0; i < keyTab.length; i++) {
          // emailService.sendEmail(emailTab[i],"Clé d'activation de la societé "+companyName+" : "+keyTab[i]," Activation de la societé "+companyName);
        }
    }
    public static void executeFlywayMigration(HikariDataSource dataSource){
            Flyway flyway = Flyway.configure().dataSource(dataSource).load();
            MigrateResult migrate = flyway.migrate();
            dataSource.close();
    }
    private void executeNativeQuery(String query) throws SQLException {
        Connection connection = Objects.requireNonNull(entityManagerFactory.getDataSource()).getConnection();
        connection.createStatement().execute(query);
    }
    private Boolean databaseDontExist(String dbname){
        return companyDatasourceConfigRepo.findAll().stream().noneMatch(companyDataSourceConfig -> companyDataSourceConfig.getDatabaseName().equals(dbname));
    }
    private Boolean databaseDontExistOnPgServer(String dbname){
        return companyRepository.databaseDontExistOnPgServer(dbname)==0;
    }
    //private EmailService emailService;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private CompanyDatasourceConfigRepo companyDatasourceConfigRepo;
    @Autowired
    private LocalContainerEntityManagerFactoryBean entityManagerFactory;
    @Autowired
    private MapMultiTenantConnectionProvider mapMultiTenantConnectionProvider;

}