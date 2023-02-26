package org.softparadigm.bankbackend.service;

import org.softparadigm.bankbackend.dto.PageDto;
import org.softparadigm.bankbackend.dto.PaginationParams;
import org.softparadigm.bankbackend.exception.EntityNotFoundException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * This is an abstract service.
 *
 * @param <E> model.
 * @param <ID> id type.
 * @param <DTO> POJO type.
 */
public abstract class BaseService<E, ID, DTO, PARAMS> {
    public abstract JpaRepository<E, ID> getRepository();

    public abstract DTO getDto(@NonNull E model);
    public abstract E mapEntity(@NonNull PARAMS payload);
    public abstract E buildCriteria(@NonNull Map<String, String> filters);

    @Nullable
    public DTO findOne(@NonNull ID id) {
        Optional<E> model = getRepository().findById(id);
        if (model.isEmpty()) {
            throw new EntityNotFoundException(String.format("Entity not found with ID: %s", id));
        }

        return getDto(model.get());
    }

    public List<DTO> findAll() {
        List<DTO> dtos = new ArrayList<>();
        List<E> models = getRepository().findAll();
        for (E model : models) {
            dtos.add(getDto(model));
        }

        return dtos;
    }

    public PageDto<DTO> findAll(@NonNull PaginationParams payload) {
        PageDto<DTO> dtos = new PageDto<>();

        List<Sort.Order> orders = new ArrayList<>();
        payload.getSorts().forEach((key, direction) -> orders.add(Sort.Order.by(key).with(direction)));
        Pageable pageable = PageRequest.of(payload.getPage(), payload.getSize(), Sort.by(orders));
        Example<E> criteria = Example.of(buildCriteria(payload.getFilters()));

        Page<E> models = getRepository().findAll(criteria, pageable);
        for (E model : models) {
            dtos.getContent().add(getDto(model));
        }
        dtos.setSize(models.getTotalElements());

        return dtos;
    }

    public DTO upsert(@NonNull @Validated PARAMS payload) {
        E model = mapEntity(payload);
        getRepository().save(model);

        return getDto(model);
    }

    public void delete(@NonNull ID id) {
        if (!getRepository().existsById(id)) {
            throw new EntityNotFoundException(String.format("Entity not found with ID: %s", id));
        }

        getRepository().deleteById(id);
    }

}
