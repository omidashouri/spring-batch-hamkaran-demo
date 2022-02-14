package ir.omidashouri.models.response.hamkaran.v1;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HamkaranDeletedFinancialResponse {

    private HamkaranStatus status;

    private List<HamkaranDeleted> deleteds= new ArrayList<>();

    private HamkaranPagination pagination;

}
