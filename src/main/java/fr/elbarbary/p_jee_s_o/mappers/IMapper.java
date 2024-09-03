package fr.elbarbary.p_jee_s_o.mappers;

public interface IMapper<E1, D1> {

	E1 mapToEntity(D1 dto);
	
	D1 mapToDto(E1 entity);
	
}
