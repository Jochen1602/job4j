package ru.job4j.servlets;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ValidateService.class)
public class UserCreateServletTest {
    @Ignore
    @Test
    public void whenAddUserThenStoreIt() throws IOException {
        Validate validate = new UserCreateStub();
        PowerMockito.mockStatic(ValidateService.class);
        //Mockito.when(ValidateService.getInstance()).thenReturn(validate);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("name")).thenReturn("g1");
        when(req.getParameter("login")).thenReturn("g2");
        when(req.getParameter("password")).thenReturn("g3");
        when(req.getParameter("role")).thenReturn("g4");
        when(req.getParameter("email")).thenReturn("g5");
        new UserCreateServlet().doPost(req, resp);
        assertThat(validate.findAll().iterator().next().getName(), is("g1"));
    }
}