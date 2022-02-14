package ir.omidashouri.models.response.mainparts;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.sql.Timestamp;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VoucherListItemsApiResponse extends RepresentationModel<VoucherListItemsApiResponse> {


    private Long idApi;

    private String publicId;

    private String tarh;

    private Long sanad;

    private Long atf;

    private Long moein;

    private String codeTafzil;

    private Long dl6;

    private String sharh;

    private Long bestankar;

    private Long bedehkar;

    private String tarikhSanad;

    private String tarikhMiladi;

    private Timestamp tarikhMiladiTs;

    private Long eslahiHazfi;

    private String saderKonande;

    private String akharinVirayeshConande;

    private String saleMali;

    private String lastCreateDate;

    private String lastEditDate;

    private String lastCreateDateMiladi;

    private Timestamp lastCreateDateMiladiTs;

    private String lastEditDateMiladi;

    private Timestamp lastEditDateMiladiTs;

    private Long voucherNumber;

    private Long voucherSequence;

    private Long voucherDailyNumber;

    private Long fiscalYearRef;

    private Long fiscalYear;

    private Timestamp createDateTs;

    private Timestamp editDateTs;

    private Timestamp deleteDateTs;

    private Long voucherDeleteItemsId;

    private Long recordStatus;
}
