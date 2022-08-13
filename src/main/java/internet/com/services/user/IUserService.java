package internet.com.services.user;

import internet.com.entity.user.AppUser;

import java.util.Optional;

public interface IUserService {
    Optional<AppUser> findByUsername(String name);

    Boolean existsByUsername(String username);

    void createUser(String username, String password);
}
