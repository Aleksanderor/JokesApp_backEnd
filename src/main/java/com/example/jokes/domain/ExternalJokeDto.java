package com.example.jokes.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
public class ExternalJokeDto {
    public String _id;
    public String setup;
    public String punchline;
    public String type;
    public ArrayList<Object> likes;
    public boolean approved;
    public int date;
    @JsonProperty("NSFW")
    public boolean nSFW;
    public String shareableLink;
}