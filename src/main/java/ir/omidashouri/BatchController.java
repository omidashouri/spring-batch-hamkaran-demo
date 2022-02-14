package ir.omidashouri;

import ir.omidashouri.models.dto.hamkaran.HamkaranFinancialResponseDto;
import ir.omidashouri.services.hamkaran.HamkaranService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller(value = "/")
@RequiredArgsConstructor
public class BatchController {

    private final HamkaranService hamkaranService;

    @GetMapping
    public String index() {
        HamkaranFinancialResponseDto hamkaranFinancialResponseDto = new HamkaranFinancialResponseDto();
        hamkaranFinancialResponseDto.setSearchQuery("limit=50&salemali=1400");
        hamkaranFinancialResponseDto = hamkaranService.searchHamkaranFinancialResponseBySearchQuery(hamkaranFinancialResponseDto);
        hamkaranFinancialResponseDto.toString();
        return "index";
    }

    @GetMapping("first")
    public String firstPage() {
        return "first";
    }
}
