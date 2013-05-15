package com.github.crazyfarmer.opm.gef.tool;

import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.tools.CreationTool;

public class CreationAndDirectEditTool extends CreationTool {

	@Override
	protected void performCreation(int button) {
		super.performCreation(button);
		EditPartViewer viewer = getCurrentViewer();
		final Object model = getCreateRequest().getNewObject();
		if (model == null || viewer == null) {
			return;
		}

	}

}
