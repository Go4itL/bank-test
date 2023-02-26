package org.softparadigm.bankbackend.service;

import org.softparadigm.bankbackend.dto.PageDto;
import org.softparadigm.bankbackend.dto.PaginationParams;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;

import java.util.List;

public interface IBaseService<E, ID, DTO, PARAMS> {
    DTO findOne(@NonNull ID id);

    List<DTO> findAll();
    PageDto<DTO> findAll(@NonNull PaginationParams payload);

    DTO upsert(@NonNull @Validated PARAMS payload);
    void delete(@NonNull ID id);
}
