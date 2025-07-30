package com.data.service;

import com.data.model.entity.Staff;
import com.data.repository.StaffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService {

    private final StaffRepository staffRepository;
    private final AuthenticationService authService; // Lấy thông tin user hiện tại

    @Override
    public Staff createStaff(Staff staff) {
        staff.setStatus(Staff.Status.ACTIVE);
        staff.setRole(Staff.Role.ROLE_STAFF);
        return staffRepository.save(staff);
    }

    @Override
    public Staff updateStaffRole(Long id, Staff.Role newRole) {
        Staff staff = staffRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Staff not found"));
        staff.setRole(newRole);
        return staffRepository.save(staff);
    }

    @Override
    public Staff getCurrentStaff() {
        String email = authService.getCurrentUserEmail();
        return staffRepository.findByEmail(email)
            .orElseThrow(() -> new ResourceNotFoundException("Staff not found"));
    }

    @Override
    public Staff updateMyInfo(Staff updatedStaff) {
        Staff current = getCurrentStaff();
        current.setName(updatedStaff.getName());
        current.setPhone(updatedStaff.getPhone());
        return staffRepository.save(current);
    }
}
