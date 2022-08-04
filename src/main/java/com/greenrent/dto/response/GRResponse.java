package com.greenrent.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GRResponse {
    boolean success;
    String message;
}
