package kirill.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import kirill.entity.CRMUser;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/register")
public class RegisterController {

  @Autowired
  private UserDetailsManager userDetailsManager;
  private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

  @InitBinder
  public void initBinder(WebDataBinder dataBinder) {
    StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
    dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
  }

  @GetMapping("/showRegistrationForm")
  public String showRegistrationForm(Model theModel){
    theModel.addAttribute("crmUser", new CRMUser());
    return "registration-form";
  }

  @PostMapping("/processRegistrationForm")
  public String processRegistrationForm(
          @Valid @ModelAttribute("crmUser") CRMUser crmUser,
          BindingResult bindingResult,
          Model model) {
    if (bindingResult.hasErrors()) {
      model.addAttribute("crmUser", new CRMUser());
      model.addAttribute("registrationError",
              "User name/password can not be empty.");
      return "registration-form";
    }

    String userName = crmUser.getUsername();
    if (isUserExists(userName)){
      model.addAttribute("crmUser", new CRMUser());
      model.addAttribute("registrationError",
              "User exists");
      return "registration-form";
    }

    String encodedPassword = passwordEncoder.encode(crmUser.getPassword());
    encodedPassword = "{bcrypt}" + encodedPassword;
    List<GrantedAuthority> authorities =
            AuthorityUtils.createAuthorityList("ROLE_EMPLOYEE");

    User tempUser = new User(userName, encodedPassword, authorities);
    userDetailsManager.createUser(tempUser);

    return "registration-confirmation";

  }

  private boolean isUserExists(String userName) {
    return userDetailsManager.userExists(userName);
  }
}
