# AWS-Dynamic-Resource-Manager
2022년 2학기 클라우드 컴퓨팅 학기 프로젝트 - AWS 동적 자원 관리 프로그램

`AWS SDK for Java 2.x`를 이용하여 동적으로 가상머신을 생성, 중지, 삭제 할 수 있는 관리 프로그램


### 기능 요구사항
```
1. list instance      : AWS EC2 콘솔에서 보여주는 인스턴스의 상태와 ID등의 정보를 보여준다.
2. available zones    : 가용 존 리스트를 출력한다.
3. start instance     : 중지 상태에 인스턴스를 실행 상태로 변경한다.
4. available regions  : 가용 지역 리스트를 출력한다.
5. stop instance      : 실행 상태에 인스턴스를 중지 상태로 변경한다.
6. create instance    : 이미지 ID를 이용하여 HTCondor 슬레이브 인스턴스를 생성한다.
7. reboot instance    : 인스턴스를 재시작한다.
8. list images        : aws-htcondor-worker 이미지 리스트를 출력한다.
9. condor_status      : condor_status를 출력한다.
... 기능 추가 예정
```

### 참고 사이트
AWS SDK for Java 2.x Document - https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/get-started.html
