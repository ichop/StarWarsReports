package com.reports.restcontroller;

import com.reports.model.Report;
import com.reports.model.Result;
import com.reports.service.servicesImplementations.ReportServiceImplementation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.skyscreamer.jsonassert.JSONAssert;



import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(value = ReportController.class)
public class ReportControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReportServiceImplementation reportServiceImplementation;


    Result result = Result.newBuilder()
            .setCharacterId(1)
            .setCharacterName("Luke")
            .setPlanetId(1)
            .setPlanetName("Tatooine")
            .setFilmId(1)
            .setFilmName("New Hope")
            .build();
    Set<Result> results = new HashSet<>(Arrays.asList(result));

    Report mockreport = new Report(1,
            "Luke",
            "Tatooine",
            results
    );

    String mockReportJson =
            "  {\n" +
                    "    \"report_id\": 1,\n" +
                    "    \"query_criteria_character_phrase\": \"Luke\",\n" +
                    "    \"query_criteria_planet_name\": \"Tatooine\",\n" +
                    "    \"result\": [\n" +
                    "      {\n" +
                    "        \"film_id\": 1,\n" +
                    "        \"film_name\": \"New Hope\",\n" +
                    "        \"character_id\": 1,\n" +
                    "        \"character_name\": \"Luke\",\n" +
                    "        \"planet_id\": 1,\n" +
                    "        \"planet_name\": \"Tatooine\"\n" +
                    "      }" +
                    "               ]" +
                    "}";

    @Test
    public void putReport() throws Exception {

        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.put("http://localhost:9090/reports/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(mockReportJson);
        this.mockMvc.perform(builder)
                .andExpect(status()
                        .is(204));
    }


    @Test
    public void getReportById() throws Exception {
        Mockito.when(
                reportServiceImplementation.getById(Mockito.anyInt()))
                .thenReturn(mockreport);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("http://localhost:9090/reports/1")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result.getResponse());

        JSONAssert.assertEquals(mockReportJson, result.getResponse().getContentAsString(), false);
    }


    @Test
    public void deleteByReportById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("http://localhost:9090/reports/1", 1))
                .andExpect(status().isOk());
    }
}