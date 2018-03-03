package productions.darthplagueis.hackathon.abstractclasses;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;

import com.google.android.gms.common.api.GoogleApiClient;

import productions.darthplagueis.hackathon.R;

public abstract class AbstractActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

    }

    protected abstract int getLayoutId();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(getMenuLayoutId(), menu);
        return true;
    }

    protected abstract int getMenuLayoutId();

    public void showSnackbar(String message) {
        Snackbar.make(findViewById(R.id.coord_layout), message, Snackbar.LENGTH_SHORT).show();
    }
}
