package com.asudak.pico.db.mapper;

import com.asudak.pico.core.config.MapperConfiguration;
import com.asudak.pico.db.entity.Role;
import com.asudak.pico.db.model.RoleDTO;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfiguration.class)
public interface RoleMapper {

    RoleDTO toDTO(Role role);
}
