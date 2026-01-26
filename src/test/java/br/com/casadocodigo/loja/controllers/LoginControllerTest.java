package br.com.casadocodigo.loja.controllers;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LoginControllerTest {
    @Test
    void loginFormDeveRetornarView() {
        LoginController controller = new LoginController();
        String view = controller.loginForm();
        assertEquals("formLogin", view);
    }
}
