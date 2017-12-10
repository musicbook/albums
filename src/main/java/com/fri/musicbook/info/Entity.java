package com.fri.musicbook.info;

import java.util.List;

public class Entity {
    private String opis_projekta;
    private List<String> mikrostoritve;
    private List<String> github;
    private List<String> dockerhub;
    private List<String> travis;
    private List<String> clani;

    public Entity(){};

    public Entity(String op_proj, List<String> ms, List<String> gh, List<String> dh,List<String> trvs,List<String> clni){
        opis_projekta=op_proj;
        mikrostoritve=ms;
        github=gh;
        dockerhub=dh;
        travis=trvs;
        clani=clni;
    }

    public String getOpis_projekta() {
        return opis_projekta;
    }

    public void setOpis_projekta(String opis_projekta) {
        this.opis_projekta = opis_projekta;
    }

    public List<String> getMikrostoritve() {
        return mikrostoritve;
    }

    public void setMikrostoritve(List<String> mikrostoritve) {
        this.mikrostoritve = mikrostoritve;
    }

    public List<String> getClani() {
        return clani;
    }

    public void setClani(List<String> clani) {
        this.clani = clani;
    }

    public List<String> getGithub() {
        return github;
    }

    public void setGithub(List<String> github) {
        this.github = github;
    }



    public List<String> getTravis() {
        return travis;
    }

    public void setTravis(List<String> travis) {
        this.travis = travis;
    }

    public List<String> getDockerhub() {
        return dockerhub;
    }

    public void setDockerhub(List<String> dockerhub) {
        this.dockerhub = dockerhub;
    }

}
