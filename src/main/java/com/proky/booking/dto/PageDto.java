package com.proky.booking.dto;

import com.proky.booking.util.constans.http.Parameters;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class PageDto implements Serializable {
    private static final Logger log = LogManager.getLogger(PageDto.class);

    private Long currentPageIndex;
    private Boolean isNextClicked;
    private Boolean isPreviousClicked;
    private String pageSize;
    private static final long DEFAULT_START_INDEX = 0;
    private boolean isLeftButtonDisabled;
    private boolean isRightButtonDisabled;
    private Long startPageIndex;
    private Long endPageIndex;
    private long allPagesAmount;

//    private Long userId;
    private List pageList;

    private PageDto(Builder builder) {
        setCurrentPageIndex(builder.currentPageIndex);
        isNextClicked = builder.isNextClicked;
        isPreviousClicked = builder.isPreviousClicked;
        setPageSize(builder.pageSize);
        setLeftButtonDisabled(builder.isLeftButtonDisabled);
        setRightButtonDisabled(builder.isRightButtonDisabled);
        startPageIndex = builder.startPageIndex;
        endPageIndex = builder.endPageIndex;
        setAllPagesAmount(builder.allPagesAmount);
        setPageList(builder.pageList);
    }

    public void setRequestParameters(HttpServletRequest request) {
        String selectedPageIndex = request.getParameter(Parameters.SELECTED_PAGE_INDEX);
        final String pageSize = request.getParameter(Parameters.PAGE_SIZE);
        final String isNextClicked = request.getParameter(Parameters.NEXT_PAGE_CLICK);
        final String isPreviousClicked = request.getParameter(Parameters.PREV_PAGE_CLICK);

        if (selectedPageIndex == null && currentPageIndex != null) {
            selectedPageIndex = currentPageIndex.toString();
        }

        this.currentPageIndex = (selectedPageIndex != null) ? Long.parseLong(selectedPageIndex) : DEFAULT_START_INDEX;
        this.isNextClicked = Boolean.parseBoolean(isNextClicked);
        this.isPreviousClicked = Boolean.parseBoolean(isPreviousClicked);
        this.pageSize = pageSize;
    }

    public PageDto() {
        currentPageIndex = DEFAULT_START_INDEX;
        this.isNextClicked =  false;
        this.isPreviousClicked = false;
    }

    public List getPageList() {
        return pageList;
    }

    public void setPageList(List pageList) {
        this.pageList = pageList;
    }

    public Long getCurrentPageIndex() {
        return currentPageIndex;
    }

    public void setCurrentPageIndex(Long selectedPageIndex) {
        this.currentPageIndex = selectedPageIndex;
    }

    public Boolean getNextClicked() {
        return isNextClicked;
    }

    public void setNextClicked(Boolean nextClicked) {
        isNextClicked = nextClicked;
    }

    public Boolean getPreviousClicked() {
        return isPreviousClicked;
    }

    public void setPreviousClicked(Boolean previousClicked) {
        isPreviousClicked = previousClicked;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

//    public Long getUserId() {
//        return userId;
//    }
//
//    public void setUserId(Long userId) {
//        this.userId = userId;
//    }

    public boolean getIsLeftButtonDisabled() {
        return isLeftButtonDisabled;
    }

    public void setLeftButtonDisabled(boolean leftButtonDisabled) {
        isLeftButtonDisabled = leftButtonDisabled;
    }

    public boolean getIsRightButtonDisabled() {
        return isRightButtonDisabled;
    }

    public void setRightButtonDisabled(boolean rightButtonDisabled) {
        isRightButtonDisabled = rightButtonDisabled;
    }

    public Long getStartPageIndex() {
        return startPageIndex;
    }

    public void setStartPageIndex(long startPageIndex) {
        this.startPageIndex = startPageIndex;
    }

    public Long getEndPageIndex() {
        return endPageIndex;
    }

    public void setEndPageIndex(long endPageIndex) {
        this.endPageIndex = endPageIndex;
    }

    public long getAllPagesAmount() {
        return allPagesAmount;
    }

    public void setAllPagesAmount(long allPagesAmount) {
        this.allPagesAmount = allPagesAmount;
    }

    @Override
    public String toString() {
        return "PaginationDto{" +
                "isLeftButtonDisabled=" + isLeftButtonDisabled +
                ", isRightButtonDisabled=" + isRightButtonDisabled +
                ", startPageIndex=" + startPageIndex +
                ", endPageIndex=" + endPageIndex +
                ", currentPageIndex=" + currentPageIndex +
                ", isNextClicked=" + isNextClicked +
                ", isPreviousClicked=" + isPreviousClicked +
                ", allPagesAmount=" + allPagesAmount +
                ", pageSize=" + pageSize +
                ", paginationListSize=" + (pageList != null ? pageList.size() : "empty") +
                '}';
    }


    public static final class Builder {
        private Long currentPageIndex;
        private Boolean isNextClicked;
        private Boolean isPreviousClicked;
        private String pageSize;
        private boolean isLeftButtonDisabled;
        private boolean isRightButtonDisabled;
        private Long startPageIndex;
        private Long endPageIndex;
        private long allPagesAmount;
        private List pageList;

        public Builder() {
        }

        public Builder currentPageIndex(Long val) {
            currentPageIndex = val;
            return this;
        }

        public Builder isNextClicked(Boolean val) {
            isNextClicked = val;
            return this;
        }

        public Builder isPreviousClicked(Boolean val) {
            isPreviousClicked = val;
            return this;
        }

        public Builder pageSize(String val) {
            pageSize = val;
            return this;
        }

        public Builder isLeftButtonDisabled(boolean val) {
            isLeftButtonDisabled = val;
            return this;
        }

        public Builder isRightButtonDisabled(boolean val) {
            isRightButtonDisabled = val;
            return this;
        }

        public Builder startPageIndex(Long val) {
            startPageIndex = val;
            return this;
        }

        public Builder endPageIndex(Long val) {
            endPageIndex = val;
            return this;
        }

        public Builder allPagesAmount(long val) {
            allPagesAmount = val;
            return this;
        }

        public Builder pageList(List val) {
            pageList = val;
            return this;
        }

        public PageDto build() {
            return new PageDto(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PageDto pageDto = (PageDto) o;
        return isLeftButtonDisabled == pageDto.isLeftButtonDisabled &&
                isRightButtonDisabled == pageDto.isRightButtonDisabled &&
                allPagesAmount == pageDto.allPagesAmount &&
                Objects.equals(currentPageIndex, pageDto.currentPageIndex) &&
                Objects.equals(isNextClicked, pageDto.isNextClicked) &&
                Objects.equals(isPreviousClicked, pageDto.isPreviousClicked) &&
                Objects.equals(pageSize, pageDto.pageSize) &&
                Objects.equals(startPageIndex, pageDto.startPageIndex) &&
                Objects.equals(endPageIndex, pageDto.endPageIndex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currentPageIndex, isNextClicked, isPreviousClicked, pageSize, isLeftButtonDisabled, isRightButtonDisabled, startPageIndex, endPageIndex, allPagesAmount);
    }

}
