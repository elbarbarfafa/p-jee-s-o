package fr.elbarbary.p_jee_s_o.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fr.elbarbary.p_jee_s_o.dtos.PrescriptionDetailDto;
import fr.elbarbary.p_jee_s_o.entities.Prescription;
import fr.elbarbary.p_jee_s_o.mappers.PrescriptionMapperImpl;
import fr.elbarbary.p_jee_s_o.repositories.PrescriptionRepository;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {

	private final PrescriptionRepository prescriptionRepository;
	
	private final PrescriptionMapperImpl prescriptionMapper;
	
	public PrescriptionServiceImpl(PrescriptionRepository prescriptionRepository, PrescriptionMapperImpl prescriptionMapper) {
		this.prescriptionRepository = prescriptionRepository;
		this.prescriptionMapper = prescriptionMapper;
	}
	
	@Override
	public Page<PrescriptionDetailDto> findMedicamentsByConsultationNumero(final int numero, Pageable pageable)
	{
		Page<Prescription> prescriptions = prescriptionRepository.findByConsultationNumero(numero, pageable);
		return prescriptions.map(prescriptionMapper::mapToDto);
	}
	
	public List<PrescriptionDetailDto> replacePrescriptionsByConsultationNumero(final int numero, List<PrescriptionDetailDto> detailsDto)
	{
		List<Prescription> presc = new ArrayList<>();
		for (PrescriptionDetailDto prescriptionDto : detailsDto) {
			presc.add(prescriptionMapper.mapToEntity(prescriptionDto));
		}
		List<Prescription> currentPrescriptions = prescriptionRepository.findByConsultationNumero(numero);
		prescriptionRepository.deleteAll(currentPrescriptions);
		List<Prescription> saved = prescriptionRepository.saveAll(presc);
		List<PrescriptionDetailDto> ret = new ArrayList<>();
		for (Prescription prescription : saved) {
			ret.add(prescriptionMapper.mapToDto(prescription));
		}
		return ret;
	}
	
}
