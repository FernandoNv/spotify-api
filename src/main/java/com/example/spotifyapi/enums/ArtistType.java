package com.example.spotifyapi.enums;

public enum ArtistType {
    BAND("band", "banda"),
    SOLO("solo", "solo"),
    DUO("duo", "dupla");

    private String typeEng;
    private String typePt;

    ArtistType(String typeEng, String typePt) {
        this.typeEng = typeEng;
        this.typePt = typePt;
    }
    public static ArtistType fromString(String type){
        for(ArtistType artistType : ArtistType.values()){
            if(artistType.typePt.equalsIgnoreCase(type)){
                return artistType;
            }
        }

        throw new IllegalArgumentException("No type found for this value: " + type);
    }
}
