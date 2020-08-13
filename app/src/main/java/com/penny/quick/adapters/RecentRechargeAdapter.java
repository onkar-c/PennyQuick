package com.penny.quick.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.penny.database.dao.RecentRecharge;
import com.penny.quick.R;
import java.util.List;

public class RecentRechargeAdapter extends
    RecyclerView.Adapter<RecentRechargeAdapter.MyViewHolder> {

  private List<RecentRecharge> recentRecharges;
  private Context context;

  public RecentRechargeAdapter(List<RecentRecharge> recentRecharges, Context context) {
    this.recentRecharges = recentRecharges;
    this.context = context;
  }

  @NonNull
  @Override
  public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.recent_recharge_item, parent, false);
    return new MyViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
    RecentRecharge recentRecharge = recentRecharges.get(position);
    holder.companyType.setText(recentRecharge.getCompanyType());
    holder.customerId.setText(recentRecharge.getCustomerId());
    holder.transactionId.setText(recentRecharge.getTransactionId());
    holder.date.setText(recentRecharge.getDate());
    holder.amount.setText(
        String.format("%s%s", context.getString(R.string.rupees_sign), recentRecharge.getAmount()));
    Drawable img = ContextCompat.getDrawable(context,
        (recentRecharge.getStatus() == 0) ? R.drawable.pending_small
            : (recentRecharge.getStatus() == 1) ? R.drawable.success_small
                : R.drawable.failed_small);
    holder.amount.setCompoundDrawablesWithIntrinsicBounds(null, null, null, img);
  }

  @Override
  public int getItemCount() {
    return recentRecharges.size();
  }

  static class MyViewHolder extends ViewHolder {

    private TextView companyType, customerId, transactionId, date, amount;

    public MyViewHolder(@NonNull View itemView) {
      super(itemView);
      companyType = itemView.findViewById(R.id.company_type);
      customerId = itemView.findViewById(R.id.customer_id);
      transactionId = itemView.findViewById(R.id.transaction_id);
      date = itemView.findViewById(R.id.date);
      amount = itemView.findViewById(R.id.amount);
    }
  }
}
