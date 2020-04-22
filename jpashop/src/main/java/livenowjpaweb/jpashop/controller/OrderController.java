package livenowjpaweb.jpashop.controller;

import livenowjpaweb.jpashop.domain.Member;
import livenowjpaweb.jpashop.domain.item.Item;
import livenowjpaweb.jpashop.repository.OrderSearch;
import livenowjpaweb.jpashop.service.ItemService;
import livenowjpaweb.jpashop.service.MemberService;
import livenowjpaweb.jpashop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final MemberService memberService;
    private final ItemService itemService;

    @GetMapping("/order")
    public String creatForm(Model model){

        List<Member> members = memberService.findMembers();
        List<Item> items = itemService.findItems();

        model.addAttribute("members", members);
        model.addAttribute("items", items);

        return "/order/orderForm";
    }

    @PostMapping("/order")
    public String order(@RequestParam("memberId") Long memberId,    //파라미터를 memberId로해서 받았다
                        @RequestParam("itemId") Long itemId,        //RequestParam은 templates의 select옵션의 memberId나
                        @RequestParam("count") int count){          //ItemId의 선택된 value가 submit으로 넘어오게 되는것

        orderService.order(memberId, itemId, count);    //이러면 order로직이 돌아감, 이렇게 비지니스 로직으로 보내게 짜는게 좋다.
        return "redirect:/order";                      //여려개 상품을 선택할 수 있게 해보자!



    }

}
