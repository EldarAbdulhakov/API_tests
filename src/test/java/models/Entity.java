package models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder

public class Entity {

    private Integer id;

    private Addition addition;

    @Builder.Default
    private List<Integer> important_numbers = List.of(42, 87, 15);

    @Builder.Default
    private String title = "Заголовок сущности";

    @Builder.Default
    private Boolean verified = true;

//    public Entity(Addition addition, List<Integer> important_numbers, String title, Boolean verified) {
//        this.addition = addition;
//        this.important_numbers = important_numbers;
//        this.title = title;
//        this.verified = verified;
//    }

//    public Addition getAddition() {
//        return addition;
//    }
//
//    public void setAddition(Addition addition) {
//        this.addition = addition;
//    }
//
//    public List<Integer> getImportant_numbers() {
//        return important_numbers;
//    }
//
//    public void setImportant_numbers(List<Integer> important_numbers) {
//        this.important_numbers = important_numbers;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public Boolean getVerified() {
//        return verified;
//    }
//
//    public void setVerified(Boolean verified) {
//        this.verified = verified;
//    }
}
