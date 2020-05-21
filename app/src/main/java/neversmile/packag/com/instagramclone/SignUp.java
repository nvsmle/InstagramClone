package neversmile.packag.com.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.RequestPasswordResetCallback;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUp extends AppCompatActivity {

    private EditText edtName, edtPunchSpeed, edtPunchPower, edtKickSpeed, edtKickPower;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edtName = findViewById(R.id.edtName);
        edtPunchPower = findViewById(R.id.edtPunchPower);
        edtPunchSpeed = findViewById(R.id.edtPunchSpeed);
        edtKickPower = findViewById(R.id.edtKickPower);
        edtKickSpeed = findViewById(R.id.edtKickSpeed);


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
