package com.reports.restcontroller;

import com.reports.service.servicesImplementations.ReportServiceImplementation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


@RunWith(SpringRunner.class)
@WebMvcTest(value = ReportController.class)
public class ReportControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReportServiceImplementation reportServiceImplementation;

    @Test
    public void putReport() {
    }

    @Test
    public void getAllReports() {
    }

    @Test
    public void getReportById() {
    }

    @Test
    public void deleteAllReports() {
    }

    @Test
    public void deleteByReportById() {
    }
}