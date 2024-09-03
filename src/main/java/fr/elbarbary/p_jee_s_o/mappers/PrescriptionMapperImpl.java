package fr.elbarbary.p_jee_s_o.mappers;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

import fr.elbarbary.p_jee_s_o.dtos.PrescriptionDetailDto;
import fr.elbarbary.p_jee_s_o.entities.Consultation;
import fr.elbarbary.p_jee_s_o.entities.Medicament;
import fr.elbarbary.p_jee_s_o.entities.Prescription;

@Component
public class PrescriptionMapperImpl extends Mapper<Prescription, PrescriptionDetailDto> {
	
	public PrescriptionMapperImpl(Class<Prescription> entityClass, Class<PrescriptionDetailDto> dtoClass) {
		super(entityClass, dtoClass);
	}
	
	public PrescriptionMapperImpl() {
		super(Prescription.class, PrescriptionDetailDto.class);
	}
	
	/**
	 * Mapping de prescription vers son dto détaillé
	 * @param prescription
	 * @return
	 */
	@Override
	public PrescriptionDetailDto mapToDto(Prescription prescription)
	{
		ModelMapper modelMapper = new ModelMapper();
		TypeMap<Prescription, PrescriptionDetailDto> propertyMapper = modelMapper.createTypeMap(Prescription.class, PrescriptionDetailDto.class);
		propertyMapper.addMapping(src-> src.getMedicament().getCode(), PrescriptionDetailDto::setMedicamentCode);
		propertyMapper.addMapping(src->src.getMedicament().getLibelle(), PrescriptionDetailDto::setMedicamentLibelle);
		propertyMapper.addMapping(src->src.getConsultation().getNumero(), PrescriptionDetailDto::setNumeroConsultation);
		propertyMapper.addMapping(src->src.getPrises(), PrescriptionDetailDto::setNombrePrises);
		
		return modelMapper.map(prescription, PrescriptionDetailDto.class);
	}
	
	/**
	 * Mapping du dto d'une prescription vers son entité
	 * @param dto 
	 * @return Une instance de l'entité Prescription
	 */
	@Override
	public Prescription mapToEntity(PrescriptionDetailDto dto)
	{
		ModelMapper modelMapper = new ModelMapper();
		TypeMap<PrescriptionDetailDto, Prescription> propertyMapper = modelMapper.createTypeMap(PrescriptionDetailDto.class, Prescription.class);
		propertyMapper.<String>addMapping(src-> src.getMedicamentCode(), (destination, value) -> {
			Medicament med = new Medicament();med.setCode(value);destination.setMedicament(med);
			});
		propertyMapper
				.<Integer>addMapping(src -> src.getNumeroConsultation(), (destination, value) -> {
					Consultation consultation = new Consultation();
					consultation.setNumero(value);
					destination.setConsultation(consultation);
				});
		propertyMapper.<Integer>addMapping(src->src.getNombrePrises(), (destination, value) -> destination.setPrises(value));
		
		return modelMapper.map(dto, Prescription.class);
	}

}
