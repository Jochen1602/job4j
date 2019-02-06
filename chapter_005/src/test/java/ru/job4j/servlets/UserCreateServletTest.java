package ru.job4j.servlets;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;


import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ValidateService.class)
public class UserCreateServletTest {
    private Validate validate;

    @Before
    public void createMocks() {
        this.validate = UserCreateStub.getInstance();
        PowerMockito.mockStatic(ValidateService.class);
        when(ValidateService.getInstance()).thenReturn(this.validate);
    }

    @Test
    public void whenAddUserThenStoreIt() throws IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("name")).thenReturn("g1");
        when(req.getParameter("login")).thenReturn("g2");
        when(req.getParameter("password")).thenReturn("g3");
        when(req.getParameter("role")).thenReturn("user");
        when(req.getParameter("email")).thenReturn("g5");
        new UserCreateServlet().doPost(req, resp);
        assertThat(validate.findAll().iterator().next().getName(), is("g1"));
    }

    @Test
    public void whenUpdateUser() throws IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        when(req.getParameter("name")).thenReturn("g1");
        when(req.getParameter("login")).thenReturn("g2");
        when(req.getParameter("password")).thenReturn("g3");
        when(req.getParameter("role")).thenReturn("admin");
        when(req.getParameter("email")).thenReturn("g5");
        new UserCreateServlet().doPost(req, resp);

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getParameter("id")).thenReturn("1");
        when(request.getParameter("name")).thenReturn("d2");
        when(request.getParameter("login")).thenReturn("d3");
        when(request.getParameter("password")).thenReturn("d4");
        when(request.getParameter("role")).thenReturn("admin");
        when(request.getParameter("email")).thenReturn("d6");
        when(request.getSession()).thenReturn(session);
        session.setAttribute("role", "admin");
        session.setAttribute("login", "g2");
        when(request.getSession().getAttribute("role")).thenReturn("admin");
        when(request.getSession().getAttribute("login")).thenReturn("g2");
        new UserUpdateServlet().doPost(request, response);
        assertThat(validate.findAll().iterator().next().getEmail(), is("d6"));
    }

    @Test
    public void whenDeleteUser() throws IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        ServletOutputStream outputStream = mock(ServletOutputStream.class);
        when(req.getParameter("name")).thenReturn("g1");
        when(req.getParameter("login")).thenReturn("g2");
        when(req.getParameter("password")).thenReturn("g3");
        when(req.getParameter("role")).thenReturn("admin");
        when(req.getParameter("email")).thenReturn("g5");
        new UserCreateServlet().doPost(req, resp);

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(response.getOutputStream()).thenReturn(outputStream);
        when(request.getParameter("id")).thenReturn("1");
        new UserDeleteServlet().doPost(request, response);
        assertThat(validate.findAll().size(), is(0));
    }
}