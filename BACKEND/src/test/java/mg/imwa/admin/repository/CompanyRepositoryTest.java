package mg.imwa.admin.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CompanyRepositoryTest{

    @Autowired
    private CompanyRepository companyRepository;

    @Test
    void findAllCompany(){
//        List<Company> companies = companyRepository.findAll();
//        Assertions.assertThat(companies).isNotEmpty();
//        companies.forEach(System.out::println);
    }

    @Test
    void findByName() {
    }

    @Test
    void databaseDontExist() {
    }

    @Test
    void testFindByName() {
    }

    @Test
    void testDatabaseDontExist() {
    }
}