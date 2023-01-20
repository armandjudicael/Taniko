package mg.imwa.admin.model.Entity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import mg.imwa.admin.model.Enum.UserType;
import javax.persistence.*;
import java.io.Serializable;
@Getter
@Setter
@Entity
@Table(name = "Admin", indexes = {
        @Index(name = "idx_admin_id_username", columnList = "id, userName, password, userType")
})
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({
        @NamedQuery(name = "Admin.countBy", query = "select count(a) from Admin a"),
        @NamedQuery(name = "Admin.findAll", query = "select a from Admin a")
})
@Data
@ToString
@Slf4j
public class Admin implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String password;
    @Enumerated(value = EnumType.STRING)
    private UserType userType;


    // REMOVE

    @PreRemove
    public void preRemove() {

    }

    @PostRemove
    public void postRemove() {

    }

    // UPDATE

    @PostUpdate
    public void postUpdate() {

    }

    @PreUpdate
    public void preUpdate() {

    }


    // PERSIST
    @PostPersist
    public void postPersist() {

    }

    @PrePersist
    public void prePersist() {

    }

    @PostLoad
    public void postLoad() {

    }
}
