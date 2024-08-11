package com.shivasai.ecommerce.adaptors;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.shivasai.ecommerce.R;
import com.shivasai.ecommerce.admin.UsersListActivirty.usersListActivity;
import com.shivasai.ecommerce.admin.productsActivity.products;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;

    // Data for images and texts
    private Integer[] mThumbIds = {
            R.drawable.application, R.drawable.customer,
            R.drawable.dressimage, R.drawable.infoimage
    };

    private String[] mTexts = {
            "Mobile List ", "Users List", "T-shirts List", "Orders Info"
    };

    public ImageAdapter(Context c) {
        mContext = c;
        mInflater = LayoutInflater.from(c);
    }

    @Override
    public int getCount() {
        return mThumbIds.length;
    }

    @Override
    public Object getItem(int position) {
        return mThumbIds[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View gridItem = convertView;

        if (gridItem == null) {
            gridItem = mInflater.inflate(R.layout.grid_item, parent, false);
        }

        ImageView imageView = gridItem.findViewById(R.id.image);
        TextView textView = gridItem.findViewById(R.id.text);

        imageView.setImageResource(mThumbIds[position]);
        textView.setText(mTexts[position]);

        // Set an OnClickListener for the grid item
        gridItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null;

                switch (position) {
                    case 0:
                        // Navigate to ProductListActivity

                        intent = new Intent(mContext, products.class);
                        break;
                    case 1:
                        // Navigate to DeleteProductActivity.
                        intent = new Intent(mContext, usersListActivity.class);
                        break;
                    case 2:
                        // Navigate to AddProductActivity
                       // intent = new Intent(mContext, AddProductActivity.class);
                        break;
                    case 3:
                        // Navigate to UpdateProductActivity
                       // intent = new Intent(mContext, UpdateProductActivity.class);
                        break;
                }

                if (intent != null) {
                    mContext.startActivity(intent);
                }
            }
        });

        return gridItem;
    }
}