package student.examples.ggengine.conn;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import student.examples.ggengine.game.Item;


//should be immutable because it already happened
public final class Frame {
	
	private int id;
	private Set<Item> items;
	
	
	public Frame(int id, Set<Item> items) {
		this.id = id;
		this.items = new HashSet<>(items);
	}
	
	public Set<Item> getItems() {
		return Collections.unmodifiableSet(items);
	}
	
//	public void addItem(Item item) {
//		items.add(item);
//	}

}
