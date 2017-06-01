package com.yonder.exercise.network.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by YusufMac on 30/05/17.
 */

public class SingleBook implements Parcelable {
    /**
     * kind : books#volume
     * id : MU7FNih0ptgC
     * etag : scCbW3HPNFI
     * selfLink : https://www.googleapis.com/books/v1/volumes/MU7FNih0ptgC
     * volumeInfo : {"title":"Cairo Jim in Search of Martenarten","subtitle":"A Tale of Archaeology, Adventure and Astonishment","authors":["Geoffrey McSkimming"],"publisher":"Walker","publishedDate":"2006","description":"In the Valley of the Kings, Egypt, our hero Cairo Jim is hunting for the lost Tomb of the Pharaoh Martenarten. With him for the ride are his trusty talking parrot Doris and Brenda the Wonder Camel. But the devious, treacherous Captain Neptune Bone is after the priceless treasure! It looks like the game is up when Bone captures the fearless trio.","industryIdentifiers":[{"type":"ISBN_10","identifier":"1406300209"},{"type":"ISBN_13","identifier":"9781406300208"}],"readingModes":{"text":false,"image":false},"pageCount":187,"printedPageCount":192,"dimensions":{"height":"20.00 cm","width":"12.90 cm","thickness":"1.40 cm"},"printType":"BOOK","maturityRating":"NOT_MATURE","allowAnonLogging":false,"contentVersion":"preview-1.0.0","imageLinks":{"smallThumbnail":"http://books.google.com/books/content?id=MU7FNih0ptgC&printsec=frontcover&img=1&zoom=5&imgtk=AFLRE72KmfVhmgNToZWt7-5_68gCbCXrPjzf_saUh6Fl7oQ7_AOH7UM4rBqGuK46NJ7hjk6RC29ehm9VOcgu67LZ56FH8pUay6tMN6FgIFhFQFT-Wt9coMyw2ILT4WeAVRp6LFSIn6gB&source=gbs_api","thumbnail":"http://books.google.com/books/content?id=MU7FNih0ptgC&printsec=frontcover&img=1&zoom=1&imgtk=AFLRE72O3Pxcmxh3wuwa-xOR87iXdhCzvVOSR0xtWcnv5Fleo8vs_1AHWmaN2hf3wVnM1WvHx__y9wjKeWsHe2H58tTRdifXmjygfVeQzD-6jRXf8T7-GSzcpf1t2ur1U4bNmSuSo0Sd&source=gbs_api","small":"http://books.google.com/books/content?id=MU7FNih0ptgC&printsec=frontcover&img=1&zoom=2&imgtk=AFLRE71RbMRlTlZaBthoUl8HZkSZKz1kxxWQukEAgwBcaju3xRHTOEOz1dhIhvqcdveSUOCIGZjeGftrB-dwG7o4-6rnD7rtcEReLoKnzrnKu71l1cS2Lki2blLRRx87wuKWVHDrlaWI&source=gbs_api","medium":"http://books.google.com/books/content?id=MU7FNih0ptgC&printsec=frontcover&img=1&zoom=3&imgtk=AFLRE72fhKcgdx9Ea1oVs5t90Gaim7bO2xWnRT9tO5QSSz298djDT-oPzuQFSkgrgWBUeqHLJgA9TxMbYb-8AjK_r7dlTjOAuFhKp0yiQFbzci4dSWOO9WVq3_5HH3E6pTajAq_GSo9D&source=gbs_api","large":"http://books.google.com/books/content?id=MU7FNih0ptgC&printsec=frontcover&img=1&zoom=4&imgtk=AFLRE7176ChspRClxo0S1NViszoAIzL5dnZ4aOjRPS-WytzKRyUx0fpHBnC9x1iPnK0VaxvtDDFmvoivY2x1QtWN17IUyF2f7ynQh2BDnJFJyD127YeRM2oFYkknpClOXi455jMutJIe&source=gbs_api","extraLarge":"http://books.google.com/books/content?id=MU7FNih0ptgC&printsec=frontcover&img=1&zoom=6&imgtk=AFLRE71ONES9ScbjMyDa1t1WWYSHiaIystBR70DygZ3VNW82eKOyOGEnt9merbBwrECLNl-Oi4ra7uSvPXIpyQaCg04pLkj4j16uEyyx7tUiR1h8SQ_lV7rqpe4Df29kM1fE5FccDkHp&source=gbs_api"},"language":"en","previewLink":"http://books.google.com.tr/books?id=MU7FNih0ptgC&hl=&source=gbs_api","infoLink":"https://play.google.com/store/books/details?id=MU7FNih0ptgC&source=gbs_api","canonicalVolumeLink":"https://market.android.com/details?id=book-MU7FNih0ptgC"}
     * saleInfo : {"country":"TR","saleability":"NOT_FOR_SALE","isEbook":false}
     * accessInfo : {"country":"TR","viewability":"NO_PAGES","embeddable":false,"publicDomain":false,"textToSpeechPermission":"ALLOWED","epub":{"isAvailable":false},"pdf":{"isAvailable":false},"webReaderLink":"http://play.google.com/books/reader?id=MU7FNih0ptgC&hl=&printsec=frontcover&source=gbs_api","accessViewStatus":"NONE","quoteSharingAllowed":false}
     */

    private String kind;
    private String id;
    private String etag;
    private String selfLink;
    private BookVolumeInfo volumeInfo;
    private BookSaleInfo saleInfo;
    private BookAccessInfo accessInfo;


    protected SingleBook(Parcel in) {
        kind = in.readString();
        id = in.readString();
        etag = in.readString();
        selfLink = in.readString();
        volumeInfo = in.readParcelable(BookVolumeInfo.class.getClassLoader());
        saleInfo = in.readParcelable(BookSaleInfo.class.getClassLoader());
    }

    public static final Creator<SingleBook> CREATOR = new Creator<SingleBook>() {
        @Override
        public SingleBook createFromParcel(Parcel in) {
            return new SingleBook(in);
        }

        @Override
        public SingleBook[] newArray(int size) {
            return new SingleBook[size];
        }
    };

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public String getSelfLink() {
        return selfLink;
    }

    public void setSelfLink(String selfLink) {
        this.selfLink = selfLink;
    }

    public BookVolumeInfo getVolumeInfo() {
        return volumeInfo;
    }

    public void setVolumeInfo(BookVolumeInfo volumeInfo) {
        this.volumeInfo = volumeInfo;
    }

    public BookSaleInfo getSaleInfo() {
        return saleInfo;
    }

    public void setSaleInfo(BookSaleInfo saleInfo) {
        this.saleInfo = saleInfo;
    }

    public BookAccessInfo getAccessInfo() {
        return accessInfo;
    }


    public void setAccessInfo(BookAccessInfo accessInfo) {
        this.accessInfo = accessInfo;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(kind);
        parcel.writeString(id);
        parcel.writeString(etag);
        parcel.writeString(selfLink);
        parcel.writeParcelable(volumeInfo, i);
        parcel.writeParcelable(saleInfo, i);
    }
}
