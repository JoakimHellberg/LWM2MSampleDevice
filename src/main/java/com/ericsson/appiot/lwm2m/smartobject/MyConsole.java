package com.ericsson.appiot.lwm2m.smartobject;

import java.util.logging.Logger;

import org.eclipse.leshan.client.resource.BaseInstanceEnabler;
import org.eclipse.leshan.core.node.LwM2mResource;
import org.eclipse.leshan.core.response.ExecuteResponse;
import org.eclipse.leshan.core.response.ReadResponse;
import org.eclipse.leshan.core.response.WriteResponse;


public class MyConsole extends BaseInstanceEnabler {
	private final Logger logger = Logger.getLogger(this.getClass().getName()); 

    private String text;
    
    public MyConsole() {
    }
    
    @Override
	public WriteResponse write(int resourceid, LwM2mResource value) {
    	logger.finest("Write on Text Resource " + resourceid + " value: " + value.getValue().toString());
    	 switch (resourceid) {
         	case 5527:
         		setText(value.getValue().toString());
         		return WriteResponse.success();
         	default:
         		return WriteResponse.notFound();
    	 }
	}

	@Override
    public ExecuteResponse execute(int resourceid, String params) {
        switch (resourceid) {
        case 5530:
        	setText("<cleared>");
        	return ExecuteResponse.success();
        default: 
        	return super.execute(resourceid, params);
        }
    }

	@Override
    public ReadResponse read(int resourceid) {
        logger.finest("Read on Text Resource " + resourceid);
        switch (resourceid) {
        case 5527:
            return ReadResponse.success(resourceid, getText());
        default:
            return super.read(resourceid);
        }
    }

    public String getText() {
		return text;
	}

	public void setText(String value) {
		this.text = value;
		System.out.println(getText());
		fireResourcesChange(5527);
	}
}