package sorokin.java.course.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class Pet {
    private Long id;
    @NotBlank(message = "Pet name is required")
    @Size(min = 2, max = 50, message = "Pet name must be between 2 and 50 characters")
    private String name;

    @NotNull(message = "User ID is required")
    @Positive(message = "User ID must be a positive number")
    private Long userId;


    public Pet() {
    }

    public Pet(Long id, @NotBlank(message = "Pet name is required") @Size(min = 2, max = 50, message = "Pet name must be between 2 and 50 characters") String name, @NotNull(message = "User ID is required") @Positive(message = "User ID must be a positive number") Long userId) {
        this.id = id;
        this.name = name;
        this.userId = userId;
    }

    public static PetBuilder builder() {
        return new PetBuilder();
    }

    public Long getId() {
        return this.id;
    }

    public @NotBlank(message = "Pet name is required") @Size(min = 2, max = 50, message = "Pet name must be between 2 and 50 characters") String getName() {
        return this.name;
    }

    public @NotNull(message = "User ID is required") @Positive(message = "User ID must be a positive number") Long getUserId() {
        return this.userId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(@NotBlank(message = "Pet name is required") @Size(min = 2, max = 50, message = "Pet name must be between 2 and 50 characters") String name) {
        this.name = name;
    }

    public void setUserId(@NotNull(message = "User ID is required") @Positive(message = "User ID must be a positive number") Long userId) {
        this.userId = userId;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Pet)) return false;
        final Pet other = (Pet) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$userId = this.getUserId();
        final Object other$userId = other.getUserId();
        if (this$userId == null ? other$userId != null : !this$userId.equals(other$userId)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Pet;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $userId = this.getUserId();
        result = result * PRIME + ($userId == null ? 43 : $userId.hashCode());
        return result;
    }

    public static class PetBuilder {
        private Long id;
        private @NotBlank(message = "Pet name is required")
        @Size(min = 2, max = 50, message = "Pet name must be between 2 and 50 characters") String name;
        private @NotNull(message = "User ID is required")
        @Positive(message = "User ID must be a positive number") Long userId;

        PetBuilder() {
        }

        public PetBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public PetBuilder name(@NotBlank(message = "Pet name is required") @Size(min = 2, max = 50, message = "Pet name must be between 2 and 50 characters") String name) {
            this.name = name;
            return this;
        }

        public PetBuilder userId(@NotNull(message = "User ID is required") @Positive(message = "User ID must be a positive number") Long userId) {
            this.userId = userId;
            return this;
        }

        public Pet build() {
            return new Pet(this.id, this.name, this.userId);
        }

        public String toString() {
            return "Pet.PetBuilder(id=" + this.id + ", name=" + this.name + ", userId=" + this.userId + ")";
        }
    }
}
