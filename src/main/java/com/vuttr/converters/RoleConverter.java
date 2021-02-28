package com.vuttr.converters;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.vuttr.dto.RoleDTO;
import com.vuttr.models.Role;

@Component
public class RoleConverter {

	/* Converter Models to DTO */
	public RoleDTO entityToDto(Role role) {
		RoleDTO dto = new RoleDTO();
		dto.setId(role.getId());
		dto.setNameRole(role.getNameRole());
		dto.setDescription(role.getDescription());		
		return dto;
	}
	
	public List<RoleDTO> entityToDto(List<Role> role ){
		return	role.stream().map(x -> entityToDto(x)).collect(Collectors.toList());
	}
	
	/* Converter DTO to Models */
	public Role dtoToEntity(RoleDTO dto) {
		Role role = new Role();
		role.setId(dto.getId());
		role.setNameRole(dto.getNameRole());
		role.setDescription(dto.getDescription());		
		return role;
	}
	
	public List<Role> dtoToEntity(List<RoleDTO> dto)
	{
		return dto.stream().map(x -> dtoToEntity(x)).collect(Collectors.toList());
	}
}
