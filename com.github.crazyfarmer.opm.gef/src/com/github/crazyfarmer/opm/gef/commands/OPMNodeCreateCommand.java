package com.github.crazyfarmer.opm.gef.commands;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

import com.github.crazyfarmer.opm.model.OPMContainer;
import com.github.crazyfarmer.opm.model.OPMNode;

public class OPMNodeCreateCommand extends Command {

	private OPMNode node;
	private Rectangle constraints;
	private OPMContainer container;

	@Override
	public boolean canExecute() {
		return node != null && constraints != null && container != null;
	}

	@Override
	public void execute() {
		node.setConstraints(constraints);
		node.setContainer(container);
	}

	@Override
	public void undo() {
		node.setContainer(null);
	}

	public void setConstraints(final Rectangle constraints) {
		this.constraints = constraints;
	}

	public void setContainer(final OPMContainer opd) {
		this.container = opd;
	}

	public void setNode(final OPMNode node) {
		this.node = node;
	}
}