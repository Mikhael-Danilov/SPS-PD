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
package com.hmdzl.spspd.change.items.weapon.melee;

import com.hmdzl.spspd.change.sprites.ItemSpriteSheet;
import com.hmdzl.spspd.change.items.Item;
import com.hmdzl.spspd.change.actors.Char;
import com.hmdzl.spspd.change.actors.buffs.Buff;
import com.hmdzl.spspd.change.actors.buffs.Cripple;
import com.watabou.utils.Random;

public class Spear extends MeleeWeapon {

	{
		//name = "spear";
		image = ItemSpriteSheet.SPEAR;
	}

	public Spear() {
		super(2, 1f, 1.5f, 2);
	}
	
	@Override
	public Item upgrade(boolean enchant) {
		
		if (DLY > 1.2f) {
			DLY -= 0.05f ;
		}

		if (DLY < 1.2f && RCH < 3) {
			RCH ++ ;
		}
		
		MIN+=2;
        MAX+=3;
		
		return super.upgrade(enchant);
    }
	
	@Override
    public void proc(Char attacker, Char defender, int damage) {

		if (Random.Int(100) < 40) {
			Buff.affect(defender, Cripple.class, 3);
		}
		if (enchantment != null) {
			enchantment.proc(this, attacker, defender, damage);		
		}
	}
}
