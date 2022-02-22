package ir.omidashouri.batch.itemwriters;

import ir.omidashouri.models.response.hamkaran.v1.HamkaranData;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HamkaranItemWriter implements ItemWriter<HamkaranData> {
    @Override
    public void write(List<? extends HamkaranData> items) throws Exception {
        System.out.println("Inside item Writer");
        items.stream()
                .map(HamkaranData::getId)
                .forEach(System.out::println);
    }
}
