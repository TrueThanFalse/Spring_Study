console.log("boardModify.js Join Success")

document.addEventListener('click', (e)=>{
    if(e.target.classList.contains('file-x')){
        const uuid = e.target.dataset.uuid;
        const li = e.target.closest('li');
        console.log('uuid >>>>> ' + uuid);

        removeFileFromServer(uuid).then(result=>{
            if(result == "1"){
                alert('파일 삭제 성공');
                li.remove();
            }
        })
    }
})

async function removeFileFromServer(uuid){
    try {
        const url = "/board/removeFile";
        const config = {
            method:"delete",
            headers:{
                'Content-Type':'application/json'
            },
            body:JSON.stringify({uuid:uuid})
        };

        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}