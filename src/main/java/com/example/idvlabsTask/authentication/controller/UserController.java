package com.example.idvlabsTask.authentication.controller;

import com.example.idvlabsTask.admin.controller.response.AdminResponse;
import com.example.idvlabsTask.authentication.controller.request.AddUserRequest;
import com.example.idvlabsTask.authentication.controller.responses.UserQueryModel;
import com.example.idvlabsTask.authentication.service.UserService;
import com.example.idvlabsTask.common.response.MessageResponse;
import javax.validation.*;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Validated
public class UserController {
    private final UserService userService;
    @PostMapping()
    public MessageResponse addUsers(@Valid @RequestBody AddUserRequest adduser) {
        return userService.addUsers(adduser.toDomainEntity());
    }
    @DeleteMapping("/{id}")
    public MessageResponse deleteUserbyId(@PathVariable @NotNull Long id) {
        return userService.deleteUserById(id);
    }

    @GetMapping()
    public List<UserQueryModel> getAllUsers() {
        return userService.getAllUsers()
                .stream()
                .map(user -> new UserQueryModel(user))
                .toList();
    }
    @GetMapping("/{id}")
    public UserQueryModel getById(@NotNull @PathVariable Long id) {
        return new UserQueryModel(userService.getById(id));
    }

    @GetMapping("/admin/{id}")
    public AdminResponse getAdminByUserId(@NotNull @PathVariable Long id) {
        return new AdminResponse(userService.getAdminByUserId(id));
    }
    /*
    @PostMapping("/forgotPassword")
    public MessageResponse forgotPassword(@Valid @RequestBody ForgotPasswordRequest request) {
        return userService.forgotPassword(request.getEmail());
    }
     */
}