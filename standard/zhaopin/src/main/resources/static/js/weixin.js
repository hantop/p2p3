
wx.ready(function () {

    document.querySelector('#addCard').onclick = function () {
        $.post("/weixin/cards",{},function(result){
            console.info(result)
            wx.addCard({
                cardList: result,
                success: function (res) {
                    alert('已成功的添加到卡包里');
                }
            });
        });
    };
});
/*

$(function() {



   $("#chooseImage").click(function() {
       wx.chooseImage({
           success: function (res) {
               alert('已选择 ' + res.localIds.length + ' 张图片');
               var length = res.localIds.length;
                for(var i = 0; i < length; i++) {
                    var item = res.localIds[i];
                    var img = "<img src='" + item + "'/>";
                    alert(img);
                    $("#imagelist").append(img)
                }
           }
       });
   });

    // 1 判断当前版本是否支持指定 JS 接口，支持批量判断
    document.querySelector('#checkJsApi').onclick = function () {
        wx.checkJsApi({
            jsApiList: [
                'onMenuShareTimeline'
            ],
            success: function (res) {
//                        alert(JSON.stringify(res));
            }
        });
    };

    $("#addCard").click(function() {
        //wx.addCard({
        //    cardList: [{"cardId":"p5D9Ts3f6ZixivEgJTuTWrIvKqNE","cardExt":{"timestamp":"1437128339","openid":"","code":"","signature":"ebec00de804b3c7683c98ffa36227f15f166f672"}}],
        //    success: function (res) {
        //        alert('已添加卡券：' + JSON.stringify(res.cardList));
        //    }
        //});
        $.post("/weixin/cards",{},function(result){
            console.info(result)
            wx.addCard({
                cardList: result,
                success: function (res) {
                    alert('已成功的添加到卡包里');
                }
            });
        });
    });

});*/
