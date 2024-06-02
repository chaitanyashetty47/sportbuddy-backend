package com.example.sportsbuddy.response;

import com.example.sportsbuddy.playground.PlaygroundDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
public class PlaygroundResponse {
    private List<PlaygroundDTO> playgrounds;
    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean lastPage;

}
