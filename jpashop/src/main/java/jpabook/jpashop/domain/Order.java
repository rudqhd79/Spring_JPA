package jpabook.jpashop.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

	@Id
	@GeneratedValue
	@Column(name = "order_id")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;
	
	// cascade = CascadeType.ALL은 현재 Entity(Order)를 저장하면 orderItems도 저장된다
	// cascade는 Order가 현재 Delivery와 OrderItem 객체의 주인이기 때문에 Order에 적용가능하다
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<OrderItem> orderItems = new ArrayList<>();
	
	// cascade 미적용 시
	/*
	 * persist(orderItemA) persist(orderItemB) persist(orderItemC) persist(order)
	 */
	// cascade 적용 시
	/* persist(order) */
	
	// 1 대 1 관계에서는 연관관계 주인을 정할 수 있다 (지금은 order 상태)
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "delivery_id")
	private Delivery delivery;
	
	private LocalDateTime orderDate;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus status;	 // 주문상태 [Order, Cancel]
	
	// 연관관계 편의 메소드 (Order 주변의 연관관계에 대한 Entity 세팅)
	public void setMember(Member member) {
		this.member = member;
		member.getOrders().add(this);
	}
	
	public void addOrderItem(OrderItem orderItem) {
		orderItems.add(orderItem);
		orderItem.setOrder(this);
	}
	
	public void setDelivery (Delivery delivery) {
		this.delivery = delivery;
		delivery.setOrder(this);
	}
	
	/*
	 * public static void main(String[] args) { Member member = new Member(); Order
	 * order = new Order();
	 * 
	 * member.getOrders().add(order); order.setMember(member); }
	 */
	
	// 생성 메서드
	public static Order createOrder(Member member, Delivery delivery, OrderItem... orderItems) {
		Order order = new Order();
		order.setMember(member);
		order.setDelivery(delivery);
		
		for (OrderItem orderItem : orderItems) {
			order.addOrderItem(orderItem);
		}
		order.setStatus(OrderStatus.ORDER);
		order.setOrderDate(LocalDateTime.now());
		return order;
	}
	
	// 비즈니스 로직
	// Cancel Order
	public void cancel() {
		if (delivery.getStatus() == DeliveryStatus.COMP) {
			throw new IllegalStateException("이미 배송이 완료된 상품입니다.");
		}
		
		this.setStatus(OrderStatus.CANCEL);
		for (OrderItem orderItem : orderItems) {
			orderItem.cancel();
		}
	}
	
	// 조회 로직
	// 전체 주문 가격 (select)
	public int getTotalPrice() {
		int totalPrice = 0;
		for (OrderItem orderItem : orderItems) {
			totalPrice += orderItem.getTotalPrice();
		}
		return totalPrice;
	}
	
}
