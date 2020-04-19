package phamthanhhuong.com.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import phamthanhhuong.com.baitapmenu.R;
import phamthanhhuong.com.model.NhanVien;

public class NhanVienAdapter extends ArrayAdapter<NhanVien> {

    Activity context;
    int resource;
    public NhanVienAdapter(@NonNull Activity context, int resource) {
        super(context, resource);
        this.context= context;
        this.resource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
      View view=this.context.getLayoutInflater().inflate(this.resource,null);
      ImageView imgHinh=view.findViewById(R.id.imgHinh);
        TextView txtMa=view.findViewById(R.id.txtMa);
        TextView txtTen=view.findViewById(R.id.txtTen);
        NhanVien nv=getItem(position);
        txtMa.setText(nv.getMa());
        txtTen.setText(nv.getTen());
        if(nv.isLaNu())
            imgHinh.setImageResource(R.drawable.woman);
        else
            imgHinh.setImageResource(R.drawable.man);
        return view;
    }
}
