package com.github.crazyfarmer.opm.gef.factory;

import org.eclipse.gef.requests.CreationFactory;

import com.github.crazyfarmer.opm.model.OPMFactory;
import com.github.crazyfarmer.opm.model.OPMLink;

public class OPMLinkFactory implements CreationFactory {
 
  @Override public Object getNewObject() {
    return OPMFactory.eINSTANCE.createOPMLink();
  }
 
  @Override public Object getObjectType() {
    return OPMLink.class;
  }
 
}