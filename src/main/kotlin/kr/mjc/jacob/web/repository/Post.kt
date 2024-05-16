package kr.mjc.jacob.web.repository

import jakarta.persistence.*
import kr.mjc.jacob.web.formatted
import java.time.LocalDateTime

@Entity
class Post {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long = 0
  lateinit var title: String
  lateinit var content: String
  @ManyToOne @JoinColumn(name = "user_id") lateinit var user: User
  lateinit var pubDate: LocalDateTime
  lateinit var lastModified: LocalDateTime

  val pubDateFormatted get() = pubDate.formatted
  val lastModifiedFormatted get() = lastModified.formatted

  override fun toString(): String {
    return "Post(id=$id, title='$title', content='$content', pubDate=${pubDate.formatted}, lastModified=${lastModified.formatted}, user=$user)"
  }
}