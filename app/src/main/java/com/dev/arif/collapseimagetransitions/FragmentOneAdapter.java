package com.dev.arif.collapseimagetransitions;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.dev.arif.ModelResponse.Images;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by mohdarif on 11/04/18.
 */

public class FragmentOneAdapter extends RecyclerView.Adapter<FragmentOneAdapter.ViewHolder>{
    List<Images>list;
    Context context;
    FragmentImages fragmentImages;
    public FragmentOneAdapter(Context context,List<Images> list,FragmentImages fragmentImages)
    {
        this.list=list;
        this.context=context;
        this.fragmentImages=fragmentImages;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new FragmentOneAdapter.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull final FragmentOneAdapter.ViewHolder holder, final int position) {
        Picasso.get().load(list.get(position).getDisplaySizes().get(0).getUri())
                .into(holder.iv);
        holder.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, PreviewPageActivity.class);
                intent.putExtra("Image", list.get(position).getDisplaySizes().get(0).getUri());
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(fragmentImages.getActivity(), holder.iv,"profile");
               context.startActivity(intent, options.toBundle());
            }
        });

    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv;

        public ViewHolder(View view) {
            super(view);
            iv=view.findViewById(R.id.imageView);

        }
    }
}
