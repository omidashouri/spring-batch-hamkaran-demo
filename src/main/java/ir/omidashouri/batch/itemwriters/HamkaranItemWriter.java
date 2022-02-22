package ir.omidashouri.batch.itemwriters;

import ir.omidashouri.models.dto.hamkaran.HamkaranFinancialResponseDto;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HamkaranItemWriter implements ItemWriter<HamkaranFinancialResponseDto> {
    @Override
    public void write(List<? extends HamkaranFinancialResponseDto> items) throws Exception {
        System.out.println("Inside item Writer");
        items.stream()
                .map(HamkaranFinancialResponseDto::getVoucherListItemsApiDtos)
                .forEach(System.out::println);
    }
}
