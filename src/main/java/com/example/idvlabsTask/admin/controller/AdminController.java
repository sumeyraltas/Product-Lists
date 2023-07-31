package com.example.idvlabsTask.admin.controller;

import com.example.idvlabsTask.admin.controller.request.AddAdminRequest;
import com.example.idvlabsTask.admin.controller.request.UpdateAdminRequest;
import com.example.idvlabsTask.admin.controller.response.AdminResponse;
import com.example.idvlabsTask.admin.service.AdminService;
import com.example.idvlabsTask.common.response.MessageResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@Validated
public class AdminController {
    private final AdminService adminService;

    @PostMapping
    public MessageResponse addAdmin(@Valid @RequestBody AddAdminRequest addAdminRequest) {
        return adminService.addAdmin(addAdminRequest.toDomainEntity());

    }

    @PutMapping("{id}")
    public MessageResponse updateAdmin(@Valid @RequestBody UpdateAdminRequest updateAdminRequest, @PathVariable Long id) {
        return adminService.updateAdmin(id, updateAdminRequest.toDomainEntity());
    }

    @DeleteMapping("{id}")
    public MessageResponse deleteAdmin(@PathVariable Long id) {
        return adminService.deleteAdminById(id);
    }


    @GetMapping("{id}")
    public AdminResponse getAdminById(@PathVariable Long id) {
        return new AdminResponse(adminService.getById(id));
    }

    @GetMapping
    public List<AdminResponse> getAllAdmins() {

        return adminService.getAllAdmin()
                .stream()
                .map(admin -> new AdminResponse(admin))
                .toList();
    }
/*
    @PostMapping("/changePassword/{id}")
    public MessageResponse changePassword(@Valid @RequestBody ChangePasswordRequest request, @PathVariable Long id) {
        return studentService.changePassword(id, request);
   }
 */
}