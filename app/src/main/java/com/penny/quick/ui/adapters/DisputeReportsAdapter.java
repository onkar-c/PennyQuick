package com.penny.quick.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.penny.database.entities.Report;
import com.penny.quick.R;
import java.util.List;

public class DisputeReportsAdapter extends
    RecyclerView.Adapter<DisputeReportsAdapter.MyViewHolder> {

  private List<Report> reports;

  public DisputeReportsAdapter(List<Report> reports) {
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
    Report report = reports.get(position);
    holder.date.setText(report.getDate());
    holder.transactionId.setText(report.getTransaction_id());
    holder.message.setText(report.getDescription());
    holder.date.setText(report.getDate());
  }

  @Override
  public int getItemCount() {
    return reports.size();
  }

  static class MyViewHolder extends ViewHolder {

    private TextView date;
    private TextView transactionId;
    private TextView message;

    public MyViewHolder(@NonNull View itemView) {
      super(itemView);
      transactionId = itemView.findViewById(R.id.transaction_id);
      message = itemView.findViewById(R.id.message);
//      TextView status = itemView.findViewById(R.id.status);
      date = itemView.findViewById(R.id.date);
    }
  }
}
