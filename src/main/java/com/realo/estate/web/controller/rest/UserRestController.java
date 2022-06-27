package com.realo.estate.web.controller.rest;

import com.realo.estate.domain.dto.UserDto;
import com.realo.estate.domain.persistence.user.ContactInfo;
import com.realo.estate.domain.persistence.user.Role;
import com.realo.estate.domain.persistence.user.UserAddress;
import com.realo.estate.exception.ClientStateException;
import com.realo.estate.repository.filter.UserFilter;
import com.realo.estate.service.UserService;
import com.realo.estate.web.controller.dto.RegisterRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.notFound;

@Slf4j
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;
    private static final String USER_MUST_NOT_HAVE_ADMIN_AUTHORITIES = "User must not have Admin Authorities!";
    private static final String ADMIN_WAS_SAVED_IN_USER_CONTROLLER = "Admin was saved in User controller: {}";
    private static final String USER_WAS_SAVED_IN_USER_CONTROLLER = "User was saved in User controller: {}";
    private static final String USER_WAS_UPDATED_IN_CONTROLLER = "User was updated in controller: {}";
    private static final String USER_WITH_ID_WAS_DELETED_IN_CONTROLLER = "User with id: {} was deleted in controller";

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public UserDto createUser(@RequestBody @Validated RegisterRequest registerRequest) {
        UserDto userToSave = buildUserFromRegisterRequest(registerRequest);
        if (userToSave.getRole().equals(Role.ADMIN)) {
            throw new ClientStateException(USER_MUST_NOT_HAVE_ADMIN_AUTHORITIES);
        }
        UserDto savedUser = userService.save(userToSave);
        log.info(USER_WAS_SAVED_IN_USER_CONTROLLER, savedUser);
        return savedUser;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/add-new-admin")
    public UserDto addNewAdmin(@RequestBody @Validated RegisterRequest registerRequest) {
        UserDto admin = buildAdminFromRegisterRequest(registerRequest);
        UserDto savedAdmin = userService.save(admin);
        log.info(ADMIN_WAS_SAVED_IN_USER_CONTROLLER, savedAdmin);
        return savedAdmin;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        ResponseEntity<Object> response = userService.deleteById(id)
                ? noContent().build()
                : notFound().build();
        log.info(USER_WITH_ID_WAS_DELETED_IN_CONTROLLER, id);
        return response;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public UserDto update(@PathVariable Long id, @Validated @RequestBody UserDto userDto) {
        UserDto updated = userService.update(id, userDto);
        log.info(USER_WAS_UPDATED_IN_CONTROLLER, updated);
        return updated;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<UserDto> getAll() {
        return userService.findAll();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/filter")
    public List<UserDto> getAll(@RequestBody UserFilter filter) {
        return userService.findAll(filter);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public UserDto getById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/login")
    public UserDto getByLogin(@RequestParam String login) {
        return userService.findByLogin(login);
    }

    @PreAuthorize("hasAnyAuthority('USER', 'AGENT')")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/title")
    public List<UserDto> getAllByLikedAnnouncementTitle(@RequestParam String title) {
        return userService.findAllByLikedAnnouncement(title);
    }

    @PreAuthorize("hasAnyAuthority('USER', 'AGENT')")
    @PostMapping("/add-to-interests/{id}")
    public void addToInterest(@AuthenticationPrincipal User principal,
                              @PathVariable("id") Long announcementId) {
        UserDto user = userService.findByLogin(principal.getUsername());
        userService.addAnnouncementToInterests(user.getId(), announcementId);
    }


    private UserDto buildUserFromRegisterRequest(RegisterRequest registerRequest) {
        return UserDto.builder()
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .login(registerRequest.getLogin())
                .email(registerRequest.getEmail())
                .password(registerRequest.getRawPassword())
                .gender(registerRequest.getGender())
                .userAddress(UserAddress.builder()
                        .country(registerRequest.getCountry())
                        .build())
                .contactInfo(ContactInfo.builder()
                        .phoneNumber(registerRequest.getPhoneNumber())
                        .build())
                .role(registerRequest.getRole())
                .build();
    }

    private UserDto buildAdminFromRegisterRequest(RegisterRequest registerRequest) {
        return UserDto.builder()
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .login(registerRequest.getLogin())
                .email(registerRequest.getEmail())
                .password(registerRequest.getRawPassword())
                .gender(registerRequest.getGender())
                .userAddress(UserAddress.builder()
                        .country(registerRequest.getCountry())
                        .build())
                .contactInfo(ContactInfo.builder()
                        .phoneNumber(registerRequest.getPhoneNumber())
                        .build())
                .role(Role.ADMIN)
                .build();
    }
}