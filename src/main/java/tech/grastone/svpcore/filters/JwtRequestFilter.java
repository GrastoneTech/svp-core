package tech.grastone.svpcore.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import tech.grastone.svpcore.services.SVPUserDetailsService;
import tech.grastone.svpcore.util.JwtUtil;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	@Autowired
	SVPUserDetailsService userDetailsService;

	@Autowired
	private JwtUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		try {
			final String authorizationHeader = request.getHeader("Authorization");

			String username = null;
			String jwt = null;

			try {
				if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
					jwt = authorizationHeader.substring(7);
					username = jwtUtil.extractUsername(jwt);
				}
			} catch (Exception e) {
				System.out.println("Invalid JWT");
			}

			if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				try {
					UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);				

					if (jwtUtil.validateToken(jwt, userDetails)) {
						UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
								userDetails, null, userDetails.getAuthorities());
						usernamePasswordAuthenticationToken
								.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
						SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			filterChain.doFilter(request, response);

		} catch (Exception e) {
			System.out.println("Servlet Not found");
			e.printStackTrace();
		}
	}

}
