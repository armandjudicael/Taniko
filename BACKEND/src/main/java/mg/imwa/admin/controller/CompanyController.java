package mg.imwa.admin.controller;
import mg.imwa.admin.model.Entity.Company;
import mg.imwa.admin.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/imwa/v1")
public class CompanyController{
    @Autowired
    private CompanyService companyService;

    @PostMapping("/companies")
    public ResponseEntity<Object> create(@RequestBody Company company){
        Company company1 = companyService.create(company);
        return ResponseEntity.ok(company1);
    }
    @GetMapping("/companies")
    public ResponseEntity<Object> getAllCompany(){
        return new ResponseEntity<Object>(companyService.findAll(),HttpStatus.OK);
    }

    @GetMapping("/companies/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id){
        return new ResponseEntity<Object>(companyService.findById(id),HttpStatus.OK);
    }

    @PutMapping("/companies/{id}")
    public  ResponseEntity<Object> update(@RequestBody Company company,@PathVariable Long id){
        return new ResponseEntity<>(companyService.update(company,id),HttpStatus.OK);
    }
    @DeleteMapping("/companies/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id){
        return new ResponseEntity<>(companyService.deleteById(id),HttpStatus.OK);
    }
}
