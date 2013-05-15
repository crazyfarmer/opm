package com.github.crazyfarmer.opm.gef.factory;

import org.eclipse.gef.requests.CreationFactory;

import com.github.crazyfarmer.opm.model.OPMFactory;
import com.github.crazyfarmer.opm.model.OPMProcess;

public class OPMProcessFactory implements CreationFactory {
 
  @Override public Object getNewObject() {
    return OPMFactory.eINSTANCE.createOPMProcess();
  }
 
  @Override public Object getObjectType() {
    return OPMProcess.class;
  }
 
}