package mg.imwa.admin.model.Entity;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;
import mg.imwa.admin.model.Enum.UserType;

import jakarta.persistence.*;

@Entity
@Table(name = "tenant_user")
@Getter
@Setter
@SuperBuilder
public class TenantUser extends Admin{
    private String key;
    public TenantUser() {

    }
}
