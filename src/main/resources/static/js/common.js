
function checkIsEmpty(value) {
  if (value == undefined || value == null || value == '') {
    return true;
  } else {
    return false;
  }
}


const setCookie = function(name, value, exp, expKind, secure) {
  let date = new Date();
  let cookieString = '';
  if (expKind == 'sec') {
    date.setTime(date.getTime() + exp*1000);
  } else if (expKind == 'min') {
    date.setTime(date.getTime() + exp*60*1000);
  } else if (expKind == 'hour') {
    date.setTime(date.getTime() + exp*60*60*1000);
  } else {
    date.setTime(date.getTime() + exp*24*60*60*1000);
  }
  cookieString = name + '=' + value + ';expires=' + date.toUTCString() + ';path=/;';
  if (secure) {
    cookieString += 'SameSite=strict;';
  }
  document.cookie = cookieString;
}

const getCookie = function(name) {
  let value = document.cookie.match('(^|;) ?' + name + '=([^;]*)(;|$)');
  return value? value[2] : null;
}

const deleteCookie = function(name) {
  document.cookie = name + '=; expires=Thu, 01 Jan 1999 00:00:10 GMT;';
}
