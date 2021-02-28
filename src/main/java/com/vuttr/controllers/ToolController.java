package com.vuttr.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.vuttr.converters.ToolConverter;
import com.vuttr.dto.ToolDTO;
import com.vuttr.services.implementation.ToolServiceImp;

@RestController
@RequestMapping(value = "/api/tools")
public class ToolController {

	@Autowired
	ToolServiceImp service;
	
	@Autowired
	ToolConverter converter;
	

	/* List All Tools and Seach by Tag */
	@GetMapping
	public ResponseEntity<?> getAllTool(
			@RequestParam(required = false) String tag,
	        @RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "3") int size){
							
			try {			
				
				Pageable paging = PageRequest.of(page, size);
		
				Page<ToolDTO> pageTools;
				
				 if (tag == null) {
					 pageTools = service.findAllToolCustom(paging);
				 }
				 else {
					 pageTools = service.findByTagCustom(tag, paging);
				 }
				 
				 List<ToolDTO> tools = new ArrayList<ToolDTO>();				 				 
				 tools = pageTools.getContent();
				 
				 Map<String, Object> response = new HashMap<>();
			     response.put("tools", tools);
			     response.put("currentPage", pageTools.getNumber());
			     response.put("totalItems", pageTools.getTotalElements());
			     response.put("totalPages", pageTools.getTotalPages());
				 
				 if(!tools.isEmpty()) {
					 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
				 }
				 return new ResponseEntity<String>("Não foi encontrada nenhuma Ferramenta!!!", HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<String>("Ocorreu um erro no controller da requisição: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			}		
	}
	
	/* Get Tool by Id */
	@GetMapping("/{id}")
	public ResponseEntity<?> findByIdTool(@PathVariable Long id){
		ToolDTO tool = service.findByIdTool(id);
		try {
			if(tool != null) {
				return new ResponseEntity<ToolDTO>(tool, HttpStatus.OK);
			}else {
				return new ResponseEntity<String>("Ferramenta com o códgio '" + id + "' não encontrada!!!",  HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>("Ocorreu um erro no controller da requisição: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/* Create Tool */
	@PostMapping
	public ResponseEntity<?> create(@RequestBody @Valid ToolDTO tool){
		ToolDTO entity = service.create(tool);
		try {
			if(entity != null) {
				return new ResponseEntity<ToolDTO>(entity, HttpStatus.OK);
			}else {
				return new ResponseEntity<String>("Não foi possível cadastrar a ferramenta!!!",  HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>("Ocorreu um erro no controller da requisição: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/* Update Tool */
	@SuppressWarnings("null")
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid ToolDTO tool){
		
		try {
			tool = service.update(id, tool);
				if(tool.getId() != null) {
				return new ResponseEntity<ToolDTO>(tool, HttpStatus.OK);
			}else {
				return new ResponseEntity<String>("Não foi possível atualizar a ferramenta "+ tool.getTitle() +" !!!",  HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>("Ocorreu um erro na requisição: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/* Delete Tool */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		String msg = service.delete(id);
		try {
			return new ResponseEntity<String>(msg, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Ocorreu um erro no controller da requisição: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/** Response BAD REQUEST */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(
	  MethodArgumentNotValidException ex) {
	    Map<String, String> errors = new HashMap<>();
	    ex.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
	        errors.put(fieldName, errorMessage);
	    });
	    return errors;
	}
	
}
