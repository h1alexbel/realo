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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
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

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;
    private static final String USER_MUST_NOT_HAVE_ADMIN_AUTHORITIES = "User must not have Admin Authorities!";

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    public UserDto register(@RequestBody RegisterRequest registerRequest) {
        UserDto userToSave = buildUserFromRegisterRequest(registerRequest);
        if (userToSave.getRole().equals(Role.ADMIN)) {
            throw new ClientStateException(USER_MUST_NOT_HAVE_ADMIN_AUTHORITIES);
        }
        return userService.save(userToSave);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        return userService.deleteById(id)
                ? noContent().build()
                : notFound().build();
    }

    @PutMapping("/{id}")
    public UserDto update(@PathVariable Long id, @RequestBody UserDto userDto) {
        return userService.update(id, userDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<UserDto> getAll() {
        return userService.findAll();
    }

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

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/title")
    public List<UserDto> getAllByLikedAnnouncementTitle(@RequestParam String title) {
        return userService.findAllByLikedAnnouncement(title);
    }

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
}