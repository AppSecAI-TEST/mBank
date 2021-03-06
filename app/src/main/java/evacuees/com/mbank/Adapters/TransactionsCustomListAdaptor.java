package evacuees.com.mbank.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import evacuees.com.mbank.R;
import evacuees.com.mbank.TransactionsListData;

/**
 * Created by Deepak on 10-Aug-17.
 */

public class TransactionsCustomListAdaptor extends BaseAdapter {
    ArrayList<TransactionsListData> mylist = new ArrayList<TransactionsListData>();
    LayoutInflater inflater;
    Context context;

    public TransactionsCustomListAdaptor(Context context, ArrayList<TransactionsListData> mylist) {
        this.mylist = mylist;
        this.context = context;
        inflater = LayoutInflater.from(this.context);
    }

    @Override
    public int getCount() {
        return mylist.size();
    }

    @Override
    public Object getItem(int position) {
        return mylist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder myViewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.transaction_row_layout, parent, false);
            myViewHolder = new MyViewHolder(convertView);
            convertView.setTag(myViewHolder);
        } else {
            myViewHolder = (MyViewHolder) convertView.getTag();
        }
        TransactionsListData currentListData = (TransactionsListData) getItem(position);
        myViewHolder.date.setText(currentListData.getDate());
        myViewHolder.time.setText(currentListData.getTime());
        myViewHolder.amount.setText("Rs. " + currentListData.getAmount());


        if (currentListData.getStatus().equals("credit")) {
            myViewHolder.status.setText("C");
            myViewHolder.sendto.setText("From: " + currentListData.getSendto());
            myViewHolder.amount.setText("+Rs. " + currentListData.getAmount());


        } else if (currentListData.getStatus().equals("added")) {
            myViewHolder.status.setText("A");
            myViewHolder.sendto.setText("From: " + currentListData.getSendto());
            myViewHolder.amount.setText("+Rs. " + currentListData.getAmount());


        } else {

            myViewHolder.status.setText("D");
            myViewHolder.sendto.setText("To: " + currentListData.getSendto());
            myViewHolder.amount.setText("-Rs. " + currentListData.getAmount());


        }


        return convertView;
    }

    private class MyViewHolder {
        TextView sendto, date, time, amount, status;

        public MyViewHolder(View Item) {
            sendto = (TextView) Item.findViewById(R.id.to);
            date = (TextView) Item.findViewById(R.id.date);
            time = (TextView) Item.findViewById(R.id.time);
            amount = (TextView) Item.findViewById(R.id.money);
            status = (TextView) Item.findViewById(R.id.status);


        }
    }
}
