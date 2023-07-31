package com.example.idvlabsTask.common.response;

public record MessageResponse(
        ResponseType responseType,
        String message
) {
}