package com.github.crazyfarmer.opm.gef.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import com.github.crazyfarmer.opm.gef.commands.OPMNodeDeleteCommand;
import com.github.crazyfarmer.opm.model.OPMLink;
import com.github.crazyfarmer.opm.model.OPMNode;
import com.github.crazyfarmer.opm.model.OPMThing;

public class OPMNodeComponentEditPolicy extends ComponentEditPolicy {

	@Override
	protected Command createDeleteCommand(GroupRequest deleteRequest) {
		
		   OPMNode nodeToDelete = (OPMNode) getHost().getModel();
	        CompoundCommand compoundCommand = new CompoundCommand();
	 
	        // For every outgoing structural link, create a command to delete the aggregator
	        // node at the end of the link.
	        for(OPMLink outgoingStructuralLink : nodeToDelete.getOutgoingStructuralLinks()) {
	            OPMNode aggregatorNode = outgoingStructuralLink.getTarget();
	            OPMNodeDeleteCommand aggregatorNodeDeleteCommand = new OPMNodeDeleteCommand();
	            aggregatorNodeDeleteCommand.setNode(aggregatorNode);
	            compoundCommand.add(aggregatorNodeDeleteCommand);
	        }
	        // For every incoming structural link whose aggregator has only one outgoing
	        // link, create a command to delete the aggregator.
	        for(OPMLink incomingStructuralLink : nodeToDelete.getIncomingStructuralLinks()) {
	            OPMNode aggregatorNode = incomingStructuralLink.getSource();
	            if(aggregatorNode.getOutgoingLinks().size() == 1) {
	                OPMNodeDeleteCommand aggregatorNodeDeleteCommand = new OPMNodeDeleteCommand();
	                aggregatorNodeDeleteCommand.setNode(aggregatorNode);
	                compoundCommand.add(aggregatorNodeDeleteCommand);
	            }
	        }
		
		
		OPMNodeDeleteCommand nodeDeleteCommand = new OPMNodeDeleteCommand();
		nodeDeleteCommand.setNode((OPMThing) getHost().getModel());
	    compoundCommand.add(nodeDeleteCommand);
		return compoundCommand;
	}
}
