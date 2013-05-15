package com.github.crazyfarmer.opm.gef.policies;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;

import com.github.crazyfarmer.opm.gef.commands.OPMNodeChangeConstraintCommand;
import com.github.crazyfarmer.opm.gef.commands.OPMNodeCreateCommand;
import com.github.crazyfarmer.opm.model.OPMContainer;
import com.github.crazyfarmer.opm.model.OPMNode;
import com.github.crazyfarmer.opm.model.OPMObject;
import com.github.crazyfarmer.opm.model.OPMProcess;
import com.github.crazyfarmer.opm.model.OPMThing;

public class OPMObjectProcessDiagramXYLayoutPolicy extends XYLayoutEditPolicy {
	 private static final Dimension DEFAULT_THING_DIMENSION = new Dimension(50, 50);
	
	@Override
	protected Command getCreateCommand(CreateRequest request) {
		Command retVal = null;
		if(request.getNewObjectType().equals(OPMObject.class) || request.getNewObjectType().equals(OPMProcess.class)) {
			OPMNodeCreateCommand command = new OPMNodeCreateCommand();
			Point clickLocation = request.getLocation();
            ((GraphicalEditPart)getHost()).getFigure().translateFromParent(clickLocation);
			command.setConstraints(new Rectangle(clickLocation, DEFAULT_THING_DIMENSION));
			command.setContainer((OPMContainer) getHost().getModel());
			command.setNode((OPMNode)(request.getNewObject()));
			retVal = command;
		} 
		return retVal;
	}

	@Override
	protected Command createChangeConstraintCommand(EditPart child,
			Object constraint) {
		OPMNodeChangeConstraintCommand command = new OPMNodeChangeConstraintCommand();
		command.setModel((OPMThing) child.getModel());
		command.setNewConstraint((Rectangle) constraint);
		return command;

	}
}