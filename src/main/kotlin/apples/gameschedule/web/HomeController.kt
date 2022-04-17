package apples.gameschedule.web

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/")
class HomeController {
    fun index(): String {
        return "index"
    }
}