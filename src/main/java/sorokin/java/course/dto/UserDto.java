package sorokin.java.course.dto;

import jakarta.validation.constraints.*;
import sorokin.java.course.entity.Pet;

import java.util.List;

public class UserDto {
    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotNull(message = "Age is required")
    @Min(value = 1, message = "Age should not be less than 1")
    @Max(value = 100, message = "Age should not be greater than 100")
    private Integer age;

    private List<Pet> pets;

    public UserDto() {
    }

    public UserDto(@NotBlank(message = "Name is required") @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters") String name, @NotBlank(message = "Email is required") @Email(message = "Email should be valid") String email, @NotNull(message = "Age is required") @Min(value = 1, message = "Age should not be less than 1") @Max(value = 100, message = "Age should not be greater than 100") Integer age, List<Pet> pets) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.pets = pets;
    }

    public static UserDtoBuilder builder() {
        return new UserDtoBuilder();
    }

    public @NotBlank(message = "Name is required") @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters") String getName() {
        return this.name;
    }

    public @NotBlank(message = "Email is required") @Email(message = "Email should be valid") String getEmail() {
        return this.email;
    }

    public @NotNull(message = "Age is required") @Min(value = 1, message = "Age should not be less than 1") @Max(value = 100, message = "Age should not be greater than 100") Integer getAge() {
        return this.age;
    }

    public List<Pet> getPets() {
        return this.pets;
    }

    public void setName(@NotBlank(message = "Name is required") @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters") String name) {
        this.name = name;
    }

    public void setEmail(@NotBlank(message = "Email is required") @Email(message = "Email should be valid") String email) {
        this.email = email;
    }

    public void setAge(@NotNull(message = "Age is required") @Min(value = 1, message = "Age should not be less than 1") @Max(value = 100, message = "Age should not be greater than 100") Integer age) {
        this.age = age;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof UserDto)) return false;
        final UserDto other = (UserDto) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$email = this.getEmail();
        final Object other$email = other.getEmail();
        if (this$email == null ? other$email != null : !this$email.equals(other$email)) return false;
        final Object this$age = this.getAge();
        final Object other$age = other.getAge();
        if (this$age == null ? other$age != null : !this$age.equals(other$age)) return false;
        final Object this$pets = this.getPets();
        final Object other$pets = other.getPets();
        if (this$pets == null ? other$pets != null : !this$pets.equals(other$pets)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof UserDto;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $email = this.getEmail();
        result = result * PRIME + ($email == null ? 43 : $email.hashCode());
        final Object $age = this.getAge();
        result = result * PRIME + ($age == null ? 43 : $age.hashCode());
        final Object $pets = this.getPets();
        result = result * PRIME + ($pets == null ? 43 : $pets.hashCode());
        return result;
    }

    public static class UserDtoBuilder {
        private @NotBlank(message = "Name is required")
        @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters") String name;
        private @NotBlank(message = "Email is required")
        @Email(message = "Email should be valid") String email;
        private @NotNull(message = "Age is required")
        @Min(value = 1, message = "Age should not be less than 1")
        @Max(value = 100, message = "Age should not be greater than 100") Integer age;
        private List<Pet> pets;

        UserDtoBuilder() {
        }

        public UserDtoBuilder name(@NotBlank(message = "Name is required") @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters") String name) {
            this.name = name;
            return this;
        }

        public UserDtoBuilder email(@NotBlank(message = "Email is required") @Email(message = "Email should be valid") String email) {
            this.email = email;
            return this;
        }

        public UserDtoBuilder age(@NotNull(message = "Age is required") @Min(value = 1, message = "Age should not be less than 1") @Max(value = 100, message = "Age should not be greater than 100") Integer age) {
            this.age = age;
            return this;
        }

        public UserDtoBuilder pets(List<Pet> pets) {
            this.pets = pets;
            return this;
        }

        public UserDto build() {
            return new UserDto(this.name, this.email, this.age, this.pets);
        }

        public String toString() {
            return "UserDto.UserDtoBuilder(name=" + this.name + ", email=" + this.email + ", age=" + this.age + ", pets=" + this.pets + ")";
        }
    }
}
