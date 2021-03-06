package com.epam.petclinic.order.repository;

import com.epam.petclinic.order.domain.Order;
import com.google.common.collect.Lists;
import org.apache.commons.collections.ListUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
public class OrderRepositoryIntegrationTest {
    @Autowired
    private IOrderRepository orderRepository;

    @Test
    public void testFindAll() {
        List<Order> orders = Lists.newArrayList(initOrder(), initOrder(), initOrder());
        orders.stream().forEach(order -> orderRepository.save(order));
        assertNotNull(orderRepository.findAll());
        assertTrue(ListUtils.isEqualList(orders, orderRepository.findAll()));
    }

    @Test
    public void testSaveOrder() {
        Order order = initOrder();
        orderRepository.save(order);
        Order savedOrder = orderRepository.findOne(order.getId());
        assertNotNull(savedOrder);
        assertEquals(order, savedOrder);
    }

    private Order initOrder() {
        Order order = new Order();
        order.setId(UUID.randomUUID());
        order.setName("name");
        order.setEmail("test@test.com");
        order.setAddress("address");
        order.setAnimalId(UUID.randomUUID());
        order.setServiceIds(Lists.newArrayList(UUID.randomUUID(), UUID.randomUUID()));
        return order;
    }
}
