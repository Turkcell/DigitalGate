package com.turkcell.digitalgate.testapp;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.turkcell.digitalgate.DGLoginCoordinator;
import com.turkcell.digitalgate.DGTheme;
import com.turkcell.digitalgate.model.exception.DGException;
import com.turkcell.digitalgate.model.result.DGResult;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final String NORMAL_FLOW = "NORMAL_FLOW";
    String demoFlowType;
    private Button buttonLogin;
    private Button buttonRegister;
    private Button buttonAccountChange;
    private Button buttonLogOut;
    private Spinner spinner;
    private TextView textViewResult;
    private List<String> spinnerItemList;
    private EditText appId;
    private CheckBox disableCellLogin;
    private CheckBox autoLoginOnly;
    private CheckBox disableAutoLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity_main);
        spinner = findViewById(R.id.spinner);
        textViewResult = findViewById(R.id.textViewResult);
        buttonLogin = findViewById(R.id.button);
        buttonRegister = findViewById(R.id.buttonRegister);
        buttonAccountChange = findViewById(R.id.buttonAccountChange);
        buttonLogOut = findViewById(R.id.buttonLogOut);
        appId = findViewById(R.id.appId);
        disableCellLogin = findViewById(R.id.disableCellLogin);
        autoLoginOnly = findViewById(R.id.autoLoginOnly);
        disableAutoLogin = findViewById(R.id.disableAutoLogin);

        int permissionSendMessage = ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_PHONE_STATE);
        if (permissionSendMessage != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, 666);
        }

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                demoFlowType = spinnerItemList.get(spinner.getSelectedItemPosition());
                if (demoFlowType.equals(NORMAL_FLOW)) {
                    demoFlowType = null;
                }
                openLoginSdkForStart();
            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                demoFlowType = spinnerItemList.get(spinner.getSelectedItemPosition());
                if (demoFlowType.equals(NORMAL_FLOW)) {
                    demoFlowType = null;
                }
                openLoginSdkForRegister();
            }
        });

        buttonAccountChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                demoFlowType = spinnerItemList.get(spinner.getSelectedItemPosition());
                if (demoFlowType.equals(NORMAL_FLOW)) {
                    demoFlowType = null;
                }
                openLoginSdkForAccounChange();
            }
        });

        buttonLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DGLoginCoordinator.logout(MainActivity.this,getAppId());
            }
        });


        spinnerItemList = new ArrayList();
        spinnerItemList.add(NORMAL_FLOW);
        spinnerItemList.add("ACTIVE_REMEMBERME_LOGIN");
        spinnerItemList.add("ACTIVE_REMEMBERME_LOGIN_SHOW_DIGITAL_ID_ENTRY");
        spinnerItemList.add("ACTIVE_REMEMBERME_LOGIN_SHOW_EMAIL_ENTRY");
        spinnerItemList.add("ACTIVE_REMEMBERME_LOGIN_SHOW_GSM_ENTRY");
        spinnerItemList.add("ACCOUNT_PASSWORD_LOGIN");
        spinnerItemList.add("SHOW_LOGIN_REGISTERREQUIRED");
        spinnerItemList.add("MSISDN_LOGIN_REQUIRED");
        spinnerItemList.add("DIGITAL_ID_REGISTERREQUIRED");
        spinnerItemList.add("DIGITAL_ID_VERIFYEMAIL_WARN");
        spinnerItemList.add("DIGITAL_ID_VERIFYEMAIL_ERROR");
        spinnerItemList.add("SHOW_LOGIN_REGISTERREQUIRED");
        spinnerItemList.add("MC_LOGIN");


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerItemList);
        adapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(adapter);


    }

    private void openLoginSdkForRegister() {
        DGTheme dgTheme = new DGTheme.Builder().setBackgroundColor(android.R.color.holo_green_light).setTitleLabelColor(android.R.color.holo_red_dark).setDescriptionTextColor(android.R.color.holo_orange_dark).setCheckBoxPassiveIcon(R.drawable.dg_checkbox_normal).setPositiveButtonBackgroundColor(android.R.color.darker_gray).setPositiveButtonTextColor(android.R.color.black).build();
        DGLoginCoordinator dg = new DGLoginCoordinator.Builder().theme(null).appId(getAppId()).build();

        try {
            dg.startForRegister(this, demoFlowType);
        } catch (DGException e) {
            e.printStackTrace();
        }
    }

    private void openLoginSdkForStart() {
        DGTheme dgTheme = new DGTheme.Builder().setBackgroundColor(android.R.color.holo_green_light).setTitleLabelColor(android.R.color.holo_red_dark).setDescriptionTextColor(android.R.color.holo_orange_dark).setCheckBoxPassiveIcon(R.drawable.dg_checkbox_normal).setPositiveButtonBackgroundColor(android.R.color.darker_gray).setPositiveButtonTextColor(android.R.color.black).build();
        DGLoginCoordinator dg = new DGLoginCoordinator.Builder().theme(null).appId(getAppId()).build();

        try {
            dg.startForLogin(this, disableCellLogin.isChecked(), autoLoginOnly.isChecked(), disableAutoLogin.isChecked(), demoFlowType);
        } catch (DGException e) {
            e.printStackTrace();
        }
    }

    @NonNull
    private Integer getAppId() {
        Integer appId;
        if (!TextUtils.isEmpty(this.appId.getText().toString())) {
            appId = Integer.valueOf(this.appId.getText().toString());
        } else {
            appId = 2;
        }
        return appId;
    }

    private void openLoginSdkForAccounChange() {
        DGTheme dgTheme = new DGTheme.Builder().setBackgroundColor(android.R.color.holo_green_light).setTitleLabelColor(android.R.color.holo_red_dark).setDescriptionTextColor(android.R.color.holo_orange_dark).setCheckBoxPassiveIcon(R.drawable.dg_checkbox_normal).setPositiveButtonBackgroundColor(android.R.color.darker_gray).setPositiveButtonTextColor(android.R.color.black).build();
        DGLoginCoordinator dg = new DGLoginCoordinator.Builder().theme(dgTheme).appId(getAppId()).build();

        try {
            dg.startForSwitchAccount(this, demoFlowType);
        } catch (DGException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == DGLoginCoordinator.DG_REQUEST_CODE) {

            if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(this, "activtyResultError", Toast.LENGTH_LONG).show();
            }
            if (resultCode == Activity.RESULT_OK) {
                DGResult dgResult = DGLoginCoordinator.getDGResult(data);
                textViewResult.setText(String.format(" Result :  %s", dgResult.toString()));
                Toast.makeText(this, dgResult.getDgResultType().getResultMessage(), Toast.LENGTH_LONG).show();
            }
        }
    }
}
