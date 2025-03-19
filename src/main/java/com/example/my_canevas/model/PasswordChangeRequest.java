package com.example.my_canevas.model;

public class PasswordChangeRequest {
    private String oldPassword;
    private String newPassword;
    private String confirmPassword;

    // Getter pour oldPassword
    public String getOldPassword() {
        return oldPassword;
    }

    // Setter pour oldPassword
    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    // Getter pour newPassword
    public String getNewPassword() {
        return newPassword;
    }

    // Setter pour newPassword
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    // Getter pour confirmPassword
    public String getConfirmPassword() {
        return confirmPassword;
    }

    // Setter pour confirmPassword
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
