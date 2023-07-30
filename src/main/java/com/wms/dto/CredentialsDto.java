package com.wms.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CredentialsDto {
    private String login;
    private char[] password;
    private String authRoles;
}
