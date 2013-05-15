package com.github.crazyfarmer.opm.gef.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.DirectEditPolicy;
import org.eclipse.gef.requests.DirectEditRequest;

import com.github.crazyfarmer.opm.gef.commands.OPMNodeRenameCommand;
import com.github.crazyfarmer.opm.gef.figures.OPMNodeFigure;
import com.github.crazyfarmer.opm.gef.figures.OPMThingFigure;
import com.github.crazyfarmer.opm.model.OPMThing;

public class OPMNodeDirectEditPolicy extends DirectEditPolicy {

	@Override
	protected Command getDirectEditCommand(DirectEditRequest request) {
		OPMNodeRenameCommand command = new OPMNodeRenameCommand();
		command.setModel((OPMThing) getHost().getModel());
		command.setNewName((String) request.getCellEditor().getValue());
		return command;
	}

	@Override
	protected void showCurrentEditValue(DirectEditRequest request) {
		String value = (String)request.getCellEditor().getValue();
		((OPMThingFigure)getHostFigure()).getNameLabel().setText(value);
	}

}
