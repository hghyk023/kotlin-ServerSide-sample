package com.example.kotlinSpringBootTodoList

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.context.junit4.SpringRunner


@SpringBootTest
@RunWith(SpringRunner::class)
@Sql(statements = arrayOf("DELETE FROM task"))
class JdbcTaskRepositoryTest {

    // lateinitを使用しない場合は、Nullableを意識する必要がある.
    @Autowired
    private lateinit var sut: JdbcTaskRepository

    @Test
    fun 何も作成しなければfindAllは空のリストを返すこと() {
        val got = sut.findAll()
        assertThat(got).isEmpty()
    }

    // 本来はこのような確認方法はよろしくない。
    // createが正常に動く事が前提となってしまっているため。
    @Test
    fun createで作成したタスクがfindByIdで取得できること() {
        val task = sut.create("TEST")
        val got = sut.findById(task.id)
        assertThat(got).isEqualTo(task)
    }
}