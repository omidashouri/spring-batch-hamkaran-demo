package ir.omidashouri.mapper.mainparts.hamkaran;


import ir.omidashouri.models.dto.hamkaran.HamkaranStatusDto;
import ir.omidashouri.models.response.hamkaran.v1.HamkaranStatus;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL,
        mappingInheritanceStrategy = MappingInheritanceStrategy.AUTO_INHERIT_FROM_CONFIG,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface HamkaranStatusMapper {

    @Named("hamkaranStatusToHamkaranStatusDto")
    @Mappings({
            @Mapping(source = "success", target = "success"),
            @Mapping(source = "statusCode", target = "statusCode"),
            @Mapping(source = "message", target = "message")
    })
    @BeanMapping(ignoreByDefault = true)
    HamkaranStatusDto hamkaranStatusToHamkaranStatusDto(HamkaranStatus source);

    @Named("hamkaranStatusDtoToHamkaranStatus")
    @BeanMapping(ignoreByDefault = true)
    @InheritInverseConfiguration(name = "hamkaranStatusToHamkaranStatusDto")
    HamkaranStatus hamkaranStatusDtoToHamkaranStatus(HamkaranStatusDto source);


    @IterableMapping(qualifiedByName = "hamkaranStatusToHamkaranStatusDto")
    List<HamkaranStatusDto> hamkaranStatusesToHamkaranStatusDtoes(List<HamkaranStatus> sources);

    @IterableMapping(qualifiedByName = "hamkaranStatusDtoToHamkaranStatus")
    List<HamkaranStatus> hamkaranStatusDtosToHamkaranStatuses(List<HamkaranStatusDto> sources);
}
