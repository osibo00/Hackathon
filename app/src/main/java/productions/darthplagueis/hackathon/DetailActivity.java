package productions.darthplagueis.hackathon;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

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
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(detailResponse.getLocation());
        ImageView imageView = (ImageView) findViewById(R.id.detail_image);
        Glide.with(this)
                .load(getResources().getDrawable(R.drawable.green_nature04))
                .into(imageView);
        setTextViews();
    }

    private void setTextViews() {
        TextView compostedText = (TextView) findViewById(R.id.detail_composted_by);
        if (detailResponse.getComposted_by() == null) {
            compostedText.setText(R.string.org_unavailable);
        } else {
            compostedText.setText(String.format("Composted by %s", detailResponse.getComposted_by()));
        }
        TextView addressText = (TextView) findViewById(R.id.detail_address);
        if (detailResponse.getLocation_1_address() == null) {
            addressText.setText(R.string.address_unavailable);
        } else {
            addressText.setText(detailResponse.getLocation_1_address());
        }
        TextView postcodeText = (TextView) findViewById(R.id.detail_postcode);
        if (detailResponse.getPostcode() == null) {
            postcodeText.setText(R.string.postcode_unavailabe);
        } else {
            postcodeText.setText(detailResponse.getPostcode());
        }
        TextView daysText = (TextView) findViewById(R.id.detail_days);
        if (detailResponse.getDays() ==  null) {
            daysText.setText(R.string.days_unavailable);
        } else {
            daysText.setText(detailResponse.getDays());
        }
        TextView hoursText = (TextView) findViewById(R.id.detail_hours);
        if (detailResponse.getHours() == null) {
            hoursText.setText(R.string.hours_unavailable);
        } else {
            hoursText.setText(detailResponse.getHours());
        }
        TextView citymapText = (TextView) findViewById(R.id.detail_citymap);
        if (detailResponse.getCitymap_location() == null) {
            citymapText.setText(R.string.citymap_unavail);
            citymapText.setTextColor(Color.BLACK);
        } else {
            citymapText.setText(R.string.citymap_click);
            citymapText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse(detailResponse.getCitymap_location())));
                }
            });
        }
        TextView websiteText = (TextView) findViewById(R.id.detail_website);
        if (detailResponse.getWebsite() == null) {
            websiteText.setText(R.string.website_unavail);
            websiteText.setTextColor(Color.BLACK);
        } else {
            websiteText.setText(R.string.website_click);
            websiteText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse(detailResponse.getWebsite())));
                }
            });
        }
        TextView materialsText = (TextView) findViewById(R.id.detail_materials);
        if (detailResponse.getMaterials_accepted() == null) {
            materialsText.setText(R.string.materails_unavail);
        } else {
            materialsText.setText(String.format("Materials accepted are %s", detailResponse.getMaterials_accepted()));
        }
        TextView monthsText = (TextView) findViewById(R.id.detail_months);
        if (detailResponse.getMonths_of_operation() == null) {
            monthsText.setText(R.string.months_unavail);
        } else {
            monthsText.setText(String.format("Months of operation are %s", detailResponse.getMonths_of_operation()));
        }
        TextView requirementsText = (TextView) findViewById(R.id.detail_requirements);
        if (detailResponse.getRequirements() == null) {
            requirementsText.setText(R.string.reqs_unavail);
        } else {
            requirementsText.setText(String.format("Requirements are %s", detailResponse.getRequirements()));
        }
        TextView boardText = (TextView) findViewById(R.id.detail_board);
        if (detailResponse.getCommunity_board() == null) {
            boardText.setText(R.string.board_unavail);
        } else {
            boardText.setText(String.format("Community board: %s", detailResponse.getCommunity_board()));
        }
        TextView councilText = (TextView) findViewById(R.id.detail_council);
        if (detailResponse.getCommunity_council() == null) {
            councilText.setText(R.string.council_unavail);
        } else {
            councilText.setText(String.format("Community council: %s", detailResponse.getCommunity_council()));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sign_in_menu:
                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if (firebaseUser == null) {
                    startActivity(new Intent(this, SignInActivity.class));
                    finish();
                } else {
                    showSnackbar(String.format("Already logged in as %s.", firebaseUser.getDisplayName()));
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
