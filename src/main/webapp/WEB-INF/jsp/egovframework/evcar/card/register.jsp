<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>카드등록</title>
</head>
<body>

<fieldset class="sign">
    <h4>신용카드</h4>
    <dl>
        <dt>카드번호</dt>
        <dd><input id="user-name" name="user-name" type="text" placeholder="****-****-****-****"></dd>
        <dt><label for="tmoney-date">발급년월</label></dt>
        <dd>
            <p class="select">
                <select id="tmoney-date">
                    <option>yyyy</option>
                </select>
                <i class="fi icon-back"></i>
            </p>
            <p class="select">
                <select>
                    <option>mm</option>
                </select>
                <i class="fi icon-back"></i>
            </p>
        </dd>
    </dl>
</fieldset>

</body>
</html>
