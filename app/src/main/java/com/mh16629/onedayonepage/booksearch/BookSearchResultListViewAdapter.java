package com.mh16629.onedayonepage.booksearch;

import android.content.Context;
import android.net.Uri;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mh16629.onedayonepage.R;
import com.mh16629.onedayonepage.aladdin.AladdinBookSearchItem;

import java.util.ArrayList;

public class BookSearchResultListViewAdapter extends BaseAdapter {

    private ImageView itemBookSearchImg;
    private TextView itemBookSearchTitle ;
    private TextView itemBookSearchAuthor ;
    private TextView itemBookSearchPubDate ;
    private TextView itemBookSearchPublisher ;
    private TextView itemBookSearchDescription ;

    private ArrayList<AladdinBookSearchItem> listViewItemList = new ArrayList<AladdinBookSearchItem>();

    public BookSearchResultListViewAdapter() {
    }

    @Override
    public int getCount() {
        return listViewItemList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        // "listview_item" Layout을 inflate하여 convertView참조 획득
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_book_search, parent, false);
        }

        itemBookSearchImg = (ImageView) convertView.findViewById(R.id.item_book_search_img);
        itemBookSearchTitle = (TextView) convertView.findViewById(R.id.item_book_search_title);
        itemBookSearchAuthor = (TextView) convertView.findViewById(R.id.item_book_search_author);
        itemBookSearchPubDate = (TextView) convertView.findViewById(R.id.item_book_search_pubDate);
        itemBookSearchPublisher = (TextView) convertView.findViewById(R.id.item_book_search_publisher);
        itemBookSearchDescription = (TextView) convertView.findViewById(R.id.item_book_search_description);

        AladdinBookSearchItem listViewItem = listViewItemList.get(position);

        //아이템 내 각 위젯에 데이터 반영
        //FIXME: 이미지 Uri파싱을 여기서 하도록 수정
//        itemBookSearchImg.setImageURI(listViewItem.getImgUri());
        itemBookSearchTitle.setText(listViewItem.getTitle());
        itemBookSearchAuthor.setText(listViewItem.getAuthor());
        itemBookSearchPubDate.setText(listViewItem.getPubDate());
        itemBookSearchPublisher.setText(listViewItem.getPublisher());
        itemBookSearchDescription.setText(listViewItem.getDescription());

        return convertView;
    }

    /**
     * 지정한 위치(position)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴
     * @param position
     * @return
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * 지정한 위치(position)에 있는 데이터 리턴
     * @param position
     * @return
     */
    @Override
    public Object getItem(int position) {
        return listViewItemList.get(position);
    }

    /**
     * 아이템 데이터 추가
     * @param imgUri
     * @param title
     * @param author
     * @param pubDate
     * @param publisher
     * @param description
     */
    public void addItem(Uri imgUri, String title, String author, String pubDate, String publisher, String description) {
        AladdinBookSearchItem item = new AladdinBookSearchItem();

        //FIXME: 이미지 Uri파싱을 여기서 하도록 수정
//        item.setImgRrlStr(imgUri);
        item.setTitle(title);
        item.setAuthor(author);
        item.setPubDate(pubDate);
        item.setPublisher(publisher);
        item.setDescription(description);

        listViewItemList.add(item);
    }

    public void addItem(AladdinBookSearchItem item){
        listViewItemList.add(item);
    }

    public void addItem(ArrayList<AladdinBookSearchItem> list) {
        listViewItemList.addAll(list);
    }

    public void listViewClear() {
        listViewItemList = new ArrayList<AladdinBookSearchItem>();
    }
}
