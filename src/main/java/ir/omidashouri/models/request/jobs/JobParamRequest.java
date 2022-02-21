package ir.omidashouri.models.request.jobs;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobParamRequest {

    private String paramKey;

    private String paramValue;


}
