package livenowjpaweb.jpashop.domain;


import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

@Entity
@Table(name ="orders")
@Getter
@Setter
public class Order {
    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = LAZY)  //Manythoone은 default가 Eager이기 때문에 다바꿔야함
    @JoinColumn(name="member_id")       //FK해줌
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)   //각자 persist 설정하는것을 하나로 줄여줌
    private List<OrderItem> orderItems = new ArrayList<>();

    // persist(orderItemaA)
    // persist(orderItemaB)
    // persist(orderItemaC)
    //persist(order) 를 해주는 것을 cascade를 쓰면

    //persis(order)만 쓰면됨

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)                  //엑세스를 많이하는 곳에 FK를 둠  oto 에서 어디에 두냐는 상관없음
    @JoinColumn(name="delivery_id")
    private Delivery delivery;

    //order_date로 들어감 springPhysicalNamingStrategy 하이버네이트에서 저절로 바꿔줌
    private LocalDateTime orderDate;        //주문시간

    @Enumerated(EnumType.STRING)
    private OrderStatus status; // 주문상태[ORDER, CANCEL]

    //==연관관계 메서드 ==// 양방향일때 연관관계를 한번에 설정
    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }
    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery){
        this.delivery = delivery;
        delivery.setOrder(this);
    }



}
