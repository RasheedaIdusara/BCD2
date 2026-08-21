package lk.rasheeda.web.exceptions.mapper;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lk.rasheeda.web.dto.ErrorResponse;
import lk.rasheeda.web.exceptions.InvalidCredentialException;

import java.util.Map;

@Provider
public class InvalidCredentialExceptionMapper implements ExceptionMapper<InvalidCredentialException> {
    @Override
    public Response toResponse(InvalidCredentialException e) {

        ErrorResponse body = ErrorResponse.of("Unauthoruzed",e.getMessage(),Response.Status.UNAUTHORIZED.getStatusCode());

//        return Response.status(Response.Status.UNAUTHORIZED)
//                .type(MediaType.APPLICATION_JSON)
//                .entity(
//                        Map.of("error", "Unauthorized",
//                                "message", e.getMessage()
//                        )
//                ).build();

        return Response.status(Response.Status.UNAUTHORIZED)
                .type(MediaType.APPLICATION_JSON)
                .entity(body)
                .build();
    }
}
