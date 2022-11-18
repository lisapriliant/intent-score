package id.putraprima.skorbola;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private EditText etRound, etTeam1, etTeam2;
    private ImageView imgTeam1, imgTeam2;
    private Button btnNext;
    private Uri uTeam1, uTeam2;

    private static final String TAG = MainActivity.class.getCanonicalName();
    private static final int TEAM1_REQUEST_CODE = 1;
    private static final int TEAM2_REQUEST_CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etRound = findViewById(R.id.roundhit);
        etTeam1 = findViewById(R.id.team1);
        etTeam2 = findViewById(R.id.team2);
        imgTeam1 = findViewById(R.id.team1_logo);
        imgTeam2 = findViewById(R.id.team2_logo);
        btnNext = findViewById(R.id.btn_team);

        imgTeam1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI), TEAM1_REQUEST_CODE);
            }
        });

        imgTeam2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI), TEAM2_REQUEST_CODE);
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sround = etRound.getText().toString();
                String steam1 = etTeam1.getText().toString();
                String steam2 = etTeam2.getText().toString();

                Intent intent = new Intent(MainActivity.this, MatchActivity.class);
                intent.putExtra("round", sround);
                intent.putExtra("team1", steam1);
                intent.putExtra("team2", steam2);
                intent.putExtra("imageteam1", uTeam1.toString());
                intent.putExtra("imageteam2", uTeam2.toString());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_CANCELED){
            Log.d(TAG, "Pilih gambar dicancel");
            return;
        }
        else if(requestCode == TEAM1_REQUEST_CODE){
            if(data != null){
                try {
                    uTeam1 = data.getData();
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uTeam1);
                    imgTeam1.setImageBitmap(bitmap);
                }
                catch (IOException error){
                    Toast.makeText(this, "Can't load image", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, error.getMessage());
                }
            }
        }
        else if(requestCode == TEAM2_REQUEST_CODE){
            if(data != null){
                try {
                    uTeam2 = data.getData();
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uTeam2);
                    imgTeam2.setImageBitmap(bitmap);
                }
                catch (IOException error){
                    Toast.makeText(this, "Can't load image", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, error.getMessage());
                }
            }
        }
    }
}
