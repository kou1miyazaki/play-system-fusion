//ページ読み込みできたら・・・
$(document).ready(function(){

  //指定したセレクタのモーダルを表示させる
  $('#auto_modal').modal('show');


  $('#auto_modal').on('hidden.bs.modal', function (e) {
    //非表示を解除
    $('#hide_modal').removeClass('hidden');
  });


  $('body').on('activate.bs.scrollspy', function () {
    
    //ScrollSpyがターゲットに反応するたびに以下を発動
    //alert('発動！');

  });

  $('#carousel_sample2').on('slid.bs.carousel', function () {

    //スライドごとにポーズをかける
    $('#carousel_sample2').carousel('pause');

  });

  //クリックしたらイベント発動
  $('#collapse_icon').click(function () {

    //指定したidを閉じたり開いたり
    $('#demo2').collapse('toggle');
  });


  //Tooltip
  $('#tooltip-sample1').tooltip();
  $('#tooltip-sample2').tooltip();
  $('#tooltip-sample3').tooltip();
  $('#tooltip-sample4').tooltip();

  $('#tooltip-sample5').tooltip({
    animation : false,
    placement : 'right',
    title     : 'Tooltip5 title',
    trigger   : 'click'
  });

});
