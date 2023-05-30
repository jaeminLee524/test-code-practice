package sample.cafekiosk.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import sample.cafekiosk.domain.stock.Stock;

@ActiveProfiles("test")
@DataJpaTest
class StockRepositoryTest {

    @Autowired
    private StockRepository stockRepository;

    @DisplayName("상품 번호 리스트로 재고르 조회한다.")
    @Test
    void test() {
        // given
        Stock stock1 = Stock.create("001", 2);
        Stock stock2 = Stock.create("002", 3);
        Stock stock3 = Stock.create("003", 4);
        stockRepository.saveAll(List.of(stock1, stock2, stock3));

        // when
        List<Stock> findStocks = stockRepository.findAllByProductNumberIn(List.of("001", "002"));

        // then
        assertThat(findStocks).hasSize(2)
            .extracting("productNumber", "quantity")
            .containsExactlyInAnyOrder(
                tuple("001", 2),
                tuple("002", 3)
            );
    }
}