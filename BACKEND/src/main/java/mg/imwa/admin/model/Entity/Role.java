package mg.imwa.admin.model.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Primary;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
public class Role implements Serializable {
    @Id
    private Long id;
    private String name;

    public Role(String name) {
        this.name = name;
    }
}
