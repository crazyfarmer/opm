package com.github.crazyfarmer.opm.gef.editor;

import org.eclipse.gef.palette.CombinedTemplateCreationEntry;
import org.eclipse.gef.palette.ConnectionCreationToolEntry;
import org.eclipse.gef.palette.CreationToolEntry;
import org.eclipse.gef.palette.MarqueeToolEntry;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.PaletteStack;
import org.eclipse.gef.palette.PanningSelectionToolEntry;
import org.eclipse.gef.palette.SelectionToolEntry;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gef.tools.MarqueeSelectionTool;
import org.eclipse.jface.resource.ImageDescriptor;

import com.github.crazyfarmer.opm.gef.factory.OPMAgentLinkFactory;
import com.github.crazyfarmer.opm.gef.factory.OPMAggregationStructuralLinkAggregatorFactory;
import com.github.crazyfarmer.opm.gef.factory.OPMConsumptionLinkFactory;
import com.github.crazyfarmer.opm.gef.factory.OPMEffectLinkFactory;
import com.github.crazyfarmer.opm.gef.factory.OPMExhibitionStructuralLinkAggregatorFactory;
import com.github.crazyfarmer.opm.gef.factory.OPMGeneralizationStructuralLinkAggregatorFactory;
import com.github.crazyfarmer.opm.gef.factory.OPMInstrumentLinkFactory;
import com.github.crazyfarmer.opm.gef.factory.OPMLinkFactory;
import com.github.crazyfarmer.opm.gef.factory.OPMObjectFactory;
import com.github.crazyfarmer.opm.gef.factory.OPMProcessFactory;
import com.github.crazyfarmer.opm.gef.factory.OPMResultLinkFactory;
import com.github.crazyfarmer.opm.gef.tool.CreationAndDirectEditTool;

public class OPMGraphicalEditorPalette extends PaletteRoot {

	PaletteGroup group;

	public OPMGraphicalEditorPalette() {
		add(createGeneralPaletteTools());
		add(createBasicOPMPaletteTools());
	 
	}

	private PaletteDrawer createGeneralPaletteTools() {
		PaletteDrawer drawer = new PaletteDrawer("General");
		ToolEntry tool = new PanningSelectionToolEntry();
		drawer.add(tool);
		setDefaultEntry(tool);

		PaletteStack marqueeStack = new PaletteStack("Marquee Tools", "", null);
		   // NODES CONTAINED (default)
	    marqueeStack.add(new MarqueeToolEntry());

	    // NODES TOUCHED
	    MarqueeToolEntry marquee = new MarqueeToolEntry();
	    marquee.setToolProperty(MarqueeSelectionTool.PROPERTY_MARQUEE_BEHAVIOR, new Integer(
	        MarqueeSelectionTool.BEHAVIOR_NODES_TOUCHED));
	    marqueeStack.add(marquee);

	    // NODES CONTAINED AND RELATED CONNECTIONS
	    marquee = new MarqueeToolEntry();
	    marquee.setToolProperty(MarqueeSelectionTool.PROPERTY_MARQUEE_BEHAVIOR, new Integer(
	        MarqueeSelectionTool.BEHAVIOR_NODES_CONTAINED_AND_RELATED_CONNECTIONS));
	    marqueeStack.add(marquee);

	    // NODES TOUCHED AND RELATED CONNECTIONS
	    marquee = new MarqueeToolEntry();
	    marquee.setToolProperty(MarqueeSelectionTool.PROPERTY_MARQUEE_BEHAVIOR, new Integer(
	        MarqueeSelectionTool.BEHAVIOR_NODES_TOUCHED_AND_RELATED_CONNECTIONS));
	    marqueeStack.add(marquee);

	    // CONNECTIONS CONTAINED
	    marquee = new MarqueeToolEntry();
	    marquee.setToolProperty(MarqueeSelectionTool.PROPERTY_MARQUEE_BEHAVIOR, new Integer(
	        MarqueeSelectionTool.BEHAVIOR_CONNECTIONS_CONTAINED));
	    marqueeStack.add(marquee);

	    // CONNECTIONS TOUCHED
	    marquee = new MarqueeToolEntry();
	    marquee.setToolProperty(MarqueeSelectionTool.PROPERTY_MARQUEE_BEHAVIOR, new Integer(
	        MarqueeSelectionTool.BEHAVIOR_CONNECTIONS_TOUCHED));
	    marqueeStack.add(marquee);

	    marqueeStack.setUserModificationPermission(PaletteEntry.PERMISSION_NO_MODIFICATION);
	    drawer.add(marqueeStack);

	    return drawer;
	}

