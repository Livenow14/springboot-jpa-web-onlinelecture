package livenowjpaweb.jpashop.controller;


import livenowjpaweb.jpashop.domain.item.Book;
import livenowjpaweb.jpashop.domain.item.Item;
import livenowjpaweb.jpashop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/items/new")
    public String createFrom(Model model){
        model.addAttribute("form", new BookForm());
        return "items/createItemForm";
    }

    @PostMapping("/items/new")
    public String create(BookForm form){

        Book book = new Book();                         //이것은 이후에 바꿔야한다. set을 이렇게 하는건 좋지않다.
        book.setName(form.getName());                   //실무에서는 setter를 다 랄림
        book.setName(form.getName());
        book.setPrice(form.getPrice());
        book.setStockQuantity(form.getStockQuantity());
        book.setAuthor(form.getAuthor());
        book.setIsbn(form.getIsbn());

        itemService.saveItem(book);
        return "redirect:/";
    }

    @GetMapping("/items")
    public String list(Model model){
        List<Item> items = itemService.findItems();
        model.addAttribute("items", items);     //"items"라는 name에 items라는 value를 넣어두고 뿌린다. 그러면
                                                             //itemList.html에서 받는다.
        return "items/itemList";
    }
}
