package com.epam.brest.courses.rest_app;

import com.epam.brest.courses.model.Transport;
import com.epam.brest.courses.rest_app.exception.CustomExceptionHandler;
import com.epam.brest.courses.util.DateUtilites;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

import static com.epam.brest.courses.constants.TransportConstants.TRANSPORT_NAME_SIZE;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:app-context-test.xml"})
public class TransportControllerIT {

    public static final String EUROPE_MINSK = "Europe/Minsk";
    public static final String datePattern = "yyyy-MM-dd";
    private static Logger LOGGER = LoggerFactory.getLogger(TransportControllerIT.class);

    public static final String TRANSPORTS_ENDPOINT = "/transports";

    @Autowired
    private TransportController transportController;
    @Autowired
    private CustomExceptionHandler customExceptionHandler;

    private ObjectMapper objectMapper;

    private MockMvc mockMvc;

    MockMvcTransportService transportService = new MockMvcTransportService();

    @BeforeEach
    private void before(){
        objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat(datePattern));
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES,
                true);
//        objectMapper.setTimeZone(TimeZone.getTimeZone(EUROPE_MINSK));

        mockMvc = MockMvcBuilders.standaloneSetup(transportController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .setControllerAdvice(customExceptionHandler)
                .alwaysDo
                        (MockMvcResultHandlers.print())
                .build();
    }

    private static ObjectMapper createObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("MM/dd/yyyy"));
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES,
                true);
        return mapper;
    }

    public static final String DATE_FROM = "2020-03-01";
    public static final String DATE_FOR_TRANSPORT2 = "2020-03-02";
    public static final String DATE_TO = "2020-03-08";

    @Test
    public void shoulFindAllTransports() throws Exception {
        List<Transport> transports = transportService.findAll();
        assertNotNull(transports);
        assertTrue(transports.size() > 0);
    }

    @Test
    public void shouldCreateTransport() throws Exception {
        Transport transport = new Transport()
                .setTransportName(RandomStringUtils.randomAlphabetic(TRANSPORT_NAME_SIZE))
                .setFuelId(1)
                .setTransportTankCapasity(50d)
                .setTransportDate(DateUtilites.getDateByString("2020-01-01"));

        Integer id = transportService.create(transport);
        assertNotNull(id);
    }

    @Test
    public void shouldFindAllTransportsInValueFromDateToDate() throws Exception {
        // given
        Date dateFrom = DateUtilites.getDateByString(DATE_FROM);
        Transport transport1 = new Transport()
                .setTransportName(RandomStringUtils.randomAlphabetic(TRANSPORT_NAME_SIZE))
                .setFuelId(1)
                .setTransportTankCapasity(50d)
                .setTransportDate(dateFrom);

        Integer id1 = transportService.create(transport1);
        assertNotNull(id1);

        Date dateTransport2 = DateUtilites.getDateByString(DATE_FOR_TRANSPORT2);
        Transport transport2 = new Transport()
                .setTransportName(RandomStringUtils.randomAlphabetic(TRANSPORT_NAME_SIZE))
                .setFuelId(1)
                .setTransportTankCapasity(50d)
                .setTransportDate(dateTransport2);

        Integer id2 = transportService.create(transport2);
        assertNotNull(id2);
        Date dateTo = DateUtilites.getDateByString(DATE_TO);

        // when
        List<Transport> transports = transportService.findAllFromDateToDate(dateFrom, dateTo);
        // then
        assertNotNull(transports);
        assertTrue(2 == transports.size());
    }

    @Test
    public void shouldFindAllTransportsInValueFromDateToDatePost() throws Exception {
        // given
        Date dateFrom = DateUtilites.getDateByString(DATE_FROM);
        Transport transport1 = new Transport()
                .setTransportName(RandomStringUtils.randomAlphabetic(TRANSPORT_NAME_SIZE))
                .setFuelId(1)
                .setTransportTankCapasity(50d)
                .setTransportDate(dateFrom);

        Integer id1 = transportService.create(transport1);
        assertNotNull(id1);

        Date dateTransport2 = DateUtilites.getDateByString(DATE_FOR_TRANSPORT2);
        Transport transport2 = new Transport()
                .setTransportName(RandomStringUtils.randomAlphabetic(TRANSPORT_NAME_SIZE))
                .setFuelId(1)
                .setTransportTankCapasity(50d)
                .setTransportDate(dateTransport2);

        Integer id2 = transportService.create(transport2);
        assertNotNull(id2);
        Date dateTo = DateUtilites.getDateByString(DATE_TO);

        // when
        List<Transport> transports = transportService.findAllFromDateToDatePost(dateFrom, dateTo);
        // then
        assertNotNull(transports);
        assertTrue(2 == transports.size());
    }

    @Test
    public void shouldFindTransportsByFuilId() throws Exception {

        // given
        Date dateFrom = DateUtilites.getDateByString(DATE_FROM);
        Transport transport1 = new Transport()
                .setTransportName(RandomStringUtils.randomAlphabetic(TRANSPORT_NAME_SIZE))
                .setFuelId(1)
                .setTransportTankCapasity(50d)
                .setTransportDate(dateFrom);

        Integer id1 = transportService.create(transport1);
        assertNotNull(id1);

        Date dateTransport2 = DateUtilites.getDateByString(DATE_FOR_TRANSPORT2);
        Transport transport2 = new Transport()
                .setTransportName(RandomStringUtils.randomAlphabetic(TRANSPORT_NAME_SIZE))
                .setFuelId(1)
                .setTransportTankCapasity(50d)
                .setTransportDate(dateTransport2);

        Integer id2 = transportService.create(transport2);
        assertNotNull(id2);
        Date dateTo = DateUtilites.getDateByString(DATE_TO);

        // when
        List<Transport> transports = transportService.findByFuelId(1);
        // then
        assertNotNull(transports);
        assertTrue(transports.size() > 0);
    }

    @Test
    public void shouldFindTransportById() throws Exception {

        // given
        Transport transport = new Transport()
                .setTransportName(RandomStringUtils.randomAlphabetic(TRANSPORT_NAME_SIZE))
                .setFuelId(1)
                .setTransportTankCapasity(50d)
                .setTransportDate(getDateWithoutTimeUsingCalendar());

        Integer id = transportService.create(transport);
        assertNotNull(id);

        // when
        Optional<Transport> optionalTransport = transportService.findById(id);

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
    public void shouldUpdateTransport() throws Exception {

        // given
        Transport transport = new Transport()
                .setTransportName(RandomStringUtils.randomAlphabetic(TRANSPORT_NAME_SIZE))
                .setFuelId(1)
                .setTransportTankCapasity(50d)
                .setTransportDate(getDateWithoutTimeUsingCalendar());

        Integer id = transportService.create(transport);
        assertNotNull(id);

        Optional<Transport> transportOptional = transportService.findById(id);
        assertTrue(transportOptional.isPresent());
        transportOptional.get()
                .setTransportName(RandomStringUtils.randomAlphabetic(TRANSPORT_NAME_SIZE));

        //when
        int result = transportService.update(transportOptional.get());

        //then
        assertTrue(1 == result);
        Optional<Transport> updatedTransportOptional = transportService.findById(id);
        assertTrue(updatedTransportOptional.isPresent());
        assertEquals(id, updatedTransportOptional.get().getTransportId());
        assertEquals(transportOptional.get().getTransportName(), updatedTransportOptional.get().getTransportName());
    }

    @Test
    public void shouldDeleteTransport() throws Exception {

        // given
        Transport transport = new Transport()
                .setTransportName(RandomStringUtils.randomAlphabetic(TRANSPORT_NAME_SIZE))
                .setFuelId(1)
                .setTransportTankCapasity(50d)
                .setTransportDate(getDateWithoutTimeUsingCalendar());

        Integer id = transportService.create(transport);
        assertNotNull(id);

        List<Transport> transports =
                transportService.findAll();
        assertNotNull(transports);

        // when
        int result = transportService.delete(id);
        assertTrue(1 == result);

        List<Transport> currentTransports = transportService.findAll();
        assertNotNull(currentTransports);
        assertEquals(transports.size() - 1, currentTransports.size());
    }

    class MockMvcTransportService {

        public List<Transport> findAll() throws Exception {
           LOGGER.debug("findAll()");
            MockHttpServletResponse response =
                    mockMvc.perform(get(TRANSPORTS_ENDPOINT)
                    .accept(MediaType.APPLICATION_JSON)
                    ).andExpect(status().isOk())
                    .andReturn().getResponse();
            assertNotNull(response);
            return objectMapper.readValue(response.getContentAsString(), new TypeReference<List<Transport>>() {});
        }

        public List<Transport> findAllFromDateToDate(Date dateFrom, Date dateTo) throws Exception {
            LOGGER.debug("findAllFromDateToDate({}, {})", dateFrom, dateTo);
            String findTransportFromDateToDateUrl = TRANSPORTS_ENDPOINT
                    + "/from/" + DateUtilites.getStringByDate(dateFrom)
                    + "/to/" + DateUtilites.getStringByDate(dateTo);
            MockHttpServletResponse response =
                    mockMvc.perform(get(findTransportFromDateToDateUrl)
                            .accept(MediaType.APPLICATION_JSON)
                    ).andExpect(status().isOk())
                            .andReturn().getResponse();
            return objectMapper.readValue(response.getContentAsString(), new TypeReference<List<Transport>>() {});
        }

        public List<Transport> findAllFromDateToDatePost(Date dateFrom, Date dateTo) throws Exception {
            LOGGER.debug("findAllFromDateToDatePost({}, {})", dateFrom, dateTo);
            Map<String, Date> datesMap = new HashMap<>();
            datesMap.put("dateFrom", dateFrom);
            datesMap.put("dateTo", dateTo);
            String datesJson = objectMapper.writeValueAsString(datesMap);
            MockHttpServletResponse response =
                    mockMvc.perform(post(TRANSPORTS_ENDPOINT+"/filter")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(datesJson)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andReturn().getResponse();
            return objectMapper.readValue(response.getContentAsString(), new TypeReference<List<Transport>>() {});

        }

        public List<Transport> findByFuelId(Integer fuelId) throws Exception {
            LOGGER.debug("findByFuelId({})", fuelId);
            MockHttpServletResponse response =
                    mockMvc.perform(get(TRANSPORTS_ENDPOINT + "/fuel/" + fuelId)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andReturn().getResponse();
            return objectMapper.readValue(response.getContentAsString(), new TypeReference<List<Transport>>() {});
        }

        public Optional<Transport> findById(Integer transportId) throws Exception {
            LOGGER.debug("findById({})", transportId);
            MockHttpServletResponse response =
                    mockMvc.perform(get(TRANSPORTS_ENDPOINT + "/" + transportId)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andReturn().getResponse();
            return Optional.of(objectMapper.readValue(response.getContentAsString(), Transport.class));
        }

        public Integer create(Transport transport) throws Exception {
            LOGGER.debug("create({})", transport);
            String transportJson = objectMapper.writeValueAsString(transport);
            MockHttpServletResponse response =
                    mockMvc.perform(post(TRANSPORTS_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                            .content(transportJson)
                            .accept(MediaType.APPLICATION_JSON)
                    ).andExpect(status().isOk())
                    .andReturn().getResponse();
            return objectMapper.readValue(response.getContentAsString(), Integer.class);
        }

        public int update(Transport transport) throws Exception {
            LOGGER.debug("update({})", transport);
            String updateTransportJson = objectMapper.writeValueAsString(transport);
            MockHttpServletResponse response =
                        mockMvc.perform(put(TRANSPORTS_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateTransportJson)
                        .accept(MediaType.APPLICATION_JSON)
                        ).andExpect(status().isOk())
                    .andReturn().getResponse();
            return objectMapper.readValue(response.getContentAsString(), Integer.class);
        }

        public int delete(Integer transportId) throws Exception {
            LOGGER.debug("delete({})", transportId);
            MockHttpServletResponse response =
                    mockMvc.perform(MockMvcRequestBuilders.delete(TRANSPORTS_ENDPOINT + "/" + transportId)
                            .accept(MediaType.APPLICATION_JSON)
                    ).andExpect(status().isOk())
                    .andReturn().getResponse();
            return objectMapper.readValue(response.getContentAsString(), Integer.class);
        }
    }

}
