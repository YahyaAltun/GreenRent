package com.greenrent.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImageSavedResponse extends GRResponse{

    private String imageId;
    public ImageSavedResponse(String imageId, String message,boolean success) {
        super(success,message);
        this.imageId=imageId;
    }
}
