package com.asudak.pico.db.mapper;

import com.asudak.pico.core.config.MapperConfiguration;
import com.asudak.pico.db.entity.RefreshToken;
import com.asudak.pico.db.model.RefreshTokenDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfiguration.class)
public interface RefreshTokenMapper {

    RefreshTokenDTO toDTO(RefreshToken Token);

    RefreshToken toEntity(RefreshTokenDTO Token);

    void updateEntity(@MappingTarget RefreshToken entity, RefreshTokenDTO dto);
}
