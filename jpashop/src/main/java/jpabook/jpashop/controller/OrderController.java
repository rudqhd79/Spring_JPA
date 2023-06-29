package jpabook.jpashop.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderSearch;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.service.ItemService;
import jpabook.jpashop.service.MemberService;
import jpabook.jpashop.service.OrderService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class OrderController {

	private final OrderService orderService;
	private final MemberService memberService;
	private final ItemService itemService;
	
	@GetMapping("/order")
	public String createForm(Model model) {
		
		List<Member> members = memberService.findMembers();
		List<Item> items = itemService.findItems();
		
		model.addAttribute("members", members);
		model.addAttribute("items", items);
		
		return "order/orderForm";
	}
	
	// requestparam은 name을 넣어주고 submit 시 th:value의 넘어온 값을 얻는다
	@PostMapping("/order")
	public String order(@RequestParam("memberId") Long memberId, @RequestParam("itemId") Long itemId, @RequestParam("count") int count) {
		
		orderService.order(memberId, itemId, count);
		return "redirect:/orders";
	}
	
	// ModelAttribute와 RequestParam의 차이점은 1:1 매핑, 객체 매핑이냐 인 것
	// ModelAttribute를 RequestParam으로 변환하려면 변환하려는 정보를 일일이 하나하나 다 담아서 뿌려야 한다
	// ModelAttirubte는 th:object 객체를 받는다
	@GetMapping("/orders")
	public String orderList(@ModelAttribute("orderSearch") OrderSearch orderSearch, Model model) {
		List<Order> orders = orderService.findOrders(orderSearch);
		model.addAttribute("orders", orders);
		return "order/orderList";
	}
	
	@PostMapping(value = "/orders/{orderId}/cancel")
	 public String cancelOrder(@PathVariable("orderId") Long orderId) {
	 orderService.cancelOrder(orderId);
	 return "redirect:/orders";
	 }
}
