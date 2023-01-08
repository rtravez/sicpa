package com.sicpa.demo.common;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponseDto<T> {
	@Builder.Default
	private Integer code = 200;
	private String message;
	private List<String> errors;
	private T data;

}
