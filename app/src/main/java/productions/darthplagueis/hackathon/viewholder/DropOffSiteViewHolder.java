package productions.darthplagueis.hackathon.viewholder;

import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import productions.darthplagueis.hackathon.R;
import productions.darthplagueis.hackathon.model.FoodScrapsResponse;

public class DropOffSiteViewHolder extends RecyclerView.ViewHolder {

    private CardView cardView;
    private TextView nameText, addressText, hoursText, daysText, boroughText;

    public DropOffSiteViewHolder(View itemView) {
        super(itemView);
        cardView = (CardView) itemView.findViewById(R.id.card_view);
        nameText = (TextView) itemView.findViewById(R.id.location_name);
        addressText = (TextView) itemView.findViewById(R.id.location_address);
        hoursText = (TextView) itemView.findViewById(R.id.location_hours);
        daysText = (TextView) itemView.findViewById(R.id.location_days);
        boroughText = (TextView) itemView.findViewById(R.id.location_borough);
    }

    public void onBind(FoodScrapsResponse foodScrapsResponse) {
        cardView.setCardBackgroundColor(getMatColor("500"));
        nameText.setText(foodScrapsResponse.getLocation());
        addressText.setText(foodScrapsResponse.getLocation_1_address());
        hoursText.setText(foodScrapsResponse.getHours());
        daysText.setText(foodScrapsResponse.getDays());
        boroughText.setText(foodScrapsResponse.getBorough());
    }

    private int getMatColor(String typeColor) {
        int returnColor = Color.BLACK;
        int arrayId = itemView.getResources().getIdentifier("mdcolor_" + typeColor,
                "array", itemView.getContext().getPackageName());
        if (arrayId != 0) {
            TypedArray colors = itemView.getResources().obtainTypedArray(arrayId);
            int index = (int) (Math.random() * colors.length());
            returnColor = colors.getColor(index, Color.BLACK);
            colors.recycle();
        }
        return returnColor;
    }
}
