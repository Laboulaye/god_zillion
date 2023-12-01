package student.examples.ggengine.rest;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RockController {
	
	@GetMapping("rock")
	@CrossOrigin
	public Map<String, Integer> getData() {
		Map<String, Integer> result = new HashMap<>();
		result.put("top", new Random().nextInt(100));
		result.put("left", new Random().nextInt(100));
		result.put("width", 60);
		result.put("hight", 30);
		result.put("rotation", 45);
		return result;
	}

}
