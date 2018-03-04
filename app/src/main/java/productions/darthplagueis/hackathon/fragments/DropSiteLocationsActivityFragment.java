package productions.darthplagueis.hackathon.fragments;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import java.util.List;

import productions.darthplagueis.hackathon.R;
import productions.darthplagueis.hackathon.abstractclasses.AbstractActivityFragment;
import productions.darthplagueis.hackathon.controller.DropOffSiteAdapter;
import productions.darthplagueis.hackathon.model.FoodScrapsResponse;
import productions.darthplagueis.hackathon.network.RetrofitFactory;

public class DropSiteLocationsActivityFragment extends AbstractActivityFragment {

    private ProgressBar progressBar;
    private RecyclerView recyclerView;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_drop_site_locations;
    }

    @Override
    public void onCreateView() {
        setViews();
        getDropOffSiteLocations();
    }

    private void setViews() {
        progressBar = (ProgressBar) parentView.findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.VISIBLE);
        recyclerView = (RecyclerView) parentView.findViewById(R.id.recycler_view);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL));
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false));
    }

    private void getDropOffSiteLocations() {
        RetrofitFactory.FoodScrapsListener listener = new RetrofitFactory.FoodScrapsListener() {
            @Override
            public void dropOffSiteCallBack(List<FoodScrapsResponse> responseList) {
                recyclerView.setAdapter(new DropOffSiteAdapter(responseList));
                progressBar.setVisibility(View.GONE);
                getParentActivity().showSnackbar(String.valueOf(responseList.size()) + " " +
                        getString(R.string.results_returned));
            }

            @Override
            public void onErrorCallBack(Throwable t) {
                progressBar.setVisibility(View.GONE);
                getParentActivity().showSnackbar(getParentActivity().getString(R.string.network_error));
            }
        };
        RetrofitFactory.getInstance().setFoodScrapsListener(listener);
        RetrofitFactory.getInstance().getDropOffSiteLocations();
    }
}
