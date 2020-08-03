package com.asudak.pico.db.service.user;

import com.asudak.pico.db.DbConfiguration;
import com.asudak.pico.db.mapper.UserMapper;
import com.asudak.pico.db.model.UserDTO;
import com.asudak.pico.db.model.page.Page;
import com.asudak.pico.db.model.page.PageRequest;
import com.asudak.pico.db.repository.UserRepository;
import com.asudak.pico.db.service.NotFoundException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.UUID;

@Stateless
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private DbConfiguration configuration;
    private UserMapper userMapper;

    @Inject
    public UserServiceImpl(UserRepository userRepository, DbConfiguration configuration, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.configuration = configuration;
        this.userMapper = userMapper;
    }

    @Override
    public Page<UserDTO> getUsers(Integer page) {
        return userRepository.findUsers(PageRequest.of(page, configuration.getPageSize()))
                .map(userMapper::toDTO);
    }

    @Override
    public UserDTO getUser(String id) {
        return userRepository.findUserById(UUID.fromString(id))
                .map(userMapper::toDTO)
                .orElseThrow(() -> new NotFoundException(UserDTO.class, id));
    }
}
