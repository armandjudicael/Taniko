package mg.imwa.admin.model.Entity;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import mg.imwa.admin.model.Enum.UserType;

import javax.persistence.*;

@Entity
@Data
public class TenantUser extends Admin{
    private String key;
}
