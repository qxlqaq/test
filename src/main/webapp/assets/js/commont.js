function logout()
  {
	  $.ajax({
			url:"http://localhost:8088/baozha/client/deleSession.action",
			async:true,
			success:function(data){
				var result=eval('('+data+')');
				var name=result.msg;
				if(name)
				{
					window.location.reload();
				}
			}
		});
  }
window.onload = function() { 
	$.ajax({
		url:"http://localhost:8088/baozha/client/getUserName.action",
		async:true,
		success:function(data){
			var result=eval('('+data+')');
			var name=result.username;
			var cid=result.cid;
			//var uuid=document.getElementById("123");
			if(name!=undefined)
			{
				$("#huanying1").text("欢迎"+name);
				$("#huanying1").attr('href','#');
				$("#huanying2").text('注销');
				$("#huanying2").attr('href','javascript:logout();');
				$("#log1").text("欢迎"+name);
				$("#log1").attr('href','#');
				$("#log2").text('注销');
				$("#log2").attr('href','javascript:logout();');
			}
		}
	});
}
function order()
{
	  $.ajax({
			url:"http://localhost:8088/baozha/client/getUserName.action",
			async:true,
			success:function(data){
				var result=eval('('+data+')');
				var name=result.username;
				//alert(name);
				if(name!=undefined)
				{	
					window.location.href="order";
				}else {
					window.location.href="log";
				}
			}
		});
}