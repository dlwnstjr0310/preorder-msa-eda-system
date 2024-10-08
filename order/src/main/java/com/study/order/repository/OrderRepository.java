package com.study.order.repository;

import com.study.order.domain.entity.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

	List<Order> findAllByMemberId(Long memberId);
}
