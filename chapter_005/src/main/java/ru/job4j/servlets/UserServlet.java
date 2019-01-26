package ru.job4j.servlets;

import javax.servlet.http.HttpServlet;


/**
 * Presentation layout. Interact only with logic layout.
 */
public class UserServlet extends HttpServlet {
    private final Validate logic = ValidateService.getInstance();

}
