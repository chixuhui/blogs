window.onload = function(){
    var c = document.getElementById('canvBrckgroud');
    var w = c.width = window.innerWidth;
    var h = c.height = window.innerHeight;

    var $ = c.getContext('2d');
    var _x = w / 2;
    var _y = h / 2;

    var sc = 150;
    var num = 35;

    var midX = new Array(num);
    var midY = new Array(num);
    var rad = new Array(num);

    var msX = 0.0;
    var msY = 0.0;
    var _msX = 0.0;
    var _msY = 0.0;

    var invX = 0.0;
    var invY = 0.0;
    var _invX = 0.0;
    var _invY = 0.0;

    var spr = 0.95;
    var fric = 0.95;
    var flag = 1;
    var arr = [];
    var rint = 60;

    var draw = function() {
        window.requestAnimationFrame(draw);
        fill();
        for (var j = 0; j < arr.length; j++) {
            arr[j].fade();
            arr[j].anim();
            arr[j]._draw();
        }
    }

    var txt = function() {
        var t0 = "Adheres by blog".split("").join(String.fromCharCode(0x2004));
        var t = " ".split("").join(String.fromCharCode(0x2004));
        $.font = "3em Economica";
        $.fillStyle = 'hsla(220, 95%, 75%, .55)';
        $.fillText(t0, (c.width - $.measureText(t0).width) * 0.5, c.height * 0.45);
        $.fillText(t, (c.width - $.measureText(t).width) * 0.5, c.height * 0.55);
        return t;
    }
    var fill = function() {
        $.globalCompositeOperation = 'source-over';
        var g_ = $.createLinearGradient(c.width + c.width, c.height + c.height * 1.5, c.width + c.width, 1);
        g_.addColorStop(0, ' hsla(220, 95%, 10%, .55)');
        g_.addColorStop(0.5, 'hsla(220, 95%, 30%, .55)');
        g_.addColorStop(1, 'hsla(0, 0%, 5%, 1)');
        $.fillStyle = g_;
        $.fillRect(0, 0, w, h);
        $.globalCompositeOperation = 'lighter';
        txt();
    }
    for (var j = 0; j < 150; j++) {
        arr[j] = new stars();
        arr[j].reset();
    }

    function stars() {
        this.s = {
            tlap: 8000,
            maxx: 5,
            maxy: 2,
            rmax: 5,
            rt: 1,
            dx: 960,
            dy: 540,
            mvx: 4,
            mvy: 4,
            rnd: true,
            twinkle: true
        };
        this.reset = function() {
            this.x = (this.s.rnd ? w * Math.random() : this.s.dx);
            this.y = (this.s.rnd ? h * Math.random() : this.s.dy);
            this.r = ((this.s.rmax - 1) * Math.random()) + .5;
            this.dx = (Math.random() * this.s.maxx) * (Math.random() < .5 ? -1 : 1);
            this.dy = (Math.random() * this.s.maxy) * (Math.random() < .5 ? -1 : 1);
            this.tw = (this.s.tlap / rint) * (this.r / this.s.rmax);
            this.rt = Math.random() * this.tw;
            this.s.rt = Math.random() + 1;
            this.cs = Math.random() * .2 + .4;
            this.s.mvx *= Math.random() * (Math.random() < .5 ? -1 : 1);
            this.s.mvy *= Math.random() * (Math.random() < .5 ? -1 : 1);
        }
        this.fade = function() {
            this.rt += this.s.rt;
        }
        this._draw = function() {
            if (this.s.twinkle && (this.rt <= 0 || this.rt >= this.tw)) this.s.rt = this.s.rt * -1;
            else if (this.rt >= this.tw) this.reset();
            var o = 1 - (this.rt / this.tw);
            $.beginPath();
            $.arc(this.x, this.y, this.r, 0, Math.PI * 2, true);
            $.closePath();
            var rad = this.r * o;
            var g = $.createRadialGradient(this.x, this.y, 0, this.x, this.y, (rad <= 0 ? 1 : rad));
            g.addColorStop(0.0, 'hsla(255,255%,255%,' + o + ')');
            g.addColorStop(this.cs, 'hsla(201, 95%, 25%,' + (o * .6) + ')');
            g.addColorStop(1.0, 'hsla(201, 95%, 45%,0)');
            $.fillStyle = g;
            $.fill();
        }

        this.anim = function() {
            this.x += (this.rt / this.tw) * this.dx;
            this.y += (this.rt / this.tw) * this.dy;
            if (this.x > w || this.x < 0) this.dx *= -1;
            if (this.y > h || this.y < 0) this.dy *= -1;
        }
    }
    var set = function() {
        var radi = Math.PI * 2.0 / num;
        for (i = 0; i < num; i++) {
            midX[i] = Math.cos(radi * i);
            midY[i] = Math.sin(radi * i);
            rad[i] = 0.1;
        }
        draw();
    }
    window.addEventListener('mousemove', function(e) {
        msX = (e.clientX - _x) / sc;
        msY = (e.clientY - _y) / sc;
    }, false);
    window.addEventListener('touchmove', function(e) {
        e.preventDefault();
        msX = (e.touches[0].clientX - _x) / sc;
        msY = (e.touches[0].clientY - _y) / sc;
    }, false);
    window.addEventListener('resize', function() {
        c.width = w = window.innerWidth;
        c.height = h = window.innerHeight;
        draw();
    }, true);
    set();
}