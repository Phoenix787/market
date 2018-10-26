package ru.xenya.market.ui.dataproviders;

import com.vaadin.flow.data.provider.AbstractBackEndDataProvider;
import com.vaadin.flow.data.provider.Query;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.springframework.data.domain.PageRequest;
import ru.xenya.market.backend.data.entity.ItemPrice;

import java.util.stream.Stream;


//todo ItemPriceService, ItemPriceRepository
@SpringComponent
public class PriceDataProvider extends AbstractBackEndDataProvider<ItemPrice, String> {

    private final ItemPriceService itemPriceService;

    public PriceDataProvider(ItemPriceService itemPriceService) {
        this.itemPriceService = itemPriceService;
    }

    @Override
    protected Stream<ItemPrice> fetchFromBackEnd(Query<ItemPrice, String> query) {
        return itemPriceService.findAnyMatching(query.getFilter(), PageRequest.of(query.getOffset(), query.getLimit()))
                .stream();
    }

    @Override
    protected int sizeInBackEnd(Query<ItemPrice, String> query) {
        return (int) itemPriceService.countAnyMatching(query.getFilter());
    }
}
