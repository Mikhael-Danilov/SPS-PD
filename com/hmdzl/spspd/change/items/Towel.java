/*
 * Pixel Dungeon
 * Copyright (C) 2012-2014  Oleg Dolya
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */
package com.hmdzl.spspd.change.items;

import java.util.ArrayList;

import com.hmdzl.spspd.change.Dungeon;
import com.hmdzl.spspd.change.actors.Actor;
import com.hmdzl.spspd.change.actors.buffs.Bleeding;
import com.hmdzl.spspd.change.actors.buffs.Buff;
import com.hmdzl.spspd.change.actors.buffs.Cold;
import com.hmdzl.spspd.change.actors.buffs.Dry;
import com.hmdzl.spspd.change.actors.buffs.Hot;
import com.hmdzl.spspd.change.actors.buffs.Ooze;
import com.hmdzl.spspd.change.actors.buffs.Wet;
import com.hmdzl.spspd.change.actors.hero.Hero;
import com.hmdzl.spspd.change.actors.mobs.Mob;
import com.hmdzl.spspd.change.actors.mobs.pets.PET;
import com.hmdzl.spspd.change.levels.Level;
import com.hmdzl.spspd.change.levels.Terrain;
import com.hmdzl.spspd.change.messages.Messages;
import com.hmdzl.spspd.change.scenes.GameScene;
import com.hmdzl.spspd.change.sprites.ItemSpriteSheet;
import com.hmdzl.spspd.change.utils.GLog;

public class Towel extends Item {

	public static final float TIME_TO_USE = 1;

	public static final String AC_TOWEL = "TOWEL";
	public static final String AC_TOWEL_PET = "TOWEL_PET";

		{
		//name = "towel";
		image = ItemSpriteSheet.TOWEL;
		unique = true;
		level = 20;

		}

	
	@Override
	public ArrayList<String> actions(Hero hero) {
		ArrayList<String> actions = super.actions(hero);
		
		actions.add(AC_TOWEL);
		
		if(checkpet()!=null && checkpetNear()){
			actions.add(AC_TOWEL_PET);
		}
		
		return actions;
	}

	private PET checkpet(){
		for (Mob mob : Dungeon.level.mobs) {
			if(mob instanceof PET) {
				return (PET) mob;
			}
		}	
		return null;
	}
	
	private boolean checkpetNear(){
		Hero hero = Dungeon.hero;
		for (int n : Level.NEIGHBOURS8) {
			int c = hero.pos + n;
			if (Actor.findChar(c) instanceof PET) {
				return true;
			}
		}
		return false;
	}
	
	
	@Override
	public void execute(Hero hero, String action) {

		
		if (action == AC_TOWEL) {
           	  		  		 
			  Buff.detach(hero, Bleeding.class);
			  Buff.detach(hero, Ooze.class);
			  Buff.detach(hero,Hot.class);
			  Buff.detach(hero,Cold.class);
			  Buff.detach(hero,Wet.class);
			  Buff.detach(hero,Dry.class);
			  
			  Dungeon.observe();
			  			  
			  GLog.w(Messages.get(this,"apply"));
			  
			  level -= 1;
			  if (level==0){
			     detach(Dungeon.hero.belongings.backpack);	
			     GLog.w(Messages.get(this,"end"));
			  }
				  
	
		} else if (action == AC_TOWEL_PET) {
			
			PET pet = checkpet();
			pet.HP=pet.HT;
			  Dungeon.observe();
			  			  
			  GLog.w(Messages.get(this,"apply"));  
			  
			  level -= 1;
			  if (level==0){
			     detach(Dungeon.hero.belongings.backpack);	
			     GLog.w(Messages.get(this,"end"));
			  }
			
				
		} else {
			
			super.execute(hero, action);
		}
	}

	@Override
	public int price() {
		return 500 * quantity;
	}
	
	
	@Override
	public boolean isUpgradable() {
		return false;
	}

	@Override
	public boolean isIdentified() {
		return true;
	}

}
