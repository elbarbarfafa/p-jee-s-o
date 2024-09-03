package fr.elbarbary.p_jee_s_o.controleurs;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;

import fr.elbarbary.p_jee_s_o.controllers.PatientController;
import fr.elbarbary.p_jee_s_o.dtos.ConsultationDto;
import fr.elbarbary.p_jee_s_o.services.ConsultationService;
import fr.elbarbary.p_jee_s_o.services.PatientService;


@WebMvcTest(PatientController.class)
@ExtendWith(MockitoExtension.class)
class PatientControllerTest {

	@MockBean
	PatientService patientService;
	
	@MockBean
	ConsultationService consultationService;

	Page<ConsultationDto> consultationsDto;
	
	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	void setup() {
		
		ConsultationDto dtoTemp = new ConsultationDto();
		dtoTemp.setNumero(1);
		
		List<ConsultationDto> temp = new ArrayList<>();
		
		temp.add(dtoTemp);
		
		consultationsDto = new PageImpl<ConsultationDto>(temp);
		
	}
	
	@Test
	void getConsultationsTest() {
		
		Mockito.when(consultationService.findByPatientNumeroSecurite(Mockito.anyString(), Mockito.any(Pageable.class))).thenReturn(consultationsDto);
		
		//mockMvc.perform(get("/secu-number/consultations").)
		
	}
	
}
