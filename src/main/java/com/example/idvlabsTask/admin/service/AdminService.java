package com.example.idvlabsTask.admin.service;

import com.example.idvlabsTask.ChangePasswordRequest;
import com.example.idvlabsTask.admin.entity.Admin;
import com.example.idvlabsTask.admin.repository.AdminRepository;
import com.example.idvlabsTask.authentication.entity.Authority;
import com.example.idvlabsTask.authentication.entity.User;
import com.example.idvlabsTask.authentication.repository.UserRepository;
import com.example.idvlabsTask.common.response.MessageResponse;
import com.example.idvlabsTask.common.response.ResponseType;
import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.List;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public MessageResponse addAdmin(Admin admin) {
        User user = new User();
        if(userRepository.existsByUsername(admin.getEmail())){
            return new MessageResponse(ResponseType.WARNING, "Username/Email already exists");
        }
        if(!emailValidation(admin.getEmail())){
            return new MessageResponse(ResponseType.WARNING, "Invalid Email Address");
        }
        user.getAuthorities().add(new Authority("ADMIN"));
        user.setUsername(admin.getEmail());
        user.setPassword(passwordEncoder.encode(admin.getPassword()));
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));

        user.setAdmin(admin);
        adminRepository.save(admin);
        userRepository.save(user);

        return new MessageResponse(ResponseType.SUCCESS, "SUCCESS");
    }

    public MessageResponse updateAdmin(Long id, Admin updateAdmin){
        Admin admin = adminRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Admin not found"));
        admin.update(updateAdmin);
        adminRepository.save(admin);
        return new MessageResponse(ResponseType.SUCCESS,"Updated");
    }

    public MessageResponse deleteAdminById(Long id){
        adminRepository.deleteById(id);
        return new
                MessageResponse(ResponseType.SUCCESS,"Deleted");
    }

    public List<Admin> getAllAdmin(){
        return adminRepository.findAll();
    }

    public Admin getAdminById(Long id){
        return adminRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("No records find"));
    }
    @GetMapping("{id}")
    public Admin getById(@PathVariable Long id) {
        return adminRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("No records find"));
    }
    public static boolean emailValidation(String emailAddress) {
        String regexPattern = "^(.+)@(\\S+)$";
        return Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches();
    }

    public MessageResponse changePassword(Long id, ChangePasswordRequest passwordFields) {
        User user = userRepository.findByAdminId(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found"));

        String encodedPassword = user.getPassword();
        String plainTextPassword = passwordFields.getEskiSifre();

        if (passwordEncoder.matches(plainTextPassword, encodedPassword)) {
            if (passwordFields.getYeniSifre().equals(passwordFields.getYeniSifreTekrar())) {
                String newEncodedPassword = passwordEncoder.encode(passwordFields.getYeniSifre());
                user.setPassword(newEncodedPassword);
                userRepository.save(user);
                return new MessageResponse(ResponseType.SUCCESS, "Changed Password");
            }
        }

        return new MessageResponse(ResponseType.ERROR, "Not Valid");
    }

}