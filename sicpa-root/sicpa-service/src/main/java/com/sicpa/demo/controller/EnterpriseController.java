package com.sicpa.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sicpa.demo.common.BaseResponseDto;
import com.sicpa.demo.dto.EnterpriseDto;
import com.sicpa.demo.entity.Enterprise;
import com.sicpa.demo.exception.ExceptionManager;
import com.sicpa.demo.mapper.IEnterpriseMapper;
import com.sicpa.demo.service.IEnterpriseService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Validated
@RequestMapping("/api/enterprises")
@Slf4j
public class EnterpriseController {

	@Autowired
	private IEnterpriseService enterpriseService;
	@Autowired
	private IEnterpriseMapper enterpriseMapper;
	private Map<String, Object> response = new HashMap<>();

	@Secured({ "ROLE_ADMIN", "ROLE_USER" ,"ROLE_OPERATOR"})
	@GetMapping("")
	public ResponseEntity<BaseResponseDto<Object>> findAll() {
		try {

			List<EnterpriseDto> enterprises = enterpriseMapper.entitiesToDtos(enterpriseService.findAll());

			if (!CollectionUtils.isEmpty(enterprises)) {
				return ResponseEntity.ok(BaseResponseDto.builder().data(enterprises).build());
			}
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(BaseResponseDto.builder().code(HttpStatus.NOT_FOUND.value()).build());

		} catch (Exception e) {
			log.error("findAll: ", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(BaseResponseDto.builder()
					.message(e.getMessage()).code(HttpStatus.INTERNAL_SERVER_ERROR.value()).build());
		}
	}

	@Secured({ "ROLE_ADMIN", "ROLE_USER" ,"ROLE_OPERATOR"})
	@PostMapping("")
	public ResponseEntity<Map<String, Object>> create(@Valid @RequestBody EnterpriseDto enterpriseDto,
			BindingResult result) {
		response = new HashMap<>();
		try {
			if (result.hasErrors()) {
				List<String> errors = result.getFieldErrors().stream()
						.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
						.collect(Collectors.toList());
				response.put("errors", errors);
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			}

			Enterprise enterprise = enterpriseMapper.dtoToEntity(enterpriseDto);
			enterprise = enterpriseService.save(enterprise);
			response.put("message", "El registro ha sido creado con éxito");
			response.put("enterprise", enterpriseMapper.entityToDto(enterprise));
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		} catch (ExceptionManager e) {
			log.error("create", e);
			response.put("message", "Error al guardar el registro");
			response.put("error", e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Secured({ "ROLE_ADMIN", "ROLE_USER" ,"ROLE_OPERATOR"})
	@PutMapping("/{id}")
	public ResponseEntity<Map<String, Object>> update(@PathVariable Long id, @Valid @RequestBody EnterpriseDto enterpriseDto,
			BindingResult result) {
		response = new HashMap<>();
		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		try {
			Enterprise enterprise = enterpriseMapper.dtoToEntity(enterpriseDto);
			enterprise = enterpriseService.save(enterprise);

			response.put("message", "El registro ha sido actualizado con éxito");
			response.put("enterprise", enterprise);
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		} catch (ExceptionManager e) {
			response.put("message", "Error al actualizar el registro");
			response.put("error", e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Secured({ "ROLE_ADMIN" })
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Object>> delete(@PathVariable Long id) {
		response = new HashMap<>();
		try {
			Optional<Enterprise> enterprise = enterpriseService.findById(id);
			if (enterprise.isPresent()) {
				enterpriseService.delete(enterprise.get());
				response.put("message", "El registro ha sido eliminado con éxito");
				return ResponseEntity.status(HttpStatus.OK).body(response);
			} else {
				response.put("message", "El registro no existe");
				response.put("error", "El registro no existe");
				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			}
		} catch (ExceptionManager e) {
			response.put("message", "Error al eliminar el registro");
			response.put("error", e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
