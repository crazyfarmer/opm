package com.github.crazyfarmer.opm.gef.figures;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Rectangle;

public class OPMObjectFigure extends OPMThingFigure {
 
	private RectangleFigure rectangle;
	private ConnectionAnchor connectionAnchor;
	
	public OPMObjectFigure() {
        setLayoutManager(new XYLayout());
        nameLabel = new Label();
        add(nameLabel);
        rectangle = new RectangleFigure();
        rectangle.setFill(false);
        rectangle.setLayoutManager(new XYLayout());
        add(rectangle);
	}

	@Override
	public IFigure getContentPane() {
	    return rectangle;
	}
	
	@Override
	protected void paintFigure(Graphics graphics) {
		Rectangle r = getBounds().getCopy();
		setConstraint(rectangle, new Rectangle(0, 0, r.width, r.height));
		setConstraint(getNameLabel(), new Rectangle(0, 0, r.width, r.height));
	}

 
	public ConnectionAnchor getConnectionAnchor() {
		if(connectionAnchor == null){
			connectionAnchor = new ChopboxAnchor(this);
		}
		return connectionAnchor;
	}
	@Override
    public ConnectionAnchor getSourceConnectionAnchor() {
        return getConnectionAnchor();
    }

    @Override
    public ConnectionAnchor getTargetConnectionAnchor() {
        return getConnectionAnchor();
    }
}