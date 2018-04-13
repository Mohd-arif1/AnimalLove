package com.dev.arif.collapseimagetransitions;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.dev.arif.ModelResponse.ImageResponse;
import com.dev.arif.ModelResponse.Images;
import com.dev.arif.Utilities.EndlessRecyclerViewScrollListener;
import com.dev.arif.api.ApiCallingService;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mohdarif on 11/04/18.
 */
public class FragmentImages extends Fragment {
    RecyclerView recyclerView;
    GridLayoutManager layoutManager;
    FragmentOneAdapter fragmentOneAdapter;
    ImageView imageView;
    Context context;
    List<Images> images;
    ImageResponse imageResponse;
    EndlessRecyclerViewScrollListener endlessScrollListener;
    private static String ARG_ID;
    private int  animalId;
    String phrase;

    public static Fragment newInstance(int i)
    {
        Fragment fragment= new FragmentImages();
        Bundle bundle=new Bundle();
        bundle.putInt(ARG_ID,i);
        fragment.setArguments(bundle);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragmentone, parent, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        imageView = view.findViewById(R.id.image);
        context=parent.getContext();
        layoutManager = new GridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(layoutManager);
        return view;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            animalId = getArguments().getInt(ARG_ID);
        }
        switch (animalId)
        {
            case 1:
                phrase="Lion";
                break;

            case 2:
                phrase="Tiger";
                break;

            case 3:
                phrase="Panther";
                break;

            case 4:
                phrase="Dinosour";
                break;
            case 5:
                phrase="Cat";
                break;
            case 6:
                phrase="Elephant";
                break;
            case 7:
                phrase="Dog";
                break;
            case 8:
                phrase="Zebra";
                break;
        }
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        images=new ArrayList<>();
        getImages(1);

        fragmentOneAdapter = new FragmentOneAdapter(context, images, FragmentImages.this);
        recyclerView.setAdapter(fragmentOneAdapter);

        endlessScrollListener=new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
              getImages(page+1);

          }
      };
      recyclerView.addOnScrollListener(endlessScrollListener);

    }
    public void getImages( final int page)
    {
        Call<ImageResponse> call;

        call = ApiCallingService.getService().getImageResponse(phrase,page);
        call.enqueue(new Callback<ImageResponse>() {
            @Override
            public void onResponse(Call<ImageResponse> call, Response<ImageResponse> response) {
                try{
                    if(page<=100) {

                        imageResponse = response.body();
                        images.addAll(imageResponse.getImages());
                        recyclerView.getAdapter().notifyDataSetChanged();
                        fragmentOneAdapter.notifyItemRangeInserted(fragmentOneAdapter.getItemCount(),images.size()-1);
                    }
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ImageResponse> call, Throwable t) {

            }
        });
    }
}
