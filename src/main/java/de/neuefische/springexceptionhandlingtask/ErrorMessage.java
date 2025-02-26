package de.neuefische.springexceptionhandlingtask;

import java.time.LocalDateTime;

public record ErrorMessage(
        String message,
        int status,
        LocalDateTime timestamp)
{
}
