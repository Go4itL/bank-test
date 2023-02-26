package org.softparadigm.bankbackend.controller;

import org.softparadigm.bankbackend.dto.PageDto;
import org.softparadigm.bankbackend.dto.PaginationParams;
import org.softparadigm.bankbackend.service.IBaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;

public abstract class BaseController<E, ID, DTO, PARAMS> {
    public abstract IBaseService<E, ID, DTO, PARAMS> getService();

    @GetMapping("/{id}")
    public ResponseEntity<DTO> getById(@PathVariable("id") ID id) {
        return ResponseEntity.of(Optional.ofNullable(getService().findOne(id)));
    }

    @GetMapping()
    public ResponseEntity<List<DTO>> getAll() {
        return ResponseEntity.ok(getService().findAll());
    }

    @PostMapping()
    public ResponseEntity<PageDto<DTO>> getAllPaginated(@RequestBody @Validated PaginationParams payload) {
        return ResponseEntity.ok(getService().findAll(payload));
    }

    @RequestMapping(value = "upsert", method = {RequestMethod.POST, RequestMethod.PUT})
    public ResponseEntity<DTO> upsert(@RequestBody @Validated PARAMS payload) {
        return ResponseEntity.of(Optional.ofNullable(getService().upsert(payload)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") ID id) {
        getService().delete(id);
        return ResponseEntity.ok().build();
    }
}
