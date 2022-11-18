package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MatchActivity extends AppCompatActivity {

    private TextView tvRound, tvTeam1, tvTeam2, tvScore1, tvScore2;
    private int score1, score2;
    private ImageView ivTeam1, ivTeam2;
    private Button btnResult, btnAdd1Team1, btnAdd2Team1, btnAdd3Team1, btnResetTeam1, btnAdd1Team2, btnAdd2Team2, btnAdd3Team2, btnResetTeam2;
    private String sWinner, sTeam1, sTeam2, sRound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);
        tvRound = findViewById(R.id.rounde);
        tvTeam1 = findViewById(R.id.name1);
        tvTeam2 = findViewById(R.id.name2);
        score1 = findViewById(R.id.skorteam1);
        score2 = findViewById(R.id.skorteam2);
        ivTeam1 = findViewById(R.id.team1_logo);
        ivTeam2 = findViewById(R.id.team2_logo);
        btnResult = findViewById(R.id.btn_result);

        btnAdd1Team1 = findViewById(R.id.tambah1);
        btnAdd2Team1 = findViewById(R.id.tambah2);
        btnAdd3Team1 = findViewById(R.id.tambah3);
        btnResetTeam1 = findViewById(R.id.reset1);

        btnAdd1Team2 = findViewById(R.id.plus1);
        btnAdd2Team2 = findViewById(R.id.plus2);
        btnAdd3Team2 = findViewById(R.id.plus3);
        btnResetTeam2 = findViewById(R.id.reset2);

        score1 = 0;
        score2 = 0;
        tvScore1.setText(String.valueOf(score1));
        tvScore2.setText(String.valueOf(score2));

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            if (bundle.getString("imageteam1")!=null && bundle.getString("imageteam2")!=null){
                ivTeam1.setImageURI(Uri.parse(bundle.getString("imageteam1")));
                ivTeam2.setImageURI(Uri.parse(bundle.getString("imageteam2")));
            }
            sRound = bundle.getString("round");
            sTeam1 = bundle.getString("team1");
            sTeam2 = bundle.getString("team2");
            tvRound.setText(sRound);
            tvTeam1.setText(sTeam1);
            tvTeam2.setText(sTeam2);
        } else{
            Toast.makeText(MatchActivity.this, "Intent is missing", Toast.LENGTH_SHORT).show();
            return;
        }

        btnAdd1Team1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                score1 += 1;
                tvScore1.setText(String.valueOf(score1));
            }
        });

        btnAdd2Team1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                score1 += 2;
                tvScore1.setText(String.valueOf(score1));
            }
        });

        btnAdd3Team1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                score1 += 3;
                tvScore1.setText(String.valueOf(score1));
            }
        });

        btnResetTeam1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                score1 = 0;
                tvScore1.setText(String.valueOf(score1));
            }
        });

        btnAdd1Team2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                score2 += 1;
                tvScore2.setText(String.valueOf(score2));
            }
        });

        btnAdd2Team2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                score2 += 2;
                tvScore2.setText(String.valueOf(score2));
            }
        });

        btnAdd3Team2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                score2 += 3;
                tvScore2.setText(String.valueOf(score2));
            }
        });

        btnResetTeam2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                score2 = 0;
                tvScore2.setText(String.valueOf(score2));
            }
        });

        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sWinner = "empty";
                if (score1 > score2){
                    sWinner = sTeam1;
                }
                else if (score1 == score2){
                    sWinner = "Draw";
                }
                else {
                    sWinner = sTeam2;
                }

                Intent intent = new Intent(MatchActivity.this, ResultActivity.class);
                intent.putExtra("winner", sWinner);
                intent.putExtra("round", sRound);
                startActivity(intent);
            }
        });
    }
}
