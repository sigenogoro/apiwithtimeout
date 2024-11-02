package com.example.apiwithtimeout.controller

import kotlinx.coroutines.delay
import kotlinx.coroutines.reactor.mono
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers
import java.time.Duration


@RestController
@RequestMapping("/api")
class TimeoutController {
    @GetMapping("/timeout")
    fun timeoutEndpoint(): Mono<ResponseEntity<Void>> {
        // 重い処理をバックグラウンドで実行
        mono {
            performHeavyTask()
            println("aaaa")
        }.subscribe()// バックグラウンドスレッドで実行

        // 即座に204 No Contentを返す
        return Mono.just(ResponseEntity.status(HttpStatus.NO_CONTENT).build())
    }

    private suspend fun performHeavyTask() {
        delay(1000) // 10秒の遅延をシミュレート
        println("バックグラウンド処理が完了しました")
    }
}