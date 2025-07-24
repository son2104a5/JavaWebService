package ra.edu.service;

import ra.edu.model.dto.request.UserRegister;
import ra.edu.model.entity.User;

public interface UserService {
    User registerUser(UserRegister userRegister);
}
