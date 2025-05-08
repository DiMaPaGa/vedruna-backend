package com.vedruna.vedruna_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExpoPushRequest {

    private String expoPushId;
    private String userId;
    
}
