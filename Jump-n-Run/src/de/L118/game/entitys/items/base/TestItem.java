package de.L118.game.entitys.items.base;

import de.L118.game.Player;

public class TestItem extends IItem {
	
	public TestItem() {
		
		super("Test");
	}
	
	@Override
	public String getName() {
		
		return "Test";
	}
	
	@Override
	public void onUse(Player p) {
	
	}
	
	@Override
	public void onPickup(Player p) {
	
	}
}