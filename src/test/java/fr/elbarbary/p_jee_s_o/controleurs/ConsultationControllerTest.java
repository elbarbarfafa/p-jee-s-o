package fr.elbarbary.p_jee_s_o.controleurs;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.elbarbary.p_jee_s_o.controllers.ConsultationController;
import fr.elbarbary.p_jee_s_o.dtos.ConsultationDto;
import fr.elbarbary.p_jee_s_o.dtos.PrescriptionDetailDto;
import fr.elbarbary.p_jee_s_o.services.ConsultationService;
import fr.elbarbary.p_jee_s_o.services.PrescriptionService;

@WebMvcTest(ConsultationController.class)
@ExtendWith(MockitoExtension.class)
class ConsultationControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ConsultationService consultationService;

	@MockBean
	private PrescriptionService prescriptionService;

	@InjectMocks
	private ConsultationController consultationController;

	private ObjectMapper objectMapper = new ObjectMapper();

	private ConsultationDto consultationDto;

	@BeforeEach
	public void setup() {
		consultationDto = new ConsultationDto();
		// Initialize ConsultationDto with test data
	}

	@Test
	void testAddConsultationSuccess() throws Exception {
		Mockito.when(consultationService.add(Mockito.any(ConsultationDto.class))).thenReturn(consultationDto);

		mockMvc.perform(post("/api/consultations").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(consultationDto))).andExpect(status().isOk())
				.andExpect(jsonPath("$.numero").value(consultationDto.getNumero()));
	}

	@Test
	void testEditConsultationSuccess() throws Exception {
		Mockito.when(consultationService.edit(Mockito.anyInt(), Mockito.any(ConsultationDto.class))).thenReturn(consultationDto);

		mockMvc.perform(put("/api/consultations/{id}", 1).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(consultationDto))).andExpect(status().isOk())
				.andExpect(jsonPath("$.numero").value(consultationDto.getNumero()));
	}

	@Test
	void testGetMedicamentsSuccess() throws Exception {
		Page<PrescriptionDetailDto> page = new PageImpl<>(Collections.emptyList(), PageRequest.of(0, 10), 0);
		Mockito.when(prescriptionService.findMedicamentsByConsultationNumero(Mockito.anyInt(), Mockito.any(Pageable.class))).thenReturn(page);

		mockMvc.perform(get("/api/consultations/{id}/medicaments", 1).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	void testUpdatePrescriptionsSuccess() throws Exception {
		Mockito.when(prescriptionService.replacePrescriptionsByConsultationNumero(Mockito.anyInt(), Mockito.anyList()))
				.thenReturn(Collections.emptyList());

		mockMvc.perform(put("/api/consultations/{id}/prescriptions", 1).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(Collections.emptyList()))).andExpect(status().isOk());
	}

	@Test
	void testUploadDocumentSuccess() throws Exception {
		MockMultipartFile file = new MockMultipartFile("file", "test.txt", MediaType.TEXT_PLAIN_VALUE,
				"Test content".getBytes());
		Mockito.when(consultationService.setDocument(Mockito.eq(1), Mockito.any(MultipartFile.class))).thenReturn(consultationDto);

		mockMvc.perform(multipart("/api/consultations/{id}/document", 1).file(file)).andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(consultationDto.getNumero()));
	}

}
