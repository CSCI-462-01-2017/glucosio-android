/*
 * Copyright (C) 2016 Glucosio Foundation
 *
 * This file is part of Glucosio.
 *
 * Glucosio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3.
 *
 * Glucosio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Glucosio.  If not, see <http://www.gnu.org/licenses/>.
 *
 *
 */

package org.glucosio.android.presenter;

import org.glucosio.android.activity.AddPressureActivity;
import org.glucosio.android.db.DatabaseHandler;
import org.glucosio.android.db.PressureReading;

import java.util.Date;

public class AddPressurePresenter extends AddReadingPresenter {
    private DatabaseHandler dB;
    private AddPressureActivity activity;


    public AddPressurePresenter(AddPressureActivity addPressureActivity) {
        this.activity = addPressureActivity;
        dB = new DatabaseHandler(addPressureActivity.getApplicationContext());
    }

    public void dialogOnAddButtonPressed(String time, String date, String minReading, String maxReading) {
        if (validateEmpty(date) && validateEmpty(time) && validateEmpty(minReading) && validateEmpty(maxReading)) {
            Date finalDateTime = getCurrentTime();
            int minFinalReading = Integer.parseInt(minReading);
            int maxFinalReading = Integer.parseInt(maxReading);
            PressureReading pReading = new PressureReading(minFinalReading, maxFinalReading, finalDateTime);

            dB.addPressureReading(pReading);
            activity.finishActivity();
        } else {
            activity.showErrorMessage();
        }
    }

    private boolean validateEmpty(String time) {
        return !time.equals("");
    }

    // Getters and Setters

    public String getUnitMeasuerement() {
        return dB.getUser(1).getPreferred_unit();
    }
}
