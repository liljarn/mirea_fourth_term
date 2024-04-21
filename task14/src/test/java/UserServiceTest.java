import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.mirea.dao.entity.UserEntity;
import ru.mirea.dao.repository.UserRepository;
import ru.mirea.dto.UserRegistrationRequest;
import ru.mirea.service.user.DefaultUserService;

public class UserServiceTest {
    @Test
    @DisplayName("Тестирование UserService#loadUserByUsername")
    public void loadUserByUsernameShouldReturnUser() {
        var passwordEncoder = Mockito.mock(PasswordEncoder.class);
        var userRepository = Mockito.mock(UserRepository.class);
        var userService = new DefaultUserService(
                userRepository,
                passwordEncoder
        );

        Mockito.when(userRepository.findUserEntityByUsername("user"))
                .thenReturn(new UserEntity("user", "password"));

        var user = userService.loadUserByUsername("user");
        Assertions.assertThat(user.getUsername()).isEqualTo("user");
    }

    @Test
    @DisplayName("Тестирование UserService#saveUser")
    public void saveUserShouldSaveUser() {
        var passwordEncoder = Mockito.mock(PasswordEncoder.class);
        var userRepository = Mockito.mock(UserRepository.class);
        var userService = new DefaultUserService(
                userRepository,
                passwordEncoder
        );
        userService.saveUser(new UserRegistrationRequest("user", "password"));
        Mockito.verify(passwordEncoder).encode("password");
        Mockito.verify(userRepository).save(Mockito.any(UserEntity.class));
    }
}

