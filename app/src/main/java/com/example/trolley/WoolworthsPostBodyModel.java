package com.example.trolley;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WoolworthsPostBodyModel {

    @SerializedName("IsSpecial")
    @Expose
    private Boolean isSpecial;
    @SerializedName("Location")
    @Expose
    private String location;
    @SerializedName("PageNumber")
    @Expose
    private Integer pageNumber;
    @SerializedName("PageSize")
    @Expose
    private Integer pageSize;
    @SerializedName("SearchTerm")
    @Expose
    private String searchTerm;
    @SerializedName("SortType")
    @Expose
    private String sortType;

    WoolworthsPostBodyModel(String searchTerm) {
        this.searchTerm = searchTerm;
        this.isSpecial = false;
        this.location = "/shop/search/products?searchTerm="+searchTerm;
        this.pageNumber = 1;
        this.pageSize = 24;
        this.sortType = "TraderRelevance";
    }

    public Boolean getIsSpecial() {
        return isSpecial;
    }

    public void setIsSpecial(Boolean isSpecial) {
        this.isSpecial = isSpecial;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public String getSortType() {
        return sortType;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }

}