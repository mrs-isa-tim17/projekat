package com.project.mrsisa.dto.simple_user;

public class PaginationDTO {

    protected int fromElement;
    protected int numberToDisplay;

    public int getFromElement() {
        return fromElement;
    }

    public void setFromElement(int fromElement) {
        this.fromElement = fromElement;
    }

    public int getNumberToDisplay() {
        return numberToDisplay;
    }

    public void setNumberToDisplay(int numberToDisplay) {
        this.numberToDisplay = numberToDisplay;
    }

    public int getUntilElement(int maxNum){
        int untilElement = fromElement + numberToDisplay;
        if (untilElement > maxNum)
            untilElement = maxNum;
        return untilElement;
    }
}
