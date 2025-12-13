package com.sid.productcatalogservice.model;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public abstract class BaseModel {
    // common attributes
    private Long id; // this indicates the unique identifier for each model
    private Date createdAt; // this indicates when the model was created
    private Date updatedAt; // this indicates when the model was last updated
    private State state; // this indicates the current state of the model
    private String createdBy; // this indicates who created the model
    private String updatedBy; // this indicates who last updated the model
}
