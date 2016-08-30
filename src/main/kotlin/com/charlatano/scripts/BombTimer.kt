/*
 * Charlatan is a premium CS:GO cheat ran on the JVM.
 * Copyright (C) 2016 Thomas Nappo, Jonathan Beaudoin
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
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.charlatano.scripts

import com.charlatano.game.CSGO
import com.charlatano.game.EntityType
import com.charlatano.game.hooks.GlowIteration
import com.charlatano.game.netvars.NetVarOffsets


fun bombTimer() = GlowIteration {
	if (EntityType.CPlantedC4 == EntityType.byEntityAddress(entityAddress)) {
		val flags = CSGO.csgoEXE.float(entityAddress + NetVarOffsets.flC4Blow)
		val timeLeft = (CSGO.engineDLL.float(6008768 + 16) - flags) * -1
		if (timeLeft > 0) {
			val hasKit = false
			val canDefuse = if (hasKit) timeLeft >= 5 else timeLeft >= 10
			println("$timeLeft seconds, can defuse? $canDefuse")
		}
		return@GlowIteration
	}
}