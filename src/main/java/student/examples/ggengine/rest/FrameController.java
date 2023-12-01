package student.examples.ggengine.rest;

import java.util.HashSet;
import java.util.Set;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import student.examples.ggengine.conn.Frame;
import student.examples.ggengine.services.GameService;

@CrossOrigin
@RestController
@RequiredArgsConstructor
public class FrameController {
	
	private final GameService gameService;


	@PostMapping("/update")
	public Frame update() {
		Frame frame= new Frame(0, gameService.getGames().stream().findFirst().get().getItems());
		
		return frame;
	}

}
