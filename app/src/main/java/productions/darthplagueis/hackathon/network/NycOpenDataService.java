package productions.darthplagueis.hackathon.network;

import java.util.List;

import productions.darthplagueis.hackathon.model.FoodScrapsResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface NycOpenDataService {

    String foodScrapsEndpoint = "resource/9m2c-n6wx.json";

    @GET(foodScrapsEndpoint)
    Call<List<FoodScrapsResponse>> getFoodScrapsDropOffSites();
}
