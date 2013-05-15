package com.github.crazyfarmer.opm.gef.figures;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.XYLayout;

public abstract class OPMThingFigure extends Figure implements OPMNodeFigure {

	private TooltipFigure tooltipFigure;
	protected Label nameLabel;

	/**
	 * The figure on which this figure's childs should be added instead of
	 * adding them directory.
	 * 
	 * @return a figure on which child figures can be added.
	 */
	abstract public IFigure getContentPane();

	public OPMThingFigure() {
		setLayoutManager(new XYLayout());
		nameLabel = new Label();
		add(nameLabel);
		tooltipFigure = new TooltipFigure();
	}

	/**
	 * Get the name {@link Label} of the figure.
	 * 
	 * @return the name {@link Label} of the figure.
	 */
	public Label getNameLabel() {
		return nameLabel;
	}

	/**
	 * Set the text of the figure's tooltip. If the text is null or empty, no
	 * tooltip will be shown.
	 * 
	 * @param tooltipText
	 *            the text to show as the figure's tooltip.
	 */
	public void setTooltipText(String tooltipText) {
		if (tooltipText != null && tooltipText != "") {
			tooltipFigure.setMessage(tooltipText);
			setToolTip(tooltipFigure);
		} else {
			setToolTip(null);
		}
	}

	protected boolean useLocalCoordinates() {
		return true;
	}

}
