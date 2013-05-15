package com.github.crazyfarmer.opm.gef.factory;

import org.eclipse.gef.requests.CreationFactory;

import com.github.crazyfarmer.opm.model.OPMFactory;
import com.github.crazyfarmer.opm.model.OPMStructuralLinkAggregator;
import com.github.crazyfarmer.opm.model.OPMStructuralLinkAggregatorKind;

/**
 * Factory used by palette tools to create {@link OPMStructuralLinkAggregator} of
 * {@link OPMStructuralLinkAggregatorKind#EXHIBITION} kind. 
 * @author vainolo
 *
 */
public class OPMExhibitionStructuralLinkAggregatorFactory implements CreationFactory {

    @Override
    public Object getNewObject() {
        OPMStructuralLinkAggregator aggregator = OPMFactory.eINSTANCE.createOPMStructuralLinkAggregator();
        aggregator.setKind(OPMStructuralLinkAggregatorKind.EXHIBITION);
        return aggregator;
    }

    @Override
    public Object getObjectType() {
        return OPMStructuralLinkAggregator.class;
    }

}
