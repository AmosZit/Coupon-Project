package coupon.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import coupon.bean.User;
import coupon.bean.UserDataClient;
import coupon.exeption.ApplicationException;
import coupon.logic.UsersController;

@RestController
@RequestMapping("/user")
public class UsersApi {

	@Autowired
	private UsersController usersController;

	// http://localhost:8080/user
	@PostMapping
	public void createUser(@RequestBody User user) throws ApplicationException {
		this.usersController.creatUser(user);
	}
    //http://localhost:8080/user/login
	@PostMapping("/login")
	public  UserDataClient login(@RequestBody User user)throws ApplicationException {
		return this.usersController.login(user);
	}

	// http://localhost:8080/user
	@PutMapping
	public void updateUser(@RequestBody User user) throws ApplicationException {
		System.out.println(user.toString());
		this.usersController.updateUser(user);
	}

	// http://localhost:8080/user/2
	@DeleteMapping("/{usersId}")
	public void deleteUsers(@PathVariable("usersId") long id) throws ApplicationException {
		this.usersController.deleteUser(id);
	}

	// http://localhost:8080/user/5
	@GetMapping("/{usersId}")
	public User getOneUsers(@PathVariable("usersId") long id) throws ApplicationException {
		System.out.println(usersController.getOneUserById(id));
		return this.usersController.getOneUserById(id);
	}

}
