package com.asudak.pico.db.mapper;

import com.asudak.pico.core.config.MapperConfiguration;
import com.asudak.pico.db.entity.AccessToken;
import com.asudak.pico.db.model.AccessTokenDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfiguration.class)
public interface AccessTokenMapper {

    AccessTokenDTO toDTO(AccessToken Token);

    AccessToken toEntity(AccessTokenDTO Token);

    void updateEntity(@MappingTarget AccessToken entity, AccessTokenDTO dto);
}
