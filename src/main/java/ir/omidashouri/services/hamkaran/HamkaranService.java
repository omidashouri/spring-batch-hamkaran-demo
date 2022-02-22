package ir.omidashouri.services.hamkaran;

import ir.omidashouri.models.dto.hamkaran.HamkaranAuthenticationTokenDto;
import ir.omidashouri.models.dto.hamkaran.HamkaranDeletedFinancialResponseDto;
import ir.omidashouri.models.dto.hamkaran.HamkaranFinancialResponseDto;
import ir.omidashouri.models.response.hamkaran.v1.HamkaranData;

import java.util.List;


public interface HamkaranService {


    HamkaranAuthenticationTokenDto getToken(HamkaranAuthenticationTokenDto hamkaranAuthenticationTokenDto);

    HamkaranFinancialResponseDto searchHamkaranFinancialResponseBySearchQuery(HamkaranFinancialResponseDto hamkaranFinancialResponseDto);

    //todo: record count
    //    todo:save in a list
    //    todo: get single record
    List<HamkaranData> getHamkaranDataFromService(String limit);

    HamkaranData getHamkaranDataForJob(String limit);

    HamkaranDeletedFinancialResponseDto searchHamkaranDeletedFinancialResponseBySearchQuery(HamkaranDeletedFinancialResponseDto hamkaranDeletedFinancialResponseDto);
}
