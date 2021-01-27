package com.penny.quick.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.penny.database.entities.Dispute;
import com.penny.database.entities.Report;
import com.penny.database.utils.DateUtils;
import com.penny.quick.R;
import java.util.List;

public class DisputeReportsAdapter extends
    RecyclerView.Adapter<DisputeReportsAdapter.MyViewHolder> {

  private final List<Dispute> reports;

  public DisputeReportsAdapter(List<Dispute> reports) {
    this.reports = reports;
  }

  @NonNull
  @Override
  public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.dispute_report_item, parent, false);
    return new MyViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
    Dispute report = reports.get(position);
    holder.date.setText(DateUtils.getDateInRechargeDateFormat(report.getDate()));
    holder.transactionId.setText(report.getTransactionId());
    holder.message.setText(report.getMessage());
  }

  @Override
  public int getItemCount() {
    return reports.size();
  }

  static class MyViewHolder extends ViewHolder {

    private final TextView date;
    private final TextView transactionId;
    private final TextView message;

    public MyViewHolder(@NonNull View itemView) {
      super(itemView);
      transactionId = itemView.findViewById(R.id.transaction_id);
      message = itemView.findViewById(R.id.message);
//      TextView status = itemView.findViewById(R.id.status);
      date = itemView.findViewById(R.id.date);
    }
  }
}
