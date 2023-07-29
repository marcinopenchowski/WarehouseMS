package com.wms.dto;

import com.wms.entity.enums.AuthRole;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CredentialsDto {
    private String login;
    private char[] password;
    private AuthRole authRole;
}
