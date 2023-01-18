package mg.imwa.admin.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

public interface BasicControllerMethod <T > {
    ResponseEntity<Object> create(T entity);
    ResponseEntity<Object> findAll();
    ResponseEntity<Object> updateById(T newEntity , Long id );
    ResponseEntity<Object>  findById(Long id);
    ResponseEntity<Boolean> delete(@PathVariable  Long id);
}
