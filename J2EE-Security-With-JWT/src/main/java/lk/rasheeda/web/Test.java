package lk.rasheeda.web;

import lk.rasheeda.web.util.JwtUtil;

import java.util.Set;

public class Test {

    public static void main(String[] args) {

//        String token = JwtUtil.genrateToken("Rasheeda", Set.of("Admin", "User"));
//        System.out.println(token);

        boolean valid =
                JwtUtil.isValid("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJSYXNoZWVkYSIsInJvbGVzIjpbIkFkbWluIiwiVXNlciJdLCJpYXQiOjE3ODY3MjgwMjksImV4cCI6MTc4NjcyODA1OX0.x_FGkME4mrLxa-Me0sp84eafpGmdLXegnwjOqS93F64");
        System.out.println(valid);

    }

}
