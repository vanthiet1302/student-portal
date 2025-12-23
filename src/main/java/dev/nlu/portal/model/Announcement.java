package dev.nlu.portal.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
    public class Announcement {
        private Long id;
        private String title;
        private String content;
        private Long postedBy;           // user_id
        private LocalDateTime postedAt;
        private AnnouncementScope scope;
        private Long targetId;           // class_id hoặc department_id tùy scope
        private boolean pinned = false;
    }

    enum AnnouncementScope {
        SCHOOL, DEPARTMENT, CLASS
    }