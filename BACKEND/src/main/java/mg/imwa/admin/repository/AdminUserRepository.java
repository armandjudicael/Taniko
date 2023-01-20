package mg.imwa.admin.repository;

import mg.imwa.admin.model.Entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Repository
public interface AdminUserRepository extends JpaRepository<Admin,Long>{
    @Query(value = "from Admin a where a.userName=:username")
    Optional<Admin> findByUserName(@Param("username") String username);
    @Query(value = "from Admin a where a.userName=:username and a.password=:password")
    Optional<Admin> findByUserNameAndPassword(@Param("username") String username,@Param("password") String password);
}
