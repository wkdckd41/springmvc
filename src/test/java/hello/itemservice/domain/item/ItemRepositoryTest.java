package hello.itemservice.domain.item;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    // 테스트가 끝날 때마다 실행되는 메서드
    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }

    // save() 메서드 테스트
    @Test
    void save() {
        // given
        Item item = new Item("itemA", 10000, 10);

        // when
        Item savedItem = itemRepository.save(item);

        // then
        Item findItem = itemRepository.findById(item.getId());
        assertEquals(findItem, savedItem);
    }

    // 전체조회 테스트
    @Test
    void findAll() {
        // given
        Item item1 = new Item("item1", 10000, 10);
        Item item2 = new Item("item2", 20000, 20);

        itemRepository.save(item1);
        itemRepository.save(item2);

        // when
        // then
        assertEquals(itemRepository.findAll().size(), 2);
    }

    // update() 메서드 테스트
    @Test
    void updateItem() {
        // given
        Item item = new Item("item1", 10000, 10);
        Item savedItem = itemRepository.save(item);
        Long itemId = savedItem.getId();

        // when
        Item updateParam = new Item("item2", 20000, 30);
        itemRepository.update(itemId, updateParam);

        // then
        Item findItem = itemRepository.findById(itemId);
        assertEquals(findItem.getItemName(), updateParam.getItemName());
        assertEquals(findItem.getPrice(), updateParam.getPrice());
        assertEquals(findItem.getQuantity(), updateParam.getQuantity());
    }

}