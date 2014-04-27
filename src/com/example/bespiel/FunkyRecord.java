package com.example.bespiel;

import java.util.ArrayList;
import java.util.List;

public class FunkyRecord implements java.io.Serializable {


	private static final long serialVersionUID = 1L;
	public FunkyRecord(int i2, String s2, int size) {
	
		i = i2;
		s = s2;
		List<String>lst = new ArrayList<String>();
		for (int ctr=0;ctr<size;ctr++){
		   	lst.add("x");
		}
		ls = lst;
	}
	
	public int i;
	public String s;
	public List<String> ls;
}
