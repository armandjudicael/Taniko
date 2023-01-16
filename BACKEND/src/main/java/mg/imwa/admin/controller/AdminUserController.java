package mg.imwa.admin.controller;
import mg.imwa.admin.model.Entity.Admin;
import mg.imwa.admin.service.AdminUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/api/imwa/v1")
public class AdminUserController{
    private AdminUserService adminUserService;
    @PostMapping("/admin-user")
    public ResponseEntity<Object> create(@RequestBody Admin admin){
        return new ResponseEntity<>(adminUserService.create(admin),HttpStatus.CREATED);
    }
    @GetMapping("/admin-user")
    public ResponseEntity<Object> getAll(){
        return new ResponseEntity<>(adminUserService.findAll(),HttpStatus.OK);
    }
    public void setAdminUserService(AdminUserService adminUserService){
        this.adminUserService = adminUserService;
    }

}
