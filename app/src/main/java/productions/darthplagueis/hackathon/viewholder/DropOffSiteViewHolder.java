package productions.darthplagueis.hackathon.viewholder;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import productions.darthplagueis.hackathon.DetailActivity;
import productions.darthplagueis.hackathon.R;
import productions.darthplagueis.hackathon.model.FoodScrapsResponse;

import static productions.darthplagueis.hackathon.util.Constants.CARD_COLOR;
import static productions.darthplagueis.hackathon.util.Constants.FOOD_SCRAPS_RESPONSE;
import static productions.darthplagueis.hackathon.util.MaterialDesignColorGenerator.getMaterialDesignColor;

public class DropOffSiteViewHolder extends RecyclerView.ViewHolder {

    private CardView cardView;
    private TextView nameText, addressText, hoursText, daysText, boroughText;

    public DropOffSiteViewHolder(View itemView) {
        super(itemView);
        cardView = (CardView) itemView.findViewById(R.id.card_view);
        nameText = (TextView) itemView.findViewById(R.id.location_name);
        addressText = (TextView) itemView.findViewById(R.id.location_address);
        daysText = (TextView) itemView.findViewById(R.id.location_days);
        hoursText = (TextView) itemView.findViewById(R.id.location_hours);
        boroughText = (TextView) itemView.findViewById(R.id.location_borough);
    }

    public void onBind(FoodScrapsResponse response) {
        cardView.setCardBackgroundColor(getMaterialDesignColor(itemView.getContext(), "600"));
        setOnClick(response);
        nameText.setText(response.getLocation());
        addressText.setText(response.getLocation_1_address());
        if (response.getDays() == null) {
            daysText.setText(R.string.days_unavailable);
        } else {
            daysText.setText(response.getDays());
        }
        if (response.getHours() == null) {
            hoursText.setText(R.string.hours_unavailable);
        } else {
            hoursText.setText(response.getHours());
        }
        boroughText.setText(response.getBorough());
    }

    private void setOnClick(final FoodScrapsResponse response) {
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detailActivityIntent = new Intent(itemView.getContext(), DetailActivity.class);
                detailActivityIntent.putExtra(FOOD_SCRAPS_RESPONSE, response);
                itemView.getContext().startActivity(detailActivityIntent);
            }
        });
    }
}
