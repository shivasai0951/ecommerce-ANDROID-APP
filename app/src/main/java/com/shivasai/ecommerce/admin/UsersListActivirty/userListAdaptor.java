package com.shivasai.ecommerce.admin.UsersListActivirty;

import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import com.shivasai.ecommerce.R;
import com.shivasai.ecommerce.Service.usersListService.usersGetModel;
import java.util.List;

public class userListAdaptor extends RecyclerView.Adapter<userListAdaptor.MyviewHolder> {

    List<usersGetModel> list;

    public userListAdaptor(List<usersGetModel> list){
        this.list=list;
    }

    @NonNull
    @Override
    public userListAdaptor.MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.userlayout,null);
        userListAdaptor.MyviewHolder myviewHolder=new userListAdaptor.MyviewHolder(view);
        return  myviewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull userListAdaptor.MyviewHolder holder, int position) {

        holder.name.setText(list.get(position).getName());
        holder.role.setText(list.get(position).getType());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyviewHolder extends RecyclerView.ViewHolder {

        TextView name,role;

        public MyviewHolder(@NonNull View itemView) {
            super(itemView);
            name =itemView.findViewById(R.id.userListNameId);
            role =itemView.findViewById(R.id.userListRoleId);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getLayoutPosition();
                    String email = list.get(position).getEmail();
                    String password = list.get(position).getPassword();
                    String userType = list.get(position).getType();
                    String userName = list.get(position).getName();

                    // Log details
                    Log.e("Email", email);
                    Log.e("Password", password);
                    Log.e("UserType", userType);
                    Log.e("Name", userName);

                    // Create AlertDialog
                    AlertDialog.Builder builder = new AlertDialog.Builder(itemView.getContext());
                    builder.setTitle("User Details")
                            .setMessage("Name: " + userName + "\nEmail: " + email + "\nPassword: " + password + "\nUser Type: " + userType)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });

                    AlertDialog dialog = builder.create();
                    dialog.show();

                }
            });
        }
    }

}
