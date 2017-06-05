package com.example.da08.viewpager;


import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


/**
 * A simple {@link Fragment} subclass.
 */
public class FourFragment extends Fragment implements OnMapReadyCallback {



    LocationManager manager = null;
    GoogleMap map = null;

    public FourFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_four, container, false);
        // 프래그먼트에서 맵 호출하기 ( 프래그먼트 안에서 다른 프래그먼트를 가져올때 )
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        // map 이 사용할 준비가되면 onMapReady메소드 호출
        mapFragment.getMapAsync(this);

        // 상위 액티비티의 자원을 사용하기위해서 액티비티를 가져옴
        MainActivity activity = (MainActivity) getActivity();

        manager = activity.getLocationManager();

        return view;
    }



    /*
    이 함수에서부터 map 을 그리기 시작
     */


    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        // 좌표만 생성
        LatLng sinsa = new LatLng(37.5169583, 127.0208001);
        // 좌표 적용
        // 1 마커를 생성
        MarkerOptions marker = new MarkerOptions();
        marker.position(sinsa);
        marker.title("석봉토스트");
        // 1.2 마커를 화면에 그린다
        googleMap.addMarker(marker);

        // 2 맵의 중심을 해당 좌표로 이동시킨다                         좌표 , 줌레벨
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sinsa, 12));

    }

        // 현재 프래그먼트가 러닝 직전
        @Override
        public void onResume() {
            super.onResume();
            // 마시멜로 이상 버전에서는 런타임권한 체크 여부를 확인해야함
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                // gps 사용을의한 권한 확득이 되어 있지 않으면 리스너 등록을 하지 않는다
                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_CHECKIN_PROPERTIES)
                        != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
            }
            // gps리스너 등록
            manager.requestLocationUpdates(LocationManager.GPS_PROVIDER,// 위치 제공자
                    3000, // 변경사항 체크 추기 밀리세컨드 단위
                    1, // 변경사항 체크 거리  미터 단위
                    locationListener
            );
        }

        // 현재 프래그먼트가 정지

        @Override
        public void onPause() {
            super.onPause();

            // 마시멜로 이상 버전에서는 런타임권한 체크 여부를 확인해야함
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                // gps 사용을의한 권한 확득이 되어 있지 않으면 리스너를 해제하지 않는다
                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_CHECKIN_PROPERTIES)
                        != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
            }
            // 리스너 해제
            manager.removeUpdates(locationListener);
        }


        // gps사용을 위해서 좌표 리스너를 생성해야 함
        LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                /*
                 내 좌표에 변경사항이 있으면 좌표를 불러옴
                 */
                // 경도
                double lng = location.getLongitude();
                // 위도
                double lat = location.getLatitude();
                // 고도
                double alt = location.getAltitude();
                // 정확도
                double acc = location.getAccuracy();
                // 위치제공자
                String provider = location.getProvider();

                // 바뀐 현재 좌표
                LatLng current = new LatLng(lat, lng);
                // 현재 좌표로 카메라 이동
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(current, 12));
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extra) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

    }


