package mg.imwa.config.dbConfig;
import org.hibernate.engine.jdbc.connections.spi.AbstractMultiTenantConnectionProvider;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.springframework.stereotype.Component;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class MapMultiTenantConnectionProvider extends AbstractMultiTenantConnectionProvider{
    private Map<String,ConnectionProvider> connectionProviderMap = new ConcurrentHashMap<>();

    public MapMultiTenantConnectionProvider() {
    }

    @Override protected ConnectionProvider getAnyConnectionProvider(){
        return connectionProviderMap.values().iterator().next();
    }

    @Override
    protected ConnectionProvider selectConnectionProvider(Object o) {
        return connectionProviderMap.get((String) o);
    }


    public Map<String, ConnectionProvider> getConnectionProviderMap() {
        return connectionProviderMap;
    }

}