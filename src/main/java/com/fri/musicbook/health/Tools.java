package com.fri.musicbook.health;

import com.kumuluz.ee.discovery.annotations.DiscoverService;

import javax.inject.Inject;
import java.util.Optional;

public class Tools {
    public static boolean health=true;
    @Inject
    @DiscoverService("songs")
    private Optional<String> basePath;

    public Optional<String> getBasePath(){
        return basePath;
    }

    public Integer fibNo(Integer no){
        if(no==1) return 1;
        if(no==2) return 1;
        return fibNo(no-1)+fibNo(no-2);
    }
}
