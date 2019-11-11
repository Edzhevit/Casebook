package app.web;

import app.domain.models.services.UserServiceModel;
import app.domain.models.view.HomeViewModel;
import app.services.UserService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class HomeBean extends BaseBean {

    private List<HomeViewModel> models;
    private ModelMapper modelMapper;
    private UserService userService;

    public HomeBean() {
    }

    @Inject
    public HomeBean(ModelMapper modelMapper, UserService userService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @PostConstruct
    public void init(){

        String username = (String) ((HttpSession)FacesContext.getCurrentInstance().getExternalContext()
                .getSession(false)).getAttribute("username");

        this.setModels(this.userService.findAll()
                .stream()
                .filter(u -> !u.getUsername().equals(username)
                        && !u.getFriends()
                        .stream()
                        .map(UserServiceModel::getUsername)
                        .collect(Collectors.toList()).contains(username))
                .map(u -> this.modelMapper
                        .map(u, HomeViewModel.class))
                        .collect(Collectors.toList()));
    }

    public void addFriend(String friendId){
        String id = (String) ((HttpSession)FacesContext.getCurrentInstance().getExternalContext()
                .getSession(false)).getAttribute("userId");

        UserServiceModel loggedIn = this.userService.getById(id);
        UserServiceModel friend = this.userService.getById(friendId);

        loggedIn.getFriends().add(friend);
        friend.getFriends().add(loggedIn);

        this.userService.addFriend(loggedIn);
        this.userService.addFriend(friend);

        this.redirect("/home");
    }

    public List<HomeViewModel> getModels() {
        return models;
    }

    public void setModels(List<HomeViewModel> models) {
        this.models = models;
    }
}
