package com.citynow.smallerp.controller.v1;

import com.citynow.smallerp.annotation.APIVersion;
import com.citynow.smallerp.configuration.security.jwt.JwtProvider;
import com.citynow.smallerp.entity.LeaveCalendar;
import com.citynow.smallerp.entity.Role;
import com.citynow.smallerp.entity.RoleName;
import com.citynow.smallerp.entity.User;
import com.citynow.smallerp.mapper.UserMapper;
import com.citynow.smallerp.model.*;
import com.citynow.smallerp.repository.CalendarRepository;
import com.citynow.smallerp.repository.RoleRepository;
import com.citynow.smallerp.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

@RestController
@APIVersion
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    CalendarRepository calendarRepository;

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginModel user) {
        logger.info("login api");

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        user.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateJwtToken(authentication);

        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return ResponseEntity.ok("logout api");
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpModel signUpForm){
        if(userRepository.existsByEmail(signUpForm.getEmail())){
            return new ResponseEntity<String>("Fail -> Email is already taken!", HttpStatus.BAD_REQUEST);
        }

        User user = new User(signUpForm.getUsername(),signUpForm.getEmail(), encoder.encode(signUpForm.getPassword()));
        Set<String> strRoles = signUpForm.getRole();
        Set<Role> roles = new HashSet<>();

        strRoles.forEach(role -> {
            switch (role){
                case "admin":
                    Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    roles.add(adminRole);
                    break;
                case "pm":
                    Role pmRole = roleRepository.findByName(RoleName.ROLE_PM)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    roles.add(pmRole);

                    break;
                default:
                    Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    roles.add(userRole);
            }


        });

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok().body("User registered successfully!");
    }
    @PostMapping("/register")
    public ResponseEntity<?> registerLeaving(@Valid @RequestBody LeaveForm leaveForm){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        System.out.println(leaveForm.getToDate());
        System.out.println(leaveForm.getFromDate());
        System.out.println(leaveForm.getApprove());
        System.out.println(leaveForm.getApplicate());
        LeaveCalendar leaveCalendar = new LeaveCalendar(leaveForm.getFromDate(), leaveForm.getToDate(), username, userRepository.findByUsername(leaveForm.getApprove()));
        calendarRepository.save(leaveCalendar);
        return ResponseEntity.ok().body("Register Success");
    }
}
