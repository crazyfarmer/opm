package com.github.crazyfarmer.opm.gef.parts;


import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;

import com.github.crazyfarmer.opm.gef.figures.OPMThingFigure;
import com.github.crazyfarmer.opm.gef.policies.OPMNodeDirectEditPolicy;
import com.github.crazyfarmer.opm.model.OPMThing;

public abstract class OPMThingEditPart extends OPMNodeEditPart
{

	public OPMThingEditPart() {
		super();
	}
	
	@Override
    public IFigure getContentPane() {
        return ((OPMThingFigure)getFigure()).getContentPane();
    }
	
	@Override
	public void performRequest(Request req) {
		if(req.getType() == RequestConstants.REQ_DIRECT_EDIT) {
			performDirectEditing();
		} else if(req.getType() == RequestConstants.REQ_OPEN) {
		    IEditorPart editorPart = ((DefaultEditDomain)getViewer().getEditDomain()).getEditorPart();
		    IFileEditorInput input = (IFileEditorInput) editorPart.getEditorInput();
		    IFile file = input.getFile();
		    IFolder parent = (IFolder) file.getParent();
		    IFile newFile = parent.getFile("OPP Editor.opm");
		    
		    IEditorDescriptor editor = PlatformUI.getWorkbench().getEditorRegistry().getDefaultEditor(newFile.getName());
		    IWorkbenchPage page = editorPart.getSite().getPage();
		    try {
                page.openEditor(new FileEditorInput(newFile), editor.getId());
            } catch (PartInitException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
		}
	}
	@Override protected void createEditPolicies() {
	    super.createEditPolicies();
		installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, new OPMNodeDirectEditPolicy());
	}
	private void performDirectEditing() {
		Label label = ((OPMThingFigure) getFigure()).getNameLabel();
		OPMThingDirectEditManager manager = new OPMThingDirectEditManager(this,
				TextCellEditor.class, new OPMThingCellEditorLocator(label),
				label);
		manager.show();
	}

	@Override
	protected void refreshVisuals() {
//		OPMThingFigure figure = (OPMThingFigure) getFigure();
//		OPMThing model = (OPMThing) getModel();
//		OPMObjectProcessDiagramEditPart parent = (OPMObjectProcessDiagramEditPart) getParent();
//
//		figure.getNameLabel().setText(model.getName());
//		parent.setLayoutConstraint(this, figure, model.getConstraints());
		OPMThingFigure figure = (OPMThingFigure)getFigure();
		OPMThing model = (OPMThing)getModel();
		GraphicalEditPart parent = (GraphicalEditPart) getParent();
		
		figure.getNameLabel().setText(model.getName());
		parent.setLayoutConstraint(this, figure, model.getConstraints());
		
		figure.setTooltipText(model.getDescription());
	}
}