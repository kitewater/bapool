package com.bapool.bapool.ui.fragment

import android.Manifest
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.UiThread
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.bapool.bapool.R
import com.bapool.bapool.databinding.FragmentMapBinding
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.normal.TedPermission
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraPosition
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.util.FusedLocationSource
import com.naver.maps.map.util.MarkerIcons


class MapFragment : Fragment(), OnMapReadyCallback {
    private var _binding: FragmentMapBinding? = null
    private val binding get() = _binding!!

    private lateinit var locationSource: FusedLocationSource
    private lateinit var naverMap: NaverMap
    private lateinit var cameraPosition: CameraPosition

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMapBinding.inflate(inflater, container, false)

        binding.floatingActionButton.setOnClickListener {
            cameraPosition = naverMap.cameraPosition

            Log.i("MYTAG", "now camera : $cameraPosition")

            Toast.makeText(
                context,
                cameraPosition.toString(),
                Toast.LENGTH_SHORT
            ).show()
        }

        /******************************************************************************************/
        /*
        /*** 상단 검색 바 ***/
        //View - 변수 연결
        val lv = binding.mListView
        val searchBar = binding.searchBar
        searchBar.setHint("Search")
        //음성검색모드 끄기
        searchBar.setSpeechMode(false)
        //검색어 목록 넣기
        var galaxies = arrayOf(
            "Sombrero",
            "Cartwheel",
            "Pinwheel",
            "StarBust",
            "Whirlpool",
            "Ring Nebular",
            "Own Nebular",
            "Centaurus A",
            "Virgo Stellar Stream",
            "Canis Majos Overdensity",
            "Mayall's Object",
            "Leo",
            "Milky Way",
            "IC 1011",
            "Messier 81",
            "Andromeda",
            "Messier 87"
        )

        val adapter =
            ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_1, galaxies)
        //리스트뷰 초기에 안보이게 설정
        lv.visibility = View.INVISIBLE
        //SearchBar와 ListView 연동
        lv.adapter = adapter
        searchBar.setOnSearchActionListener(object : MaterialSearchBar.OnSearchActionListener {
            override fun onButtonClicked(buttonCode: Int) {
                TODO("Not yet implemented")
            }

            //검색창 누른 상태 여부 확인
            override fun onSearchStateChanged(enabled: Boolean) {
                //맞으면 리스트뷰 보이게 설정
                if (enabled) {
                    lv.visibility = View.VISIBLE
                } else { //아니면 안 보이게
                    lv.visibility = View.INVISIBLE
                }
            }

            override fun onSearchConfirmed(text: CharSequence?) {
                TODO("Not yet implemented")
            }

        })

        searchBar.addTextChangeListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            //검색어 변경하면 ListView 내용 변경
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                adapter.filter.filter(s)
            }

        })

        //ListView 내의 아이템 누르면 Toast 발생
        lv.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                Toast.makeText(
                    context,
                    adapter.getItem(position)!!.toString(),
                    Toast.LENGTH_SHORT
                ).show()
            }
         */
        /******************************************************************************************/

        // Framelayout에 네이버 지도 띄우기
        val fm = childFragmentManager
        val mapFragment = fm.findFragmentById(R.id.map) as MapFragment?
            ?: MapFragment.newInstance().also {
                fm.beginTransaction().add(R.id.map, it).commit()
            }
        mapFragment.getMapAsync(this)

        // 현위치 반환 구현체 locationSource 선언
        locationSource = FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)

        // 위치 권한 요청
        requestPermissions()

        // 네비게이션 바 간 스위칭 관련 리스너
        listener()

        return binding.root
    }

    @UiThread
    override fun onMapReady(naverMap: NaverMap) {
        // ui setting
        val uiSettings = naverMap.uiSettings
        uiSettings.isCompassEnabled = true
        uiSettings.isZoomControlEnabled = false
        uiSettings.isLocationButtonEnabled = true

        this.naverMap = naverMap
        // 실행된 네이버 지도에 locationSource 연결
        naverMap.locationSource = locationSource

        // 마커 추가 테스트
        val marker = Marker()
        marker.position = LatLng(37.5670135, 126.9783740)
        marker.map = naverMap
        marker.width = Marker.SIZE_AUTO
        marker.height = Marker.SIZE_AUTO
        marker.captionText = "테스트용 1"

        val marker2 = Marker()
        marker2.position = LatLng(37.4447533294745, 126.702653350562)
        marker2.map = naverMap
        marker2.icon = MarkerIcons.YELLOW
//        marker2.iconTintColor = Color.YELLOW
        marker2.width = Marker.SIZE_AUTO
        marker2.height = Marker.SIZE_AUTO
        marker2.captionText = "테스트용 2"


    }

    // 위치 권한 획득 위함
    private fun requestPermissions() {
        // 권한 리스너 선언
        val permissionlistener: PermissionListener = object : PermissionListener {
            override fun onPermissionGranted() {
                Toast.makeText(context, "Permission Granted", Toast.LENGTH_SHORT).show()
            }

            override fun onPermissionDenied(deniedPermissions: List<String>) {
                Toast.makeText(context, "Permission Denied\n$deniedPermissions", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        // TedPermission 라이브러리 // 위치 권한 요청
        TedPermission.create()
            .setPermissionListener(permissionlistener)
            .setRationaleTitle("위치권한 요청!")
            .setRationaleMessage("현재 위치로 이동하기 위해 위치권한이 필요해요!!")
            .setPermissions(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
            .check()
    }

    // 네비게이션 바 간 스위칭 관련 리스너
    fun listener() {
        binding.fragmentGroup.setOnClickListener {
            it.findNavController().navigate(R.id.action_mapFragment_to_groupFragment)
        }
        binding.fragmentMypage.setOnClickListener {
            it.findNavController().navigate(R.id.action_mapFragment_to_mypageFragment)
        }
    }

    //뷰바인딩 생명주기 관리
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // LOCATION_PERMISSION_REQUEST_CODE
    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1000
    }
}