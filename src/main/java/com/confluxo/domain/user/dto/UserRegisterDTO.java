package com.confluxo.domain.user.dto;

import com.confluxo.domain.user.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRegisterDTO {

    private String username;
    private String password;
    private String email;
    private UserRole role;

}
