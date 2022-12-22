package mg.imwa.admin.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class EmailInfo {
    private Company createdCompany;
    // Class data members
    private String recipient;
    private String msgBody;
    private String subject;
}
