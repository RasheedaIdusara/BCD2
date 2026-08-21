package lk.rasheeda.web.resource;

import jakarta.inject.Inject;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;
import jakarta.security.enterprise.identitystore.IdentityStoreHandler;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lk.rasheeda.web.entity.RefreshToken;
import lk.rasheeda.web.exceptions.InvalidCredentialException;
import lk.rasheeda.web.model.LoginRequest;
import lk.rasheeda.web.service.LoginService;
import lk.rasheeda.web.service.RefreshTokenService;
import lk.rasheeda.web.util.JwtUtil;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthResource {

    @Inject
    private IdentityStoreHandler identityStoreHandler;

    @Inject
    private LoginService loginService;

    @Inject
    private RefreshTokenService refreshTokenService;

    public record LoginRequest(String username, String password) {
    } //jdk 16 above

    @Path("/login")
    @POST
    @Transactional(
            rollbackOn = {Exception.class},
            dontRollbackOn = {InvalidCredentialException.class}
    )
    public Response login(LoginRequest loginRequest) {

        if (loginRequest == null || loginRequest.username == null || loginRequest.password == null) {

            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("error", "Missing UserName And Password"))
                    .build();

        }

        UsernamePasswordCredential credential =
                new UsernamePasswordCredential(loginRequest.username, loginRequest.password);

        CredentialValidationResult result = identityStoreHandler.validate(credential);

        if (result.getStatus() == CredentialValidationResult.Status.VALID) {

            String token = JwtUtil.genrateToken(
                    result.getCallerPrincipal().getName(),
                    result.getCallerGroups()
            );

            RefreshToken refreshToken
                    = refreshTokenService.create(result.getCallerPrincipal().getName());

            return Response.status(Response.Status.OK).entity(
                    Map.of(
                            "Accesstoken", token,
                            "RefreshToken", refreshToken.getToken(),
                            "username", result.getCallerPrincipal().getName(),
                            "roles", result.getCallerGroups()
                    )
            ).build();

        }else {
            throw new InvalidCredentialException("Invalid Username Or Password");
        }

        //return Response.status(Response.Status.UNAUTHORIZED).build();

    }

    public record RefreshRequest(String refreshToken) {

    }

    @POST
    @Path("/refresh")
    public Response refresh(RefreshRequest refreshRequest) {

        if (refreshRequest != null || refreshRequest.refreshToken() != null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("error", "Missing Refresh Token"))
                    .build();
        }

        java.util.Optional<RefreshToken> tokenOptional
                = refreshTokenService.findValid(refreshRequest.refreshToken());

        if (tokenOptional.isEmpty()) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(Map.of(
                            "error", "Invalid Or Expire Refresh Token"
                    )).build();
        }

        RefreshToken oldToken = tokenOptional.get();
        String username = oldToken.getUsername();

        refreshTokenService.deleteToken(oldToken.getToken());
        RefreshToken refreshToken = refreshTokenService.create(username);

        Set<String> roles = loginService.getRoles(username);

        String token = JwtUtil.genrateToken(username, roles);

        return Response.status(Response.Status.OK).entity(
                Map.of(
                        "Accesstoken", token,
                        "RefreshToken", refreshToken.getToken(),
                        "username", username,
                        "roles", roles
                )
        ).build();

    }

    //optional
    @POST
    @Path("/logout")
    public Response logout(RefreshRequest request) {
        if(request != null || request.refreshToken() != null) {
            refreshTokenService.deleteToken(request.refreshToken());
        }

        return Response.status(Response.Status.OK).entity(
                Map.of(
                        "message", "Successfully Logged Out"
                )
        ).build();
    }

}
