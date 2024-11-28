//삭제 기능
const deleteButton = document.getElementById('delete-btn')

/*
HTML에서 id를 delete-btn으로 설정한 엘리먼트를 찾아 그 엘리먼트에서 클릭 이벤트가 발생하면
fetch() 메서드를 통해 /api/articles/DELETE 요청을 보내는 역할
*/
if(deleteButton){
    deleteButton.addEventListener('click', event => {
        let id = document.getElementById('article-id').value;
        fetch(`/api/articles/${id}`, {
            method: 'DELETE'
        })
        .then(() => {
            alert('삭제가 완료되었습니다.');
            location.replace('/articles');
        })
    })
}

//수정 기능
//id가 modify-btn인 엘리먼트 조회
const modifyButton = document.getElementById('modify-btn');

/*
id가 modify-btn인 엘리먼트를 찾고 그 엘리먼트에서 클릭 이벤트가 발생하면 id가 title, content인 엘리먼트의 값을 가져와
fetch() 메서드를 통해 수정 api로 /api/articles/PUT 요청 보냄
요청을 보낼 때는 headers에 요청 형식을 지정하고, body에 HTML에 입력한 데이터를 JSON 형식으로 바꿔 보낸다.
*/
if(modifyButton){
    //클릭 이벤트가 감지되면 수정 API 요청
    modifyButton.addEventListener('click', event => {
        let params = new URLSearchParams(location.search);
        let id = params.get('id');

        fetch(`/api/articles/${id}`, {
            method: 'PUT',
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                title: document.getElementById('title').value,
                content: document.getElementById('content').value
            })
        })
        .then(() => {
            alert('수정이 완료되었습니다.');
            location.replace(`/articles/${id}`);
        });
    });
}

//등록 기능
//id가 create-btn인 엘리먼트
const createButton = document.getElementById("create-btn");

if(createButton){
    //클릭 이벤트가 감지되면 생성 API 요청
    createButton.addEventListener("click", (event) => {
        fetch("/api/articles", {
            method: 'POST',
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                title: document.getElementById('title').value,
                content: document.getElementById('content').value
            })
        })
        .then(() => {
            alert('등록 완료되었습니다.');
            location.replace("articles");
        });
    });
}