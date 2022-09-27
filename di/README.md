# DI

[📖 DI 컨테이너 구현하기](https://techcourse.woowahan.com/s/cCM7rQR9/ls/hRNYZg9Y)

---

### 3단계
- [X] 생성자 파라미터로 `Set<Class<?>>`를 받아 해당 클래스의 인스턴스를 생성한다.
- [X] 생성한 인스턴스의 필드 타입에 맞는 객체를 대입한다.
- [X] 특정 Class 타입의 bean을 찾아 반환한다.

### 4단계
- [ ] 생성자 파라미터로 root package의 이름을 받아 해당 패키지 내 빈으로 등록할 class를 scan한다.
- [ ] scan한 class의 인스턴스를 생성한다.
- [ ] 생성한 인스턴스의 필드 중 객체를 주입해야할 필드를 scan하여 객체를 대입힌다.
