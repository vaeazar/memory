
document.addEventListener("DOMContentLoaded", function () {
  let loginBtn = document.querySelector('#loginBtn');

  loginBtn.addEventListener('click',function() {
    login(function (result) {
      let tempJson = JSON.parse(result);
      if (tempJson.resultFlag == 'wrongMember') {
        alert('아이디와 비밀번호를 확인해주세요!!!');
      } else if (tempJson.resultFlag == 'complete') {
        alert('로그인 성공!!!');
      } else if (tempJson.resultFlag == 'fail') {
        alert('로그인 실패!!!');
      }
    },'application/json');
  })
});

function login(callBack, contentType) {
  let options = checkMemberInfo();
  if (checkIsEmpty(options)) {
    alert('아이디와 비밀번호를 입력해주세요!!!');
    return;
  }
  $.ajax({
    url: '/auth/authenticate',
    data: options,
    type: 'post',
    contentType: contentType != null ? contentType
        : 'application/x-www-form-urlencoded; charset=UTF-8',
    success: function (res) {
      callBack(res);
    },
    error: function (err) {
      console.log('error');
      callBack(err);
    }
  });
}

function checkMemberInfo() {
  let memberID = document.querySelector('#memberID').value;
  let memberPW = document.querySelector('#memberPW').value;

  return {
    memberID: memberID,
    memberPW: memberPW,
  };
}