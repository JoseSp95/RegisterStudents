package com.josesp.students_register.paginator;

import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public class PageRender<T> {

    private String url;
    private Page<T> pageList;
    private int actualPage;
    private int totalPages;
    private int elementesPerPage;
    private List<PageItem> items;

    private final int linksPages = 8;

    public PageRender(Page<T> pageList, String url) {
        this.url = url;
        this.pageList = pageList;
        this.items = new ArrayList<>();

        this.actualPage = pageList.getNumber() + 1;
        this.totalPages = pageList.getTotalPages();
        this.elementesPerPage = pageList.getSize();

        int from = 0 , until = 8;

        if (totalPages <= linksPages) {
            from = 1;
            until = totalPages;
        }
        else {

            boolean find = false;
            int i = 1;
            int limit;

            for (i = 1; !find;  i++){
                limit = (linksPages * i) - (i - 1);
                if (actualPage < limit){
                    from = limit - (8 - 1);
                    if (totalPages <= limit) {
                        until = totalPages;
                    }
                    else {
                        until = limit;
                    }
                    find = true;
                }
            }
        }

        for (int i = from; i <= until; i++){
            items.add(new PageItem(i, actualPage == i));
        }

    }

    public String getUrl() {
        return url;
    }

    public Page<T> getPageList() {
        return pageList;
    }

    public int getActualPage() {
        return actualPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getElementesPerPage() {
        return elementesPerPage;
    }

    public List<PageItem> getItems() {
        return items;
    }

    public int getLinksPages() {
        return linksPages;
    }


    public boolean isFirst(){
        return this.pageList.isFirst();
    }

    public boolean isLast(){
        return this.pageList.isLast();
    }

    public boolean isHasNext(){
        return pageList.hasNext();
    }

    public boolean isHasPrevious(){
        return this.pageList.hasPrevious();
    }

}
