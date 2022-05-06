package jp.ac.titech.itpro.sdl.startactivitysub;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private final static String TAG = MainActivity.class.getSimpleName();

    private TextView answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        setContentView(R.layout.activity_main);

        TextView title = findViewById(R.id.main_title);
        title.setText(TAG);

        answer = findViewById(R.id.main_answer);

        Button button = findViewById(R.id.main_button_go);
        button.setOnClickListener(v -> {
            Log.d(TAG, "onClick");
            Intent intent = new Intent(MainActivity.this, InputActivity.class);
            launcher.launch(intent);
        });
    }

    ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK) {
                    if (result.getData() != null) {
                        String name = result.getData().getStringExtra(InputActivity.NAME_EXTRA);
                        if (name != null && !name.isEmpty()) {
                            answer.setText(getString(R.string.answer_format, name));
                        }
                    }
                } else {
                    answer.setText(R.string.answer_receive_default);
                }
            });

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }
}