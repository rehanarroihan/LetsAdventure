package id.sch.smktelkom_mlg.letsadventure.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import id.sch.smktelkom_mlg.letsadventure.R;
import id.sch.smktelkom_mlg.letsadventure.model.Pariwisata;

/**
 * Created by Rehan on 26/04/2017.
 */

public class PariwisataAdapter extends RecyclerView.Adapter<PariwisataAdapter.MyViewHolder> {
    private List<Pariwisata> pariwisataList;
    private Context context;

    public PariwisataAdapter(List<Pariwisata> pariwisataList) {
        this.pariwisataList = pariwisataList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list, parent, false);
        context = parent.getContext();
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Pariwisata p = pariwisataList.get(position);
        Log.d("APP", p.getJudul_wisata());
        Log.d("APP", p.getDetail_wisata());
        holder.judul.setText(p.getJudul_wisata());
        holder.deskripsi.setText(p.getLokasi());

        String link = p.getGambar();
        if (p.getGambar().contains("imgur.com")) {
            String newLink = link.replace("imgur.com", "i.imgur.com");
            link = newLink + ".jpg";
        }

        Picasso.with(context).load(link).into(holder.gambar);
    }

    @Override
    public int getItemCount() {
        return pariwisataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView judul, deskripsi;
        public ImageView gambar;

        public MyViewHolder(View view) {
            super(view);
            judul = (TextView) view.findViewById(R.id.rvJudul);
            deskripsi = (TextView) view.findViewById(R.id.rvDeskripsi);
            gambar = (ImageView) view.findViewById(R.id.imageView);
        }
    }
}
