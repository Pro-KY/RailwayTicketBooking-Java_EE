package com.proky.booking.service;

import com.proky.booking.dto.PageDto;
import com.proky.booking.util.constans.http.Attributes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpSession;

public class PaginationService {
    private long pageSize;
    private long offSet = 0;
    private long startVisibleIndex;
    private long endVisibleIndex;
    private long currentPageIndex;

    private long allRowsAmount;
    private long allPagesAmount;

    private static final int DEFAULT_PAGE_SIZE = 3; // 5
    private static final int DEFAULT_START_INDEX = 0;
    private static final int DEFAULT_END_INDEX = 4;

    private boolean isLeftButtonDisabled;
    private boolean isRightButtonDisabled;
    private PageDto pageDto;

    public PageDto getpageDto() {
        return pageDto;
    }

    private static final Logger log = LogManager.getLogger(PaginationService.class);

    public PaginationService() { }

    public PaginationService(PageDto pageDto) {
        this.pageDto = pageDto;
    }

    public void calculatePagination() {
        final Long startVisibleIndex = pageDto.getStartPageIndex();
        final Long endVisibleIndex = pageDto.getEndPageIndex();
        final Long currentPageIndex = pageDto.getCurrentPageIndex();

        this.startVisibleIndex = (startVisibleIndex != null) ? startVisibleIndex : DEFAULT_START_INDEX;
        this.endVisibleIndex = (endVisibleIndex != null) ? endVisibleIndex : DEFAULT_END_INDEX;
        this.currentPageIndex = (currentPageIndex != null) ? (Long) currentPageIndex : DEFAULT_START_INDEX;
        final String pageSize = pageDto.getPageSize();
        this.pageSize = (pageSize != null) ? Integer.parseInt(pageSize) : DEFAULT_PAGE_SIZE;

        calculateAllPagesAmount();
        calculateEndVisibleIndex();
        changeButtonsState();

        if (pageDto.getNextClicked()) {
            handleNextButtonClick();
        } else if (pageDto.getPreviousClicked()) {
            handlePreviousButtonClick();
        } else {
            calculateOffset();
        }
    }

    public void updatePageDto() {
        pageDto.setCurrentPageIndex(currentPageIndex);
        pageDto.setStartPageIndex(startVisibleIndex);
        pageDto.setEndPageIndex(endVisibleIndex);
        pageDto.setLeftButtonDisabled(isLeftButtonDisabled);
        pageDto.setRightButtonDisabled(isRightButtonDisabled);
        pageDto.setAllPagesAmount(allPagesAmount);
        pageDto.setPageSize(String.valueOf(pageSize));
    }

    public void setAllRowsAmount(long allRowsAmount) {
        this.allRowsAmount = allRowsAmount;
    }


    public void calculateAllPagesAmount() {
        allPagesAmount = allRowsAmount / this.pageSize;
        log.info("exp: {}", allPagesAmount % this.pageSize);
//        allPagesAmount += allPagesAmount % this.pageSize > 0 ? 1 : 0;
        allPagesAmount += allRowsAmount % this.pageSize > 0 ? 1 : 0;
    }

    public void calculateEndVisibleIndex() {
        log.info("endVisibleIndex before - {}", endVisibleIndex);
        if (allPagesAmount == 0) {
            endVisibleIndex = 0;
        } else if (endVisibleIndex == 0 & allPagesAmount > DEFAULT_END_INDEX) {
            endVisibleIndex = DEFAULT_END_INDEX;
        } else if (allPagesAmount < DEFAULT_END_INDEX) {
            endVisibleIndex = allPagesAmount - 1;
        }
        log.info("endVisibleIndex after - {}", endVisibleIndex);
    }

    // 3
    public void calculateOffset() { // 2
        offSet = currentPageIndex * pageSize;
    }

    public void handleNextButtonClick() {
        if (currentPageIndex < endVisibleIndex) {
            currentPageIndex += 1;
        } else if (currentPageIndex == endVisibleIndex) {
            shiftPagesIndexes(true);
        }
        changeButtonsState();
        calculateOffset();
    }

    public void handlePreviousButtonClick() {
        if (currentPageIndex > startVisibleIndex) {
            currentPageIndex -= 1;
        } else if (currentPageIndex == startVisibleIndex) {
            shiftPagesIndexes(false);
        }
        changeButtonsState();
        calculateOffset();
    }

    public void changeButtonsState() {
        isLeftButtonDisabled = (currentPageIndex == startVisibleIndex) & (currentPageIndex == DEFAULT_START_INDEX);
        isRightButtonDisabled = currentPageIndex == allPagesAmount - 1;
    }

    private void shiftPagesIndexes(boolean forward) {
        startVisibleIndex += forward ? 1 : -1;
        endVisibleIndex += forward ? 1 : -1;
        currentPageIndex += forward ? 1 : -1;

        log.info("shift indexes");
        log.info("startVisibleIndex - {}", startVisibleIndex);
        log.info("endVisibleIndex - {}", endVisibleIndex);
    }

    public long getPageSize() {
        return pageSize;
    }

    public long getOffSet() {
        return offSet;
    }

    public static PageDto getCurrentPageDto(HttpSession session) {
        PageDto currentPageDto = (PageDto) session.getAttribute(Attributes.MODEL);
        if (currentPageDto == null) {
            currentPageDto = new PageDto();
        }
        return currentPageDto;
    }
}
