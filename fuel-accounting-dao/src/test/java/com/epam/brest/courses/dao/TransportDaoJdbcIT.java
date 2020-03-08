package com.epam.brest.courses.dao;

import com.epam.brest.courses.model.Transport;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.epam.brest.courses.constants.TransportConstants.TRANSPORT_NAME_SIZE;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-dao.xml", "classpath:dao.xml"})
public class TransportDaoJdbcIT {

    private final TransportDao transportDao;

    @Autowired
    public TransportDaoJdbcIT(TransportDao transportDao) {
        this.transportDao = transportDao;
    }

    @Test
    public void shoulFindAllTransportss() {
        List<Transport> transports = transportDao.findAll();
        assertNotNull(transports);
        assertTrue(transports.size() > 0);
    }

    @Test
    public void shouldFindTransportsByFuilId(){
        List<Transport> transports = transportDao.findByFuelId(1);
        assertNotNull(transports);
        assertTrue(transports.size() > 0);
    }

    @Test
    public void shouldFindTransportById(){

        // given
        Transport transport = new Transport()
                .setTransportName(RandomStringUtils.randomAlphabetic(TRANSPORT_NAME_SIZE))
                .setFuelId(1)
                .setTransportTankCapasity(50d)
                .setTransportDate(getDateWithoutTimeUsingCalendar());

        Integer id = transportDao.create(transport);
        assertNotNull(id);

        // when
        Optional<Transport> optionalTransport = transportDao.findById(id);

        // then
        assertTrue(optionalTransport.isPresent());
        assertEquals(id, optionalTransport.get().getTransportId());
        assertEquals(transport.getTransportName(), optionalTransport.get().getTransportName());
        assertEquals(transport.getFuelId(), optionalTransport.get().getFuelId());
        assertEquals(transport.getTransportTankCapasity(), optionalTransport.get().getTransportTankCapasity());
        Date dateFromOptional = new Date(optionalTransport.get().getTransportDate().getTime());
        assertEquals(transport.getTransportDate(), dateFromOptional);
    }

    /**
     * Get date without time.
     */
    public static Date getDateWithoutTimeUsingCalendar() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    @Test
    public void shouldCreateTransport(){

        Transport transport = new Transport()
                .setTransportName(RandomStringUtils.randomAlphabetic(TRANSPORT_NAME_SIZE))
                .setFuelId(1)
                .setTransportTankCapasity(50d)
                .setTransportDate(getDateWithoutTimeUsingCalendar());

        Integer id = transportDao.create(transport);
        assertNotNull(id);
    }

    @Test
    public void shouldUpdateTransport(){

        // given
        Transport transport = new Transport()
                .setTransportName(RandomStringUtils.randomAlphabetic(TRANSPORT_NAME_SIZE))
                .setFuelId(1)
                .setTransportTankCapasity(50d)
                .setTransportDate(getDateWithoutTimeUsingCalendar());

        Integer id = transportDao.create(transport);
        assertNotNull(id);

        Optional<Transport> transportOptional = transportDao.findById(id);
        assertTrue(transportOptional.isPresent());
        transportOptional.get()
                .setTransportName(RandomStringUtils.randomAlphabetic(TRANSPORT_NAME_SIZE));

        //when
        int result = transportDao.update(transportOptional.get());

        //then
        assertTrue(1 == result);
        Optional<Transport> updatedTransportOptional = transportDao.findById(id);
        assertTrue(updatedTransportOptional.isPresent());
        assertEquals(id, updatedTransportOptional.get().getTransportId());
        assertEquals(transportOptional.get().getTransportName(), updatedTransportOptional.get().getTransportName());
    }

    @Test
    public void shouldDeleteTransport(){

        // given
        Transport transport = new Transport()
                .setTransportName(RandomStringUtils.randomAlphabetic(TRANSPORT_NAME_SIZE))
                .setFuelId(1)
                .setTransportTankCapasity(50d)
                .setTransportDate(getDateWithoutTimeUsingCalendar());

        Integer id = transportDao.create(transport);
        assertNotNull(id);

        List<Transport> transports =
                transportDao.findAll();
        assertNotNull(transports);

        // when
        int result = transportDao.delete(id);
        assertTrue(1 == result);

        List<Transport> currentTransports = transportDao.findAll();
        assertNotNull(currentTransports);
        assertEquals(transports.size()-1, currentTransports.size());


    }




}
