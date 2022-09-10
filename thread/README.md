# Thread

[📖 Thread 활용하기](https://techcourse.woowahan.com/s/cCM7rQR9/ls/3DFiyVnX)

### 학습한 내용
- [Thread](https://forky-freeky-forky.notion.site/Thread-ea7a2bdd80224f00a1ce3bf65db20190)
- [Tomcat Connector Configuration](https://forky-freeky-forky.notion.site/Tomcat-Connector-Configuration-c2a30ba934fe4730874693ca99c48080)

---

### 0단계 - 스레드 이해하기
- [X] `ThreadTest`, `SynchronizationTest`, `ThreadPoolsTest`의 모든 test를 통과시킨다.

### 1단계 - 동시성 이슈 확인하기
- [X] `ConcurrencyTest`에서 동시성 이슈를 발생시킨다.
- [X] 해결방법에 대해 생각해본다.

### 2단계 - WAS에 스레드 설정하기
- [X] `application.yml`의 `accept-count`, `max-connections`, `threads.max`가 각각 무슨 의미인지 학습한다.
- [X] `concurrency.stage2.AppTest`를 활용해 thread 설정에 따른 변화를 확인한다.

#### 설정에 따른 변화
- `acceptCount` + `maxConnections` 수가 10 이하일 경우 -> 일부 thread에서 Http Connection time-out 발생
    - 두 값의 합이 10 이상이 되면 connection time-out은 발생하지 않음
- `maxConnections` 수가 적으면 `maxThreads`수를 늘려도 http call count나 test 결과값이 적게 나옴
    - `maxConnections`수를 고정하고 `maxThreads`수를 늘리면 아무리 많이 늘려도 큰 변화가 없음
- `maxConnections` 수를 늘리면 `maxThreads` 수가 적어도 http call count가 많이 찍힘
    - test 결과값은 call count에 비해 적게 나옴
- http call count는 대체로 `maxConnections`를, test 결과값은 대체로 `maxThreads`를 따라감
- 정리하자면 `maxConnections` + `acceptCounts`가 동접자 수, `maxThreads`가 동시에 처리되는 요청 수를 결정한다고 생각할 수 있을 듯 하다
    - `maxConnections`는 요청을 보낼 수 있는 접속자 수 (maxConnections를 넘어가면 connection은 살아있지만, 요청을 보냈을 때는 block됨)