	private PaletteEntry createBasicOPMPaletteTools() {
	    PaletteDrawer drawer = new PaletteDrawer("OPP Basic");
//	    drawer.add(new CreationToolEntry("Label", "Create new Label", new LabelFactory(), ImageDescriptor.createFromFile(
//	        this.getClass(), "icons/label.ico"), ImageDescriptor.createFromFile(this.getClass(), "icons/label.ico")));

	    ToolEntry entry =
	        new CombinedTemplateCreationEntry("OPMObject", "Create a new Object", new OPMObjectFactory(),
	            ImageDescriptor.createFromFile(this.getClass(), "icons/object.ico"), ImageDescriptor.createFromFile(
	                this.getClass(), "icons/object.ico"));
	    entry.setToolClass(CreationAndDirectEditTool.class);
	    drawer.add(entry);

	    entry =
	        new CombinedTemplateCreationEntry("OPMProcess", "Create a new Process", new OPMProcessFactory(),
	            ImageDescriptor.createFromFile(this.getClass(), "icons/process.ico"), ImageDescriptor.createFromFile(
	                this.getClass(), "icons/process.ico"));
	    entry.setToolClass(CreationAndDirectEditTool.class);
	    drawer.add(entry);

//	    entry =
//	        new CreationToolEntry("OPMState", "Create a new State", new OPMStateFactory(), ImageDescriptor.createFromFile(
//	            this.getClass(), "icons/state.ico"), ImageDescriptor.createFromFile(this.getClass(), "icons/state.ico"));
//	    entry.setToolClass(CreationAndDirectEditTool.class);
//	    drawer.add(entry);

	    drawer.add(new ConnectionCreationToolEntry("Instrument", "Create a new Instrument link",
	        new OPMInstrumentLinkFactory(), ImageDescriptor.createFromFile(this.getClass(), "icons/instrument.ico"),
	        ImageDescriptor.createFromFile(this.getClass(), "icons/instrument.ico")));

	    drawer.add(new ConnectionCreationToolEntry("Result", "Create a new Result link", new OPMResultLinkFactory(),
	        ImageDescriptor.createFromFile(this.getClass(), "icons/result.ico"), ImageDescriptor.createFromFile(
	            this.getClass(), "icons/result.ico")));

	    return drawer;
	  }
	private void addSelectionTool() {
		SelectionToolEntry entry = new SelectionToolEntry();
		group.add(entry);
		setDefaultEntry(entry);
	}

	private void addGroup() {
		group = new PaletteGroup("OPM Controls");
		add(group);
	}

	private void addOPMObjectTool() {
		CreationToolEntry entry = new CreationToolEntry("OPMObject",
				"Create a new Object", new OPMObjectFactory(), null, null);
		entry.setToolClass(CreationAndDirectEditTool.class);
		group.add(entry);
	}

	private void addOPMProcessTool() {
		CreationToolEntry entry = new CreationToolEntry("OPMProcess",
				"Create a new Process", new OPMProcessFactory(), null, null);
		entry.setToolClass(CreationAndDirectEditTool.class);
		group.add(entry);
	}

	private void addOPMLinkTool() {
		ConnectionCreationToolEntry entry = new ConnectionCreationToolEntry(
				"Link", "Creates a new link", new OPMLinkFactory(), null, null);
		group.add(entry);
	}

	private void addOPMProceduralLinkTools() {
		ConnectionCreationToolEntry entry;
		entry = new ConnectionCreationToolEntry("Agent",
				"Create a new Agent link", new OPMAgentLinkFactory(), null,
				null);
		group.add(entry);
		entry = new ConnectionCreationToolEntry("Instrument",
				"Create a new Instrument link", new OPMInstrumentLinkFactory(),
				null, null);
		group.add(entry);
		entry = new ConnectionCreationToolEntry("Consumption",
				"Create a new Consumption link",
				new OPMConsumptionLinkFactory(), null, null);
		group.add(entry);
		entry = new ConnectionCreationToolEntry("Result",
				"Create a new Result link", new OPMResultLinkFactory(), null,
				null);
		group.add(entry);
		entry = new ConnectionCreationToolEntry("Effect",
				"Create a new Effect link", new OPMEffectLinkFactory(), null,
				null);
		group.add(entry);
	}

	private void addOPMStructuralLinkTools() {
		ConnectionCreationToolEntry entry;
		entry = new ConnectionCreationToolEntry("Aggregation",
				"Create a new Aggregation link",
				new OPMAggregationStructuralLinkAggregatorFactory(), null, null);
		group.add(entry);
		entry = new ConnectionCreationToolEntry("Exhibition",
				"Create a new Exhibition link",
				new OPMExhibitionStructuralLinkAggregatorFactory(), null, null);
		group.add(entry);
		entry = new ConnectionCreationToolEntry("Generalization",
				"Create a new Generalization link",
				new OPMGeneralizationStructuralLinkAggregatorFactory(), null,
				null);
		group.add(entry);
	}
}