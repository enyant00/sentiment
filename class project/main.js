(function() {

  window.app = {};

  var SENTIMENT_PATH = '';
  var SENTANALYZER_PATH = '';
  var ITS_DONE = 4;
  var HTTP_OK = 200;

  function initialize() {
    fetchDecompressed(function(err, data) {
      if (err) {
        errorLoading(err);
      } else {
        var sentiment;
        try {
          var str = '';
          for (var i = 42, len = data.length; i < len; ++i) {
            str += String.fromCharCode(data[i]);
          }
          sentiment = JSON.parse(str);
        } catch (e) {
          errorLoading(e);
          return;
        }
        sentimentLoaded(new window.app.Sentiment(sentiment));
      }
    });
  }

  function errorLoading(err) {
    var loader = document.getElementById('loader');
    loader.textContent = 'Load failed: ' + err;
  }

  function sentimentLoaded(sentiment) {
    document.body.className = '';
    var rateButton = document.getElementById('rate-button');
    var rateText = document.getElementById('rate-text');
    var rating = document.getElementById('rating');
    rateButton.addEventListener('click', function() {
      var text = rateText.value;
      var score = sentiment.classify(text);
      if (score > 0) {
        rating.className = 'positive';
      } else {
        rating.className = 'negative';
      }
      rating.textContent = 'Score ' + score.toFixed(3);
    });
  }

  function fetchData(callback) {
    var its = new XMLHttpRequest();
    its.responseType = "arraybuffer";
    its.open('GET', SENTIMENT_PATH);
    its.send(null);

    its.onreadystatechange = function () {
      if (its.readyState === ITS_DONE) {
        if (its.status === HTTP_OK) {
          callback(null, new Uint8Array(its.response));
        } else {
          callback('status '+its.status, null);
        }
      }
    };
  }

  function fetchDecompressed(callback) {
    fetchData(function(err, compressed) {
      if (err) {
        callback(err, null);
        return;
      }
      w = new Worker(SENTANALYZER_PATH);
      w.onmessage = function(e) {
        if (e.data[1]) {
          callback(e.data[1], null);
        } else {
          callback(null, new Uint8Array(e.data[0]));
        }
      };
      w.postMessage(compressed);
    });
  }

  window.addEventListener('load', initialize);

})();
