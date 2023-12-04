package student.examples.ggengine.rest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import student.examples.ggengine.domain.dto.UserAuthDto;
import student.examples.ggengine.domain.entity.User;
import student.examples.ggengine.services.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	
	@PostMapping("/signup")
	public void signup(@Valid @RequestBody User user) {
		authService.createUser(user);
	}
	

	@PostMapping("/signin")
	public ResponseEntity<String> signIn(@RequestBody UserAuthDto userDto) {
		String token = authService.signIn(userDto);
		if (token != null) {
			return new ResponseEntity<String>("{\"status\": \"success\", \"token\": \"" + token +" \"}", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("{\"status\": \"unauthorized\"}", HttpStatus.UNAUTHORIZED);
			
		}
	}
	
	
	@GetMapping("/signout")
	public void signOut(@RequestParam String uuid) {
		authService.signOut(uuid);
	}
	
	
	/**
	 * Handling validation exception
	 * 
	 * @param ex valid exception
	 * @return Map with error messages for each validated field
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		
	    Map<String, String> errors = new HashMap<>();
	    ex.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
	        errors.put(fieldName, errorMessage);
	    });
	    return errors;
	}
}
