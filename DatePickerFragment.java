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
        LocalDate currentDate = LocalDate.now();
        int year = currentDate.getYear();
        int month = currentDate.getMonthValue() - 1; // Subtract 1 to match DatePicker's zero-based month
        int day = currentDate.getDayOfMonth();

        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        // Increment month by 1 to match standard month representation
        month++;

        if (getActivity() instanceof HikeEntryActivity) {
            HikeEntryActivity mainActivity = (HikeEntryActivity) getActivity();
            mainActivity.setDate(LocalDate.of(year, month, day));
        } else if (getActivity() instanceof HikeAdvancedSearchActivity) {
            HikeAdvancedSearchActivity searchActivity = (HikeAdvancedSearchActivity) getActivity();
            searchActivity.setDate(LocalDate.of(year, month, day));
        }
    }
}
