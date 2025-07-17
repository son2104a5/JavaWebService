package com.data.service;

import com.data.model.entity.PaymentSlip;
import com.data.repository.PaymentSlipRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentSlipService {
    private final PaymentSlipRepository paymentSlipRepo;

    public PaymentSlip create(PaymentSlip slip) {
        slip.setCreatedAt(LocalDateTime.now());
        return paymentSlipRepo.save(slip);
    }

    public PaymentSlip update(Long id, PaymentSlip updated) {
        PaymentSlip existing = paymentSlipRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Phiếu chi không tồn tại"));

        existing.setTitle(updated.getTitle());
        existing.setDescription(updated.getDescription());
        existing.setMoney(updated.getMoney());

        return paymentSlipRepo.save(existing);
    }

    public List<PaymentSlip> getAll() {
        return paymentSlipRepo.findAll();
    }

    public void delete(Long id) {
        if (!paymentSlipRepo.existsById(id))
            throw new EntityNotFoundException("Phiếu chi không tồn tại");
        paymentSlipRepo.deleteById(id);
    }
}
