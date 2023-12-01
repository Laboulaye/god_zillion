package student.examples.ggengine.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import student.examples.ggengine.services.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private AuthService authService;

	@GetMapping("/signin")
	public void signIn(@RequestParam String user, @RequestParam String password) {
		authService.signIn(user, password);
	}
	
	@GetMapping("/signout")
	public void signOut(@RequestParam String uuid) {
		authService.signOut(uuid);
	}
	
}
