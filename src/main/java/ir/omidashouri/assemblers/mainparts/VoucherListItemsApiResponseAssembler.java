package ir.omidashouri.assemblers.mainparts;

import ir.omidashouri.controllers.mainparts.VoucherListItemsController;
import ir.omidashouri.mapper.mainparts.VoucherListItemsApiResponseMapper;
import ir.omidashouri.models.dto.mainparts.VoucherListItemsApiDto;
import ir.omidashouri.models.response.mainparts.VoucherListItemsApiResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class VoucherListItemsApiResponseAssembler extends RepresentationModelAssemblerSupport<VoucherListItemsApiDto, VoucherListItemsApiResponse> {

    private final VoucherListItemsApiResponseMapper voucherListItemsApiResponseMapper;


    public VoucherListItemsApiResponseAssembler(VoucherListItemsApiResponseMapper voucherListItemsApiResponseMapper) {
        super(VoucherListItemsController.class, VoucherListItemsApiResponse.class);
        this.voucherListItemsApiResponseMapper = voucherListItemsApiResponseMapper;
    }

    @Override
    public VoucherListItemsApiResponse toModel(VoucherListItemsApiDto voucherListItemsApiDto) {

        VoucherListItemsApiResponse voucherListItemsApiResponse = voucherListItemsApiResponseMapper
                .voucherListItemsApiDtoToVoucherListItemsApiResponse(voucherListItemsApiDto);

        if (voucherListItemsApiDto.getPublicId() != null) {
            voucherListItemsApiResponse
                    .add(linkTo(
                            methodOn(
                                    VoucherListItemsController.class)
                                    .findByPublicId(voucherListItemsApiDto.getPublicId()))
                            .withSelfRel());
        }

        return voucherListItemsApiResponse;
    }


    @Override
    public CollectionModel<VoucherListItemsApiResponse> toCollectionModel(Iterable<? extends VoucherListItemsApiDto> voucherListItemsApiDtos) {

        CollectionModel<VoucherListItemsApiResponse> voucherListItemsApiResponseCollectionModel = super.toCollectionModel(voucherListItemsApiDtos);

        Pageable pageable = Pageable.unpaged();

        voucherListItemsApiResponseCollectionModel
                .add(linkTo(methodOn(VoucherListItemsController.class).getVoucherListItems(pageable)).withRel("voucherListItemsApi"));

        return voucherListItemsApiResponseCollectionModel;
    }

}