package com.dr3amers.repository.projection;

import com.dr3amers.model.Project;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "noTasks", types = {Project.class})
public interface NoTasks {
    String getId();
    String getName();
 /*   String getDescription();
    Timestamp getCreationDate();
    int getCreatorId();*/
}
