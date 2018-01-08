package com.example.android.bluetoothlegatt;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by eomhyeong-geun on 2017. 9. 30..
 */

public class LoginActivity extends Activity {

    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT=15000;

    EditText editTextID, editTextPW;
    Button btnLogin, btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.login_layout);
        editTextID = (EditText)findViewById(R.id.editText_login_id);
        editTextPW = (EditText)findViewById(R.id.editText_login_password);
        btnLogin = (Button)findViewById(R.id.btnLogin);
        btnRegister = (Button)findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String id = editTextID.getText().toString();
                final String password = editTextPW.getText().toString();
                MyID myID = (MyID) getApplication();
                myID.setId(id);
                Log.e("MyID is ", myID.getId());
                new AsyncLogin().execute(id, password);
            }
        });
    }

    private class AsyncLogin extends AsyncTask<String, String, String>
    {
        ProgressDialog pdLoading = new ProgressDialog(LoginActivity.this);
        HttpURLConnection conn;
        URL url = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pdLoading.setMessage("\tLoading...");
            pdLoading.setCancelable(false);
            pdLoading.show();
        }

        @Override
        protected String doInBackground(String... params) {
            try{
                url = new URL("http://192.168.0.12:80/login.inc.php");
            } catch (MalformedURLException e){
                e.printStackTrace();
                return "exception";
            }

            try{
                conn = (HttpURLConnection)url.openConnection();
                conn.setReadTimeout(READ_TIMEOUT);
                conn.setConnectTimeout(CONNECTION_TIMEOUT);
                conn.setRequestMethod("POST");

                conn.setDoInput(true);
                conn.setDoOutput(true);

                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter("username", params[0])
                        .appendQueryParameter("password", params[1]);
                String query = builder.build().getEncodedQuery();

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));

                writer.write(query);
                writer.flush();
                writer.close();
                os.close();
                conn.connect();
            }catch (IOException e1){
                e1.printStackTrace();
                return "exception";
            }

            try{
                int response_code = conn.getResponseCode();

                if(response_code == HttpURLConnection.HTTP_OK){ // Connection Success
                    InputStream input = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();
                    String line;

                    while((line = reader.readLine()) != null){
                        result.append(line);
                    }
                    return(result.toString());
                }
                else{
                    return("unsuccessful");
                }
            } catch (IOException e){
                e.printStackTrace();
                return "exception";
            } finally {
                conn.disconnect();
            }
        }

        @Override
        protected void onPostExecute(String result) {

            pdLoading.dismiss();

            if(result.equalsIgnoreCase("true")){
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                LoginActivity.this.finish();

            } else if(result.equalsIgnoreCase("false")){

                Toast.makeText(LoginActivity.this, "Invalid Id or Password", Toast.LENGTH_LONG).show();

            } else if(result.equalsIgnoreCase("exception") || result.equalsIgnoreCase("unsuccessful")){

                Toast.makeText(LoginActivity.this, "OOPS! Something went wrong. Connection Problem", Toast.LENGTH_LONG).show();

            }
        }
    }
}
