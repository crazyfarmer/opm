package com.github.crazyfarmer.opm.gef.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ConnectionEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import com.github.crazyfarmer.opm.gef.commands.OPMLinkDeleteCommand;
import com.github.crazyfarmer.opm.model.OPMLink;

public class OPMLinkConnectionEditPolicy extends ConnectionEditPolicy {

	@Override
	protected Command getDeleteCommand(GroupRequest request) {
		OPMLinkDeleteCommand command = new OPMLinkDeleteCommand();
		command.setLink((OPMLink) getHost().getModel());
		return command;
	}

}
