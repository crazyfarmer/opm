package com.github.crazyfarmer.opm.gef.factory;

import org.eclipse.gef.requests.CreationFactory;

import com.github.crazyfarmer.opm.model.OPMFactory;
import com.github.crazyfarmer.opm.model.OPMProceduralLink;
import com.github.crazyfarmer.opm.model.OPMProceduralLinkKind;

public class OPMEffectLinkFactory implements CreationFactory {
 
    @Override
    public Object getNewObject() {
        OPMProceduralLink link = OPMFactory.eINSTANCE.createOPMProceduralLink();
        link.setKind(OPMProceduralLinkKind.EFFECT);
        return link;
    }
 
    @Override
    public Object getObjectType() {
        return OPMProceduralLink.class;
    }
}