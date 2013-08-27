package org.glydar.paraglydar.event.events;

import org.glydar.paraglydar.data.EntityData;
import org.glydar.paraglydar.models.Player;

public class EntityMoveEvent extends EntityUpdateEvent {

	public EntityMoveEvent(final Player player, final EntityData ed) {
		super(player, ed);
	}

}
