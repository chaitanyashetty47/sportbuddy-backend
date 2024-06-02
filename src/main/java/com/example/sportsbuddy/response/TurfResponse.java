package com.example.sportsbuddy.response;


import com.example.sportsbuddy.turf.TurfDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class TurfResponse {
    private List<TurfDTO> content;
    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean lastPage;

}
