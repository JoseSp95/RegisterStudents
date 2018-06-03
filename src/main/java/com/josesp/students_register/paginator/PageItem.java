package com.josesp.students_register.paginator;

public class PageItem {

    private int numberOfPage;
    private boolean actual;

    PageItem(int numberOfPage, boolean actual){
        this.numberOfPage = numberOfPage;
        this.actual = actual;
    }

    public int getNumberOfPage() {
        return numberOfPage;
    }

    public void setNumberOfPage(int numberOfPage) {
        this.numberOfPage = numberOfPage;
    }

    public boolean isActual() {
        return actual;
    }

    public void setActual(boolean actual) {
        this.actual = actual;
    }
}
