package productions.darthplagueis.hackathon.abstractclasses;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public abstract class AbstractOnBoardingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
    }

    @Override
    protected void onResume() {
        super.onResume();
        setAnimation();
    }

    private void toNextActivity() {
        startActivity(new Intent(AbstractOnBoardingActivity.this, getNextActivity()));
    }

    protected abstract int getLayoutId();

    protected abstract Class<?> getNextActivity();

    protected abstract void setAnimation();
}
