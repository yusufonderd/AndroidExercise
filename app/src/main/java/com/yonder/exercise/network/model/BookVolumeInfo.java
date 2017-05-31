package com.yonder.exercise.network.model;

import java.util.List;

/**
 * Created by YusufMac on 30/05/17.
 */

public class BookVolumeInfo {


    /**
     * title : Cairo Jim in Search of Martenarten
     * subtitle : A Tale of Archaeology, Adventure and Astonishment
     * authors : ["Geoffrey McSkimming"]
     * publisher : Walker
     * publishedDate : 2006
     * description : In the Valley of the Kings, Egypt, our hero Cairo Jim is hunting for the lost Tomb of the Pharaoh Martenarten. With him for the ride are his trusty talking parrot Doris and Brenda the Wonder Camel. But the devious, treacherous Captain Neptune Bone is after the priceless treasure! It looks like the game is up when Bone captures the fearless trio.
     * industryIdentifiers : [{"type":"ISBN_10","identifier":"1406300209"},{"type":"ISBN_13","identifier":"9781406300208"}]
     * readingModes : {"text":false,"image":false}
     * pageCount : 187
     * printedPageCount : 192
     * dimensions : {"height":"20.00 cm","width":"12.90 cm","thickness":"1.40 cm"}
     * printType : BOOK
     * maturityRating : NOT_MATURE
     * allowAnonLogging : false
     * contentVersion : preview-1.0.0
     * imageLinks : {"smallThumbnail":"http://books.google.com/books/content?id=MU7FNih0ptgC&printsec=frontcover&img=1&zoom=5&imgtk=AFLRE72KmfVhmgNToZWt7-5_68gCbCXrPjzf_saUh6Fl7oQ7_AOH7UM4rBqGuK46NJ7hjk6RC29ehm9VOcgu67LZ56FH8pUay6tMN6FgIFhFQFT-Wt9coMyw2ILT4WeAVRp6LFSIn6gB&source=gbs_api","thumbnail":"http://books.google.com/books/content?id=MU7FNih0ptgC&printsec=frontcover&img=1&zoom=1&imgtk=AFLRE72O3Pxcmxh3wuwa-xOR87iXdhCzvVOSR0xtWcnv5Fleo8vs_1AHWmaN2hf3wVnM1WvHx__y9wjKeWsHe2H58tTRdifXmjygfVeQzD-6jRXf8T7-GSzcpf1t2ur1U4bNmSuSo0Sd&source=gbs_api","small":"http://books.google.com/books/content?id=MU7FNih0ptgC&printsec=frontcover&img=1&zoom=2&imgtk=AFLRE71RbMRlTlZaBthoUl8HZkSZKz1kxxWQukEAgwBcaju3xRHTOEOz1dhIhvqcdveSUOCIGZjeGftrB-dwG7o4-6rnD7rtcEReLoKnzrnKu71l1cS2Lki2blLRRx87wuKWVHDrlaWI&source=gbs_api","medium":"http://books.google.com/books/content?id=MU7FNih0ptgC&printsec=frontcover&img=1&zoom=3&imgtk=AFLRE72fhKcgdx9Ea1oVs5t90Gaim7bO2xWnRT9tO5QSSz298djDT-oPzuQFSkgrgWBUeqHLJgA9TxMbYb-8AjK_r7dlTjOAuFhKp0yiQFbzci4dSWOO9WVq3_5HH3E6pTajAq_GSo9D&source=gbs_api","large":"http://books.google.com/books/content?id=MU7FNih0ptgC&printsec=frontcover&img=1&zoom=4&imgtk=AFLRE7176ChspRClxo0S1NViszoAIzL5dnZ4aOjRPS-WytzKRyUx0fpHBnC9x1iPnK0VaxvtDDFmvoivY2x1QtWN17IUyF2f7ynQh2BDnJFJyD127YeRM2oFYkknpClOXi455jMutJIe&source=gbs_api","extraLarge":"http://books.google.com/books/content?id=MU7FNih0ptgC&printsec=frontcover&img=1&zoom=6&imgtk=AFLRE71ONES9ScbjMyDa1t1WWYSHiaIystBR70DygZ3VNW82eKOyOGEnt9merbBwrECLNl-Oi4ra7uSvPXIpyQaCg04pLkj4j16uEyyx7tUiR1h8SQ_lV7rqpe4Df29kM1fE5FccDkHp&source=gbs_api"}
     * language : en
     * previewLink : http://books.google.com.tr/books?id=MU7FNih0ptgC&hl=&source=gbs_api
     * infoLink : https://play.google.com/store/books/details?id=MU7FNih0ptgC&source=gbs_api
     * canonicalVolumeLink : https://market.android.com/details?id=book-MU7FNih0ptgC
     */

    private String title;
    private String subtitle;
    private String publisher;
    private String publishedDate;
    private String description;
    private int pageCount;
    private int printedPageCount;
    private BookDimensions dimensions;
    private String printType;
    private String maturityRating;
    private boolean allowAnonLogging;
    private String contentVersion;
    private BookImageLinks imageLinks;
    private String language;
    private String previewLink;
    private String infoLink;
    private String canonicalVolumeLink;
    private List<String> authors;
    private List<BookIndustryIdentifiers> industryIdentifiers;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getPrintedPageCount() {
        return printedPageCount;
    }

    public void setPrintedPageCount(int printedPageCount) {
        this.printedPageCount = printedPageCount;
    }

    public BookDimensions getDimensions() {
        return dimensions;
    }

    public void setDimensions(BookDimensions dimensions) {
        this.dimensions = dimensions;
    }

    public String getPrintType() {
        return printType;
    }

    public void setPrintType(String printType) {
        this.printType = printType;
    }

    public String getMaturityRating() {
        return maturityRating;
    }

    public void setMaturityRating(String maturityRating) {
        this.maturityRating = maturityRating;
    }

    public boolean isAllowAnonLogging() {
        return allowAnonLogging;
    }

    public void setAllowAnonLogging(boolean allowAnonLogging) {
        this.allowAnonLogging = allowAnonLogging;
    }

    public String getContentVersion() {
        return contentVersion;
    }

    public void setContentVersion(String contentVersion) {
        this.contentVersion = contentVersion;
    }

    public BookImageLinks getImageLinks() {
        return imageLinks;
    }

    public void setImageLinks(BookImageLinks imageLinks) {
        this.imageLinks = imageLinks;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPreviewLink() {
        return previewLink;
    }

    public void setPreviewLink(String previewLink) {
        this.previewLink = previewLink;
    }

    public String getInfoLink() {
        return infoLink;
    }

    public void setInfoLink(String infoLink) {
        this.infoLink = infoLink;
    }

    public String getCanonicalVolumeLink() {
        return canonicalVolumeLink;
    }

    public void setCanonicalVolumeLink(String canonicalVolumeLink) {
        this.canonicalVolumeLink = canonicalVolumeLink;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public List<BookIndustryIdentifiers> getIndustryIdentifiers() {
        return industryIdentifiers;
    }

}

