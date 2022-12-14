package service;

import model.User;
import model.dto.UserRequest;
import model.dto.UserResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import repository.UserRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;


@SpringBootTest
public class UserServiceTest {

    private UserRepository userRepository = Mockito.mock(UserRepository.class);

    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository);  //수동 DI
    }

    @Test
    @DisplayName("회원 등록 성공 메세지가 나오는지")
    void addTest(){

        Mockito.when(userRepository.save(any()))
                .thenReturn(new User(1l, "suyeon", "112233"));

        UserResponse userResponse = userService.addUser(new UserRequest("suyeon", "112233"));
        assertEquals("suyeon", userResponse.getUsername());
        assertEquals("회원 등록 성공", userResponse.getMessage());

    }
}
