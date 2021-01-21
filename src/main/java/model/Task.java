package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Task {

    private int id;
    private String name;
    private String description;
    private Date createdDate;
    private Date deadline;
    private String comment;
    private User user;
}
