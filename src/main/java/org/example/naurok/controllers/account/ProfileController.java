package org.example.naurok.controllers.account;

import org.example.naurok.payload.response.account.get.GetAccountResponse;
import org.example.naurok.security.CustomUserDetails;
import org.example.naurok.services.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/account")
public class ProfileController {
    @Autowired
    private AccountService accountService;
    @GetMapping("/get/my")
    public ResponseEntity<?> getMyProfile(Authentication auth) {
        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
        GetAccountResponse response = accountService.getOwnAccount(userDetails.getUUID());
        return response != null ? ResponseEntity.ok(response) : ResponseEntity.badRequest().body("Account didn't find");
    }

    @GetMapping("/get/{uuid}")
    public ResponseEntity<?> getProfile(@PathVariable String uuid, Authentication auth) {
        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
        GetAccountResponse response = new GetAccountResponse();

        if (userDetails.getUUID().equals(uuid)) {
            response = accountService.getOwnAccount(userDetails.getUUID());
        }else{
            response = accountService.getAccount(uuid);
        }
        return response != null ? ResponseEntity.ok(response) : ResponseEntity.badRequest().body("Account didn't find");
    }
}
