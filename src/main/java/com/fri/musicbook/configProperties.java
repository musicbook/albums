package com.fri.musicbook;

import com.kumuluz.ee.configuration.cdi.ConfigBundle;
import com.kumuluz.ee.configuration.cdi.ConfigValue;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
//@ConfigBundle("songs-endpoint-config")
public class configProperties {
   // @ConfigValue(watch=true)
    private boolean isSongsRunning;

    public boolean getIsSongsRunning(){
        return isSongsRunning;
    }

    public void setIsSongsRunning(boolean val){
        isSongsRunning=val;
    }
}
