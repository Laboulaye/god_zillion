package student.examples.ggengine.services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Optional;
import java.util.UUID;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import lombok.extern.slf4j.Slf4j;
import student.examples.ggengine.domain.entity.User;
import student.examples.ggengine.domain.repository.UserRepository;
import student.examples.ggengine.events.EventPublisher;
import student.examples.ggengine.game.UserStatus;

@Service
@Slf4j
public class AuthService {
	
	@Autowired
	private EventPublisher gameEventPublisher;
	
	@Autowired
	private UserRepository userRepository;
	
	
	public void createUser(User user) {
		
		UUID uuid = UUID.randomUUID();
		
		String token = createToken(uuid.toString());
		String hashedPassword = hashPassword(user.getPassword());
		
		user.setPassword(hashedPassword);
		user.setId(uuid);
		user.setToken(token);
		
		userRepository.save(user);
		log.info("Created user: " + user);
	}
	
	
	public String signIn(String userName, String password) {
		User user = userRepository.findByUserName(userName);
		
		if (user != null && checkPassword(password, user.getPassword())) {
			gameEventPublisher.publishUserEvent(user, UserStatus.SIGNIN);
			return user.getToken();
		}
		return null;
	}
	
	public void signOut(String uuid) {
		Optional<User> user = userRepository.findById(UUID.fromString(uuid));
		if (user.isPresent()) {
			gameEventPublisher.publishUserEvent(user.get(), UserStatus.SIGNOUT);
		}	
	}
	
	
	
	
	public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

	// Check if a password matches a hashed version
    public static boolean checkPassword(String candidatePassword, String hashedPassword) {
        return BCrypt.checkpw(candidatePassword, hashedPassword);
    }
    
    
    private String createToken(String uuid) {
    	byte[] hashUUID = null;
		
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			hashUUID = messageDigest.digest(uuid.getBytes());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		String token = Base64.getEncoder().encodeToString(hashUUID);
		return token;
    }
}
