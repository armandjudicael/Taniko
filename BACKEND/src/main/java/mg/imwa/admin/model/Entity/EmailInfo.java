package mg.imwa.admin.model.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.io.Serializable;
@AllArgsConstructor
@Data
@Builder
public class EmailInfo implements Serializable {
    private Company createdCompany;
    // Class data members
    private String recipient;
    private String msgBody;
    private String subject;
}
