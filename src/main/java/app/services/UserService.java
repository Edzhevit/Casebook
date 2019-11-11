package app.services;

import app.domain.models.services.UserServiceModel;

import java.util.List;

public interface UserService {

    void register(UserServiceModel userServiceModel);

    UserServiceModel getById(String id);

    UserServiceModel getByUsernameAndPassword(String username, String password);

    List<UserServiceModel> findAll();

    void addFriend(UserServiceModel userServiceModel);

    void remove(String userId, String friendId);
}
