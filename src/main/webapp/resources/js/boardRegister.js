console.log("boardRegister.js Join Success");

document.getElementById('trigger').addEventListener('click', ()=>{
    document.getElementById('file').click();
})

// 정규표현식을 활용하여 파일의 형식을 제한하는 함수 만들기
// 1. 실행 파일 업로드 금지 (바이러스 예방) : exe, bat, sh, mis, dll, jar
// 2. 파일 사이즈 체크(20MB 사이즈 보다 크면 업로드 금지)
// 3. 이미지 파일만 올리기(jpg, jpeg, gif, png, bmp)

const regExp = new RegExp("\.(exe|bat|sh|mis|dll|jar)$"); // 실행 파일의 패턴 정의
const regExpImg = new RegExp("\.(jpg|jpeg|gif|png|bmp)$") // 이미지 파일의 패턴 정의
const maxSize = 1024*1024*20; // 20MB 용량 설정

// Validation : 규칙 설정
// Validation은 보통 화면에서 처리함
// 대표적으로 회원 가입할 때 Validation을 통과하지 못하면 회원가입 버튼이 활성화되지 않음
// return은 0 혹은 1로 리턴(1:true / 0:false)
function fileValidation(fileName, fileSize){
    fileName = fileName.toLowerCase();
    if(regExp.test(fileName)){
        // 파일명이 실행 파일인지 확인
        return 0;
    }else if(fileSize > maxSize){
        return 0;
    }else if(!regExpImg.test(fileName)){
        return 0;
    }else{
        // 통과 안되는 case를 모두 조건으로 달고 나머지(else)에 통과되는 값을 작성해야 함
        return 1;
    }
}

// 첨부 파일에 따라 등록 가능한지 체크하는 함수
document.addEventListener('change', (e)=>{
    console.log(e.target);

    if(e.target.id === 'file'){
         // 여러개의 파일이 배열로 반환됨
        const fileObject = document.getElementById('file').files;
        console.log(fileObject);

        // disabled이 한번 true가 되면 자동으로 false가 되진 않음
        // 따라서 초기화하는 로직이 필요(초기화)
        document.getElementById('regBtn').disabled = false;

        let div = document.getElementById('fileZone');
        div.innerHTML = ''; // 초기화
        let ul = `<ul>`;
        // fileValidation 함수의 retrun 여부를 체크 => 모든 파일이 통과되어야 가능
        // 곱하기로 isOK를 처리하여 모든 파일이 1이 되어야만 통과되도록 로직을 만들 것임
        let isOK = 1;
        for(let file of fileObject){
            // 콘솔에서 찍어보면 각각의 파일이 배열에 객체로 저장됨, 이 객체에서 name, size, type을 활용하여 유효성 검사를 할 것임
            let ValidationResult = fileValidation(file.name, file.size) // 한 파일에 대한 결과
            isOK *= ValidationResult; // 하나의 파일이라도 통과가 안되면 0이 곱해져서 0이 될 것임
            ul += `<li>`;
            // 조건식 없이 ValidationResult만 있어도 true / false로 인식됨
            ul += `<div>${ValidationResult ? '업로드 가능' : '업로드 불가능'}</div>`;
            ul += `${file.name}`;
            // 클래스에도 삼항연산자로 조건식 삽입 가능
            ul += `<span class="badge text-bg-${ValidationResult ? 'success':'danger'}">${file.size}Byte</span>`;
            ul += `</li>`;
        }
        ul += `</ul>`;
        div.innerHTML = ul;

        if(isOK == 0){
            // 업로드 불가능한 파일이 1개라도 있다면...
            document.getElementById('regBtn').disabled = true; // 버튼 비활성화
        }
    }
})

// fileZone > ul > li(파일 개수 만큼 li가 추가되는 형식)