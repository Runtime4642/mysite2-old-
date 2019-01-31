
구현한 기능
====================
1. 회원가입
2. 로그인 
3. 회원정보수정
4. 방명록 --> 입력,삭제 시 (비밀번호 필요)
5. 게시판 --> 페이징(자동으로 번호 증가 및 꺽세등장),검색,답글기능
6. 파일업로드 --> 첨부 및 삭제 가능(삭제 시 실제 서버파일도 같이삭제)
7. 댓글기능 -> 댓글도 페이징구현
8. 상태에 따른 UI 변화 --> 글쓰기 및 댓글남기기는 로그인필요, 자기글만 삭제가능 등..


체크리스트
===================================
MVC 적용 (✔)  <br>
게시물 입력 (✔)  <br>
게시물 리스트 (✔) <br>
게시물 삭제 (✔)<br>
게시물 보기 (✔)<br>
게시물 수정 (✔)<br>
상태에 따른 UI 변화(✔)<br> 
     -  각 뷰별 세션에 따른 버튼 배치<br>
     -  적절한 import<br>
     -  인증 유무에 따른 네비게이션및 탑 상태의 변화<br> 
조회수( ✔) --- 쿠기를 사용하여 이미 본 게시물이면 조회수 증가안함<br>
댓글( ✔ ) --- 댓글도 페이징기능 구현<br>
첨부파일 ( ✔ ) --- 첨부파일 삭제시 서버에 실제 파일도 같이 삭제<br>
리스트에서 게시물 검색 기능 ( ✔ )<br>
페이징 처리  ( ✔ ) --- 상황에 맞는 버튼 개수의 동적변화
