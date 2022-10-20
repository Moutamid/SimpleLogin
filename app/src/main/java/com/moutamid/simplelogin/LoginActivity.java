package com.moutamid.simplelogin;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginActivity extends AppCompatActivity {

    EditText email, pass;
    Button login;
    Connection connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email);
        pass = findViewById(R.id.passw);
        login = findViewById(R.id.login);

        login.setOnClickListener(v -> {
            if (validation()){
            connection = conclass();
                try {
                    if (connection != null) {
                        String query = "SELECT * FROM userDetails where email = '" + email.getText().toString() + "'";
                        Statement statement = connection.createStatement();
                        ResultSet set = statement.executeQuery(query);
                        while (set.next()) {
                            if (pass.getText().toString().equals(set.getString(3))){
                                startActivity(new Intent(this, MainActivity.class));
                            } else{
                                Toast.makeText(this, "Password is Wrong", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private boolean validation() {
        if (email.getText().toString().isEmpty()){
            email.setError("Email is Required");
            return false;
        }
        if (pass.getText().toString().isEmpty()){
            pass.setError("Password is Required");
            return false;
        }
        return true;
    }

    @SuppressLint("NewApi")
    public Connection conclass() {
        Connection con = null;
        
        String ip = "192.168.10.3", port = "1433", db = "users", username = "sa", password = "suleman";
        StrictMode.ThreadPolicy a = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(a);
        String connectURL = null;

        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            connectURL = "jdbc:jtds:sqlserver://" + ip + ":" + port + ";databasename=" + db + ";User=" + username + ";"+"password=" + password + ";";
            con = DriverManager.getConnection(connectURL);
        } catch (Exception e) {
            Log.e("Error :", e.getMessage());
        }
        return con;
    }
}