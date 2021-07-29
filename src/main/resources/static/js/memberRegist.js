
document.addEventListener("DOMContentLoaded", function () {
  let memberPWEvent = document.querySelector('#memberPW');
  let memberPWConfirmEvent = document.querySelector('#memberPWConfirm');
  let warningText = document.querySelector('#memberPWText');
  let memberRegisterBtn = document.querySelector('#memberRegisterBtn');

  memberPWEvent.addEventListener('keyup',function() {
    pwConfirm(memberPWEvent, memberPWConfirmEvent, warningText)
  });

  memberPWConfirmEvent.addEventListener('keyup',function() {
    pwConfirm(memberPWEvent, memberPWConfirmEvent, warningText)
  });

  memberPWEvent.addEventListener('blur',function() {
    pwConfirm(memberPWEvent, memberPWConfirmEvent, warningText)
  });

  memberPWConfirmEvent.addEventListener('blur',function() {
    pwConfirm(memberPWEvent, memberPWConfirmEvent, warningText)
  });

  memberRegisterBtn.addEventListener('click',function() {
    memberRegister(function (result) {
      console.log(result);
      let tempJson = JSON.parse(result);
      console.log(tempJson.flag);
      if (tempJson.reason == 'duplicate') {
        alert('id가 중복됩니다!!!');
      }
    });
  })
});

function pwConfirm(memberPWEvent, memberPWConfirmEvent, warningText) {
  let nowPW = memberPWEvent.value;
  let nowPWConfirm = memberPWConfirmEvent.value;
  if (nowPW != nowPWConfirm) {
    warningText.classList.replace('confirm-small','warning-small');
    warningText.textContent = '비밀 번호가 일치하지 않습니다.';
  } else {
    warningText.classList.replace('warning-small','confirm-small');
    warningText.textContent = '비밀 번호가 일치합니다.';
  }
}

function memberRegister(callBack, contentType) {
  let options = checkMemberInfo();
  if (checkIsEmpty(options)) {
    return;
  }
  $.ajax({
    url: 'memberRegister.do',
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
  let memberPWEvent = document.querySelector('#memberPW');
  let memberPW = memberPWEvent.value;
  let memberIDEvent = document.querySelector('#memberID');
  let memberID = memberIDEvent.value.replaceAll(' ','');
  console.log(checkNotPass(memberPW));

  if (checkNotPass(memberPW)) {
    memberPWEvent.focus();
    alert('비밀번호 조건을 확인해주세요.\n8~16자리 영문 숫자 특수문자 포함');
    return;
  }

  if (checkNotID(memberID)) {
    memberIDEvent.focus();
    alert('ID 조건을 확인해주세요.\n1~16자리 영문 숫자 가능');
    return;
  }


  return {
    memberID: memberID,
    memberPW: memberPW,
  };
}

function checkNotPass(str) {
  const regExp = /^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+])(?!.*[^a-zA-z0-9$`~!@$!%*#^?&\\(\\)\-_=+]).{8,16}$/;
  if(regExp.test(str)) {
    return false;
  } else {
    return true;
  }
}

function checkNotID(str) {
  const regExp = /^[A-Za-z0-9+]{1,16}$/;
  if(regExp.test(str)) {
    return false;
  } else {
    return true;
  }
}