package com.example.demo.common.Vo;

import lombok.Data;

@Data
public class ResponseJson {

    private String status;

    private String code;

    private String message;

    public ResponseJson(String s, String hello_word, String s1) {
    }

    public ResponseJson() {

    }
}
