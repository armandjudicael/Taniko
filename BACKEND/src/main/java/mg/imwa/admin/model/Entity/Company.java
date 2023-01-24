package mg.imwa.admin.model.Entity;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;
import mg.imwa.admin.model.Enum.CompanyStatus;
import mg.imwa.tenant.model.tenantEntityBeans.Person;
import jakarta.persistence.*;
import java.sql.Blob;
import java.util.List;

@Data
@Entity
@SuperBuilder
public class Company extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  id;
    @Column(columnDefinition = "TEXT")
    private String verset;
    @Column(columnDefinition = "TEXT")
    private String slogan;
    @Enumerated(EnumType.ORDINAL)
    private CompanyStatus companyStatus = CompanyStatus.ENABLED;
    @OneToOne(cascade = CascadeType.PERSIST)
    private CompanyDataSourceConfig companyDataSourceConfig;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Filiale> filiales;
    private String validationKey;
    @OneToOne(cascade = CascadeType.PERSIST)
    private TenantUser admin;
    private boolean isValidated = true;

    public Company() {

    }

    @Override
    public String toString(){
        return "Company{" +
                "id=" + id +
                ", verset='" + verset + '\'' +
                ", slogan='" + slogan + '\'' +
                ", societeStatus=" + companyStatus +
                ", companyDataSourceConfig=" + companyDataSourceConfig +
                ", validationKey='" + validationKey + '\'' +
                ", isValidated=" + isValidated +
                '}';
    }

}