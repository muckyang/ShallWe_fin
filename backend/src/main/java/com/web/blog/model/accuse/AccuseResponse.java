package com.web.blog.model.accuse;

import io.swagger.annotations.ApiModelProperty;

public class AccuseResponse {
    public AccuseResponse(){
          
    }
    public AccuseResponse(int accuseId, String reporter, String defendant, int accuseKind, String accuseReason, String accuseUrl, int accuseConfirm) {
        this.accuseId = accuseId;
        this.reporter = reporter;
        this.defendant = defendant;
        this.accuseKind = accuseKind;
        this.accuseReason = accuseReason;
        this.accuseUrl = accuseUrl;
        this.accuseConfirm = accuseConfirm;
    }
    @ApiModelProperty(value = "accuseId", position = 1)
     public int accuseId;
     @ApiModelProperty(value = "reporter", position = 2)
     public String reporter;
     @ApiModelProperty(value = "defendant", position = 3)
     public String defendant;
     @ApiModelProperty(value = "accuseKind", position = 4)
     public int accuseKind;
     @ApiModelProperty(value = "accuseReason", position = 5)
     public String accuseReason;
     @ApiModelProperty(value = "accuseUrl", position = 6)
     public String accuseUrl;
     @ApiModelProperty(value = "accuseConfirm", position = 7)
     public int accuseConfirm;
   
}