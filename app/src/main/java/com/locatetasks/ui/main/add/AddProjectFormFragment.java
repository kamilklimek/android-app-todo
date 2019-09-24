package com.locatetasks.ui.main.add;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.snackbar.Snackbar;
import com.locatetasks.AddModelActivity;
import com.locatetasks.MainActivity;
import com.locatetasks.R;
import com.locatetasks.ui.main.dao.ProjectDao;
import com.locatetasks.ui.main.ioc.DependencyManager;
import com.locatetasks.ui.main.model.ProjectModel;
import com.locatetasks.ui.main.model.TaskModel;

import java.util.LinkedList;

public class AddProjectFormFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.e("ADD FORM", "getItem: ");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        Log.e("ADD FORM", "VIEW: ");
        View root = inflater.inflate(R.layout.add_project_form, container, false);
        Button button = root.findViewById(R.id.add_project_btn);

        button.setOnClickListener(new ButtonOnClick(this));

        return root;
    }

    class ButtonOnClick implements View.OnClickListener {
        final Fragment fragment;

        ButtonOnClick(Fragment fragment) {
            this.fragment = fragment;
        }

        @Override
        public void onClick(View v) {
            EditText view = fragment.getView().findViewById(R.id.project_name);
            String projectName = view.getText().toString();

            DependencyManager.get(ProjectDao.class).save(new ProjectModel(projectName, new LinkedList<TaskModel>()));
            String message = "Dodano pomyślnie projekt: " + projectName;


            Intent myIntent = new Intent(fragment.getActivity().getBaseContext(), MainActivity.class);
            Snackbar.make(v, message, message.length());
            fragment.getActivity().getBaseContext().startActivity(myIntent);
        }
    }
}
