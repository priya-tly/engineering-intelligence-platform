package com.eip.dto.error;

import java.time.LocalDateTime;
import java.util.Map;

public record ErrorResponse
        (
          LocalDateTime timeStamp,
          int Status,
          String message,
          Map<String, String> errors
        )
{
}
