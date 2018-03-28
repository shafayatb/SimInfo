package com.example.siminfo;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.SubscriptionInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * Created by hello on 29/3/18.
 */

public class SimAdapter extends RecyclerView.Adapter<SimAdapter.SimViewHolder> {

    private List<SubscriptionInfo> activeSubscriptionInfoList;
    Context context;

    SimAdapter(Context context, List<SubscriptionInfo> activeSubscriptionInfoList) {
        this.context = context;
        this.activeSubscriptionInfoList = activeSubscriptionInfoList;
    }

    @Override
    public SimAdapter.SimViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SimAdapter.SimViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_sim, parent, false));
    }

    @Override
    public void onBindViewHolder(SimAdapter.SimViewHolder holder, int position) {
        holder.simCarrierName.setText("Sim " + (position + 1));
        holder.simCarrierName.setText(activeSubscriptionInfoList.get(position).getCarrierName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNetworkTypePopup();
            }
        });
    }

    @Override
    public int getItemCount() {
        return activeSubscriptionInfoList.size();
    }

    private void showNetworkTypePopup(){
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.popup_network_type);
        dialog.setTitle("Network Type");

        RecyclerView recNetworkType = (RecyclerView) dialog.findViewById(R.id.rec_popup_networktype);
        recNetworkType.setLayoutManager(new LinearLayoutManager(context));

        List<String> networkTypeList = new ArrayList<>();
        networkTypeList.add("LTE");
        networkTypeList.add("2G");
        networkTypeList.add("3G");

        recNetworkType.setAdapter(new NetworkTypeAdapter(context, networkTypeList));

        Button dialogButton = (Button) dialog.findViewById(R.id.btn_popup_ok);

        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    class SimViewHolder extends RecyclerView.ViewHolder {

        TextView simNumber;
        TextView simCarrierName;

        SimViewHolder(View itemView) {
            super(itemView);
            simNumber = itemView.findViewById(R.id.lbl_sim_number);
            simCarrierName = itemView.findViewById(R.id.lbl_carrier_name);
        }
    }
}

