package ir.omidashouri.services.mainparts;

import ir.omidashouri.domain.mainparts.VoucherDeleteItemsApiEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VoucherDeleteItemsService {

    Page<VoucherDeleteItemsApiEntity> findAllByDeleteStatusIsNull(Pageable pageable);

    VoucherDeleteItemsApiEntity findByPublicId(String publicId);

    VoucherDeleteItemsApiEntity findLastRecordOrderByApiId();
}
