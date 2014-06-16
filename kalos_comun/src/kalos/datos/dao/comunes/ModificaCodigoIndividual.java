package kalos.datos.dao.comunes;

import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

// modificación de un código
public class ModificaCodigoIndividual extends SqlUpdate {
    public ModificaCodigoIndividual(DataSource dataSource, String sql) {
        super(dataSource, sql);
        declareParameter(new SqlParameter(Types.INTEGER));
        declareParameter(new SqlParameter(Types.CHAR));
    }
}
