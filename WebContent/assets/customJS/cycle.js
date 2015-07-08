jQuery(function(e) {
	e.Body = e("body");
	e.Window = e(window);
	e.Scroll = e.browser.mozilla || e.browser.msie ? e("html") : e.Body;
	e.MobileWebkit = e.Body.hasClass("webkit-mobile")
			|| navigator.userAgent.match(/iPhone/i)
			|| navigator.userAgent.match(/iPod/i)
			|| navigator.userAgent.match(/iPad/i);
	e.MobileDevice = navigator.userAgent.match(/iPhone/i)
			|| navigator.userAgent.match(/iPod/i)
			|| navigator.userAgent.match(/Android/i);
	e.Tablet = navigator.userAgent.match(/iPad/i);
	e("[data-script]").Instantiate();
	e.Body;
	if (e.MobileWebkit)
		e.Body.Mobile()
});
(function(e) {
	e.Events = {
		LOAD : "siteLoad",
		ARTICLE_SCROLL : "articleScroll",
		ARTICLE_ENTER : "articleEnter",
		ARTICLE_EXIT : "articleExit",
		ARTICLE_NEXT : "articleNext",
		ARTICLE_PREV : "articlePrev"
	}
})(jQuery);
(function(e) {
	e.fn.Instantiate = function(t) {
		var n = {};
		if (t)
			e.extend(n, t);
		this.each(function() {
			var t = e(this), n = t.attr("data-script");
			if (t[n])
				t[n]()
		})
	}
})(jQuery);
(function(e) {
	e.fn.Cover = function(t) {
		var n = {
			threshold : 0,
			offset_scroll : 0,
			offset_intertia : 0
		};
		if (t)
			e.extend(n, t);
		this.each(function() {
			var t = e(this);
			if (!e.MobileDevice)
				t.css({
					height : e.Window.height()
				})
		});
		return this
	};
	e.fn.Article = function() {
		function f(t) {
			t.preventDefault();
			var r = p();
			if (r.length > 1) {
				a++;
				if (a < r.length) {
					r.each(function(t) {
						if (t == a) {
							e.Scroll.stop().animate({
								scrollTop : n[u].min + e(this).height() * t
							}, 600, "easeOutQuart")
						}
					})
				} else {
					a = 0;
					c(t)
				}
			} else {
				a = 0;
				c(t)
			}
		}
		function l(t) {
			t.preventDefault();
			var r = p();
			if (r.length > 1) {
				a--;
				if (a >= 0) {
					r.each(function(t) {
						if (t == a)
							e.Scroll.stop().animate({
								scrollTop : n[u].min + e(this).height() * t
							}, 600, "easeOutQuart")
					})
				} else {
					a = 0;
					h(t)
				}
			} else {
				a = 0;
				h(t)
			}
		}
		function c(e) {
			u++;
			if (u >= s)
				u = s - 1;
			e.preventDefault()
		}
		function h(e) {
			u--;
			if (u < 0)
				u = 0;
			e.preventDefault()
		}
		function p() {
			var n = {};
			t.each(function(t) {
				if (t == u)
					n = e(this).find("figure").children()
			});
			return n
		}
		function d(e) {
		}
		function v(e, t, n, r) {
		}
		function m() {
			r = 0;
			t.triggerHandler(e.Events.RESIZE);
			y()
		}
		function g() {
			var n = window.orientation;
			t.triggerHandler(e.Events.ORIENT, n)
		}
		function y() {
			if (!e.MobileDevice)
				e.Body.css({
					height : r
				})
		}
		var t = this, n = new Array, r = 0, i = e.Body.attr("data-issue"), s = this.length, o = 0, u = 0, a = 0;
		e.Body.bind(e.Events.ARTICLE_NEXT, c).bind(e.Events.ARTICLE_PREV, h)
				.bind(e.Events.ARTICLE_ENTER, v).bind(e.Events.KEY_RIGHT, f)
				.bind(e.Events.KEY_DOWN, f).bind(e.Events.KEY_LEFT, l).bind(
						e.Events.KEY_UP, l);
		e.Window.bind("resize", m);
		window.onorientationchange = g;
		this
				.each(function(s) {
					function C() {
						x = l ? l.height() : 0;
						T = a ? a.height() : 0;
						n[s] = {
							min : r,
							max : r + O()
						};
						r += O();
						if (b && !e.MobileDevice && !e.Tablet) {
							c.css({
								top : A() < e.Window.height() ? A() / 2
										: e.Window.height() / 2
							});
							p.css({
								top : A() < e.Window.height() ? A() / 2
										: e.Window.height() / 2
							})
						}
						if (!e.MobileDevice) {
							a.css({
								height : T
							});
							u.css({
								height : A(),
								overflow : "hidden",
								zIndex : 1e3 - E
							})
						}
					}
					function k(t) {
						var r = e.Window.scrollTop(), i = n[E], s = L(r);
						switch (s) {
						case "page":
							u.css({
								marginTop : -(r - (i.min + T)),
								display : "block"
							});
							a.css({
								marginTop : -(r - i.min)
							});
							u
									.triggerHandler(e.Events.ARTICLE_SCROLL, r
											- i.min);
							break;
						case "inview":
							a.css({
								marginTop : -(r - i.min)
							});
							if (v != s)
								u.css({
									marginTop : 0,
									display : "block"
								});
							u
									.triggerHandler(e.Events.ARTICLE_SCROLL, r
											- i.min);
							break;
						case "above":
							if (v != s) {
								a.css({
									marginTop : -T - 25
								});
								u.css({
									marginTop : -O() - 25,
									display : "none"
								});
								u.triggerHandler(e.Events.ARTICLE_SCROLL, r
										- i.min)
							}
							break;
						case "outofview":
							if (v != s) {
								u.triggerHandler(e.Events.ARTICLE_SCROLL, 0);
								a.css({
									marginTop : 0
								});
								u.css({
									marginTop : 0,
									display : "none"
								})
							}
							break;
						default:
							if (v != s) {
								u.triggerHandler(e.Events.ARTICLE_SCROLL, 0);
								a.css({
									marginTop : 0
								});
								u.css({
									marginTop : 0,
									display : "block"
								})
							}
							break
						}
						v = s
					}
					function L(t) {
						var r = n[E];
						if (t > r.min && t <= r.max) {
							if (!u.hasClass("_inview")) {
								u.addClass("_inview");
								e.Body.triggerHandler(e.Events.ARTICLE_ENTER, [
										i, g, s ])
							}
							if (t >= r.min + T)
								return "page";
							else
								return "inview"
						} else {
							if (u.hasClass("_inview"))
								u.removeClass("_inview")
						}
						if (t <= r.min - e.Window.height()) {
							u
									.triggerHandler(e.Events.ARTICLE_EXIT, [ i,
											g, s ]);
							return "outofview"
						}
						if (t <= r.min)
							return "below";
						if (t > r.max) {
							u
									.triggerHandler(e.Events.ARTICLE_EXIT, [ i,
											g, s ]);
							return "above"
						}
					}
					function A() {
						var t = e.Window.height(), n = e.Window.width();
						if (S) {
							return M(S, n, t)
						}
						if (e.MobileWebkit)
							return T + 250 > t ? T + 250 : t;
						return x + 50 < t ? t : x + 50
					}
					function O() {
						var t = x + 50 < e.Window.height() ? e.Window.height()
								: x + 50, n = e.Window.width();
						if (S) {
							return M(S, n)
						}
						return T + t
					}
					function M(e, t) {
						if (N * t <= 1200) {
							return 1200
						}
						if (N * t > 1200) {
							return N * t
						}
						return parseInt(e)
					}
					var u = e(this), a = u.find("figure"), f = a.children(), l = u
							.find(".column"), c = u.find("header"), h = u
							.find(".numeral"), p = u.find(".extras"), d = u
							.find("[data-video]"), v = "", m = 0, g = u
							.attr("data-google"), y = u.hasClass("title-page"), b = u
							.hasClass("chapter"), w = u.hasClass("fullscreen"), E = s, S = u
							.attr("data-height"), x = l ? l.height() : 0, T = a ? a
							.height()
							: 0, N = 1200 / 1440;
					t.bind(e.Events.RESIZE, C).bind(e.Events.ORIENT, C);
					if (d.length > 0)
						d.Video({
							scope : u
						});
					C();
					if (!e.MobileWebkit)
						e.Window.bind("scroll", k);
					if (!y) {
						o++;
						h.html("No. " + o)
					}
				});
		y();
		return this
	};
	e.fn.Articles = function(t) {
		var n = {};
		if (t)
			e.extend(n, t);
		this.each(function() {
			var t = e(this), n = t.find("article");
			n.Article()
		});
		return this
	};
	e.fn.Mobile = function(t) {
		var n = {};
		if (t)
			e.extend(n, t);
		this.each(function() {
			var t = e(this), n = e("article"), r = e("figure");
			e.Body.triggerHandler(e.Events.MOBILE);
			t.addClass("_mobile");
			t.css({
				height : "auto"
			});
			r.css({
				height : "auto",
				minHeight : "0"
			})
		});
		return this
	};
	e.fn.TOC = function(t) {
		var n = {};
		if (t)
			e.extend(n, t);
		this.each(function() {
			function s() {
				if (!r)
					o();
				else
					u()
			}
			function o() {
				r = true;
				i = e.Window.scrollTop();
				e.Body.triggerHandler(e.Events.ABOUT);
				t.css({
					display : "block"
				})
			}
			function u() {
				r = false;
				t.css({
					display : "none"
				})
			}
			var t = e(this), n = e("[data-about]"), r = false, i = 0;
			e.Body.bind(e.Events.KEY_ESC, s).bind(e.Events.MODAL, u);
			e.Window.bind("scroll", function() {
				if (r)
					if (Math.abs(e.Window.scrollTop() - i) > 125)
						u()
			})
		});
		return this
	};
	e.fn.Modal = function(t) {
		var n = {};
		if (t)
			e.extend(n, t);
		this
				.each(function() {
					function s(r) {
						i = e.Window.scrollTop();
						t.removeClass().addClass(r.attr("data-modal-id"))
								.addClass("_active");
						if (e.MobileWebkit)
							t.css({
								position : "absolute",
								top : e.Window.scrollTop() + e.Window.height()
										/ 2,
								left : e.Window.width() / 2
							});
						if (r.attr("data-camera")) {
							if (r.attr("data-film"))
								n.html("<ul><li><h1>Camera</h1><p>"
										+ r.attr("data-camera")
										+ "</p></li><li><h1>Film</h1><p>"
										+ r.attr("data-film")
										+ "</p></li></ul>");
							else
								n.html("<ul><li><h1>Camera</h1><p>"
										+ r.attr("data-camera")
										+ "</p></li><li><h1>Lens</h1><p>"
										+ r.attr("data-lens")
										+ "</p></li></ul>")
						} else {
							n.html("<ul><li><h1>Location</h1><p>"
									+ r.attr("data-location")
									+ "</p></li><li><h1>Year</h1><p>"
									+ r.attr("data-year") + "</p></li></ul>")
						}
					}
					function o() {
						t.removeClass("_active")
					}
					var t = e(this), n = e(".modal-details"), r = e("a[data-camera],a[data-location]"), i = 0;
					e.Body.bind(e.Events.ABOUT, function() {
						o()
					});
					e.Window.bind("scroll", function(n) {
						if (t.hasClass("_active")
								&& Math.abs(e.Window.scrollTop() - i) > 50)
							o();
						n.preventDefault()
					});
					r.bind("click", function(e) {
						e.preventDefault()
					}).bind("mouseenter", function(t) {
						e.Body.triggerHandler(e.Events.MODAL);
						s(e(this));
						t.preventDefault()
					}).bind("mouseleave", function(e) {
						o();
						e.preventDefault()
					})
				});
		return this
	}
})(jQuery)