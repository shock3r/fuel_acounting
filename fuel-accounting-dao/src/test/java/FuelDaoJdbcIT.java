import com.epam.brest.courses.dao.FuelDao;
import com.epam.brest.courses.model.Fuel;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-dao.xml", "classpath:dao.xml"})
public class FuelDaoJdbcIT {
    @Autowired
    private FuelDao fuelDao;

    @Test
    public void shoulFindAllFuels() {
        List<Fuel> fuels = fuelDao.findAll();
        assertNotNull(fuels);
        assertTrue(fuels.size() > 0);
    }
}
