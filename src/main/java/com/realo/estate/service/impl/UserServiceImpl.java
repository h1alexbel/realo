package com.realo.estate.service.impl;

import com.realo.estate.domain.dto.UserDto;
import com.realo.estate.domain.mapper.UserMapper;
import com.realo.estate.domain.persistence.user.Role;
import com.realo.estate.domain.persistence.user.User;
import com.realo.estate.exception.ClientStateException;
import com.realo.estate.exception.ResourceNotFoundException;
import com.realo.estate.repository.UserRepository;
import com.realo.estate.repository.filter.UserFilter;
import com.realo.estate.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private static final String USER_NOT_FOUND_MESSAGE = "User Not Found! Please try again.";
    private static final String USER_CREDENTIALS_ALREADY_EXISTS = "User with this name or email already exists!";
    private static final String USER_SAVED_IN_SERVICE = "User was saved in service :{}";
    private static final String USER_UPDATED_IN_SERVICE = "User was updated in service :{}";
    private static final String ADDED_TO_USER_INTEREST_WITH_ID = "Announcement with id :{} was added to user interest with id :{}";
    private static final String USER_WAS_DELETED_IN_SERVICE = "User was deleted in service :{}";
    private static final String ADMIN_SAVED_IN_SERVICE = "Admin was saved in service :{}";
    private static final String AGENT_SAVED_IN_SERVICE = "Agent was saved in service :{}";

    @Transactional
    @Override
    public UserDto save(UserDto userDto) {
        if (!userRepository.existsByLogin(userDto.getLogin())
            && !userRepository.existsByEmail(userDto.getEmail())) {
            if (userDto.getRole().equals(Role.ADMIN)) {
                UserDto adminAccount = createAdminAccount(userDto);
                log.info(ADMIN_SAVED_IN_SERVICE, adminAccount);
                return adminAccount;
            }
            if (userDto.getRole().equals(Role.AGENT)) {
                UserDto agentAccount = createAgentAccount(userDto);
                log.info(AGENT_SAVED_IN_SERVICE, agentAccount);
                return agentAccount;
            }
            UserDto saved = saveUser(userDto, Role.USER);
            log.info(USER_SAVED_IN_SERVICE, saved);
            return saved;
        }
        throw new ClientStateException(USER_CREDENTIALS_ALREADY_EXISTS);
    }

    @Transactional
    @Override
    public UserDto update(Long id, UserDto userDto) {
        if (!userRepository.existsByLogin(userDto.getLogin())
            && !userRepository.existsByEmail(userDto.getEmail())) {
            UserDto updated = userRepository.findById(id)
                    .map(entity -> {
                        User user = userMapper.toEntity(userDto);
                        user.setId(userDto.getId());
                        return user;
                    })
                    .map(userRepository::saveAndFlush)
                    .map(userMapper::toDto)
                    .orElseThrow(() -> new ResourceNotFoundException(USER_NOT_FOUND_MESSAGE));
            log.info(USER_UPDATED_IN_SERVICE, updated);
            return updated;
        }
        throw new ClientStateException(USER_CREDENTIALS_ALREADY_EXISTS);
    }

    @Transactional
    @Override
    public void addAnnouncementToInterests(Long userId, Long announcementId) {
        userRepository.addToInterests(announcementId, userId);
        log.info(ADDED_TO_USER_INTEREST_WITH_ID, announcementId, userId);
    }

    @Transactional
    @Override
    public boolean deleteById(Long id) {
        return userRepository.findById(id)
                .map(user -> {
                    userRepository.delete(user);
                    userRepository.flush();
                    log.info(USER_WAS_DELETED_IN_SERVICE, user);
                    return true;
                }).orElse(false);
    }

    @Transactional(readOnly = true)
    @Override
    public UserDto findById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException(USER_NOT_FOUND_MESSAGE));
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .collect(toList());
    }

    @Override
    public List<UserDto> findAll(UserFilter filter) {
        return userRepository.findByFilter(filter).stream()
                .map(userMapper::toDto)
                .collect(toList());
    }

    @Transactional(readOnly = true)
    @Override
    public UserDto findByLogin(String login) {
        return userRepository.findByLogin(login)
                .map(userMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException(USER_NOT_FOUND_MESSAGE));
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserDto> findAllByLoginContaining(String login) {
        return userRepository.findAllByLoginContaining(login).stream()
                .map(userMapper::toDto)
                .collect(toList());
    }

    @Transactional(readOnly = true)
    @Override
    public UserDto findByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(userMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException(USER_NOT_FOUND_MESSAGE));
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserDto> findAllByLikedAnnouncement(String title) {
        return userRepository.findAllByLikedAnnouncement(title).stream()
                .map(userMapper::toDto)
                .collect(toList());
    }

    private UserDto createAdminAccount(UserDto admin) {
        return saveUser(admin, Role.ADMIN);
    }

    private UserDto createAgentAccount(UserDto agent) {
        return saveUser(agent, Role.AGENT);
    }

    private UserDto saveUser(UserDto userDto, Role role) {
        return Optional.of(userDto)
                .map(userMapper::toEntity)
                .map(user -> {
                    user.setPassword(passwordEncoder.encode(user.getPassword()));
                    user.setRole(role);
                    return userRepository.save(user);
                })
                .map(userMapper::toDto)
                .orElseThrow();
    }
}