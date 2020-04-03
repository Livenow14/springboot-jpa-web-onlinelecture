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


}
