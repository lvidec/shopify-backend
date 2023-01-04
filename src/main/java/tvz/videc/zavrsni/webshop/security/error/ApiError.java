package tvz.videc.zavrsni.webshop.security.error;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;

@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.CUSTOM, property = "error", visible = true)
@JsonTypeIdResolver(LowerCaseClassNameResolver.class)
public class ApiError {

    private HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private String message;
    private String debugMessage;
    private List<ApiSubError> subErrors;

    private ApiError() {
        this.timestamp = LocalDateTime.now();
    }

    public ApiError(final HttpStatus status) {
        this();
        this.status = status;
    }

    public ApiError(final HttpStatus status, final Throwable ex) {
        this();
        this.status = status;
        this.message = "Unexpected error";
        this.debugMessage = ex.getLocalizedMessage();
    }

    public ApiError(final HttpStatus status, final String message, final Throwable ex) {
        this();
        this.status = status;
        this.message = message;
        this.debugMessage = ex.getLocalizedMessage();
    }

    public HttpStatus getStatus() {
        return this.status;
    }

    public void setStatus(final HttpStatus status) {
        this.status = status;
    }

    public LocalDateTime getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(final LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public String getDebugMessage() {
        return this.debugMessage;
    }

    public void setDebugMessage(final String debugMessage) {
        this.debugMessage = debugMessage;
    }

    public List<ApiSubError> getSubErrors() {
        return this.subErrors;
    }

    public void setSubErrors(final List<ApiSubError> subErrors) {
        this.subErrors = subErrors;
    }

    private void addSubError(final ApiSubError subError) {
        if (this.subErrors == null) {
            this.subErrors = new ArrayList<>();
        }
        this.subErrors.add(subError);
    }

    private void addValidationError(final String object, final String field, final Object rejectedValue, final String message) {
        this.addSubError(new ApiValidationError(object, field, rejectedValue, message));
    }

    private void addValidationError(final String object, final String message) {
        this.addSubError(new ApiValidationError(object, message));
    }

    private void addValidationError(final FieldError fieldError) {
        this.addValidationError(fieldError.getObjectName(), fieldError.getField(), fieldError.getRejectedValue(), fieldError.getDefaultMessage());
    }

    void addValidationErrors(final List<FieldError> fieldErrors) {
        fieldErrors.forEach(this::addValidationError);
    }

    private void addValidationError(final ObjectError objectError) {
        this.addValidationError(objectError.getObjectName(), objectError.getDefaultMessage());
    }

    void addValidationError(final List<ObjectError> globalErrors) {
        globalErrors.forEach(this::addValidationError);
    }

    private void addValidationError(final ConstraintViolation<?> cv) {
        this.addValidationError(cv.getRootBeanClass().getSimpleName(), ((PathImpl) cv.getPropertyPath()).getLeafNode().asString(),
          cv.getInvalidValue(), cv.getMessage());
    }

    void addValidationErrors(final Set<ConstraintViolation<?>> constraintViolations) {
        constraintViolations.forEach(this::addValidationError);
    }

    abstract class ApiSubError {

    }

    class ApiValidationError extends ApiSubError {

        private String object;
        private String field;
        private Object rejectedValue;
        private String message;

        ApiValidationError(final String object, final String message) {
            this.object = object;
            this.message = message;
        }

        public ApiValidationError(final String object, final String field, final Object rejectedValue, final String message) {
            this.object = object;
            this.field = field;
            this.rejectedValue = rejectedValue;
            this.message = message;
        }

        public String getObject() {
            return this.object;
        }

        public void setObject(final String object) {
            this.object = object;
        }

        public String getField() {
            return this.field;
        }

        public void setField(final String field) {
            this.field = field;
        }

        public Object getRejectedValue() {
            return this.rejectedValue;
        }

        public void setRejectedValue(final Object rejectedValue) {
            this.rejectedValue = rejectedValue;
        }

        public String getMessage() {
            return this.message;
        }

        public void setMessage(final String message) {
            this.message = message;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || this.getClass() != o.getClass()) {
                return false;
            }
            final ApiValidationError that = (ApiValidationError) o;
            return Objects.equals(this.object, that.object) && Objects.equals(
              this.field, that.field) && Objects.equals(this.rejectedValue, that.rejectedValue) && Objects.equals(this.message, that.message);
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.object, this.field, this.rejectedValue, this.message);
        }

    }

}