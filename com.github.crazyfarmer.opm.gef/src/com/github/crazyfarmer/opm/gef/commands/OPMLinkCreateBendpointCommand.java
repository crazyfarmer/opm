package com.github.crazyfarmer.opm.gef.commands;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;

import com.github.crazyfarmer.opm.model.OPMLink;

public class OPMLinkCreateBendpointCommand extends Command {
	private int index;
	private Point location;
	private OPMLink link;

	@Override
	public void execute() {
		link.getBendpoints().add(index, location);
	}

	@Override
	public void undo() {
		link.getBendpoints().remove(index);
	}

	public void setIndex(final int index) {
		this.index = index;
		// TODO:validation checks.
	}

	public void setLocation(final Point location) {
		this.location = location;
	}

	public void setOPMLink(final OPMLink link) {
		this.link = link;
	}

}
