package com.example.common_library.commons.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {
    @NotNull
    private String name;
    @NotNull
    private String email;
    @NotNull
    private String password;
}
