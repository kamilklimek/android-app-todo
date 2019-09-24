package com.locatetasks.ui.main.add;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;
import com.locatetasks.MainActivity;
import com.locatetasks.R;
import com.locatetasks.ui.main.dao.ProjectDao;
import com.locatetasks.ui.main.ioc.DependencyManager;
import com.locatetasks.ui.main.model.ProjectModel;
import com.locatetasks.ui.main.model.TaskModel;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.LinkedList;
import java.util.List;

public class AddTaskFormFragment extends Fragment {
    private Long projectId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.add_task_form, container, false);
        Button button = root.findViewById(R.id.add_task_btn);


        Spinner projectSelect = root.findViewById(R.id.project_select);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(
                this.getContext(), R.layout.project_select_spinner, R.id.item, new String[]{"PROJEKT", "DRUGI"});
        projectSelect.setAdapter(spinnerArrayAdapter);

        projectSelect.setOnItemSelectedListener(new Adapter(this));


        button.setOnClickListener(new ButtonOnClick(this));

        return root;
    }

    class Adapter implements AdapterView.OnItemSelectedListener {
        final AddTaskFormFragment fragment;

        Adapter(AddTaskFormFragment fragment) {
            this.fragment = fragment;
        }

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            fragment.projectId = id;
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }
    }

    class ButtonOnClick implements View.OnClickListener {
        final AddTaskFormFragment fragment;

        ButtonOnClick(AddTaskFormFragment fragment) {
            this.fragment = fragment;
        }

        @Override
        public void onClick(View v) {
            EditText name = fragment.getView().findViewById(R.id.task_name);
            EditText desc = fragment.getView().findViewById(R.id.task_description);
            DatePicker date = fragment.getView().findViewById(R.id.executeDate);
            TimePicker time = fragment.getView().findViewById(R.id.executeTime);

            OffsetDateTime exec = OffsetDateTime.of(
                    date.getYear(), date.getMonth(), date.getDayOfMonth(), time.getHour(), time.getMinute(), 0, 0, ZoneOffset.of(ZoneId.systemDefault().getId()));

            String taskName = name.getText().toString();
            DependencyManager.get(ProjectDao.class)
                    .addTask(this.fragment.projectId, new TaskModel(
                            taskName,
                            desc.getText().toString(),
                            exec
                    ));
            String message = "Dodano pomyślnie zadanie: " + taskName;


            Intent myIntent = new Intent(fragment.getActivity().getBaseContext(), MainActivity.class);

            Snackbar.make(v, message, Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            fragment.getActivity().getBaseContext().startActivity(myIntent);
        }
    }
}
