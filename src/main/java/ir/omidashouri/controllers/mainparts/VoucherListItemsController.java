package ir.omidashouri.controllers.mainparts;



import ir.omidashouri.assemblers.mainparts.VoucherListItemsApiResponseAssembler;
import ir.omidashouri.domain.mainparts.VoucherListItemsApiEntity;
import ir.omidashouri.exceptions.NotFoundException;
import ir.omidashouri.mapper.mainparts.VoucherListItemsApiMapper;
import ir.omidashouri.mapper.mainparts.VoucherListItemsApiResponseMapper;
import ir.omidashouri.models.dto.mainparts.VoucherListItemsApiDto;
import ir.omidashouri.models.response.mainparts.VoucherListItemsApiResponse;
import ir.omidashouri.services.mainparts.VoucherListItemsService;
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
@RequestMapping("/api/v1/voucherListItems")
@RequiredArgsConstructor
public class VoucherListItemsController {


    private final VoucherListItemsService voucherListItemsService;
    private final VoucherListItemsApiMapper voucherListItemsApiMapper;
    private final VoucherListItemsApiResponseMapper voucherListItemsApiResponseMapper;
    private final VoucherListItemsApiResponseAssembler voucherListItemsApiResponseAssembler;
    private final PagedResourcesAssembler<VoucherListItemsApiDto> voucherListItemsApiDtoPagedResourcesAssembler;





    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<PagedModel<VoucherListItemsApiResponse>> getVoucherListItems(@SortDefault(sort = "lastCreateDateMiladi", direction = Sort.Direction.DESC)
                                                                                       @PageableDefault(page = 0, size = 10, value = 10)
                                                                                               Pageable pageable) {

        Page<VoucherListItemsApiEntity> voucherListItemsPages =
                voucherListItemsService.findAllByDeleteStatusIsNull(pageable);

        Page<VoucherListItemsApiDto> voucherListItemsApiDtoPage = voucherListItemsPages
                .map(voucherListItemsApiMapper::voucherListItemsApiToVoucherListItemsApiDto);

        PagedModel<VoucherListItemsApiResponse> voucherListItemsResponsePagedModel = voucherListItemsApiDtoPagedResourcesAssembler
                .toModel(voucherListItemsApiDtoPage, voucherListItemsApiResponseAssembler);

        return ResponseEntity.ok(voucherListItemsResponsePagedModel);
    }


    @GetMapping(path = "/{voucherListItemsPublicId}",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> findByPublicId(@PathVariable String voucherListItemsPublicId) {

        VoucherListItemsApiEntity voucherListItemsApi = voucherListItemsService.findByPublicId(voucherListItemsPublicId);
        if (voucherListItemsApi == null) {
            throw new NotFoundException("requested voucher list item not found");
        }

        VoucherListItemsApiDto voucherListItemsApiDto = voucherListItemsApiMapper
                .voucherListItemsApiToVoucherListItemsApiDto(voucherListItemsApi);

        VoucherListItemsApiResponse voucherListItemsApiResponse =
                voucherListItemsApiResponseAssembler.toModel(voucherListItemsApiDto);

        return ResponseEntity.ok(voucherListItemsApiResponse);

    }



    @GetMapping(path = "/lastCreatedRecord",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> findLastCreatedRecord() {

        VoucherListItemsApiEntity voucherListItemsApi = voucherListItemsService.findLastRecordOrderByCreateDate();
        if (voucherListItemsApi == null) {
            throw new NotFoundException("requested voucher list item not found");
        }

        VoucherListItemsApiDto voucherListItemsApiDto = voucherListItemsApiMapper
                .voucherListItemsApiToVoucherListItemsApiDto(voucherListItemsApi);

        VoucherListItemsApiResponse voucherListItemsApiResponse =
                voucherListItemsApiResponseAssembler.toModel(voucherListItemsApiDto);

        return ResponseEntity.ok(voucherListItemsApiResponse);
    }




    @GetMapping(path = "/lastEditedRecord",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> findLastEditedRecord() {

        VoucherListItemsApiEntity voucherListItemsApi = voucherListItemsService.findLastRecordOrderByEditDate();
        if (voucherListItemsApi == null) {
            throw new NotFoundException("requested voucher list item not found");
        }

        VoucherListItemsApiDto voucherListItemsApiDto = voucherListItemsApiMapper
                .voucherListItemsApiToVoucherListItemsApiDto(voucherListItemsApi);

        VoucherListItemsApiResponse voucherListItemsApiResponse =
                voucherListItemsApiResponseAssembler.toModel(voucherListItemsApiDto);

        return ResponseEntity.ok(voucherListItemsApiResponse);
    }




    @GetMapping(path = "/lastDeletedRecord",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> findLastDeletedRecord() {

        VoucherListItemsApiEntity voucherListItemsApi = voucherListItemsService.findLastRecordOrderByDeleteDateTs();
        if (voucherListItemsApi == null) {
            throw new NotFoundException("requested voucher list item not found");
        }

        VoucherListItemsApiDto voucherListItemsApiDto = voucherListItemsApiMapper
                .voucherListItemsApiToVoucherListItemsApiDto(voucherListItemsApi);

        VoucherListItemsApiResponse voucherListItemsApiResponse =
                voucherListItemsApiResponseAssembler.toModel(voucherListItemsApiDto);

        return ResponseEntity.ok(voucherListItemsApiResponse);
    }




}
