package productions.darthplagueis.hackathon;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import productions.darthplagueis.hackathon.abstractclasses.AbstractActivity;
import productions.darthplagueis.hackathon.model.FoodScrapsResponse;

import static productions.darthplagueis.hackathon.util.Constants.CARD_COLOR;
import static productions.darthplagueis.hackathon.util.Constants.FOOD_SCRAPS_RESPONSE;

public class DetailActivity extends AbstractActivity {

    private static final String TAG = "DetailActivity";
    private int backgroundColor;
    private FoodScrapsResponse detailResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent extras = getIntent();
        if (extras != null) {
            backgroundColor = extras.getIntExtra(CARD_COLOR, getResources().getColor(R.color.colorPrimary));
            detailResponse = (FoodScrapsResponse) extras.getSerializableExtra(FOOD_SCRAPS_RESPONSE);
        }

        setViews();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_detail;
    }

    @Override
    protected int getMenuLayoutId() {
        return R.menu.menu_sign_in;
    }

    private void setViews() {
        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(detailResponse.getLocation());
        ImageView imageView = (ImageView) findViewById(R.id.detail_image);
        Glide.with(this)
                .load(getResources().getDrawable(R.drawable.green_nature04))
                .into(imageView);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sign_in_menu:
                startActivity(new Intent(this, SignInActivity.class));
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
