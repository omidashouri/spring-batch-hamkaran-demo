package ir.omidashouri.services.hamkaran;


import ir.omidashouri.mapper.hamkaran.HamkaranAuthenticationTokenResponseMapper;
import ir.omidashouri.mapper.mainparts.hamkaran.HamkaranDataVoucherListItemsApiDtoMapper;
import ir.omidashouri.mapper.mainparts.hamkaran.HamkaranDataVoucherListItemsApiDtoMapperImpl;
import ir.omidashouri.mapper.mainparts.hamkaran.HamkaranDeletedFinancialResponseMapper;
import ir.omidashouri.mapper.mainparts.hamkaran.HamkaranFinancialResponseMapper;
import ir.omidashouri.models.dto.hamkaran.HamkaranAuthenticationTokenDto;
import ir.omidashouri.models.dto.hamkaran.HamkaranDeletedFinancialResponseDto;
import ir.omidashouri.models.dto.hamkaran.HamkaranFinancialResponseDto;
import ir.omidashouri.models.dto.mainparts.VoucherListItemsApiDto;
import ir.omidashouri.models.response.hamkaran.v1.HamkaranAuthenticationTokenResponse;
import ir.omidashouri.models.response.hamkaran.v1.HamkaranData;
import ir.omidashouri.models.response.hamkaran.v1.HamkaranDeletedFinancialResponse;
import ir.omidashouri.models.response.hamkaran.v1.HamkaranFinancialResponse;
import ir.omidashouri.security.HamkaranCredential;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class HamkaranServiceImpl implements HamkaranService {

    private final HamkaranAuthenticationTokenResponseMapper hamkaranAuthenticationTokenResponseMapper;
    private final HamkaranFinancialResponseMapper hamkaranFinancialResponseMapper;
    private final HamkaranDeletedFinancialResponseMapper hamkaranDeletedFinancialResponseMapper;
    private final HamkaranCredential hamkaranCredential;
    private final RestTemplate restTemplate;

    private final HamkaranDataVoucherListItemsApiDtoMapper hamkaranDataVoucherListItemsApiDtoMapper;
    private final List<HamkaranData> hamkaranDataz = new ArrayList<>();

    @Override
    public HamkaranAuthenticationTokenDto getToken(HamkaranAuthenticationTokenDto hamkaranAuthenticationTokenDto) {

        String completeGetTokenUri = String.format(hamkaranCredential.getApiUriGetToken(),
                hamkaranAuthenticationTokenDto.getCode());

        ResponseEntity<HamkaranAuthenticationTokenResponse> hamkaranAuthenticationTokenResponseResponseEntity =
                restTemplate.exchange(this.uriComponentsBuilderToString(completeGetTokenUri),
                        HttpMethod.GET, this.requestHttpEntity(false, hamkaranCredential),
                        HamkaranAuthenticationTokenResponse.class);

        HamkaranAuthenticationTokenResponse hamkaranAuthenticationTokenResponse =
                hamkaranAuthenticationTokenResponseResponseEntity.getBody();

        hamkaranAuthenticationTokenResponseMapper
                .updateHamkaranAuthenticationTokenDtoByHamkaranAuthenticationTokenResponse(hamkaranAuthenticationTokenDto, hamkaranAuthenticationTokenResponse);

        return hamkaranAuthenticationTokenDto;
    }

    @Override
    public HamkaranFinancialResponseDto searchHamkaranFinancialResponseBySearchQuery(HamkaranFinancialResponseDto hamkaranFinancialResponseDto) {

        HamkaranFinancialResponse hamkaranFinancialResponse;

        String searchQuery = hamkaranFinancialResponseDto.getSearchQuery();
        String completeFinancialUri = String.format(hamkaranCredential.getApiUriGetFinancial(), searchQuery);

        ResponseEntity<HamkaranFinancialResponse> hamkaranFinancialResponseEntity =
                restTemplate.exchange(this.uriComponentsBuilderToString(completeFinancialUri),
                        HttpMethod.GET, this.requestHttpEntity(true, hamkaranCredential),
                        HamkaranFinancialResponse.class);

        hamkaranFinancialResponse = hamkaranFinancialResponseEntity.getBody();
//todo: later handle it
        if (hamkaranFinancialResponse == null) {

        }

        hamkaranFinancialResponseDto = hamkaranFinancialResponseMapper
                .hamkaranFinancialResponseToHamkaranFinancialResponseDto(hamkaranFinancialResponse);

        hamkaranFinancialResponseDto.setSearchQuery(searchQuery);

        return hamkaranFinancialResponseDto;
    }

//todo: record count
//    todo:save in a list
//    todo: get single record
    public List<HamkaranData> getHamkaranDataFromService(String limit){

        List<HamkaranData> hamkaranDataList = new ArrayList<>();

        HamkaranFinancialResponseDto hamkaranFinancialResponseDto = new HamkaranFinancialResponseDto();
        hamkaranFinancialResponseDto.setSearchQuery("limit="+limit);
        hamkaranFinancialResponseDto = this.searchHamkaranFinancialResponseBySearchQuery(hamkaranFinancialResponseDto);
        hamkaranDataList = hamkaranFinancialResponseDto
                .getVoucherListItemsApiDtos()
                .stream()
                .map(vli -> new HamkaranDataVoucherListItemsApiDtoMapperImpl().voucherListItemsApiToHamkaranData(vli))
                .collect(Collectors.toList());

        return hamkaranDataList;
    }


    Function<Object, VoucherListItemsApiDto> streamListFunction =
            sv -> (VoucherListItemsApiDto) sv;

    @Override
    public HamkaranDeletedFinancialResponseDto searchHamkaranDeletedFinancialResponseBySearchQuery(HamkaranDeletedFinancialResponseDto hamkaranDeletedFinancialResponseDto) {

        HamkaranDeletedFinancialResponse hamkaranDeletedFinancialResponse;

        String searchQuery = hamkaranDeletedFinancialResponseDto.getSearchQuery();
        String completeDeletedFinancialUri = String.format(hamkaranCredential.getApiUriGetDeletedFinancial(), searchQuery);

        ResponseEntity<HamkaranDeletedFinancialResponse> hamkaranDeletedFinancialResponseEntity =
                restTemplate.exchange(this.uriComponentsBuilderToString(completeDeletedFinancialUri),
                        HttpMethod.GET, this.requestHttpEntity(true, hamkaranCredential),
                        HamkaranDeletedFinancialResponse.class);

        hamkaranDeletedFinancialResponse = hamkaranDeletedFinancialResponseEntity.getBody();
//todo: later handle it
        if (hamkaranDeletedFinancialResponse == null) {

        }

        hamkaranDeletedFinancialResponseDto = hamkaranDeletedFinancialResponseMapper
                .hamkaranDeletedFinancialResponseToHamkaranDeletedFinancialResponseDto(hamkaranDeletedFinancialResponse);

        hamkaranDeletedFinancialResponseDto.setSearchQuery(searchQuery);

        return hamkaranDeletedFinancialResponseDto;
    }

/*    @Override
    public void getFinancial() {
        HamkaranFinancialResponse hamkaranFinancialResponse = new HamkaranFinancialResponse();

        String completeFinancialUri = hamkaranCredential.getApiUriGetFinancial();

        ResponseEntity<HamkaranFinancialResponse> hamkaranAuthenticationTokenResponseResponseEntity =
                restTemplate.exchange(this.uriComponentsBuilderToString(completeFinancialUri),
                        HttpMethod.GET, this.requestHttpEntity(true, hamkaranCredential),
                        HamkaranFinancialResponse.class);

        hamkaranFinancialResponse = hamkaranAuthenticationTokenResponseResponseEntity.getBody();
    }*/

    protected String uriComponentsBuilderToString(String stringUri) {
        return UriComponentsBuilder.fromUriString(stringUri).toUriString();
    }

    protected HttpEntity<?> requestHttpEntity(boolean needToken, HamkaranCredential hamkaranCredential) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        if (needToken)
            headers.set("api-key", hamkaranCredential.getToken());

        HttpEntity<?> requestHttpEntity = new HttpEntity<>(null, headers);
        return requestHttpEntity;
    }
}
