package ir.omidashouri.repositories.mainparts;

import ir.omidashouri.domain.mainparts.VoucherDeleteItemsApiEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoucherDeleteItemsApiRepository extends CrudRepository<VoucherDeleteItemsApiEntity, Long> {

    Page<VoucherDeleteItemsApiEntity> findAllByDeleteDateTsIsNull(Pageable pageable);

    VoucherDeleteItemsApiEntity findByPublicId(String publicId);

    VoucherDeleteItemsApiEntity findTopByOrderByIdApiDesc();


}
