package examlple.com.testdialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;

public class TestingDialog extends AppCompatDialogFragment {
    private AppCompatActivity appCompatActivity;

    private RadioButton rbtn3;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        appCompatActivity = (AppCompatActivity) context;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Log.d("test_flow", "onCreateDialog: ");

        AlertDialog.Builder builder = new AlertDialog.Builder(appCompatActivity);

        LayoutInflater inflater = appCompatActivity.getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_layout, null);

        rbtn3 = view.findViewById(R.id.r3);

        final RadioGroup radioGroup = view.findViewById(R.id.radio_group);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int id = radioGroup.getCheckedRadioButtonId();
                Log.d("test_flow1", "onCheckedChanged: id is "+ id);
                Log.d("test_flow1", "all ids are 1,2,3, "+ R.id.r1+", "+R.id.r2+", "+R.id.r3);


            }
        });

        Button btnDialog = view.findViewById(R.id.btn_dialog);

        btnDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//
//                    rbtn2.setChecked(true);

                radioGroup.check(R.id.r1);
                Toast.makeText(appCompatActivity,"I will check btn 1", Toast.LENGTH_LONG).show();
            }
        });

        builder.setView(view)
                .setTitle("another title")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(appCompatActivity,"navigate back", Toast.LENGTH_LONG).show();
                    }
                })
                .setPositiveButton("save btn", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(appCompatActivity,"save clicked", Toast.LENGTH_LONG).show();

                    }
                });

        return builder.create();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("test_flow", "onCreateView: ");
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
