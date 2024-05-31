package uz.imed.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApiResponse<T>
{
    T object;
    String message;
    int statusCode=200;

    public ApiResponse(T object, String message)
    {
        this.object = object;
        this.message = message;
    }
}
