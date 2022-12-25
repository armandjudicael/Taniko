package mg.imwa.tenant.repository;

import mg.imwa.tenant.model.tenantEntityBeans.PersonPhysique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(transactionManager = "defaultTenantTxManager")
public interface PersonnePhysiqueRepository extends JpaRepository<PersonPhysique,Long>{
    @Query(value = "from PersonPhysique p join p.fonction f where lower(f.nomFonction)=:name")
    List<PersonPhysique> findAllByFunction(@Param("name") String name);
}
