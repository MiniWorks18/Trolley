package com.example.trolley;

public class WoolworthsPostBodyModel {

    private boolean IsSpecial;
    private String SearchTerm;
    private String Location;
    private int PageNumber;
    private int PageSize;
    private String SortType;

    public WoolworthsPostBodyModel(String searchTerm) {
        this.SearchTerm = searchTerm;
        this.IsSpecial = false;
        this.Location = "/shop/search/products?searchTerm=" + SearchTerm;
        this.PageNumber = 1;
        this.PageSize = 24;
        this.SortType = "TraderRelevance";
    }

}
