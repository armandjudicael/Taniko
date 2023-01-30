package mg.imwa.admin.model.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;
import mg.imwa.admin.model.Enum.UserType;
import java.io.Serializable;
@Entity
@Table(name = "Admin", indexes = {
        @Index(name = "idx_admin_id_username", columnList = "id, userName, password, userType")
})
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({
        @NamedQuery(name = "Admin.countBy", query = "select count(a) from Admin a"),
        @NamedQuery(name = "Admin.findAll", query = "select a from Admin a")
})
@ToString
@Slf4j
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class Admin implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String password;
    @Enumerated(value = EnumType.STRING)
    private UserType userType;
}
