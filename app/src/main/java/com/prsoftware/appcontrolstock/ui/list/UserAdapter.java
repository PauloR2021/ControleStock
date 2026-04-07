package com.prsoftware.appcontrolstock.ui.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.prsoftware.appcontrolstock.R;
import com.prsoftware.appcontrolstock.dto.user.UserData;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private final List<UserData> listaUsuarios;

    public UserAdapter(List<UserData> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user, parent, false);
        return new UserViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull UserAdapter.UserViewHolder holder, int position) {

        UserData user = listaUsuarios.get(position);

        holder.txtId.setText("ID: " + user.getId());
        holder.txtName.setText("Nome: " + user.getName());
        holder.txtUsername.setText("Usuário: " + user.getUsername());
        holder.txtRole.setText("Acesso: " + user.getRole());


    }

    @Override
    public int getItemCount() {
        return listaUsuarios != null ? listaUsuarios.size() : 0;
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {

        TextView txtId, txtName, txtUsername, txtRole;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            txtId = itemView.findViewById(R.id.txtId);
            txtName = itemView.findViewById(R.id.txtName);
            txtUsername = itemView.findViewById(R.id.txtUsername);
            txtRole = itemView.findViewById(R.id.txtRole);
        }
    }
}
