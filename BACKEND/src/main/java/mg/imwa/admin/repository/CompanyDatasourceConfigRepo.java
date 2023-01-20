package mg.imwa.admin.repository;
import mg.imwa.admin.model.Entity.CompanyDataSourceConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CompanyDatasourceConfigRepo extends JpaRepository<CompanyDataSourceConfig,Long>{}
