package neversmile.packag.com.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class SignUp extends AppCompatActivity {

    private EditText edtName, edtPunchSpeed, edtPunchPower, edtKickSpeed, edtKickPower;
    private TextView txtGetData;

    private Button btnGetAllData;
    private Button btnTransition;

    private String allKickBoxers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edtName = findViewById(R.id.edtName);
        edtPunchPower = findViewById(R.id.edtPunchPower);
        edtPunchSpeed = findViewById(R.id.edtPunchSpeed);
        edtKickPower = findViewById(R.id.edtKickPower);
        edtKickSpeed = findViewById(R.id.edtKickSpeed);

        txtGetData = findViewById(R.id.txtGetData);
        txtGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("KickBoxer");
                parseQuery.getInBackground("ypzefsQOhv", new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject object, ParseException e) {

                        if (object != null && e == null) {

                            txtGetData.setText(object.get("name").toString());
                            FancyToast.makeText(SignUp.this, "Success!", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, true).show();

                        } else {

                            FancyToast.makeText(SignUp.this, e.getMessage(), FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();

                        }

                    }
                });

            }
        });

        btnGetAllData = findViewById(R.id.btnGetAllData);
        btnGetAllData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                allKickBoxers = "";

                ParseQuery<ParseObject> queryAll = ParseQuery.getQuery("KickBoxer");

                queryAll.whereGreaterThan("punch_power", 300);
                queryAll.whereNotEqualTo("punch_power", 999);
                queryAll.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {

                        if (e == null) {

                            if (objects.size() > 0) {

                                for (ParseObject kickBoxer : objects) {
                                    allKickBoxers = allKickBoxers + kickBoxer.get("name") + "\n";
                                }

                                FancyToast.makeText(SignUp.this, allKickBoxers, FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, false).show();

                            } else {
                                FancyToast.makeText(SignUp.this, e.getMessage(), FancyToast.LENGTH_SHORT, FancyToast.ERROR, false).show();
                            }
                        } else {
                            FancyToast.makeText(SignUp.this, e.getMessage(), FancyToast.LENGTH_SHORT, FancyToast.ERROR, false).show();
                        }


                    }
                });

            }
        });

        btnTransition = findViewById(R.id.btnNextActivity);
        btnTransition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SignUp.this, SignUpLoginActivity.class);
                startActivity(intent);

            }
        });
    }

    public void btnCreatePAIsTapped(View view) {

        final ParseObject kickBoxer = new ParseObject("KickBoxer");

        kickBoxer.put("name", edtName.getText().toString());
        kickBoxer.put("punch_speed", Integer.parseInt(edtPunchSpeed.getText().toString()));
        kickBoxer.put("punch_power", Integer.parseInt(edtPunchPower.getText().toString()));
        kickBoxer.put("kick_speed", Integer.parseInt(edtKickSpeed.getText().toString()));
        kickBoxer.put("kick_power", Integer.parseInt(edtKickPower.getText().toString()));

        kickBoxer.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {

                if (e == null)  {

                    //Toast.makeText(SignUp.this, "New KickBoxer " + kickBoxer.get("name") + " is created successfully!", Toast.LENGTH_SHORT).show();
                    FancyToast.makeText(SignUp.this,"New KickBoxer " + kickBoxer.get("name") + " is created successfully!", FancyToast.LENGTH_LONG, FancyToast.SUCCESS,true).show();

                } else {

                    FancyToast.makeText(SignUp.this,e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR,true).show();

                }



            }
        });

    }


}
