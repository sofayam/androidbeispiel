package com.example.bespiel;

import java.io.Serializable;
import java.util.HashMap;


public class NestedMap extends HashMap<String,Object> implements Serializable{

	
	private static final long serialVersionUID = -5690396298231517172L;
	
	String[] tail(String[] s) {
		String[] restIndices = new String [s.length-1];
		System.arraycopy(s, 1,restIndices, 0, s.length-1);	
		return restIndices;
	}

	
	void putNested(String indices, String val) {
		String[] exploded = indices.split(":");
		putNested(exploded,val);
	}
	
	void putNested(String[] indices, String val) {
		String front = indices[0];
		int indLength = indices.length;
		if(indLength == 1) {
			this.put(front,val);
		} else {
			if  (!this.containsKey(indices[0])) {
				this.put(front, new NestedMap());
			}
			NestedMap deeper = (NestedMap) this.get(front);
			deeper.putNested(tail(indices), val);
		}
		
	}
	Object getNested(String[] indices) {
		Object base = this.get(indices[0]);
		if (indices.length == 1) {
			return base;
		} else {
			return ((NestedMap)base).getNested(tail(indices));	
		}	
	}
	NestedMap getNestedMap(String indices) {
		return (NestedMap)getNested(indices.split(":")); 
	}
	String getNestedString(String indices) {
		return (String)getNested(indices.split(":"));		
	}
}


