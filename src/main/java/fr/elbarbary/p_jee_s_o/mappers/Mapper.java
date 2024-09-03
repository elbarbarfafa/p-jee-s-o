package fr.elbarbary.p_jee_s_o.mappers;

import org.modelmapper.ModelMapper;

public class Mapper<E1,D1> implements IMapper<E1, D1> {

	protected ModelMapper modelMapper;
	private Class<E1> entityClass;
	private Class<D1> dtoClass;
	
	public Mapper(Class<E1> entityClass, Class<D1> dtoClass) {
		this.modelMapper = new ModelMapper();
		this.entityClass = entityClass;
		this.dtoClass = dtoClass;
	}
	
	@Override
	public E1 mapToEntity(D1 dto) {
		return this.modelMapper.map(dto, this.entityClass);
	}

	@Override
	public D1 mapToDto(E1 entity) {
		return this.modelMapper.map(entity, this.dtoClass);
	}	

}
