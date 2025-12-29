package dev.nlu.portal.model;

public enum Status {
    STUDYING, //Đang trong quá trình học tập
    SUSPENDED,// Đang bị đình chỉ học tập (do kỷ luật hoặc nợ học phí).
    DEFERRED,// Đang bảo lưu kết quả (nghỉ học tạm thời có thời hạn).
    DROPPED_OUT,// Đã thôi học (tự bỏ hoặc bị đuổi học).
    GRADUATED // Đã hoàn thành chương trình và tốt nghiệp.
}
