package fr.elbarbary.p_jee_s_o.mappers;

import org.modelmapper.ModelMapper;

public class Mapper<Entity,Dto> implements IMapper<Entity, Dto> {

	protected ModelMapper modelMapper;
	private Class<Entity> entityClass;
	private Class<Dto> dtoClass;
	
	public Mapper(Class<Entity> entityClass, Class<Dto> dtoClass) {
		this.modelMapper = new ModelMapper();
		this.entityClass = entityClass;
		this.dtoClass = dtoClass;
	}
	
	@Override
	public Entity mapToEntity(Dto dto) {
		return this.modelMapper.map(dto, this.entityClass);
	}

	@Override
	public Dto mapToDto(Entity entity) {
		return this.modelMapper.map(entity, this.dtoClass);
	}	

}
