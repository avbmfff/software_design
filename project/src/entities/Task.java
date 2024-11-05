package entities;

import java.util.Date;
import java.util.List;

public class Task {
    private int id;
    private String title;
    private String status;
    private int author;
    private List<Integer> workersIds;
    private Date dedicatedTime;
    private String priority;
    private String description;
    private List<Integer> changelogsIds;
}
