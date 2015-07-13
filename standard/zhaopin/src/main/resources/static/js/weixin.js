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
});