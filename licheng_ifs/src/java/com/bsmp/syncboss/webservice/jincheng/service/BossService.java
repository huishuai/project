package com.bsmp.syncboss.webservice.jincheng.service;

import com.bsmp.syncboss.webservice.jincheng.model.Affirm;
import com.bsmp.syncboss.webservice.jincheng.model.PorductPackage;
import com.bsmp.syncboss.webservice.jincheng.model.PorductPackageAssest;
import com.bsmp.syncboss.webservice.jincheng.model.Cooperater;

import javax.jws.WebService;

@WebService
public interface BossService {     
	public Affirm addCooperater(Cooperater cooperater);
    public Affirm editCooperater(Cooperater cooperater);
    public Affirm removeCooperater(Cooperater cooperater);
    public Affirm savePorductPackage(PorductPackage porductPackage); 
    public Affirm savePorductPackageAssest(PorductPackageAssest porductPackageAssest);
}
