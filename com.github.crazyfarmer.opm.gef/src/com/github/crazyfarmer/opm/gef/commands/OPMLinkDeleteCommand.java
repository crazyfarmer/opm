package com.github.crazyfarmer.opm.gef.commands;

import org.eclipse.gef.commands.Command;

import com.github.crazyfarmer.opm.model.OPMLink;
import com.github.crazyfarmer.opm.model.OPMNode;
import com.github.crazyfarmer.opm.model.OPMObjectProcessDiagram;
import com.github.crazyfarmer.opm.model.OPMThing;

public class OPMLinkDeleteCommand extends Command {

	private OPMLink link;
	private OPMNode source;
	private OPMNode target;
	private OPMObjectProcessDiagram opd;

	@Override
	public boolean canExecute() {
		return link != null;
	}

	@Override
	public void execute() {
		opd = link.getOpd();
		source = link.getSource();
		target = link.getTarget();

		link.setSource(null);
		link.setTarget(null);
		link.setOpd(null);
	}

	@Override
	public void undo() {
		link.setSource(source);
		link.setTarget(target);
		link.setOpd(opd);
	}

	public void setLink(final OPMLink linkParam) {
		link = linkParam;
	}
}
