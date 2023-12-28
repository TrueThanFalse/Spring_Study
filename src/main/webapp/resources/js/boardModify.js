console.log("boardModify.js Join Success")

document.addEventListener('click', (e)=>{
    console.log(e.target);
    if(e.target.classList.contains('file-x')){
        const uuid = e.target.dataset.uuid;
        const li = e.target.closest('li');
        console.log('uuid >>>>> ' + uuid);

        removeFileFromServer(uuid, bnoVal).then(result=>{
            if(result == "1"){
                alert('파일 삭제 성공');
                li.remove(); // 화면에서 li 삭제
                location.replace(); // 새로고침
            }else{
                alert('파일 삭제 실패');
            }
        })
    }
})

async function removeFileFromServer(uuid, bnoVal){
    try {
        const url = "/board/removeFile";
        const config = {
            method:"delete",
            headers:{
                'Content-Type':'application/json'
            },
            body:JSON.stringify({uuid:uuid, bno:bnoVal})
        };

        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}

// 선생님이 해주신 것
async function removeFileToServer(uuid){
    try {
        const url = "/board/file/"+uuid;
        const config = {
            method:'delete'
        }
        const resp = await fetch(url,config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}