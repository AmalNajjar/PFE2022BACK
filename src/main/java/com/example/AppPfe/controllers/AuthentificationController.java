package com.example.AppPfe.controllers;

import com.example.AppPfe.Models.Authentification;
import com.example.AppPfe.Models.CompteUtilisateur;
import com.example.AppPfe.Models.ERole;
import com.example.AppPfe.Models.Role;
import com.example.AppPfe.exception.ResourceNotFoundException;
import com.example.AppPfe.payload.request.LoginRequest;
import com.example.AppPfe.payload.request.SignupRequest;
import com.example.AppPfe.payload.response.JwtResponse;
import com.example.AppPfe.payload.response.MessageResponse;
import com.example.AppPfe.repository.AuthtificationRepositories;
import com.example.AppPfe.repository.Rolerepo;
import com.example.AppPfe.security.jwt.JwtUtils;
import com.example.AppPfe.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/auth")
public class AuthentificationController {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  AuthtificationRepositories userRepository;

  @Autowired
  Rolerepo roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @PostMapping("/signin")
  public ResponseEntity<?> Authentifier( @RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);
    new MessageResponse(jwt);
    
    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    List<String> roles = userDetails.getAuthorities().stream()
        .map(item -> item.getAuthority())
        .collect(Collectors.toList());

    return ResponseEntity.ok(new JwtResponse(jwt,
           userDetails.getId(),
                         userDetails.getUsername(), 
                         userDetails.getEmail(), 
                         roles));
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(  @RequestBody SignupRequest signUpRequest) {
    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Username déja utilisé!"));
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Email déja utilisé!"));
    }

    // Create new user's account
     Authentification user = new Authentification(signUpRequest.getUsername(),
            signUpRequest.getEmail(),
            encoder.encode(signUpRequest.getPassword()));


    Set<Role> roles = new HashSet<>();
    System.out.println(signUpRequest);
    if (signUpRequest.getRole().equals("user") ) {
      Role userRole = roleRepository.findByName(ERole.ROLE_USER)
              .orElseThrow(() -> new RuntimeException("Error: Role n'existe pas."));
      roles.add(userRole);
    } else {
      Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
              .orElseThrow(() -> new RuntimeException("Error: Role n'existe pas."));
      roles.add(adminRole);

    }


    user.setRoles(roles);


    return ResponseEntity.ok( userRepository.save(user));
  }

   /* @GetMapping("/authentification")
    public List<Authentification> getAllComptes() {
        return  userRepository.findAll();
    }


    @GetMapping("/authentification/{id}")
    public ResponseEntity<Authentification> consulter_utilisateur(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        Authentification authentification = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Compte not found"));
        return ResponseEntity.ok().body(authentification);
    }
*/





  
  
}
