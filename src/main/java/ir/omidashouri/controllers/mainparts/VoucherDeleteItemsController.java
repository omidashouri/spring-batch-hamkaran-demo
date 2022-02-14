package ir.omidashouri.controllers.mainparts;

import ir.omidashouri.assemblers.mainparts.VoucherDeleteItemsApiResponseAssembler;
import ir.omidashouri.domain.mainparts.VoucherDeleteItemsApiEntity;
import ir.omidashouri.exceptions.NotFoundException;
import ir.omidashouri.mapper.mainparts.VoucherDeleteItemsApiMapper;
import ir.omidashouri.mapper.mainparts.VoucherDeleteItemsApiResponseMapper;
import ir.omidashouri.models.dto.mainparts.VoucherDeleteItemsApiDto;
import ir.omidashouri.models.response.mainparts.VoucherDeleteItemsApiResponse;
import ir.omidashouri.services.mainparts.VoucherDeleteItemsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.data.web.SortDefault;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/voucherDeleteItems")
@RequiredArgsConstructor
public class VoucherDeleteItemsController {

    private final VoucherDeleteItemsService voucherDeleteItemsService;
    private final VoucherDeleteItemsApiMapper voucherDeleteItemsApiMapper;
    private final VoucherDeleteItemsApiResponseMapper voucherDeleteItemsApiResponseMapper;
    private final VoucherDeleteItemsApiResponseAssembler voucherDeleteItemsApiResponseAssembler;
    private final PagedResourcesAssembler<VoucherDeleteItemsApiDto> voucherDeleteItemsApiDtoPagedResourcesAssembler;



    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<PagedModel<VoucherDeleteItemsApiResponse>> getVoucherDeleteItems(
                                                                                       @SortDefault(sort = "dateOfRemoval", direction = Sort.Direction.DESC)
                                                                                       @PageableDefault(page = 0, size = 10, value = 10)
                                                                                               Pageable pageable) {

        Page<VoucherDeleteItemsApiEntity> voucherDeleteItemsPages =
                voucherDeleteItemsService.findAllByDeleteStatusIsNull(pageable);

        Page<VoucherDeleteItemsApiDto> voucherDeleteItemsApiDtoPage = voucherDeleteItemsPages
                .map(voucherDeleteItemsApiMapper::voucherDeleteItemsApiToVoucherDeleteItemsApiDto);

        PagedModel<VoucherDeleteItemsApiResponse> voucherDeleteItemsResponsePagedModel = voucherDeleteItemsApiDtoPagedResourcesAssembler
                .toModel(voucherDeleteItemsApiDtoPage, voucherDeleteItemsApiResponseAssembler);

        return ResponseEntity.ok(voucherDeleteItemsResponsePagedModel);
    }




    @GetMapping(path = "/{voucherDeleteItemsPublicId}",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> findByPublicId(@PathVariable String voucherDeleteItemsPublicId) {

        VoucherDeleteItemsApiEntity voucherDeleteItemsApi = voucherDeleteItemsService.findByPublicId(voucherDeleteItemsPublicId);
        if (voucherDeleteItemsApi == null) {
            throw new NotFoundException("requested voucher list item not found");
        }

        VoucherDeleteItemsApiDto voucherDeleteItemsApiDto = voucherDeleteItemsApiMapper
                .voucherDeleteItemsApiToVoucherDeleteItemsApiDto(voucherDeleteItemsApi);

        VoucherDeleteItemsApiResponse voucherDeleteItemsApiResponse =
                voucherDeleteItemsApiResponseAssembler.toModel(voucherDeleteItemsApiDto);

        return ResponseEntity.ok(voucherDeleteItemsApiResponse);

    }



    @GetMapping(path = "/lastRecordOrderByIdApi",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> findLastInsertedRecord() {

        VoucherDeleteItemsApiEntity voucherDeleteItemsApi = voucherDeleteItemsService.findLastRecordOrderByApiId();
        if (voucherDeleteItemsApi == null) {
            throw new NotFoundException("requested voucher list item not found");
        }

        VoucherDeleteItemsApiDto voucherDeleteItemsApiDto = voucherDeleteItemsApiMapper
                .voucherDeleteItemsApiToVoucherDeleteItemsApiDto(voucherDeleteItemsApi);

        VoucherDeleteItemsApiResponse voucherDeleteItemsApiResponse =
                voucherDeleteItemsApiResponseAssembler.toModel(voucherDeleteItemsApiDto);

        return ResponseEntity.ok(voucherDeleteItemsApiResponse);

    }
}
