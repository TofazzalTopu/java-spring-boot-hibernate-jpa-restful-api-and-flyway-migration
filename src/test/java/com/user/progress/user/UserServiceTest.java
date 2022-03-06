package com.user.progress.user;

import com.user.progress.model.User;
import com.user.progress.repository.UserRepository;
import com.user.progress.service.impl.UserServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    private static final Long DEFAULT_USER_ID = 1L;
    private static final Long UPDATED_USER_ID = 2L;

    private static final String DEFAULT_NAME = "RANA";
    private static final String UPDATED_NAME = "JOHN";

    private static final String DEFAULT_COUNTRY = "BD";
    private static final String UPDATED_COUNTRY = "US";

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Test
    public void save() {
        User user = new User();
        user.setId(DEFAULT_USER_ID);
        user.setName(DEFAULT_NAME);
        user.setCountry(DEFAULT_COUNTRY);

        Mockito.when(userRepository.save(user)).thenReturn(user);
        Assert.assertEquals(user, userService.save(user));
    }

    @Test
    public void findById() {
        User user = new User();
        user.setId(DEFAULT_USER_ID);
        Mockito.when(userRepository.findById(DEFAULT_USER_ID)).thenReturn(Optional.of(user));
        User userById = userService.findById(DEFAULT_USER_ID);
        Assert.assertEquals(userById, user);
    }

    @Test
    public void findUserByName() {
        User user = new User();
        user.setId(DEFAULT_USER_ID);
        user.setName(DEFAULT_NAME);
        user.setCountry(DEFAULT_COUNTRY);

        Mockito.when(userRepository.findByName(DEFAULT_NAME)).thenReturn(Optional.of(user));
        Optional<User> userOptional = userService.findByName(DEFAULT_NAME);
        Assert.assertEquals(userOptional, Optional.of(user));
    }

    @Test
    public void updateUserCountryById() {
        User user = new User();
        user.setId(DEFAULT_USER_ID);
        user.setCountry(DEFAULT_COUNTRY);

        Mockito.when(userRepository.save(user)).thenReturn(user);
        Mockito.when(userRepository.findById(DEFAULT_USER_ID)).thenReturn(Optional.of(user));
        User updatedUser = userService.updatePartial(DEFAULT_USER_ID, UPDATED_COUNTRY);
        Assert.assertEquals(updatedUser, user);
    }

    @Test
    public void updateUser() {
        User user = new User();
        user.setId(DEFAULT_USER_ID);
        user.setName(DEFAULT_NAME);
        user.setCountry(DEFAULT_COUNTRY);

        Mockito.when(userRepository.save(user)).thenReturn(user);
        Mockito.when(userRepository.findById(DEFAULT_USER_ID)).thenReturn(Optional.of(user));

        user.setName(UPDATED_NAME);
        user.setCountry(UPDATED_COUNTRY);
        User updatedUser = userService.update(DEFAULT_USER_ID, user);
        Assert.assertEquals(updatedUser.getCountry(), UPDATED_COUNTRY);
    }

    @Test
    public void findUserList() {
        List<User> userList = new ArrayList<>();
        User user = new User();
        user.setId(DEFAULT_USER_ID);
        user.setName(DEFAULT_NAME);
        user.setCountry(DEFAULT_COUNTRY);
        userList.add(user);

        Mockito.when(userRepository.findAll()).thenReturn(userList);
        List<User> users = userService.findAll();
        Assert.assertEquals(1, users.size());
        Assert.assertEquals(user, users.get(0));
    }

}
