package mg.imwa.admin.repository;

import mg.imwa.admin.model.Entity.Admin;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AdminUserRepositoryTest {

    @Autowired
    private AdminUserRepository adminUserRepository;

    @Test
    void findByUserName() {
        Admin admin = adminUserRepository.findByUserName("armand_judicael").get();
        Assertions.assertThat(admin).isNotNull();
    }

    @Test
    void findByUserNameAndPassword() {
    }
}