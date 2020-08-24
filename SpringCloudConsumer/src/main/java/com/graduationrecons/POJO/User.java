package com.graduationrecons.POJO;


import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class User implements Serializable
{
    private String username;
    private String mm;
    private int permission;
    private int primary_key;
    private int pass;
    private String email;
    private String code;


}
