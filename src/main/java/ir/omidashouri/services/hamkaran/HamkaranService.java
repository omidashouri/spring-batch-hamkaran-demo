package ir.omidashouri.services.hamkaran;

import ir.omidashouri.models.dto.hamkaran.HamkaranAuthenticationTokenDto;
import ir.omidashouri.models.dto.hamkaran.HamkaranDeletedFinancialResponseDto;
import ir.omidashouri.models.dto.hamkaran.HamkaranFinancialResponseDto;


public interface HamkaranService {


    HamkaranAuthenticationTokenDto getToken(HamkaranAuthenticationTokenDto hamkaranAuthenticationTokenDto);

    HamkaranFinancialResponseDto searchHamkaranFinancialResponseBySearchQuery(HamkaranFinancialResponseDto hamkaranFinancialResponseDto);

    HamkaranDeletedFinancialResponseDto searchHamkaranDeletedFinancialResponseBySearchQuery(HamkaranDeletedFinancialResponseDto hamkaranDeletedFinancialResponseDto);
}
