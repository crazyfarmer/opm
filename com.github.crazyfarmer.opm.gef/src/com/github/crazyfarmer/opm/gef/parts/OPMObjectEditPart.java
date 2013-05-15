package com.github.crazyfarmer.opm.gef.parts;

import org.eclipse.draw2d.IFigure;

import com.github.crazyfarmer.opm.gef.figures.OPMObjectFigure;

public class OPMObjectEditPart extends OPMThingEditPart {

	@Override
	protected IFigure createFigure() {
		return new OPMObjectFigure();
	}

}
