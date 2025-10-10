package com.springLataleLauncher.demo.vo;


public enum Servers {
    SEA("sea"),
    EU("eu"),
    EA("ea")
;

    private String server;

    Servers(String server) {
			this.server = server;
    }

    public String getServer(){
			return server;
	}

}
