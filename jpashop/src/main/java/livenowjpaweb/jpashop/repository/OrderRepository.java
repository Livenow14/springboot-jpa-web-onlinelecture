package livenowjpaweb.jpashop.repository;

import livenowjpaweb.jpashop.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class OrderRepository {
    private final EntityManager em;

    public void save(Order order){
        em.persist(order); //영속성 컨텍스트에 order객체를 넣음 transaction이 커밋되는 시점에 디비에 반영됨
    }
    public Order findOne(Long id){
        return em.find(Order.class, id );
    }

    // public List<Order> findAll(DrderSearch orderSearch){} //나중에 만듬

}
