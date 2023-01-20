package mg.imwa.admin.controller;
import mg.imwa.admin.model.Entity.Admin;
import mg.imwa.admin.service.MainUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
@Controller
@RequestMapping("/api/imwa/v1")
public class AdminUserController implements BasicControllerMethod<Admin>{
    @Autowired
    private MainUserService mainUserService;
    @Override
    @PostMapping("/admin-user")
    public ResponseEntity<Object> create(@RequestBody Admin admin){
        return new ResponseEntity<>(mainUserService.create(admin),HttpStatus.CREATED);
    }
    @Override
    @GetMapping("/admin-user")
    public ResponseEntity<Object> findAll() {
        return new ResponseEntity<>(mainUserService.findAll(),HttpStatus.OK);
    }

    @Override
    @PutMapping("/admin-user/{id}")
    public ResponseEntity<Object> updateById(@RequestBody Admin newEntity, @PathVariable Long id){
        return new ResponseEntity<>(mainUserService.update(newEntity,id),HttpStatus.OK);
    }

    @Override
    @GetMapping("/admin-user/{id}")
    public ResponseEntity<Object> findById( @PathVariable Long id) {
        return new ResponseEntity<>(mainUserService.findById(id),HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/admin-user/{id}")
    public ResponseEntity<Boolean> delete( @PathVariable  Long id) {
        return new ResponseEntity<>(mainUserService.deleteById(id),HttpStatus.OK);
    }

}
