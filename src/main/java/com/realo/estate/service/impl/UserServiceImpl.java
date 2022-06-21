package com.realo.estate.service.impl;

import com.realo.estate.domain.dto.UserDto;
import com.realo.estate.domain.mapper.UserMapper;
import com.realo.estate.domain.persistence.user.User;
import com.realo.estate.exception.ResourceNotFoundException;
import com.realo.estate.repository.UserRepository;
import com.realo.estate.repository.filter.UserFilter;
import com.realo.estate.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private static final String USER_NOT_FOUND_MESSAGE = "User Not Found! Please try again.";
    private static final String USER_CREDENTIALS_ALREADY_EXISTS = "User with this name or email already exists!";

    @Transactional
    @Override
    public UserDto save(UserDto userDto) {
        if (!userRepository.existsByLogin(userDto.getLogin())
            && !userRepository.existsByEmail(userDto.getEmail())) {
            return Optional.of(userDto)
                    .map(userMapper::toEntity)
                    .map(user -> {
                        user.setPassword(passwordEncoder.encode(user.getPassword()));
                        return userRepository.save(user);
                    })
                    .map(userMapper::toDto)
                    .orElseThrow();
        }
        throw new IllegalStateException(USER_CREDENTIALS_ALREADY_EXISTS);
    }

    @Transactional
    @Override
    public UserDto update(Long id, UserDto userDto) {
        if (!userRepository.existsByLogin(userDto.getLogin())
            && !userRepository.existsByEmail(userDto.getEmail())) {
            return userRepository.findById(id)
                    .map(entity -> {
                        User user = userMapper.toEntity(userDto);
                        user.setId(userDto.getId());
                        return user;
                    })
                    .map(userRepository::saveAndFlush)
                    .map(userMapper::toDto)
                    .orElseThrow(() -> new ResourceNotFoundException(USER_NOT_FOUND_MESSAGE));
        }
        throw new IllegalStateException(USER_CREDENTIALS_ALREADY_EXISTS);
    }

    @Transactional
    @Override
    public void addAnnouncementToInterests(Long user, Long announcementId) {
        userRepository.addToInterests(announcementId, user);
    }

    @Transactional
    @Override
    public boolean deleteById(Long id) {
        return userRepository.findById(id)
                .map(user -> {
                    userRepository.delete(user);
                    userRepository.flush();
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
}