console.log("boardComment.js Join Success!!!");
console.log("bno >>>>> "+bnoVal);

document.getElementById("cmtAddBtn").addEventListener('click', ()=>{
    const cmtText = document.getElementById('cmtText').value;
    const cmtWriter = document.getElementById('cmtWriter').innerText;

    if(cmtText == "" || cmtText == null){
        alert("댓글을 입력해주세요!");
        document.getElementById('cmtText').focus();
        return false;
    }else{
        let cmtData = {
            bno:bnoVal,
            writer:cmtWriter,
            content:cmtText
        };
        console.log(cmtData);

        // 호출
        postCommentToServer(cmtData).then(result =>{
            console.log(result);
            if(result === "1"){
                alert("댓글 등록 성공");
                // comment 등록 input 태그 초기화
                cmtText = '';
                // 화면에 뿌리기
                PrintCommentList(bnoVal);
            }

        })
    }
})

// 비동기 통신 구문
// JS에서 주석을 함수 내부에 작성하면
// JSP에서 읽을 때 Error가 발생할 수 있음을 인식하자
async function postCommentToServer(cmtData){
    try {
        // 목적지 경로
        const url = "/comment/post";
        const config = {
            // method 종류 (REST 방식)
            // post : 데이터 삽입
            // get : 데이터 조회 => 생략 가능(default 값)
            // put(patch) : 데이터 수정
            // delete : 데이터 삭제
            method : "post",
            // headers는 반드시 객체로 작성해야 함
            headers : {
                "content-type":"application/json; charset=utf-8"
            },
            // 화면에서 Server로 보낼려면 String으로 보내야 함
            body : JSON.stringify(cmtData)
        };

        // resp = 실질적으로는 response임
        // result = 실질적으로는 body임
        const resp = await fetch(url, config);
        // fetch를 하면 CommentController의 @PostMapping("/post")로 들어가서 post 메서드의 return을 resp에 저장함
        const result = await resp.text(); // body 값을 String으로 추출
        return result;
    } catch (error) {
        console.log(error);
    }
}

// 게시글 하나의 Comment List 불러오기
async function getCommentList(bno){
    try {
        const resp = await fetch("/comment/"+bno);
        // 값을 여러개 전송하고자 한다면 /bno/cno/writer... 이런 방식으로 내보낼 수 있다.
        const result = resp.json(); // bno의 댓글 List 전체
        return result;
    } catch (error) {
        console.log(error);
    }
}

// Comment 화면에 뿌리기
function PrintCommentList(bno){
    getCommentList(bno).then(result =>{
        console.log
        const div = document.getElementById('accordionPanelsStayOpenExample');

        if(result.length > 0){
            // list가 존재할 경우
            div.innerHTML = '';

            // 수정과 삭제를 위해서 class에 cmtModBtn,cmtDelBtn과 data-con를 작성함
            for(let i=0; i<result.length; i++){
                let html = `<div class="accordion-item">`;
                html += `<h2 class="accordion-header">`;
                html += `<button class="accordion-button" type="button" data-bs-toggle="collapse"`;
                html += ` data-bs-target="#panelsStayOpen-collapse${i}"`;
                html += ` aria-expanded="true" aria-controls="panelsStayOpen-collapse${i}">`;
                html += `no.${result[i].cno} / ${result[i].writer} / ${result[i].reg_date}</button></h2>`;
                html += `<div id="panelsStayOpen-collapse${i}" class="accordion-collapse collapse show">`;
                html += `<div class="accordion-body">`;
                html += `<button type="button" data-cno="${result[i].cno}" class="btn htn-sm btn-outline-primary cmtModBtn">수정</button>`;
                html += `<button type="button" data-cno="${result[i].cno}" class="btn htn-sm btn-outline-danger cmtDelBtn">삭제</button>`;
                html += `<input type="text" class="form-control cmtText" value="${result[i].content}">`;
                html += `</div></div></div>`;
                div.innerHTML += html;
            }
        }else{
            div.innerHTML = `<div class="accordion-body">Comment List Empty</div>`;
        }
    })
}

// 비동기 삭제
async function removeCommentFromServer(cnoVal, bnoVal){
    try {
        // comment 삭제시 boardVO의 멤버변수 commentCount의 값을 내리기 위해 bnoVal도 보내기
        // 다시 살펴보니 bnoVal을 보낼 필요가 없음. cvo 내부에 bno가 있으므로...
        // 최상단의 document.getElementById("cmtAddBtn").addEventListener 보면 알 수 있음
        const url = "/comment/"+cnoVal+"/"+bnoVal;
        const config = {
            method:"delete"
        }
        
        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}

// 비동기 수정
async function updateCommentToServer(cmtData){
    try {
        const url = "/comment/modify"
        const config = {
            method:"put",
            headers:{
                'content-type':'application/json; charset=utf-8'
            },
            body:JSON.stringify(cmtData)
        };

        const resp = await fetch(url, config);
        const result = await resp.text(); // body의 타입이 text다.
        return result;
    } catch (error) {
        console.log(error);
    }
}

// 수정,삭제 버튼 처리
document.addEventListener('click', (e)=>{
    console.log(e.target);
    
    if(e.target.classList.contains('cmtDelBtn')){
        // 필요한 cno 변수 값 추출
        // data-변수명 : dataset 키워드로 추출 가능
        let cnoVal = e.target.dataset.cno;
        
        removeCommentFromServer(cnoVal, bnoVal).then(result =>{
            if(result == 1){
                alert("댓글 삭제 성공");
                PrintCommentList(bnoVal);
            }
        })
    }

    if(e.target.classList.contains('cmtModBtn')){
        let cnoVal = e.target.dataset.cno;

        // cmtModBtn와 cmtText는 하나의 div 내부의 공간에 같이 존재해야 함
        // closest : e.target과 가장 가까운 것을 찾는 키워드
        let div = e.target.closest('div');
        let cmtText = div.querySelector('.cmtText').value;
        let cmtData = {
            cno:cnoVal,
            content:cmtText
        };
        console.log(cmtData);

        updateCommentToServer(cmtData).then(result =>{
            if(result === "1"){
                alert("댓글 수정 성공")
                PrintCommentList(bnoVal);
            }
        })
    }
})