package ir.omidashouri;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller(value = "/")
public class BatchController {

    @GetMapping
    public String index(){
        return "index";
    }

    @GetMapping("first")
    public String firstPage(){
        return "first";
    }
}
