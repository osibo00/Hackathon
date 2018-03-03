package productions.darthplagueis.hackathon.network;

import android.util.Log;

import java.util.List;

import productions.darthplagueis.hackathon.abstractclasses.AbstractRetrofitFactory;
import productions.darthplagueis.hackathon.model.DropOffSites;
import productions.darthplagueis.hackathon.model.FoodScrapsResponse;
import productions.darthplagueis.hackathon.util.Host;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitFactory extends AbstractRetrofitFactory {

    private static final String TAG = "RetrofitFactory";
    private static RetrofitFactory retrofitFactory;

    private FoodScrapsListener foodScrapsListener = null;

    public static RetrofitFactory getInstance() {
        if (retrofitFactory == null) {
            retrofitFactory = new RetrofitFactory();
        }
        return retrofitFactory;
    }

    private RetrofitFactory() {
    }

    public void setFoodScrapsListener(FoodScrapsListener foodScrapsListener) {
        this.foodScrapsListener = foodScrapsListener;
    }

    public void getDropOffSiteLocations() {
        NycOpenDataService service = buildRetrofit().create(NycOpenDataService.class);
        Call<FoodScrapsResponse> responseCall = service.getFoodScrapsDropOffSites();
        responseCall.enqueue(new Callback<FoodScrapsResponse>() {
            @Override
            public void onResponse(Call<FoodScrapsResponse> call, Response<FoodScrapsResponse> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: " + response.isSuccessful());
                    List<DropOffSites> responseList = response.body().getSitesList();
                    foodScrapsListener.dropOffSiteCallBack(responseList);
                    Log.d(TAG, "onResponse: listSize=" + responseList.size());
                }
            }

            @Override
            public void onFailure(Call<FoodScrapsResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
                foodScrapsListener.onErrorCallBack(t);
            }
        });
    }


    @Override
    public String getHostUrl() {
        return Host.NycOpenDataApi.getUrl();
    }

    public interface FoodScrapsListener {
        void dropOffSiteCallBack(List<DropOffSites> responseList);

        void onErrorCallBack(Throwable t);
    }
}
