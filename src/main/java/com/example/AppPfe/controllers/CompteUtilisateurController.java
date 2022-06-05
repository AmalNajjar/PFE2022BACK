package com.example.AppPfe.controllers;
import com.example.AppPfe.Models.Authentification;
import com.example.AppPfe.Models.ERole;
import com.example.AppPfe.Models.Role;
import com.example.AppPfe.Models.CompteUtilisateur;
import com.example.AppPfe.exception.ResourceNotFoundException;
import com.example.AppPfe.payload.request.SignupRequest;
import com.example.AppPfe.payload.response.MessageResponse;
import com.example.AppPfe.repository.AuthtificationRepositories;
import com.example.AppPfe.repository.Rolerepo;
import com.example.AppPfe.repository.compteUtilisateurRepository;
import com.example.AppPfe.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/test")
public class CompteUtilisateurController {
    @Autowired
    compteUtilisateurRepository compteRepository;
 @Autowired
 AuthtificationRepositories authRepo;
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

    @PostMapping("/Compte")
    public ResponseEntity<?> créer_utilisateur(@RequestBody CompteUtilisateur compte) throws Exception {
        SignupRequest signUpRequest=new SignupRequest();
        signUpRequest.setEmail(compte.getEmail());
        signUpRequest.setPassword(compte.getPassword());
        signUpRequest.setUsername(compte.getPrenom());
        signUpRequest.setRole("user");
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
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

        compteRepository.save(compte);
       return ResponseEntity.ok( userRepository.save(user));

    }

    @GetMapping("/Comptes")
    public List<CompteUtilisateur> getAllComptes() {
        return  compteRepository.findAll();
    }


    @GetMapping("/Comptes/{id}")
    public ResponseEntity<CompteUtilisateur> consulter_utilisateur(@PathVariable(value = "id") Integer id)
            throws ResourceNotFoundException {
        CompteUtilisateur compte = compteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Compte n'existe pas"));
        return ResponseEntity.ok().body(compte);
    }


    @PutMapping("/Comptes/{id}")
    public ResponseEntity<CompteUtilisateur>modifier_utilisateur(@PathVariable(value = "id") Integer id,
                                                                 @RequestBody CompteUtilisateur compteDetails)// ,@RequestBody Authentification auth)
            throws ResourceNotFoundException {
        CompteUtilisateur compte= compteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("compte utilisateur n'exite pas"));

        compte.setNom(compteDetails.getNom());
        compte.setMatricule(compteDetails.getMatricule());
        compte.setPrenom(compteDetails.getPrenom());
         compte.setLibelleDirection(compteDetails.getLibelleDirection());
        compte.setEmail(compteDetails.getEmail());
        compte.setPassword(compteDetails.getPassword());

        final CompteUtilisateur updateCompte  = compteRepository.save(compte);
      //  Authentification var=userRepository.findByUsername(compte.getPrenom()).get();
      //  var.setUsername(auth.getUsername());
      //  var.setPassword(auth.getPassword());
      //  var.setEmail(auth.getEmail());
      //  userRepository.save(var);
        return ResponseEntity.ok(updateCompte );
    }

    @DeleteMapping("/Comptes/{id}")
    public Map<String, Boolean> supprimer_utilisateur(@PathVariable(value = "id") Integer id)
            throws ResourceNotFoundException {

        CompteUtilisateur compte = compteRepository.findById(id)

                .orElseThrow(() -> new ResourceNotFoundException("Compte n'existe pas"));
        compteRepository.delete(compte);
        Authentification var=userRepository.findByUsername(compte.getPrenom()).get();
        userRepository.delete(var);
        Map<String, Boolean> response = new HashMap<>();
        response.put("delete", Boolean.TRUE);
        return response;
    }

    @GetMapping("/Comptes/findbyEmail/{email}")
    public CompteUtilisateur trouverParEmail(@PathVariable String email){
        return compteRepository.findByEmail(email);
    }
}