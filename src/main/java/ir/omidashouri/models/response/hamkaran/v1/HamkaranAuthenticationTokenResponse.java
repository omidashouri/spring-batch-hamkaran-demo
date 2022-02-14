package ir.omidashouri.models.response.hamkaran.v1;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HamkaranAuthenticationTokenResponse implements Serializable {

    private String success;
    private String token;
    private String message;
}
