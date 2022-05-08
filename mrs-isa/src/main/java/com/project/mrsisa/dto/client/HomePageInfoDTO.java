package com.project.mrsisa.dto.client;

public class HomePageInfoDTO {
    private int penalties;
    private boolean deleteRequestMade;

    public HomePageInfoDTO() {
    }

    public int getPenalties() {
        return penalties;
    }

    public void setPenalties(int penalties) {
        this.penalties = penalties;
    }

    public boolean isDeleteRequestMade() {
        return deleteRequestMade;
    }

    public void setDeleteRequestMade(boolean deleteRequestMade) {
        this.deleteRequestMade = deleteRequestMade;
    }
}
