package mg.imwa.admin.model.Entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zaxxer.hikari.HikariDataSource;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mg.imwa.admin.model.Enum.DatabaseType;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Slf4j
@Table(name = "Company_datasource_config")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyDataSourceConfig implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String driverClassName;
    private String host;
    private String port;
    @Enumerated(EnumType.ORDINAL)
    private DatabaseType databaseType;
    private String databaseName;
    @Transient
    @JsonIgnore
    public HikariDataSource initDatasource(){
      String  url = "jdbc:"+databaseType.dbType2String()+"://"+host+":"+port+"/"+databaseName.toLowerCase();
      DataSourceProperties dsp = new DataSourceProperties();
      dsp.setUrl(url);
      dsp.setUsername(username);
      dsp.setPassword(password);
      dsp.setDriverClassName(driverClassName);
      log.info(" INIT DATASOURCE URL =  "+url);
      return dsp.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }
}
