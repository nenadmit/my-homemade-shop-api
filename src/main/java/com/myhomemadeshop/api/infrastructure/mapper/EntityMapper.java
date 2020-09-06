package com.myhomemadeshop.api.infrastructure.mapper;

import java.util.List;
import java.util.Set;

public interface EntityMapper<E,R,D> {

    E mapToEntity(R requestDto);

    D mapToResponseDto(E entity);

    Set<D> mapToResponseDto(Set<E> entities);

    List<D> mapToResponseDto(List<E> entities);

}
