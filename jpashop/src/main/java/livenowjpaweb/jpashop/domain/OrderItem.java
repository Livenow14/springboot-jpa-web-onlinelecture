package livenowjpaweb.jpashop.domain;
import livenowjpaweb.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Table(name = "order_item")
@Getter
@Setter
public class OrderItem {
    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = LAZY)  //Manythoone은 default가 Eager이기 때문에 다바꿔야함 ctrl+shift+f 로 찾기
    @JoinColumn(name="item_id")    //FK 설정
    private Item item;

    @ManyToOne(fetch = LAZY)  //Manythoone은 default가 Eager이기 때문에 다바꿔야함
    @JoinColumn(name="order_id")    //FK 설정
    private Order order;

    private int orderPrice;
    private int count;

    //==생성 메서드 == //
    public static OrderItem createOrderItem(Item item, int orderPrice, int count){
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);

        item.removeStock(count);
        return orderItem;
    }

    //==비즈니스 로직 ==//
    //주문취소
    public void cancel() {
        getItem().addStock(count);
    }

    //==w조회 로직 ==//
    //주문 상품 전체 가격 조회
    public int getTotalPrice() {
        return getOrderPrice()* getCount();
    }
}
