package com.proky.booking.stub;

import com.proky.booking.dto.PageDto;

public class PageDtoStubProvider {
    private static PageDtoStubProvider mInstance;

    private PageDtoStubProvider() {
    }

    public static PageDtoStubProvider getInstance() {
        if (mInstance == null) {
            mInstance = new PageDtoStubProvider();
        }
        return mInstance;
    }

    public PageDto getDefaultPageDto() {
        return new PageDto.Builder().currentPageIndex(0L).isNextClicked(false).isPreviousClicked(false).pageSize("3").build();
    }

    public PageDto getFirstPageDto() {
        return new PageDto.Builder()
                .currentPageIndex(0L)
                .allPagesAmount(2L)
                .startPageIndex(0L)
                .endPageIndex(1L)
                .pageSize("3")
                .isNextClicked(false)
                .isPreviousClicked(false)
                .isLeftButtonDisabled(true)
                .isRightButtonDisabled(false)
                .build();
    }
}
