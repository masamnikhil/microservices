package com.microservice.order_ms.external;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

    private Long userid;

    private String name;

    private String username;

    private String email;

    private  String password;
}
