package com.werq.patient.Controller;

import com.werq.patient.Interfaces.BasicActivities;
import com.werq.patient.Interfaces.FilesInteface;
import com.werq.patient.MockData.JsonData;
import com.werq.patient.Models.FilesData;

public class FilesController implements FilesInteface {
   BasicActivities basicActivities;

    public FilesController(BasicActivities basicActivities) {
        this.basicActivities = basicActivities;
    }

    @Override
    public void getData() {
        FilesData filesData=JsonData.getFilesData();
        basicActivities.setView(filesData);

    }
}
