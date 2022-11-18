package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private TextView winRound, winTeam;
    private ImageView btnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        winRound = findViewById(R.id.winner);
        winTeam = findViewById(R.id.winnerteam);
        btnHome = findViewById(R.id.btn_home);

        Bundle bundle = getIntent().getExtras();
        String sWinRound = bundle.getString("round");
        winRound.setText("Round: "+sWinRound);
        String sWinTeam = bundle.getString("winner");
        winTeam.setText(sWinTeam);

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
