package br.com.migotto.light.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.migotto.light.R;
import br.com.migotto.light.model.Light;

/**
 * Created by appCivico on 24/10/2017.
 */

public class RVLightAdapter  extends RecyclerView.Adapter<RVLightAdapter.LightViewHolder>{

    private final List<Light> list;
    private final Context context;
    private final LightOnClickListener lightOnClickListener;

    public RVLightAdapter(List<Light> list, Context context, LightOnClickListener lightOnClickListener) {
        this.list = list;
        this.context = context;
        this.lightOnClickListener=lightOnClickListener;
    }

    public interface LightOnClickListener{
        public void onClickListener(View view, int position);
    }

    @Override
    public RVLightAdapter.LightViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.light_adapter, parent, false);
        return new LightViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RVLightAdapter.LightViewHolder holder, final int position) {
        holder.description.setText(list.get(position).getDescription());
        if(lightOnClickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    lightOnClickListener.onClickListener(view, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list!=null?list.size():0;
    }

    public class LightViewHolder extends RecyclerView.ViewHolder{
        TextView description;
        public LightViewHolder(View itemView) {
            super(itemView);
            description =itemView.findViewById(R.id.description_light_adapter);
        }
    }
}
