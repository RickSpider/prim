package com.prim.util;


import java.util.ArrayList;
import java.util.List;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Init;

import com.doxacore.TemplateViewModel;

public abstract class TemplateViewModelLocal extends TemplateViewModel{
	
	@Init(superclass = true)
	public void initTemplateViewModelLocal() {
		
	}

	@AfterCompose(superclass = true)
	public void afterComposeTemplateViewModelLocal() {
		
	}

	
	
}
