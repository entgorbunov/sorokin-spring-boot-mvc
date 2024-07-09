package sorokin.java.course.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class PetDto {
    @NotBlank(message = "Pet name is required")
    @Size(min = 2, max = 50, message = "Pet name must be between 2 and 50 characters")
    private String name;

    @NotNull(message = "User ID is required")
    @Positive(message = "User ID must be a positive number")
    private Long userId;

    public PetDto() {
    }

    public PetDto(@NotBlank(message = "Pet name is required") @Size(min = 2, max = 50, message = "Pet name must be between 2 and 50 characters") String name, @NotNull(message = "User ID is required") @Positive(message = "User ID must be a positive number") Long userId) {
        this.name = name;
        this.userId = userId;
    }

    public static PetDtoBuilder builder() {
        return new PetDtoBuilder();
    }

    public @NotBlank(message = "Pet name is required") @Size(min = 2, max = 50, message = "Pet name must be between 2 and 50 characters") String getName() {
        return this.name;
    }

    public @NotNull(message = "User ID is required") @Positive(message = "User ID must be a positive number") Long getUserId() {
        return this.userId;
    }

    public void setName(@NotBlank(message = "Pet name is required") @Size(min = 2, max = 50, message = "Pet name must be between 2 and 50 characters") String name) {
        this.name = name;
    }

    public void setUserId(@NotNull(message = "User ID is required") @Positive(message = "User ID must be a positive number") Long userId) {
        this.userId = userId;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof PetDto)) return false;
        final PetDto other = (PetDto) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$userId = this.getUserId();
        final Object other$userId = other.getUserId();
        if (this$userId == null ? other$userId != null : !this$userId.equals(other$userId)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof PetDto;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $userId = this.getUserId();
        result = result * PRIME + ($userId == null ? 43 : $userId.hashCode());
        return result;
    }

    public static class PetDtoBuilder {
        private @NotBlank(message = "Pet name is required")
        @Size(min = 2, max = 50, message = "Pet name must be between 2 and 50 characters") String name;
        private @NotNull(message = "User ID is required")
        @Positive(message = "User ID must be a positive number") Long userId;

        PetDtoBuilder() {
        }

        public PetDtoBuilder name(@NotBlank(message = "Pet name is required") @Size(min = 2, max = 50, message = "Pet name must be between 2 and 50 characters") String name) {
            this.name = name;
            return this;
        }

        public PetDtoBuilder userId(@NotNull(message = "User ID is required") @Positive(message = "User ID must be a positive number") Long userId) {
            this.userId = userId;
            return this;
        }

        public PetDto build() {
            return new PetDto(this.name, this.userId);
        }

        public String toString() {
            return "PetDto.PetDtoBuilder(name=" + this.name + ", userId=" + this.userId + ")";
        }
    }
}

