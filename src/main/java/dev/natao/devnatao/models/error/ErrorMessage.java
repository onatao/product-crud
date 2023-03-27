package dev.natao.devnatao.models.error;

public class ErrorMessage {

    private String errorTitle;
    private String errorMessage;
    private Integer errorStatus;
    
    public ErrorMessage(String errorTitle, String errorMessage, Integer errorStatus) {
        this.errorTitle = errorTitle;
        this.errorMessage = errorMessage;
        this.errorStatus = errorStatus;
    }

    public String getErrorTitle() {
        return errorTitle;
    }

    public void setErrorTitle(String errorTitle) {
        this.errorTitle = errorTitle;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Integer getErrorStatus() {
        return errorStatus;
    }

    public void setErrorStatus(Integer errorStatus) {
        this.errorStatus = errorStatus;
    }    
}
