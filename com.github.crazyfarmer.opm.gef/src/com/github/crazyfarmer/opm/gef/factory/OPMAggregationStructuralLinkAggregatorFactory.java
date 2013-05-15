package com.github.crazyfarmer.opm.gef.factory;

import org.eclipse.gef.requests.CreationFactory;

import com.github.crazyfarmer.opm.model.OPMFactory;
import com.github.crazyfarmer.opm.model.OPMStructuralLinkAggregator;
import com.github.crazyfarmer.opm.model.OPMStructuralLinkAggregatorKind;

public class OPMAggregationStructuralLinkAggregatorFactory implements CreationFactory {
 
    @Override public Object getNewObject() {
        OPMStructuralLinkAggregator aggregator = OPMFactory.eINSTANCE.createOPMStructuralLinkAggregator();
        aggregator.setKind(OPMStructuralLinkAggregatorKind.AGGREGATION);
        return aggregator;
    }
 
    @Override public Object getObjectType() {
        return OPMStructuralLinkAggregator.class;
    }
}