package com.github.crazyfarmer.opm.gef.figures;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;

public interface OPMNodeFigure extends IFigure {
	public abstract ConnectionAnchor getSourceConnectionAnchor();

	public abstract ConnectionAnchor getTargetConnectionAnchor();
}