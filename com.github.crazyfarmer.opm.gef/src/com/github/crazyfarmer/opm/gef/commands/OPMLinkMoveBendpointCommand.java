package com.github.crazyfarmer.opm.gef.commands;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;

import com.github.crazyfarmer.opm.model.OPMLink;

public class OPMLinkMoveBendpointCommand extends Command {

	private Point oldLocation;
	private Point newLocation;

	private int index;
	private OPMLink link;

	@Override
	public void execute() {
		if (oldLocation == null) {
			oldLocation = link.getBendpoints().get(index);
		}
		link.getBendpoints().set(index, newLocation);
	}

	@Override
	public void undo() {
		link.getBendpoints().set(index, oldLocation);
	}

	public void setIndex(final int index) {
		this.index = index;
	}

	public void setOPMLink(final OPMLink link) {
		this.link = link;
	}

	public void setLocation(final Point newLocation) {
		this.newLocation = newLocation;
	}
}
