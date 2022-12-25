package mg.imwa.admin.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@AllArgsConstructor
@Data
public class EmailInfo implements Serializable {
    private Company createdCompany;
    // Class data members
    private String recipient;
    private String msgBody;
    private String subject;
}
