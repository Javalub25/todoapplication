package pl.sda.todoapp.model;

public class TodoDto {

    private long id;
    private String task;
    private String createDate;

    public TodoDto() {
    }

    public TodoDto(long id, String task, String createDate) {
        this.id = id;
        this.task = task;
        this.createDate = createDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
