package fr.elbarbary.p_jee_s_o.mappers;

public interface IMapper<Entity, Dto> {

	Entity mapToEntity(Dto dto);
	
	Dto mapToDto(Entity entity);
	
}
