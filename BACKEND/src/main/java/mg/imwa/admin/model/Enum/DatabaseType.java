package mg.imwa.admin.model.Enum;

import java.io.Serializable;

public enum DatabaseType implements Serializable {
    MYSQL,POSTGRESQL,H2,SQL_SERVER,MONGODB,REDIS;
    public String dbType2String(){
        switch (this){
            case MYSQL: return "mysql";
            default: return "postgresql";
        }
    }
}
