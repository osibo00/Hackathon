package productions.darthplagueis.hackathon.fragments;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import productions.darthplagueis.hackathon.R;
import productions.darthplagueis.hackathon.abstractclasses.AbstractActivityFragment;
import productions.darthplagueis.hackathon.model.FoodScrapsResponse;

public class MapsActivityFragment extends AbstractActivityFragment {

    private SupportMapFragment supportMapFragment;
    private TextView locationText, addressText, boroughText;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_map_locations;
    }

    @Override
    public void onCreateView() {
        supportMapFragment = SupportMapFragment.newInstance(new GoogleMapOptions()
                .camera(new CameraPosition.Builder()
                .target(new LatLng(40.785091d, -73.968285d))
                .zoom(10f)
                .build()));
        getChildFragmentManager().beginTransaction().replace(R.id.map, supportMapFragment).commit();
        locationText = (TextView) parentView.findViewById(R.id.locationNameTextView);
        addressText = (TextView) parentView.findViewById(R.id.locationAddressTextView);
        boroughText = (TextView) parentView.findViewById(R.id.locationBoroughTextView);
    }

    public void setMarkers(final List<FoodScrapsResponse> responseList) {
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                for (FoodScrapsResponse response : responseList) {
                    googleMap.addMarker(new MarkerOptions().position(new LatLng(response.getLocation_1().getCoordinates()[1],
                            response.getLocation_1().getCoordinates()[0])));
                }
                googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        for (int i = 0; i < responseList.size(); i++) {
                            Log.d("Maps", "onMarkerClick: "+ roundFourPlaces(marker.getPosition().latitude)
                                    + " " + roundFourPlaces(responseList.get(i).getLocation_1().getCoordinates()[1]));
                            if (roundFourPlaces(marker.getPosition().latitude) == roundFourPlaces(responseList.get(i).getLocation_1().getCoordinates()[1])) {
                                locationText.setText(responseList.get(i).getLocation());
                                addressText.setText(responseList.get(i).getLocation_1_address());
                                boroughText.setText(responseList.get(i).getBorough());
                                parentView.findViewById(R.id.location_first_row).setVisibility(View.VISIBLE);
                                break;
                            }
                        }
                        return true;
                    }
                });
            }
        });
    }

    private double roundFourPlaces(double value) {
        BigDecimal bigDecimal = new BigDecimal(value);
        bigDecimal = bigDecimal.setScale(4, RoundingMode.UP);
        return bigDecimal.doubleValue();
    }

}
