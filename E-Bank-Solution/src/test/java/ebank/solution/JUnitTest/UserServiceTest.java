//package ebank.solution.JUnitTest;
//
//import ebank.solution.model.User;
//import ebank.solution.repository.UserRepository;
//import ebank.solution.service.UserService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.Mockito.*;
//
//
//public class UserServiceTest {
//
//    @Mock
//    private UserRepository userRepository;
//
//    @InjectMocks
//    private UserService userService;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void testFindAllUsers() {
//        List<User> users = new ArrayList<>();
//        users.add(new User());
//        users.add(new User());
//        when(userRepository.findAll()).thenReturn(users);
//
//        List<User> result = userService.findAllUsers();
//
//        assertEquals(2, result.size());
//        verify(userRepository, times(1)).findAll();
//    }
//
//
//    @Test
//    public void testAddUser() {
//        User user = new User();
//        when(userRepository.save(user)).thenReturn(user);
//
//        User result = userService.addUser(user);
//
//        assertNotNull(result);
//        verify(userRepository, times(1)).save(user);
//    }
//
//    @Test
//    public void testUpdateUser() {
//        User existingUser = new User();
//        existingUser.setUserId(1L);
//        existingUser.setUserName("Old Name");
//
//        User updatedUser = new User();
//        updatedUser.setUserName("New Name");
//        updatedUser.setEmail("newemail@example.com");
//        updatedUser.setAddress("New Address");
//        updatedUser.setAccounts(new ArrayList<>());
//
//        when(userRepository.findById(1L)).thenReturn(Optional.of(existingUser));
//        when(userRepository.save(existingUser)).thenReturn(existingUser);
//
//        User result = userService.updateUser(1L, updatedUser);
//
//        assertNotNull(result);
//        assertEquals("New Name", result.getUserName());
//        assertEquals("newemail@example.com", result.getEmail());
//        assertEquals("New Address", result.getAddress());
//        verify(userRepository, times(1)).findById(1L);
//        verify(userRepository, times(1)).save(existingUser);
//    }
//
//    @Test
//    public void testDeleteUser() {
//        doNothing().when(userRepository).deleteById(1L);
//        userService.deleteUser(1L);
//        verify(userRepository, times(1)).deleteById(1L);
//    }
//}
