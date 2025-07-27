package com.springdatajpa.demo.vo;

import java.util.HashMap;
import java.util.Map;

public enum Classes {
    WARRIOR("warrior"),
    MAGE("mage"),
		KEMONO("kemono"),
;

    private String classes;

    classes(String classes) {
			this.classes = classes;
    }

		public String getClasses(){
			return classes;
		}


}
