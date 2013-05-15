package com.github.crazyfarmer.opm.gef.commands;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;

import com.github.crazyfarmer.opm.model.OPMLink;

public class OPMLinkDeleteBendpointCommand extends Command {

	private OPMLink link;
	private int index;
	private Point location;

	@Override
	public boolean canExecute() {
		return (link != null) && (link.getBendpoints().size() > index);
	}

	@Override
	public void execute() {
		location = link.getBendpoints().get(index);
		link.getBendpoints().remove(index);
	}

	public void setIndex(final int index) {
		this.index = index;
	}

	public void setOPMLink(final OPMLink link) {
		this.link = link;
	}

}
