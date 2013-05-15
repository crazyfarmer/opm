package com.github.crazyfarmer.opm.gef.editor;

import java.io.IOException;
import java.util.EventObject;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor.PropertyValueWrapper;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.dnd.TemplateTransferDragSourceListener;
import org.eclipse.gef.dnd.TemplateTransferDropTargetListener;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.ui.actions.ToggleGridAction;
import org.eclipse.gef.ui.actions.ToggleSnapToGeometryAction;
import org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette;
import org.eclipse.gef.ui.properties.UndoablePropertySheetEntry;
import org.eclipse.gef.ui.properties.UndoablePropertySheetPage;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.IPropertySourceProvider;
import org.eclipse.ui.views.properties.PropertySheetPage;

import com.github.crazyfarmer.opm.gef.parts.OPMEditPartFactory;
import com.github.crazyfarmer.opm.model.OPMObjectProcessDiagram;
import com.github.crazyfarmer.opm.model.OPMPackage;
import com.github.crazyfarmer.opm.model.provider.OPMItemProviderAdapterFactory;

public class OPMGraphicalEditor extends GraphicalEditorWithFlyoutPalette {

	private Resource opdResource;
	private OPMObjectProcessDiagram opd;
	PropertySheetPage propertyPage;
	
	public OPMGraphicalEditor() {
		setEditDomain(new DefaultEditDomain(this));
	}

	@Override
	protected void initializeGraphicalViewer() {
		super.initializeGraphicalViewer();
		getGraphicalViewer().setContents(opd);
	}

	@Override
	protected void configureGraphicalViewer() {
		super.configureGraphicalViewer();
		getGraphicalViewer().setEditPartFactory(new OPMEditPartFactory());
		getActionRegistry().registerAction(new ToggleGridAction(getGraphicalViewer())); 
	    getActionRegistry().registerAction(new ToggleSnapToGeometryAction(getGraphicalViewer()));        
	    // D&D
	    getGraphicalViewer().addDropTargetListener(new TemplateTransferDropTargetListener(getGraphicalViewer()));
	    getEditDomain().getPaletteViewer().addDragSourceListener(
	      new TemplateTransferDragSourceListener(getEditDomain().getPaletteViewer()));
	    // end D&D
	}

	@Override
	protected PaletteRoot getPaletteRoot() {
		return new OPMGraphicalEditorPalette();
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		if (opdResource == null) {
			return;
		}

		try {
			opdResource.save(null);
			getCommandStack().markSaveLocation();   
		} catch (IOException e) {
			e.printStackTrace();
			opdResource = null;
		}
	}

	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		super.init(site, input);
		OPMPackage.eINSTANCE.eClass();
		loadInput(input);
	}

	private void loadInput(IEditorInput input) {
		// This initializes the OPMPackage
		// singleton implementation.
		ResourceSet resourceSet = new ResourceSetImpl();
		if (input instanceof IFileEditorInput) {
			IFileEditorInput fileInput = (IFileEditorInput) input;
			IFile file = fileInput.getFile();
			opdResource = resourceSet.createResource(URI.createURI(file
					.getLocationURI().toString()));
			try {
				opdResource.load(null);
				opd = (OPMObjectProcessDiagram) opdResource.getContents()
						.get(0);
			} catch (IOException e) {
				e.printStackTrace();
				opdResource = null;
			}
		}
	}

	@Override
	public void commandStackChanged(EventObject event) {
		firePropertyChange(PROP_DIRTY);
		super.commandStackChanged(event);
	}
	
	 @Override
	    public Object getAdapter(@SuppressWarnings("rawtypes") Class type) {
	        if(type.equals(IPropertySheetPage.class)) {
	            if(propertyPage == null) {
	                propertyPage = (UndoablePropertySheetPage) super
	                        .getAdapter(type);
	                // A new PropertySourceProvider was implemented to fetch the model
	                // from the edit part when required. The property source is provided
	                // by the generated EMF classes and wrapped by the AdapterFactoryContentProvider
	                // to yield standard eclipse interfaces.
	                IPropertySourceProvider sourceProvider = new IPropertySourceProvider() {
	                    IPropertySourceProvider modelPropertySourceProvider = new AdapterFactoryContentProvider(new OPMItemProviderAdapterFactory());
	 
	                    @Override
	                    public IPropertySource getPropertySource(Object object) {
	                        IPropertySource source = null;
	                        if(object instanceof EditPart) {
	                            source = modelPropertySourceProvider.getPropertySource(((EditPart) object).getModel());
	                        } else {
	                            source = modelPropertySourceProvider.getPropertySource(object);
	                        }
	 
	                        if(source != null) {
	                            return new UnwrappingPropertySource(source);
	                        } else {
	                            return null;
	                        }
	                    }
	 
	                };
	                UndoablePropertySheetEntry root = new UndoablePropertySheetEntry(getCommandStack());
	                root.setPropertySourceProvider(sourceProvider);
	                propertyPage.setRootEntry(root);
	            }
	            return propertyPage;
	        }
	        return super.getAdapter(type);
	    }
	 
	 /**
	     * A property source which unwraps values that are wrapped in an EMF
	     * {@link PropertyValueWrapper}
	     * 
	     * @author vainolo
	     * 
	     */
	    public class UnwrappingPropertySource implements IPropertySource {
	        private IPropertySource source;
	 
	        public UnwrappingPropertySource(final IPropertySource source) {
	            this.source = source;
	        }
	 
	        @Override
	        public Object getEditableValue() {
	            Object value = source.getEditableValue();
	            if(value instanceof PropertyValueWrapper) {
	                PropertyValueWrapper wrapper = (PropertyValueWrapper) value;
	                return wrapper.getEditableValue(null);
	            } else {
	                return source.getEditableValue();
	            }
	        }
	 
	        @Override
	        public IPropertyDescriptor[] getPropertyDescriptors() {
	            return source.getPropertyDescriptors();
	        }
	 
	        @Override
	        public Object getPropertyValue(Object id) {
	            Object value = source.getPropertyValue(id);
	            if(value instanceof PropertyValueWrapper) {
	                PropertyValueWrapper wrapper = (PropertyValueWrapper) value;
	                return wrapper.getEditableValue(null);
	            } else {
	                return source.getPropertyValue(id);
	            }
	        }
	 
	        @Override
	        public boolean isPropertySet(Object id) {
	            return source.isPropertySet(id);
	        }
	 
	        @Override
	        public void resetPropertyValue(Object id) {
	            source.resetPropertyValue(id);
	        }
	 
	        @Override
	        public void setPropertyValue(Object id, Object value) {
	            source.setPropertyValue(id, value);
	        }
	    }
}
