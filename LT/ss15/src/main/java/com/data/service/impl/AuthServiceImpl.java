package com.data.service.impl;

import com.data.model.dto.request.LoginRequest;
import com.data.model.dto.request.RegisterRequest;
import com.data.model.dto.response.JWTResponse;
import com.data.model.entity.Role;
import com.data.model.entity.User;
import com.data.repository.RoleRepository;
import com.data.repository.UserRepository;
import com.data.security.jwt.JwtUtils;
import com.data.security.principle.CustomUserDetails;
import com.data.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    @Override
    public User register(RegisterRequest registerRequest) {
        User user = User.builder()
                .userName(registerRequest.getUserName())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .fullName(registerRequest.getFullName())
                .email(registerRequest.getEmail())
                .phone(registerRequest.getPhoneNumber())
                .enabled(true)
                .roles(mapRoleStringToRole(registerRequest.getRoles()))
                .build();
        return userRepository.save(user);
    }

    @Override
    public JWTResponse login(LoginRequest loginRequest) {
        Authentication authentication = null;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUserName(),
                            loginRequest.getPassword()
                    )
            );

        } catch (AuthenticationException e) {
            log.error("Sai ten dang nhap hoac mat khau");
        }

        CustomUserDetails userDetails = (CustomUserDetails) Objects.requireNonNull(authentication).getPrincipal();
        String jwtToken = jwtUtils.generateToken(userDetails.getUsername());

        return JWTResponse.builder()
                .username(userDetails.getUsername())
                .fullName(userDetails.getFullName())
                .email(userDetails.getEmail())
                .phone(userDetails.getPhone())
                .enabled(userDetails.getEnabled())
                .authorities(userDetails.getAuthorities())
                .token(jwtToken)
                .build();
    }

    private List<Role> mapRoleStringToRole(List<String> roles) {
        List<Role> roleList = new ArrayList<>();

        if(roles!=null && !roles.isEmpty()){
            roles.forEach(role->{
                switch (role){
                    case "ROLE_ADMIN":
                        roleList.add(roleRepository.findByRoleName(role).orElseThrow(()-> new NoSuchElementException("Khong ton tai role_admin")));
                        break;
                    case "ROLE_USER":
                        roleList.add(roleRepository.findByRoleName(role).orElseThrow(()-> new NoSuchElementException("Khong ton tai role_user")));
                        break;
                    case "ROLE_MODERATOR":
                        roleList.add(roleRepository.findByRoleName(role).orElseThrow(()-> new NoSuchElementException("Khong ton tai role_moderator")));
                        break;
                    default:
                        roleList.add(roleRepository.findByRoleName("ROLE_USER").orElseThrow(()-> new NoSuchElementException("Khong ton tai role_user")));
                }
            });
        }else{
            roleList.add(roleRepository.findByRoleName("ROLE_USER").orElseThrow(()-> new NoSuchElementException("Khong ton tai role_user")));
        }
        return roleList;
    }
}
