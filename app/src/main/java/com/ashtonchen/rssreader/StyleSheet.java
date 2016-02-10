package com.ashtonchen.rssreader;

/**
 * Created by Ashton Chen on 15-12-10.
 */
public class StyleSheet {

    public static final int PADDING = 10;

    public static final int CELL_TOP_PADDING = 0;
    public static final int CELL_BOTTOM_PADDING = CELL_TOP_PADDING;
    public static final int CELL_LEFT_PADDING = CELL_TOP_PADDING;
    public static final int CELL_RIGHT_PADDING = CELL_LEFT_PADDING;

    //public static final int CELL_THUMBNAIL_SIZE = 100;
    public static final int CELL_Thumbnail_TEXT_SPACING = 10;

    public static final int CELL_TEXT_VIEW_SPACING = 6;

    public static final int CELL_TITLE_MAX_LINE = 3;
    public static final float CELL_TITLE_LINE_HEIGHT_MULTIPLIER = 0.975f;
    public static final int CELL_TITLE_FONT_SIZE = 17;

    public static final int CELL_DESCRIPTION_MAX_LINE = 2;
    public static final float CELL_DESCRIPTION_LINE_HEIGHT_MULTIPLIER = 0.875f;
    public static final int CELL_DESCRIPTION_FONT_SIZE = 15;

    public static final int DETAIL_TITLE_FONT_SIZE = CELL_TITLE_FONT_SIZE + 2;
    public static final int DETAIL_DESCRIPTION_FONT_SIZE = CELL_DESCRIPTION_FONT_SIZE + 2;

    public static final int CELL_THUMBNAIL_SIZE =
            CELL_TITLE_FONT_SIZE +
                    ((int) (CELL_TITLE_FONT_SIZE * CELL_TITLE_LINE_HEIGHT_MULTIPLIER + CELL_TITLE_FONT_SIZE) * (CELL_TITLE_MAX_LINE - 1)) +
                    CELL_TEXT_VIEW_SPACING +
                    CELL_DESCRIPTION_FONT_SIZE +
                    ((int) (CELL_DESCRIPTION_FONT_SIZE * CELL_DESCRIPTION_LINE_HEIGHT_MULTIPLIER) * (CELL_DESCRIPTION_MAX_LINE - 1));
}
