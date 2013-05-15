package com.github.crazyfarmer.opm.gef.commands;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

import com.github.crazyfarmer.opm.model.OPMThing;

public class OPMThingChangeConstraintCommand extends Command {

	private Rectangle oldConstraint;
	private Rectangle newConstraint;
	private OPMThing model;
	
	@Override
	public void execute() {
		if(oldConstraint==null){
			oldConstraint = model.getConstraints();
		}
		model.setConstraints(newConstraint);
	}
	
	@Override
	public void undo(){
		model.setConstraints(oldConstraint);
	}
	
	public void setModel(OPMThing model){
		this.model = model;
	}
	
	public void setNewConstraint(Rectangle newConstraint){
		this.newConstraint = newConstraint;
	}
}
