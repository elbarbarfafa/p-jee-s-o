package fr.elbarbary.p_jee_s_o.controllers;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(path = "/api")
@RestController
public abstract class BaseApiController<MainEntity, MainDto, MainService> implements ICrudRestController {
	
	protected MainService principalService;
	
	public BaseApiController(MainService mainService) {
		this.principalService = mainService;
	}
	
	public Page<MainDto> all(){
		return null;
		
	}
	
	
	
}
