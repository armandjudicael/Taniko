package mg.imwa.admin.model.Entity;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import mg.imwa.admin.model.Enum.UserType;

import javax.persistence.*;

@Entity
@Data
public class TenantUser extends Admin{
    private String key;
    @Enumerated(value = EnumType.ORDINAL)
    private UserType userType;
    @Override
    public String toString() {
        return "TenantUser{" +
                "key='" + key + '\'' +
                ", userType=" + userType +super.toString()+
                '}';
    }
}
