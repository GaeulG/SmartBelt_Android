package com.example.android.bluetoothlegatt;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class RegisterActivity extends Activity {
    private static String TAG = "phptest_RegisterAct";

    private EditText mEditTextID;
    private EditText mEditTextPwd;
    private EditText mEditTextName;
    private EditText mEditTextPwdCheck;
    private TextView mTextViewResult;
    private Button btnCancle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.register_layout);

        mEditTextName = (EditText)findViewById(R.id.editText_main_name);
        mEditTextID = (EditText)findViewById(R.id.editText_main_id);
        mEditTextPwd = (EditText)findViewById(R.id.editText_main_pwd);
        mEditTextPwdCheck = (EditText)findViewById(R.id.editText_main_pwdcheck);
        mTextViewResult = (TextView)findViewById(R.id.textView_main_result);
        btnCancle = (Button)findViewById(R.id.button_main_cancel);

        Button buttonInsert = (Button)findViewById(R.id.button_main_insert);
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = mEditTextName.getText().toString();
                String id = mEditTextID.getText().toString();
                String pwd = mEditTextPwd.getText().toString();
                String pwdCheck = mEditTextPwdCheck.getText().toString();
                if(pwd.equals(pwdCheck)){
                    InsertData task = new InsertData();
                    task.execute(id, pwd, name);
                    mEditTextName.setText("");
                    mEditTextID.setText("");
                    mEditTextPwdCheck.setText("");
                    mEditTextPwd.setText("");
                }
                else{
                    Toast.makeText(getApplicationContext(), "비밀번호를 확인해주세요.", Toast.LENGTH_SHORT).show();
                    mEditTextPwdCheck.setText("");
                    mEditTextPwd.setText("");
                }



            }

        });
        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }


    class InsertData extends AsyncTask<String, Void, String>{
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(RegisterActivity.this,
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            mTextViewResult.setText(result);
            Log.d(TAG, "POST response  - " + result);
        }


        @Override
        protected String doInBackground(String... params) {

            String paramId = (String)params[0];
            String paramPwd = (String)params[1];
            String paramName = (String)params[2];

            String serverURL = "http://192.168.0.12:80/insert.php";
            String postParameters = "id=" + paramId + "&password=" + paramPwd + "&name=" + paramName;


            try {

                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();


                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                //httpURLConnection.setRequestProperty("content-type", "application/json");
                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();


                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();


                int responseStatusCode = httpURLConnection.getResponseCode();
                Log.d(TAG, "POST response code - " + responseStatusCode);

                InputStream inputStream;
                if(responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                }
                else{
                    inputStream = httpURLConnection.getErrorStream();
                }


                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line = null;

                while((line = bufferedReader.readLine()) != null){
                    sb.append(line);
                }


                bufferedReader.close();


                return sb.toString();


            } catch (Exception e) {

                Log.d(TAG, "InsertData: Error ", e);

                return new String("Error: " + e.getMessage());
            }

        }
    }

}