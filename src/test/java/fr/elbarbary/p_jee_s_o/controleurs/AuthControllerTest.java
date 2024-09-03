package fr.elbarbary.p_jee_s_o.controleurs;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MockMvc;

import fr.elbarbary.p_jee_s_o.controllers.AuthController;
import fr.elbarbary.p_jee_s_o.services.AuthService;

@WebMvcTest(AuthController.class)
@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

	@Mock
	AuthService authService;
	

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	void authenticateTest() throws Exception {
		Mockito.when(authService.authenticate(Mockito.any(Authentication.class))).thenReturn("UN TOKEN");
		
		mockMvc.perform(post("/token")).andExpect(status().isOk()).andExpect(content().string("UN TOKEN"));
	}
	
	
	
}
