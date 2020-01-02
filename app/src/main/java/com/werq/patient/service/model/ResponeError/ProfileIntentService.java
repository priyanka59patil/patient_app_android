package com.werq.patient.service.model.ResponeError;

import com.werq.patient.service.model.ResponcejsonPojo.Doctor;

public class ProfileIntentService {

    Doctor doctor;
    boolean isMessageEnabled;

    public ProfileIntentService(Doctor doctor, boolean isMessageEnabled) {
        this.doctor = doctor;
        this.isMessageEnabled = isMessageEnabled;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public boolean isMessageEnabled() {
        return isMessageEnabled;
    }
}
