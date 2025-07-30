package com.data.service;

import com.data.model.entity.Staff;

public interface StaffService {
    Staff createStaff(Staff staff);
    Staff updateStaffRole(Long id, Staff.Role newRole);
    Staff getCurrentStaff();
    Staff updateMyInfo(Staff updatedStaff);
}
