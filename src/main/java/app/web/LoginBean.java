package app.web;

import app.domain.models.binding.LoginBindingModel;
import app.domain.models.services.UserServiceModel;
import app.services.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Map;

@Named
@RequestScoped
public class LoginBean extends BaseBean {

    private LoginBindingModel user;
    private UserService userService;
    private ModelMapper modelMapper;

    public LoginBean() {
    }

    @Inject
    public LoginBean(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void init(){
        this.user = new LoginBindingModel();
    }

    public void login(){
        UserServiceModel user = this.userService.
                getByUsernameAndPassword(this.user.getUsername(), DigestUtils.sha256Hex(this.user.getPassword()));

        if (user == null){
            this.redirect("/login");
        }

        Map<String, Object> sessionMap = FacesContext.getCurrentInstance()
                .getExternalContext()
                .getSessionMap();

        sessionMap.put("username", this.user.getUsername());
        sessionMap.put("userId", user.getId());
        this.redirect("/home");


    }

    public LoginBindingModel getUser() {
        return user;
    }

    public void setUser(LoginBindingModel user) {
        this.user = user;
    }
}
