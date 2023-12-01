package student.examples.ggengine.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import student.examples.ggengine.services.GameService;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/game")
public class GameController {
	
	@Autowired
	private GameService gameService;
	
	
	@GetMapping("/join/{id}")
	public ResponseEntity<String> joinGame(@PathVariable("id") String id) {
		
		if (gameService.joinGame(id)) {
			return new ResponseEntity<String>("", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(" {status: \"failed\", \"message\": \"unauthorized game access\"}", 
					HttpStatus.UNAUTHORIZED);
		}
	}
	
	@GetMapping("/leave/{id}")
	public ResponseEntity<String> leaveGame(@PathVariable("id") String id) {
		
		if (gameService.leaveGame(id)) {
			return new ResponseEntity<String>("", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(" {status: \"failed\", \"message\": \"unauthorized game access\"}", 
					HttpStatus.UNAUTHORIZED);
		}
		
	}
	
	

}
