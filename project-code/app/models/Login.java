package models;

import com.beligum.core.models.repositories.UserRepository;

import play.data.validation.Constraints.Required;
import com.beligum.core.login.security.UserManager;
import com.beligum.core.utils.FlashHelper;

public class Login
{

    @Required
    public String email;
    public String password;

    public String validate()
    {
	User user = UserRepository.findByEmail(email);
	String retVal = "Login failed";
	if (user != null) {
	    if (UserManager.authenticate(user, password)) {
		FlashHelper.addSuccess("Welcome " + user.getEmail() + ". Your login was successfull.");
		retVal = null;
	    } else {
		retVal = "Password incorrect. Please try again.";
	    }
	} else {
	    retVal = "Email not found. Please try again";
	}
	return retVal;
    }

}
