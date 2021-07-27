<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="css/common.css">
    <script  src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script src="js/memberRegist.js"></script>
    <title>Document</title>
</head>
<body>
    <h2>www2</h2>
    <div>
        <ui>
            <li><input id="memberID" name="memberID"></li>
            <li><input type="password" id="memberPW" name="memberPW" class="memberPW"></li>
            <li><input type="password" id="memberPWConfirm" name="memberPWConfirm" class="memberPW"></li>
            <li id="memberPWText" class="warning-small">비밀번호를 입력해주세요.</li>
            <li><button id="memberRegisterBtn">등록</button></li>
        </ui>
    </div>
</body>
</html>