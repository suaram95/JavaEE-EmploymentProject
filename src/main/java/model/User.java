package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    private int id;
    private String name;
    private String surname;
    private int age;
    private Gender gender;
    private String phoneNumber;
    private int workExperience;
    private String username;
    private String password;
    private StaffType staffType;
    private UserType userType;
}
