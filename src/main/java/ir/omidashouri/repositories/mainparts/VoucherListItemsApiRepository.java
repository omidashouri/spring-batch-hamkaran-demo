package ir.omidashouri.repositories.mainparts;

import ir.omidashouri.domain.mainparts.VoucherListItemsApiEntity;
import ir.omidashouri.models.projections.mainparts.VoucherListItemsDeleted;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoucherListItemsApiRepository extends CrudRepository<VoucherListItemsApiEntity, Long> {

    Page<VoucherListItemsApiEntity> findAllByDeleteDateTsIsNull(Pageable pageable);

    VoucherListItemsApiEntity findByPublicId(String publicId);

    VoucherListItemsApiEntity findTopByOrderByLastCreateDateMiladiDescIdApiDesc();

    VoucherListItemsApiEntity findTopByOrderByLastEditDateMiladiDescIdApiDesc();

    VoucherListItemsApiEntity findTopByDeleteDateTsIsNotNullOrderByDeleteDateTsDescIdApiDesc();

    List<VoucherListItemsApiEntity> findAllByPublicIdIn(List<String> publicIds);


    @Query(name = "VoucherListItemsApiEntity.queryAllVoucherListItemsDeleted"
            , countName = "VoucherListItemsApiEntity.queryAllVoucherListItemsDeleted.count"
            , nativeQuery = true)
    List<VoucherListItemsDeleted> findVoucherListItemsApiDeleted();


}
