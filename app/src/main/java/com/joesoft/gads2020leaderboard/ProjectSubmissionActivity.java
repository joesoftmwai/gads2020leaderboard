package com.joesoft.gads2020leaderboard;

import android.os.Bundle;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;

import com.google.android.material.textfield.TextInputEditText;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class ProjectSubmissionActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView mBackArrow;
    private TextInputEditText mTextInputFirstName;
    private TextInputEditText mTextInputLastName;
    private TextInputEditText mTextInputEmail;
    private TextInputEditText mTextInputProjectLink;
    private Button mButtonSubmit;

    private SweetAlertDialog mSweetAlertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_submission);
        mBackArrow = findViewById(R.id.back_arrow);
        mTextInputFirstName = findViewById(R.id.text_input_firstname);
        mTextInputLastName = findViewById(R.id.text_intput_lastname);
        mTextInputEmail = findViewById(R.id.text_input_email);
        mTextInputProjectLink = findViewById(R.id.text_input_project_link);
        mButtonSubmit = findViewById(R.id.btn_submit);

        mBackArrow.setOnClickListener(this);
        mButtonSubmit.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.back_arrow:
                finish();
                break;
            case R.id.btn_submit:
                validateProjectDetails();
                break;
        }
    }

    private void validateProjectDetails() {
        if (!mTextInputFirstName.getText().toString().isEmpty()
                && !mTextInputLastName.getText().toString().isEmpty()
                && !mTextInputEmail.getText().toString().isEmpty()
                && !mTextInputProjectLink.getText().toString().isEmpty()) {
            // check if email is valid
            if (validateEmail(mTextInputEmail)) {
                mSweetAlertDialog = new SweetAlertDialog(
                        ProjectSubmissionActivity.this, SweetAlertDialog.WARNING_TYPE);
                mSweetAlertDialog
                        .setTitleText("Project submission")
                        .setContentText("Are you sure ?")
                        .setConfirmText("Yes")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetDialog) {
                                submitProject();
                                sweetDialog.dismissWithAnimation();
                            }
                        })
                        .show();
            }

        } else {
            Toast.makeText(ProjectSubmissionActivity.this,
                    "Please fill all the fields", Toast.LENGTH_SHORT).show();
        }
    }

    private void submitProject() {

    }

    public boolean validateEmail(TextInputEditText email) {
        String emailInput = email.getText().toString();
        if (Patterns.EMAIL_ADDRESS.matcher(emailInput).matches())
            return true;

        mTextInputEmail.setError("Invalid email address");
        return false;
    }


}
