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
import com.sicpa.demo.dto.DepartmentEmployeeDto;
import com.sicpa.demo.entity.DepartmentEmployee;
import com.sicpa.demo.entity.Employee;
import com.sicpa.demo.exception.ExceptionManager;
import com.sicpa.demo.mapper.IDepartmentEmployeeMapper;
import com.sicpa.demo.mapper.IEmployeeMapper;
import com.sicpa.demo.service.IDepartmentEmployeeService;
import com.sicpa.demo.service.IEmployeeService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Validated
@RequestMapping("/api/employees")
@Slf4j
public class EmployeeController {

	@Autowired
	private IDepartmentEmployeeService departmentEmployeeService;

	@Autowired
	private IEmployeeService employeeService;
	@Autowired
	private IDepartmentEmployeeMapper departmentEmployeeMapper;
	@Autowired
	private IEmployeeMapper employeeMapper;
	private Map<String, Object> response = new HashMap<>();

	@Secured({ "ROLE_ADMIN", "ROLE_USER" ,"ROLE_OPERATOR"})
	@GetMapping("")
	public ResponseEntity<BaseResponseDto<Object>> findAll() {
		try {

			List<DepartmentEmployeeDto> departmentEmployees = departmentEmployeeMapper
					.entitiesToDtos(departmentEmployeeService.findDepartmentEmployeeAll());

			if (!CollectionUtils.isEmpty(departmentEmployees)) {
				return ResponseEntity.ok(BaseResponseDto.builder().data(departmentEmployees).build());
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
	public ResponseEntity<Map<String, Object>> create(@Valid @RequestBody DepartmentEmployeeDto departmentEmployeeDto,
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

			DepartmentEmployee departmentEmployee = departmentEmployeeMapper.dtoToEntity(departmentEmployeeDto);
			Employee employee = employeeMapper.dtoToEntity(departmentEmployeeDto.getEmployee());

			employee = employeeService.save(employee);
			departmentEmployee.getEmployee().setId(employee.getId());
			
			departmentEmployee = departmentEmployeeService.save(departmentEmployee);
			response.put("message", "El registro ha sido creado con éxito");
			response.put("departmentEmployee", departmentEmployeeMapper.entityToDto(departmentEmployee));
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
	public ResponseEntity<Map<String, Object>> update(@PathVariable Long id,
			@Valid @RequestBody DepartmentEmployeeDto departmentEmployeeDto, BindingResult result) {
		response = new HashMap<>();
		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		try {
			DepartmentEmployee departmentEmployee = departmentEmployeeMapper.dtoToEntity(departmentEmployeeDto);
			Employee employee = employeeMapper.dtoToEntity(departmentEmployeeDto.getEmployee());

			employee = employeeService.save(employee);
			departmentEmployeeService.save(departmentEmployee);
			departmentEmployee = departmentEmployeeService.findDepartmentEmployeeById(id).get();

			response.put("message", "El registro ha sido actualizado con éxito");
			response.put("departmentEmployee", departmentEmployeeMapper.entityToDto(departmentEmployee));
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
			Optional<DepartmentEmployee> departmentEmployee = departmentEmployeeService.findById(id);
			if (departmentEmployee.isPresent()) {
				departmentEmployeeService.delete(departmentEmployee.get());
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
