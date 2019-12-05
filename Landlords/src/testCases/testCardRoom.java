package testCases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entities.CardRoom;
import enums.RoomType;

public class testCardRoom {
	CardRoom cr;
	
	@Test // test setup()
	public void setup_PVP(){
		CardRoom cr = new CardRoom();
		cr.setType(RoomType.PVP);
		cr.setup();
		assertEquals(cr.getPlayers().size(), 3);
		assertEquals(cr.getPlayers().get(0).getCards().size(), 17);
		assertEquals(cr.getPlayers().get(1).getCards().size(), 17);
		assertEquals(cr.getPlayers().get(2).getCards().size(), 17);
	}
	
	@Test // test setup()
	public void setup_PVE(){
		CardRoom cr = new CardRoom();
		cr.setType(RoomType.PVE);
		cr.setup();
		assertEquals(cr.getPlayers().size(), 3);
		assertEquals(cr.getPlayers().get(0).getCards().size(), 17);
		assertEquals(cr.getPlayers().get(1).getCards().size(), 17);
		assertEquals(cr.getPlayers().get(2).getCards().size(), 17);
	}
}
