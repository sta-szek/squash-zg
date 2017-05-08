package pl.pojo.squash.zg

import com.fasterxml.jackson.annotation.JsonAnyGetter
import org.zalando.problem.Problem
import java.net.URI
import javax.ws.rs.core.Response

class DefaultProblem(private val type: URI,
                     private val title: String?,
                     private val status: Response.StatusType?,
                     private val parameters: MutableMap<String, Any>,
                     private val instance: URI?,
                     private val detail: String?) : Problem {
    override fun getType(): URI = type
    override fun getTitle(): String? = title
    override fun getStatus(): Response.StatusType? = status
    @JsonAnyGetter
    override fun getParameters(): MutableMap<String, Any> = parameters
    override fun getInstance(): URI? = instance
    override fun getDetail(): String? = detail
}