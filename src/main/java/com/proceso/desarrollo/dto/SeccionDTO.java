package com.proceso.desarrollo.dto;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;


@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record SeccionDTO(Long id, String nombre, List<SeccionDTO> secciones, List<Long> figuritas) {

}