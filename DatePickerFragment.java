package com.kmd.uog.mhike;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;
import androidx.fragment.app.DialogFragment;
import java.time.LocalDate;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        LocalDate d = LocalDate.now();
        int year = d.getYear();
        int month = d.getMonthValue();
        int day = d.getDayOfMonth();
        return new DatePickerDialog(getActivity(), this, year, --month, day);
    }


    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        HikeEntryActivity mainActivity =(HikeEntryActivity)getActivity();
        mainActivity.setDate(LocalDate.of(year, month, day));
    }

}
