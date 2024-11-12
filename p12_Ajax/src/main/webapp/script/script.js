$(function(){
	
	$("button#idChk").click(()=>{
		
		let chkId = $("#memId").val().trim();
		if (chkId == "") {
			alert("아이디를 입력하세요.");
			$("#memId").focus();
		} else {
			// 비동기 통신 시작(= ajax 시작 )
			$.ajax({
				type:"get", 
				url:"/member/joinIdChk.jsp",
				data: {"userId": chkId},
				dataType: "html",
				async: true,
				success: function(data){
					console.log("수신된 데이터(html형식) : " + data);
				},
				error: function(){
					console.log("error!");
				}
			});
		}
		
	});
	
	
});



