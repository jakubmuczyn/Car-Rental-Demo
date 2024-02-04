package pl.sda.carrental.util;

import org.springframework.data.auditing.AuditingHandler;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import pl.sda.carrental.service.CustomUserDetailsService;

@ControllerAdvice
public class GlobalControllerAdvice {
    private final CustomUserDetailsService userDetailsService;

    public GlobalControllerAdvice(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @ModelAttribute
    //To jest dodawane do KAŻDEGO requesta, więc nie musimy się martwić o odświeżanie po zalogowaniu. Yay!
    public void addAttributes(Model model) {
        userDetailsService.getAuthenticatedUser().ifPresent(
                (user) -> {
                    model.addAttribute("currentUser", user);
                }
        );
    }
}
