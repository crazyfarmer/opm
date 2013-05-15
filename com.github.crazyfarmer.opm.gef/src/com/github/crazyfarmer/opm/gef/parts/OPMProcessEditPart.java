package com.github.crazyfarmer.opm.gef.parts;

import org.eclipse.draw2d.IFigure;

import com.github.crazyfarmer.opm.gef.figures.OPMProcessFigure;

public class OPMProcessEditPart extends OPMThingEditPart {

	@Override
	protected IFigure createFigure() {
		return new OPMProcessFigure();
	}

 
}
