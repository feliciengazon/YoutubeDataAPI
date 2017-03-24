package fr.esilv.s8.youtubedataapi.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import fr.esilv.s8.youtubedataapi.R;
import fr.esilv.s8.youtubedataapi.models.Video;

public class VideoAdapter extends ArrayAdapter<Video.ItemsBean> {

    private final LayoutInflater layoutInflater;

    public VideoAdapter(Context context, List<Video.ItemsBean> objects) {
        super(context, 0, objects);
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        //The convertView is null when the view is inflated for the first time
        if (convertView == null) {
            //In that case, we create the view and we hold the sub views in a ViewHolder for an easier, more efficient access
            convertView = layoutInflater.inflate(R.layout.video_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.titleTextView = (TextView) convertView.findViewById(R.id.titleTextView);
            viewHolder.descriptionTextView = (TextView) convertView.findViewById(R.id.descriptionTextView);
            viewHolder.imageview = (ImageView) convertView.findViewById(R.id.image);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //Finally, we get the current item, and we set the View according to the data we wish to display
        Video.ItemsBean item = getItem(position);
        viewHolder.titleTextView.setText(item.getSnippet().getTitle());
        viewHolder.descriptionTextView.setText(item.getSnippet().getDescription());
        Picasso.with(convertView.getContext()).load(item.getSnippet().getThumbnails().getHigh().getUrl()).into(viewHolder.imageview);
        return convertView;
    }

    static class ViewHolder {
        private TextView titleTextView;
        private TextView descriptionTextView;
        private ImageView imageview;
    }
}

