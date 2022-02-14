package ir.omidashouri.assemblers.mainparts.hamkaran;


import ir.omidashouri.models.dto.hamkaran.HamkaranPaginationDto;
import ir.omidashouri.models.response.hamkaran.v1.HamkaranPagination;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = {HamkaranLinkMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL,
        mappingInheritanceStrategy = MappingInheritanceStrategy.AUTO_INHERIT_FROM_CONFIG,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface HamkaranPaginationMapper {

    @Named("hamkaranPaginationToHamkaranPaginationDto")
    @Mappings({
            @Mapping(source = "currentPage", target = "currentPage"),
            @Mapping(source = "pageCount", target = "pageCount"),
            @Mapping(source = "totalItems", target = "totalItems"),
            @Mapping(source = "limit", target = "limit"),
            @Mapping(source = "links", target = "links", qualifiedByName = {"HamkaranLinkMapper", "hamkaranLinksToHamkaranLinkDtoes"})
    })
    @BeanMapping(ignoreByDefault = true)
    HamkaranPaginationDto hamkaranPaginationToHamkaranPaginationDto(HamkaranPagination source);

    @Named("hamkaranPaginationDtoToHamkaranPagination")
    @Mappings({
            @Mapping(source = "links", target = "links", qualifiedByName = {"HamkaranLinkMapper", "hamkaranLinkDtosToHamkaranLinkes"})
    })
    @BeanMapping(ignoreByDefault = true)
    @InheritInverseConfiguration(name = "hamkaranPaginationToHamkaranPaginationDto")
    HamkaranPagination hamkaranPaginationDtoToHamkaranPagination(HamkaranPaginationDto source);


    @IterableMapping(qualifiedByName = "hamkaranPaginationToHamkaranPaginationDto")
    List<HamkaranPaginationDto> hamkaranPaginationsToHamkaranPaginationDtoes(List<HamkaranPagination> sources);

    @IterableMapping(qualifiedByName = "hamkaranPaginationDtoToHamkaranPagination")
    List<HamkaranPagination> hamkaranPaginationDtosToHamkaranPaginationes(List<HamkaranPaginationDto> sources);

}
