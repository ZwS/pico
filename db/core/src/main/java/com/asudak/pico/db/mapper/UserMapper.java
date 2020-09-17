package com.asudak.pico.db.mapper;

import com.asudak.pico.core.config.MapperConfiguration;
import com.asudak.pico.db.entity.User;
import com.asudak.pico.db.model.UserDTO;
import com.asudak.pico.db.model.UserDetailsDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfiguration.class)
public interface UserMapper {

    UserDTO toDTO(User user);

    UserDetailsDTO toDetailsDTO(User user);

    User toEntity(UserDTO user);

    void updateEntity(@MappingTarget User entity, UserDTO dto);
}
