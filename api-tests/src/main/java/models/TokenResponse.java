package models;

import lombok.Getter;

@Getter
public class TokenResponse {
    private String token;
    private String expires;
    private String status;
    private String result;
}
