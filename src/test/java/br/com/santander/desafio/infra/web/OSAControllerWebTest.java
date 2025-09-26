package br.com.santander.desafio.infra.web;

import br.com.santander.desafio.infra.security.SecurityConfig;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;

@Import(SecurityConfig.class)
@WebMvcTest(controllers = OSAController.class)
class OSAControllerWebTest {




}