package com.springLataleLauncher.demo.vo;


public enum Classes {
    WARRIOR("warrior"),
    MAGE("mage"),
    KEMONO("kemono"),
;

    private String classes;

    Classes(String classes) {
		this.classes = classes;
    }

    public String getClasses(){
		return classes;
	}

}
