package com.jonathanyk.chainfusion.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetailedErrorResponse {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String path;
}
