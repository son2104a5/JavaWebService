package com.data.service;

import com.data.model.entity.Combo;
import com.data.model.entity.TicketOrder;
import com.data.model.entity.User;
import com.data.repository.ComboRepository;
import com.data.repository.TicketOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketOrderService {
    private final TicketOrderRepository orderRepo;
    private final ComboRepository comboRepo;

    private static final BigDecimal TICKET_PRICE = BigDecimal.valueOf(100000); // mỗi vé 100k

    public TicketOrder placeOrder(User user, Integer quantityTicket, List<Long> comboIds) {
        List<Combo> combos = comboRepo.findAllById(comboIds);
        BigDecimal comboTotal = combos.stream()
                .map(Combo::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal ticketTotal = TICKET_PRICE.multiply(BigDecimal.valueOf(quantityTicket));
        BigDecimal total = ticketTotal.add(comboTotal);

        TicketOrder order = new TicketOrder();
        order.setUser(user);
        order.setQuantityTicket(quantityTicket);
        order.setCombos(combos);
        order.setTotalMoney(total);
        order.setCreatedAt(LocalDateTime.now());

        return orderRepo.save(order);
    }

    public List<TicketOrder> getMyOrders(User user) {
        return orderRepo.findByUser(user);
    }

    public List<TicketOrder> getAllOrders() {
        return orderRepo.findAll();
    }
}
