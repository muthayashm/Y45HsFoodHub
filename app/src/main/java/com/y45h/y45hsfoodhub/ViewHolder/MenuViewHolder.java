package com.y45h.y45hsfoodhub.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.y45h.y45hsfoodhub.Interface.ItemClickListener;
import com.y45h.y45hsfoodhub.R;

public class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView txtMenuName;
    public ImageView imageView;
    public ProgressBar menu_image_progress;
    private ItemClickListener itemClickListener;

    public MenuViewHolder(View itemView){
        super(itemView);
        txtMenuName = itemView.findViewById(R.id.menu_name);
        imageView = itemView.findViewById(R.id.menu_image);
        menu_image_progress = itemView.findViewById(R.id.menu_image_progress);
        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition(),false);
    }
}
