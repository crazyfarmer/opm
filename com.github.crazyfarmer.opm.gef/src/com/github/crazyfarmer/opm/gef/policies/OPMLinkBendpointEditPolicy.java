package com.github.crazyfarmer.opm.gef.policies;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.BendpointEditPolicy;
import org.eclipse.gef.requests.BendpointRequest;

import com.github.crazyfarmer.opm.gef.commands.OPMLinkCreateBendpointCommand;
import com.github.crazyfarmer.opm.gef.commands.OPMLinkMoveBendpointCommand;
import com.github.crazyfarmer.opm.model.OPMLink;

public class OPMLinkBendpointEditPolicy extends BendpointEditPolicy {

	@Override
	protected Command getCreateBendpointCommand(BendpointRequest request) {
		OPMLinkCreateBendpointCommand command = new OPMLinkCreateBendpointCommand();
		command.setOPMLink((OPMLink) request.getSource().getModel());
		command.setIndex(request.getIndex());
		return command;
	}

	@Override
	protected Command getDeleteBendpointCommand(BendpointRequest request) {
		return null;
	}

	@Override
	protected Command getMoveBendpointCommand(BendpointRequest request) {
		OPMLinkMoveBendpointCommand command = new OPMLinkMoveBendpointCommand();
		Point p = request.getLocation();
		command.setOPMLink((OPMLink) request.getSource().getModel());
		command.setLocation(p);
		command.setIndex(request.getIndex());
		return command;
	}

}
