package kr.mjc.jacob.web.repository

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import kr.mjc.jacob.web.formatted
import org.mindrot.jbcrypt.BCrypt
import java.io.Serializable
import java.time.LocalDateTime

/**
 * 세션에 넣는 사용자 정보. 세션에 넣는 객체는 Serializable을 구현해야
 * 웹서버를 재시작할 때 세션에 다시 올라간다.
 */
@Entity
class User : Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  var id: Long = 0
  lateinit var username: String
  lateinit var password: String
  lateinit var firstName: String
  lateinit var dateJoined: LocalDateTime
  lateinit var lastLogin: LocalDateTime

  /**
   * @param password 평문 비밀번호
   * @return 평문 비밀번호와 user의 비밀번호가 매치하면 true 아니면 false
   */
  fun matchPassword(password: String): Boolean =
    BCrypt.checkpw(password, this.password)

  val dateJoinedFormatted get() = dateJoined.formatted

  val lastLoginFormatted get() = lastLogin.formatted

  override fun toString(): String =
    "User(id=$id, username='$username', firstName='$firstName', dateJoined=${dateJoined.formatted}, lastLogin=${lastLogin.formatted})"
}