package productions.darthplagueis.hackathon.controller;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import productions.darthplagueis.hackathon.R;
import productions.darthplagueis.hackathon.model.FoodScrapsResponse;
import productions.darthplagueis.hackathon.viewholder.DropOffSiteViewHolder;

public class DropOffSiteAdapter extends RecyclerView.Adapter<DropOffSiteViewHolder> {

    private List<FoodScrapsResponse> foodScrapsResponseList;

    public DropOffSiteAdapter(List<FoodScrapsResponse> foodScrapsResponseList) {
        this.foodScrapsResponseList = foodScrapsResponseList;
    }

    @Override
    public DropOffSiteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dropoff_site_item_view, parent, false);
        return new DropOffSiteViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DropOffSiteViewHolder holder, int position) {
        holder.onBind(foodScrapsResponseList.get(position));
    }

    @Override
    public int getItemCount() {
        return foodScrapsResponseList.size();
    }
}
