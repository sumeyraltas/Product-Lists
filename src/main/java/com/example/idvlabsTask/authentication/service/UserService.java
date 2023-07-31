package com.example.idvlabsTask.authentication.service;

import com.example.idvlabsTask.admin.entity.Admin;
import com.example.idvlabsTask.admin.repository.AdminRepository;
import com.example.idvlabsTask.authentication.entity.User;
import com.example.idvlabsTask.authentication.repository.UserRepository;
import com.example.idvlabsTask.common.response.MessageResponse;
import com.example.idvlabsTask.common.response.ResponseType;
import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AdminRepository adminRepository;

    @Transactional
    public MessageResponse addUsers(User user) {
        if(userRepository.existsByUsername(user.getUsername())){
            return new MessageResponse(ResponseType.WARNING, "Username already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        return new MessageResponse(ResponseType.SUCCESS, "User has been added successfully");
    }

    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    @Transactional(readOnly = true)
    public Admin getAdminByUserId(Long id) {
        User user= userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        Admin admin = adminRepository.findByEmail(user.getUsername()).orElseThrow(() -> new EntityNotFoundException("User not found"));
        return admin;
    }
    @Transactional
    public MessageResponse deleteUserById(Long id) {
        String name = userRepository.findById(id).orElseThrow().getUsername();
        System.out.println(name);
        if ((name.equals("admin"))) {
            return new MessageResponse(ResponseType.ERROR, "user could not be deleted");
        }
        userRepository.deleteById(id);

        return new MessageResponse(ResponseType.SUCCESS, "User has been deleted successfully");
    }

    @Transactional
    public MessageResponse updateUser(Long id, User updatedUser) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        user.update(updatedUser);
        userRepository.save(user);

        return new MessageResponse(ResponseType.SUCCESS, "User has been updated successfully");
    }
/*
    @Transactional
    public MessageResponse forgotPassword(String email) {

        if(!studentService.emailValidation(email)){
            return new MessageResponse(ResponseType.WARNING, "Invalid Email Address");
        }
        User users = userRepository.findByUsername(email).orElseThrow();
        if(users.getId()==null){
            return new MessageResponse(ResponseType.WARNING, "sisteme kayıtlı değilsiniz");
        }
        String password = studentService.generateRandomPassword();
        EmailDetails details = new EmailDetails();
        details.setRecipient(email);
        details.setSubject(" USER INFORMATION");
        details.setMsgBody(" yeni şifreniz: " + password + " Lütfen güvenliğiniz için şifrenizi profil sayfanızda değiştiriniz.");
        boolean result = emailService.sendSimpleMail(details);
        if(!result){
            return new MessageResponse(ResponseType.WARNING, "mail could not be sent");
        }
        users.setPassword(passwordEncoder.encode(password));
        return new MessageResponse(ResponseType.SUCCESS, "User has been updated successfully");
    }

 */
}
