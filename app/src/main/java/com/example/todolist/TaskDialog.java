package com.example.todolist;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;


public class TaskDialog extends DialogFragment {
    private EditText Task;
    private EditText Date;

    TaskDialogListener listener;

    public static interface TaskDialogListener {
        void setItem(String text, String date);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState ){
        return inflater.inflate(R.layout.task_dialog, container);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener = (TaskDialogListener) context;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = inflater.inflate(R.layout.task_dialog, null);
        Task = view.findViewById(R.id.task_input);
        Date = view.findViewById(R.id.date_input);
        builder.setTitle("Enter Task");
        builder.setView(view);

        this.Task = view.findViewById(R.id.task_input);
        this.Date = view.findViewById(R.id.date_input);

        builder.setNegativeButton("cancel", null);
        builder.setPositiveButton("Submit", (dialog, which) -> {
            Log.i("INFO", "Positive pressed");
            String task = this.Task.getText().toString();
            String date = this.Date.getText().toString();
            if (TextUtils.isEmpty(task) || TextUtils.isEmpty(date)) {
                Task.setError("Error");
                Date.setError("Error");
            } else {
                listener.setItem(task, date);
            }
        });

        return builder.create();
    }
}
