package com.example.siminfo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by hello on 29/3/18.
 */

public class NetworkTypeAdapter extends RecyclerView.Adapter<NetworkTypeAdapter.NetworkViewHolder> {

    private List<String> networkTypeList;
    Context context;

    NetworkTypeAdapter(Context context, List<String> networkTypeList) {
        this.context = context;
        this.networkTypeList = networkTypeList;
    }

    @Override
    public NetworkTypeAdapter.NetworkViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NetworkTypeAdapter.NetworkViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_sim, parent, false));
    }

    @Override
    public void onBindViewHolder(NetworkTypeAdapter.NetworkViewHolder holder, int position) {
        holder.simNumber.setText(networkTypeList.get(position));
        if (networkTypeList.get(position).equals(getNetworkType())) {
            holder.simCarrierName.setText("Connected");
        } else {
            holder.simCarrierName.setText("");
        }
    }

    @Override
    public int getItemCount() {
        return networkTypeList.size();
    }

    String getNetworkType() {
        TelephonyManager mTelephonyManager = (TelephonyManager)
                context.getSystemService(Context.TELEPHONY_SERVICE);

        int networkType = mTelephonyManager.getNetworkType();
        switch (networkType) {
            case TelephonyManager.NETWORK_TYPE_IDEN:
                return "2G";
            case TelephonyManager.NETWORK_TYPE_HSPAP:
                return "3G";
            case TelephonyManager.NETWORK_TYPE_LTE:
                return "LTE";
            default:
                return "Notfound";
        }
    }


    class NetworkViewHolder extends RecyclerView.ViewHolder {

        TextView simNumber;
        TextView simCarrierName;

        NetworkViewHolder(View itemView) {
            super(itemView);
            simNumber = itemView.findViewById(R.id.lbl_sim_number);
            simCarrierName = itemView.findViewById(R.id.lbl_carrier_name);
        }
    }


}