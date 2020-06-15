(window.webpackJsonp = window.webpackJsonp || []).push([[5], {
    655: function (t, e, n) {
        "use strict";
        var r = n(317);
        e.__esModule = !0, e.addMethod = function (t, e, n) {
            if (!t || !(0, g.default)(t.prototype)) throw new TypeError("You must provide a yup schema constructor function");
            if ("string" !== typeof e) throw new TypeError("A Method name must be provided");
            if ("function" !== typeof n) throw new TypeError("Method function must be provided");
            t.prototype[e] = n
        }, e.lazy = e.ref = e.boolean = void 0;
        var i = r(n(656));
        e.mixed = i.default;
        var a = r(n(927));
        e.bool = a.default;
        var o = r(n(928));
        e.string = o.default;
        var s = r(n(929));
        e.number = s.default;
        var l = r(n(930));
        e.date = l.default;
        var u = r(n(932));
        e.object = u.default;
        var c = r(n(952));
        e.array = c.default;
        var d = r(n(669)), h = r(n(953)), f = r(n(713));
        e.ValidationError = f.default;
        var p = r(n(761));
        e.reach = p.default;
        var g = r(n(658));
        e.isSchema = g.default;
        var v = r(n(954));
        e.setLocale = v.default;
        var m = a.default;
        e.boolean = m;
        e.ref = function (t, e) {
            return new d.default(t, e)
        };
        e.lazy = function (t) {
            return new h.default(t)
        }
    }, 656: function (t, e, n) {
        "use strict";
        var r = n(317);
        e.__esModule = !0, e.default = b;
        var i = r(n(108)), a = r(n(663)), o = r(n(893)), s = r(n(917)), l = n(657), u = r(n(924)), c = r(n(712)),
            d = r(n(925)), h = r(n(658)), f = r(n(926)), p = r(n(685)), g = r(n(669)), v = n(761), m = function () {
                function t() {
                    this.list = new Set, this.refs = new Map
                }

                var e = t.prototype;
                return e.toArray = function () {
                    return (0, s.default)(this.list).concat((0, s.default)(this.refs.values()))
                }, e.add = function (t) {
                    g.default.isRef(t) ? this.refs.set(t.key, t) : this.list.add(t)
                }, e.delete = function (t) {
                    g.default.isRef(t) ? this.refs.delete(t.key, t) : this.list.delete(t)
                }, e.has = function (t, e) {
                    if (this.list.has(t)) return !0;
                    for (var n, r = this.refs.values(); !(n = r.next()).done;) if (e(n.value) === t) return !0;
                    return !1
                }, t
            }();

        function b(t) {
            var e = this;
            if (void 0 === t && (t = {}), !(this instanceof b)) return new b;
            this._deps = [], this._conditions = [], this._options = {
                abortEarly: !0,
                recursive: !0
            }, this._exclusive = Object.create(null), this._whitelist = new m, this._blacklist = new m, this.tests = [], this.transforms = [], this.withMutation(function () {
                e.typeError(l.mixed.notType)
            }), (0, a.default)(t, "default") && (this._defaultDefault = t.default), this._type = t.type || "mixed"
        }

        for (var y = b.prototype = {
            __isYupSchema__: !0, constructor: b, clone: function () {
                var t = this;
                return this._mutate ? this : (0, o.default)(this, function (e) {
                    if ((0, h.default)(e) && e !== t) return e
                })
            }, label: function (t) {
                var e = this.clone();
                return e._label = t, e
            }, meta: function (t) {
                if (0 === arguments.length) return this._meta;
                var e = this.clone();
                return e._meta = (0, i.default)(e._meta || {}, t), e
            }, withMutation: function (t) {
                var e = this._mutate;
                this._mutate = !0;
                var n = t(this);
                return this._mutate = e, n
            }, concat: function (t) {
                if (!t || t === this) return this;
                if (t._type !== this._type && "mixed" !== this._type) throw new TypeError("You cannot `concat()` schema's of different types: " + this._type + " and " + t._type);
                var e = (0, d.default)(t.clone(), this);
                return (0, a.default)(t, "_default") && (e._default = t._default), e.tests = this.tests, e._exclusive = this._exclusive, e.withMutation(function (e) {
                    t.tests.forEach(function (t) {
                        e.test(t.OPTIONS)
                    })
                }), e
            }, isType: function (t) {
                return !(!this._nullable || null !== t) || (!this._typeCheck || this._typeCheck(t))
            }, resolve: function (t) {
                var e = this;
                if (e._conditions.length) {
                    var n = e._conditions;
                    (e = e.clone())._conditions = [], e = (e = n.reduce(function (e, n) {
                        return n.resolve(e, t)
                    }, e)).resolve(t)
                }
                return e
            }, cast: function (t, e) {
                void 0 === e && (e = {});
                var n = this.resolve((0, i.default)({}, e, {value: t})), r = n._cast(t, e);
                if (void 0 !== t && !1 !== e.assert && !0 !== n.isType(r)) {
                    var a = (0, p.default)(t), o = (0, p.default)(r);
                    throw new TypeError("The value of " + (e.path || "field") + ' could not be cast to a value that satisfies the schema type: "' + n._type + '". \n\nattempted value: ' + a + " \n" + (o !== a ? "result of cast: " + o : ""))
                }
                return r
            }, _cast: function (t) {
                var e = this, n = void 0 === t ? t : this.transforms.reduce(function (n, r) {
                    return r.call(e, n, t)
                }, t);
                return void 0 === n && (0, a.default)(this, "_default") && (n = this.default()), n
            }, _validate: function (t, e) {
                var n = this;
                void 0 === e && (e = {});
                var r = t, a = null != e.originalValue ? e.originalValue : t, o = this._option("strict", e),
                    s = this._option("abortEarly", e), l = e.sync, u = e.path, d = this._label;
                o || (r = this._cast(r, (0, i.default)({assert: !1}, e)));
                var h = {value: r, path: u, schema: this, options: e, label: d, originalValue: a, sync: l}, f = [];
                return this._typeError && f.push(this._typeError(h)), this._whitelistError && f.push(this._whitelistError(h)), this._blacklistError && f.push(this._blacklistError(h)), (0, c.default)({
                    validations: f,
                    endEarly: s,
                    value: r,
                    path: u,
                    sync: l
                }).then(function (t) {
                    return (0, c.default)({
                        path: u,
                        sync: l,
                        value: t,
                        endEarly: s,
                        validations: n.tests.map(function (t) {
                            return t(h)
                        })
                    })
                })
            }, validate: function (t, e) {
                return void 0 === e && (e = {}), this.resolve((0, i.default)({}, e, {value: t}))._validate(t, e)
            }, validateSync: function (t, e) {
                var n, r;
                if (void 0 === e && (e = {}), this.resolve((0, i.default)({}, e, {value: t}))._validate(t, (0, i.default)({}, e, {sync: !0})).then(function (t) {
                    return n = t
                }).catch(function (t) {
                    return r = t
                }), r) throw r;
                return n
            }, isValid: function (t, e) {
                return this.validate(t, e).then(function () {
                    return !0
                }).catch(function (t) {
                    if ("ValidationError" === t.name) return !1;
                    throw t
                })
            }, isValidSync: function (t, e) {
                try {
                    return this.validateSync(t, e), !0
                } catch (n) {
                    if ("ValidationError" === n.name) return !1;
                    throw n
                }
            }, getDefault: function (t) {
                return void 0 === t && (t = {}), this.resolve(t).default()
            }, default: function (t) {
                if (0 === arguments.length) {
                    var e = (0, a.default)(this, "_default") ? this._default : this._defaultDefault;
                    return "function" === typeof e ? e.call(this) : (0, o.default)(e)
                }
                var n = this.clone();
                return n._default = t, n
            }, strict: function (t) {
                void 0 === t && (t = !0);
                var e = this.clone();
                return e._options.strict = t, e
            }, _isPresent: function (t) {
                return null != t
            }, required: function (t) {
                return void 0 === t && (t = l.mixed.required), this.test({
                    message: t,
                    name: "required",
                    exclusive: !0,
                    test: function (t) {
                        return this.schema._isPresent(t)
                    }
                })
            }, notRequired: function () {
                var t = this.clone();
                return t.tests = t.tests.filter(function (t) {
                    return "required" !== t.OPTIONS.name
                }), t
            }, nullable: function (t) {
                void 0 === t && (t = !0);
                var e = this.clone();
                return e._nullable = t, e
            }, transform: function (t) {
                var e = this.clone();
                return e.transforms.push(t), e
            }, test: function () {
                var t;
                if (void 0 === (t = 1 === arguments.length ? "function" === typeof (arguments.length <= 0 ? void 0 : arguments[0]) ? {test: arguments.length <= 0 ? void 0 : arguments[0]} : arguments.length <= 0 ? void 0 : arguments[0] : 2 === arguments.length ? {
                    name: arguments.length <= 0 ? void 0 : arguments[0],
                    test: arguments.length <= 1 ? void 0 : arguments[1]
                } : {
                    name: arguments.length <= 0 ? void 0 : arguments[0],
                    message: arguments.length <= 1 ? void 0 : arguments[1],
                    test: arguments.length <= 2 ? void 0 : arguments[2]
                }).message && (t.message = l.mixed.default), "function" !== typeof t.test) throw new TypeError("`test` is a required parameters");
                var e = this.clone(), n = (0, f.default)(t), r = t.exclusive || t.name && !0 === e._exclusive[t.name];
                if (t.exclusive && !t.name) throw new TypeError("Exclusive tests must provide a unique `name` identifying the test");
                return e._exclusive[t.name] = !!t.exclusive, e.tests = e.tests.filter(function (e) {
                    if (e.OPTIONS.name === t.name) {
                        if (r) return !1;
                        if (e.OPTIONS.test === n.OPTIONS.test) return !1
                    }
                    return !0
                }), e.tests.push(n), e
            }, when: function (t, e) {
                1 === arguments.length && (e = t, t = ".");
                var n = this.clone(), r = [].concat(t).map(function (t) {
                    return new g.default(t)
                });
                return r.forEach(function (t) {
                    t.isSibling && n._deps.push(t.key)
                }), n._conditions.push(new u.default(r, e)), n
            }, typeError: function (t) {
                var e = this.clone();
                return e._typeError = (0, f.default)({
                    message: t, name: "typeError", test: function (t) {
                        return !(void 0 !== t && !this.schema.isType(t)) || this.createError({params: {type: this.schema._type}})
                    }
                }), e
            }, oneOf: function (t, e) {
                void 0 === e && (e = l.mixed.oneOf);
                var n = this.clone();
                return t.forEach(function (t) {
                    n._whitelist.add(t), n._blacklist.delete(t)
                }), n._whitelistError = (0, f.default)({
                    message: e, name: "oneOf", test: function (t) {
                        if (void 0 === t) return !0;
                        var e = this.schema._whitelist;
                        return !!e.has(t, this.resolve) || this.createError({params: {values: e.toArray().join(", ")}})
                    }
                }), n
            }, notOneOf: function (t, e) {
                void 0 === e && (e = l.mixed.notOneOf);
                var n = this.clone();
                return t.forEach(function (t) {
                    n._blacklist.add(t), n._whitelist.delete(t)
                }), n._blacklistError = (0, f.default)({
                    message: e, name: "notOneOf", test: function (t) {
                        var e = this.schema._blacklist;
                        return !e.has(t, this.resolve) || this.createError({params: {values: e.toArray().join(", ")}})
                    }
                }), n
            }, strip: function (t) {
                void 0 === t && (t = !0);
                var e = this.clone();
                return e._strip = t, e
            }, _option: function (t, e) {
                return (0, a.default)(e, t) ? e[t] : this._options[t]
            }, describe: function () {
                var t = this.clone();
                return {
                    type: t._type, meta: t._meta, label: t._label, tests: t.tests.map(function (t) {
                        return {name: t.OPTIONS.name, params: t.OPTIONS.params}
                    }).filter(function (t, e, n) {
                        return n.findIndex(function (e) {
                            return e.name === t.name
                        }) === e
                    })
                }
            }
        }, x = ["validate", "validateSync"], _ = function () {
            var t = x[w];
            y[t + "At"] = function (e, n, r) {
                void 0 === r && (r = {});
                var a = (0, v.getIn)(this, e, n, r.context), o = a.parent, s = a.parentPath;
                return a.schema[t](o && o[s], (0, i.default)({}, r, {parent: o, path: e}))
            }
        }, w = 0; w < x.length; w++) _();
        for (var k = ["equals", "is"], M = 0; M < k.length; M++) {
            y[k[M]] = y.oneOf
        }
        for (var F = ["not", "nope"], S = 0; S < F.length; S++) {
            y[F[S]] = y.notOneOf
        }
        y.optional = y.notRequired, t.exports = e.default
    }, 657: function (t, e, n) {
        "use strict";
        var r = n(317);
        e.__esModule = !0, e.default = e.array = e.object = e.boolean = e.date = e.number = e.string = e.mixed = void 0;
        var i = r(n(685)), a = {
            default: "${path} is invalid",
            required: "${path} is a required field",
            oneOf: "${path} must be one of the following values: ${values}",
            notOneOf: "${path} must not be one of the following values: ${values}",
            notType: function (t) {
                var e = t.path, n = t.type, r = t.value, a = t.originalValue, o = null != a && a !== r,
                    s = e + " must be a `" + n + "` type, but the final value was: `" + (0, i.default)(r, !0) + "`" + (o ? " (cast from the value `" + (0, i.default)(a, !0) + "`)." : ".");
                return null === r && (s += '\n If "null" is intended as an empty value be sure to mark the schema as `.nullable()`'), s
            }
        };
        e.mixed = a;
        var o = {
            length: "${path} must be exactly ${length} characters",
            min: "${path} must be at least ${min} characters",
            max: "${path} must be at most ${max} characters",
            matches: '${path} must match the following: "${regex}"',
            email: "${path} must be a valid email",
            url: "${path} must be a valid URL",
            trim: "${path} must be a trimmed string",
            lowercase: "${path} must be a lowercase string",
            uppercase: "${path} must be a upper case string"
        };
        e.string = o;
        var s = {
            min: "${path} must be greater than or equal to ${min}",
            max: "${path} must be less than or equal to ${max}",
            lessThan: "${path} must be less than ${less}",
            moreThan: "${path} must be greater than ${more}",
            notEqual: "${path} must be not equal to ${notEqual}",
            positive: "${path} must be a positive number",
            negative: "${path} must be a negative number",
            integer: "${path} must be an integer"
        };
        e.number = s;
        var l = {min: "${path} field must be later than ${min}", max: "${path} field must be at earlier than ${max}"};
        e.date = l;
        var u = {};
        e.boolean = u;
        var c = {noUnknown: "${path} field cannot have keys not specified in the object shape"};
        e.object = c;
        var d = {
            min: "${path} field must have at least ${min} items",
            max: "${path} field must have less than or equal to ${max} items"
        };
        e.array = d;
        var h = {mixed: a, string: o, number: s, date: l, object: c, array: d, boolean: u};
        e.default = h
    }, 658: function (t, e, n) {
        "use strict";
        e.__esModule = !0, e.default = void 0;
        e.default = function (t) {
            return t && t.__isYupSchema__
        }, t.exports = e.default
    }, 663: function (t, e, n) {
        var r = n(892), i = n(747);
        t.exports = function (t, e) {
            return null != t && i(t, e, r)
        }
    }, 664: function (t, e, n) {
        "use strict";
        var r = n(317);
        e.__esModule = !0, e.default = function (t, e, n) {
            t.prototype = Object.create(e.prototype, {
                constructor: {
                    value: t,
                    enumerable: !1,
                    writable: !0,
                    configurable: !0
                }
            }), (0, i.default)(t.prototype, n)
        };
        var i = r(n(108));
        t.exports = e.default
    }, 669: function (t, e, n) {
        "use strict";
        var r = n(317);
        e.__esModule = !0, e.default = void 0;
        var i = r(n(108)), a = n(686), o = {context: "$", value: "."}, s = function () {
            function t(t, e) {
                if (void 0 === e && (e = {}), "string" !== typeof t) throw new TypeError("ref must be a string, got: " + t);
                if (this.key = t.trim(), "" === t) throw new TypeError("ref must be a non-empty string");
                this.isContext = this.key[0] === o.context, this.isValue = this.key[0] === o.value, this.isSibling = !this.isContext && !this.isValue;
                var n = this.isContext ? o.context : this.isValue ? o.value : "";
                this.path = this.key.slice(n.length), this.getter = this.path && (0, a.getter)(this.path, !0), this.map = e.map
            }

            var e = t.prototype;
            return e.getValue = function (t) {
                var e = this.isContext ? t.context : this.isValue ? t.value : t.parent;
                return this.getter && (e = this.getter(e || {})), this.map && (e = this.map(e)), e
            }, e.cast = function (t, e) {
                return this.getValue((0, i.default)({}, e, {value: t}))
            }, e.resolve = function () {
                return this
            }, e.describe = function () {
                return {type: "ref", key: this.key}
            }, e.toString = function () {
                return "Ref(" + this.key + ")"
            }, t.isRef = function (t) {
                return t && t.__isYupRef
            }, t
        }();
        e.default = s, s.prototype.__isYupRef = !0, t.exports = e.default
    }, 683: function (t, e, n) {
        var r = n(896);
        t.exports = function (t, e, n) {
            "__proto__" == e && r ? r(t, e, {configurable: !0, enumerable: !0, value: n, writable: !0}) : t[e] = n
        }
    }, 684: function (t, e, n) {
        var r = n(752), i = n(683);
        t.exports = function (t, e, n, a) {
            var o = !n;
            n || (n = {});
            for (var s = -1, l = e.length; ++s < l;) {
                var u = e[s], c = a ? a(n[u], t[u], u, n, t) : void 0;
                void 0 === c && (c = t[u]), o ? i(n, u, c) : r(n, u, c)
            }
            return n
        }
    }, 685: function (t, e, n) {
        "use strict";
        e.__esModule = !0, e.default = function (t, e) {
            var n = l(t, e);
            return null !== n ? n : JSON.stringify(t, function (t, n) {
                var r = l(this[t], e);
                return null !== r ? r : n
            }, 2)
        };
        var r = Object.prototype.toString, i = Error.prototype.toString, a = RegExp.prototype.toString,
            o = "undefined" !== typeof Symbol ? Symbol.prototype.toString : function () {
                return ""
            }, s = /^Symbol\((.*)\)(.*)$/;

        function l(t, e) {
            if (void 0 === e && (e = !1), null == t || !0 === t || !1 === t) return "" + t;
            var n = typeof t;
            if ("number" === n) return function (t) {
                return t != +t ? "NaN" : 0 === t && 1 / t < 0 ? "-0" : "" + t
            }(t);
            if ("string" === n) return e ? '"' + t + '"' : t;
            if ("function" === n) return "[Function " + (t.name || "anonymous") + "]";
            if ("symbol" === n) return o.call(t).replace(s, "Symbol($1)");
            var l = r.call(t).slice(8, -1);
            return "Date" === l ? isNaN(t.getTime()) ? "" + t : t.toISOString(t) : "Error" === l || t instanceof Error ? "[" + i.call(t) + "]" : "RegExp" === l ? a.call(t) : null
        }

        t.exports = e.default
    }, 686: function (t, e, n) {
        "use strict";

        function r(t) {
            this._maxSize = t, this.clear()
        }

        r.prototype.clear = function () {
            this._size = 0, this._values = {}
        }, r.prototype.get = function (t) {
            return this._values[t]
        }, r.prototype.set = function (t, e) {
            return this._size >= this._maxSize && this.clear(), this._values.hasOwnProperty(t) || this._size++, this._values[t] = e
        };
        var i = /[^.^\]^[]+|(?=\[\]|\.\.)/g, a = /^\d+$/, o = /^\d/, s = /[~`!#$%\^&*+=\-\[\]\\';,\/{}|\\":<>\?]/g,
            l = /^\s*(['"]?)(.*?)(\1)\s*$/, u = !1, c = new r(512), d = new r(512), h = new r(512);
        try {
            new Function("")
        } catch (y) {
            u = !0
        }

        function f(t) {
            return c.get(t) || c.set(t, p(t).map(function (t) {
                return t.replace(l, "$2")
            }))
        }

        function p(t) {
            return t.match(i)
        }

        function g(t, e, n) {
            return "string" === typeof e && (n = e, e = !1), n = n || "data", (t = t || "") && "[" !== t.charAt(0) && (t = "." + t), e ? function (t, e) {
                var n, r = e, i = p(t);
                return v(i, function (t, e, i, a, o) {
                    n = a === o.length - 1, r += (t = e || i ? "[" + t + "]" : "." + t) + (n ? ")" : " || {})")
                }), new Array(i.length + 1).join("(") + r
            }(t, n) : n + t
        }

        function v(t, e, n) {
            var r, i, a, o, s = t.length;
            for (i = 0; i < s; i++) (r = t[i]) && (b(r) && (r = '"' + r + '"'), a = !(o = m(r)) && /^\d+$/.test(r), e.call(n, r, o, a, i, t))
        }

        function m(t) {
            return "string" === typeof t && t && -1 !== ["'", '"'].indexOf(t.charAt(0))
        }

        function b(t) {
            return !m(t) && (function (t) {
                return t.match(o) && !t.match(a)
            }(t) || function (t) {
                return s.test(t)
            }(t))
        }

        t.exports = {
            Cache: r, expr: g, split: p, normalizePath: f, setter: u ? function (t) {
                var e = f(t);
                return function (t, n) {
                    return function (t, e, n) {
                        var r = 0, i = t.length;
                        for (; r < i - 1;) e = e[t[r++]];
                        e[t[r]] = n
                    }(e, t, n)
                }
            } : function (t) {
                return d.get(t) || d.set(t, new Function("data, value", g(t, "data") + " = value"))
            }, getter: u ? function (t, e) {
                var n = f(t);
                return function (t) {
                    return function (t, e, n) {
                        var r = 0, i = t.length;
                        for (; r < i;) {
                            if (null == n && e) return;
                            n = n[t[r++]]
                        }
                        return n
                    }(n, e, t)
                }
            } : function (t, e) {
                var n = t + "_" + e;
                return h.get(n) || h.set(n, new Function("data", "return " + g(t, e, "data")))
            }, join: function (t) {
                return t.reduce(function (t, e) {
                    return t + (m(e) || a.test(e) ? "[" + e + "]" : (t ? "." : "") + e)
                }, "")
            }, forEach: function (t, e, n) {
                v(p(t), e, n)
            }
        }
    }, 687: function (t, e, n) {
        "use strict";
        e.__esModule = !0, e.default = void 0;
        e.default = function (t) {
            return null == t
        }, t.exports = e.default
    }, 688: function (t, e) {
        function n() {
        }

        function r(t) {
            var e = t.annotation.elements;
            return Object.keys(e).map(function (t) {
                return e[t]
            })
        }

        function i() {
            return Math.random().toString(36).substr(2, 6)
        }

        function a(t) {
            return null !== t && "undefined" !== typeof t && ("number" === typeof t ? isFinite(t) : !!t)
        }

        function o(t, e, n) {
            t["$" + e] || (t[e] ? (t["$" + e] = t[e].bind(t), t[e] = function () {
                var r = [t["$" + e]].concat(Array.prototype.slice.call(arguments));
                return n.apply(t, r)
            }) : t[e] = function () {
                var e = [void 0].concat(Array.prototype.slice.call(arguments));
                return n.apply(t, e)
            })
        }

        function s(t, e) {
            t.forEach(function (t) {
                (e ? t[e] : t)()
            })
        }

        function l(t) {
            return "on" + t[0].toUpperCase() + t.substring(1)
        }

        function u(t, e) {
            try {
                return new MouseEvent(t, e)
            } catch (i) {
                try {
                    var n = document.createEvent("MouseEvent");
                    return n.initMouseEvent(t, e.canBubble, e.cancelable, e.view, e.detail, e.screenX, e.screenY, e.clientX, e.clientY, e.ctrlKey, e.altKey, e.shiftKey, e.metaKey, e.button, e.relatedTarget), n
                } catch (a) {
                    var r = document.createEvent("Event");
                    return r.initEvent(t, e.canBubble, e.cancelable), r
                }
            }
        }

        t.exports = function (t) {
            var e = t.helpers;
            return {
                initConfig: function (n) {
                    return n = e.configMerge(t.Annotation.defaults, n), e.isArray(n.annotations) && n.annotations.forEach(function (n) {
                        n.label = e.configMerge(t.Annotation.labelDefaults, n.label)
                    }), n
                },
                elements: r,
                callEach: s,
                noop: n,
                objectId: i,
                isValid: a,
                decorate: o,
                adjustScaleRange: function (t) {
                    var e = function (t, e, n, r) {
                        var i = e.filter(function (e) {
                            return !!e._model.ranges[t]
                        }).map(function (e) {
                            return e._model.ranges[t]
                        });
                        return {
                            min: i.map(function (t) {
                                return Number(t.min)
                            }).reduce(function (t, e) {
                                return isFinite(e) && !isNaN(e) && e < t ? e : t
                            }, n), max: i.map(function (t) {
                                return Number(t.max)
                            }).reduce(function (t, e) {
                                return isFinite(e) && !isNaN(e) && e > t ? e : t
                            }, r)
                        }
                    }(t.id, r(t.chart), t.min, t.max);
                    "undefined" === typeof t.options.ticks.min && "undefined" === typeof t.options.ticks.suggestedMin && (t.min = e.min), "undefined" === typeof t.options.ticks.max && "undefined" === typeof t.options.ticks.suggestedMax && (t.max = e.max), t.handleTickRangeOptions && t.handleTickRangeOptions()
                },
                getNearestItems: function (t, n) {
                    var r = Number.POSITIVE_INFINITY;
                    return t.filter(function (t) {
                        return t.inRange(n.x, n.y)
                    }).reduce(function (t, i) {
                        var a = i.getCenterPoint(), o = e.distanceBetweenPoints(n, a);
                        return o < r ? (t = [i], r = o) : o === r && t.push(i), t
                    }, []).sort(function (t, e) {
                        var n = t.getArea(), r = e.getArea();
                        return n > r || n < r ? n - r : t._index - e._index
                    }).slice(0, 1)[0]
                },
                getEventHandlerName: l,
                createMouseEvent: u
            }
        }
    }, 711: function (t, e, n) {
        var r = n(731);
        t.exports = function (t) {
            var e = new t.constructor(t.byteLength);
            return new r(e).set(new r(t)), e
        }
    }, 712: function (t, e, n) {
        "use strict";
        var r = n(317);
        e.__esModule = !0, e.propagateErrors = function (t, e) {
            return t ? null : function (t) {
                return e.push(t), t.value
            }
        }, e.settled = u, e.collectErrors = c, e.default = function (t) {
            var e = t.endEarly, n = (0, i.default)(t, ["endEarly"]);
            return e ? (r = n.validations, a = n.value, o = n.sync, s(o).all(r).catch(function (t) {
                throw"ValidationError" === t.name && (t.value = a), t
            }).then(function () {
                return a
            })) : c(n);
            var r, a, o
        };
        var i = r(n(209)), a = n(759), o = r(n(713)), s = function (t) {
            return t ? a.SynchronousPromise : Promise
        }, l = function (t) {
            return void 0 === t && (t = []), t.inner && t.inner.length ? t.inner : [].concat(t)
        };

        function u(t, e) {
            var n = s(e);
            return n.all(t.map(function (t) {
                return n.resolve(t).then(function (t) {
                    return {fulfilled: !0, value: t}
                }, function (t) {
                    return {fulfilled: !1, value: t}
                })
            }))
        }

        function c(t) {
            var e = t.validations, n = t.value, r = t.path, i = t.sync, a = t.errors, s = t.sort;
            return a = l(a), u(e, i).then(function (t) {
                var e = t.filter(function (t) {
                    return !t.fulfilled
                }).reduce(function (t, e) {
                    var n = e.value;
                    if (!o.default.isError(n)) throw n;
                    return t.concat(n)
                }, []);
                if (s && e.sort(s), (a = e.concat(a)).length) throw new o.default(a, n, r);
                return n
            })
        }
    }, 713: function (t, e, n) {
        "use strict";
        var r = n(317);
        e.__esModule = !0, e.default = o;
        var i = r(n(685)), a = /\$\{\s*(\w+)\s*\}/g;

        function o(t, e, n, r) {
            var i = this;
            this.name = "ValidationError", this.value = e, this.path = n, this.type = r, this.errors = [], this.inner = [], t && [].concat(t).forEach(function (t) {
                i.errors = i.errors.concat(t.errors || t), t.inner && (i.inner = i.inner.concat(t.inner.length ? t.inner : t))
            }), this.message = this.errors.length > 1 ? this.errors.length + " errors occurred" : this.errors[0], Error.captureStackTrace && Error.captureStackTrace(this, o)
        }

        o.prototype = Object.create(Error.prototype), o.prototype.constructor = o, o.isError = function (t) {
            return t && "ValidationError" === t.name
        }, o.formatError = function (t, e) {
            var n;
            "string" === typeof t && (n = t, t = function (t) {
                return n.replace(a, function (e, n) {
                    return (0, i.default)(t[n])
                })
            });
            var r = function (e) {
                return e.path = e.label || e.path || "this", "function" === typeof t ? t(e) : t
            };
            return 1 === arguments.length ? r : r(e)
        }, t.exports = e.default
    }, 714: function (t, e, n) {
    }, 752: function (t, e, n) {
        var r = n(683), i = n(674), a = Object.prototype.hasOwnProperty;
        t.exports = function (t, e, n) {
            var o = t[e];
            a.call(t, e) && i(o, n) && (void 0 !== n || e in t) || r(t, e, n)
        }
    }, 753: function (t, e, n) {
        var r = n(738), i = n(899), a = n(661);
        t.exports = function (t) {
            return a(t) ? r(t, !0) : i(t)
        }
    }, 754: function (t, e) {
        t.exports = function (t, e) {
            var n = -1, r = t.length;
            for (e || (e = Array(r)); ++n < r;) e[n] = t[n];
            return e
        }
    }, 755: function (t, e, n) {
        var r = n(736), i = n(756), a = n(701), o = n(737), s = Object.getOwnPropertySymbols ? function (t) {
            for (var e = []; t;) r(e, a(t)), t = i(t);
            return e
        } : o;
        t.exports = s
    }, 756: function (t, e, n) {
        var r = n(741)(Object.getPrototypeOf, Object);
        t.exports = r
    }, 757: function (t, e, n) {
        var r = n(920), i = n(758), a = n(921);
        t.exports = function (t) {
            return i(t) ? a(t) : r(t)
        }
    }, 758: function (t, e) {
        var n = RegExp("[\\u200d\\ud800-\\udfff\\u0300-\\u036f\\ufe20-\\ufe2f\\u20d0-\\u20ff\\ufe0e\\ufe0f]");
        t.exports = function (t) {
            return n.test(t)
        }
    }, 759: function (t, e, n) {
        "use strict";

        function r(t) {
            return Array.prototype.slice.apply(t)
        }

        var i = "pending";

        function a(t) {
            this.status = i, this._continuations = [], this._parent = null, this._paused = !1, t && t.call(this, this._continueWith.bind(this), this._failWith.bind(this))
        }

        function o(t) {
            return t && "function" === typeof t.then
        }

        if (a.prototype = {
            then: function (t, e) {
                var n = a.unresolved()._setParent(this);
                if (this._isRejected()) {
                    if (this._paused) return this._continuations.push({promise: n, nextFn: t, catchFn: e}), n;
                    if (e) try {
                        var r = e(this._error);
                        return o(r) ? (this._chainPromiseData(r, n), n) : a.resolve(r)._setParent(this)
                    } catch (i) {
                        return a.reject(i)._setParent(this)
                    }
                    return a.reject(this._error)._setParent(this)
                }
                return this._continuations.push({promise: n, nextFn: t, catchFn: e}), this._runResolutions(), n
            }, catch: function (t) {
                if (this._isResolved()) return a.resolve(this._data)._setParent(this);
                var e = a.unresolved()._setParent(this);
                return this._continuations.push({promise: e, catchFn: t}), this._runRejections(), e
            }, finally: function (t) {
                var e = !1;

                function n() {
                    if (!e) return e = !0, t()
                }

                return this.then(n).catch(n)
            }, pause: function () {
                return this._paused = !0, this
            }, resume: function () {
                var t = this._findFirstPaused();
                return t && (t._paused = !1, t._runResolutions(), t._runRejections()), this
            }, _findAncestry: function () {
                return this._continuations.reduce(function (t, e) {
                    if (e.promise) {
                        var n = {promise: e.promise, children: e.promise._findAncestry()};
                        t.push(n)
                    }
                    return t
                }, [])
            }, _setParent: function (t) {
                if (this._parent) throw new Error("parent already set");
                return this._parent = t, this
            }, _continueWith: function (t) {
                var e = this._findFirstPending();
                e && (e._data = t, e._setResolved())
            }, _findFirstPending: function () {
                return this._findFirstAncestor(function (t) {
                    return t._isPending && t._isPending()
                })
            }, _findFirstPaused: function () {
                return this._findFirstAncestor(function (t) {
                    return t._paused
                })
            }, _findFirstAncestor: function (t) {
                for (var e, n = this; n;) t(n) && (e = n), n = n._parent;
                return e
            }, _failWith: function (t) {
                var e = this._findFirstPending();
                e && (e._error = t, e._setRejected())
            }, _takeContinuations: function () {
                return this._continuations.splice(0, this._continuations.length)
            }, _runRejections: function () {
                if (!this._paused && this._isRejected()) {
                    var t = this._error, e = this._takeContinuations(), n = this;
                    e.forEach(function (e) {
                        if (e.catchFn) try {
                            var r = e.catchFn(t);
                            n._handleUserFunctionResult(r, e.promise)
                        } catch (i) {
                            i.message;
                            e.promise.reject(i)
                        } else e.promise.reject(t)
                    })
                }
            }, _runResolutions: function () {
                if (!this._paused && this._isResolved() && !this._isPending()) {
                    var t = this._takeContinuations();
                    if (o(this._data)) return this._handleWhenResolvedDataIsPromise(this._data);
                    var e = this._data, n = this;
                    t.forEach(function (t) {
                        if (t.nextFn) try {
                            var r = t.nextFn(e);
                            n._handleUserFunctionResult(r, t.promise)
                        } catch (i) {
                            n._handleResolutionError(i, t)
                        } else t.promise && t.promise.resolve(e)
                    })
                }
            }, _handleResolutionError: function (t, e) {
                if (this._setRejected(), e.catchFn) try {
                    return void e.catchFn(t)
                } catch (n) {
                    t = n
                }
                e.promise && e.promise.reject(t)
            }, _handleWhenResolvedDataIsPromise: function (t) {
                var e = this;
                return t.then(function (t) {
                    e._data = t, e._runResolutions()
                }).catch(function (t) {
                    e._error = t, e._setRejected(), e._runRejections()
                })
            }, _handleUserFunctionResult: function (t, e) {
                o(t) ? this._chainPromiseData(t, e) : e.resolve(t)
            }, _chainPromiseData: function (t, e) {
                t.then(function (t) {
                    e.resolve(t)
                }).catch(function (t) {
                    e.reject(t)
                })
            }, _setResolved: function () {
                this.status = "resolved", this._paused || this._runResolutions()
            }, _setRejected: function () {
                this.status = "rejected", this._paused || this._runRejections()
            }, _isPending: function () {
                return this.status === i
            }, _isResolved: function () {
                return "resolved" === this.status
            }, _isRejected: function () {
                return "rejected" === this.status
            }
        }, a.resolve = function (t) {
            return new a(function (e, n) {
                o(t) ? t.then(function (t) {
                    e(t)
                }).catch(function (t) {
                    n(t)
                }) : e(t)
            })
        }, a.reject = function (t) {
            return new a(function (e, n) {
                n(t)
            })
        }, a.unresolved = function () {
            return new a(function (t, e) {
                this.resolve = t, this.reject = e
            })
        }, a.all = function () {
            var t = r(arguments);
            return Array.isArray(t[0]) && (t = t[0]), t.length ? new a(function (e, n) {
                var r = [], i = 0, o = !1;
                t.forEach(function (s, l) {
                    a.resolve(s).then(function (n) {
                        r[l] = n, (i += 1) === t.length && e(r)
                    }).catch(function (t) {
                        !function (t) {
                            o || (o = !0, n(t))
                        }(t)
                    })
                })
            }) : a.resolve([])
        }, Promise === a) throw new Error("Please use SynchronousPromise.installGlobally() to install globally");
        var s = Promise;
        a.installGlobally = function (t) {
            if (Promise === a) return t;
            var e = function (t) {
                if ("undefined" === typeof t || t.__patched) return t;
                var e = t;
                return (t = function () {
                    e.apply(this, r(arguments))
                }).__patched = !0, t
            }(t);
            return Promise = a, e
        }, a.uninstallGlobally = function () {
            Promise === a && (Promise = s)
        }, t.exports = {SynchronousPromise: a}
    }, 760: function (t, e, n) {
        var r = n(683), i = n(709), a = n(666);
        t.exports = function (t, e) {
            var n = {};
            return e = a(e, 3), i(t, function (t, i, a) {
                r(n, i, e(t, i, a))
            }), n
        }
    }, 761: function (t, e, n) {
        "use strict";
        var r = n(317);
        e.__esModule = !0, e.getIn = s, e.default = void 0;
        var i = n(686), a = r(n(663)), o = function (t) {
            return t.substr(0, t.length - 1).substr(1)
        };

        function s(t, e, n, r) {
            var s, l, u;
            return r = r || n, e ? ((0, i.forEach)(e, function (i, c, d) {
                var h = c ? o(i) : i;
                if (d || (0, a.default)(t, "_subType")) {
                    var f = d ? parseInt(h, 10) : 0;
                    if (t = t.resolve({context: r, parent: s, value: n})._subType, n) {
                        if (d && f >= n.length) throw new Error("Yup.reach cannot resolve an array item at index: " + i + ", in the path: " + e + ". because there is no value at that index. ");
                        n = n[f]
                    }
                }
                if (!d) {
                    if (t = t.resolve({
                        context: r,
                        parent: s,
                        value: n
                    }), !(0, a.default)(t, "fields") || !(0, a.default)(t.fields, h)) throw new Error("The schema does not contain the path: " + e + ". (failed at: " + u + ' which is a type: "' + t._type + '") ');
                    t = t.fields[h], s = n, n = n && n[h], l = h, u = c ? "[" + i + "]" : "." + i
                }
            }), {schema: t, parent: s, parentPath: l}) : {parent: s, parentPath: e, schema: t}
        }

        var l = function (t, e, n, r) {
            return s(t, e, n, r).schema
        };
        e.default = l
    }, 762: function (t, e) {
        t.exports = function (t) {
            if (t && t.__esModule) return t;
            var e = {};
            if (null != t) for (var n in t) if (Object.prototype.hasOwnProperty.call(t, n)) {
                var r = Object.defineProperty && Object.getOwnPropertyDescriptor ? Object.getOwnPropertyDescriptor(t, n) : {};
                r.get || r.set ? Object.defineProperty(e, n, r) : e[n] = t[n]
            }
            return e.default = t, e
        }
    }, 763: function (t, e) {
        t.exports = function (t, e) {
            return e || (e = t.slice(0)), t.raw = e, t
        }
    }, 764: function (t, e, n) {
        var r = n(934), i = n(935), a = n(938), o = RegExp("['\u2019]", "g");
        t.exports = function (t) {
            return function (e) {
                return r(a(i(e).replace(o, "")), t, "")
            }
        }
    }, 765: function (t, e, n) {
        "use strict";
        e.__esModule = !0, e.default = function (t) {
            for (var e = arguments.length, n = new Array(e > 1 ? e - 1 : 0), r = 1; r < e; r++) n[r - 1] = arguments[r];
            return t.reduce(function (t, e) {
                var r = n.shift();
                return t + (null == r ? "" : r) + e
            }).replace(/^\./, "")
        }, t.exports = e.default
    }, 768: function (t, e, n) {
        t.exports = function (t) {
            "use strict";
            t = t && t.hasOwnProperty("default") ? t.default : t;
            var e, n = {
                aliceblue: [240, 248, 255],
                antiquewhite: [250, 235, 215],
                aqua: [0, 255, 255],
                aquamarine: [127, 255, 212],
                azure: [240, 255, 255],
                beige: [245, 245, 220],
                bisque: [255, 228, 196],
                black: [0, 0, 0],
                blanchedalmond: [255, 235, 205],
                blue: [0, 0, 255],
                blueviolet: [138, 43, 226],
                brown: [165, 42, 42],
                burlywood: [222, 184, 135],
                cadetblue: [95, 158, 160],
                chartreuse: [127, 255, 0],
                chocolate: [210, 105, 30],
                coral: [255, 127, 80],
                cornflowerblue: [100, 149, 237],
                cornsilk: [255, 248, 220],
                crimson: [220, 20, 60],
                cyan: [0, 255, 255],
                darkblue: [0, 0, 139],
                darkcyan: [0, 139, 139],
                darkgoldenrod: [184, 134, 11],
                darkgray: [169, 169, 169],
                darkgreen: [0, 100, 0],
                darkgrey: [169, 169, 169],
                darkkhaki: [189, 183, 107],
                darkmagenta: [139, 0, 139],
                darkolivegreen: [85, 107, 47],
                darkorange: [255, 140, 0],
                darkorchid: [153, 50, 204],
                darkred: [139, 0, 0],
                darksalmon: [233, 150, 122],
                darkseagreen: [143, 188, 143],
                darkslateblue: [72, 61, 139],
                darkslategray: [47, 79, 79],
                darkslategrey: [47, 79, 79],
                darkturquoise: [0, 206, 209],
                darkviolet: [148, 0, 211],
                deeppink: [255, 20, 147],
                deepskyblue: [0, 191, 255],
                dimgray: [105, 105, 105],
                dimgrey: [105, 105, 105],
                dodgerblue: [30, 144, 255],
                firebrick: [178, 34, 34],
                floralwhite: [255, 250, 240],
                forestgreen: [34, 139, 34],
                fuchsia: [255, 0, 255],
                gainsboro: [220, 220, 220],
                ghostwhite: [248, 248, 255],
                gold: [255, 215, 0],
                goldenrod: [218, 165, 32],
                gray: [128, 128, 128],
                green: [0, 128, 0],
                greenyellow: [173, 255, 47],
                grey: [128, 128, 128],
                honeydew: [240, 255, 240],
                hotpink: [255, 105, 180],
                indianred: [205, 92, 92],
                indigo: [75, 0, 130],
                ivory: [255, 255, 240],
                khaki: [240, 230, 140],
                lavender: [230, 230, 250],
                lavenderblush: [255, 240, 245],
                lawngreen: [124, 252, 0],
                lemonchiffon: [255, 250, 205],
                lightblue: [173, 216, 230],
                lightcoral: [240, 128, 128],
                lightcyan: [224, 255, 255],
                lightgoldenrodyellow: [250, 250, 210],
                lightgray: [211, 211, 211],
                lightgreen: [144, 238, 144],
                lightgrey: [211, 211, 211],
                lightpink: [255, 182, 193],
                lightsalmon: [255, 160, 122],
                lightseagreen: [32, 178, 170],
                lightskyblue: [135, 206, 250],
                lightslategray: [119, 136, 153],
                lightslategrey: [119, 136, 153],
                lightsteelblue: [176, 196, 222],
                lightyellow: [255, 255, 224],
                lime: [0, 255, 0],
                limegreen: [50, 205, 50],
                linen: [250, 240, 230],
                magenta: [255, 0, 255],
                maroon: [128, 0, 0],
                mediumaquamarine: [102, 205, 170],
                mediumblue: [0, 0, 205],
                mediumorchid: [186, 85, 211],
                mediumpurple: [147, 112, 219],
                mediumseagreen: [60, 179, 113],
                mediumslateblue: [123, 104, 238],
                mediumspringgreen: [0, 250, 154],
                mediumturquoise: [72, 209, 204],
                mediumvioletred: [199, 21, 133],
                midnightblue: [25, 25, 112],
                mintcream: [245, 255, 250],
                mistyrose: [255, 228, 225],
                moccasin: [255, 228, 181],
                navajowhite: [255, 222, 173],
                navy: [0, 0, 128],
                oldlace: [253, 245, 230],
                olive: [128, 128, 0],
                olivedrab: [107, 142, 35],
                orange: [255, 165, 0],
                orangered: [255, 69, 0],
                orchid: [218, 112, 214],
                palegoldenrod: [238, 232, 170],
                palegreen: [152, 251, 152],
                paleturquoise: [175, 238, 238],
                palevioletred: [219, 112, 147],
                papayawhip: [255, 239, 213],
                peachpuff: [255, 218, 185],
                peru: [205, 133, 63],
                pink: [255, 192, 203],
                plum: [221, 160, 221],
                powderblue: [176, 224, 230],
                purple: [128, 0, 128],
                rebeccapurple: [102, 51, 153],
                red: [255, 0, 0],
                rosybrown: [188, 143, 143],
                royalblue: [65, 105, 225],
                saddlebrown: [139, 69, 19],
                salmon: [250, 128, 114],
                sandybrown: [244, 164, 96],
                seagreen: [46, 139, 87],
                seashell: [255, 245, 238],
                sienna: [160, 82, 45],
                silver: [192, 192, 192],
                skyblue: [135, 206, 235],
                slateblue: [106, 90, 205],
                slategray: [112, 128, 144],
                slategrey: [112, 128, 144],
                snow: [255, 250, 250],
                springgreen: [0, 255, 127],
                steelblue: [70, 130, 180],
                tan: [210, 180, 140],
                teal: [0, 128, 128],
                thistle: [216, 191, 216],
                tomato: [255, 99, 71],
                turquoise: [64, 224, 208],
                violet: [238, 130, 238],
                wheat: [245, 222, 179],
                white: [255, 255, 255],
                whitesmoke: [245, 245, 245],
                yellow: [255, 255, 0],
                yellowgreen: [154, 205, 50]
            }, r = (function (t) {
                var e = {};
                for (var r in n) n.hasOwnProperty(r) && (e[n[r]] = r);
                var i = t.exports = {
                    rgb: {channels: 3, labels: "rgb"},
                    hsl: {channels: 3, labels: "hsl"},
                    hsv: {channels: 3, labels: "hsv"},
                    hwb: {channels: 3, labels: "hwb"},
                    cmyk: {channels: 4, labels: "cmyk"},
                    xyz: {channels: 3, labels: "xyz"},
                    lab: {channels: 3, labels: "lab"},
                    lch: {channels: 3, labels: "lch"},
                    hex: {channels: 1, labels: ["hex"]},
                    keyword: {channels: 1, labels: ["keyword"]},
                    ansi16: {channels: 1, labels: ["ansi16"]},
                    ansi256: {channels: 1, labels: ["ansi256"]},
                    hcg: {channels: 3, labels: ["h", "c", "g"]},
                    apple: {channels: 3, labels: ["r16", "g16", "b16"]},
                    gray: {channels: 1, labels: ["gray"]}
                };
                for (var a in i) if (i.hasOwnProperty(a)) {
                    if (!("channels" in i[a])) throw new Error("missing channels property: " + a);
                    if (!("labels" in i[a])) throw new Error("missing channel labels property: " + a);
                    if (i[a].labels.length !== i[a].channels) throw new Error("channel and label counts mismatch: " + a);
                    var o = i[a].channels, s = i[a].labels;
                    delete i[a].channels, delete i[a].labels, Object.defineProperty(i[a], "channels", {value: o}), Object.defineProperty(i[a], "labels", {value: s})
                }
                i.rgb.hsl = function (t) {
                    var e, n, r = t[0] / 255, i = t[1] / 255, a = t[2] / 255, o = Math.min(r, i, a),
                        s = Math.max(r, i, a), l = s - o;
                    return s === o ? e = 0 : r === s ? e = (i - a) / l : i === s ? e = 2 + (a - r) / l : a === s && (e = 4 + (r - i) / l), (e = Math.min(60 * e, 360)) < 0 && (e += 360), n = (o + s) / 2, [e, 100 * (s === o ? 0 : n <= .5 ? l / (s + o) : l / (2 - s - o)), 100 * n]
                }, i.rgb.hsv = function (t) {
                    var e, n, r, i, a, o = t[0] / 255, s = t[1] / 255, l = t[2] / 255, u = Math.max(o, s, l),
                        c = u - Math.min(o, s, l), d = function (t) {
                            return (u - t) / 6 / c + .5
                        };
                    return 0 === c ? i = a = 0 : (a = c / u, e = d(o), n = d(s), r = d(l), o === u ? i = r - n : s === u ? i = 1 / 3 + e - r : l === u && (i = 2 / 3 + n - e), i < 0 ? i += 1 : i > 1 && (i -= 1)), [360 * i, 100 * a, 100 * u]
                }, i.rgb.hwb = function (t) {
                    var e = t[0], n = t[1], r = t[2], a = i.rgb.hsl(t)[0], o = 1 / 255 * Math.min(e, Math.min(n, r));
                    return r = 1 - 1 / 255 * Math.max(e, Math.max(n, r)), [a, 100 * o, 100 * r]
                }, i.rgb.cmyk = function (t) {
                    var e, n = t[0] / 255, r = t[1] / 255, i = t[2] / 255;
                    return e = Math.min(1 - n, 1 - r, 1 - i), [100 * ((1 - n - e) / (1 - e) || 0), 100 * ((1 - r - e) / (1 - e) || 0), 100 * ((1 - i - e) / (1 - e) || 0), 100 * e]
                }, i.rgb.keyword = function (t) {
                    var r = e[t];
                    if (r) return r;
                    var i, a, o, s = 1 / 0;
                    for (var l in n) if (n.hasOwnProperty(l)) {
                        var u = n[l],
                            c = (a = t, o = u, Math.pow(a[0] - o[0], 2) + Math.pow(a[1] - o[1], 2) + Math.pow(a[2] - o[2], 2));
                        c < s && (s = c, i = l)
                    }
                    return i
                }, i.keyword.rgb = function (t) {
                    return n[t]
                }, i.rgb.xyz = function (t) {
                    var e = t[0] / 255, n = t[1] / 255, r = t[2] / 255;
                    e = e > .04045 ? Math.pow((e + .055) / 1.055, 2.4) : e / 12.92, n = n > .04045 ? Math.pow((n + .055) / 1.055, 2.4) : n / 12.92, r = r > .04045 ? Math.pow((r + .055) / 1.055, 2.4) : r / 12.92;
                    var i = .4124 * e + .3576 * n + .1805 * r, a = .2126 * e + .7152 * n + .0722 * r,
                        o = .0193 * e + .1192 * n + .9505 * r;
                    return [100 * i, 100 * a, 100 * o]
                }, i.rgb.lab = function (t) {
                    var e = i.rgb.xyz(t), n = e[0], r = e[1], a = e[2];
                    return r /= 100, a /= 108.883, n = (n /= 95.047) > .008856 ? Math.pow(n, 1 / 3) : 7.787 * n + 16 / 116, r = r > .008856 ? Math.pow(r, 1 / 3) : 7.787 * r + 16 / 116, a = a > .008856 ? Math.pow(a, 1 / 3) : 7.787 * a + 16 / 116, [116 * r - 16, 500 * (n - r), 200 * (r - a)]
                }, i.hsl.rgb = function (t) {
                    var e, n, r, i, a, o = t[0] / 360, s = t[1] / 100, l = t[2] / 100;
                    if (0 === s) return [a = 255 * l, a, a];
                    e = 2 * l - (n = l < .5 ? l * (1 + s) : l + s - l * s), i = [0, 0, 0];
                    for (var u = 0; u < 3; u++) (r = o + 1 / 3 * -(u - 1)) < 0 && r++, r > 1 && r--, a = 6 * r < 1 ? e + 6 * (n - e) * r : 2 * r < 1 ? n : 3 * r < 2 ? e + (n - e) * (2 / 3 - r) * 6 : e, i[u] = 255 * a;
                    return i
                }, i.hsl.hsv = function (t) {
                    var e = t[0], n = t[1] / 100, r = t[2] / 100, i = n, a = Math.max(r, .01);
                    return n *= (r *= 2) <= 1 ? r : 2 - r, i *= a <= 1 ? a : 2 - a, [e, 100 * (0 === r ? 2 * i / (a + i) : 2 * n / (r + n)), (r + n) / 2 * 100]
                }, i.hsv.rgb = function (t) {
                    var e = t[0] / 60, n = t[1] / 100, r = t[2] / 100, i = Math.floor(e) % 6, a = e - Math.floor(e),
                        o = 255 * r * (1 - n), s = 255 * r * (1 - n * a), l = 255 * r * (1 - n * (1 - a));
                    switch (r *= 255, i) {
                        case 0:
                            return [r, l, o];
                        case 1:
                            return [s, r, o];
                        case 2:
                            return [o, r, l];
                        case 3:
                            return [o, s, r];
                        case 4:
                            return [l, o, r];
                        case 5:
                            return [r, o, s]
                    }
                }, i.hsv.hsl = function (t) {
                    var e, n, r, i = t[0], a = t[1] / 100, o = t[2] / 100, s = Math.max(o, .01);
                    return r = (2 - a) * o, n = a * s, [i, 100 * (n = (n /= (e = (2 - a) * s) <= 1 ? e : 2 - e) || 0), 100 * (r /= 2)]
                }, i.hwb.rgb = function (t) {
                    var e, n, r, i, a, o, s, l = t[0] / 360, u = t[1] / 100, c = t[2] / 100, d = u + c;
                    switch (d > 1 && (u /= d, c /= d), e = Math.floor(6 * l), r = 6 * l - e, 0 !== (1 & e) && (r = 1 - r), i = u + r * ((n = 1 - c) - u), e) {
                        default:
                        case 6:
                        case 0:
                            a = n, o = i, s = u;
                            break;
                        case 1:
                            a = i, o = n, s = u;
                            break;
                        case 2:
                            a = u, o = n, s = i;
                            break;
                        case 3:
                            a = u, o = i, s = n;
                            break;
                        case 4:
                            a = i, o = u, s = n;
                            break;
                        case 5:
                            a = n, o = u, s = i
                    }
                    return [255 * a, 255 * o, 255 * s]
                }, i.cmyk.rgb = function (t) {
                    var e, n, r, i = t[0] / 100, a = t[1] / 100, o = t[2] / 100, s = t[3] / 100;
                    return e = 1 - Math.min(1, i * (1 - s) + s), n = 1 - Math.min(1, a * (1 - s) + s), r = 1 - Math.min(1, o * (1 - s) + s), [255 * e, 255 * n, 255 * r]
                }, i.xyz.rgb = function (t) {
                    var e, n, r, i = t[0] / 100, a = t[1] / 100, o = t[2] / 100;
                    return n = -.9689 * i + 1.8758 * a + .0415 * o, r = .0557 * i + -.204 * a + 1.057 * o, e = (e = 3.2406 * i + -1.5372 * a + -.4986 * o) > .0031308 ? 1.055 * Math.pow(e, 1 / 2.4) - .055 : 12.92 * e, n = n > .0031308 ? 1.055 * Math.pow(n, 1 / 2.4) - .055 : 12.92 * n, r = r > .0031308 ? 1.055 * Math.pow(r, 1 / 2.4) - .055 : 12.92 * r, e = Math.min(Math.max(0, e), 1), n = Math.min(Math.max(0, n), 1), r = Math.min(Math.max(0, r), 1), [255 * e, 255 * n, 255 * r]
                }, i.xyz.lab = function (t) {
                    var e = t[0], n = t[1], r = t[2];
                    return n /= 100, r /= 108.883, e = (e /= 95.047) > .008856 ? Math.pow(e, 1 / 3) : 7.787 * e + 16 / 116, n = n > .008856 ? Math.pow(n, 1 / 3) : 7.787 * n + 16 / 116, r = r > .008856 ? Math.pow(r, 1 / 3) : 7.787 * r + 16 / 116, [116 * n - 16, 500 * (e - n), 200 * (n - r)]
                }, i.lab.xyz = function (t) {
                    var e, n, r, i = t[0], a = t[1], o = t[2];
                    e = a / 500 + (n = (i + 16) / 116), r = n - o / 200;
                    var s = Math.pow(n, 3), l = Math.pow(e, 3), u = Math.pow(r, 3);
                    return n = s > .008856 ? s : (n - 16 / 116) / 7.787, e = l > .008856 ? l : (e - 16 / 116) / 7.787, r = u > .008856 ? u : (r - 16 / 116) / 7.787, [e *= 95.047, n *= 100, r *= 108.883]
                }, i.lab.lch = function (t) {
                    var e, n, r, i = t[0], a = t[1], o = t[2];
                    return e = Math.atan2(o, a), (n = 360 * e / 2 / Math.PI) < 0 && (n += 360), r = Math.sqrt(a * a + o * o), [i, r, n]
                }, i.lch.lab = function (t) {
                    var e, n, r, i = t[0], a = t[1], o = t[2];
                    return r = o / 360 * 2 * Math.PI, e = a * Math.cos(r), n = a * Math.sin(r), [i, e, n]
                }, i.rgb.ansi16 = function (t) {
                    var e = t[0], n = t[1], r = t[2], a = 1 in arguments ? arguments[1] : i.rgb.hsv(t)[2];
                    if (0 === (a = Math.round(a / 50))) return 30;
                    var o = 30 + (Math.round(r / 255) << 2 | Math.round(n / 255) << 1 | Math.round(e / 255));
                    return 2 === a && (o += 60), o
                }, i.hsv.ansi16 = function (t) {
                    return i.rgb.ansi16(i.hsv.rgb(t), t[2])
                }, i.rgb.ansi256 = function (t) {
                    var e = t[0], n = t[1], r = t[2];
                    if (e === n && n === r) return e < 8 ? 16 : e > 248 ? 231 : Math.round((e - 8) / 247 * 24) + 232;
                    var i = 16 + 36 * Math.round(e / 255 * 5) + 6 * Math.round(n / 255 * 5) + Math.round(r / 255 * 5);
                    return i
                }, i.ansi16.rgb = function (t) {
                    var e = t % 10;
                    if (0 === e || 7 === e) return t > 50 && (e += 3.5), [e = e / 10.5 * 255, e, e];
                    var n = .5 * (1 + ~~(t > 50)), r = (1 & e) * n * 255, i = (e >> 1 & 1) * n * 255,
                        a = (e >> 2 & 1) * n * 255;
                    return [r, i, a]
                }, i.ansi256.rgb = function (t) {
                    if (t >= 232) {
                        var e = 10 * (t - 232) + 8;
                        return [e, e, e]
                    }
                    var n;
                    t -= 16;
                    var r = Math.floor(t / 36) / 5 * 255, i = Math.floor((n = t % 36) / 6) / 5 * 255,
                        a = n % 6 / 5 * 255;
                    return [r, i, a]
                }, i.rgb.hex = function (t) {
                    var e = ((255 & Math.round(t[0])) << 16) + ((255 & Math.round(t[1])) << 8) + (255 & Math.round(t[2])),
                        n = e.toString(16).toUpperCase();
                    return "000000".substring(n.length) + n
                }, i.hex.rgb = function (t) {
                    var e = t.toString(16).match(/[a-f0-9]{6}|[a-f0-9]{3}/i);
                    if (!e) return [0, 0, 0];
                    var n = e[0];
                    3 === e[0].length && (n = n.split("").map(function (t) {
                        return t + t
                    }).join(""));
                    var r = parseInt(n, 16), i = r >> 16 & 255, a = r >> 8 & 255, o = 255 & r;
                    return [i, a, o]
                }, i.rgb.hcg = function (t) {
                    var e, n = t[0] / 255, r = t[1] / 255, i = t[2] / 255, a = Math.max(Math.max(n, r), i),
                        o = Math.min(Math.min(n, r), i), s = a - o;
                    return e = s <= 0 ? 0 : a === n ? (r - i) / s % 6 : a === r ? 2 + (i - n) / s : 4 + (n - r) / s + 4, e /= 6, [360 * (e %= 1), 100 * s, 100 * (s < 1 ? o / (1 - s) : 0)]
                }, i.hsl.hcg = function (t) {
                    var e = t[1] / 100, n = t[2] / 100, r = 1, i = 0;
                    return (r = n < .5 ? 2 * e * n : 2 * e * (1 - n)) < 1 && (i = (n - .5 * r) / (1 - r)), [t[0], 100 * r, 100 * i]
                }, i.hsv.hcg = function (t) {
                    var e = t[1] / 100, n = t[2] / 100, r = e * n, i = 0;
                    return r < 1 && (i = (n - r) / (1 - r)), [t[0], 100 * r, 100 * i]
                }, i.hcg.rgb = function (t) {
                    var e = t[0] / 360, n = t[1] / 100, r = t[2] / 100;
                    if (0 === n) return [255 * r, 255 * r, 255 * r];
                    var i = [0, 0, 0], a = e % 1 * 6, o = a % 1, s = 1 - o, l = 0;
                    switch (Math.floor(a)) {
                        case 0:
                            i[0] = 1, i[1] = o, i[2] = 0;
                            break;
                        case 1:
                            i[0] = s, i[1] = 1, i[2] = 0;
                            break;
                        case 2:
                            i[0] = 0, i[1] = 1, i[2] = o;
                            break;
                        case 3:
                            i[0] = 0, i[1] = s, i[2] = 1;
                            break;
                        case 4:
                            i[0] = o, i[1] = 0, i[2] = 1;
                            break;
                        default:
                            i[0] = 1, i[1] = 0, i[2] = s
                    }
                    return l = (1 - n) * r, [255 * (n * i[0] + l), 255 * (n * i[1] + l), 255 * (n * i[2] + l)]
                }, i.hcg.hsv = function (t) {
                    var e = t[1] / 100, n = t[2] / 100, r = e + n * (1 - e), i = 0;
                    return r > 0 && (i = e / r), [t[0], 100 * i, 100 * r]
                }, i.hcg.hsl = function (t) {
                    var e = t[1] / 100, n = t[2] / 100, r = n * (1 - e) + .5 * e, i = 0;
                    return r > 0 && r < .5 ? i = e / (2 * r) : r >= .5 && r < 1 && (i = e / (2 * (1 - r))), [t[0], 100 * i, 100 * r]
                }, i.hcg.hwb = function (t) {
                    var e = t[1] / 100, n = t[2] / 100, r = e + n * (1 - e);
                    return [t[0], 100 * (r - e), 100 * (1 - r)]
                }, i.hwb.hcg = function (t) {
                    var e = t[1] / 100, n = t[2] / 100, r = 1 - n, i = r - e, a = 0;
                    return i < 1 && (a = (r - i) / (1 - i)), [t[0], 100 * i, 100 * a]
                }, i.apple.rgb = function (t) {
                    return [t[0] / 65535 * 255, t[1] / 65535 * 255, t[2] / 65535 * 255]
                }, i.rgb.apple = function (t) {
                    return [t[0] / 255 * 65535, t[1] / 255 * 65535, t[2] / 255 * 65535]
                }, i.gray.rgb = function (t) {
                    return [t[0] / 100 * 255, t[0] / 100 * 255, t[0] / 100 * 255]
                }, i.gray.hsl = i.gray.hsv = function (t) {
                    return [0, 0, t[0]]
                }, i.gray.hwb = function (t) {
                    return [0, 100, t[0]]
                }, i.gray.cmyk = function (t) {
                    return [0, 0, 0, t[0]]
                }, i.gray.lab = function (t) {
                    return [t[0], 0, 0]
                }, i.gray.hex = function (t) {
                    var e = 255 & Math.round(t[0] / 100 * 255), n = (e << 16) + (e << 8) + e,
                        r = n.toString(16).toUpperCase();
                    return "000000".substring(r.length) + r
                }, i.rgb.gray = function (t) {
                    var e = (t[0] + t[1] + t[2]) / 3;
                    return [e / 255 * 100]
                }
            }(e = {exports: {}}, e.exports), e.exports);

            function i(t) {
                var e = function () {
                    for (var t = {}, e = Object.keys(r), n = e.length, i = 0; i < n; i++) t[e[i]] = {
                        distance: -1,
                        parent: null
                    };
                    return t
                }(), n = [t];
                for (e[t].distance = 0; n.length;) for (var i = n.pop(), a = Object.keys(r[i]), o = a.length, s = 0; s < o; s++) {
                    var l = a[s], u = e[l];
                    -1 === u.distance && (u.distance = e[i].distance + 1, u.parent = i, n.unshift(l))
                }
                return e
            }

            function a(t, e) {
                return function (n) {
                    return e(t(n))
                }
            }

            function o(t, e) {
                for (var n = [e[t].parent, t], i = r[e[t].parent][t], o = e[t].parent; e[o].parent;) n.unshift(e[o].parent), i = a(r[e[o].parent][o], i), o = e[o].parent;
                return i.conversion = n, i
            }

            r.rgb, r.hsl, r.hsv, r.hwb, r.cmyk, r.xyz, r.lab, r.lch, r.hex, r.keyword, r.ansi16, r.ansi256, r.hcg, r.apple, r.gray;
            var s = {};
            Object.keys(r).forEach(function (t) {
                s[t] = {}, Object.defineProperty(s[t], "channels", {value: r[t].channels}), Object.defineProperty(s[t], "labels", {value: r[t].labels});
                var e = function (t) {
                    for (var e = i(t), n = {}, r = Object.keys(e), a = r.length, s = 0; s < a; s++) {
                        var l = r[s], u = e[l];
                        null !== u.parent && (n[l] = o(l, e))
                    }
                    return n
                }(t), n = Object.keys(e);
                n.forEach(function (n) {
                    var r = e[n];
                    s[t][n] = function (t) {
                        var e = function (e) {
                            if (void 0 === e || null === e) return e;
                            arguments.length > 1 && (e = Array.prototype.slice.call(arguments));
                            var n = t(e);
                            if ("object" === typeof n) for (var r = n.length, i = 0; i < r; i++) n[i] = Math.round(n[i]);
                            return n
                        };
                        return "conversion" in t && (e.conversion = t.conversion), e
                    }(r), s[t][n].raw = function (t) {
                        var e = function (e) {
                            return void 0 === e || null === e ? e : (arguments.length > 1 && (e = Array.prototype.slice.call(arguments)), t(e))
                        };
                        return "conversion" in t && (e.conversion = t.conversion), e
                    }(r)
                })
            });
            var l = s, u = {
                aliceblue: [240, 248, 255],
                antiquewhite: [250, 235, 215],
                aqua: [0, 255, 255],
                aquamarine: [127, 255, 212],
                azure: [240, 255, 255],
                beige: [245, 245, 220],
                bisque: [255, 228, 196],
                black: [0, 0, 0],
                blanchedalmond: [255, 235, 205],
                blue: [0, 0, 255],
                blueviolet: [138, 43, 226],
                brown: [165, 42, 42],
                burlywood: [222, 184, 135],
                cadetblue: [95, 158, 160],
                chartreuse: [127, 255, 0],
                chocolate: [210, 105, 30],
                coral: [255, 127, 80],
                cornflowerblue: [100, 149, 237],
                cornsilk: [255, 248, 220],
                crimson: [220, 20, 60],
                cyan: [0, 255, 255],
                darkblue: [0, 0, 139],
                darkcyan: [0, 139, 139],
                darkgoldenrod: [184, 134, 11],
                darkgray: [169, 169, 169],
                darkgreen: [0, 100, 0],
                darkgrey: [169, 169, 169],
                darkkhaki: [189, 183, 107],
                darkmagenta: [139, 0, 139],
                darkolivegreen: [85, 107, 47],
                darkorange: [255, 140, 0],
                darkorchid: [153, 50, 204],
                darkred: [139, 0, 0],
                darksalmon: [233, 150, 122],
                darkseagreen: [143, 188, 143],
                darkslateblue: [72, 61, 139],
                darkslategray: [47, 79, 79],
                darkslategrey: [47, 79, 79],
                darkturquoise: [0, 206, 209],
                darkviolet: [148, 0, 211],
                deeppink: [255, 20, 147],
                deepskyblue: [0, 191, 255],
                dimgray: [105, 105, 105],
                dimgrey: [105, 105, 105],
                dodgerblue: [30, 144, 255],
                firebrick: [178, 34, 34],
                floralwhite: [255, 250, 240],
                forestgreen: [34, 139, 34],
                fuchsia: [255, 0, 255],
                gainsboro: [220, 220, 220],
                ghostwhite: [248, 248, 255],
                gold: [255, 215, 0],
                goldenrod: [218, 165, 32],
                gray: [128, 128, 128],
                green: [0, 128, 0],
                greenyellow: [173, 255, 47],
                grey: [128, 128, 128],
                honeydew: [240, 255, 240],
                hotpink: [255, 105, 180],
                indianred: [205, 92, 92],
                indigo: [75, 0, 130],
                ivory: [255, 255, 240],
                khaki: [240, 230, 140],
                lavender: [230, 230, 250],
                lavenderblush: [255, 240, 245],
                lawngreen: [124, 252, 0],
                lemonchiffon: [255, 250, 205],
                lightblue: [173, 216, 230],
                lightcoral: [240, 128, 128],
                lightcyan: [224, 255, 255],
                lightgoldenrodyellow: [250, 250, 210],
                lightgray: [211, 211, 211],
                lightgreen: [144, 238, 144],
                lightgrey: [211, 211, 211],
                lightpink: [255, 182, 193],
                lightsalmon: [255, 160, 122],
                lightseagreen: [32, 178, 170],
                lightskyblue: [135, 206, 250],
                lightslategray: [119, 136, 153],
                lightslategrey: [119, 136, 153],
                lightsteelblue: [176, 196, 222],
                lightyellow: [255, 255, 224],
                lime: [0, 255, 0],
                limegreen: [50, 205, 50],
                linen: [250, 240, 230],
                magenta: [255, 0, 255],
                maroon: [128, 0, 0],
                mediumaquamarine: [102, 205, 170],
                mediumblue: [0, 0, 205],
                mediumorchid: [186, 85, 211],
                mediumpurple: [147, 112, 219],
                mediumseagreen: [60, 179, 113],
                mediumslateblue: [123, 104, 238],
                mediumspringgreen: [0, 250, 154],
                mediumturquoise: [72, 209, 204],
                mediumvioletred: [199, 21, 133],
                midnightblue: [25, 25, 112],
                mintcream: [245, 255, 250],
                mistyrose: [255, 228, 225],
                moccasin: [255, 228, 181],
                navajowhite: [255, 222, 173],
                navy: [0, 0, 128],
                oldlace: [253, 245, 230],
                olive: [128, 128, 0],
                olivedrab: [107, 142, 35],
                orange: [255, 165, 0],
                orangered: [255, 69, 0],
                orchid: [218, 112, 214],
                palegoldenrod: [238, 232, 170],
                palegreen: [152, 251, 152],
                paleturquoise: [175, 238, 238],
                palevioletred: [219, 112, 147],
                papayawhip: [255, 239, 213],
                peachpuff: [255, 218, 185],
                peru: [205, 133, 63],
                pink: [255, 192, 203],
                plum: [221, 160, 221],
                powderblue: [176, 224, 230],
                purple: [128, 0, 128],
                rebeccapurple: [102, 51, 153],
                red: [255, 0, 0],
                rosybrown: [188, 143, 143],
                royalblue: [65, 105, 225],
                saddlebrown: [139, 69, 19],
                salmon: [250, 128, 114],
                sandybrown: [244, 164, 96],
                seagreen: [46, 139, 87],
                seashell: [255, 245, 238],
                sienna: [160, 82, 45],
                silver: [192, 192, 192],
                skyblue: [135, 206, 235],
                slateblue: [106, 90, 205],
                slategray: [112, 128, 144],
                slategrey: [112, 128, 144],
                snow: [255, 250, 250],
                springgreen: [0, 255, 127],
                steelblue: [70, 130, 180],
                tan: [210, 180, 140],
                teal: [0, 128, 128],
                thistle: [216, 191, 216],
                tomato: [255, 99, 71],
                turquoise: [64, 224, 208],
                violet: [238, 130, 238],
                wheat: [245, 222, 179],
                white: [255, 255, 255],
                whitesmoke: [245, 245, 245],
                yellow: [255, 255, 0],
                yellowgreen: [154, 205, 50]
            }, c = {
                getRgba: d, getHsla: h, getRgb: function (t) {
                    var e = d(t);
                    return e && e.slice(0, 3)
                }, getHsl: function (t) {
                    var e = h(t);
                    return e && e.slice(0, 3)
                }, getHwb: f, getAlpha: function (t) {
                    var e = d(t);
                    return e ? e[3] : (e = h(t)) ? e[3] : (e = f(t)) ? e[3] : void 0
                }, hexString: function (t, e) {
                    var e = void 0 !== e && 3 === t.length ? e : t[3];
                    return "#" + b(t[0]) + b(t[1]) + b(t[2]) + (e >= 0 && e < 1 ? b(Math.round(255 * e)) : "")
                }, rgbString: function (t, e) {
                    return e < 1 || t[3] && t[3] < 1 ? p(t, e) : "rgb(" + t[0] + ", " + t[1] + ", " + t[2] + ")"
                }, rgbaString: p, percentString: function (t, e) {
                    if (e < 1 || t[3] && t[3] < 1) return g(t, e);
                    var n = Math.round(t[0] / 255 * 100), r = Math.round(t[1] / 255 * 100),
                        i = Math.round(t[2] / 255 * 100);
                    return "rgb(" + n + "%, " + r + "%, " + i + "%)"
                }, percentaString: g, hslString: function (t, e) {
                    return e < 1 || t[3] && t[3] < 1 ? v(t, e) : "hsl(" + t[0] + ", " + t[1] + "%, " + t[2] + "%)"
                }, hslaString: v, hwbString: function (t, e) {
                    return void 0 === e && (e = void 0 !== t[3] ? t[3] : 1), "hwb(" + t[0] + ", " + t[1] + "%, " + t[2] + "%" + (void 0 !== e && 1 !== e ? ", " + e : "") + ")"
                }, keyword: function (t) {
                    return y[t.slice(0, 3)]
                }
            };

            function d(t) {
                if (t) {
                    var e = [0, 0, 0], n = 1, r = t.match(/^#([a-fA-F0-9]{3,4})$/i), i = "";
                    if (r) {
                        r = r[1], i = r[3];
                        for (var a = 0; a < e.length; a++) e[a] = parseInt(r[a] + r[a], 16);
                        i && (n = Math.round(parseInt(i + i, 16) / 255 * 100) / 100)
                    } else if (r = t.match(/^#([a-fA-F0-9]{6}([a-fA-F0-9]{2})?)$/i)) {
                        i = r[2], r = r[1];
                        for (var a = 0; a < e.length; a++) e[a] = parseInt(r.slice(2 * a, 2 * a + 2), 16);
                        i && (n = Math.round(parseInt(i, 16) / 255 * 100) / 100)
                    } else if (r = t.match(/^rgba?\(\s*([+-]?\d+)\s*,\s*([+-]?\d+)\s*,\s*([+-]?\d+)\s*(?:,\s*([+-]?[\d\.]+)\s*)?\)$/i)) {
                        for (var a = 0; a < e.length; a++) e[a] = parseInt(r[a + 1]);
                        n = parseFloat(r[4])
                    } else if (r = t.match(/^rgba?\(\s*([+-]?[\d\.]+)\%\s*,\s*([+-]?[\d\.]+)\%\s*,\s*([+-]?[\d\.]+)\%\s*(?:,\s*([+-]?[\d\.]+)\s*)?\)$/i)) {
                        for (var a = 0; a < e.length; a++) e[a] = Math.round(2.55 * parseFloat(r[a + 1]));
                        n = parseFloat(r[4])
                    } else if (r = t.match(/(\w+)/)) {
                        if ("transparent" == r[1]) return [0, 0, 0, 0];
                        if (!(e = u[r[1]])) return
                    }
                    for (var a = 0; a < e.length; a++) e[a] = m(e[a], 0, 255);
                    return n = n || 0 == n ? m(n, 0, 1) : 1, e[3] = n, e
                }
            }

            function h(t) {
                if (t) {
                    var e = t.match(/^hsla?\(\s*([+-]?\d+)(?:deg)?\s*,\s*([+-]?[\d\.]+)%\s*,\s*([+-]?[\d\.]+)%\s*(?:,\s*([+-]?[\d\.]+)\s*)?\)/);
                    if (e) {
                        var n = parseFloat(e[4]), r = m(parseInt(e[1]), 0, 360), i = m(parseFloat(e[2]), 0, 100),
                            a = m(parseFloat(e[3]), 0, 100), o = m(isNaN(n) ? 1 : n, 0, 1);
                        return [r, i, a, o]
                    }
                }
            }

            function f(t) {
                if (t) {
                    var e = t.match(/^hwb\(\s*([+-]?\d+)(?:deg)?\s*,\s*([+-]?[\d\.]+)%\s*,\s*([+-]?[\d\.]+)%\s*(?:,\s*([+-]?[\d\.]+)\s*)?\)/);
                    if (e) {
                        var n = parseFloat(e[4]), r = m(parseInt(e[1]), 0, 360), i = m(parseFloat(e[2]), 0, 100),
                            a = m(parseFloat(e[3]), 0, 100), o = m(isNaN(n) ? 1 : n, 0, 1);
                        return [r, i, a, o]
                    }
                }
            }

            function p(t, e) {
                return void 0 === e && (e = void 0 !== t[3] ? t[3] : 1), "rgba(" + t[0] + ", " + t[1] + ", " + t[2] + ", " + e + ")"
            }

            function g(t, e) {
                var n = Math.round(t[0] / 255 * 100), r = Math.round(t[1] / 255 * 100),
                    i = Math.round(t[2] / 255 * 100);
                return "rgba(" + n + "%, " + r + "%, " + i + "%, " + (e || t[3] || 1) + ")"
            }

            function v(t, e) {
                return void 0 === e && (e = void 0 !== t[3] ? t[3] : 1), "hsla(" + t[0] + ", " + t[1] + "%, " + t[2] + "%, " + e + ")"
            }

            function m(t, e, n) {
                return Math.min(Math.max(e, t), n)
            }

            function b(t) {
                var e = t.toString(16).toUpperCase();
                return e.length < 2 ? "0" + e : e
            }

            var y = {};
            for (var x in u) y[u[x]] = x;
            var _ = function t(e) {
                return e instanceof t ? e : this instanceof t ? (this.valid = !1, this.values = {
                    rgb: [0, 0, 0],
                    hsl: [0, 0, 0],
                    hsv: [0, 0, 0],
                    hwb: [0, 0, 0],
                    cmyk: [0, 0, 0, 0],
                    alpha: 1
                }, void ("string" === typeof e ? (n = c.getRgba(e)) ? this.setValues("rgb", n) : (n = c.getHsla(e)) ? this.setValues("hsl", n) : (n = c.getHwb(e)) && this.setValues("hwb", n) : "object" === typeof e && (void 0 !== (n = e).r || void 0 !== n.red ? this.setValues("rgb", n) : void 0 !== n.l || void 0 !== n.lightness ? this.setValues("hsl", n) : void 0 !== n.v || void 0 !== n.value ? this.setValues("hsv", n) : void 0 !== n.w || void 0 !== n.whiteness ? this.setValues("hwb", n) : void 0 === n.c && void 0 === n.cyan || this.setValues("cmyk", n)))) : new t(e);
                var n
            };
            (_.prototype = {
                isValid: function () {
                    return this.valid
                }, rgb: function () {
                    return this.setSpace("rgb", arguments)
                }, hsl: function () {
                    return this.setSpace("hsl", arguments)
                }, hsv: function () {
                    return this.setSpace("hsv", arguments)
                }, hwb: function () {
                    return this.setSpace("hwb", arguments)
                }, cmyk: function () {
                    return this.setSpace("cmyk", arguments)
                }, rgbArray: function () {
                    return this.values.rgb
                }, hslArray: function () {
                    return this.values.hsl
                }, hsvArray: function () {
                    return this.values.hsv
                }, hwbArray: function () {
                    var t = this.values;
                    return 1 !== t.alpha ? t.hwb.concat([t.alpha]) : t.hwb
                }, cmykArray: function () {
                    return this.values.cmyk
                }, rgbaArray: function () {
                    var t = this.values;
                    return t.rgb.concat([t.alpha])
                }, hslaArray: function () {
                    var t = this.values;
                    return t.hsl.concat([t.alpha])
                }, alpha: function (t) {
                    return void 0 === t ? this.values.alpha : (this.setValues("alpha", t), this)
                }, red: function (t) {
                    return this.setChannel("rgb", 0, t)
                }, green: function (t) {
                    return this.setChannel("rgb", 1, t)
                }, blue: function (t) {
                    return this.setChannel("rgb", 2, t)
                }, hue: function (t) {
                    return t && (t = (t %= 360) < 0 ? 360 + t : t), this.setChannel("hsl", 0, t)
                }, saturation: function (t) {
                    return this.setChannel("hsl", 1, t)
                }, lightness: function (t) {
                    return this.setChannel("hsl", 2, t)
                }, saturationv: function (t) {
                    return this.setChannel("hsv", 1, t)
                }, whiteness: function (t) {
                    return this.setChannel("hwb", 1, t)
                }, blackness: function (t) {
                    return this.setChannel("hwb", 2, t)
                }, value: function (t) {
                    return this.setChannel("hsv", 2, t)
                }, cyan: function (t) {
                    return this.setChannel("cmyk", 0, t)
                }, magenta: function (t) {
                    return this.setChannel("cmyk", 1, t)
                }, yellow: function (t) {
                    return this.setChannel("cmyk", 2, t)
                }, black: function (t) {
                    return this.setChannel("cmyk", 3, t)
                }, hexString: function () {
                    return c.hexString(this.values.rgb)
                }, rgbString: function () {
                    return c.rgbString(this.values.rgb, this.values.alpha)
                }, rgbaString: function () {
                    return c.rgbaString(this.values.rgb, this.values.alpha)
                }, percentString: function () {
                    return c.percentString(this.values.rgb, this.values.alpha)
                }, hslString: function () {
                    return c.hslString(this.values.hsl, this.values.alpha)
                }, hslaString: function () {
                    return c.hslaString(this.values.hsl, this.values.alpha)
                }, hwbString: function () {
                    return c.hwbString(this.values.hwb, this.values.alpha)
                }, keyword: function () {
                    return c.keyword(this.values.rgb, this.values.alpha)
                }, rgbNumber: function () {
                    var t = this.values.rgb;
                    return t[0] << 16 | t[1] << 8 | t[2]
                }, luminosity: function () {
                    for (var t = this.values.rgb, e = [], n = 0; n < t.length; n++) {
                        var r = t[n] / 255;
                        e[n] = r <= .03928 ? r / 12.92 : Math.pow((r + .055) / 1.055, 2.4)
                    }
                    return .2126 * e[0] + .7152 * e[1] + .0722 * e[2]
                }, contrast: function (t) {
                    var e = this.luminosity(), n = t.luminosity();
                    return e > n ? (e + .05) / (n + .05) : (n + .05) / (e + .05)
                }, level: function (t) {
                    var e = this.contrast(t);
                    return e >= 7.1 ? "AAA" : e >= 4.5 ? "AA" : ""
                }, dark: function () {
                    var t = this.values.rgb;
                    return (299 * t[0] + 587 * t[1] + 114 * t[2]) / 1e3 < 128
                }, light: function () {
                    return !this.dark()
                }, negate: function () {
                    for (var t = [], e = 0; e < 3; e++) t[e] = 255 - this.values.rgb[e];
                    return this.setValues("rgb", t), this
                }, lighten: function (t) {
                    var e = this.values.hsl;
                    return e[2] += e[2] * t, this.setValues("hsl", e), this
                }, darken: function (t) {
                    var e = this.values.hsl;
                    return e[2] -= e[2] * t, this.setValues("hsl", e), this
                }, saturate: function (t) {
                    var e = this.values.hsl;
                    return e[1] += e[1] * t, this.setValues("hsl", e), this
                }, desaturate: function (t) {
                    var e = this.values.hsl;
                    return e[1] -= e[1] * t, this.setValues("hsl", e), this
                }, whiten: function (t) {
                    var e = this.values.hwb;
                    return e[1] += e[1] * t, this.setValues("hwb", e), this
                }, blacken: function (t) {
                    var e = this.values.hwb;
                    return e[2] += e[2] * t, this.setValues("hwb", e), this
                }, greyscale: function () {
                    var t = this.values.rgb, e = .3 * t[0] + .59 * t[1] + .11 * t[2];
                    return this.setValues("rgb", [e, e, e]), this
                }, clearer: function (t) {
                    var e = this.values.alpha;
                    return this.setValues("alpha", e - e * t), this
                }, opaquer: function (t) {
                    var e = this.values.alpha;
                    return this.setValues("alpha", e + e * t), this
                }, rotate: function (t) {
                    var e = this.values.hsl, n = (e[0] + t) % 360;
                    return e[0] = n < 0 ? 360 + n : n, this.setValues("hsl", e), this
                }, mix: function (t, e) {
                    var n = t, r = void 0 === e ? .5 : e, i = 2 * r - 1, a = this.alpha() - n.alpha(),
                        o = ((i * a === -1 ? i : (i + a) / (1 + i * a)) + 1) / 2, s = 1 - o;
                    return this.rgb(o * this.red() + s * n.red(), o * this.green() + s * n.green(), o * this.blue() + s * n.blue()).alpha(this.alpha() * r + n.alpha() * (1 - r))
                }, toJSON: function () {
                    return this.rgb()
                }, clone: function () {
                    var t, e, n = new _, r = this.values, i = n.values;
                    for (var a in r) r.hasOwnProperty(a) && (t = r[a], "[object Array]" === (e = {}.toString.call(t)) ? i[a] = t.slice(0) : "[object Number]" === e ? i[a] = t : console.error("unexpected color value:", t));
                    return n
                }
            }).spaces = {
                rgb: ["red", "green", "blue"],
                hsl: ["hue", "saturation", "lightness"],
                hsv: ["hue", "saturation", "value"],
                hwb: ["hue", "whiteness", "blackness"],
                cmyk: ["cyan", "magenta", "yellow", "black"]
            }, _.prototype.maxes = {
                rgb: [255, 255, 255],
                hsl: [360, 100, 100],
                hsv: [360, 100, 100],
                hwb: [360, 100, 100],
                cmyk: [100, 100, 100, 100]
            }, _.prototype.getValues = function (t) {
                for (var e = this.values, n = {}, r = 0; r < t.length; r++) n[t.charAt(r)] = e[t][r];
                return 1 !== e.alpha && (n.a = e.alpha), n
            }, _.prototype.setValues = function (t, e) {
                var n, r, i = this.values, a = this.spaces, o = this.maxes, s = 1;
                if (this.valid = !0, "alpha" === t) s = e; else if (e.length) i[t] = e.slice(0, t.length), s = e[t.length]; else if (void 0 !== e[t.charAt(0)]) {
                    for (n = 0; n < t.length; n++) i[t][n] = e[t.charAt(n)];
                    s = e.a
                } else if (void 0 !== e[a[t][0]]) {
                    var u = a[t];
                    for (n = 0; n < t.length; n++) i[t][n] = e[u[n]];
                    s = e.alpha
                }
                if (i.alpha = Math.max(0, Math.min(1, void 0 === s ? i.alpha : s)), "alpha" === t) return !1;
                for (n = 0; n < t.length; n++) r = Math.max(0, Math.min(o[t][n], i[t][n])), i[t][n] = Math.round(r);
                for (var c in a) c !== t && (i[c] = l[t][c](i[t]));
                return !0
            }, _.prototype.setSpace = function (t, e) {
                var n = e[0];
                return void 0 === n ? this.getValues(t) : ("number" === typeof n && (n = Array.prototype.slice.call(e)), this.setValues(t, n), this)
            }, _.prototype.setChannel = function (t, e, n) {
                var r = this.values[t];
                return void 0 === n ? r[e] : n === r[e] ? this : (r[e] = n, this.setValues(t, r), this)
            }, "undefined" !== typeof window && (window.Color = _);
            var w = _, k = {
                noop: function () {
                }, uid: function () {
                    var t = 0;
                    return function () {
                        return t++
                    }
                }(), isNullOrUndef: function (t) {
                    return null === t || "undefined" === typeof t
                }, isArray: function (t) {
                    if (Array.isArray && Array.isArray(t)) return !0;
                    var e = Object.prototype.toString.call(t);
                    return "[object" === e.substr(0, 7) && "Array]" === e.substr(-6)
                }, isObject: function (t) {
                    return null !== t && "[object Object]" === Object.prototype.toString.call(t)
                }, isFinite: function (t) {
                    function e(e) {
                        return t.apply(this, arguments)
                    }

                    return e.toString = function () {
                        return t.toString()
                    }, e
                }(function (t) {
                    return ("number" === typeof t || t instanceof Number) && isFinite(t)
                }), valueOrDefault: function (t, e) {
                    return "undefined" === typeof t ? e : t
                }, valueAtIndexOrDefault: function (t, e, n) {
                    return k.valueOrDefault(k.isArray(t) ? t[e] : t, n)
                }, callback: function (t, e, n) {
                    if (t && "function" === typeof t.call) return t.apply(n, e)
                }, each: function (t, e, n, r) {
                    var i, a, o;
                    if (k.isArray(t)) if (a = t.length, r) for (i = a - 1; i >= 0; i--) e.call(n, t[i], i); else for (i = 0; i < a; i++) e.call(n, t[i], i); else if (k.isObject(t)) for (o = Object.keys(t), a = o.length, i = 0; i < a; i++) e.call(n, t[o[i]], o[i])
                }, arrayEquals: function (t, e) {
                    var n, r, i, a;
                    if (!t || !e || t.length !== e.length) return !1;
                    for (n = 0, r = t.length; n < r; ++n) if (i = t[n], a = e[n], i instanceof Array && a instanceof Array) {
                        if (!k.arrayEquals(i, a)) return !1
                    } else if (i !== a) return !1;
                    return !0
                }, clone: function (t) {
                    if (k.isArray(t)) return t.map(k.clone);
                    if (k.isObject(t)) {
                        for (var e = {}, n = Object.keys(t), r = n.length, i = 0; i < r; ++i) e[n[i]] = k.clone(t[n[i]]);
                        return e
                    }
                    return t
                }, _merger: function (t, e, n, r) {
                    var i = e[t], a = n[t];
                    k.isObject(i) && k.isObject(a) ? k.merge(i, a, r) : e[t] = k.clone(a)
                }, _mergerIf: function (t, e, n) {
                    var r = e[t], i = n[t];
                    k.isObject(r) && k.isObject(i) ? k.mergeIf(r, i) : e.hasOwnProperty(t) || (e[t] = k.clone(i))
                }, merge: function (t, e, n) {
                    var r, i, a, o, s, l = k.isArray(e) ? e : [e], u = l.length;
                    if (!k.isObject(t)) return t;
                    for (r = (n = n || {}).merger || k._merger, i = 0; i < u; ++i) if (e = l[i], k.isObject(e)) for (a = Object.keys(e), s = 0, o = a.length; s < o; ++s) r(a[s], t, e, n);
                    return t
                }, mergeIf: function (t, e) {
                    return k.merge(t, e, {merger: k._mergerIf})
                }, extend: Object.assign || function (t) {
                    return k.merge(t, [].slice.call(arguments, 1), {
                        merger: function (t, e, n) {
                            e[t] = n[t]
                        }
                    })
                }, inherits: function (t) {
                    var e = this, n = t && t.hasOwnProperty("constructor") ? t.constructor : function () {
                        return e.apply(this, arguments)
                    }, r = function () {
                        this.constructor = n
                    };
                    return r.prototype = e.prototype, n.prototype = new r, n.extend = k.inherits, t && k.extend(n.prototype, t), n.__super__ = e.prototype, n
                }, _deprecated: function (t, e, n, r) {
                    void 0 !== e && console.warn(t + ': "' + n + '" is deprecated. Please use "' + r + '" instead')
                }
            }, M = k;
            k.callCallback = k.callback, k.indexOf = function (t, e, n) {
                return Array.prototype.indexOf.call(t, e, n)
            }, k.getValueOrDefault = k.valueOrDefault, k.getValueAtIndexOrDefault = k.valueAtIndexOrDefault;
            var F = {
                linear: function (t) {
                    return t
                }, easeInQuad: function (t) {
                    return t * t
                }, easeOutQuad: function (t) {
                    return -t * (t - 2)
                }, easeInOutQuad: function (t) {
                    return (t /= .5) < 1 ? .5 * t * t : -.5 * (--t * (t - 2) - 1)
                }, easeInCubic: function (t) {
                    return t * t * t
                }, easeOutCubic: function (t) {
                    return (t -= 1) * t * t + 1
                }, easeInOutCubic: function (t) {
                    return (t /= .5) < 1 ? .5 * t * t * t : .5 * ((t -= 2) * t * t + 2)
                }, easeInQuart: function (t) {
                    return t * t * t * t
                }, easeOutQuart: function (t) {
                    return -((t -= 1) * t * t * t - 1)
                }, easeInOutQuart: function (t) {
                    return (t /= .5) < 1 ? .5 * t * t * t * t : -.5 * ((t -= 2) * t * t * t - 2)
                }, easeInQuint: function (t) {
                    return t * t * t * t * t
                }, easeOutQuint: function (t) {
                    return (t -= 1) * t * t * t * t + 1
                }, easeInOutQuint: function (t) {
                    return (t /= .5) < 1 ? .5 * t * t * t * t * t : .5 * ((t -= 2) * t * t * t * t + 2)
                }, easeInSine: function (t) {
                    return 1 - Math.cos(t * (Math.PI / 2))
                }, easeOutSine: function (t) {
                    return Math.sin(t * (Math.PI / 2))
                }, easeInOutSine: function (t) {
                    return -.5 * (Math.cos(Math.PI * t) - 1)
                }, easeInExpo: function (t) {
                    return 0 === t ? 0 : Math.pow(2, 10 * (t - 1))
                }, easeOutExpo: function (t) {
                    return 1 === t ? 1 : 1 - Math.pow(2, -10 * t)
                }, easeInOutExpo: function (t) {
                    return 0 === t ? 0 : 1 === t ? 1 : (t /= .5) < 1 ? .5 * Math.pow(2, 10 * (t - 1)) : .5 * (2 - Math.pow(2, -10 * --t))
                }, easeInCirc: function (t) {
                    return t >= 1 ? t : -(Math.sqrt(1 - t * t) - 1)
                }, easeOutCirc: function (t) {
                    return Math.sqrt(1 - (t -= 1) * t)
                }, easeInOutCirc: function (t) {
                    return (t /= .5) < 1 ? -.5 * (Math.sqrt(1 - t * t) - 1) : .5 * (Math.sqrt(1 - (t -= 2) * t) + 1)
                }, easeInElastic: function (t) {
                    var e = 1.70158, n = 0, r = 1;
                    return 0 === t ? 0 : 1 === t ? 1 : (n || (n = .3), r < 1 ? (r = 1, e = n / 4) : e = n / (2 * Math.PI) * Math.asin(1 / r), -r * Math.pow(2, 10 * (t -= 1)) * Math.sin((t - e) * (2 * Math.PI) / n))
                }, easeOutElastic: function (t) {
                    var e = 1.70158, n = 0, r = 1;
                    return 0 === t ? 0 : 1 === t ? 1 : (n || (n = .3), r < 1 ? (r = 1, e = n / 4) : e = n / (2 * Math.PI) * Math.asin(1 / r), r * Math.pow(2, -10 * t) * Math.sin((t - e) * (2 * Math.PI) / n) + 1)
                }, easeInOutElastic: function (t) {
                    var e = 1.70158, n = 0, r = 1;
                    return 0 === t ? 0 : 2 === (t /= .5) ? 1 : (n || (n = .45), r < 1 ? (r = 1, e = n / 4) : e = n / (2 * Math.PI) * Math.asin(1 / r), t < 1 ? r * Math.pow(2, 10 * (t -= 1)) * Math.sin((t - e) * (2 * Math.PI) / n) * -.5 : r * Math.pow(2, -10 * (t -= 1)) * Math.sin((t - e) * (2 * Math.PI) / n) * .5 + 1)
                }, easeInBack: function (t) {
                    var e = 1.70158;
                    return t * t * ((e + 1) * t - e)
                }, easeOutBack: function (t) {
                    var e = 1.70158;
                    return (t -= 1) * t * ((e + 1) * t + e) + 1
                }, easeInOutBack: function (t) {
                    var e = 1.70158;
                    return (t /= .5) < 1 ? t * t * ((1 + (e *= 1.525)) * t - e) * .5 : .5 * ((t -= 2) * t * ((1 + (e *= 1.525)) * t + e) + 2)
                }, easeInBounce: function (t) {
                    return 1 - F.easeOutBounce(1 - t)
                }, easeOutBounce: function (t) {
                    return t < 1 / 2.75 ? 7.5625 * t * t : t < 2 / 2.75 ? 7.5625 * (t -= 1.5 / 2.75) * t + .75 : t < 2.5 / 2.75 ? 7.5625 * (t -= 2.25 / 2.75) * t + .9375 : 7.5625 * (t -= 2.625 / 2.75) * t + .984375
                }, easeInOutBounce: function (t) {
                    return t < .5 ? .5 * F.easeInBounce(2 * t) : .5 * F.easeOutBounce(2 * t - 1) + .5
                }
            }, S = {effects: F};
            M.easingEffects = F;
            var C = Math.PI, A = C / 180, T = 2 * C, P = C / 2, D = C / 4, O = 2 * C / 3, E = {
                clear: function (t) {
                    t.ctx.clearRect(0, 0, t.width, t.height)
                }, roundedRect: function (t, e, n, r, i, a) {
                    if (a) {
                        var o = Math.min(a, i / 2, r / 2), s = e + o, l = n + o, u = e + r - o, c = n + i - o;
                        t.moveTo(e, l), s < u && l < c ? (t.arc(s, l, o, -C, -P), t.arc(u, l, o, -P, 0), t.arc(u, c, o, 0, P), t.arc(s, c, o, P, C)) : s < u ? (t.moveTo(s, n), t.arc(u, l, o, -P, P), t.arc(s, l, o, P, C + P)) : l < c ? (t.arc(s, l, o, -C, 0), t.arc(s, c, o, 0, C)) : t.arc(s, l, o, -C, C), t.closePath(), t.moveTo(e, n)
                    } else t.rect(e, n, r, i)
                }, drawPoint: function (t, e, n, r, i, a) {
                    var o, s, l, u, c, d = (a || 0) * A;
                    if (e && "object" === typeof e && ("[object HTMLImageElement]" === (o = e.toString()) || "[object HTMLCanvasElement]" === o)) return t.save(), t.translate(r, i), t.rotate(d), t.drawImage(e, -e.width / 2, -e.height / 2, e.width, e.height), void t.restore();
                    if (!(isNaN(n) || n <= 0)) {
                        switch (t.beginPath(), e) {
                            default:
                                t.arc(r, i, n, 0, T), t.closePath();
                                break;
                            case"triangle":
                                t.moveTo(r + Math.sin(d) * n, i - Math.cos(d) * n), d += O, t.lineTo(r + Math.sin(d) * n, i - Math.cos(d) * n), d += O, t.lineTo(r + Math.sin(d) * n, i - Math.cos(d) * n), t.closePath();
                                break;
                            case"rectRounded":
                                u = n - (c = .516 * n), s = Math.cos(d + D) * u, l = Math.sin(d + D) * u, t.arc(r - s, i - l, c, d - C, d - P), t.arc(r + l, i - s, c, d - P, d), t.arc(r + s, i + l, c, d, d + P), t.arc(r - l, i + s, c, d + P, d + C), t.closePath();
                                break;
                            case"rect":
                                if (!a) {
                                    u = Math.SQRT1_2 * n, t.rect(r - u, i - u, 2 * u, 2 * u);
                                    break
                                }
                                d += D;
                            case"rectRot":
                                s = Math.cos(d) * n, l = Math.sin(d) * n, t.moveTo(r - s, i - l), t.lineTo(r + l, i - s), t.lineTo(r + s, i + l), t.lineTo(r - l, i + s), t.closePath();
                                break;
                            case"crossRot":
                                d += D;
                            case"cross":
                                s = Math.cos(d) * n, l = Math.sin(d) * n, t.moveTo(r - s, i - l), t.lineTo(r + s, i + l), t.moveTo(r + l, i - s), t.lineTo(r - l, i + s);
                                break;
                            case"star":
                                s = Math.cos(d) * n, l = Math.sin(d) * n, t.moveTo(r - s, i - l), t.lineTo(r + s, i + l), t.moveTo(r + l, i - s), t.lineTo(r - l, i + s), d += D, s = Math.cos(d) * n, l = Math.sin(d) * n, t.moveTo(r - s, i - l), t.lineTo(r + s, i + l), t.moveTo(r + l, i - s), t.lineTo(r - l, i + s);
                                break;
                            case"line":
                                s = Math.cos(d) * n, l = Math.sin(d) * n, t.moveTo(r - s, i - l), t.lineTo(r + s, i + l);
                                break;
                            case"dash":
                                t.moveTo(r, i), t.lineTo(r + Math.cos(d) * n, i + Math.sin(d) * n)
                        }
                        t.fill(), t.stroke()
                    }
                }, _isPointInArea: function (t, e) {
                    return t.x > e.left - 1e-6 && t.x < e.right + 1e-6 && t.y > e.top - 1e-6 && t.y < e.bottom + 1e-6
                }, clipArea: function (t, e) {
                    t.save(), t.beginPath(), t.rect(e.left, e.top, e.right - e.left, e.bottom - e.top), t.clip()
                }, unclipArea: function (t) {
                    t.restore()
                }, lineTo: function (t, e, n, r) {
                    var i = n.steppedLine;
                    if (i) {
                        if ("middle" === i) {
                            var a = (e.x + n.x) / 2;
                            t.lineTo(a, r ? n.y : e.y), t.lineTo(a, r ? e.y : n.y)
                        } else "after" === i && !r || "after" !== i && r ? t.lineTo(e.x, n.y) : t.lineTo(n.x, e.y);
                        t.lineTo(n.x, n.y)
                    } else n.tension ? t.bezierCurveTo(r ? e.controlPointPreviousX : e.controlPointNextX, r ? e.controlPointPreviousY : e.controlPointNextY, r ? n.controlPointNextX : n.controlPointPreviousX, r ? n.controlPointNextY : n.controlPointPreviousY, n.x, n.y) : t.lineTo(n.x, n.y)
                }
            }, I = E;
            M.clear = E.clear, M.drawRoundedRectangle = function (t) {
                t.beginPath(), E.roundedRect.apply(E, arguments)
            };
            var R = {
                _set: function (t, e) {
                    return M.merge(this[t] || (this[t] = {}), e)
                }
            };
            R._set("global", {
                defaultColor: "rgba(0,0,0,0.1)",
                defaultFontColor: "#666",
                defaultFontFamily: "'Helvetica Neue', 'Helvetica', 'Arial', sans-serif",
                defaultFontSize: 12,
                defaultFontStyle: "normal",
                defaultLineHeight: 1.2,
                showLines: !0
            });
            var L = R, N = M.valueOrDefault, j = {
                toLineHeight: function (t, e) {
                    var n = ("" + t).match(/^(normal|(\d+(?:\.\d+)?)(px|em|%)?)$/);
                    if (!n || "normal" === n[1]) return 1.2 * e;
                    switch (t = +n[2], n[3]) {
                        case"px":
                            return t;
                        case"%":
                            t /= 100
                    }
                    return e * t
                }, toPadding: function (t) {
                    var e, n, r, i;
                    return M.isObject(t) ? (e = +t.top || 0, n = +t.right || 0, r = +t.bottom || 0, i = +t.left || 0) : e = n = r = i = +t || 0, {
                        top: e,
                        right: n,
                        bottom: r,
                        left: i,
                        height: e + r,
                        width: i + n
                    }
                }, _parseFont: function (t) {
                    var e = L.global, n = N(t.fontSize, e.defaultFontSize), r = {
                        family: N(t.fontFamily, e.defaultFontFamily),
                        lineHeight: M.options.toLineHeight(N(t.lineHeight, e.defaultLineHeight), n),
                        size: n,
                        style: N(t.fontStyle, e.defaultFontStyle),
                        weight: null,
                        string: ""
                    };
                    return r.string = function (t) {
                        return !t || M.isNullOrUndef(t.size) || M.isNullOrUndef(t.family) ? null : (t.style ? t.style + " " : "") + (t.weight ? t.weight + " " : "") + t.size + "px " + t.family
                    }(r), r
                }, resolve: function (t, e, n, r) {
                    var i, a, o, s = !0;
                    for (i = 0, a = t.length; i < a; ++i) if (void 0 !== (o = t[i]) && (void 0 !== e && "function" === typeof o && (o = o(e), s = !1), void 0 !== n && M.isArray(o) && (o = o[n], s = !1), void 0 !== o)) return r && !s && (r.cacheable = !1), o
                }
            }, z = {
                _factorize: function (t) {
                    var e, n = [], r = Math.sqrt(t);
                    for (e = 1; e < r; e++) t % e === 0 && (n.push(e), n.push(t / e));
                    return r === (0 | r) && n.push(r), n.sort(function (t, e) {
                        return t - e
                    }).pop(), n
                }, log10: Math.log10 || function (t) {
                    var e = Math.log(t) * Math.LOG10E, n = Math.round(e), r = t === Math.pow(10, n);
                    return r ? n : e
                }
            }, V = z;
            M.log10 = z.log10;
            var B = M, W = S, H = I, U = j, Y = V, q = {
                getRtlAdapter: function (t, e, n) {
                    return t ? function (t, e) {
                        return {
                            x: function (n) {
                                return t + t + e - n
                            }, setWidth: function (t) {
                                e = t
                            }, textAlign: function (t) {
                                return "center" === t ? t : "right" === t ? "left" : "right"
                            }, xPlus: function (t, e) {
                                return t - e
                            }, leftForLtr: function (t, e) {
                                return t - e
                            }
                        }
                    }(e, n) : {
                        x: function (t) {
                            return t
                        }, setWidth: function (t) {
                        }, textAlign: function (t) {
                            return t
                        }, xPlus: function (t, e) {
                            return t + e
                        }, leftForLtr: function (t, e) {
                            return t
                        }
                    }
                }, overrideTextDirection: function (t, e) {
                    var n, r;
                    "ltr" !== e && "rtl" !== e || (n = t.canvas.style, r = [n.getPropertyValue("direction"), n.getPropertyPriority("direction")], n.setProperty("direction", e, "important"), t.prevTextDirection = r)
                }, restoreTextDirection: function (t) {
                    var e = t.prevTextDirection;
                    void 0 !== e && (delete t.prevTextDirection, t.canvas.style.setProperty("direction", e[0], e[1]))
                }
            };
            B.easing = W, B.canvas = H, B.options = U, B.math = Y, B.rtl = q;
            var $ = function (t) {
                B.extend(this, t), this.initialize.apply(this, arguments)
            };
            B.extend($.prototype, {
                _type: void 0, initialize: function () {
                    this.hidden = !1
                }, pivot: function () {
                    var t = this;
                    return t._view || (t._view = B.extend({}, t._model)), t._start = {}, t
                }, transition: function (t) {
                    var e = this, n = e._model, r = e._start, i = e._view;
                    return n && 1 !== t ? (i || (i = e._view = {}), r || (r = e._start = {}), function (t, e, n, r) {
                        var i, a, o, s, l, u, c, d, h, f = Object.keys(n);
                        for (i = 0, a = f.length; i < a; ++i) if (o = f[i], u = n[o], e.hasOwnProperty(o) || (e[o] = u), (s = e[o]) !== u && "_" !== o[0]) {
                            if (t.hasOwnProperty(o) || (t[o] = s), l = t[o], (c = typeof u) === typeof l) if ("string" === c) {
                                if ((d = w(l)).valid && (h = w(u)).valid) {
                                    e[o] = h.mix(d, r).rgbString();
                                    continue
                                }
                            } else if (B.isFinite(l) && B.isFinite(u)) {
                                e[o] = l + (u - l) * r;
                                continue
                            }
                            e[o] = u
                        }
                    }(r, i, n, t), e) : (e._view = B.extend({}, n), e._start = null, e)
                }, tooltipPosition: function () {
                    return {x: this._model.x, y: this._model.y}
                }, hasValue: function () {
                    return B.isNumber(this._model.x) && B.isNumber(this._model.y)
                }
            }), $.extend = B.inherits;
            var X = $, G = X.extend({
                chart: null,
                currentStep: 0,
                numSteps: 60,
                easing: "",
                render: null,
                onAnimationProgress: null,
                onAnimationComplete: null
            }), K = G;
            Object.defineProperty(G.prototype, "animationObject", {
                get: function () {
                    return this
                }
            }), Object.defineProperty(G.prototype, "chartInstance", {
                get: function () {
                    return this.chart
                }, set: function (t) {
                    this.chart = t
                }
            }), L._set("global", {
                animation: {
                    duration: 1e3,
                    easing: "easeOutQuart",
                    onProgress: B.noop,
                    onComplete: B.noop
                }
            });
            var Z = {
                animations: [], request: null, addAnimation: function (t, e, n, r) {
                    var i, a, o = this.animations;
                    for (e.chart = t, e.startTime = Date.now(), e.duration = n, r || (t.animating = !0), i = 0, a = o.length; i < a; ++i) if (o[i].chart === t) return void (o[i] = e);
                    o.push(e), 1 === o.length && this.requestAnimationFrame()
                }, cancelAnimation: function (t) {
                    var e = B.findIndex(this.animations, function (e) {
                        return e.chart === t
                    });
                    -1 !== e && (this.animations.splice(e, 1), t.animating = !1)
                }, requestAnimationFrame: function () {
                    var t = this;
                    null === t.request && (t.request = B.requestAnimFrame.call(window, function () {
                        t.request = null, t.startDigest()
                    }))
                }, startDigest: function () {
                    this.advance(), this.animations.length > 0 && this.requestAnimationFrame()
                }, advance: function () {
                    for (var t, e, n, r, i = this.animations, a = 0; a < i.length;) t = i[a], e = t.chart, n = t.numSteps, r = Math.floor((Date.now() - t.startTime) / t.duration * n) + 1, t.currentStep = Math.min(r, n), B.callback(t.render, [e, t], e), B.callback(t.onAnimationProgress, [t], e), t.currentStep >= n ? (B.callback(t.onAnimationComplete, [t], e), e.animating = !1, i.splice(a, 1)) : ++a
                }
            }, J = B.options.resolve, Q = ["push", "pop", "shift", "splice", "unshift"];

            function tt(t, e) {
                var n = t._chartjs;
                if (n) {
                    var r = n.listeners, i = r.indexOf(e);
                    -1 !== i && r.splice(i, 1), r.length > 0 || (Q.forEach(function (e) {
                        delete t[e]
                    }), delete t._chartjs)
                }
            }

            var et = function (t, e) {
                this.initialize(t, e)
            };
            B.extend(et.prototype, {
                datasetElementType: null,
                dataElementType: null,
                _datasetElementOptions: ["backgroundColor", "borderCapStyle", "borderColor", "borderDash", "borderDashOffset", "borderJoinStyle", "borderWidth"],
                _dataElementOptions: ["backgroundColor", "borderColor", "borderWidth", "pointStyle"],
                initialize: function (t, e) {
                    var n = this;
                    n.chart = t, n.index = e, n.linkScales(), n.addElements(), n._type = n.getMeta().type
                },
                updateIndex: function (t) {
                    this.index = t
                },
                linkScales: function () {
                    var t = this.getMeta(), e = this.chart, n = e.scales, r = this.getDataset(), i = e.options.scales;
                    null !== t.xAxisID && t.xAxisID in n && !r.xAxisID || (t.xAxisID = r.xAxisID || i.xAxes[0].id), null !== t.yAxisID && t.yAxisID in n && !r.yAxisID || (t.yAxisID = r.yAxisID || i.yAxes[0].id)
                },
                getDataset: function () {
                    return this.chart.data.datasets[this.index]
                },
                getMeta: function () {
                    return this.chart.getDatasetMeta(this.index)
                },
                getScaleForId: function (t) {
                    return this.chart.scales[t]
                },
                _getValueScaleId: function () {
                    return this.getMeta().yAxisID
                },
                _getIndexScaleId: function () {
                    return this.getMeta().xAxisID
                },
                _getValueScale: function () {
                    return this.getScaleForId(this._getValueScaleId())
                },
                _getIndexScale: function () {
                    return this.getScaleForId(this._getIndexScaleId())
                },
                reset: function () {
                    this._update(!0)
                },
                destroy: function () {
                    this._data && tt(this._data, this)
                },
                createMetaDataset: function () {
                    var t = this.datasetElementType;
                    return t && new t({_chart: this.chart, _datasetIndex: this.index})
                },
                createMetaData: function (t) {
                    var e = this.dataElementType;
                    return e && new e({_chart: this.chart, _datasetIndex: this.index, _index: t})
                },
                addElements: function () {
                    var t, e, n = this.getMeta(), r = this.getDataset().data || [], i = n.data;
                    for (t = 0, e = r.length; t < e; ++t) i[t] = i[t] || this.createMetaData(t);
                    n.dataset = n.dataset || this.createMetaDataset()
                },
                addElementAndReset: function (t) {
                    var e = this.createMetaData(t);
                    this.getMeta().data.splice(t, 0, e), this.updateElement(e, t, !0)
                },
                buildOrUpdateElements: function () {
                    var t, e, n = this, r = n.getDataset(), i = r.data || (r.data = []);
                    n._data !== i && (n._data && tt(n._data, n), i && Object.isExtensible(i) && (e = n, (t = i)._chartjs ? t._chartjs.listeners.push(e) : (Object.defineProperty(t, "_chartjs", {
                        configurable: !0,
                        enumerable: !1,
                        value: {listeners: [e]}
                    }), Q.forEach(function (e) {
                        var n = "onData" + e.charAt(0).toUpperCase() + e.slice(1), r = t[e];
                        Object.defineProperty(t, e, {
                            configurable: !0, enumerable: !1, value: function () {
                                var e = Array.prototype.slice.call(arguments), i = r.apply(this, e);
                                return B.each(t._chartjs.listeners, function (t) {
                                    "function" === typeof t[n] && t[n].apply(t, e)
                                }), i
                            }
                        })
                    }))), n._data = i), n.resyncElements()
                },
                _configure: function () {
                    this._config = B.merge({}, [this.chart.options.datasets[this._type], this.getDataset()], {
                        merger: function (t, e, n) {
                            "_meta" !== t && "data" !== t && B._merger(t, e, n)
                        }
                    })
                },
                _update: function (t) {
                    this._configure(), this._cachedDataOpts = null, this.update(t)
                },
                update: B.noop,
                transition: function (t) {
                    for (var e = this.getMeta(), n = e.data || [], r = n.length, i = 0; i < r; ++i) n[i].transition(t);
                    e.dataset && e.dataset.transition(t)
                },
                draw: function () {
                    var t = this.getMeta(), e = t.data || [], n = e.length, r = 0;
                    for (t.dataset && t.dataset.draw(); r < n; ++r) e[r].draw()
                },
                getStyle: function (t) {
                    var e, n = this.getMeta(), r = n.dataset;
                    return this._configure(), r && void 0 === t ? e = this._resolveDatasetElementOptions(r || {}) : (t = t || 0, e = this._resolveDataElementOptions(n.data[t] || {}, t)), !1 !== e.fill && null !== e.fill || (e.backgroundColor = e.borderColor), e
                },
                _resolveDatasetElementOptions: function (t, e) {
                    var n, r, i, a, o = this, s = o.chart, l = o._config, u = t.custom || {},
                        c = s.options.elements[o.datasetElementType.prototype._type] || {},
                        d = o._datasetElementOptions, h = {},
                        f = {chart: s, dataset: o.getDataset(), datasetIndex: o.index, hover: e};
                    for (n = 0, r = d.length; n < r; ++n) i = d[n], a = e ? "hover" + i.charAt(0).toUpperCase() + i.slice(1) : i, h[i] = J([u[a], l[a], c[a]], f);
                    return h
                },
                _resolveDataElementOptions: function (t, e) {
                    var n = this, r = t && t.custom, i = n._cachedDataOpts;
                    if (i && !r) return i;
                    var a, o, s, l, u = n.chart, c = n._config,
                        d = u.options.elements[n.dataElementType.prototype._type] || {}, h = n._dataElementOptions,
                        f = {}, p = {chart: u, dataIndex: e, dataset: n.getDataset(), datasetIndex: n.index},
                        g = {cacheable: !r};
                    if (r = r || {}, B.isArray(h)) for (o = 0, s = h.length; o < s; ++o) l = h[o], f[l] = J([r[l], c[l], d[l]], p, e, g); else for (a = Object.keys(h), o = 0, s = a.length; o < s; ++o) l = a[o], f[l] = J([r[l], c[h[l]], c[l], d[l]], p, e, g);
                    return g.cacheable && (n._cachedDataOpts = Object.freeze(f)), f
                },
                removeHoverStyle: function (t) {
                    B.merge(t._model, t.$previousStyle || {}), delete t.$previousStyle
                },
                setHoverStyle: function (t) {
                    var e = this.chart.data.datasets[t._datasetIndex], n = t._index, r = t.custom || {}, i = t._model,
                        a = B.getHoverColor;
                    t.$previousStyle = {
                        backgroundColor: i.backgroundColor,
                        borderColor: i.borderColor,
                        borderWidth: i.borderWidth
                    }, i.backgroundColor = J([r.hoverBackgroundColor, e.hoverBackgroundColor, a(i.backgroundColor)], void 0, n), i.borderColor = J([r.hoverBorderColor, e.hoverBorderColor, a(i.borderColor)], void 0, n), i.borderWidth = J([r.hoverBorderWidth, e.hoverBorderWidth, i.borderWidth], void 0, n)
                },
                _removeDatasetHoverStyle: function () {
                    var t = this.getMeta().dataset;
                    t && this.removeHoverStyle(t)
                },
                _setDatasetHoverStyle: function () {
                    var t, e, n, r, i, a, o = this.getMeta().dataset, s = {};
                    if (o) {
                        for (a = o._model, i = this._resolveDatasetElementOptions(o, !0), r = Object.keys(i), t = 0, e = r.length; t < e; ++t) n = r[t], s[n] = a[n], a[n] = i[n];
                        o.$previousStyle = s
                    }
                },
                resyncElements: function () {
                    var t = this.getMeta(), e = this.getDataset().data, n = t.data.length, r = e.length;
                    r < n ? t.data.splice(r, n - r) : r > n && this.insertElements(n, r - n)
                },
                insertElements: function (t, e) {
                    for (var n = 0; n < e; ++n) this.addElementAndReset(t + n)
                },
                onDataPush: function () {
                    var t = arguments.length;
                    this.insertElements(this.getDataset().data.length - t, t)
                },
                onDataPop: function () {
                    this.getMeta().data.pop()
                },
                onDataShift: function () {
                    this.getMeta().data.shift()
                },
                onDataSplice: function (t, e) {
                    this.getMeta().data.splice(t, e), this.insertElements(t, arguments.length - 2)
                },
                onDataUnshift: function () {
                    this.insertElements(0, arguments.length)
                }
            }), et.extend = B.inherits;
            var nt = et, rt = 2 * Math.PI;

            function it(t, e) {
                var n = e.startAngle, r = e.endAngle, i = e.pixelMargin, a = i / e.outerRadius, o = e.x, s = e.y;
                t.beginPath(), t.arc(o, s, e.outerRadius, n - a, r + a), e.innerRadius > i ? (a = i / e.innerRadius, t.arc(o, s, e.innerRadius - i, r + a, n - a, !0)) : t.arc(o, s, i, r + Math.PI / 2, n - Math.PI / 2), t.closePath(), t.clip()
            }

            function at(t, e, n) {
                var r = "inner" === e.borderAlign;
                r ? (t.lineWidth = 2 * e.borderWidth, t.lineJoin = "round") : (t.lineWidth = e.borderWidth, t.lineJoin = "bevel"), n.fullCircles && function (t, e, n, r) {
                    var i, a = n.endAngle;
                    for (r && (n.endAngle = n.startAngle + rt, it(t, n), n.endAngle = a, n.endAngle === n.startAngle && n.fullCircles && (n.endAngle += rt, n.fullCircles--)), t.beginPath(), t.arc(n.x, n.y, n.innerRadius, n.startAngle + rt, n.startAngle, !0), i = 0; i < n.fullCircles; ++i) t.stroke();
                    for (t.beginPath(), t.arc(n.x, n.y, e.outerRadius, n.startAngle, n.startAngle + rt), i = 0; i < n.fullCircles; ++i) t.stroke()
                }(t, e, n, r), r && it(t, n), t.beginPath(), t.arc(n.x, n.y, e.outerRadius, n.startAngle, n.endAngle), t.arc(n.x, n.y, n.innerRadius, n.endAngle, n.startAngle, !0), t.closePath(), t.stroke()
            }

            L._set("global", {
                elements: {
                    arc: {
                        backgroundColor: L.global.defaultColor,
                        borderColor: "#fff",
                        borderWidth: 2,
                        borderAlign: "center"
                    }
                }
            });
            var ot = X.extend({
                _type: "arc", inLabelRange: function (t) {
                    var e = this._view;
                    return !!e && Math.pow(t - e.x, 2) < Math.pow(e.radius + e.hoverRadius, 2)
                }, inRange: function (t, e) {
                    var n = this._view;
                    if (n) {
                        for (var r = B.getAngleFromPoint(n, {
                            x: t,
                            y: e
                        }), i = r.angle, a = r.distance, o = n.startAngle, s = n.endAngle; s < o;) s += rt;
                        for (; i > s;) i -= rt;
                        for (; i < o;) i += rt;
                        var l = i >= o && i <= s, u = a >= n.innerRadius && a <= n.outerRadius;
                        return l && u
                    }
                    return !1
                }, getCenterPoint: function () {
                    var t = this._view, e = (t.startAngle + t.endAngle) / 2, n = (t.innerRadius + t.outerRadius) / 2;
                    return {x: t.x + Math.cos(e) * n, y: t.y + Math.sin(e) * n}
                }, getArea: function () {
                    var t = this._view;
                    return Math.PI * ((t.endAngle - t.startAngle) / (2 * Math.PI)) * (Math.pow(t.outerRadius, 2) - Math.pow(t.innerRadius, 2))
                }, tooltipPosition: function () {
                    var t = this._view, e = t.startAngle + (t.endAngle - t.startAngle) / 2,
                        n = (t.outerRadius - t.innerRadius) / 2 + t.innerRadius;
                    return {x: t.x + Math.cos(e) * n, y: t.y + Math.sin(e) * n}
                }, draw: function () {
                    var t, e = this._chart.ctx, n = this._view, r = "inner" === n.borderAlign ? .33 : 0, i = {
                        x: n.x,
                        y: n.y,
                        innerRadius: n.innerRadius,
                        outerRadius: Math.max(n.outerRadius - r, 0),
                        pixelMargin: r,
                        startAngle: n.startAngle,
                        endAngle: n.endAngle,
                        fullCircles: Math.floor(n.circumference / rt)
                    };
                    if (e.save(), e.fillStyle = n.backgroundColor, e.strokeStyle = n.borderColor, i.fullCircles) {
                        for (i.endAngle = i.startAngle + rt, e.beginPath(), e.arc(i.x, i.y, i.outerRadius, i.startAngle, i.endAngle), e.arc(i.x, i.y, i.innerRadius, i.endAngle, i.startAngle, !0), e.closePath(), t = 0; t < i.fullCircles; ++t) e.fill();
                        i.endAngle = i.startAngle + n.circumference % rt
                    }
                    e.beginPath(), e.arc(i.x, i.y, i.outerRadius, i.startAngle, i.endAngle), e.arc(i.x, i.y, i.innerRadius, i.endAngle, i.startAngle, !0), e.closePath(), e.fill(), n.borderWidth && at(e, n, i), e.restore()
                }
            }), st = B.valueOrDefault, lt = L.global.defaultColor;
            L._set("global", {
                elements: {
                    line: {
                        tension: .4,
                        backgroundColor: lt,
                        borderWidth: 3,
                        borderColor: lt,
                        borderCapStyle: "butt",
                        borderDash: [],
                        borderDashOffset: 0,
                        borderJoinStyle: "miter",
                        capBezierPoints: !0,
                        fill: !0
                    }
                }
            });
            var ut = X.extend({
                _type: "line", draw: function () {
                    var t, e, n, r = this, i = r._view, a = r._chart.ctx, o = i.spanGaps, s = r._children.slice(),
                        l = L.global, u = l.elements.line, c = -1, d = r._loop;
                    if (s.length) {
                        if (r._loop) {
                            for (t = 0; t < s.length; ++t) if (e = B.previousItem(s, t), !s[t]._view.skip && e._view.skip) {
                                s = s.slice(t).concat(s.slice(0, t)), d = o;
                                break
                            }
                            d && s.push(s[0])
                        }
                        for (a.save(), a.lineCap = i.borderCapStyle || u.borderCapStyle, a.setLineDash && a.setLineDash(i.borderDash || u.borderDash), a.lineDashOffset = st(i.borderDashOffset, u.borderDashOffset), a.lineJoin = i.borderJoinStyle || u.borderJoinStyle, a.lineWidth = st(i.borderWidth, u.borderWidth), a.strokeStyle = i.borderColor || l.defaultColor, a.beginPath(), (n = s[0]._view).skip || (a.moveTo(n.x, n.y), c = 0), t = 1; t < s.length; ++t) n = s[t]._view, e = -1 === c ? B.previousItem(s, t) : s[c], n.skip || (c !== t - 1 && !o || -1 === c ? a.moveTo(n.x, n.y) : B.canvas.lineTo(a, e._view, n), c = t);
                        d && a.closePath(), a.stroke(), a.restore()
                    }
                }
            }), ct = B.valueOrDefault, dt = L.global.defaultColor;

            function ht(t) {
                var e = this._view;
                return !!e && Math.abs(t - e.x) < e.radius + e.hitRadius
            }

            L._set("global", {
                elements: {
                    point: {
                        radius: 3,
                        pointStyle: "circle",
                        backgroundColor: dt,
                        borderColor: dt,
                        borderWidth: 1,
                        hitRadius: 1,
                        hoverRadius: 4,
                        hoverBorderWidth: 1
                    }
                }
            });
            var ft = X.extend({
                _type: "point", inRange: function (t, e) {
                    var n = this._view;
                    return !!n && Math.pow(t - n.x, 2) + Math.pow(e - n.y, 2) < Math.pow(n.hitRadius + n.radius, 2)
                }, inLabelRange: ht, inXRange: ht, inYRange: function (t) {
                    var e = this._view;
                    return !!e && Math.abs(t - e.y) < e.radius + e.hitRadius
                }, getCenterPoint: function () {
                    var t = this._view;
                    return {x: t.x, y: t.y}
                }, getArea: function () {
                    return Math.PI * Math.pow(this._view.radius, 2)
                }, tooltipPosition: function () {
                    var t = this._view;
                    return {x: t.x, y: t.y, padding: t.radius + t.borderWidth}
                }, draw: function (t) {
                    var e = this._view, n = this._chart.ctx, r = e.pointStyle, i = e.rotation, a = e.radius, o = e.x,
                        s = e.y, l = L.global, u = l.defaultColor;
                    e.skip || (void 0 === t || B.canvas._isPointInArea(e, t)) && (n.strokeStyle = e.borderColor || u, n.lineWidth = ct(e.borderWidth, l.elements.point.borderWidth), n.fillStyle = e.backgroundColor || u, B.canvas.drawPoint(n, r, a, o, s, i))
                }
            }), pt = L.global.defaultColor;

            function gt(t) {
                return t && void 0 !== t.width
            }

            function vt(t) {
                var e, n, r, i, a;
                return gt(t) ? (a = t.width / 2, e = t.x - a, n = t.x + a, r = Math.min(t.y, t.base), i = Math.max(t.y, t.base)) : (a = t.height / 2, e = Math.min(t.x, t.base), n = Math.max(t.x, t.base), r = t.y - a, i = t.y + a), {
                    left: e,
                    top: r,
                    right: n,
                    bottom: i
                }
            }

            function mt(t, e, n) {
                return t === e ? n : t === n ? e : t
            }

            function bt(t, e, n) {
                var r, i, a, o, s = t.borderWidth, l = function (t) {
                    var e = t.borderSkipped, n = {};
                    return e ? (t.horizontal ? t.base > t.x && (e = mt(e, "left", "right")) : t.base < t.y && (e = mt(e, "bottom", "top")), n[e] = !0, n) : n
                }(t);
                return B.isObject(s) ? (r = +s.top || 0, i = +s.right || 0, a = +s.bottom || 0, o = +s.left || 0) : r = i = a = o = +s || 0, {
                    t: l.top || r < 0 ? 0 : r > n ? n : r,
                    r: l.right || i < 0 ? 0 : i > e ? e : i,
                    b: l.bottom || a < 0 ? 0 : a > n ? n : a,
                    l: l.left || o < 0 ? 0 : o > e ? e : o
                }
            }

            function yt(t, e, n) {
                var r = null === e, i = null === n, a = !(!t || r && i) && vt(t);
                return a && (r || e >= a.left && e <= a.right) && (i || n >= a.top && n <= a.bottom)
            }

            L._set("global", {
                elements: {
                    rectangle: {
                        backgroundColor: pt,
                        borderColor: pt,
                        borderSkipped: "bottom",
                        borderWidth: 0
                    }
                }
            });
            var xt = X.extend({
                _type: "rectangle", draw: function () {
                    var t = this._chart.ctx, e = this._view, n = function (t) {
                        var e = vt(t), n = e.right - e.left, r = e.bottom - e.top, i = bt(t, n / 2, r / 2);
                        return {
                            outer: {x: e.left, y: e.top, w: n, h: r},
                            inner: {x: e.left + i.l, y: e.top + i.t, w: n - i.l - i.r, h: r - i.t - i.b}
                        }
                    }(e), r = n.outer, i = n.inner;
                    t.fillStyle = e.backgroundColor, t.fillRect(r.x, r.y, r.w, r.h), r.w === i.w && r.h === i.h || (t.save(), t.beginPath(), t.rect(r.x, r.y, r.w, r.h), t.clip(), t.fillStyle = e.borderColor, t.rect(i.x, i.y, i.w, i.h), t.fill("evenodd"), t.restore())
                }, height: function () {
                    var t = this._view;
                    return t.base - t.y
                }, inRange: function (t, e) {
                    return yt(this._view, t, e)
                }, inLabelRange: function (t, e) {
                    var n = this._view;
                    return gt(n) ? yt(n, t, null) : yt(n, null, e)
                }, inXRange: function (t) {
                    return yt(this._view, t, null)
                }, inYRange: function (t) {
                    return yt(this._view, null, t)
                }, getCenterPoint: function () {
                    var t, e, n = this._view;
                    return gt(n) ? (t = n.x, e = (n.y + n.base) / 2) : (t = (n.x + n.base) / 2, e = n.y), {x: t, y: e}
                }, getArea: function () {
                    var t = this._view;
                    return gt(t) ? t.width * Math.abs(t.y - t.base) : t.height * Math.abs(t.x - t.base)
                }, tooltipPosition: function () {
                    var t = this._view;
                    return {x: t.x, y: t.y}
                }
            }), _t = {}, wt = ot, kt = ut, Mt = ft, Ft = xt;
            _t.Arc = wt, _t.Line = kt, _t.Point = Mt, _t.Rectangle = Ft;
            var St = B._deprecated, Ct = B.valueOrDefault;

            function At(t, e, n) {
                var r, i, a = n.barThickness, o = e.stackCount, s = e.pixels[t],
                    l = B.isNullOrUndef(a) ? function (t, e) {
                        var n, r, i, a, o = t._length;
                        for (i = 1, a = e.length; i < a; ++i) o = Math.min(o, Math.abs(e[i] - e[i - 1]));
                        for (i = 0, a = t.getTicks().length; i < a; ++i) r = t.getPixelForTick(i), o = i > 0 ? Math.min(o, Math.abs(r - n)) : o, n = r;
                        return o
                    }(e.scale, e.pixels) : -1;
                return B.isNullOrUndef(a) ? (r = l * n.categoryPercentage, i = n.barPercentage) : (r = a * o, i = 1), {
                    chunk: r / o,
                    ratio: i,
                    start: s - r / 2
                }
            }

            L._set("bar", {
                hover: {mode: "label"},
                scales: {
                    xAxes: [{type: "category", offset: !0, gridLines: {offsetGridLines: !0}}],
                    yAxes: [{type: "linear"}]
                }
            }), L._set("global", {datasets: {bar: {categoryPercentage: .8, barPercentage: .9}}});
            var Tt = nt.extend({
                dataElementType: _t.Rectangle,
                _dataElementOptions: ["backgroundColor", "borderColor", "borderSkipped", "borderWidth", "barPercentage", "barThickness", "categoryPercentage", "maxBarThickness", "minBarLength"],
                initialize: function () {
                    var t, e, n = this;
                    nt.prototype.initialize.apply(n, arguments), (t = n.getMeta()).stack = n.getDataset().stack, t.bar = !0, e = n._getIndexScale().options, St("bar chart", e.barPercentage, "scales.[x/y]Axes.barPercentage", "dataset.barPercentage"), St("bar chart", e.barThickness, "scales.[x/y]Axes.barThickness", "dataset.barThickness"), St("bar chart", e.categoryPercentage, "scales.[x/y]Axes.categoryPercentage", "dataset.categoryPercentage"), St("bar chart", n._getValueScale().options.minBarLength, "scales.[x/y]Axes.minBarLength", "dataset.minBarLength"), St("bar chart", e.maxBarThickness, "scales.[x/y]Axes.maxBarThickness", "dataset.maxBarThickness")
                },
                update: function (t) {
                    var e, n, r = this.getMeta().data;
                    for (this._ruler = this.getRuler(), e = 0, n = r.length; e < n; ++e) this.updateElement(r[e], e, t)
                },
                updateElement: function (t, e, n) {
                    var r = this, i = r.getMeta(), a = r.getDataset(), o = r._resolveDataElementOptions(t, e);
                    t._xScale = r.getScaleForId(i.xAxisID), t._yScale = r.getScaleForId(i.yAxisID), t._datasetIndex = r.index, t._index = e, t._model = {
                        backgroundColor: o.backgroundColor,
                        borderColor: o.borderColor,
                        borderSkipped: o.borderSkipped,
                        borderWidth: o.borderWidth,
                        datasetLabel: a.label,
                        label: r.chart.data.labels[e]
                    }, B.isArray(a.data[e]) && (t._model.borderSkipped = null), r._updateElementGeometry(t, e, n, o), t.pivot()
                },
                _updateElementGeometry: function (t, e, n, r) {
                    var i = this, a = t._model, o = i._getValueScale(), s = o.getBasePixel(), l = o.isHorizontal(),
                        u = i._ruler || i.getRuler(), c = i.calculateBarValuePixels(i.index, e, r),
                        d = i.calculateBarIndexPixels(i.index, e, u, r);
                    a.horizontal = l, a.base = n ? s : c.base, a.x = l ? n ? s : c.head : d.center, a.y = l ? d.center : n ? s : c.head, a.height = l ? d.size : void 0, a.width = l ? void 0 : d.size
                },
                _getStacks: function (t) {
                    var e, n, r = this._getIndexScale(), i = r._getMatchingVisibleMetas(this._type),
                        a = r.options.stacked, o = i.length, s = [];
                    for (e = 0; e < o && (n = i[e], (!1 === a || -1 === s.indexOf(n.stack) || void 0 === a && void 0 === n.stack) && s.push(n.stack), n.index !== t); ++e) ;
                    return s
                },
                getStackCount: function () {
                    return this._getStacks().length
                },
                getStackIndex: function (t, e) {
                    var n = this._getStacks(t), r = void 0 !== e ? n.indexOf(e) : -1;
                    return -1 === r ? n.length - 1 : r
                },
                getRuler: function () {
                    var t, e, n = this._getIndexScale(), r = [];
                    for (t = 0, e = this.getMeta().data.length; t < e; ++t) r.push(n.getPixelForValue(null, t, this.index));
                    return {
                        pixels: r,
                        start: n._startPixel,
                        end: n._endPixel,
                        stackCount: this.getStackCount(),
                        scale: n
                    }
                },
                calculateBarValuePixels: function (t, e, n) {
                    var r, i, a, o, s, l, u, c = this.chart, d = this._getValueScale(), h = d.isHorizontal(),
                        f = c.data.datasets, p = d._getMatchingVisibleMetas(this._type),
                        g = d._parseValue(f[t].data[e]), v = n.minBarLength, m = d.options.stacked,
                        b = this.getMeta().stack, y = void 0 === g.start ? 0 : g.max >= 0 && g.min >= 0 ? g.min : g.max,
                        x = void 0 === g.start ? g.end : g.max >= 0 && g.min >= 0 ? g.max - g.min : g.min - g.max,
                        _ = p.length;
                    if (m || void 0 === m && void 0 !== b) for (r = 0; r < _ && (i = p[r]).index !== t; ++r) i.stack === b && (u = d._parseValue(f[i.index].data[e]), a = void 0 === u.start ? u.end : u.min >= 0 && u.max >= 0 ? u.max : u.min, (g.min < 0 && a < 0 || g.max >= 0 && a > 0) && (y += a));
                    return o = d.getPixelForValue(y), s = d.getPixelForValue(y + x), l = s - o, void 0 !== v && Math.abs(l) < v && (l = v, s = x >= 0 && !h || x < 0 && h ? o - v : o + v), {
                        size: l,
                        base: o,
                        head: s,
                        center: s + l / 2
                    }
                },
                calculateBarIndexPixels: function (t, e, n, r) {
                    var i = "flex" === r.barThickness ? function (t, e, n) {
                            var r, i = e.pixels, a = i[t], o = t > 0 ? i[t - 1] : null,
                                s = t < i.length - 1 ? i[t + 1] : null, l = n.categoryPercentage;
                            return null === o && (o = a - (null === s ? e.end - e.start : s - a)), null === s && (s = a + a - o), r = a - (a - Math.min(o, s)) / 2 * l, {
                                chunk: Math.abs(s - o) / 2 * l / e.stackCount,
                                ratio: n.barPercentage,
                                start: r
                            }
                        }(e, n, r) : At(e, n, r), a = this.getStackIndex(t, this.getMeta().stack),
                        o = i.start + i.chunk * a + i.chunk / 2,
                        s = Math.min(Ct(r.maxBarThickness, 1 / 0), i.chunk * i.ratio);
                    return {base: o - s / 2, head: o + s / 2, center: o, size: s}
                },
                draw: function () {
                    var t = this.chart, e = this._getValueScale(), n = this.getMeta().data, r = this.getDataset(),
                        i = n.length, a = 0;
                    for (B.canvas.clipArea(t.ctx, t.chartArea); a < i; ++a) {
                        var o = e._parseValue(r.data[a]);
                        isNaN(o.min) || isNaN(o.max) || n[a].draw()
                    }
                    B.canvas.unclipArea(t.ctx)
                },
                _resolveDataElementOptions: function () {
                    var t = B.extend({}, nt.prototype._resolveDataElementOptions.apply(this, arguments)),
                        e = this._getIndexScale().options, n = this._getValueScale().options;
                    return t.barPercentage = Ct(e.barPercentage, t.barPercentage), t.barThickness = Ct(e.barThickness, t.barThickness), t.categoryPercentage = Ct(e.categoryPercentage, t.categoryPercentage), t.maxBarThickness = Ct(e.maxBarThickness, t.maxBarThickness), t.minBarLength = Ct(n.minBarLength, t.minBarLength), t
                }
            }), Pt = B.valueOrDefault, Dt = B.options.resolve;
            L._set("bubble", {
                hover: {mode: "single"},
                scales: {
                    xAxes: [{type: "linear", position: "bottom", id: "x-axis-0"}],
                    yAxes: [{type: "linear", position: "left", id: "y-axis-0"}]
                },
                tooltips: {
                    callbacks: {
                        title: function () {
                            return ""
                        }, label: function (t, e) {
                            var n = e.datasets[t.datasetIndex].label || "",
                                r = e.datasets[t.datasetIndex].data[t.index];
                            return n + ": (" + t.xLabel + ", " + t.yLabel + ", " + r.r + ")"
                        }
                    }
                }
            });
            var Ot = nt.extend({
                dataElementType: _t.Point,
                _dataElementOptions: ["backgroundColor", "borderColor", "borderWidth", "hoverBackgroundColor", "hoverBorderColor", "hoverBorderWidth", "hoverRadius", "hitRadius", "pointStyle", "rotation"],
                update: function (t) {
                    var e = this, n = e.getMeta(), r = n.data;
                    B.each(r, function (n, r) {
                        e.updateElement(n, r, t)
                    })
                },
                updateElement: function (t, e, n) {
                    var r = this, i = r.getMeta(), a = t.custom || {}, o = r.getScaleForId(i.xAxisID),
                        s = r.getScaleForId(i.yAxisID), l = r._resolveDataElementOptions(t, e),
                        u = r.getDataset().data[e], c = r.index,
                        d = n ? o.getPixelForDecimal(.5) : o.getPixelForValue("object" === typeof u ? u : NaN, e, c),
                        h = n ? s.getBasePixel() : s.getPixelForValue(u, e, c);
                    t._xScale = o, t._yScale = s, t._options = l, t._datasetIndex = c, t._index = e, t._model = {
                        backgroundColor: l.backgroundColor,
                        borderColor: l.borderColor,
                        borderWidth: l.borderWidth,
                        hitRadius: l.hitRadius,
                        pointStyle: l.pointStyle,
                        rotation: l.rotation,
                        radius: n ? 0 : l.radius,
                        skip: a.skip || isNaN(d) || isNaN(h),
                        x: d,
                        y: h
                    }, t.pivot()
                },
                setHoverStyle: function (t) {
                    var e = t._model, n = t._options, r = B.getHoverColor;
                    t.$previousStyle = {
                        backgroundColor: e.backgroundColor,
                        borderColor: e.borderColor,
                        borderWidth: e.borderWidth,
                        radius: e.radius
                    }, e.backgroundColor = Pt(n.hoverBackgroundColor, r(n.backgroundColor)), e.borderColor = Pt(n.hoverBorderColor, r(n.borderColor)), e.borderWidth = Pt(n.hoverBorderWidth, n.borderWidth), e.radius = n.radius + n.hoverRadius
                },
                _resolveDataElementOptions: function (t, e) {
                    var n = this, r = n.chart, i = n.getDataset(), a = t.custom || {}, o = i.data[e] || {},
                        s = nt.prototype._resolveDataElementOptions.apply(n, arguments),
                        l = {chart: r, dataIndex: e, dataset: i, datasetIndex: n.index};
                    return n._cachedDataOpts === s && (s = B.extend({}, s)), s.radius = Dt([a.radius, o.r, n._config.radius, r.options.elements.point.radius], l, e), s
                }
            }), Et = B.valueOrDefault, It = Math.PI, Rt = 2 * It, Lt = It / 2;
            L._set("doughnut", {
                animation: {animateRotate: !0, animateScale: !1},
                hover: {mode: "single"},
                legendCallback: function (t) {
                    var e, n, r, i = document.createElement("ul"), a = t.data, o = a.datasets, s = a.labels;
                    if (i.setAttribute("class", t.id + "-legend"), o.length) for (e = 0, n = o[0].data.length; e < n; ++e) (r = i.appendChild(document.createElement("li"))).appendChild(document.createElement("span")).style.backgroundColor = o[0].backgroundColor[e], s[e] && r.appendChild(document.createTextNode(s[e]));
                    return i.outerHTML
                },
                legend: {
                    labels: {
                        generateLabels: function (t) {
                            var e = t.data;
                            return e.labels.length && e.datasets.length ? e.labels.map(function (n, r) {
                                var i = t.getDatasetMeta(0), a = i.controller.getStyle(r);
                                return {
                                    text: n,
                                    fillStyle: a.backgroundColor,
                                    strokeStyle: a.borderColor,
                                    lineWidth: a.borderWidth,
                                    hidden: isNaN(e.datasets[0].data[r]) || i.data[r].hidden,
                                    index: r
                                }
                            }) : []
                        }
                    }, onClick: function (t, e) {
                        var n, r, i, a = e.index, o = this.chart;
                        for (n = 0, r = (o.data.datasets || []).length; n < r; ++n) (i = o.getDatasetMeta(n)).data[a] && (i.data[a].hidden = !i.data[a].hidden);
                        o.update()
                    }
                },
                cutoutPercentage: 50,
                rotation: -Lt,
                circumference: Rt,
                tooltips: {
                    callbacks: {
                        title: function () {
                            return ""
                        }, label: function (t, e) {
                            var n = e.labels[t.index], r = ": " + e.datasets[t.datasetIndex].data[t.index];
                            return B.isArray(n) ? (n = n.slice())[0] += r : n += r, n
                        }
                    }
                }
            });
            var Nt = nt.extend({
                dataElementType: _t.Arc,
                linkScales: B.noop,
                _dataElementOptions: ["backgroundColor", "borderColor", "borderWidth", "borderAlign", "hoverBackgroundColor", "hoverBorderColor", "hoverBorderWidth"],
                getRingIndex: function (t) {
                    for (var e = 0, n = 0; n < t; ++n) this.chart.isDatasetVisible(n) && ++e;
                    return e
                },
                update: function (t) {
                    var e, n, r, i, a = this, o = a.chart, s = o.chartArea, l = o.options, u = 1, c = 1, d = 0, h = 0,
                        f = a.getMeta(), p = f.data, g = l.cutoutPercentage / 100 || 0, v = l.circumference,
                        m = a._getRingWeight(a.index);
                    if (v < Rt) {
                        var b = l.rotation % Rt, y = (b += b >= It ? -Rt : b < -It ? Rt : 0) + v, x = Math.cos(b),
                            _ = Math.sin(b), w = Math.cos(y), k = Math.sin(y), M = b <= 0 && y >= 0 || y >= Rt,
                            F = b <= Lt && y >= Lt || y >= Rt + Lt, S = b === -It || y >= It,
                            C = b <= -Lt && y >= -Lt || y >= It + Lt, A = S ? -1 : Math.min(x, x * g, w, w * g),
                            T = C ? -1 : Math.min(_, _ * g, k, k * g), P = M ? 1 : Math.max(x, x * g, w, w * g),
                            D = F ? 1 : Math.max(_, _ * g, k, k * g);
                        u = (P - A) / 2, c = (D - T) / 2, d = -(P + A) / 2, h = -(D + T) / 2
                    }
                    for (r = 0, i = p.length; r < i; ++r) p[r]._options = a._resolveDataElementOptions(p[r], r);
                    for (o.borderWidth = a.getMaxBorderWidth(), e = (s.right - s.left - o.borderWidth) / u, n = (s.bottom - s.top - o.borderWidth) / c, o.outerRadius = Math.max(Math.min(e, n) / 2, 0), o.innerRadius = Math.max(o.outerRadius * g, 0), o.radiusLength = (o.outerRadius - o.innerRadius) / (a._getVisibleDatasetWeightTotal() || 1), o.offsetX = d * o.outerRadius, o.offsetY = h * o.outerRadius, f.total = a.calculateTotal(), a.outerRadius = o.outerRadius - o.radiusLength * a._getRingWeightOffset(a.index), a.innerRadius = Math.max(a.outerRadius - o.radiusLength * m, 0), r = 0, i = p.length; r < i; ++r) a.updateElement(p[r], r, t)
                },
                updateElement: function (t, e, n) {
                    var r = this, i = r.chart, a = i.chartArea, o = i.options, s = o.animation,
                        l = (a.left + a.right) / 2, u = (a.top + a.bottom) / 2, c = o.rotation, d = o.rotation,
                        h = r.getDataset(),
                        f = n && s.animateRotate ? 0 : t.hidden ? 0 : r.calculateCircumference(h.data[e]) * (o.circumference / Rt),
                        p = n && s.animateScale ? 0 : r.innerRadius, g = n && s.animateScale ? 0 : r.outerRadius,
                        v = t._options || {};
                    B.extend(t, {
                        _datasetIndex: r.index,
                        _index: e,
                        _model: {
                            backgroundColor: v.backgroundColor,
                            borderColor: v.borderColor,
                            borderWidth: v.borderWidth,
                            borderAlign: v.borderAlign,
                            x: l + i.offsetX,
                            y: u + i.offsetY,
                            startAngle: c,
                            endAngle: d,
                            circumference: f,
                            outerRadius: g,
                            innerRadius: p,
                            label: B.valueAtIndexOrDefault(h.label, e, i.data.labels[e])
                        }
                    });
                    var m = t._model;
                    n && s.animateRotate || (m.startAngle = 0 === e ? o.rotation : r.getMeta().data[e - 1]._model.endAngle, m.endAngle = m.startAngle + m.circumference), t.pivot()
                },
                calculateTotal: function () {
                    var t, e = this.getDataset(), n = this.getMeta(), r = 0;
                    return B.each(n.data, function (n, i) {
                        t = e.data[i], isNaN(t) || n.hidden || (r += Math.abs(t))
                    }), r
                },
                calculateCircumference: function (t) {
                    var e = this.getMeta().total;
                    return e > 0 && !isNaN(t) ? Rt * (Math.abs(t) / e) : 0
                },
                getMaxBorderWidth: function (t) {
                    var e, n, r, i, a, o, s, l, u = 0, c = this.chart;
                    if (!t) for (e = 0, n = c.data.datasets.length; e < n; ++e) if (c.isDatasetVisible(e)) {
                        r = c.getDatasetMeta(e), t = r.data, e !== this.index && (a = r.controller);
                        break
                    }
                    if (!t) return 0;
                    for (e = 0, n = t.length; e < n; ++e) i = t[e], a ? (a._configure(), o = a._resolveDataElementOptions(i, e)) : o = i._options, "inner" !== o.borderAlign && (s = o.borderWidth, l = o.hoverBorderWidth, u = l > (u = s > u ? s : u) ? l : u);
                    return u
                },
                setHoverStyle: function (t) {
                    var e = t._model, n = t._options, r = B.getHoverColor;
                    t.$previousStyle = {
                        backgroundColor: e.backgroundColor,
                        borderColor: e.borderColor,
                        borderWidth: e.borderWidth
                    }, e.backgroundColor = Et(n.hoverBackgroundColor, r(n.backgroundColor)), e.borderColor = Et(n.hoverBorderColor, r(n.borderColor)), e.borderWidth = Et(n.hoverBorderWidth, n.borderWidth)
                },
                _getRingWeightOffset: function (t) {
                    for (var e = 0, n = 0; n < t; ++n) this.chart.isDatasetVisible(n) && (e += this._getRingWeight(n));
                    return e
                },
                _getRingWeight: function (t) {
                    return Math.max(Et(this.chart.data.datasets[t].weight, 1), 0)
                },
                _getVisibleDatasetWeightTotal: function () {
                    return this._getRingWeightOffset(this.chart.data.datasets.length)
                }
            });
            L._set("horizontalBar", {
                hover: {mode: "index", axis: "y"},
                scales: {
                    xAxes: [{type: "linear", position: "bottom"}],
                    yAxes: [{type: "category", position: "left", offset: !0, gridLines: {offsetGridLines: !0}}]
                },
                elements: {rectangle: {borderSkipped: "left"}},
                tooltips: {mode: "index", axis: "y"}
            }), L._set("global", {datasets: {horizontalBar: {categoryPercentage: .8, barPercentage: .9}}});
            var jt = Tt.extend({
                _getValueScaleId: function () {
                    return this.getMeta().xAxisID
                }, _getIndexScaleId: function () {
                    return this.getMeta().yAxisID
                }
            }), zt = B.valueOrDefault, Vt = B.options.resolve, Bt = B.canvas._isPointInArea;

            function Wt(t, e) {
                var n = t && t.options.ticks || {}, r = n.reverse, i = void 0 === n.min ? e : 0,
                    a = void 0 === n.max ? e : 0;
                return {start: r ? a : i, end: r ? i : a}
            }

            L._set("line", {
                showLines: !0,
                spanGaps: !1,
                hover: {mode: "label"},
                scales: {xAxes: [{type: "category", id: "x-axis-0"}], yAxes: [{type: "linear", id: "y-axis-0"}]}
            });
            var Ht = nt.extend({
                datasetElementType: _t.Line,
                dataElementType: _t.Point,
                _datasetElementOptions: ["backgroundColor", "borderCapStyle", "borderColor", "borderDash", "borderDashOffset", "borderJoinStyle", "borderWidth", "cubicInterpolationMode", "fill"],
                _dataElementOptions: {
                    backgroundColor: "pointBackgroundColor",
                    borderColor: "pointBorderColor",
                    borderWidth: "pointBorderWidth",
                    hitRadius: "pointHitRadius",
                    hoverBackgroundColor: "pointHoverBackgroundColor",
                    hoverBorderColor: "pointHoverBorderColor",
                    hoverBorderWidth: "pointHoverBorderWidth",
                    hoverRadius: "pointHoverRadius",
                    pointStyle: "pointStyle",
                    radius: "pointRadius",
                    rotation: "pointRotation"
                },
                update: function (t) {
                    var e, n, r = this, i = r.getMeta(), a = i.dataset, o = i.data || [], s = r.chart.options,
                        l = r._config, u = r._showLine = zt(l.showLine, s.showLines);
                    for (r._xScale = r.getScaleForId(i.xAxisID), r._yScale = r.getScaleForId(i.yAxisID), u && (void 0 !== l.tension && void 0 === l.lineTension && (l.lineTension = l.tension), a._scale = r._yScale, a._datasetIndex = r.index, a._children = o, a._model = r._resolveDatasetElementOptions(a), a.pivot()), e = 0, n = o.length; e < n; ++e) r.updateElement(o[e], e, t);
                    for (u && 0 !== a._model.tension && r.updateBezierControlPoints(), e = 0, n = o.length; e < n; ++e) o[e].pivot()
                },
                updateElement: function (t, e, n) {
                    var r, i, a = this, o = a.getMeta(), s = t.custom || {}, l = a.getDataset(), u = a.index,
                        c = l.data[e], d = a._xScale, h = a._yScale, f = o.dataset._model,
                        p = a._resolveDataElementOptions(t, e);
                    r = d.getPixelForValue("object" === typeof c ? c : NaN, e, u), i = n ? h.getBasePixel() : a.calculatePointY(c, e, u), t._xScale = d, t._yScale = h, t._options = p, t._datasetIndex = u, t._index = e, t._model = {
                        x: r,
                        y: i,
                        skip: s.skip || isNaN(r) || isNaN(i),
                        radius: p.radius,
                        pointStyle: p.pointStyle,
                        rotation: p.rotation,
                        backgroundColor: p.backgroundColor,
                        borderColor: p.borderColor,
                        borderWidth: p.borderWidth,
                        tension: zt(s.tension, f ? f.tension : 0),
                        steppedLine: !!f && f.steppedLine,
                        hitRadius: p.hitRadius
                    }
                },
                _resolveDatasetElementOptions: function (t) {
                    var e = this, n = e._config, r = t.custom || {}, i = e.chart.options, a = i.elements.line,
                        o = nt.prototype._resolveDatasetElementOptions.apply(e, arguments);
                    return o.spanGaps = zt(n.spanGaps, i.spanGaps), o.tension = zt(n.lineTension, a.tension), o.steppedLine = Vt([r.steppedLine, n.steppedLine, a.stepped]), o.clip = function (t) {
                        var e, n, r, i;
                        return B.isObject(t) ? (e = t.top, n = t.right, r = t.bottom, i = t.left) : e = n = r = i = t, {
                            top: e,
                            right: n,
                            bottom: r,
                            left: i
                        }
                    }(zt(n.clip, function (t, e, n) {
                        var r = n / 2, i = Wt(t, r), a = Wt(e, r);
                        return {top: a.end, right: i.end, bottom: a.start, left: i.start}
                    }(e._xScale, e._yScale, o.borderWidth))), o
                },
                calculatePointY: function (t, e, n) {
                    var r, i, a, o, s, l, u, c = this.chart, d = this._yScale, h = 0, f = 0;
                    if (d.options.stacked) {
                        for (s = +d.getRightValue(t), l = c._getSortedVisibleDatasetMetas(), u = l.length, r = 0; r < u && (a = l[r]).index !== n; ++r) i = c.data.datasets[a.index], "line" === a.type && a.yAxisID === d.id && ((o = +d.getRightValue(i.data[e])) < 0 ? f += o || 0 : h += o || 0);
                        return s < 0 ? d.getPixelForValue(f + s) : d.getPixelForValue(h + s)
                    }
                    return d.getPixelForValue(t)
                },
                updateBezierControlPoints: function () {
                    var t, e, n, r, i = this.chart, a = this.getMeta(), o = a.dataset._model, s = i.chartArea,
                        l = a.data || [];

                    function u(t, e, n) {
                        return Math.max(Math.min(t, n), e)
                    }

                    if (o.spanGaps && (l = l.filter(function (t) {
                        return !t._model.skip
                    })), "monotone" === o.cubicInterpolationMode) B.splineCurveMonotone(l); else for (t = 0, e = l.length; t < e; ++t) n = l[t]._model, r = B.splineCurve(B.previousItem(l, t)._model, n, B.nextItem(l, t)._model, o.tension), n.controlPointPreviousX = r.previous.x, n.controlPointPreviousY = r.previous.y, n.controlPointNextX = r.next.x, n.controlPointNextY = r.next.y;
                    if (i.options.elements.line.capBezierPoints) for (t = 0, e = l.length; t < e; ++t) n = l[t]._model, Bt(n, s) && (t > 0 && Bt(l[t - 1]._model, s) && (n.controlPointPreviousX = u(n.controlPointPreviousX, s.left, s.right), n.controlPointPreviousY = u(n.controlPointPreviousY, s.top, s.bottom)), t < l.length - 1 && Bt(l[t + 1]._model, s) && (n.controlPointNextX = u(n.controlPointNextX, s.left, s.right), n.controlPointNextY = u(n.controlPointNextY, s.top, s.bottom)))
                },
                draw: function () {
                    var t, e = this.chart, n = this.getMeta(), r = n.data || [], i = e.chartArea, a = e.canvas, o = 0,
                        s = r.length;
                    for (this._showLine && (t = n.dataset._model.clip, B.canvas.clipArea(e.ctx, {
                        left: !1 === t.left ? 0 : i.left - t.left,
                        right: !1 === t.right ? a.width : i.right + t.right,
                        top: !1 === t.top ? 0 : i.top - t.top,
                        bottom: !1 === t.bottom ? a.height : i.bottom + t.bottom
                    }), n.dataset.draw(), B.canvas.unclipArea(e.ctx)); o < s; ++o) r[o].draw(i)
                },
                setHoverStyle: function (t) {
                    var e = t._model, n = t._options, r = B.getHoverColor;
                    t.$previousStyle = {
                        backgroundColor: e.backgroundColor,
                        borderColor: e.borderColor,
                        borderWidth: e.borderWidth,
                        radius: e.radius
                    }, e.backgroundColor = zt(n.hoverBackgroundColor, r(n.backgroundColor)), e.borderColor = zt(n.hoverBorderColor, r(n.borderColor)), e.borderWidth = zt(n.hoverBorderWidth, n.borderWidth), e.radius = zt(n.hoverRadius, n.radius)
                }
            }), Ut = B.options.resolve;
            L._set("polarArea", {
                scale: {
                    type: "radialLinear",
                    angleLines: {display: !1},
                    gridLines: {circular: !0},
                    pointLabels: {display: !1},
                    ticks: {beginAtZero: !0}
                },
                animation: {animateRotate: !0, animateScale: !0},
                startAngle: -.5 * Math.PI,
                legendCallback: function (t) {
                    var e, n, r, i = document.createElement("ul"), a = t.data, o = a.datasets, s = a.labels;
                    if (i.setAttribute("class", t.id + "-legend"), o.length) for (e = 0, n = o[0].data.length; e < n; ++e) (r = i.appendChild(document.createElement("li"))).appendChild(document.createElement("span")).style.backgroundColor = o[0].backgroundColor[e], s[e] && r.appendChild(document.createTextNode(s[e]));
                    return i.outerHTML
                },
                legend: {
                    labels: {
                        generateLabels: function (t) {
                            var e = t.data;
                            return e.labels.length && e.datasets.length ? e.labels.map(function (n, r) {
                                var i = t.getDatasetMeta(0), a = i.controller.getStyle(r);
                                return {
                                    text: n,
                                    fillStyle: a.backgroundColor,
                                    strokeStyle: a.borderColor,
                                    lineWidth: a.borderWidth,
                                    hidden: isNaN(e.datasets[0].data[r]) || i.data[r].hidden,
                                    index: r
                                }
                            }) : []
                        }
                    }, onClick: function (t, e) {
                        var n, r, i, a = e.index, o = this.chart;
                        for (n = 0, r = (o.data.datasets || []).length; n < r; ++n) (i = o.getDatasetMeta(n)).data[a].hidden = !i.data[a].hidden;
                        o.update()
                    }
                },
                tooltips: {
                    callbacks: {
                        title: function () {
                            return ""
                        }, label: function (t, e) {
                            return e.labels[t.index] + ": " + t.yLabel
                        }
                    }
                }
            });
            var Yt = nt.extend({
                dataElementType: _t.Arc,
                linkScales: B.noop,
                _dataElementOptions: ["backgroundColor", "borderColor", "borderWidth", "borderAlign", "hoverBackgroundColor", "hoverBorderColor", "hoverBorderWidth"],
                _getIndexScaleId: function () {
                    return this.chart.scale.id
                },
                _getValueScaleId: function () {
                    return this.chart.scale.id
                },
                update: function (t) {
                    var e, n, r, i = this, a = i.getDataset(), o = i.getMeta(), s = i.chart.options.startAngle || 0,
                        l = i._starts = [], u = i._angles = [], c = o.data;
                    for (i._updateRadius(), o.count = i.countVisibleElements(), e = 0, n = a.data.length; e < n; e++) l[e] = s, r = i._computeAngle(e), u[e] = r, s += r;
                    for (e = 0, n = c.length; e < n; ++e) c[e]._options = i._resolveDataElementOptions(c[e], e), i.updateElement(c[e], e, t)
                },
                _updateRadius: function () {
                    var t = this, e = t.chart, n = e.chartArea, r = e.options,
                        i = Math.min(n.right - n.left, n.bottom - n.top);
                    e.outerRadius = Math.max(i / 2, 0), e.innerRadius = Math.max(r.cutoutPercentage ? e.outerRadius / 100 * r.cutoutPercentage : 1, 0), e.radiusLength = (e.outerRadius - e.innerRadius) / e.getVisibleDatasetCount(), t.outerRadius = e.outerRadius - e.radiusLength * t.index, t.innerRadius = t.outerRadius - e.radiusLength
                },
                updateElement: function (t, e, n) {
                    var r = this, i = r.chart, a = r.getDataset(), o = i.options, s = o.animation, l = i.scale,
                        u = i.data.labels, c = l.xCenter, d = l.yCenter, h = o.startAngle,
                        f = t.hidden ? 0 : l.getDistanceFromCenterForValue(a.data[e]), p = r._starts[e],
                        g = p + (t.hidden ? 0 : r._angles[e]),
                        v = s.animateScale ? 0 : l.getDistanceFromCenterForValue(a.data[e]), m = t._options || {};
                    B.extend(t, {
                        _datasetIndex: r.index,
                        _index: e,
                        _scale: l,
                        _model: {
                            backgroundColor: m.backgroundColor,
                            borderColor: m.borderColor,
                            borderWidth: m.borderWidth,
                            borderAlign: m.borderAlign,
                            x: c,
                            y: d,
                            innerRadius: 0,
                            outerRadius: n ? v : f,
                            startAngle: n && s.animateRotate ? h : p,
                            endAngle: n && s.animateRotate ? h : g,
                            label: B.valueAtIndexOrDefault(u, e, u[e])
                        }
                    }), t.pivot()
                },
                countVisibleElements: function () {
                    var t = this.getDataset(), e = this.getMeta(), n = 0;
                    return B.each(e.data, function (e, r) {
                        isNaN(t.data[r]) || e.hidden || n++
                    }), n
                },
                setHoverStyle: function (t) {
                    var e = t._model, n = t._options, r = B.getHoverColor, i = B.valueOrDefault;
                    t.$previousStyle = {
                        backgroundColor: e.backgroundColor,
                        borderColor: e.borderColor,
                        borderWidth: e.borderWidth
                    }, e.backgroundColor = i(n.hoverBackgroundColor, r(n.backgroundColor)), e.borderColor = i(n.hoverBorderColor, r(n.borderColor)), e.borderWidth = i(n.hoverBorderWidth, n.borderWidth)
                },
                _computeAngle: function (t) {
                    var e = this, n = this.getMeta().count, r = e.getDataset(), i = e.getMeta();
                    if (isNaN(r.data[t]) || i.data[t].hidden) return 0;
                    var a = {chart: e.chart, dataIndex: t, dataset: r, datasetIndex: e.index};
                    return Ut([e.chart.options.elements.arc.angle, 2 * Math.PI / n], a, t)
                }
            });
            L._set("pie", B.clone(L.doughnut)), L._set("pie", {cutoutPercentage: 0});
            var qt = Nt, $t = B.valueOrDefault;
            L._set("radar", {
                spanGaps: !1,
                scale: {type: "radialLinear"},
                elements: {line: {fill: "start", tension: 0}}
            });
            var Xt = nt.extend({
                datasetElementType: _t.Line,
                dataElementType: _t.Point,
                linkScales: B.noop,
                _datasetElementOptions: ["backgroundColor", "borderWidth", "borderColor", "borderCapStyle", "borderDash", "borderDashOffset", "borderJoinStyle", "fill"],
                _dataElementOptions: {
                    backgroundColor: "pointBackgroundColor",
                    borderColor: "pointBorderColor",
                    borderWidth: "pointBorderWidth",
                    hitRadius: "pointHitRadius",
                    hoverBackgroundColor: "pointHoverBackgroundColor",
                    hoverBorderColor: "pointHoverBorderColor",
                    hoverBorderWidth: "pointHoverBorderWidth",
                    hoverRadius: "pointHoverRadius",
                    pointStyle: "pointStyle",
                    radius: "pointRadius",
                    rotation: "pointRotation"
                },
                _getIndexScaleId: function () {
                    return this.chart.scale.id
                },
                _getValueScaleId: function () {
                    return this.chart.scale.id
                },
                update: function (t) {
                    var e, n, r = this, i = r.getMeta(), a = i.dataset, o = i.data || [], s = r.chart.scale,
                        l = r._config;
                    for (void 0 !== l.tension && void 0 === l.lineTension && (l.lineTension = l.tension), a._scale = s, a._datasetIndex = r.index, a._children = o, a._loop = !0, a._model = r._resolveDatasetElementOptions(a), a.pivot(), e = 0, n = o.length; e < n; ++e) r.updateElement(o[e], e, t);
                    for (r.updateBezierControlPoints(), e = 0, n = o.length; e < n; ++e) o[e].pivot()
                },
                updateElement: function (t, e, n) {
                    var r = this, i = t.custom || {}, a = r.getDataset(), o = r.chart.scale,
                        s = o.getPointPositionForValue(e, a.data[e]), l = r._resolveDataElementOptions(t, e),
                        u = r.getMeta().dataset._model, c = n ? o.xCenter : s.x, d = n ? o.yCenter : s.y;
                    t._scale = o, t._options = l, t._datasetIndex = r.index, t._index = e, t._model = {
                        x: c,
                        y: d,
                        skip: i.skip || isNaN(c) || isNaN(d),
                        radius: l.radius,
                        pointStyle: l.pointStyle,
                        rotation: l.rotation,
                        backgroundColor: l.backgroundColor,
                        borderColor: l.borderColor,
                        borderWidth: l.borderWidth,
                        tension: $t(i.tension, u ? u.tension : 0),
                        hitRadius: l.hitRadius
                    }
                },
                _resolveDatasetElementOptions: function () {
                    var t = this._config, e = this.chart.options,
                        n = nt.prototype._resolveDatasetElementOptions.apply(this, arguments);
                    return n.spanGaps = $t(t.spanGaps, e.spanGaps), n.tension = $t(t.lineTension, e.elements.line.tension), n
                },
                updateBezierControlPoints: function () {
                    var t, e, n, r, i = this.getMeta(), a = this.chart.chartArea, o = i.data || [];

                    function s(t, e, n) {
                        return Math.max(Math.min(t, n), e)
                    }

                    for (i.dataset._model.spanGaps && (o = o.filter(function (t) {
                        return !t._model.skip
                    })), t = 0, e = o.length; t < e; ++t) n = o[t]._model, r = B.splineCurve(B.previousItem(o, t, !0)._model, n, B.nextItem(o, t, !0)._model, n.tension), n.controlPointPreviousX = s(r.previous.x, a.left, a.right), n.controlPointPreviousY = s(r.previous.y, a.top, a.bottom), n.controlPointNextX = s(r.next.x, a.left, a.right), n.controlPointNextY = s(r.next.y, a.top, a.bottom)
                },
                setHoverStyle: function (t) {
                    var e = t._model, n = t._options, r = B.getHoverColor;
                    t.$previousStyle = {
                        backgroundColor: e.backgroundColor,
                        borderColor: e.borderColor,
                        borderWidth: e.borderWidth,
                        radius: e.radius
                    }, e.backgroundColor = $t(n.hoverBackgroundColor, r(n.backgroundColor)), e.borderColor = $t(n.hoverBorderColor, r(n.borderColor)), e.borderWidth = $t(n.hoverBorderWidth, n.borderWidth), e.radius = $t(n.hoverRadius, n.radius)
                }
            });
            L._set("scatter", {
                hover: {mode: "single"},
                scales: {
                    xAxes: [{id: "x-axis-1", type: "linear", position: "bottom"}],
                    yAxes: [{id: "y-axis-1", type: "linear", position: "left"}]
                },
                tooltips: {
                    callbacks: {
                        title: function () {
                            return ""
                        }, label: function (t) {
                            return "(" + t.xLabel + ", " + t.yLabel + ")"
                        }
                    }
                }
            }), L._set("global", {datasets: {scatter: {showLine: !1}}});
            var Gt = {
                bar: Tt,
                bubble: Ot,
                doughnut: Nt,
                horizontalBar: jt,
                line: Ht,
                polarArea: Yt,
                pie: qt,
                radar: Xt,
                scatter: Ht
            };

            function Kt(t, e) {
                return t.native ? {x: t.x, y: t.y} : B.getRelativePosition(t, e)
            }

            function Zt(t, e) {
                var n, r, i, a, o, s, l = t._getSortedVisibleDatasetMetas();
                for (r = 0, a = l.length; r < a; ++r) for (n = l[r].data, i = 0, o = n.length; i < o; ++i) (s = n[i])._view.skip || e(s)
            }

            function Jt(t, e) {
                var n = [];
                return Zt(t, function (t) {
                    t.inRange(e.x, e.y) && n.push(t)
                }), n
            }

            function Qt(t, e, n, r) {
                var i = Number.POSITIVE_INFINITY, a = [];
                return Zt(t, function (t) {
                    if (!n || t.inRange(e.x, e.y)) {
                        var o = t.getCenterPoint(), s = r(e, o);
                        s < i ? (a = [t], i = s) : s === i && a.push(t)
                    }
                }), a
            }

            function te(t) {
                var e = -1 !== t.indexOf("x"), n = -1 !== t.indexOf("y");
                return function (t, r) {
                    var i = e ? Math.abs(t.x - r.x) : 0, a = n ? Math.abs(t.y - r.y) : 0;
                    return Math.sqrt(Math.pow(i, 2) + Math.pow(a, 2))
                }
            }

            function ee(t, e, n) {
                var r = Kt(e, t);
                n.axis = n.axis || "x";
                var i = te(n.axis), a = n.intersect ? Jt(t, r) : Qt(t, r, !1, i), o = [];
                return a.length ? (t._getSortedVisibleDatasetMetas().forEach(function (t) {
                    var e = t.data[a[0]._index];
                    e && !e._view.skip && o.push(e)
                }), o) : []
            }

            var ne = {
                modes: {
                    single: function (t, e) {
                        var n = Kt(e, t), r = [];
                        return Zt(t, function (t) {
                            if (t.inRange(n.x, n.y)) return r.push(t), r
                        }), r.slice(0, 1)
                    }, label: ee, index: ee, dataset: function (t, e, n) {
                        var r = Kt(e, t);
                        n.axis = n.axis || "xy";
                        var i = te(n.axis), a = n.intersect ? Jt(t, r) : Qt(t, r, !1, i);
                        return a.length > 0 && (a = t.getDatasetMeta(a[0]._datasetIndex).data), a
                    }, "x-axis": function (t, e) {
                        return ee(t, e, {intersect: !1})
                    }, point: function (t, e) {
                        var n = Kt(e, t);
                        return Jt(t, n)
                    }, nearest: function (t, e, n) {
                        var r = Kt(e, t);
                        n.axis = n.axis || "xy";
                        var i = te(n.axis);
                        return Qt(t, r, n.intersect, i)
                    }, x: function (t, e, n) {
                        var r = Kt(e, t), i = [], a = !1;
                        return Zt(t, function (t) {
                            t.inXRange(r.x) && i.push(t), t.inRange(r.x, r.y) && (a = !0)
                        }), n.intersect && !a && (i = []), i
                    }, y: function (t, e, n) {
                        var r = Kt(e, t), i = [], a = !1;
                        return Zt(t, function (t) {
                            t.inYRange(r.y) && i.push(t), t.inRange(r.x, r.y) && (a = !0)
                        }), n.intersect && !a && (i = []), i
                    }
                }
            }, re = B.extend;

            function ie(t, e) {
                return B.where(t, function (t) {
                    return t.pos === e
                })
            }

            function ae(t, e) {
                return t.sort(function (t, n) {
                    var r = e ? n : t, i = e ? t : n;
                    return r.weight === i.weight ? r.index - i.index : r.weight - i.weight
                })
            }

            function oe(t, e, n, r) {
                return Math.max(t[n], e[n]) + Math.max(t[r], e[r])
            }

            function se(t, e, n) {
                var r, i, a = n.box, o = t.maxPadding;
                if (n.size && (t[n.pos] -= n.size), n.size = n.horizontal ? a.height : a.width, t[n.pos] += n.size, a.getPadding) {
                    var s = a.getPadding();
                    o.top = Math.max(o.top, s.top), o.left = Math.max(o.left, s.left), o.bottom = Math.max(o.bottom, s.bottom), o.right = Math.max(o.right, s.right)
                }
                if (r = e.outerWidth - oe(o, t, "left", "right"), i = e.outerHeight - oe(o, t, "top", "bottom"), r !== t.w || i !== t.h) return t.w = r, t.h = i, n.horizontal ? r !== t.w : i !== t.h
            }

            function le(t, e) {
                var n = e.maxPadding;

                function r(t) {
                    var r = {left: 0, top: 0, right: 0, bottom: 0};
                    return t.forEach(function (t) {
                        r[t] = Math.max(e[t], n[t])
                    }), r
                }

                return r(t ? ["left", "right"] : ["top", "bottom"])
            }

            function ue(t, e, n) {
                var r, i, a, o, s, l, u = [];
                for (r = 0, i = t.length; r < i; ++r) a = t[r], (o = a.box).update(a.width || e.w, a.height || e.h, le(a.horizontal, e)), se(e, n, a) && (l = !0, u.length && (s = !0)), o.fullWidth || u.push(a);
                return s && ue(u, e, n) || l
            }

            function ce(t, e, n) {
                var r, i, a, o, s = n.padding, l = e.x, u = e.y;
                for (r = 0, i = t.length; r < i; ++r) a = t[r], o = a.box, a.horizontal ? (o.left = o.fullWidth ? s.left : e.left, o.right = o.fullWidth ? n.outerWidth - s.right : e.left + e.w, o.top = u, o.bottom = u + o.height, o.width = o.right - o.left, u = o.bottom) : (o.left = l, o.right = l + o.width, o.top = e.top, o.bottom = e.top + e.h, o.height = o.bottom - o.top, l = o.right);
                e.x = l, e.y = u
            }

            L._set("global", {layout: {padding: {top: 0, right: 0, bottom: 0, left: 0}}});
            var de, he = {
                    defaults: {}, addBox: function (t, e) {
                        t.boxes || (t.boxes = []), e.fullWidth = e.fullWidth || !1, e.position = e.position || "top", e.weight = e.weight || 0, e._layers = e._layers || function () {
                            return [{
                                z: 0, draw: function () {
                                    e.draw.apply(e, arguments)
                                }
                            }]
                        }, t.boxes.push(e)
                    }, removeBox: function (t, e) {
                        var n = t.boxes ? t.boxes.indexOf(e) : -1;
                        -1 !== n && t.boxes.splice(n, 1)
                    }, configure: function (t, e, n) {
                        for (var r, i = ["fullWidth", "position", "weight"], a = i.length, o = 0; o < a; ++o) r = i[o], n.hasOwnProperty(r) && (e[r] = n[r])
                    }, update: function (t, e, n) {
                        if (t) {
                            var r = t.options.layout || {}, i = B.options.toPadding(r.padding), a = e - i.width,
                                o = n - i.height, s = function (t) {
                                    var e = function (t) {
                                            var e, n, r, i = [];
                                            for (e = 0, n = (t || []).length; e < n; ++e) r = t[e], i.push({
                                                index: e,
                                                box: r,
                                                pos: r.position,
                                                horizontal: r.isHorizontal(),
                                                weight: r.weight
                                            });
                                            return i
                                        }(t), n = ae(ie(e, "left"), !0), r = ae(ie(e, "right")), i = ae(ie(e, "top"), !0),
                                        a = ae(ie(e, "bottom"));
                                    return {
                                        leftAndTop: n.concat(i),
                                        rightAndBottom: r.concat(a),
                                        chartArea: ie(e, "chartArea"),
                                        vertical: n.concat(r),
                                        horizontal: i.concat(a)
                                    }
                                }(t.boxes), l = s.vertical, u = s.horizontal, c = Object.freeze({
                                    outerWidth: e,
                                    outerHeight: n,
                                    padding: i,
                                    availableWidth: a,
                                    vBoxMaxWidth: a / 2 / l.length,
                                    hBoxMaxHeight: o / 2
                                }), d = re({maxPadding: re({}, i), w: a, h: o, x: i.left, y: i.top}, i);
                            !function (t, e) {
                                var n, r, i;
                                for (n = 0, r = t.length; n < r; ++n) (i = t[n]).width = i.horizontal ? i.box.fullWidth && e.availableWidth : e.vBoxMaxWidth, i.height = i.horizontal && e.hBoxMaxHeight
                            }(l.concat(u), c), ue(l, d, c), ue(u, d, c) && ue(l, d, c), function (t) {
                                var e = t.maxPadding;

                                function n(n) {
                                    var r = Math.max(e[n] - t[n], 0);
                                    return t[n] += r, r
                                }

                                t.y += n("top"), t.x += n("left"), n("right"), n("bottom")
                            }(d), ce(s.leftAndTop, d, c), d.x += d.w, d.y += d.h, ce(s.rightAndBottom, d, c), t.chartArea = {
                                left: d.left,
                                top: d.top,
                                right: d.left + d.w,
                                bottom: d.top + d.h
                            }, B.each(s.chartArea, function (e) {
                                var n = e.box;
                                re(n, t.chartArea), n.update(d.w, d.h)
                            })
                        }
                    }
                }, fe = (de = Object.freeze({
                    __proto__: null,
                    default: "/*\n * DOM element rendering detection\n * https://davidwalsh.name/detect-node-insertion\n */\n@keyframes chartjs-render-animation {\n\tfrom { opacity: 0.99; }\n\tto { opacity: 1; }\n}\n\n.chartjs-render-monitor {\n\tanimation: chartjs-render-animation 0.001s;\n}\n\n/*\n * DOM element resizing detection\n * https://github.com/marcj/css-element-queries\n */\n.chartjs-size-monitor,\n.chartjs-size-monitor-expand,\n.chartjs-size-monitor-shrink {\n\tposition: absolute;\n\tdirection: ltr;\n\tleft: 0;\n\ttop: 0;\n\tright: 0;\n\tbottom: 0;\n\toverflow: hidden;\n\tpointer-events: none;\n\tvisibility: hidden;\n\tz-index: -1;\n}\n\n.chartjs-size-monitor-expand > div {\n\tposition: absolute;\n\twidth: 1000000px;\n\theight: 1000000px;\n\tleft: 0;\n\ttop: 0;\n}\n\n.chartjs-size-monitor-shrink > div {\n\tposition: absolute;\n\twidth: 200%;\n\theight: 200%;\n\tleft: 0;\n\ttop: 0;\n}\n"
                })) && de.default || de, pe = "$chartjs", ge = "chartjs-size-monitor", ve = "chartjs-render-monitor",
                me = "chartjs-render-animation", be = ["animationstart", "webkitAnimationStart"], ye = {
                    touchstart: "mousedown",
                    touchmove: "mousemove",
                    touchend: "mouseup",
                    pointerenter: "mouseenter",
                    pointerdown: "mousedown",
                    pointermove: "mousemove",
                    pointerup: "mouseup",
                    pointerleave: "mouseout",
                    pointerout: "mouseout"
                };

            function xe(t, e) {
                var n = B.getStyle(t, e), r = n && n.match(/^(\d+)(\.\d+)?px$/);
                return r ? Number(r[1]) : void 0
            }

            var _e = !!function () {
                var t = !1;
                try {
                    var e = Object.defineProperty({}, "passive", {
                        get: function () {
                            t = !0
                        }
                    });
                    window.addEventListener("e", null, e)
                } catch (n) {
                }
                return t
            }() && {passive: !0};

            function we(t, e, n) {
                t.addEventListener(e, n, _e)
            }

            function ke(t, e, n) {
                t.removeEventListener(e, n, _e)
            }

            function Me(t, e, n, r, i) {
                return {type: t, chart: e, native: i || null, x: void 0 !== n ? n : null, y: void 0 !== r ? r : null}
            }

            function Fe(t) {
                var e = document.createElement("div");
                return e.className = t || "", e
            }

            function Se(t, e, n) {
                var r = t[pe] || (t[pe] = {}), i = r.resizer = function (t) {
                    var e = Fe(ge), n = Fe(ge + "-expand"), r = Fe(ge + "-shrink");
                    n.appendChild(Fe()), r.appendChild(Fe()), e.appendChild(n), e.appendChild(r), e._reset = function () {
                        n.scrollLeft = 1e6, n.scrollTop = 1e6, r.scrollLeft = 1e6, r.scrollTop = 1e6
                    };
                    var i = function () {
                        e._reset(), t()
                    };
                    return we(n, "scroll", i.bind(n, "expand")), we(r, "scroll", i.bind(r, "shrink")), e
                }(function (t, e) {
                    var n = !1, r = [];
                    return function () {
                        r = Array.prototype.slice.call(arguments), e = e || this, n || (n = !0, B.requestAnimFrame.call(window, function () {
                            n = !1, t.apply(e, r)
                        }))
                    }
                }(function () {
                    if (r.resizer) {
                        var i = n.options.maintainAspectRatio && t.parentNode, a = i ? i.clientWidth : 0;
                        e(Me("resize", n)), i && i.clientWidth < a && n.canvas && e(Me("resize", n))
                    }
                }));
                !function (t, e) {
                    var n = t[pe] || (t[pe] = {}), r = n.renderProxy = function (t) {
                        t.animationName === me && e()
                    };
                    B.each(be, function (e) {
                        we(t, e, r)
                    }), n.reflow = !!t.offsetParent, t.classList.add(ve)
                }(t, function () {
                    if (r.resizer) {
                        var e = t.parentNode;
                        e && e !== i.parentNode && e.insertBefore(i, e.firstChild), i._reset()
                    }
                })
            }

            function Ce(t) {
                var e = t[pe] || {}, n = e.resizer;
                delete e.resizer, function (t) {
                    var e = t[pe] || {}, n = e.renderProxy;
                    n && (B.each(be, function (e) {
                        ke(t, e, n)
                    }), delete e.renderProxy), t.classList.remove(ve)
                }(t), n && n.parentNode && n.parentNode.removeChild(n)
            }

            var Ae = {
                disableCSSInjection: !1,
                _enabled: "undefined" !== typeof window && "undefined" !== typeof document,
                _ensureLoaded: function (t) {
                    if (!this.disableCSSInjection) {
                        var e = t.getRootNode ? t.getRootNode() : document, n = e.host ? e : document.head;
                        !function (t, e) {
                            var n = t[pe] || (t[pe] = {});
                            if (!n.containsStyles) {
                                n.containsStyles = !0, e = "/* Chart.js */\n" + e;
                                var r = document.createElement("style");
                                r.setAttribute("type", "text/css"), r.appendChild(document.createTextNode(e)), t.appendChild(r)
                            }
                        }(n, fe)
                    }
                },
                acquireContext: function (t, e) {
                    "string" === typeof t ? t = document.getElementById(t) : t.length && (t = t[0]), t && t.canvas && (t = t.canvas);
                    var n = t && t.getContext && t.getContext("2d");
                    return n && n.canvas === t ? (this._ensureLoaded(t), function (t, e) {
                        var n = t.style, r = t.getAttribute("height"), i = t.getAttribute("width");
                        if (t[pe] = {
                            initial: {
                                height: r,
                                width: i,
                                style: {display: n.display, height: n.height, width: n.width}
                            }
                        }, n.display = n.display || "block", null === i || "" === i) {
                            var a = xe(t, "width");
                            void 0 !== a && (t.width = a)
                        }
                        if (null === r || "" === r) if ("" === t.style.height) t.height = t.width / (e.options.aspectRatio || 2); else {
                            var o = xe(t, "height");
                            void 0 !== a && (t.height = o)
                        }
                    }(t, e), n) : null
                },
                releaseContext: function (t) {
                    var e = t.canvas;
                    if (e[pe]) {
                        var n = e[pe].initial;
                        ["height", "width"].forEach(function (t) {
                            var r = n[t];
                            B.isNullOrUndef(r) ? e.removeAttribute(t) : e.setAttribute(t, r)
                        }), B.each(n.style || {}, function (t, n) {
                            e.style[n] = t
                        }), e.width = e.width, delete e[pe]
                    }
                },
                addEventListener: function (t, e, n) {
                    var r = t.canvas;
                    if ("resize" !== e) {
                        var i = n[pe] || (n[pe] = {}), a = i.proxies || (i.proxies = {}),
                            o = a[t.id + "_" + e] = function (e) {
                                n(function (t, e) {
                                    var n = ye[t.type] || t.type, r = B.getRelativePosition(t, e);
                                    return Me(n, e, r.x, r.y, t)
                                }(e, t))
                            };
                        we(r, e, o)
                    } else Se(r, n, t)
                },
                removeEventListener: function (t, e, n) {
                    var r = t.canvas;
                    if ("resize" !== e) {
                        var i = n[pe] || {}, a = i.proxies || {}, o = a[t.id + "_" + e];
                        o && ke(r, e, o)
                    } else Ce(r)
                }
            };
            B.addEvent = we, B.removeEvent = ke;
            var Te = Ae._enabled ? Ae : {
                acquireContext: function (t) {
                    return t && t.canvas && (t = t.canvas), t && t.getContext("2d") || null
                }
            }, Pe = B.extend({
                initialize: function () {
                }, acquireContext: function () {
                }, releaseContext: function () {
                }, addEventListener: function () {
                }, removeEventListener: function () {
                }
            }, Te);
            L._set("global", {plugins: {}});
            var De = {
                _plugins: [], _cacheId: 0, register: function (t) {
                    var e = this._plugins;
                    [].concat(t).forEach(function (t) {
                        -1 === e.indexOf(t) && e.push(t)
                    }), this._cacheId++
                }, unregister: function (t) {
                    var e = this._plugins;
                    [].concat(t).forEach(function (t) {
                        var n = e.indexOf(t);
                        -1 !== n && e.splice(n, 1)
                    }), this._cacheId++
                }, clear: function () {
                    this._plugins = [], this._cacheId++
                }, count: function () {
                    return this._plugins.length
                }, getAll: function () {
                    return this._plugins
                }, notify: function (t, e, n) {
                    var r, i, a, o, s, l = this.descriptors(t), u = l.length;
                    for (r = 0; r < u; ++r) if (i = l[r], a = i.plugin, "function" === typeof (s = a[e]) && ((o = [t].concat(n || [])).push(i.options), !1 === s.apply(a, o))) return !1;
                    return !0
                }, descriptors: function (t) {
                    var e = t.$plugins || (t.$plugins = {});
                    if (e.id === this._cacheId) return e.descriptors;
                    var n = [], r = [], i = t && t.config || {}, a = i.options && i.options.plugins || {};
                    return this._plugins.concat(i.plugins || []).forEach(function (t) {
                        var e = n.indexOf(t);
                        if (-1 === e) {
                            var i = t.id, o = a[i];
                            !1 !== o && (!0 === o && (o = B.clone(L.global.plugins[i])), n.push(t), r.push({
                                plugin: t,
                                options: o || {}
                            }))
                        }
                    }), e.descriptors = r, e.id = this._cacheId, r
                }, _invalidate: function (t) {
                    delete t.$plugins
                }
            }, Oe = {
                constructors: {}, defaults: {}, registerScaleType: function (t, e, n) {
                    this.constructors[t] = e, this.defaults[t] = B.clone(n)
                }, getScaleConstructor: function (t) {
                    return this.constructors.hasOwnProperty(t) ? this.constructors[t] : void 0
                }, getScaleDefaults: function (t) {
                    return this.defaults.hasOwnProperty(t) ? B.merge({}, [L.scale, this.defaults[t]]) : {}
                }, updateScaleDefaults: function (t, e) {
                    this.defaults.hasOwnProperty(t) && (this.defaults[t] = B.extend(this.defaults[t], e))
                }, addScalesToLayout: function (t) {
                    B.each(t.scales, function (e) {
                        e.fullWidth = e.options.fullWidth, e.position = e.options.position, e.weight = e.options.weight, he.addBox(t, e)
                    })
                }
            }, Ee = B.valueOrDefault, Ie = B.rtl.getRtlAdapter;
            L._set("global", {
                tooltips: {
                    enabled: !0,
                    custom: null,
                    mode: "nearest",
                    position: "average",
                    intersect: !0,
                    backgroundColor: "rgba(0,0,0,0.8)",
                    titleFontStyle: "bold",
                    titleSpacing: 2,
                    titleMarginBottom: 6,
                    titleFontColor: "#fff",
                    titleAlign: "left",
                    bodySpacing: 2,
                    bodyFontColor: "#fff",
                    bodyAlign: "left",
                    footerFontStyle: "bold",
                    footerSpacing: 2,
                    footerMarginTop: 6,
                    footerFontColor: "#fff",
                    footerAlign: "left",
                    yPadding: 6,
                    xPadding: 6,
                    caretPadding: 2,
                    caretSize: 5,
                    cornerRadius: 6,
                    multiKeyBackground: "#fff",
                    displayColors: !0,
                    borderColor: "rgba(0,0,0,0)",
                    borderWidth: 0,
                    callbacks: {
                        beforeTitle: B.noop,
                        title: function (t, e) {
                            var n = "", r = e.labels, i = r ? r.length : 0;
                            if (t.length > 0) {
                                var a = t[0];
                                a.label ? n = a.label : a.xLabel ? n = a.xLabel : i > 0 && a.index < i && (n = r[a.index])
                            }
                            return n
                        },
                        afterTitle: B.noop,
                        beforeBody: B.noop,
                        beforeLabel: B.noop,
                        label: function (t, e) {
                            var n = e.datasets[t.datasetIndex].label || "";
                            return n && (n += ": "), B.isNullOrUndef(t.value) ? n += t.yLabel : n += t.value, n
                        },
                        labelColor: function (t, e) {
                            var n = e.getDatasetMeta(t.datasetIndex), r = n.data[t.index], i = r._view;
                            return {borderColor: i.borderColor, backgroundColor: i.backgroundColor}
                        },
                        labelTextColor: function () {
                            return this._options.bodyFontColor
                        },
                        afterLabel: B.noop,
                        afterBody: B.noop,
                        beforeFooter: B.noop,
                        footer: B.noop,
                        afterFooter: B.noop
                    }
                }
            });
            var Re = {
                average: function (t) {
                    if (!t.length) return !1;
                    var e, n, r = 0, i = 0, a = 0;
                    for (e = 0, n = t.length; e < n; ++e) {
                        var o = t[e];
                        if (o && o.hasValue()) {
                            var s = o.tooltipPosition();
                            r += s.x, i += s.y, ++a
                        }
                    }
                    return {x: r / a, y: i / a}
                }, nearest: function (t, e) {
                    var n, r, i, a = e.x, o = e.y, s = Number.POSITIVE_INFINITY;
                    for (n = 0, r = t.length; n < r; ++n) {
                        var l = t[n];
                        if (l && l.hasValue()) {
                            var u = l.getCenterPoint(), c = B.distanceBetweenPoints(e, u);
                            c < s && (s = c, i = l)
                        }
                    }
                    if (i) {
                        var d = i.tooltipPosition();
                        a = d.x, o = d.y
                    }
                    return {x: a, y: o}
                }
            };

            function Le(t, e) {
                return e && (B.isArray(e) ? Array.prototype.push.apply(t, e) : t.push(e)), t
            }

            function Ne(t) {
                return ("string" === typeof t || t instanceof String) && t.indexOf("\n") > -1 ? t.split("\n") : t
            }

            function je(t) {
                var e = t._xScale, n = t._yScale || t._scale, r = t._index, i = t._datasetIndex,
                    a = t._chart.getDatasetMeta(i).controller, o = a._getIndexScale(), s = a._getValueScale();
                return {
                    xLabel: e ? e.getLabelForIndex(r, i) : "",
                    yLabel: n ? n.getLabelForIndex(r, i) : "",
                    label: o ? "" + o.getLabelForIndex(r, i) : "",
                    value: s ? "" + s.getLabelForIndex(r, i) : "",
                    index: r,
                    datasetIndex: i,
                    x: t._model.x,
                    y: t._model.y
                }
            }

            function ze(t) {
                var e = L.global;
                return {
                    xPadding: t.xPadding,
                    yPadding: t.yPadding,
                    xAlign: t.xAlign,
                    yAlign: t.yAlign,
                    rtl: t.rtl,
                    textDirection: t.textDirection,
                    bodyFontColor: t.bodyFontColor,
                    _bodyFontFamily: Ee(t.bodyFontFamily, e.defaultFontFamily),
                    _bodyFontStyle: Ee(t.bodyFontStyle, e.defaultFontStyle),
                    _bodyAlign: t.bodyAlign,
                    bodyFontSize: Ee(t.bodyFontSize, e.defaultFontSize),
                    bodySpacing: t.bodySpacing,
                    titleFontColor: t.titleFontColor,
                    _titleFontFamily: Ee(t.titleFontFamily, e.defaultFontFamily),
                    _titleFontStyle: Ee(t.titleFontStyle, e.defaultFontStyle),
                    titleFontSize: Ee(t.titleFontSize, e.defaultFontSize),
                    _titleAlign: t.titleAlign,
                    titleSpacing: t.titleSpacing,
                    titleMarginBottom: t.titleMarginBottom,
                    footerFontColor: t.footerFontColor,
                    _footerFontFamily: Ee(t.footerFontFamily, e.defaultFontFamily),
                    _footerFontStyle: Ee(t.footerFontStyle, e.defaultFontStyle),
                    footerFontSize: Ee(t.footerFontSize, e.defaultFontSize),
                    _footerAlign: t.footerAlign,
                    footerSpacing: t.footerSpacing,
                    footerMarginTop: t.footerMarginTop,
                    caretSize: t.caretSize,
                    cornerRadius: t.cornerRadius,
                    backgroundColor: t.backgroundColor,
                    opacity: 0,
                    legendColorBackground: t.multiKeyBackground,
                    displayColors: t.displayColors,
                    borderColor: t.borderColor,
                    borderWidth: t.borderWidth
                }
            }

            function Ve(t, e) {
                return "center" === e ? t.x + t.width / 2 : "right" === e ? t.x + t.width - t.xPadding : t.x + t.xPadding
            }

            function Be(t) {
                return Le([], Ne(t))
            }

            var We = X.extend({
                initialize: function () {
                    this._model = ze(this._options), this._lastActive = []
                }, getTitle: function () {
                    var t = this._options, e = t.callbacks, n = e.beforeTitle.apply(this, arguments),
                        r = e.title.apply(this, arguments), i = e.afterTitle.apply(this, arguments), a = [];
                    return a = Le(a, Ne(n)), a = Le(a, Ne(r)), a = Le(a, Ne(i))
                }, getBeforeBody: function () {
                    return Be(this._options.callbacks.beforeBody.apply(this, arguments))
                }, getBody: function (t, e) {
                    var n = this, r = n._options.callbacks, i = [];
                    return B.each(t, function (t) {
                        var a = {before: [], lines: [], after: []};
                        Le(a.before, Ne(r.beforeLabel.call(n, t, e))), Le(a.lines, r.label.call(n, t, e)), Le(a.after, Ne(r.afterLabel.call(n, t, e))), i.push(a)
                    }), i
                }, getAfterBody: function () {
                    return Be(this._options.callbacks.afterBody.apply(this, arguments))
                }, getFooter: function () {
                    var t = this._options.callbacks, e = t.beforeFooter.apply(this, arguments),
                        n = t.footer.apply(this, arguments), r = t.afterFooter.apply(this, arguments), i = [];
                    return i = Le(i, Ne(e)), i = Le(i, Ne(n)), i = Le(i, Ne(r))
                }, update: function (t) {
                    var e, n, r = this, i = r._options, a = r._model, o = r._model = ze(i), s = r._active, l = r._data,
                        u = {xAlign: a.xAlign, yAlign: a.yAlign}, c = {x: a.x, y: a.y},
                        d = {width: a.width, height: a.height}, h = {x: a.caretX, y: a.caretY};
                    if (s.length) {
                        o.opacity = 1;
                        var f = [], p = [];
                        h = Re[i.position].call(r, s, r._eventPosition);
                        var g = [];
                        for (e = 0, n = s.length; e < n; ++e) g.push(je(s[e]));
                        i.filter && (g = g.filter(function (t) {
                            return i.filter(t, l)
                        })), i.itemSort && (g = g.sort(function (t, e) {
                            return i.itemSort(t, e, l)
                        })), B.each(g, function (t) {
                            f.push(i.callbacks.labelColor.call(r, t, r._chart)), p.push(i.callbacks.labelTextColor.call(r, t, r._chart))
                        }), o.title = r.getTitle(g, l), o.beforeBody = r.getBeforeBody(g, l), o.body = r.getBody(g, l), o.afterBody = r.getAfterBody(g, l), o.footer = r.getFooter(g, l), o.x = h.x, o.y = h.y, o.caretPadding = i.caretPadding, o.labelColors = f, o.labelTextColors = p, o.dataPoints = g, d = function (t, e) {
                            var n = t._chart.ctx, r = 2 * e.yPadding, i = 0, a = e.body, o = a.reduce(function (t, e) {
                                return t + e.before.length + e.lines.length + e.after.length
                            }, 0);
                            o += e.beforeBody.length + e.afterBody.length;
                            var s = e.title.length, l = e.footer.length, u = e.titleFontSize, c = e.bodyFontSize,
                                d = e.footerFontSize;
                            r += s * u, r += s ? (s - 1) * e.titleSpacing : 0, r += s ? e.titleMarginBottom : 0, r += o * c, r += o ? (o - 1) * e.bodySpacing : 0, r += l ? e.footerMarginTop : 0, r += l * d, r += l ? (l - 1) * e.footerSpacing : 0;
                            var h = 0, f = function (t) {
                                i = Math.max(i, n.measureText(t).width + h)
                            };
                            return n.font = B.fontString(u, e._titleFontStyle, e._titleFontFamily), B.each(e.title, f), n.font = B.fontString(c, e._bodyFontStyle, e._bodyFontFamily), B.each(e.beforeBody.concat(e.afterBody), f), h = e.displayColors ? c + 2 : 0, B.each(a, function (t) {
                                B.each(t.before, f), B.each(t.lines, f), B.each(t.after, f)
                            }), h = 0, n.font = B.fontString(d, e._footerFontStyle, e._footerFontFamily), B.each(e.footer, f), {
                                width: i += 2 * e.xPadding,
                                height: r
                            }
                        }(this, o), u = function (t, e) {
                            var n, r, i, a, o, s = t._model, l = t._chart, u = t._chart.chartArea, c = "center",
                                d = "center";
                            s.y < e.height ? d = "top" : s.y > l.height - e.height && (d = "bottom");
                            var h = (u.left + u.right) / 2, f = (u.top + u.bottom) / 2;
                            "center" === d ? (n = function (t) {
                                return t <= h
                            }, r = function (t) {
                                return t > h
                            }) : (n = function (t) {
                                return t <= e.width / 2
                            }, r = function (t) {
                                return t >= l.width - e.width / 2
                            }), i = function (t) {
                                return t + e.width + s.caretSize + s.caretPadding > l.width
                            }, a = function (t) {
                                return t - e.width - s.caretSize - s.caretPadding < 0
                            }, o = function (t) {
                                return t <= f ? "top" : "bottom"
                            }, n(s.x) ? (c = "left", i(s.x) && (c = "center", d = o(s.y))) : r(s.x) && (c = "right", a(s.x) && (c = "center", d = o(s.y)));
                            var p = t._options;
                            return {xAlign: p.xAlign ? p.xAlign : c, yAlign: p.yAlign ? p.yAlign : d}
                        }(this, d), c = function (t, e, n, r) {
                            var i = t.x, a = t.y, o = t.caretSize, s = t.caretPadding, l = t.cornerRadius, u = n.xAlign,
                                c = n.yAlign, d = o + s, h = l + s;
                            return "right" === u ? i -= e.width : "center" === u && ((i -= e.width / 2) + e.width > r.width && (i = r.width - e.width), i < 0 && (i = 0)), "top" === c ? a += d : a -= "bottom" === c ? e.height + d : e.height / 2, "center" === c ? "left" === u ? i += d : "right" === u && (i -= d) : "left" === u ? i -= h : "right" === u && (i += h), {
                                x: i,
                                y: a
                            }
                        }(o, d, u, r._chart)
                    } else o.opacity = 0;
                    return o.xAlign = u.xAlign, o.yAlign = u.yAlign, o.x = c.x, o.y = c.y, o.width = d.width, o.height = d.height, o.caretX = h.x, o.caretY = h.y, r._model = o, t && i.custom && i.custom.call(r, o), r
                }, drawCaret: function (t, e) {
                    var n = this._chart.ctx, r = this._view, i = this.getCaretPosition(t, e, r);
                    n.lineTo(i.x1, i.y1), n.lineTo(i.x2, i.y2), n.lineTo(i.x3, i.y3)
                }, getCaretPosition: function (t, e, n) {
                    var r, i, a, o, s, l, u = n.caretSize, c = n.cornerRadius, d = n.xAlign, h = n.yAlign, f = t.x,
                        p = t.y, g = e.width, v = e.height;
                    if ("center" === h) s = p + v / 2, "left" === d ? (i = (r = f) - u, a = r, o = s + u, l = s - u) : (i = (r = f + g) + u, a = r, o = s - u, l = s + u); else if ("left" === d ? (r = (i = f + c + u) - u, a = i + u) : "right" === d ? (r = (i = f + g - c - u) - u, a = i + u) : (i = n.caretX, r = i - u, a = i + u), "top" === h) s = (o = p) - u, l = o; else {
                        s = (o = p + v) + u, l = o;
                        var m = a;
                        a = r, r = m
                    }
                    return {x1: r, x2: i, x3: a, y1: o, y2: s, y3: l}
                }, drawTitle: function (t, e, n) {
                    var r, i, a, o = e.title, s = o.length;
                    if (s) {
                        var l = Ie(e.rtl, e.x, e.width);
                        for (t.x = Ve(e, e._titleAlign), n.textAlign = l.textAlign(e._titleAlign), n.textBaseline = "middle", r = e.titleFontSize, i = e.titleSpacing, n.fillStyle = e.titleFontColor, n.font = B.fontString(r, e._titleFontStyle, e._titleFontFamily), a = 0; a < s; ++a) n.fillText(o[a], l.x(t.x), t.y + r / 2), t.y += r + i, a + 1 === s && (t.y += e.titleMarginBottom - i)
                    }
                }, drawBody: function (t, e, n) {
                    var r, i, a, o, s, l, u, c, d = e.bodyFontSize, h = e.bodySpacing, f = e._bodyAlign, p = e.body,
                        g = e.displayColors, v = 0, m = g ? Ve(e, "left") : 0, b = Ie(e.rtl, e.x, e.width),
                        y = function (e) {
                            n.fillText(e, b.x(t.x + v), t.y + d / 2), t.y += d + h
                        }, x = b.textAlign(f);
                    for (n.textAlign = f, n.textBaseline = "middle", n.font = B.fontString(d, e._bodyFontStyle, e._bodyFontFamily), t.x = Ve(e, x), n.fillStyle = e.bodyFontColor, B.each(e.beforeBody, y), v = g && "right" !== x ? "center" === f ? d / 2 + 1 : d + 2 : 0, s = 0, u = p.length; s < u; ++s) {
                        for (r = p[s], i = e.labelTextColors[s], a = e.labelColors[s], n.fillStyle = i, B.each(r.before, y), o = r.lines, l = 0, c = o.length; l < c; ++l) {
                            if (g) {
                                var _ = b.x(m);
                                n.fillStyle = e.legendColorBackground, n.fillRect(b.leftForLtr(_, d), t.y, d, d), n.lineWidth = 1, n.strokeStyle = a.borderColor, n.strokeRect(b.leftForLtr(_, d), t.y, d, d), n.fillStyle = a.backgroundColor, n.fillRect(b.leftForLtr(b.xPlus(_, 1), d - 2), t.y + 1, d - 2, d - 2), n.fillStyle = i
                            }
                            y(o[l])
                        }
                        B.each(r.after, y)
                    }
                    v = 0, B.each(e.afterBody, y), t.y -= h
                }, drawFooter: function (t, e, n) {
                    var r, i, a = e.footer, o = a.length;
                    if (o) {
                        var s = Ie(e.rtl, e.x, e.width);
                        for (t.x = Ve(e, e._footerAlign), t.y += e.footerMarginTop, n.textAlign = s.textAlign(e._footerAlign), n.textBaseline = "middle", r = e.footerFontSize, n.fillStyle = e.footerFontColor, n.font = B.fontString(r, e._footerFontStyle, e._footerFontFamily), i = 0; i < o; ++i) n.fillText(a[i], s.x(t.x), t.y + r / 2), t.y += r + e.footerSpacing
                    }
                }, drawBackground: function (t, e, n, r) {
                    n.fillStyle = e.backgroundColor, n.strokeStyle = e.borderColor, n.lineWidth = e.borderWidth;
                    var i = e.xAlign, a = e.yAlign, o = t.x, s = t.y, l = r.width, u = r.height, c = e.cornerRadius;
                    n.beginPath(), n.moveTo(o + c, s), "top" === a && this.drawCaret(t, r), n.lineTo(o + l - c, s), n.quadraticCurveTo(o + l, s, o + l, s + c), "center" === a && "right" === i && this.drawCaret(t, r), n.lineTo(o + l, s + u - c), n.quadraticCurveTo(o + l, s + u, o + l - c, s + u), "bottom" === a && this.drawCaret(t, r), n.lineTo(o + c, s + u), n.quadraticCurveTo(o, s + u, o, s + u - c), "center" === a && "left" === i && this.drawCaret(t, r), n.lineTo(o, s + c), n.quadraticCurveTo(o, s, o + c, s), n.closePath(), n.fill(), e.borderWidth > 0 && n.stroke()
                }, draw: function () {
                    var t = this._chart.ctx, e = this._view;
                    if (0 !== e.opacity) {
                        var n = {width: e.width, height: e.height}, r = {x: e.x, y: e.y},
                            i = Math.abs(e.opacity < .001) ? 0 : e.opacity,
                            a = e.title.length || e.beforeBody.length || e.body.length || e.afterBody.length || e.footer.length;
                        this._options.enabled && a && (t.save(), t.globalAlpha = i, this.drawBackground(r, e, t, n), r.y += e.yPadding, B.rtl.overrideTextDirection(t, e.textDirection), this.drawTitle(r, e, t), this.drawBody(r, e, t), this.drawFooter(r, e, t), B.rtl.restoreTextDirection(t, e.textDirection), t.restore())
                    }
                }, handleEvent: function (t) {
                    var e = this, n = e._options, r = !1;
                    return e._lastActive = e._lastActive || [], "mouseout" === t.type ? e._active = [] : (e._active = e._chart.getElementsAtEventForMode(t, n.mode, n), n.reverse && e._active.reverse()), (r = !B.arrayEquals(e._active, e._lastActive)) && (e._lastActive = e._active, (n.enabled || n.custom) && (e._eventPosition = {
                        x: t.x,
                        y: t.y
                    }, e.update(!0), e.pivot())), r
                }
            }), He = Re, Ue = We;
            Ue.positioners = He;
            var Ye = B.valueOrDefault;

            function qe() {
                return B.merge({}, [].slice.call(arguments), {
                    merger: function (t, e, n, r) {
                        if ("xAxes" === t || "yAxes" === t) {
                            var i, a, o, s = n[t].length;
                            for (e[t] || (e[t] = []), i = 0; i < s; ++i) o = n[t][i], a = Ye(o.type, "xAxes" === t ? "category" : "linear"), i >= e[t].length && e[t].push({}), !e[t][i].type || o.type && o.type !== e[t][i].type ? B.merge(e[t][i], [Oe.getScaleDefaults(a), o]) : B.merge(e[t][i], o)
                        } else B._merger(t, e, n, r)
                    }
                })
            }

            function $e() {
                return B.merge({}, [].slice.call(arguments), {
                    merger: function (t, e, n, r) {
                        var i = e[t] || {}, a = n[t];
                        "scales" === t ? e[t] = qe(i, a) : "scale" === t ? e[t] = B.merge(i, [Oe.getScaleDefaults(a.type), a]) : B._merger(t, e, n, r)
                    }
                })
            }

            function Xe(t, e, n) {
                var r, i = function (t) {
                    return t.id === r
                };
                do {
                    r = e + n++
                } while (B.findIndex(t, i) >= 0);
                return r
            }

            function Ge(t) {
                return "top" === t || "bottom" === t
            }

            function Ke(t, e) {
                return function (n, r) {
                    return n[t] === r[t] ? n[e] - r[e] : n[t] - r[t]
                }
            }

            L._set("global", {
                elements: {},
                events: ["mousemove", "mouseout", "click", "touchstart", "touchmove"],
                hover: {onHover: null, mode: "nearest", intersect: !0, animationDuration: 400},
                onClick: null,
                maintainAspectRatio: !0,
                responsive: !0,
                responsiveAnimationDuration: 0
            });
            var Ze = function (t, e) {
                return this.construct(t, e), this
            };
            B.extend(Ze.prototype, {
                construct: function (t, e) {
                    var n = this;
                    e = function (t) {
                        var e = (t = t || {}).data = t.data || {};
                        return e.datasets = e.datasets || [], e.labels = e.labels || [], t.options = $e(L.global, L[t.type], t.options || {}), t
                    }(e);
                    var r = Pe.acquireContext(t, e), i = r && r.canvas, a = i && i.height, o = i && i.width;
                    n.id = B.uid(), n.ctx = r, n.canvas = i, n.config = e, n.width = o, n.height = a, n.aspectRatio = a ? o / a : null, n.options = e.options, n._bufferedRender = !1, n._layers = [], n.chart = n, n.controller = n, Ze.instances[n.id] = n, Object.defineProperty(n, "data", {
                        get: function () {
                            return n.config.data
                        }, set: function (t) {
                            n.config.data = t
                        }
                    }), r && i ? (n.initialize(), n.update()) : console.error("Failed to create chart: can't acquire context from the given item")
                }, initialize: function () {
                    var t = this;
                    return De.notify(t, "beforeInit"), B.retinaScale(t, t.options.devicePixelRatio), t.bindEvents(), t.options.responsive && t.resize(!0), t.initToolTip(), De.notify(t, "afterInit"), t
                }, clear: function () {
                    return B.canvas.clear(this), this
                }, stop: function () {
                    return Z.cancelAnimation(this), this
                }, resize: function (t) {
                    var e = this, n = e.options, r = e.canvas, i = n.maintainAspectRatio && e.aspectRatio || null,
                        a = Math.max(0, Math.floor(B.getMaximumWidth(r))),
                        o = Math.max(0, Math.floor(i ? a / i : B.getMaximumHeight(r)));
                    if ((e.width !== a || e.height !== o) && (r.width = e.width = a, r.height = e.height = o, r.style.width = a + "px", r.style.height = o + "px", B.retinaScale(e, n.devicePixelRatio), !t)) {
                        var s = {width: a, height: o};
                        De.notify(e, "resize", [s]), n.onResize && n.onResize(e, s), e.stop(), e.update({duration: n.responsiveAnimationDuration})
                    }
                }, ensureScalesHaveIDs: function () {
                    var t = this.options, e = t.scales || {}, n = t.scale;
                    B.each(e.xAxes, function (t, n) {
                        t.id || (t.id = Xe(e.xAxes, "x-axis-", n))
                    }), B.each(e.yAxes, function (t, n) {
                        t.id || (t.id = Xe(e.yAxes, "y-axis-", n))
                    }), n && (n.id = n.id || "scale")
                }, buildOrUpdateScales: function () {
                    var t = this, e = t.options, n = t.scales || {}, r = [], i = Object.keys(n).reduce(function (t, e) {
                        return t[e] = !1, t
                    }, {});
                    e.scales && (r = r.concat((e.scales.xAxes || []).map(function (t) {
                        return {options: t, dtype: "category", dposition: "bottom"}
                    }), (e.scales.yAxes || []).map(function (t) {
                        return {options: t, dtype: "linear", dposition: "left"}
                    }))), e.scale && r.push({
                        options: e.scale,
                        dtype: "radialLinear",
                        isDefault: !0,
                        dposition: "chartArea"
                    }), B.each(r, function (e) {
                        var r = e.options, a = r.id, o = Ye(r.type, e.dtype);
                        Ge(r.position) !== Ge(e.dposition) && (r.position = e.dposition), i[a] = !0;
                        var s = null;
                        if (a in n && n[a].type === o) (s = n[a]).options = r, s.ctx = t.ctx, s.chart = t; else {
                            var l = Oe.getScaleConstructor(o);
                            if (!l) return;
                            s = new l({id: a, type: o, options: r, ctx: t.ctx, chart: t}), n[s.id] = s
                        }
                        s.mergeTicksOptions(), e.isDefault && (t.scale = s)
                    }), B.each(i, function (t, e) {
                        t || delete n[e]
                    }), t.scales = n, Oe.addScalesToLayout(this)
                }, buildOrUpdateControllers: function () {
                    var t, e, n = this, r = [], i = n.data.datasets;
                    for (t = 0, e = i.length; t < e; t++) {
                        var a = i[t], o = n.getDatasetMeta(t), s = a.type || n.config.type;
                        if (o.type && o.type !== s && (n.destroyDatasetMeta(t), o = n.getDatasetMeta(t)), o.type = s, o.order = a.order || 0, o.index = t, o.controller) o.controller.updateIndex(t), o.controller.linkScales(); else {
                            var l = Gt[o.type];
                            if (void 0 === l) throw new Error('"' + o.type + '" is not a chart type.');
                            o.controller = new l(n, t), r.push(o.controller)
                        }
                    }
                    return r
                }, resetElements: function () {
                    var t = this;
                    B.each(t.data.datasets, function (e, n) {
                        t.getDatasetMeta(n).controller.reset()
                    }, t)
                }, reset: function () {
                    this.resetElements(), this.tooltip.initialize()
                }, update: function (t) {
                    var e, n, r = this;
                    if (t && "object" === typeof t || (t = {duration: t, lazy: arguments[1]}), function (t) {
                        var e = t.options;
                        B.each(t.scales, function (e) {
                            he.removeBox(t, e)
                        }), e = $e(L.global, L[t.config.type], e), t.options = t.config.options = e, t.ensureScalesHaveIDs(), t.buildOrUpdateScales(), t.tooltip._options = e.tooltips, t.tooltip.initialize()
                    }(r), De._invalidate(r), !1 !== De.notify(r, "beforeUpdate")) {
                        r.tooltip._data = r.data;
                        var i = r.buildOrUpdateControllers();
                        for (e = 0, n = r.data.datasets.length; e < n; e++) r.getDatasetMeta(e).controller.buildOrUpdateElements();
                        r.updateLayout(), r.options.animation && r.options.animation.duration && B.each(i, function (t) {
                            t.reset()
                        }), r.updateDatasets(), r.tooltip.initialize(), r.lastActive = [], De.notify(r, "afterUpdate"), r._layers.sort(Ke("z", "_idx")), r._bufferedRender ? r._bufferedRequest = {
                            duration: t.duration,
                            easing: t.easing,
                            lazy: t.lazy
                        } : r.render(t)
                    }
                }, updateLayout: function () {
                    var t = this;
                    !1 !== De.notify(t, "beforeLayout") && (he.update(this, this.width, this.height), t._layers = [], B.each(t.boxes, function (e) {
                        e._configure && e._configure(), t._layers.push.apply(t._layers, e._layers())
                    }, t), t._layers.forEach(function (t, e) {
                        t._idx = e
                    }), De.notify(t, "afterScaleUpdate"), De.notify(t, "afterLayout"))
                }, updateDatasets: function () {
                    if (!1 !== De.notify(this, "beforeDatasetsUpdate")) {
                        for (var t = 0, e = this.data.datasets.length; t < e; ++t) this.updateDataset(t);
                        De.notify(this, "afterDatasetsUpdate")
                    }
                }, updateDataset: function (t) {
                    var e = this.getDatasetMeta(t), n = {meta: e, index: t};
                    !1 !== De.notify(this, "beforeDatasetUpdate", [n]) && (e.controller._update(), De.notify(this, "afterDatasetUpdate", [n]))
                }, render: function (t) {
                    var e = this;
                    t && "object" === typeof t || (t = {duration: t, lazy: arguments[1]});
                    var n = e.options.animation, r = Ye(t.duration, n && n.duration), i = t.lazy;
                    if (!1 !== De.notify(e, "beforeRender")) {
                        var a = function (t) {
                            De.notify(e, "afterRender"), B.callback(n && n.onComplete, [t], e)
                        };
                        if (n && r) {
                            var o = new K({
                                numSteps: r / 16.66, easing: t.easing || n.easing, render: function (t, e) {
                                    var n = B.easing.effects[e.easing], r = e.currentStep, i = r / e.numSteps;
                                    t.draw(n(i), i, r)
                                }, onAnimationProgress: n.onProgress, onAnimationComplete: a
                            });
                            Z.addAnimation(e, o, r, i)
                        } else e.draw(), a(new K({numSteps: 0, chart: e}));
                        return e
                    }
                }, draw: function (t) {
                    var e, n, r = this;
                    if (r.clear(), B.isNullOrUndef(t) && (t = 1), r.transition(t), !(r.width <= 0 || r.height <= 0) && !1 !== De.notify(r, "beforeDraw", [t])) {
                        for (n = r._layers, e = 0; e < n.length && n[e].z <= 0; ++e) n[e].draw(r.chartArea);
                        for (r.drawDatasets(t); e < n.length; ++e) n[e].draw(r.chartArea);
                        r._drawTooltip(t), De.notify(r, "afterDraw", [t])
                    }
                }, transition: function (t) {
                    for (var e = 0, n = (this.data.datasets || []).length; e < n; ++e) this.isDatasetVisible(e) && this.getDatasetMeta(e).controller.transition(t);
                    this.tooltip.transition(t)
                }, _getSortedDatasetMetas: function (t) {
                    var e, n, r = this.data.datasets || [], i = [];
                    for (e = 0, n = r.length; e < n; ++e) t && !this.isDatasetVisible(e) || i.push(this.getDatasetMeta(e));
                    return i.sort(Ke("order", "index")), i
                }, _getSortedVisibleDatasetMetas: function () {
                    return this._getSortedDatasetMetas(!0)
                }, drawDatasets: function (t) {
                    var e, n;
                    if (!1 !== De.notify(this, "beforeDatasetsDraw", [t])) {
                        for (e = this._getSortedVisibleDatasetMetas(), n = e.length - 1; n >= 0; --n) this.drawDataset(e[n], t);
                        De.notify(this, "afterDatasetsDraw", [t])
                    }
                }, drawDataset: function (t, e) {
                    var n = {meta: t, index: t.index, easingValue: e};
                    !1 !== De.notify(this, "beforeDatasetDraw", [n]) && (t.controller.draw(e), De.notify(this, "afterDatasetDraw", [n]))
                }, _drawTooltip: function (t) {
                    var e = this.tooltip, n = {tooltip: e, easingValue: t};
                    !1 !== De.notify(this, "beforeTooltipDraw", [n]) && (e.draw(), De.notify(this, "afterTooltipDraw", [n]))
                }, getElementAtEvent: function (t) {
                    return ne.modes.single(this, t)
                }, getElementsAtEvent: function (t) {
                    return ne.modes.label(this, t, {intersect: !0})
                }, getElementsAtXAxis: function (t) {
                    return ne.modes["x-axis"](this, t, {intersect: !0})
                }, getElementsAtEventForMode: function (t, e, n) {
                    var r = ne.modes[e];
                    return "function" === typeof r ? r(this, t, n) : []
                }, getDatasetAtEvent: function (t) {
                    return ne.modes.dataset(this, t, {intersect: !0})
                }, getDatasetMeta: function (t) {
                    var e = this.data.datasets[t];
                    e._meta || (e._meta = {});
                    var n = e._meta[this.id];
                    return n || (n = e._meta[this.id] = {
                        type: null,
                        data: [],
                        dataset: null,
                        controller: null,
                        hidden: null,
                        xAxisID: null,
                        yAxisID: null,
                        order: e.order || 0,
                        index: t
                    }), n
                }, getVisibleDatasetCount: function () {
                    for (var t = 0, e = 0, n = this.data.datasets.length; e < n; ++e) this.isDatasetVisible(e) && t++;
                    return t
                }, isDatasetVisible: function (t) {
                    var e = this.getDatasetMeta(t);
                    return "boolean" === typeof e.hidden ? !e.hidden : !this.data.datasets[t].hidden
                }, generateLegend: function () {
                    return this.options.legendCallback(this)
                }, destroyDatasetMeta: function (t) {
                    var e = this.id, n = this.data.datasets[t], r = n._meta && n._meta[e];
                    r && (r.controller.destroy(), delete n._meta[e])
                }, destroy: function () {
                    var t, e, n = this, r = n.canvas;
                    for (n.stop(), t = 0, e = n.data.datasets.length; t < e; ++t) n.destroyDatasetMeta(t);
                    r && (n.unbindEvents(), B.canvas.clear(n), Pe.releaseContext(n.ctx), n.canvas = null, n.ctx = null), De.notify(n, "destroy"), delete Ze.instances[n.id]
                }, toBase64Image: function () {
                    return this.canvas.toDataURL.apply(this.canvas, arguments)
                }, initToolTip: function () {
                    var t = this;
                    t.tooltip = new Ue({_chart: t, _chartInstance: t, _data: t.data, _options: t.options.tooltips}, t)
                }, bindEvents: function () {
                    var t = this, e = t._listeners = {}, n = function () {
                        t.eventHandler.apply(t, arguments)
                    };
                    B.each(t.options.events, function (r) {
                        Pe.addEventListener(t, r, n), e[r] = n
                    }), t.options.responsive && (n = function () {
                        t.resize()
                    }, Pe.addEventListener(t, "resize", n), e.resize = n)
                }, unbindEvents: function () {
                    var t = this, e = t._listeners;
                    e && (delete t._listeners, B.each(e, function (e, n) {
                        Pe.removeEventListener(t, n, e)
                    }))
                }, updateHoverStyle: function (t, e, n) {
                    var r, i, a, o = n ? "set" : "remove";
                    for (i = 0, a = t.length; i < a; ++i) (r = t[i]) && this.getDatasetMeta(r._datasetIndex).controller[o + "HoverStyle"](r);
                    "dataset" === e && this.getDatasetMeta(t[0]._datasetIndex).controller["_" + o + "DatasetHoverStyle"]()
                }, eventHandler: function (t) {
                    var e = this, n = e.tooltip;
                    if (!1 !== De.notify(e, "beforeEvent", [t])) {
                        e._bufferedRender = !0, e._bufferedRequest = null;
                        var r = e.handleEvent(t);
                        n && (r = n._start ? n.handleEvent(t) : r | n.handleEvent(t)), De.notify(e, "afterEvent", [t]);
                        var i = e._bufferedRequest;
                        return i ? e.render(i) : r && !e.animating && (e.stop(), e.render({
                            duration: e.options.hover.animationDuration,
                            lazy: !0
                        })), e._bufferedRender = !1, e._bufferedRequest = null, e
                    }
                }, handleEvent: function (t) {
                    var e = this, n = e.options || {}, r = n.hover, i = !1;
                    return e.lastActive = e.lastActive || [], "mouseout" === t.type ? e.active = [] : e.active = e.getElementsAtEventForMode(t, r.mode, r), B.callback(n.onHover || n.hover.onHover, [t.native, e.active], e), "mouseup" !== t.type && "click" !== t.type || n.onClick && n.onClick.call(e, t.native, e.active), e.lastActive.length && e.updateHoverStyle(e.lastActive, r.mode, !1), e.active.length && r.mode && e.updateHoverStyle(e.active, r.mode, !0), i = !B.arrayEquals(e.active, e.lastActive), e.lastActive = e.active, i
                }
            }), Ze.instances = {};
            var Je = Ze;

            function Qe() {
                throw new Error("This method is not implemented: either no adapter can be found or an incomplete integration was provided.")
            }

            function tn(t) {
                this.options = t || {}
            }

            Ze.Controller = Ze, Ze.types = {}, B.configMerge = $e, B.scaleMerge = qe, B.extend(tn.prototype, {
                formats: Qe,
                parse: Qe,
                format: Qe,
                add: Qe,
                diff: Qe,
                startOf: Qe,
                endOf: Qe,
                _create: function (t) {
                    return t
                }
            }), tn.override = function (t) {
                B.extend(tn.prototype, t)
            };
            var en = {_date: tn}, nn = {
                formatters: {
                    values: function (t) {
                        return B.isArray(t) ? t : "" + t
                    }, linear: function (t, e, n) {
                        var r = n.length > 3 ? n[2] - n[1] : n[1] - n[0];
                        Math.abs(r) > 1 && t !== Math.floor(t) && (r = t - Math.floor(t));
                        var i = B.log10(Math.abs(r)), a = "";
                        if (0 !== t) {
                            var o = Math.max(Math.abs(n[0]), Math.abs(n[n.length - 1]));
                            if (o < 1e-4) {
                                var s = B.log10(Math.abs(t)), l = Math.floor(s) - Math.floor(i);
                                l = Math.max(Math.min(l, 20), 0), a = t.toExponential(l)
                            } else {
                                var u = -1 * Math.floor(i);
                                u = Math.max(Math.min(u, 20), 0), a = t.toFixed(u)
                            }
                        } else a = "0";
                        return a
                    }, logarithmic: function (t, e, n) {
                        var r = t / Math.pow(10, Math.floor(B.log10(t)));
                        return 0 === t ? "0" : 1 === r || 2 === r || 5 === r || 0 === e || e === n.length - 1 ? t.toExponential() : ""
                    }
                }
            }, rn = B.isArray, an = B.isNullOrUndef, on = B.valueOrDefault, sn = B.valueAtIndexOrDefault;

            function ln(t, e, n) {
                var r, i = t.getTicks().length, a = Math.min(e, i - 1), o = t.getPixelForTick(a), s = t._startPixel,
                    l = t._endPixel;
                if (!(n && (r = 1 === i ? Math.max(o - s, l - o) : 0 === e ? (t.getPixelForTick(1) - o) / 2 : (o - t.getPixelForTick(a - 1)) / 2, (o += a < e ? r : -r) < s - 1e-6 || o > l + 1e-6))) return o
            }

            function un(t, e, n, r) {
                var i, a, o, s, l, u, c, d, h, f, p, g, v, m = n.length, b = [], y = [], x = [];
                for (i = 0; i < m; ++i) {
                    if (s = n[i].label, l = n[i].major ? e.major : e.minor, t.font = u = l.string, c = r[u] = r[u] || {
                        data: {},
                        gc: []
                    }, d = l.lineHeight, h = f = 0, an(s) || rn(s)) {
                        if (rn(s)) for (a = 0, o = s.length; a < o; ++a) p = s[a], an(p) || rn(p) || (h = B.measureText(t, c.data, c.gc, h, p), f += d)
                    } else h = B.measureText(t, c.data, c.gc, h, s), f = d;
                    b.push(h), y.push(f), x.push(d / 2)
                }

                function _(t) {
                    return {width: b[t] || 0, height: y[t] || 0, offset: x[t] || 0}
                }

                return function (t, e) {
                    B.each(t, function (t) {
                        var n, r = t.gc, i = r.length / 2;
                        if (i > e) {
                            for (n = 0; n < i; ++n) delete t.data[r[n]];
                            r.splice(0, i)
                        }
                    })
                }(r, m), g = b.indexOf(Math.max.apply(null, b)), v = y.indexOf(Math.max.apply(null, y)), {
                    first: _(0),
                    last: _(m - 1),
                    widest: _(g),
                    highest: _(v)
                }
            }

            function cn(t) {
                return t.drawTicks ? t.tickMarkLength : 0
            }

            function dn(t) {
                var e, n;
                return t.display ? (e = B.options._parseFont(t), n = B.options.toPadding(t.padding), e.lineHeight + n.height) : 0
            }

            function hn(t, e) {
                return B.extend(B.options._parseFont({
                    fontFamily: on(e.fontFamily, t.fontFamily),
                    fontSize: on(e.fontSize, t.fontSize),
                    fontStyle: on(e.fontStyle, t.fontStyle),
                    lineHeight: on(e.lineHeight, t.lineHeight)
                }), {color: B.options.resolve([e.fontColor, t.fontColor, L.global.defaultFontColor])})
            }

            function fn(t) {
                var e = hn(t, t.minor), n = t.major.enabled ? hn(t, t.major) : e;
                return {minor: e, major: n}
            }

            function pn(t) {
                var e, n, r, i = [];
                for (n = 0, r = t.length; n < r; ++n) "undefined" !== typeof (e = t[n])._index && i.push(e);
                return i
            }

            function gn(t, e, n, r) {
                var i, a, o, s, l = on(n, 0), u = Math.min(on(r, t.length), t.length), c = 0;
                for (e = Math.ceil(e), r && (e = (i = r - n) / Math.floor(i / e)), s = l; s < 0;) c++, s = Math.round(l + c * e);
                for (a = Math.max(l, 0); a < u; a++) o = t[a], a === s ? (o._index = a, c++, s = Math.round(l + c * e)) : delete o.label
            }

            L._set("scale", {
                display: !0,
                position: "left",
                offset: !1,
                gridLines: {
                    display: !0,
                    color: "rgba(0,0,0,0.1)",
                    lineWidth: 1,
                    drawBorder: !0,
                    drawOnChartArea: !0,
                    drawTicks: !0,
                    tickMarkLength: 10,
                    zeroLineWidth: 1,
                    zeroLineColor: "rgba(0,0,0,0.25)",
                    zeroLineBorderDash: [],
                    zeroLineBorderDashOffset: 0,
                    offsetGridLines: !1,
                    borderDash: [],
                    borderDashOffset: 0
                },
                scaleLabel: {display: !1, labelString: "", padding: {top: 4, bottom: 4}},
                ticks: {
                    beginAtZero: !1,
                    minRotation: 0,
                    maxRotation: 50,
                    mirror: !1,
                    padding: 0,
                    reverse: !1,
                    display: !0,
                    autoSkip: !0,
                    autoSkipPadding: 0,
                    labelOffset: 0,
                    callback: nn.formatters.values,
                    minor: {},
                    major: {}
                }
            });
            var vn = X.extend({
                zeroLineIndex: 0,
                getPadding: function () {
                    return {
                        left: this.paddingLeft || 0,
                        top: this.paddingTop || 0,
                        right: this.paddingRight || 0,
                        bottom: this.paddingBottom || 0
                    }
                },
                getTicks: function () {
                    return this._ticks
                },
                _getLabels: function () {
                    var t = this.chart.data;
                    return this.options.labels || (this.isHorizontal() ? t.xLabels : t.yLabels) || t.labels || []
                },
                mergeTicksOptions: function () {
                },
                beforeUpdate: function () {
                    B.callback(this.options.beforeUpdate, [this])
                },
                update: function (t, e, n) {
                    var r, i, a, o, s, l = this, u = l.options.ticks, c = u.sampleSize;
                    if (l.beforeUpdate(), l.maxWidth = t, l.maxHeight = e, l.margins = B.extend({
                        left: 0,
                        right: 0,
                        top: 0,
                        bottom: 0
                    }, n), l._ticks = null, l.ticks = null, l._labelSizes = null, l._maxLabelLines = 0, l.longestLabelWidth = 0, l.longestTextCache = l.longestTextCache || {}, l._gridLineItems = null, l._labelItems = null, l.beforeSetDimensions(), l.setDimensions(), l.afterSetDimensions(), l.beforeDataLimits(), l.determineDataLimits(), l.afterDataLimits(), l.beforeBuildTicks(), o = l.buildTicks() || [], (!(o = l.afterBuildTicks(o) || o) || !o.length) && l.ticks) for (o = [], r = 0, i = l.ticks.length; r < i; ++r) o.push({
                        value: l.ticks[r],
                        major: !1
                    });
                    return l._ticks = o, s = c < o.length, a = l._convertTicksToLabels(s ? function (t, e) {
                        for (var n = [], r = t.length / e, i = 0, a = t.length; i < a; i += r) n.push(t[Math.floor(i)]);
                        return n
                    }(o, c) : o), l._configure(), l.beforeCalculateTickRotation(), l.calculateTickRotation(), l.afterCalculateTickRotation(), l.beforeFit(), l.fit(), l.afterFit(), l._ticksToDraw = u.display && (u.autoSkip || "auto" === u.source) ? l._autoSkip(o) : o, s && (a = l._convertTicksToLabels(l._ticksToDraw)), l.ticks = a, l.afterUpdate(), l.minSize
                },
                _configure: function () {
                    var t, e, n = this, r = n.options.ticks.reverse;
                    n.isHorizontal() ? (t = n.left, e = n.right) : (t = n.top, e = n.bottom, r = !r), n._startPixel = t, n._endPixel = e, n._reversePixels = r, n._length = e - t
                },
                afterUpdate: function () {
                    B.callback(this.options.afterUpdate, [this])
                },
                beforeSetDimensions: function () {
                    B.callback(this.options.beforeSetDimensions, [this])
                },
                setDimensions: function () {
                    var t = this;
                    t.isHorizontal() ? (t.width = t.maxWidth, t.left = 0, t.right = t.width) : (t.height = t.maxHeight, t.top = 0, t.bottom = t.height), t.paddingLeft = 0, t.paddingTop = 0, t.paddingRight = 0, t.paddingBottom = 0
                },
                afterSetDimensions: function () {
                    B.callback(this.options.afterSetDimensions, [this])
                },
                beforeDataLimits: function () {
                    B.callback(this.options.beforeDataLimits, [this])
                },
                determineDataLimits: B.noop,
                afterDataLimits: function () {
                    B.callback(this.options.afterDataLimits, [this])
                },
                beforeBuildTicks: function () {
                    B.callback(this.options.beforeBuildTicks, [this])
                },
                buildTicks: B.noop,
                afterBuildTicks: function (t) {
                    var e = this;
                    return rn(t) && t.length ? B.callback(e.options.afterBuildTicks, [e, t]) : (e.ticks = B.callback(e.options.afterBuildTicks, [e, e.ticks]) || e.ticks, t)
                },
                beforeTickToLabelConversion: function () {
                    B.callback(this.options.beforeTickToLabelConversion, [this])
                },
                convertTicksToLabels: function () {
                    var t = this.options.ticks;
                    this.ticks = this.ticks.map(t.userCallback || t.callback, this)
                },
                afterTickToLabelConversion: function () {
                    B.callback(this.options.afterTickToLabelConversion, [this])
                },
                beforeCalculateTickRotation: function () {
                    B.callback(this.options.beforeCalculateTickRotation, [this])
                },
                calculateTickRotation: function () {
                    var t, e, n, r, i, a, o, s = this, l = s.options, u = l.ticks, c = s.getTicks().length,
                        d = u.minRotation || 0, h = u.maxRotation, f = d;
                    !s._isVisible() || !u.display || d >= h || c <= 1 || !s.isHorizontal() ? s.labelRotation = d : (t = s._getLabelSizes(), e = t.widest.width, n = t.highest.height - t.highest.offset, r = Math.min(s.maxWidth, s.chart.width - e), i = l.offset ? s.maxWidth / c : r / (c - 1), e + 6 > i && (i = r / (c - (l.offset ? .5 : 1)), a = s.maxHeight - cn(l.gridLines) - u.padding - dn(l.scaleLabel), o = Math.sqrt(e * e + n * n), f = B.toDegrees(Math.min(Math.asin(Math.min((t.highest.height + 6) / i, 1)), Math.asin(Math.min(a / o, 1)) - Math.asin(n / o))), f = Math.max(d, Math.min(h, f))), s.labelRotation = f)
                },
                afterCalculateTickRotation: function () {
                    B.callback(this.options.afterCalculateTickRotation, [this])
                },
                beforeFit: function () {
                    B.callback(this.options.beforeFit, [this])
                },
                fit: function () {
                    var t = this, e = t.minSize = {width: 0, height: 0}, n = t.chart, r = t.options, i = r.ticks,
                        a = r.scaleLabel, o = r.gridLines, s = t._isVisible(), l = "bottom" === r.position,
                        u = t.isHorizontal();
                    if (u ? e.width = t.maxWidth : s && (e.width = cn(o) + dn(a)), u ? s && (e.height = cn(o) + dn(a)) : e.height = t.maxHeight, i.display && s) {
                        var c = fn(i), d = t._getLabelSizes(), h = d.first, f = d.last, p = d.widest, g = d.highest,
                            v = .4 * c.minor.lineHeight, m = i.padding;
                        if (u) {
                            var b = 0 !== t.labelRotation, y = B.toRadians(t.labelRotation), x = Math.cos(y),
                                _ = Math.sin(y), w = _ * p.width + x * (g.height - (b ? g.offset : 0)) + (b ? 0 : v);
                            e.height = Math.min(t.maxHeight, e.height + w + m);
                            var k, M, F = t.getPixelForTick(0) - t.left,
                                S = t.right - t.getPixelForTick(t.getTicks().length - 1);
                            b ? (k = l ? x * h.width + _ * h.offset : _ * (h.height - h.offset), M = l ? _ * (f.height - f.offset) : x * f.width + _ * f.offset) : (k = h.width / 2, M = f.width / 2), t.paddingLeft = Math.max((k - F) * t.width / (t.width - F), 0) + 3, t.paddingRight = Math.max((M - S) * t.width / (t.width - S), 0) + 3
                        } else {
                            var C = i.mirror ? 0 : p.width + m + v;
                            e.width = Math.min(t.maxWidth, e.width + C), t.paddingTop = h.height / 2, t.paddingBottom = f.height / 2
                        }
                    }
                    t.handleMargins(), u ? (t.width = t._length = n.width - t.margins.left - t.margins.right, t.height = e.height) : (t.width = e.width, t.height = t._length = n.height - t.margins.top - t.margins.bottom)
                },
                handleMargins: function () {
                    var t = this;
                    t.margins && (t.margins.left = Math.max(t.paddingLeft, t.margins.left), t.margins.top = Math.max(t.paddingTop, t.margins.top), t.margins.right = Math.max(t.paddingRight, t.margins.right), t.margins.bottom = Math.max(t.paddingBottom, t.margins.bottom))
                },
                afterFit: function () {
                    B.callback(this.options.afterFit, [this])
                },
                isHorizontal: function () {
                    var t = this.options.position;
                    return "top" === t || "bottom" === t
                },
                isFullWidth: function () {
                    return this.options.fullWidth
                },
                getRightValue: function (t) {
                    if (an(t)) return NaN;
                    if (("number" === typeof t || t instanceof Number) && !isFinite(t)) return NaN;
                    if (t) if (this.isHorizontal()) {
                        if (void 0 !== t.x) return this.getRightValue(t.x)
                    } else if (void 0 !== t.y) return this.getRightValue(t.y);
                    return t
                },
                _convertTicksToLabels: function (t) {
                    var e, n, r, i = this;
                    for (i.ticks = t.map(function (t) {
                        return t.value
                    }), i.beforeTickToLabelConversion(), e = i.convertTicksToLabels(t) || i.ticks, i.afterTickToLabelConversion(), n = 0, r = t.length; n < r; ++n) t[n].label = e[n];
                    return e
                },
                _getLabelSizes: function () {
                    var t = this, e = t._labelSizes;
                    return e || (t._labelSizes = e = un(t.ctx, fn(t.options.ticks), t.getTicks(), t.longestTextCache), t.longestLabelWidth = e.widest.width), e
                },
                _parseValue: function (t) {
                    var e, n, r, i;
                    return rn(t) ? (e = +this.getRightValue(t[0]), n = +this.getRightValue(t[1]), r = Math.min(e, n), i = Math.max(e, n)) : (t = +this.getRightValue(t), e = void 0, n = t, r = t, i = t), {
                        min: r,
                        max: i,
                        start: e,
                        end: n
                    }
                },
                _getScaleLabel: function (t) {
                    var e = this._parseValue(t);
                    return void 0 !== e.start ? "[" + e.start + ", " + e.end + "]" : +this.getRightValue(t)
                },
                getLabelForIndex: B.noop,
                getPixelForValue: B.noop,
                getValueForPixel: B.noop,
                getPixelForTick: function (t) {
                    var e = this.options.offset, n = this._ticks.length, r = 1 / Math.max(n - (e ? 0 : 1), 1);
                    return t < 0 || t > n - 1 ? null : this.getPixelForDecimal(t * r + (e ? r / 2 : 0))
                },
                getPixelForDecimal: function (t) {
                    return this._reversePixels && (t = 1 - t), this._startPixel + t * this._length
                },
                getDecimalForPixel: function (t) {
                    var e = (t - this._startPixel) / this._length;
                    return this._reversePixels ? 1 - e : e
                },
                getBasePixel: function () {
                    return this.getPixelForValue(this.getBaseValue())
                },
                getBaseValue: function () {
                    var t = this.min, e = this.max;
                    return this.beginAtZero ? 0 : t < 0 && e < 0 ? e : t > 0 && e > 0 ? t : 0
                },
                _autoSkip: function (t) {
                    var e, n, r, i, a = this.options.ticks, o = this._length,
                        s = a.maxTicksLimit || o / this._tickSize() + 1, l = a.major.enabled ? function (t) {
                            var e, n, r = [];
                            for (e = 0, n = t.length; e < n; e++) t[e].major && r.push(e);
                            return r
                        }(t) : [], u = l.length, c = l[0], d = l[u - 1];
                    if (u > s) return function (t, e, n) {
                        var r, i, a = 0, o = e[0];
                        for (n = Math.ceil(n), r = 0; r < t.length; r++) i = t[r], r === o ? (i._index = r, o = e[++a * n]) : delete i.label
                    }(t, l, u / s), pn(t);
                    if (r = function (t, e, n, r) {
                        var i, a, o, s, l = function (t) {
                            var e, n, r = t.length;
                            if (r < 2) return !1;
                            for (n = t[0], e = 1; e < r; ++e) if (t[e] - t[e - 1] !== n) return !1;
                            return n
                        }(t), u = (e.length - 1) / r;
                        if (!l) return Math.max(u, 1);
                        for (i = B.math._factorize(l), o = 0, s = i.length - 1; o < s; o++) if ((a = i[o]) > u) return a;
                        return Math.max(u, 1)
                    }(l, t, 0, s), u > 0) {
                        for (e = 0, n = u - 1; e < n; e++) gn(t, r, l[e], l[e + 1]);
                        return i = u > 1 ? (d - c) / (u - 1) : null, gn(t, r, B.isNullOrUndef(i) ? 0 : c - i, c), gn(t, r, d, B.isNullOrUndef(i) ? t.length : d + i), pn(t)
                    }
                    return gn(t, r), pn(t)
                },
                _tickSize: function () {
                    var t = this.options.ticks, e = B.toRadians(this.labelRotation), n = Math.abs(Math.cos(e)),
                        r = Math.abs(Math.sin(e)), i = this._getLabelSizes(), a = t.autoSkipPadding || 0,
                        o = i ? i.widest.width + a : 0, s = i ? i.highest.height + a : 0;
                    return this.isHorizontal() ? s * n > o * r ? o / n : s / r : s * r < o * n ? s / n : o / r
                },
                _isVisible: function () {
                    var t, e, n, r = this.chart, i = this.options.display;
                    if ("auto" !== i) return !!i;
                    for (t = 0, e = r.data.datasets.length; t < e; ++t) if (r.isDatasetVisible(t) && ((n = r.getDatasetMeta(t)).xAxisID === this.id || n.yAxisID === this.id)) return !0;
                    return !1
                },
                _computeGridLineItems: function (t) {
                    var e, n, r, i, a, o, s, l, u, c, d, h, f, p, g, v, m, b = this, y = b.chart, x = b.options,
                        _ = x.gridLines, w = x.position, k = _.offsetGridLines, M = b.isHorizontal(),
                        F = b._ticksToDraw, S = F.length + (k ? 1 : 0), C = cn(_), A = [],
                        T = _.drawBorder ? sn(_.lineWidth, 0, 0) : 0, P = T / 2, D = B._alignPixel, O = function (t) {
                            return D(y, t, T)
                        };
                    for ("top" === w ? (e = O(b.bottom), s = b.bottom - C, u = e - P, d = O(t.top) + P, f = t.bottom) : "bottom" === w ? (e = O(b.top), d = t.top, f = O(t.bottom) - P, s = e + P, u = b.top + C) : "left" === w ? (e = O(b.right), o = b.right - C, l = e - P, c = O(t.left) + P, h = t.right) : (e = O(b.left), c = t.left, h = O(t.right) - P, o = e + P, l = b.left + C), n = 0; n < S; ++n) r = F[n] || {}, an(r.label) && n < F.length || (n === b.zeroLineIndex && x.offset === k ? (p = _.zeroLineWidth, g = _.zeroLineColor, v = _.zeroLineBorderDash || [], m = _.zeroLineBorderDashOffset || 0) : (p = sn(_.lineWidth, n, 1), g = sn(_.color, n, "rgba(0,0,0,0.1)"), v = _.borderDash || [], m = _.borderDashOffset || 0), void 0 !== (i = ln(b, r._index || n, k)) && (a = D(y, i, p), M ? o = l = c = h = a : s = u = d = f = a, A.push({
                        tx1: o,
                        ty1: s,
                        tx2: l,
                        ty2: u,
                        x1: c,
                        y1: d,
                        x2: h,
                        y2: f,
                        width: p,
                        color: g,
                        borderDash: v,
                        borderDashOffset: m
                    })));
                    return A.ticksLength = S, A.borderValue = e, A
                },
                _computeLabelItems: function () {
                    var t, e, n, r, i, a, o, s, l, u, c, d, h = this, f = h.options, p = f.ticks, g = f.position,
                        v = p.mirror, m = h.isHorizontal(), b = h._ticksToDraw, y = fn(p), x = p.padding,
                        _ = cn(f.gridLines), w = -B.toRadians(h.labelRotation), k = [];
                    for ("top" === g ? (a = h.bottom - _ - x, o = w ? "left" : "center") : "bottom" === g ? (a = h.top + _ + x, o = w ? "right" : "center") : "left" === g ? (i = h.right - (v ? 0 : _) - x, o = v ? "left" : "right") : (i = h.left + (v ? 0 : _) + x, o = v ? "right" : "left"), t = 0, e = b.length; t < e; ++t) n = b[t], r = n.label, an(r) || (s = h.getPixelForTick(n._index || t) + p.labelOffset, l = n.major ? y.major : y.minor, u = l.lineHeight, c = rn(r) ? r.length : 1, m ? (i = s, d = "top" === g ? ((w ? 1 : .5) - c) * u : (w ? 0 : .5) * u) : (a = s, d = (1 - c) * u / 2), k.push({
                        x: i,
                        y: a,
                        rotation: w,
                        label: r,
                        font: l,
                        textOffset: d,
                        textAlign: o
                    }));
                    return k
                },
                _drawGrid: function (t) {
                    var e = this, n = e.options.gridLines;
                    if (n.display) {
                        var r, i, a, o, s, l = e.ctx, u = e.chart, c = B._alignPixel,
                            d = n.drawBorder ? sn(n.lineWidth, 0, 0) : 0,
                            h = e._gridLineItems || (e._gridLineItems = e._computeGridLineItems(t));
                        for (a = 0, o = h.length; a < o; ++a) s = h[a], r = s.width, i = s.color, r && i && (l.save(), l.lineWidth = r, l.strokeStyle = i, l.setLineDash && (l.setLineDash(s.borderDash), l.lineDashOffset = s.borderDashOffset), l.beginPath(), n.drawTicks && (l.moveTo(s.tx1, s.ty1), l.lineTo(s.tx2, s.ty2)), n.drawOnChartArea && (l.moveTo(s.x1, s.y1), l.lineTo(s.x2, s.y2)), l.stroke(), l.restore());
                        if (d) {
                            var f, p, g, v, m = d, b = sn(n.lineWidth, h.ticksLength - 1, 1), y = h.borderValue;
                            e.isHorizontal() ? (f = c(u, e.left, m) - m / 2, p = c(u, e.right, b) + b / 2, g = v = y) : (g = c(u, e.top, m) - m / 2, v = c(u, e.bottom, b) + b / 2, f = p = y), l.lineWidth = d, l.strokeStyle = sn(n.color, 0), l.beginPath(), l.moveTo(f, g), l.lineTo(p, v), l.stroke()
                        }
                    }
                },
                _drawLabels: function () {
                    var t = this, e = t.options.ticks;
                    if (e.display) {
                        var n, r, i, a, o, s, l, u, c = t.ctx,
                            d = t._labelItems || (t._labelItems = t._computeLabelItems());
                        for (n = 0, i = d.length; n < i; ++n) {
                            if (o = d[n], s = o.font, c.save(), c.translate(o.x, o.y), c.rotate(o.rotation), c.font = s.string, c.fillStyle = s.color, c.textBaseline = "middle", c.textAlign = o.textAlign, l = o.label, u = o.textOffset, rn(l)) for (r = 0, a = l.length; r < a; ++r) c.fillText("" + l[r], 0, u), u += s.lineHeight; else c.fillText(l, 0, u);
                            c.restore()
                        }
                    }
                },
                _drawTitle: function () {
                    var t = this, e = t.ctx, n = t.options, r = n.scaleLabel;
                    if (r.display) {
                        var i, a, o = on(r.fontColor, L.global.defaultFontColor), s = B.options._parseFont(r),
                            l = B.options.toPadding(r.padding), u = s.lineHeight / 2, c = n.position, d = 0;
                        if (t.isHorizontal()) i = t.left + t.width / 2, a = "bottom" === c ? t.bottom - u - l.bottom : t.top + u + l.top; else {
                            var h = "left" === c;
                            i = h ? t.left + u + l.top : t.right - u - l.top, a = t.top + t.height / 2, d = h ? -.5 * Math.PI : .5 * Math.PI
                        }
                        e.save(), e.translate(i, a), e.rotate(d), e.textAlign = "center", e.textBaseline = "middle", e.fillStyle = o, e.font = s.string, e.fillText(r.labelString, 0, 0), e.restore()
                    }
                },
                draw: function (t) {
                    this._isVisible() && (this._drawGrid(t), this._drawTitle(), this._drawLabels())
                },
                _layers: function () {
                    var t = this, e = t.options, n = e.ticks && e.ticks.z || 0, r = e.gridLines && e.gridLines.z || 0;
                    return t._isVisible() && n !== r && t.draw === t._draw ? [{
                        z: r, draw: function () {
                            t._drawGrid.apply(t, arguments), t._drawTitle.apply(t, arguments)
                        }
                    }, {
                        z: n, draw: function () {
                            t._drawLabels.apply(t, arguments)
                        }
                    }] : [{
                        z: n, draw: function () {
                            t.draw.apply(t, arguments)
                        }
                    }]
                },
                _getMatchingVisibleMetas: function (t) {
                    var e = this, n = e.isHorizontal();
                    return e.chart._getSortedVisibleDatasetMetas().filter(function (r) {
                        return (!t || r.type === t) && (n ? r.xAxisID === e.id : r.yAxisID === e.id)
                    })
                }
            });
            vn.prototype._draw = vn.prototype.draw;
            var mn = vn, bn = B.isNullOrUndef, yn = mn.extend({
                determineDataLimits: function () {
                    var t, e = this, n = e._getLabels(), r = e.options.ticks, i = r.min, a = r.max, o = 0,
                        s = n.length - 1;
                    void 0 !== i && (t = n.indexOf(i)) >= 0 && (o = t), void 0 !== a && (t = n.indexOf(a)) >= 0 && (s = t), e.minIndex = o, e.maxIndex = s, e.min = n[o], e.max = n[s]
                }, buildTicks: function () {
                    var t = this._getLabels(), e = this.minIndex, n = this.maxIndex;
                    this.ticks = 0 === e && n === t.length - 1 ? t : t.slice(e, n + 1)
                }, getLabelForIndex: function (t, e) {
                    var n = this.chart;
                    return n.getDatasetMeta(e).controller._getValueScaleId() === this.id ? this.getRightValue(n.data.datasets[e].data[t]) : this._getLabels()[t]
                }, _configure: function () {
                    var t = this, e = t.options.offset, n = t.ticks;
                    mn.prototype._configure.call(t), t.isHorizontal() || (t._reversePixels = !t._reversePixels), n && (t._startValue = t.minIndex - (e ? .5 : 0), t._valueRange = Math.max(n.length - (e ? 0 : 1), 1))
                }, getPixelForValue: function (t, e, n) {
                    var r, i, a, o = this;
                    return bn(e) || bn(n) || (t = o.chart.data.datasets[n].data[e]), bn(t) || (r = o.isHorizontal() ? t.x : t.y), (void 0 !== r || void 0 !== t && isNaN(e)) && (i = o._getLabels(), t = B.valueOrDefault(r, t), a = i.indexOf(t), e = -1 !== a ? a : e, isNaN(e) && (e = t)), o.getPixelForDecimal((e - o._startValue) / o._valueRange)
                }, getPixelForTick: function (t) {
                    var e = this.ticks;
                    return t < 0 || t > e.length - 1 ? null : this.getPixelForValue(e[t], t + this.minIndex)
                }, getValueForPixel: function (t) {
                    var e = Math.round(this._startValue + this.getDecimalForPixel(t) * this._valueRange);
                    return Math.min(Math.max(e, 0), this.ticks.length - 1)
                }, getBasePixel: function () {
                    return this.bottom
                }
            }), xn = {position: "bottom"};
            yn._defaults = xn;
            var _n = B.noop, wn = B.isNullOrUndef, kn = mn.extend({
                getRightValue: function (t) {
                    return "string" === typeof t ? +t : mn.prototype.getRightValue.call(this, t)
                }, handleTickRangeOptions: function () {
                    var t = this, e = t.options, n = e.ticks;
                    if (n.beginAtZero) {
                        var r = B.sign(t.min), i = B.sign(t.max);
                        r < 0 && i < 0 ? t.max = 0 : r > 0 && i > 0 && (t.min = 0)
                    }
                    var a = void 0 !== n.min || void 0 !== n.suggestedMin,
                        o = void 0 !== n.max || void 0 !== n.suggestedMax;
                    void 0 !== n.min ? t.min = n.min : void 0 !== n.suggestedMin && (null === t.min ? t.min = n.suggestedMin : t.min = Math.min(t.min, n.suggestedMin)), void 0 !== n.max ? t.max = n.max : void 0 !== n.suggestedMax && (null === t.max ? t.max = n.suggestedMax : t.max = Math.max(t.max, n.suggestedMax)), a !== o && t.min >= t.max && (a ? t.max = t.min + 1 : t.min = t.max - 1), t.min === t.max && (t.max++, n.beginAtZero || t.min--)
                }, getTickLimit: function () {
                    var t, e = this.options.ticks, n = e.stepSize, r = e.maxTicksLimit;
                    return n ? t = Math.ceil(this.max / n) - Math.floor(this.min / n) + 1 : (t = this._computeTickLimit(), r = r || 11), r && (t = Math.min(r, t)), t
                }, _computeTickLimit: function () {
                    return Number.POSITIVE_INFINITY
                }, handleDirectionalChanges: _n, buildTicks: function () {
                    var t = this, e = t.options, n = e.ticks, r = t.getTickLimit(), i = {
                        maxTicks: r = Math.max(2, r),
                        min: n.min,
                        max: n.max,
                        precision: n.precision,
                        stepSize: B.valueOrDefault(n.fixedStepSize, n.stepSize)
                    }, a = t.ticks = function (t, e) {
                        var n, r, i, a, o = [], s = t.stepSize, l = s || 1, u = t.maxTicks - 1, c = t.min, d = t.max,
                            h = t.precision, f = e.min, p = e.max, g = B.niceNum((p - f) / u / l) * l;
                        if (g < 1e-14 && wn(c) && wn(d)) return [f, p];
                        (a = Math.ceil(p / g) - Math.floor(f / g)) > u && (g = B.niceNum(a * g / u / l) * l), s || wn(h) ? n = Math.pow(10, B._decimalPlaces(g)) : (n = Math.pow(10, h), g = Math.ceil(g * n) / n), r = Math.floor(f / g) * g, i = Math.ceil(p / g) * g, s && (!wn(c) && B.almostWhole(c / g, g / 1e3) && (r = c), !wn(d) && B.almostWhole(d / g, g / 1e3) && (i = d)), a = (i - r) / g, a = B.almostEquals(a, Math.round(a), g / 1e3) ? Math.round(a) : Math.ceil(a), r = Math.round(r * n) / n, i = Math.round(i * n) / n, o.push(wn(c) ? r : c);
                        for (var v = 1; v < a; ++v) o.push(Math.round((r + v * g) * n) / n);
                        return o.push(wn(d) ? i : d), o
                    }(i, t);
                    t.handleDirectionalChanges(), t.max = B.max(a), t.min = B.min(a), n.reverse ? (a.reverse(), t.start = t.max, t.end = t.min) : (t.start = t.min, t.end = t.max)
                }, convertTicksToLabels: function () {
                    var t = this;
                    t.ticksAsNumbers = t.ticks.slice(), t.zeroLineIndex = t.ticks.indexOf(0), mn.prototype.convertTicksToLabels.call(t)
                }, _configure: function () {
                    var t, e = this, n = e.getTicks(), r = e.min, i = e.max;
                    mn.prototype._configure.call(e), e.options.offset && n.length && (t = (i - r) / Math.max(n.length - 1, 1) / 2, r -= t, i += t), e._startValue = r, e._endValue = i, e._valueRange = i - r
                }
            }), Mn = {position: "left", ticks: {callback: nn.formatters.linear}};

            function Fn(t, e, n, r) {
                var i, a, o = t.options, s = o.stacked, l = function (t, e, n) {
                    var r = [n.type, void 0 === e && void 0 === n.stack ? n.index : "", n.stack].join(".");
                    return void 0 === t[r] && (t[r] = {pos: [], neg: []}), t[r]
                }(e, s, n), u = l.pos, c = l.neg, d = r.length;
                for (i = 0; i < d; ++i) a = t._parseValue(r[i]), isNaN(a.min) || isNaN(a.max) || n.data[i].hidden || (u[i] = u[i] || 0, c[i] = c[i] || 0, o.relativePoints ? u[i] = 100 : a.min < 0 || a.max < 0 ? c[i] += a.min : u[i] += a.max)
            }

            function Sn(t, e, n) {
                var r, i, a = n.length;
                for (r = 0; r < a; ++r) i = t._parseValue(n[r]), isNaN(i.min) || isNaN(i.max) || e.data[r].hidden || (t.min = Math.min(t.min, i.min), t.max = Math.max(t.max, i.max))
            }

            var Cn = kn.extend({
                determineDataLimits: function () {
                    var t, e, n, r, i = this, a = i.options, o = i.chart, s = o.data.datasets,
                        l = i._getMatchingVisibleMetas(), u = a.stacked, c = {}, d = l.length;
                    if (i.min = Number.POSITIVE_INFINITY, i.max = Number.NEGATIVE_INFINITY, void 0 === u) for (t = 0; !u && t < d; ++t) e = l[t], u = void 0 !== e.stack;
                    for (t = 0; t < d; ++t) e = l[t], n = s[e.index].data, u ? Fn(i, c, e, n) : Sn(i, e, n);
                    B.each(c, function (t) {
                        r = t.pos.concat(t.neg), i.min = Math.min(i.min, B.min(r)), i.max = Math.max(i.max, B.max(r))
                    }), i.min = B.isFinite(i.min) && !isNaN(i.min) ? i.min : 0, i.max = B.isFinite(i.max) && !isNaN(i.max) ? i.max : 1, i.handleTickRangeOptions()
                }, _computeTickLimit: function () {
                    var t;
                    return this.isHorizontal() ? Math.ceil(this.width / 40) : (t = B.options._parseFont(this.options.ticks), Math.ceil(this.height / t.lineHeight))
                }, handleDirectionalChanges: function () {
                    this.isHorizontal() || this.ticks.reverse()
                }, getLabelForIndex: function (t, e) {
                    return this._getScaleLabel(this.chart.data.datasets[e].data[t])
                }, getPixelForValue: function (t) {
                    return this.getPixelForDecimal((+this.getRightValue(t) - this._startValue) / this._valueRange)
                }, getValueForPixel: function (t) {
                    return this._startValue + this.getDecimalForPixel(t) * this._valueRange
                }, getPixelForTick: function (t) {
                    var e = this.ticksAsNumbers;
                    return t < 0 || t > e.length - 1 ? null : this.getPixelForValue(e[t])
                }
            }), An = Mn;
            Cn._defaults = An;
            var Tn = B.valueOrDefault, Pn = B.math.log10,
                Dn = {position: "left", ticks: {callback: nn.formatters.logarithmic}};

            function On(t, e) {
                return B.isFinite(t) && t >= 0 ? t : e
            }

            var En = mn.extend({
                determineDataLimits: function () {
                    var t, e, n, r, i, a, o = this, s = o.options, l = o.chart, u = l.data.datasets,
                        c = o.isHorizontal();

                    function d(t) {
                        return c ? t.xAxisID === o.id : t.yAxisID === o.id
                    }

                    o.min = Number.POSITIVE_INFINITY, o.max = Number.NEGATIVE_INFINITY, o.minNotZero = Number.POSITIVE_INFINITY;
                    var h = s.stacked;
                    if (void 0 === h) for (t = 0; t < u.length; t++) if (e = l.getDatasetMeta(t), l.isDatasetVisible(t) && d(e) && void 0 !== e.stack) {
                        h = !0;
                        break
                    }
                    if (s.stacked || h) {
                        var f = {};
                        for (t = 0; t < u.length; t++) {
                            var p = [(e = l.getDatasetMeta(t)).type, void 0 === s.stacked && void 0 === e.stack ? t : "", e.stack].join(".");
                            if (l.isDatasetVisible(t) && d(e)) for (void 0 === f[p] && (f[p] = []), r = u[t].data, i = 0, a = r.length; i < a; i++) {
                                var g = f[p];
                                n = o._parseValue(r[i]), isNaN(n.min) || isNaN(n.max) || e.data[i].hidden || n.min < 0 || n.max < 0 || (g[i] = g[i] || 0, g[i] += n.max)
                            }
                        }
                        B.each(f, function (t) {
                            if (t.length > 0) {
                                var e = B.min(t), n = B.max(t);
                                o.min = Math.min(o.min, e), o.max = Math.max(o.max, n)
                            }
                        })
                    } else for (t = 0; t < u.length; t++) if (e = l.getDatasetMeta(t), l.isDatasetVisible(t) && d(e)) for (r = u[t].data, i = 0, a = r.length; i < a; i++) n = o._parseValue(r[i]), isNaN(n.min) || isNaN(n.max) || e.data[i].hidden || n.min < 0 || n.max < 0 || (o.min = Math.min(n.min, o.min), o.max = Math.max(n.max, o.max), 0 !== n.min && (o.minNotZero = Math.min(n.min, o.minNotZero)));
                    o.min = B.isFinite(o.min) ? o.min : null, o.max = B.isFinite(o.max) ? o.max : null, o.minNotZero = B.isFinite(o.minNotZero) ? o.minNotZero : null, this.handleTickRangeOptions()
                }, handleTickRangeOptions: function () {
                    var t = this, e = t.options.ticks;
                    t.min = On(e.min, t.min), t.max = On(e.max, t.max), t.min === t.max && (0 !== t.min && null !== t.min ? (t.min = Math.pow(10, Math.floor(Pn(t.min)) - 1), t.max = Math.pow(10, Math.floor(Pn(t.max)) + 1)) : (t.min = 1, t.max = 10)), null === t.min && (t.min = Math.pow(10, Math.floor(Pn(t.max)) - 1)), null === t.max && (t.max = 0 !== t.min ? Math.pow(10, Math.floor(Pn(t.min)) + 1) : 10), null === t.minNotZero && (t.min > 0 ? t.minNotZero = t.min : t.max < 1 ? t.minNotZero = Math.pow(10, Math.floor(Pn(t.max))) : t.minNotZero = 1)
                }, buildTicks: function () {
                    var t = this, e = t.options.ticks, n = !t.isHorizontal(), r = {min: On(e.min), max: On(e.max)},
                        i = t.ticks = function (t, e) {
                            var n, r, i = [], a = Tn(t.min, Math.pow(10, Math.floor(Pn(e.min)))),
                                o = Math.floor(Pn(e.max)), s = Math.ceil(e.max / Math.pow(10, o));
                            0 === a ? (n = Math.floor(Pn(e.minNotZero)), r = Math.floor(e.minNotZero / Math.pow(10, n)), i.push(a), a = r * Math.pow(10, n)) : (n = Math.floor(Pn(a)), r = Math.floor(a / Math.pow(10, n)));
                            var l = n < 0 ? Math.pow(10, Math.abs(n)) : 1;
                            do {
                                i.push(a), 10 === ++r && (r = 1, l = ++n >= 0 ? 1 : l), a = Math.round(r * Math.pow(10, n) * l) / l
                            } while (n < o || n === o && r < s);
                            var u = Tn(t.max, a);
                            return i.push(u), i
                        }(r, t);
                    t.max = B.max(i), t.min = B.min(i), e.reverse ? (n = !n, t.start = t.max, t.end = t.min) : (t.start = t.min, t.end = t.max), n && i.reverse()
                }, convertTicksToLabels: function () {
                    this.tickValues = this.ticks.slice(), mn.prototype.convertTicksToLabels.call(this)
                }, getLabelForIndex: function (t, e) {
                    return this._getScaleLabel(this.chart.data.datasets[e].data[t])
                }, getPixelForTick: function (t) {
                    var e = this.tickValues;
                    return t < 0 || t > e.length - 1 ? null : this.getPixelForValue(e[t])
                }, _getFirstTickValue: function (t) {
                    var e = Math.floor(Pn(t)), n = Math.floor(t / Math.pow(10, e));
                    return n * Math.pow(10, e)
                }, _configure: function () {
                    var t = this, e = t.min, n = 0;
                    mn.prototype._configure.call(t), 0 === e && (e = t._getFirstTickValue(t.minNotZero), n = Tn(t.options.ticks.fontSize, L.global.defaultFontSize) / t._length), t._startValue = Pn(e), t._valueOffset = n, t._valueRange = (Pn(t.max) - Pn(e)) / (1 - n)
                }, getPixelForValue: function (t) {
                    var e = this, n = 0;
                    return (t = +e.getRightValue(t)) > e.min && t > 0 && (n = (Pn(t) - e._startValue) / e._valueRange + e._valueOffset), e.getPixelForDecimal(n)
                }, getValueForPixel: function (t) {
                    var e = this, n = e.getDecimalForPixel(t);
                    return 0 === n && 0 === e.min ? 0 : Math.pow(10, e._startValue + (n - e._valueOffset) * e._valueRange)
                }
            }), In = Dn;
            En._defaults = In;
            var Rn = B.valueOrDefault, Ln = B.valueAtIndexOrDefault, Nn = B.options.resolve, jn = {
                display: !0,
                animate: !0,
                position: "chartArea",
                angleLines: {display: !0, color: "rgba(0,0,0,0.1)", lineWidth: 1, borderDash: [], borderDashOffset: 0},
                gridLines: {circular: !1},
                ticks: {
                    showLabelBackdrop: !0,
                    backdropColor: "rgba(255,255,255,0.75)",
                    backdropPaddingY: 2,
                    backdropPaddingX: 2,
                    callback: nn.formatters.linear
                },
                pointLabels: {
                    display: !0, fontSize: 10, callback: function (t) {
                        return t
                    }
                }
            };

            function zn(t) {
                var e = t.ticks;
                return e.display && t.display ? Rn(e.fontSize, L.global.defaultFontSize) + 2 * e.backdropPaddingY : 0
            }

            function Vn(t, e, n, r, i) {
                return t === r || t === i ? {start: e - n / 2, end: e + n / 2} : t < r || t > i ? {
                    start: e - n,
                    end: e
                } : {start: e, end: e + n}
            }

            function Bn(t) {
                return 0 === t || 180 === t ? "center" : t < 180 ? "left" : "right"
            }

            function Wn(t, e, n, r) {
                var i, a, o = n.y + r / 2;
                if (B.isArray(e)) for (i = 0, a = e.length; i < a; ++i) t.fillText(e[i], n.x, o), o += r; else t.fillText(e, n.x, o)
            }

            function Hn(t, e, n) {
                90 === t || 270 === t ? n.y -= e.h / 2 : (t > 270 || t < 90) && (n.y -= e.h)
            }

            function Un(t) {
                return B.isNumber(t) ? t : 0
            }

            var Yn = kn.extend({
                setDimensions: function () {
                    var t = this;
                    t.width = t.maxWidth, t.height = t.maxHeight, t.paddingTop = zn(t.options) / 2, t.xCenter = Math.floor(t.width / 2), t.yCenter = Math.floor((t.height - t.paddingTop) / 2), t.drawingArea = Math.min(t.height - t.paddingTop, t.width) / 2
                }, determineDataLimits: function () {
                    var t = this, e = t.chart, n = Number.POSITIVE_INFINITY, r = Number.NEGATIVE_INFINITY;
                    B.each(e.data.datasets, function (i, a) {
                        if (e.isDatasetVisible(a)) {
                            var o = e.getDatasetMeta(a);
                            B.each(i.data, function (e, i) {
                                var a = +t.getRightValue(e);
                                isNaN(a) || o.data[i].hidden || (n = Math.min(a, n), r = Math.max(a, r))
                            })
                        }
                    }), t.min = n === Number.POSITIVE_INFINITY ? 0 : n, t.max = r === Number.NEGATIVE_INFINITY ? 0 : r, t.handleTickRangeOptions()
                }, _computeTickLimit: function () {
                    return Math.ceil(this.drawingArea / zn(this.options))
                }, convertTicksToLabels: function () {
                    var t = this;
                    kn.prototype.convertTicksToLabels.call(t), t.pointLabels = t.chart.data.labels.map(function () {
                        var e = B.callback(t.options.pointLabels.callback, arguments, t);
                        return e || 0 === e ? e : ""
                    })
                }, getLabelForIndex: function (t, e) {
                    return +this.getRightValue(this.chart.data.datasets[e].data[t])
                }, fit: function () {
                    var t = this.options;
                    t.display && t.pointLabels.display ? function (t) {
                        var e, n, r, i = B.options._parseFont(t.options.pointLabels),
                            a = {l: 0, r: t.width, t: 0, b: t.height - t.paddingTop}, o = {};
                        t.ctx.font = i.string, t._pointLabelSizes = [];
                        var s, l, u, c = t.chart.data.labels.length;
                        for (e = 0; e < c; e++) {
                            r = t.getPointPosition(e, t.drawingArea + 5), s = t.ctx, l = i.lineHeight, u = t.pointLabels[e], n = B.isArray(u) ? {
                                w: B.longestText(s, s.font, u),
                                h: u.length * l
                            } : {w: s.measureText(u).width, h: l}, t._pointLabelSizes[e] = n;
                            var d = t.getIndexAngle(e), h = B.toDegrees(d) % 360, f = Vn(h, r.x, n.w, 0, 180),
                                p = Vn(h, r.y, n.h, 90, 270);
                            f.start < a.l && (a.l = f.start, o.l = d), f.end > a.r && (a.r = f.end, o.r = d), p.start < a.t && (a.t = p.start, o.t = d), p.end > a.b && (a.b = p.end, o.b = d)
                        }
                        t.setReductions(t.drawingArea, a, o)
                    }(this) : this.setCenterPoint(0, 0, 0, 0)
                }, setReductions: function (t, e, n) {
                    var r = this, i = e.l / Math.sin(n.l), a = Math.max(e.r - r.width, 0) / Math.sin(n.r),
                        o = -e.t / Math.cos(n.t), s = -Math.max(e.b - (r.height - r.paddingTop), 0) / Math.cos(n.b);
                    i = Un(i), a = Un(a), o = Un(o), s = Un(s), r.drawingArea = Math.min(Math.floor(t - (i + a) / 2), Math.floor(t - (o + s) / 2)), r.setCenterPoint(i, a, o, s)
                }, setCenterPoint: function (t, e, n, r) {
                    var i = this, a = i.width - e - i.drawingArea, o = t + i.drawingArea, s = n + i.drawingArea,
                        l = i.height - i.paddingTop - r - i.drawingArea;
                    i.xCenter = Math.floor((o + a) / 2 + i.left), i.yCenter = Math.floor((s + l) / 2 + i.top + i.paddingTop)
                }, getIndexAngle: function (t) {
                    var e = this.chart, n = 360 / e.data.labels.length, r = e.options || {}, i = r.startAngle || 0,
                        a = (t * n + i) % 360;
                    return (a < 0 ? a + 360 : a) * Math.PI * 2 / 360
                }, getDistanceFromCenterForValue: function (t) {
                    var e = this;
                    if (B.isNullOrUndef(t)) return NaN;
                    var n = e.drawingArea / (e.max - e.min);
                    return e.options.ticks.reverse ? (e.max - t) * n : (t - e.min) * n
                }, getPointPosition: function (t, e) {
                    var n = this.getIndexAngle(t) - Math.PI / 2;
                    return {x: Math.cos(n) * e + this.xCenter, y: Math.sin(n) * e + this.yCenter}
                }, getPointPositionForValue: function (t, e) {
                    return this.getPointPosition(t, this.getDistanceFromCenterForValue(e))
                }, getBasePosition: function (t) {
                    var e = this.min, n = this.max;
                    return this.getPointPositionForValue(t || 0, this.beginAtZero ? 0 : e < 0 && n < 0 ? n : e > 0 && n > 0 ? e : 0)
                }, _drawGrid: function () {
                    var t, e, n, r = this, i = r.ctx, a = r.options, o = a.gridLines, s = a.angleLines,
                        l = Rn(s.lineWidth, o.lineWidth), u = Rn(s.color, o.color);
                    if (a.pointLabels.display && function (t) {
                        var e = t.ctx, n = t.options, r = n.pointLabels, i = zn(n),
                            a = t.getDistanceFromCenterForValue(n.ticks.reverse ? t.min : t.max),
                            o = B.options._parseFont(r);
                        e.save(), e.font = o.string, e.textBaseline = "middle";
                        for (var s = t.chart.data.labels.length - 1; s >= 0; s--) {
                            var l = 0 === s ? i / 2 : 0, u = t.getPointPosition(s, a + l + 5),
                                c = Ln(r.fontColor, s, L.global.defaultFontColor);
                            e.fillStyle = c;
                            var d = t.getIndexAngle(s), h = B.toDegrees(d);
                            e.textAlign = Bn(h), Hn(h, t._pointLabelSizes[s], u), Wn(e, t.pointLabels[s], u, o.lineHeight)
                        }
                        e.restore()
                    }(r), o.display && B.each(r.ticks, function (t, n) {
                        0 !== n && (e = r.getDistanceFromCenterForValue(r.ticksAsNumbers[n]), function (t, e, n, r) {
                            var i, a = t.ctx, o = e.circular, s = t.chart.data.labels.length, l = Ln(e.color, r - 1),
                                u = Ln(e.lineWidth, r - 1);
                            if ((o || s) && l && u) {
                                if (a.save(), a.strokeStyle = l, a.lineWidth = u, a.setLineDash && (a.setLineDash(e.borderDash || []), a.lineDashOffset = e.borderDashOffset || 0), a.beginPath(), o) a.arc(t.xCenter, t.yCenter, n, 0, 2 * Math.PI); else {
                                    i = t.getPointPosition(0, n), a.moveTo(i.x, i.y);
                                    for (var c = 1; c < s; c++) i = t.getPointPosition(c, n), a.lineTo(i.x, i.y)
                                }
                                a.closePath(), a.stroke(), a.restore()
                            }
                        }(r, o, e, n))
                    }), s.display && l && u) {
                        for (i.save(), i.lineWidth = l, i.strokeStyle = u, i.setLineDash && (i.setLineDash(Nn([s.borderDash, o.borderDash, []])), i.lineDashOffset = Nn([s.borderDashOffset, o.borderDashOffset, 0])), t = r.chart.data.labels.length - 1; t >= 0; t--) e = r.getDistanceFromCenterForValue(a.ticks.reverse ? r.min : r.max), n = r.getPointPosition(t, e), i.beginPath(), i.moveTo(r.xCenter, r.yCenter), i.lineTo(n.x, n.y), i.stroke();
                        i.restore()
                    }
                }, _drawLabels: function () {
                    var t = this, e = t.ctx, n = t.options, r = n.ticks;
                    if (r.display) {
                        var i, a, o = t.getIndexAngle(0), s = B.options._parseFont(r),
                            l = Rn(r.fontColor, L.global.defaultFontColor);
                        e.save(), e.font = s.string, e.translate(t.xCenter, t.yCenter), e.rotate(o), e.textAlign = "center", e.textBaseline = "middle", B.each(t.ticks, function (n, o) {
                            (0 !== o || r.reverse) && (i = t.getDistanceFromCenterForValue(t.ticksAsNumbers[o]), r.showLabelBackdrop && (a = e.measureText(n).width, e.fillStyle = r.backdropColor, e.fillRect(-a / 2 - r.backdropPaddingX, -i - s.size / 2 - r.backdropPaddingY, a + 2 * r.backdropPaddingX, s.size + 2 * r.backdropPaddingY)), e.fillStyle = l, e.fillText(n, 0, -i))
                        }), e.restore()
                    }
                }, _drawTitle: B.noop
            }), qn = jn;
            Yn._defaults = qn;
            var $n = B._deprecated, Xn = B.options.resolve, Gn = B.valueOrDefault,
                Kn = Number.MIN_SAFE_INTEGER || -9007199254740991, Zn = Number.MAX_SAFE_INTEGER || 9007199254740991,
                Jn = {
                    millisecond: {common: !0, size: 1, steps: 1e3},
                    second: {common: !0, size: 1e3, steps: 60},
                    minute: {common: !0, size: 6e4, steps: 60},
                    hour: {common: !0, size: 36e5, steps: 24},
                    day: {common: !0, size: 864e5, steps: 30},
                    week: {common: !1, size: 6048e5, steps: 4},
                    month: {common: !0, size: 2628e6, steps: 12},
                    quarter: {common: !1, size: 7884e6, steps: 4},
                    year: {common: !0, size: 3154e7}
                }, Qn = Object.keys(Jn);

            function tr(t, e) {
                return t - e
            }

            function er(t) {
                return B.valueOrDefault(t.time.min, t.ticks.min)
            }

            function nr(t) {
                return B.valueOrDefault(t.time.max, t.ticks.max)
            }

            function rr(t, e, n, r) {
                var i = function (t, e, n) {
                        for (var r, i, a, o = 0, s = t.length - 1; o >= 0 && o <= s;) {
                            if (i = t[(r = o + s >> 1) - 1] || null, a = t[r], !i) return {lo: null, hi: a};
                            if (a[e] < n) o = r + 1; else {
                                if (!(i[e] > n)) return {lo: i, hi: a};
                                s = r - 1
                            }
                        }
                        return {lo: a, hi: null}
                    }(t, e, n), a = i.lo ? i.hi ? i.lo : t[t.length - 2] : t[0],
                    o = i.lo ? i.hi ? i.hi : t[t.length - 1] : t[1], s = o[e] - a[e], l = s ? (n - a[e]) / s : 0,
                    u = (o[r] - a[r]) * l;
                return a[r] + u
            }

            function ir(t, e) {
                var n = t._adapter, r = t.options.time, i = r.parser, a = i || r.format, o = e;
                return "function" === typeof i && (o = i(o)), B.isFinite(o) || (o = "string" === typeof a ? n.parse(o, a) : n.parse(o)), null !== o ? +o : (i || "function" !== typeof a || (o = a(e), B.isFinite(o) || (o = n.parse(o))), o)
            }

            function ar(t, e) {
                if (B.isNullOrUndef(e)) return null;
                var n = t.options.time, r = ir(t, t.getRightValue(e));
                return null === r ? r : (n.round && (r = +t._adapter.startOf(r, n.round)), r)
            }

            function or(t, e, n, r) {
                var i, a, o, s = Qn.length;
                for (i = Qn.indexOf(t); i < s - 1; ++i) if (a = Jn[Qn[i]], o = a.steps ? a.steps : Zn, a.common && Math.ceil((n - e) / (o * a.size)) <= r) return Qn[i];
                return Qn[s - 1]
            }

            function sr(t, e, n) {
                var r, i, a = [], o = {}, s = e.length;
                for (r = 0; r < s; ++r) i = e[r], o[i] = r, a.push({value: i, major: !1});
                return 0 !== s && n ? function (t, e, n, r) {
                    var i, a, o = t._adapter, s = +o.startOf(e[0].value, r), l = e[e.length - 1].value;
                    for (i = s; i <= l; i = +o.add(i, 1, r)) (a = n[i]) >= 0 && (e[a].major = !0);
                    return e
                }(t, a, o, n) : a
            }

            var lr = mn.extend({
                initialize: function () {
                    this.mergeTicksOptions(), mn.prototype.initialize.call(this)
                }, update: function () {
                    var t = this.options, e = t.time || (t.time = {}),
                        n = this._adapter = new en._date(t.adapters.date);
                    return $n("time scale", e.format, "time.format", "time.parser"), $n("time scale", e.min, "time.min", "ticks.min"), $n("time scale", e.max, "time.max", "ticks.max"), B.mergeIf(e.displayFormats, n.formats()), mn.prototype.update.apply(this, arguments)
                }, getRightValue: function (t) {
                    return t && void 0 !== t.t && (t = t.t), mn.prototype.getRightValue.call(this, t)
                }, determineDataLimits: function () {
                    var t, e, n, r, i, a, o, s = this, l = s.chart, u = s._adapter, c = s.options,
                        d = c.time.unit || "day", h = Zn, f = Kn, p = [], g = [], v = [], m = s._getLabels();
                    for (t = 0, n = m.length; t < n; ++t) v.push(ar(s, m[t]));
                    for (t = 0, n = (l.data.datasets || []).length; t < n; ++t) if (l.isDatasetVisible(t)) if (i = l.data.datasets[t].data, B.isObject(i[0])) for (g[t] = [], e = 0, r = i.length; e < r; ++e) a = ar(s, i[e]), p.push(a), g[t][e] = a; else g[t] = v.slice(0), o || (p = p.concat(v), o = !0); else g[t] = [];
                    v.length && (h = Math.min(h, v[0]), f = Math.max(f, v[v.length - 1])), p.length && (p = n > 1 ? function (t) {
                        var e, n, r, i = {}, a = [];
                        for (e = 0, n = t.length; e < n; ++e) r = t[e], i[r] || (i[r] = !0, a.push(r));
                        return a
                    }(p).sort(tr) : p.sort(tr), h = Math.min(h, p[0]), f = Math.max(f, p[p.length - 1])), h = ar(s, er(c)) || h, f = ar(s, nr(c)) || f, h = h === Zn ? +u.startOf(Date.now(), d) : h, f = f === Kn ? +u.endOf(Date.now(), d) + 1 : f, s.min = Math.min(h, f), s.max = Math.max(h + 1, f), s._table = [], s._timestamps = {
                        data: p,
                        datasets: g,
                        labels: v
                    }
                }, buildTicks: function () {
                    var t, e, n, r = this, i = r.min, a = r.max, o = r.options, s = o.ticks, l = o.time,
                        u = r._timestamps, c = [], d = r.getLabelCapacity(i), h = s.source, f = o.distribution;
                    for (u = "data" === h || "auto" === h && "series" === f ? u.data : "labels" === h ? u.labels : function (t, e, n, r) {
                        var i, a = t._adapter, o = t.options, s = o.time, l = s.unit || or(s.minUnit, e, n, r),
                            u = Xn([s.stepSize, s.unitStepSize, 1]), c = "week" === l && s.isoWeekday, d = e, h = [];
                        if (c && (d = +a.startOf(d, "isoWeek", c)), d = +a.startOf(d, c ? "day" : l), a.diff(n, e, l) > 1e5 * u) throw e + " and " + n + " are too far apart with stepSize of " + u + " " + l;
                        for (i = d; i < n; i = +a.add(i, u, l)) h.push(i);
                        return i !== n && "ticks" !== o.bounds || h.push(i), h
                    }(r, i, a, d), "ticks" === o.bounds && u.length && (i = u[0], a = u[u.length - 1]), i = ar(r, er(o)) || i, a = ar(r, nr(o)) || a, t = 0, e = u.length; t < e; ++t) (n = u[t]) >= i && n <= a && c.push(n);
                    return r.min = i, r.max = a, r._unit = l.unit || (s.autoSkip ? or(l.minUnit, r.min, r.max, d) : function (t, e, n, r, i) {
                        var a, o;
                        for (a = Qn.length - 1; a >= Qn.indexOf(n); a--) if (o = Qn[a], Jn[o].common && t._adapter.diff(i, r, o) >= e - 1) return o;
                        return Qn[n ? Qn.indexOf(n) : 0]
                    }(r, c.length, l.minUnit, r.min, r.max)), r._majorUnit = s.major.enabled && "year" !== r._unit ? function (t) {
                        for (var e = Qn.indexOf(t) + 1, n = Qn.length; e < n; ++e) if (Jn[Qn[e]].common) return Qn[e]
                    }(r._unit) : void 0, r._table = function (t, e, n, r) {
                        if ("linear" === r || !t.length) return [{time: e, pos: 0}, {time: n, pos: 1}];
                        var i, a, o, s, l, u = [], c = [e];
                        for (i = 0, a = t.length; i < a; ++i) (s = t[i]) > e && s < n && c.push(s);
                        for (c.push(n), i = 0, a = c.length; i < a; ++i) l = c[i + 1], o = c[i - 1], s = c[i], void 0 !== o && void 0 !== l && Math.round((l + o) / 2) === s || u.push({
                            time: s,
                            pos: i / (a - 1)
                        });
                        return u
                    }(r._timestamps.data, i, a, f), r._offsets = function (t, e, n, r, i) {
                        var a, o, s = 0, l = 0;
                        return i.offset && e.length && (a = rr(t, "time", e[0], "pos"), s = 1 === e.length ? 1 - a : (rr(t, "time", e[1], "pos") - a) / 2, o = rr(t, "time", e[e.length - 1], "pos"), l = 1 === e.length ? o : (o - rr(t, "time", e[e.length - 2], "pos")) / 2), {
                            start: s,
                            end: l,
                            factor: 1 / (s + 1 + l)
                        }
                    }(r._table, c, 0, 0, o), s.reverse && c.reverse(), sr(r, c, r._majorUnit)
                }, getLabelForIndex: function (t, e) {
                    var n = this, r = n._adapter, i = n.chart.data, a = n.options.time,
                        o = i.labels && t < i.labels.length ? i.labels[t] : "", s = i.datasets[e].data[t];
                    return B.isObject(s) && (o = n.getRightValue(s)), a.tooltipFormat ? r.format(ir(n, o), a.tooltipFormat) : "string" === typeof o ? o : r.format(ir(n, o), a.displayFormats.datetime)
                }, tickFormatFunction: function (t, e, n, r) {
                    var i = this._adapter, a = this.options, o = a.time.displayFormats, s = o[this._unit],
                        l = this._majorUnit, u = o[l], c = n[e], d = a.ticks, h = l && u && c && c.major,
                        f = i.format(t, r || (h ? u : s)), p = h ? d.major : d.minor,
                        g = Xn([p.callback, p.userCallback, d.callback, d.userCallback]);
                    return g ? g(f, e, n) : f
                }, convertTicksToLabels: function (t) {
                    var e, n, r = [];
                    for (e = 0, n = t.length; e < n; ++e) r.push(this.tickFormatFunction(t[e].value, e, t));
                    return r
                }, getPixelForOffset: function (t) {
                    var e = this._offsets, n = rr(this._table, "time", t, "pos");
                    return this.getPixelForDecimal((e.start + n) * e.factor)
                }, getPixelForValue: function (t, e, n) {
                    var r = null;
                    if (void 0 !== e && void 0 !== n && (r = this._timestamps.datasets[n][e]), null === r && (r = ar(this, t)), null !== r) return this.getPixelForOffset(r)
                }, getPixelForTick: function (t) {
                    var e = this.getTicks();
                    return t >= 0 && t < e.length ? this.getPixelForOffset(e[t].value) : null
                }, getValueForPixel: function (t) {
                    var e = this._offsets, n = this.getDecimalForPixel(t) / e.factor - e.end,
                        r = rr(this._table, "pos", n, "time");
                    return this._adapter._create(r)
                }, _getLabelSize: function (t) {
                    var e = this.options.ticks, n = this.ctx.measureText(t).width,
                        r = B.toRadians(this.isHorizontal() ? e.maxRotation : e.minRotation), i = Math.cos(r),
                        a = Math.sin(r), o = Gn(e.fontSize, L.global.defaultFontSize);
                    return {w: n * i + o * a, h: n * a + o * i}
                }, getLabelWidth: function (t) {
                    return this._getLabelSize(t).w
                }, getLabelCapacity: function (t) {
                    var e = this, n = e.options.time, r = n.displayFormats, i = r[n.unit] || r.millisecond,
                        a = e.tickFormatFunction(t, 0, sr(e, [t], e._majorUnit), i), o = e._getLabelSize(a),
                        s = Math.floor(e.isHorizontal() ? e.width / o.w : e.height / o.h);
                    return e.options.offset && s--, s > 0 ? s : 1
                }
            }), ur = {
                position: "bottom",
                distribution: "linear",
                bounds: "data",
                adapters: {},
                time: {
                    parser: !1,
                    unit: !1,
                    round: !1,
                    displayFormat: !1,
                    isoWeekday: !1,
                    minUnit: "millisecond",
                    displayFormats: {}
                },
                ticks: {autoSkip: !1, source: "auto", major: {enabled: !1}}
            };
            lr._defaults = ur;
            var cr = {category: yn, linear: Cn, logarithmic: En, radialLinear: Yn, time: lr}, dr = {
                datetime: "MMM D, YYYY, h:mm:ss a",
                millisecond: "h:mm:ss.SSS a",
                second: "h:mm:ss a",
                minute: "h:mm a",
                hour: "hA",
                day: "MMM D",
                week: "ll",
                month: "MMM YYYY",
                quarter: "[Q]Q - YYYY",
                year: "YYYY"
            };
            en._date.override("function" === typeof t ? {
                _id: "moment", formats: function () {
                    return dr
                }, parse: function (e, n) {
                    return "string" === typeof e && "string" === typeof n ? e = t(e, n) : e instanceof t || (e = t(e)), e.isValid() ? e.valueOf() : null
                }, format: function (e, n) {
                    return t(e).format(n)
                }, add: function (e, n, r) {
                    return t(e).add(n, r).valueOf()
                }, diff: function (e, n, r) {
                    return t(e).diff(t(n), r)
                }, startOf: function (e, n, r) {
                    return e = t(e), "isoWeek" === n ? e.isoWeekday(r).valueOf() : e.startOf(n).valueOf()
                }, endOf: function (e, n) {
                    return t(e).endOf(n).valueOf()
                }, _create: function (e) {
                    return t(e)
                }
            } : {}), L._set("global", {plugins: {filler: {propagate: !0}}});
            var hr = {
                dataset: function (t) {
                    var e = t.fill, n = t.chart, r = n.getDatasetMeta(e), i = r && n.isDatasetVisible(e),
                        a = i && r.dataset._children || [], o = a.length || 0;
                    return o ? function (t, e) {
                        return e < o && a[e]._view || null
                    } : null
                }, boundary: function (t) {
                    var e = t.boundary, n = e ? e.x : null, r = e ? e.y : null;
                    return B.isArray(e) ? function (t, n) {
                        return e[n]
                    } : function (t) {
                        return {x: null === n ? t.x : n, y: null === r ? t.y : r}
                    }
                }
            };

            function fr(t, e, n) {
                var r, i = t._model || {}, a = i.fill;
                if (void 0 === a && (a = !!i.backgroundColor), !1 === a || null === a) return !1;
                if (!0 === a) return "origin";
                if (r = parseFloat(a, 10), isFinite(r) && Math.floor(r) === r) return "-" !== a[0] && "+" !== a[0] || (r = e + r), !(r === e || r < 0 || r >= n) && r;
                switch (a) {
                    case"bottom":
                        return "start";
                    case"top":
                        return "end";
                    case"zero":
                        return "origin";
                    case"origin":
                    case"start":
                    case"end":
                        return a;
                    default:
                        return !1
                }
            }

            function pr(t) {
                var e = t.el._scale || {};
                return e.getPointPositionForValue ? function (t) {
                    var e, n, r, i, a, o = t.el._scale, s = o.options, l = o.chart.data.labels.length, u = t.fill,
                        c = [];
                    if (!l) return null;
                    for (e = s.ticks.reverse ? o.max : o.min, n = s.ticks.reverse ? o.min : o.max, r = o.getPointPositionForValue(0, e), i = 0; i < l; ++i) a = "start" === u || "end" === u ? o.getPointPositionForValue(i, "start" === u ? e : n) : o.getBasePosition(i), s.gridLines.circular && (a.cx = r.x, a.cy = r.y, a.angle = o.getIndexAngle(i) - Math.PI / 2), c.push(a);
                    return c
                }(t) : function (t) {
                    var e, n = t.el._model || {}, r = t.el._scale || {}, i = t.fill, a = null;
                    if (isFinite(i)) return null;
                    if ("start" === i ? a = void 0 === n.scaleBottom ? r.bottom : n.scaleBottom : "end" === i ? a = void 0 === n.scaleTop ? r.top : n.scaleTop : void 0 !== n.scaleZero ? a = n.scaleZero : r.getBasePixel && (a = r.getBasePixel()), void 0 !== a && null !== a) {
                        if (void 0 !== a.x && void 0 !== a.y) return a;
                        if (B.isFinite(a)) return {x: (e = r.isHorizontal()) ? a : null, y: e ? null : a}
                    }
                    return null
                }(t)
            }

            function gr(t, e, n) {
                var r, i = t[e], a = i.fill, o = [e];
                if (!n) return a;
                for (; !1 !== a && -1 === o.indexOf(a);) {
                    if (!isFinite(a)) return a;
                    if (!(r = t[a])) return !1;
                    if (r.visible) return a;
                    o.push(a), a = r.fill
                }
                return !1
            }

            function vr(t) {
                var e = t.fill, n = "dataset";
                return !1 === e ? null : (isFinite(e) || (n = "boundary"), hr[n](t))
            }

            function mr(t) {
                return t && !t.skip
            }

            function br(t, e, n, r, i) {
                var a, o, s, l;
                if (r && i) {
                    for (t.moveTo(e[0].x, e[0].y), a = 1; a < r; ++a) B.canvas.lineTo(t, e[a - 1], e[a]);
                    if (void 0 === n[0].angle) for (t.lineTo(n[i - 1].x, n[i - 1].y), a = i - 1; a > 0; --a) B.canvas.lineTo(t, n[a], n[a - 1], !0); else for (o = n[0].cx, s = n[0].cy, l = Math.sqrt(Math.pow(n[0].x - o, 2) + Math.pow(n[0].y - s, 2)), a = i - 1; a > 0; --a) t.arc(o, s, l, n[a].angle, n[a - 1].angle, !0)
                }
            }

            function yr(t, e, n, r, i, a) {
                var o, s, l, u, c, d, h, f, p = e.length, g = r.spanGaps, v = [], m = [], b = 0, y = 0;
                for (t.beginPath(), o = 0, s = p; o < s; ++o) u = e[l = o % p]._view, c = n(u, l, r), d = mr(u), h = mr(c), a && void 0 === f && d && (s = p + (f = o + 1)), d && h ? (b = v.push(u), y = m.push(c)) : b && y && (g ? (d && v.push(u), h && m.push(c)) : (br(t, v, m, b, y), b = y = 0, v = [], m = []));
                br(t, v, m, b, y), t.closePath(), t.fillStyle = i, t.fill()
            }

            var xr = {
                id: "filler", afterDatasetsUpdate: function (t, e) {
                    var n, r, i, a, o = (t.data.datasets || []).length, s = e.propagate, l = [];
                    for (r = 0; r < o; ++r) n = t.getDatasetMeta(r), i = n.dataset, a = null, i && i._model && i instanceof _t.Line && (a = {
                        visible: t.isDatasetVisible(r),
                        fill: fr(i, r, o),
                        chart: t,
                        el: i
                    }), n.$filler = a, l.push(a);
                    for (r = 0; r < o; ++r) (a = l[r]) && (a.fill = gr(l, r, s), a.boundary = pr(a), a.mapper = vr(a))
                }, beforeDatasetsDraw: function (t) {
                    var e, n, r, i, a, o, s, l = t._getSortedVisibleDatasetMetas(), u = t.ctx;
                    for (n = l.length - 1; n >= 0; --n) (e = l[n].$filler) && e.visible && (r = e.el, i = r._view, a = r._children || [], o = e.mapper, s = i.backgroundColor || L.global.defaultColor, o && s && a.length && (B.canvas.clipArea(u, t.chartArea), yr(u, a, o, i, s, r._loop), B.canvas.unclipArea(u)))
                }
            }, _r = B.rtl.getRtlAdapter, wr = B.noop, kr = B.valueOrDefault;

            function Mr(t, e) {
                return t.usePointStyle && t.boxWidth > e ? e : t.boxWidth
            }

            L._set("global", {
                legend: {
                    display: !0,
                    position: "top",
                    align: "center",
                    fullWidth: !0,
                    reverse: !1,
                    weight: 1e3,
                    onClick: function (t, e) {
                        var n = e.datasetIndex, r = this.chart, i = r.getDatasetMeta(n);
                        i.hidden = null === i.hidden ? !r.data.datasets[n].hidden : null, r.update()
                    },
                    onHover: null,
                    onLeave: null,
                    labels: {
                        boxWidth: 40, padding: 10, generateLabels: function (t) {
                            var e = t.data.datasets, n = t.options.legend || {}, r = n.labels && n.labels.usePointStyle;
                            return t._getSortedDatasetMetas().map(function (n) {
                                var i = n.controller.getStyle(r ? 0 : void 0);
                                return {
                                    text: e[n.index].label,
                                    fillStyle: i.backgroundColor,
                                    hidden: !t.isDatasetVisible(n.index),
                                    lineCap: i.borderCapStyle,
                                    lineDash: i.borderDash,
                                    lineDashOffset: i.borderDashOffset,
                                    lineJoin: i.borderJoinStyle,
                                    lineWidth: i.borderWidth,
                                    strokeStyle: i.borderColor,
                                    pointStyle: i.pointStyle,
                                    rotation: i.rotation,
                                    datasetIndex: n.index
                                }
                            }, this)
                        }
                    }
                }, legendCallback: function (t) {
                    var e, n, r, i = document.createElement("ul"), a = t.data.datasets;
                    for (i.setAttribute("class", t.id + "-legend"), e = 0, n = a.length; e < n; e++) (r = i.appendChild(document.createElement("li"))).appendChild(document.createElement("span")).style.backgroundColor = a[e].backgroundColor, a[e].label && r.appendChild(document.createTextNode(a[e].label));
                    return i.outerHTML
                }
            });
            var Fr = X.extend({
                initialize: function (t) {
                    B.extend(this, t), this.legendHitBoxes = [], this._hoveredItem = null, this.doughnutMode = !1
                }, beforeUpdate: wr, update: function (t, e, n) {
                    var r = this;
                    return r.beforeUpdate(), r.maxWidth = t, r.maxHeight = e, r.margins = n, r.beforeSetDimensions(), r.setDimensions(), r.afterSetDimensions(), r.beforeBuildLabels(), r.buildLabels(), r.afterBuildLabels(), r.beforeFit(), r.fit(), r.afterFit(), r.afterUpdate(), r.minSize
                }, afterUpdate: wr, beforeSetDimensions: wr, setDimensions: function () {
                    var t = this;
                    t.isHorizontal() ? (t.width = t.maxWidth, t.left = 0, t.right = t.width) : (t.height = t.maxHeight, t.top = 0, t.bottom = t.height), t.paddingLeft = 0, t.paddingTop = 0, t.paddingRight = 0, t.paddingBottom = 0, t.minSize = {
                        width: 0,
                        height: 0
                    }
                }, afterSetDimensions: wr, beforeBuildLabels: wr, buildLabels: function () {
                    var t = this, e = t.options.labels || {}, n = B.callback(e.generateLabels, [t.chart], t) || [];
                    e.filter && (n = n.filter(function (n) {
                        return e.filter(n, t.chart.data)
                    })), t.options.reverse && n.reverse(), t.legendItems = n
                }, afterBuildLabels: wr, beforeFit: wr, fit: function () {
                    var t = this, e = t.options, n = e.labels, r = e.display, i = t.ctx, a = B.options._parseFont(n),
                        o = a.size, s = t.legendHitBoxes = [], l = t.minSize, u = t.isHorizontal();
                    if (u ? (l.width = t.maxWidth, l.height = r ? 10 : 0) : (l.width = r ? 10 : 0, l.height = t.maxHeight), r) {
                        if (i.font = a.string, u) {
                            var c = t.lineWidths = [0], d = 0;
                            i.textAlign = "left", i.textBaseline = "middle", B.each(t.legendItems, function (t, e) {
                                var r = Mr(n, o), a = r + o / 2 + i.measureText(t.text).width;
                                (0 === e || c[c.length - 1] + a + 2 * n.padding > l.width) && (d += o + n.padding, c[c.length - (e > 0 ? 0 : 1)] = 0), s[e] = {
                                    left: 0,
                                    top: 0,
                                    width: a,
                                    height: o
                                }, c[c.length - 1] += a + n.padding
                            }), l.height += d
                        } else {
                            var h = n.padding, f = t.columnWidths = [], p = t.columnHeights = [], g = n.padding, v = 0,
                                m = 0;
                            B.each(t.legendItems, function (t, e) {
                                var r = Mr(n, o), a = r + o / 2 + i.measureText(t.text).width;
                                e > 0 && m + o + 2 * h > l.height && (g += v + n.padding, f.push(v), p.push(m), v = 0, m = 0), v = Math.max(v, a), m += o + h, s[e] = {
                                    left: 0,
                                    top: 0,
                                    width: a,
                                    height: o
                                }
                            }), g += v, f.push(v), p.push(m), l.width += g
                        }
                        t.width = l.width, t.height = l.height
                    } else t.width = l.width = t.height = l.height = 0
                }, afterFit: wr, isHorizontal: function () {
                    return "top" === this.options.position || "bottom" === this.options.position
                }, draw: function () {
                    var t = this, e = t.options, n = e.labels, r = L.global, i = r.defaultColor, a = r.elements.line,
                        o = t.height, s = t.columnHeights, l = t.width, u = t.lineWidths;
                    if (e.display) {
                        var c, d = _r(e.rtl, t.left, t.minSize.width), h = t.ctx,
                            f = kr(n.fontColor, r.defaultFontColor), p = B.options._parseFont(n), g = p.size;
                        h.textAlign = d.textAlign("left"), h.textBaseline = "middle", h.lineWidth = .5, h.strokeStyle = f, h.fillStyle = f, h.font = p.string;
                        var v = Mr(n, g), m = t.legendHitBoxes, b = function (t, r) {
                            switch (e.align) {
                                case"start":
                                    return n.padding;
                                case"end":
                                    return t - r;
                                default:
                                    return (t - r + n.padding) / 2
                            }
                        }, y = t.isHorizontal();
                        c = y ? {x: t.left + b(l, u[0]), y: t.top + n.padding, line: 0} : {
                            x: t.left + n.padding,
                            y: t.top + b(o, s[0]),
                            line: 0
                        }, B.rtl.overrideTextDirection(t.ctx, e.textDirection);
                        var x = g + n.padding;
                        B.each(t.legendItems, function (e, r) {
                            var f = h.measureText(e.text).width, p = v + g / 2 + f, _ = c.x, w = c.y;
                            d.setWidth(t.minSize.width), y ? r > 0 && _ + p + n.padding > t.left + t.minSize.width && (w = c.y += x, c.line++, _ = c.x = t.left + b(l, u[c.line])) : r > 0 && w + x > t.top + t.minSize.height && (_ = c.x = _ + t.columnWidths[c.line] + n.padding, c.line++, w = c.y = t.top + b(o, s[c.line]));
                            var k = d.x(_);
                            !function (t, e, r) {
                                if (!(isNaN(v) || v <= 0)) {
                                    h.save();
                                    var o = kr(r.lineWidth, a.borderWidth);
                                    if (h.fillStyle = kr(r.fillStyle, i), h.lineCap = kr(r.lineCap, a.borderCapStyle), h.lineDashOffset = kr(r.lineDashOffset, a.borderDashOffset), h.lineJoin = kr(r.lineJoin, a.borderJoinStyle), h.lineWidth = o, h.strokeStyle = kr(r.strokeStyle, i), h.setLineDash && h.setLineDash(kr(r.lineDash, a.borderDash)), n && n.usePointStyle) {
                                        var s = v * Math.SQRT2 / 2, l = d.xPlus(t, v / 2), u = e + g / 2;
                                        B.canvas.drawPoint(h, r.pointStyle, s, l, u, r.rotation)
                                    } else h.fillRect(d.leftForLtr(t, v), e, v, g), 0 !== o && h.strokeRect(d.leftForLtr(t, v), e, v, g);
                                    h.restore()
                                }
                            }(k, w, e), m[r].left = d.leftForLtr(k, m[r].width), m[r].top = w, function (t, e, n, r) {
                                var i = g / 2, a = d.xPlus(t, v + i), o = e + i;
                                h.fillText(n.text, a, o), n.hidden && (h.beginPath(), h.lineWidth = 2, h.moveTo(a, o), h.lineTo(d.xPlus(a, r), o), h.stroke())
                            }(k, w, e, f), y ? c.x += p + n.padding : c.y += x
                        }), B.rtl.restoreTextDirection(t.ctx, e.textDirection)
                    }
                }, _getLegendItemAt: function (t, e) {
                    var n, r, i, a = this;
                    if (t >= a.left && t <= a.right && e >= a.top && e <= a.bottom) for (i = a.legendHitBoxes, n = 0; n < i.length; ++n) if (r = i[n], t >= r.left && t <= r.left + r.width && e >= r.top && e <= r.top + r.height) return a.legendItems[n];
                    return null
                }, handleEvent: function (t) {
                    var e, n = this, r = n.options, i = "mouseup" === t.type ? "click" : t.type;
                    if ("mousemove" === i) {
                        if (!r.onHover && !r.onLeave) return
                    } else {
                        if ("click" !== i) return;
                        if (!r.onClick) return
                    }
                    e = n._getLegendItemAt(t.x, t.y), "click" === i ? e && r.onClick && r.onClick.call(n, t.native, e) : (r.onLeave && e !== n._hoveredItem && (n._hoveredItem && r.onLeave.call(n, t.native, n._hoveredItem), n._hoveredItem = e), r.onHover && e && r.onHover.call(n, t.native, e))
                }
            });

            function Sr(t, e) {
                var n = new Fr({ctx: t.ctx, options: e, chart: t});
                he.configure(t, n, e), he.addBox(t, n), t.legend = n
            }

            var Cr = {
                id: "legend", _element: Fr, beforeInit: function (t) {
                    var e = t.options.legend;
                    e && Sr(t, e)
                }, beforeUpdate: function (t) {
                    var e = t.options.legend, n = t.legend;
                    e ? (B.mergeIf(e, L.global.legend), n ? (he.configure(t, n, e), n.options = e) : Sr(t, e)) : n && (he.removeBox(t, n), delete t.legend)
                }, afterEvent: function (t, e) {
                    var n = t.legend;
                    n && n.handleEvent(e)
                }
            }, Ar = B.noop;
            L._set("global", {
                title: {
                    display: !1,
                    fontStyle: "bold",
                    fullWidth: !0,
                    padding: 10,
                    position: "top",
                    text: "",
                    weight: 2e3
                }
            });
            var Tr = X.extend({
                initialize: function (t) {
                    B.extend(this, t), this.legendHitBoxes = []
                },
                beforeUpdate: Ar,
                update: function (t, e, n) {
                    var r = this;
                    return r.beforeUpdate(), r.maxWidth = t, r.maxHeight = e, r.margins = n, r.beforeSetDimensions(), r.setDimensions(), r.afterSetDimensions(), r.beforeBuildLabels(), r.buildLabels(), r.afterBuildLabels(), r.beforeFit(), r.fit(), r.afterFit(), r.afterUpdate(), r.minSize
                },
                afterUpdate: Ar,
                beforeSetDimensions: Ar,
                setDimensions: function () {
                    var t = this;
                    t.isHorizontal() ? (t.width = t.maxWidth, t.left = 0, t.right = t.width) : (t.height = t.maxHeight, t.top = 0, t.bottom = t.height), t.paddingLeft = 0, t.paddingTop = 0, t.paddingRight = 0, t.paddingBottom = 0, t.minSize = {
                        width: 0,
                        height: 0
                    }
                },
                afterSetDimensions: Ar,
                beforeBuildLabels: Ar,
                buildLabels: Ar,
                afterBuildLabels: Ar,
                beforeFit: Ar,
                fit: function () {
                    var t, e, n = this, r = n.options, i = n.minSize = {}, a = n.isHorizontal();
                    r.display ? (t = B.isArray(r.text) ? r.text.length : 1, e = t * B.options._parseFont(r).lineHeight + 2 * r.padding, n.width = i.width = a ? n.maxWidth : e, n.height = i.height = a ? e : n.maxHeight) : n.width = i.width = n.height = i.height = 0
                },
                afterFit: Ar,
                isHorizontal: function () {
                    var t = this.options.position;
                    return "top" === t || "bottom" === t
                },
                draw: function () {
                    var t = this, e = t.ctx, n = t.options;
                    if (n.display) {
                        var r, i, a, o = B.options._parseFont(n), s = o.lineHeight, l = s / 2 + n.padding, u = 0,
                            c = t.top, d = t.left, h = t.bottom, f = t.right;
                        e.fillStyle = B.valueOrDefault(n.fontColor, L.global.defaultFontColor), e.font = o.string, t.isHorizontal() ? (i = d + (f - d) / 2, a = c + l, r = f - d) : (i = "left" === n.position ? d + l : f - l, a = c + (h - c) / 2, r = h - c, u = Math.PI * ("left" === n.position ? -.5 : .5)), e.save(), e.translate(i, a), e.rotate(u), e.textAlign = "center", e.textBaseline = "middle";
                        var p = n.text;
                        if (B.isArray(p)) for (var g = 0, v = 0; v < p.length; ++v) e.fillText(p[v], 0, g, r), g += s; else e.fillText(p, 0, 0, r);
                        e.restore()
                    }
                }
            });

            function Pr(t, e) {
                var n = new Tr({ctx: t.ctx, options: e, chart: t});
                he.configure(t, n, e), he.addBox(t, n), t.titleBlock = n
            }

            var Dr = {}, Or = xr, Er = Cr, Ir = {
                id: "title", _element: Tr, beforeInit: function (t) {
                    var e = t.options.title;
                    e && Pr(t, e)
                }, beforeUpdate: function (t) {
                    var e = t.options.title, n = t.titleBlock;
                    e ? (B.mergeIf(e, L.global.title), n ? (he.configure(t, n, e), n.options = e) : Pr(t, e)) : n && (he.removeBox(t, n), delete t.titleBlock)
                }
            };
            for (var Rr in Dr.filler = Or, Dr.legend = Er, Dr.title = Ir, Je.helpers = B, function () {
                function t(t, e, n) {
                    var r;
                    return "string" === typeof t ? (r = parseInt(t, 10), -1 !== t.indexOf("%") && (r = r / 100 * e.parentNode[n])) : r = t, r
                }

                function e(t) {
                    return void 0 !== t && null !== t && "none" !== t
                }

                function n(n, r, i) {
                    var a = document.defaultView, o = B._getParentNode(n), s = a.getComputedStyle(n)[r],
                        l = a.getComputedStyle(o)[r], u = e(s), c = e(l), d = Number.POSITIVE_INFINITY;
                    return u || c ? Math.min(u ? t(s, n, i) : d, c ? t(l, o, i) : d) : "none"
                }

                B.where = function (t, e) {
                    if (B.isArray(t) && Array.prototype.filter) return t.filter(e);
                    var n = [];
                    return B.each(t, function (t) {
                        e(t) && n.push(t)
                    }), n
                }, B.findIndex = Array.prototype.findIndex ? function (t, e, n) {
                    return t.findIndex(e, n)
                } : function (t, e, n) {
                    n = void 0 === n ? t : n;
                    for (var r = 0, i = t.length; r < i; ++r) if (e.call(n, t[r], r, t)) return r;
                    return -1
                }, B.findNextWhere = function (t, e, n) {
                    B.isNullOrUndef(n) && (n = -1);
                    for (var r = n + 1; r < t.length; r++) {
                        var i = t[r];
                        if (e(i)) return i
                    }
                }, B.findPreviousWhere = function (t, e, n) {
                    B.isNullOrUndef(n) && (n = t.length);
                    for (var r = n - 1; r >= 0; r--) {
                        var i = t[r];
                        if (e(i)) return i
                    }
                }, B.isNumber = function (t) {
                    return !isNaN(parseFloat(t)) && isFinite(t)
                }, B.almostEquals = function (t, e, n) {
                    return Math.abs(t - e) < n
                }, B.almostWhole = function (t, e) {
                    var n = Math.round(t);
                    return n - e <= t && n + e >= t
                }, B.max = function (t) {
                    return t.reduce(function (t, e) {
                        return isNaN(e) ? t : Math.max(t, e)
                    }, Number.NEGATIVE_INFINITY)
                }, B.min = function (t) {
                    return t.reduce(function (t, e) {
                        return isNaN(e) ? t : Math.min(t, e)
                    }, Number.POSITIVE_INFINITY)
                }, B.sign = Math.sign ? function (t) {
                    return Math.sign(t)
                } : function (t) {
                    return 0 === (t = +t) || isNaN(t) ? t : t > 0 ? 1 : -1
                }, B.toRadians = function (t) {
                    return t * (Math.PI / 180)
                }, B.toDegrees = function (t) {
                    return t * (180 / Math.PI)
                }, B._decimalPlaces = function (t) {
                    if (B.isFinite(t)) {
                        for (var e = 1, n = 0; Math.round(t * e) / e !== t;) e *= 10, n++;
                        return n
                    }
                }, B.getAngleFromPoint = function (t, e) {
                    var n = e.x - t.x, r = e.y - t.y, i = Math.sqrt(n * n + r * r), a = Math.atan2(r, n);
                    return a < -.5 * Math.PI && (a += 2 * Math.PI), {angle: a, distance: i}
                }, B.distanceBetweenPoints = function (t, e) {
                    return Math.sqrt(Math.pow(e.x - t.x, 2) + Math.pow(e.y - t.y, 2))
                }, B.aliasPixel = function (t) {
                    return t % 2 === 0 ? 0 : .5
                }, B._alignPixel = function (t, e, n) {
                    var r = t.currentDevicePixelRatio, i = n / 2;
                    return Math.round((e - i) * r) / r + i
                }, B.splineCurve = function (t, e, n, r) {
                    var i = t.skip ? e : t, a = e, o = n.skip ? e : n,
                        s = Math.sqrt(Math.pow(a.x - i.x, 2) + Math.pow(a.y - i.y, 2)),
                        l = Math.sqrt(Math.pow(o.x - a.x, 2) + Math.pow(o.y - a.y, 2)), u = s / (s + l),
                        c = l / (s + l);
                    u = isNaN(u) ? 0 : u, c = isNaN(c) ? 0 : c;
                    var d = r * u, h = r * c;
                    return {
                        previous: {x: a.x - d * (o.x - i.x), y: a.y - d * (o.y - i.y)},
                        next: {x: a.x + h * (o.x - i.x), y: a.y + h * (o.y - i.y)}
                    }
                }, B.EPSILON = Number.EPSILON || 1e-14, B.splineCurveMonotone = function (t) {
                    var e, n, r, i, a, o, s, l, u, c = (t || []).map(function (t) {
                        return {model: t._model, deltaK: 0, mK: 0}
                    }), d = c.length;
                    for (e = 0; e < d; ++e) if (!(r = c[e]).model.skip) {
                        if (n = e > 0 ? c[e - 1] : null, (i = e < d - 1 ? c[e + 1] : null) && !i.model.skip) {
                            var h = i.model.x - r.model.x;
                            r.deltaK = 0 !== h ? (i.model.y - r.model.y) / h : 0
                        }
                        !n || n.model.skip ? r.mK = r.deltaK : !i || i.model.skip ? r.mK = n.deltaK : this.sign(n.deltaK) !== this.sign(r.deltaK) ? r.mK = 0 : r.mK = (n.deltaK + r.deltaK) / 2
                    }
                    for (e = 0; e < d - 1; ++e) r = c[e], i = c[e + 1], r.model.skip || i.model.skip || (B.almostEquals(r.deltaK, 0, this.EPSILON) ? r.mK = i.mK = 0 : (a = r.mK / r.deltaK, o = i.mK / r.deltaK, (l = Math.pow(a, 2) + Math.pow(o, 2)) <= 9 || (s = 3 / Math.sqrt(l), r.mK = a * s * r.deltaK, i.mK = o * s * r.deltaK)));
                    for (e = 0; e < d; ++e) (r = c[e]).model.skip || (n = e > 0 ? c[e - 1] : null, i = e < d - 1 ? c[e + 1] : null, n && !n.model.skip && (u = (r.model.x - n.model.x) / 3, r.model.controlPointPreviousX = r.model.x - u, r.model.controlPointPreviousY = r.model.y - u * r.mK), i && !i.model.skip && (u = (i.model.x - r.model.x) / 3, r.model.controlPointNextX = r.model.x + u, r.model.controlPointNextY = r.model.y + u * r.mK))
                }, B.nextItem = function (t, e, n) {
                    return n ? e >= t.length - 1 ? t[0] : t[e + 1] : e >= t.length - 1 ? t[t.length - 1] : t[e + 1]
                }, B.previousItem = function (t, e, n) {
                    return n ? e <= 0 ? t[t.length - 1] : t[e - 1] : e <= 0 ? t[0] : t[e - 1]
                }, B.niceNum = function (t, e) {
                    var n = Math.floor(B.log10(t)), r = t / Math.pow(10, n);
                    return (e ? r < 1.5 ? 1 : r < 3 ? 2 : r < 7 ? 5 : 10 : r <= 1 ? 1 : r <= 2 ? 2 : r <= 5 ? 5 : 10) * Math.pow(10, n)
                }, B.requestAnimFrame = "undefined" === typeof window ? function (t) {
                    t()
                } : window.requestAnimationFrame || window.webkitRequestAnimationFrame || window.mozRequestAnimationFrame || window.oRequestAnimationFrame || window.msRequestAnimationFrame || function (t) {
                    return window.setTimeout(t, 1e3 / 60)
                }, B.getRelativePosition = function (t, e) {
                    var n, r, i = t.originalEvent || t, a = t.target || t.srcElement, o = a.getBoundingClientRect(),
                        s = i.touches;
                    s && s.length > 0 ? (n = s[0].clientX, r = s[0].clientY) : (n = i.clientX, r = i.clientY);
                    var l = parseFloat(B.getStyle(a, "padding-left")), u = parseFloat(B.getStyle(a, "padding-top")),
                        c = parseFloat(B.getStyle(a, "padding-right")), d = parseFloat(B.getStyle(a, "padding-bottom")),
                        h = o.right - o.left - l - c, f = o.bottom - o.top - u - d;
                    return n = Math.round((n - o.left - l) / h * a.width / e.currentDevicePixelRatio), r = Math.round((r - o.top - u) / f * a.height / e.currentDevicePixelRatio), {
                        x: n,
                        y: r
                    }
                }, B.getConstraintWidth = function (t) {
                    return n(t, "max-width", "clientWidth")
                }, B.getConstraintHeight = function (t) {
                    return n(t, "max-height", "clientHeight")
                }, B._calculatePadding = function (t, e, n) {
                    return (e = B.getStyle(t, e)).indexOf("%") > -1 ? n * parseInt(e, 10) / 100 : parseInt(e, 10)
                }, B._getParentNode = function (t) {
                    var e = t.parentNode;
                    return e && "[object ShadowRoot]" === e.toString() && (e = e.host), e
                }, B.getMaximumWidth = function (t) {
                    var e = B._getParentNode(t);
                    if (!e) return t.clientWidth;
                    var n = e.clientWidth, r = B._calculatePadding(e, "padding-left", n),
                        i = B._calculatePadding(e, "padding-right", n), a = n - r - i, o = B.getConstraintWidth(t);
                    return isNaN(o) ? a : Math.min(a, o)
                }, B.getMaximumHeight = function (t) {
                    var e = B._getParentNode(t);
                    if (!e) return t.clientHeight;
                    var n = e.clientHeight, r = B._calculatePadding(e, "padding-top", n),
                        i = B._calculatePadding(e, "padding-bottom", n), a = n - r - i, o = B.getConstraintHeight(t);
                    return isNaN(o) ? a : Math.min(a, o)
                }, B.getStyle = function (t, e) {
                    return t.currentStyle ? t.currentStyle[e] : document.defaultView.getComputedStyle(t, null).getPropertyValue(e)
                }, B.retinaScale = function (t, e) {
                    var n = t.currentDevicePixelRatio = e || "undefined" !== typeof window && window.devicePixelRatio || 1;
                    if (1 !== n) {
                        var r = t.canvas, i = t.height, a = t.width;
                        r.height = i * n, r.width = a * n, t.ctx.scale(n, n), r.style.height || r.style.width || (r.style.height = i + "px", r.style.width = a + "px")
                    }
                }, B.fontString = function (t, e, n) {
                    return e + " " + t + "px " + n
                }, B.longestText = function (t, e, n, r) {
                    var i = (r = r || {}).data = r.data || {}, a = r.garbageCollect = r.garbageCollect || [];
                    r.font !== e && (i = r.data = {}, a = r.garbageCollect = [], r.font = e), t.font = e;
                    var o, s, l, u, c, d = 0, h = n.length;
                    for (o = 0; o < h; o++) if (void 0 !== (u = n[o]) && null !== u && !0 !== B.isArray(u)) d = B.measureText(t, i, a, d, u); else if (B.isArray(u)) for (s = 0, l = u.length; s < l; s++) void 0 === (c = u[s]) || null === c || B.isArray(c) || (d = B.measureText(t, i, a, d, c));
                    var f = a.length / 2;
                    if (f > n.length) {
                        for (o = 0; o < f; o++) delete i[a[o]];
                        a.splice(0, f)
                    }
                    return d
                }, B.measureText = function (t, e, n, r, i) {
                    var a = e[i];
                    return a || (a = e[i] = t.measureText(i).width, n.push(i)), a > r && (r = a), r
                }, B.numberOfLabelLines = function (t) {
                    var e = 1;
                    return B.each(t, function (t) {
                        B.isArray(t) && t.length > e && (e = t.length)
                    }), e
                }, B.color = w ? function (t) {
                    return t instanceof CanvasGradient && (t = L.global.defaultColor), w(t)
                } : function (t) {
                    return console.error("Color.js not found!"), t
                }, B.getHoverColor = function (t) {
                    return t instanceof CanvasPattern || t instanceof CanvasGradient ? t : B.color(t).saturate(.5).darken(.1).rgbString()
                }
            }(), Je._adapters = en, Je.Animation = K, Je.animationService = Z, Je.controllers = Gt, Je.DatasetController = nt, Je.defaults = L, Je.Element = X, Je.elements = _t, Je.Interaction = ne, Je.layouts = he, Je.platform = Pe, Je.plugins = De, Je.Scale = mn, Je.scaleService = Oe, Je.Ticks = nn, Je.Tooltip = Ue, Je.helpers.each(cr, function (t, e) {
                Je.scaleService.registerScaleType(e, t, t._defaults)
            }), Dr) Dr.hasOwnProperty(Rr) && Je.plugins.register(Dr[Rr]);
            Je.platform.initialize();
            var Lr = Je;
            return "undefined" !== typeof window && (window.Chart = Je), Je.Chart = Je, Je.Legend = Dr.legend._element, Je.Title = Dr.title._element, Je.pluginService = Je.plugins, Je.PluginBase = Je.Element.extend({}), Je.canvasHelpers = Je.helpers.canvas, Je.layoutService = Je.layouts, Je.LinearScaleBase = kn, Je.helpers.each(["Bar", "Bubble", "Doughnut", "Line", "PolarArea", "Radar", "Scatter"], function (t) {
                Je[t] = function (e, n) {
                    return new Je(e, Je.helpers.merge(n || {}, {type: t.charAt(0).toLowerCase() + t.slice(1)}))
                }
            }), Lr
        }(function () {
            try {
                return n(196)
            } catch (t) {
            }
        }())
    }, 892: function (t, e) {
        var n = Object.prototype.hasOwnProperty;
        t.exports = function (t, e) {
            return null != t && n.call(t, e)
        }
    }, 893: function (t, e, n) {
        var r = n(894), i = 1, a = 4;
        t.exports = function (t, e) {
            return r(t, i | a, e = "function" == typeof e ? e : void 0)
        }
    }, 894: function (t, e, n) {
        var r = n(697), i = n(895), a = n(752), o = n(897), s = n(898), l = n(901), u = n(754), c = n(902), d = n(903),
            h = n(734), f = n(904), p = n(667), g = n(905), v = n(906), m = n(911), b = n(646), y = n(702), x = n(913),
            _ = n(651), w = n(915), k = n(654), M = 1, F = 2, S = 4, C = "[object Arguments]", A = "[object Function]",
            T = "[object GeneratorFunction]", P = "[object Object]", D = {};
        D[C] = D["[object Array]"] = D["[object ArrayBuffer]"] = D["[object DataView]"] = D["[object Boolean]"] = D["[object Date]"] = D["[object Float32Array]"] = D["[object Float64Array]"] = D["[object Int8Array]"] = D["[object Int16Array]"] = D["[object Int32Array]"] = D["[object Map]"] = D["[object Number]"] = D[P] = D["[object RegExp]"] = D["[object Set]"] = D["[object String]"] = D["[object Symbol]"] = D["[object Uint8Array]"] = D["[object Uint8ClampedArray]"] = D["[object Uint16Array]"] = D["[object Uint32Array]"] = !0, D["[object Error]"] = D[A] = D["[object WeakMap]"] = !1, t.exports = function t(e, n, O, E, I, R) {
            var L, N = n & M, j = n & F, z = n & S;
            if (O && (L = I ? O(e, E, I, R) : O(e)), void 0 !== L) return L;
            if (!_(e)) return e;
            var V = b(e);
            if (V) {
                if (L = g(e), !N) return u(e, L)
            } else {
                var B = p(e), W = B == A || B == T;
                if (y(e)) return l(e, N);
                if (B == P || B == C || W && !I) {
                    if (L = j || W ? {} : m(e), !N) return j ? d(e, s(L, e)) : c(e, o(L, e))
                } else {
                    if (!D[B]) return I ? e : {};
                    L = v(e, B, N)
                }
            }
            R || (R = new r);
            var H = R.get(e);
            if (H) return H;
            R.set(e, L), w(e) ? e.forEach(function (r) {
                L.add(t(r, n, O, r, e, R))
            }) : x(e) && e.forEach(function (r, i) {
                L.set(i, t(r, n, O, i, e, R))
            });
            var U = z ? j ? f : h : j ? keysIn : k, Y = V ? void 0 : U(e);
            return i(Y || e, function (r, i) {
                Y && (r = e[i = r]), a(L, i, t(r, n, O, i, e, R))
            }), L
        }
    }, 895: function (t, e) {
        t.exports = function (t, e) {
            for (var n = -1, r = null == t ? 0 : t.length; ++n < r && !1 !== e(t[n], n, t);) ;
            return t
        }
    }, 896: function (t, e, n) {
        var r = n(653), i = function () {
            try {
                var t = r(Object, "defineProperty");
                return t({}, "", {}), t
            } catch (e) {
            }
        }();
        t.exports = i
    }, 897: function (t, e, n) {
        var r = n(684), i = n(654);
        t.exports = function (t, e) {
            return t && r(e, i(e), t)
        }
    }, 898: function (t, e, n) {
        var r = n(684), i = n(753);
        t.exports = function (t, e) {
            return t && r(e, i(e), t)
        }
    }, 899: function (t, e, n) {
        var r = n(651), i = n(707), a = n(900), o = Object.prototype.hasOwnProperty;
        t.exports = function (t) {
            if (!r(t)) return a(t);
            var e = i(t), n = [];
            for (var s in t) ("constructor" != s || !e && o.call(t, s)) && n.push(s);
            return n
        }
    }, 900: function (t, e) {
        t.exports = function (t) {
            var e = [];
            if (null != t) for (var n in Object(t)) e.push(n);
            return e
        }
    }, 901: function (t, e, n) {
        (function (t) {
            var r = n(649), i = e && !e.nodeType && e, a = i && "object" == typeof t && t && !t.nodeType && t,
                o = a && a.exports === i ? r.Buffer : void 0, s = o ? o.allocUnsafe : void 0;
            t.exports = function (t, e) {
                if (e) return t.slice();
                var n = t.length, r = s ? s(n) : new t.constructor(n);
                return t.copy(r), r
            }
        }).call(this, n(319)(t))
    }, 902: function (t, e, n) {
        var r = n(684), i = n(701);
        t.exports = function (t, e) {
            return r(t, i(t), e)
        }
    }, 903: function (t, e, n) {
        var r = n(684), i = n(755);
        t.exports = function (t, e) {
            return r(t, i(t), e)
        }
    }, 904: function (t, e, n) {
        var r = n(735), i = n(755), a = n(753);
        t.exports = function (t) {
            return r(t, a, i)
        }
    }, 905: function (t, e) {
        var n = Object.prototype.hasOwnProperty;
        t.exports = function (t) {
            var e = t.length, r = new t.constructor(e);
            return e && "string" == typeof t[0] && n.call(t, "index") && (r.index = t.index, r.input = t.input), r
        }
    }, 906: function (t, e, n) {
        var r = n(711), i = n(907), a = n(908), o = n(909), s = n(910), l = "[object Boolean]", u = "[object Date]",
            c = "[object Map]", d = "[object Number]", h = "[object RegExp]", f = "[object Set]", p = "[object String]",
            g = "[object Symbol]", v = "[object ArrayBuffer]", m = "[object DataView]", b = "[object Float32Array]",
            y = "[object Float64Array]", x = "[object Int8Array]", _ = "[object Int16Array]", w = "[object Int32Array]",
            k = "[object Uint8Array]", M = "[object Uint8ClampedArray]", F = "[object Uint16Array]",
            S = "[object Uint32Array]";
        t.exports = function (t, e, n) {
            var C = t.constructor;
            switch (e) {
                case v:
                    return r(t);
                case l:
                case u:
                    return new C(+t);
                case m:
                    return i(t, n);
                case b:
                case y:
                case x:
                case _:
                case w:
                case k:
                case M:
                case F:
                case S:
                    return s(t, n);
                case c:
                    return new C;
                case d:
                case p:
                    return new C(t);
                case h:
                    return a(t);
                case f:
                    return new C;
                case g:
                    return o(t)
            }
        }
    }, 907: function (t, e, n) {
        var r = n(711);
        t.exports = function (t, e) {
            var n = e ? r(t.buffer) : t.buffer;
            return new t.constructor(n, t.byteOffset, t.byteLength)
        }
    }, 908: function (t, e) {
        var n = /\w*$/;
        t.exports = function (t) {
            var e = new t.constructor(t.source, n.exec(t));
            return e.lastIndex = t.lastIndex, e
        }
    }, 909: function (t, e, n) {
        var r = n(660), i = r ? r.prototype : void 0, a = i ? i.valueOf : void 0;
        t.exports = function (t) {
            return a ? Object(a.call(t)) : {}
        }
    }, 910: function (t, e, n) {
        var r = n(711);
        t.exports = function (t, e) {
            var n = e ? r(t.buffer) : t.buffer;
            return new t.constructor(n, t.byteOffset, t.length)
        }
    }, 911: function (t, e, n) {
        var r = n(912), i = n(756), a = n(707);
        t.exports = function (t) {
            return "function" != typeof t.constructor || a(t) ? {} : r(i(t))
        }
    }, 912: function (t, e, n) {
        var r = n(651), i = Object.create, a = function () {
            function t() {
            }

            return function (e) {
                if (!r(e)) return {};
                if (i) return i(e);
                t.prototype = e;
                var n = new t;
                return t.prototype = void 0, n
            }
        }();
        t.exports = a
    }, 913: function (t, e, n) {
        var r = n(914), i = n(705), a = n(706), o = a && a.isMap, s = o ? i(o) : r;
        t.exports = s
    }, 914: function (t, e, n) {
        var r = n(667), i = n(652), a = "[object Map]";
        t.exports = function (t) {
            return i(t) && r(t) == a
        }
    }, 915: function (t, e, n) {
        var r = n(916), i = n(705), a = n(706), o = a && a.isSet, s = o ? i(o) : r;
        t.exports = s
    }, 916: function (t, e, n) {
        var r = n(667), i = n(652), a = "[object Set]";
        t.exports = function (t) {
            return i(t) && r(t) == a
        }
    }, 917: function (t, e, n) {
        var r = n(660), i = n(754), a = n(667), o = n(661), s = n(918), l = n(919), u = n(732), c = n(733), d = n(757),
            h = n(922), f = "[object Map]", p = "[object Set]", g = r ? r.iterator : void 0;
        t.exports = function (t) {
            if (!t) return [];
            if (o(t)) return s(t) ? d(t) : i(t);
            if (g && t[g]) return l(t[g]());
            var e = a(t);
            return (e == f ? u : e == p ? c : h)(t)
        }
    }, 918: function (t, e, n) {
        var r = n(659), i = n(646), a = n(652), o = "[object String]";
        t.exports = function (t) {
            return "string" == typeof t || !i(t) && a(t) && r(t) == o
        }
    }, 919: function (t, e) {
        t.exports = function (t) {
            for (var e, n = []; !(e = t.next()).done;) n.push(e.value);
            return n
        }
    }, 920: function (t, e) {
        t.exports = function (t) {
            return t.split("")
        }
    }, 921: function (t, e) {
        var n = "[\\ud800-\\udfff]", r = "[\\u0300-\\u036f\\ufe20-\\ufe2f\\u20d0-\\u20ff]",
            i = "\\ud83c[\\udffb-\\udfff]", a = "[^\\ud800-\\udfff]", o = "(?:\\ud83c[\\udde6-\\uddff]){2}",
            s = "[\\ud800-\\udbff][\\udc00-\\udfff]", l = "(?:" + r + "|" + i + ")" + "?",
            u = "[\\ufe0e\\ufe0f]?" + l + ("(?:\\u200d(?:" + [a, o, s].join("|") + ")[\\ufe0e\\ufe0f]?" + l + ")*"),
            c = "(?:" + [a + r + "?", r, o, s, n].join("|") + ")", d = RegExp(i + "(?=" + i + ")|" + c + u, "g");
        t.exports = function (t) {
            return t.match(d) || []
        }
    }, 922: function (t, e, n) {
        var r = n(923), i = n(654);
        t.exports = function (t) {
            return null == t ? [] : r(t, i(t))
        }
    }, 923: function (t, e, n) {
        var r = n(746);
        t.exports = function (t, e) {
            return r(e, function (e) {
                return t[e]
            })
        }
    }, 924: function (t, e, n) {
        "use strict";
        var r = n(317);
        e.__esModule = !0, e.default = void 0;
        var i = r(n(663)), a = r(n(658)), o = function () {
            function t(t, e) {
                if (this.refs = t, "function" !== typeof e) {
                    if (!(0, i.default)(e, "is")) throw new TypeError("`is:` is required for `when()` conditions");
                    if (!e.then && !e.otherwise) throw new TypeError("either `then:` or `otherwise:` is required for `when()` conditions");
                    var n = e.is, r = e.then, a = e.otherwise, o = "function" === typeof n ? n : function () {
                        for (var t = arguments.length, e = new Array(t), r = 0; r < t; r++) e[r] = arguments[r];
                        return e.every(function (t) {
                            return t === n
                        })
                    };
                    this.fn = function () {
                        for (var t = arguments.length, e = new Array(t), n = 0; n < t; n++) e[n] = arguments[n];
                        var i = e.pop(), s = e.pop(), l = o.apply(void 0, e) ? r : a;
                        if (l) return "function" === typeof l ? l(s) : s.concat(l.resolve(i))
                    }
                } else this.fn = e
            }

            return t.prototype.resolve = function (t, e) {
                var n = this.refs.map(function (t) {
                    return t.getValue(e)
                }), r = this.fn.apply(t, n.concat(t, e));
                if (void 0 === r || r === t) return t;
                if (!(0, a.default)(r)) throw new TypeError("conditions must return a schema object");
                return r.resolve(e)
            }, t
        }();
        e.default = o, t.exports = e.default
    }, 925: function (t, e, n) {
        "use strict";
        var r = n(317);
        e.__esModule = !0, e.default = function t(e, n) {
            for (var r in n) if ((0, i.default)(n, r)) {
                var s = n[r], l = e[r];
                if (void 0 === l) e[r] = s; else {
                    if (l === s) continue;
                    (0, a.default)(l) ? (0, a.default)(s) && (e[r] = s.concat(l)) : o(l) ? o(s) && (e[r] = t(l, s)) : Array.isArray(l) && Array.isArray(s) && (e[r] = s.concat(l))
                }
            }
            return e
        };
        var i = r(n(663)), a = r(n(658)), o = function (t) {
            return "[object Object]" === Object.prototype.toString.call(t)
        };
        t.exports = e.default
    }, 926: function (t, e, n) {
        "use strict";
        var r = n(317);
        e.__esModule = !0, e.createErrorFactory = h, e.default = function (t) {
            var e = t.name, n = t.message, r = t.test, o = t.params;

            function c(t) {
                var c = t.value, f = t.path, p = t.label, g = t.options, v = t.originalValue, m = t.sync,
                    b = (0, i.default)(t, ["value", "path", "label", "options", "originalValue", "sync"]), y = g.parent,
                    x = function (t) {
                        return l.default.isRef(t) ? t.getValue({value: c, parent: y, context: g.context}) : t
                    },
                    _ = h({message: n, path: f, value: c, originalValue: v, params: o, label: p, resolve: x, name: e}),
                    w = (0, a.default)({path: f, parent: y, type: e, createError: _, resolve: x, options: g}, b);
                return function (t, e, n, r) {
                    var i = t.call(e, n);
                    if (!r) return Promise.resolve(i);
                    if (d(i)) throw new Error('Validation test of type: "' + e.type + '" returned a Promise during a synchronous validate. This test will finish after the validate call has returned');
                    return u.SynchronousPromise.resolve(i)
                }(r, w, c, m).then(function (t) {
                    if (s.default.isError(t)) throw t;
                    if (!t) throw _()
                })
            }

            return c.OPTIONS = t, c
        };
        var i = r(n(209)), a = r(n(108)), o = r(n(760)), s = r(n(713)), l = r(n(669)), u = n(759),
            c = s.default.formatError, d = function (t) {
                return t && "function" === typeof t.then && "function" === typeof t.catch
            };

        function h(t) {
            var e = t.value, n = t.label, r = t.resolve, l = t.originalValue,
                u = (0, i.default)(t, ["value", "label", "resolve", "originalValue"]);
            return function (t) {
                var i = void 0 === t ? {} : t, d = i.path, h = void 0 === d ? u.path : d, f = i.message,
                    p = void 0 === f ? u.message : f, g = i.type, v = void 0 === g ? u.name : g, m = i.params;
                return m = (0, a.default)({path: h, value: e, originalValue: l, label: n}, function (t, e, n) {
                    return (0, o.default)((0, a.default)({}, t, e), n)
                }(u.params, m, r)), (0, a.default)(new s.default(c(p, m), e, h, v), {params: m})
            }
        }
    }, 927: function (t, e, n) {
        "use strict";
        var r = n(317);
        e.__esModule = !0, e.default = void 0;
        var i = r(n(664)), a = r(n(656)), o = s;

        function s() {
            var t = this;
            if (!(this instanceof s)) return new s;
            a.default.call(this, {type: "boolean"}), this.withMutation(function () {
                t.transform(function (t) {
                    if (!this.isType(t)) {
                        if (/^(true|1)$/i.test(t)) return !0;
                        if (/^(false|0)$/i.test(t)) return !1
                    }
                    return t
                })
            })
        }

        e.default = o, (0, i.default)(s, a.default, {
            _typeCheck: function (t) {
                return t instanceof Boolean && (t = t.valueOf()), "boolean" === typeof t
            }
        }), t.exports = e.default
    }, 928: function (t, e, n) {
        "use strict";
        var r = n(317);
        e.__esModule = !0, e.default = d;
        var i = r(n(664)), a = r(n(656)), o = n(657), s = r(n(687)),
            l = /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))$/i,
            u = /^((https?|ftp):)?\/\/(((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:)*@)?(((\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5]))|((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?)(:\d*)?)(\/((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)+(\/(([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)*)*)?)?(\?((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|[\uE000-\uF8FF]|\/|\?)*)?(\#((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|\/|\?)*)?$/i,
            c = function (t) {
                return (0, s.default)(t) || t === t.trim()
            };

        function d() {
            var t = this;
            if (!(this instanceof d)) return new d;
            a.default.call(this, {type: "string"}), this.withMutation(function () {
                t.transform(function (t) {
                    return this.isType(t) ? t : null != t && t.toString ? t.toString() : t
                })
            })
        }

        (0, i.default)(d, a.default, {
            _typeCheck: function (t) {
                return t instanceof String && (t = t.valueOf()), "string" === typeof t
            }, _isPresent: function (t) {
                return a.default.prototype._cast.call(this, t) && t.length > 0
            }, length: function (t, e) {
                return void 0 === e && (e = o.string.length), this.test({
                    message: e,
                    name: "length",
                    exclusive: !0,
                    params: {length: t},
                    test: function (e) {
                        return (0, s.default)(e) || e.length === this.resolve(t)
                    }
                })
            }, min: function (t, e) {
                return void 0 === e && (e = o.string.min), this.test({
                    message: e,
                    name: "min",
                    exclusive: !0,
                    params: {min: t},
                    test: function (e) {
                        return (0, s.default)(e) || e.length >= this.resolve(t)
                    }
                })
            }, max: function (t, e) {
                return void 0 === e && (e = o.string.max), this.test({
                    name: "max",
                    exclusive: !0,
                    message: e,
                    params: {max: t},
                    test: function (e) {
                        return (0, s.default)(e) || e.length <= this.resolve(t)
                    }
                })
            }, matches: function (t, e) {
                var n, r = !1;
                return e && (e.message || e.hasOwnProperty("excludeEmptyString") ? (r = e.excludeEmptyString, n = e.message) : n = e), this.test({
                    message: n || o.string.matches,
                    params: {regex: t},
                    test: function (e) {
                        return (0, s.default)(e) || "" === e && r || t.test(e)
                    }
                })
            }, email: function (t) {
                return void 0 === t && (t = o.string.email), this.matches(l, {message: t, excludeEmptyString: !0})
            }, url: function (t) {
                return void 0 === t && (t = o.string.url), this.matches(u, {message: t, excludeEmptyString: !0})
            }, ensure: function () {
                return this.default("").transform(function (t) {
                    return null === t ? "" : t
                })
            }, trim: function (t) {
                return void 0 === t && (t = o.string.trim), this.transform(function (t) {
                    return null != t ? t.trim() : t
                }).test({message: t, name: "trim", test: c})
            }, lowercase: function (t) {
                return void 0 === t && (t = o.string.lowercase), this.transform(function (t) {
                    return (0, s.default)(t) ? t : t.toLowerCase()
                }).test({
                    message: t, name: "string_case", exclusive: !0, test: function (t) {
                        return (0, s.default)(t) || t === t.toLowerCase()
                    }
                })
            }, uppercase: function (t) {
                return void 0 === t && (t = o.string.uppercase), this.transform(function (t) {
                    return (0, s.default)(t) ? t : t.toUpperCase()
                }).test({
                    message: t, name: "string_case", exclusive: !0, test: function (t) {
                        return (0, s.default)(t) || t === t.toUpperCase()
                    }
                })
            }
        }), t.exports = e.default
    }, 929: function (t, e, n) {
        "use strict";
        var r = n(317);
        e.__esModule = !0, e.default = u;
        var i = r(n(664)), a = r(n(656)), o = n(657), s = r(n(687)), l = function (t) {
            return (0, s.default)(t) || t === (0 | t)
        };

        function u() {
            var t = this;
            if (!(this instanceof u)) return new u;
            a.default.call(this, {type: "number"}), this.withMutation(function () {
                t.transform(function (t) {
                    var e = t;
                    if ("string" === typeof e) {
                        if ("" === (e = e.replace(/\s/g, ""))) return NaN;
                        e = +e
                    }
                    return this.isType(e) ? e : parseFloat(e)
                })
            })
        }

        (0, i.default)(u, a.default, {
            _typeCheck: function (t) {
                return t instanceof Number && (t = t.valueOf()), "number" === typeof t && !function (t) {
                    return t != +t
                }(t)
            }, min: function (t, e) {
                return void 0 === e && (e = o.number.min), this.test({
                    message: e,
                    name: "min",
                    exclusive: !0,
                    params: {min: t},
                    test: function (e) {
                        return (0, s.default)(e) || e >= this.resolve(t)
                    }
                })
            }, max: function (t, e) {
                return void 0 === e && (e = o.number.max), this.test({
                    message: e,
                    name: "max",
                    exclusive: !0,
                    params: {max: t},
                    test: function (e) {
                        return (0, s.default)(e) || e <= this.resolve(t)
                    }
                })
            }, lessThan: function (t, e) {
                return void 0 === e && (e = o.number.lessThan), this.test({
                    message: e,
                    name: "max",
                    exclusive: !0,
                    params: {less: t},
                    test: function (e) {
                        return (0, s.default)(e) || e < this.resolve(t)
                    }
                })
            }, moreThan: function (t, e) {
                return void 0 === e && (e = o.number.moreThan), this.test({
                    message: e,
                    name: "min",
                    exclusive: !0,
                    params: {more: t},
                    test: function (e) {
                        return (0, s.default)(e) || e > this.resolve(t)
                    }
                })
            }, positive: function (t) {
                return void 0 === t && (t = o.number.positive), this.moreThan(0, t)
            }, negative: function (t) {
                return void 0 === t && (t = o.number.negative), this.lessThan(0, t)
            }, integer: function (t) {
                return void 0 === t && (t = o.number.integer), this.test({name: "integer", message: t, test: l})
            }, truncate: function () {
                return this.transform(function (t) {
                    return (0, s.default)(t) ? t : 0 | t
                })
            }, round: function (t) {
                var e = ["ceil", "floor", "round", "trunc"];
                if ("trunc" === (t = t && t.toLowerCase() || "round")) return this.truncate();
                if (-1 === e.indexOf(t.toLowerCase())) throw new TypeError("Only valid options for round() are: " + e.join(", "));
                return this.transform(function (e) {
                    return (0, s.default)(e) ? e : Math[t](e)
                })
            }
        }), t.exports = e.default
    }, 930: function (t, e, n) {
        "use strict";
        var r = n(317);
        e.__esModule = !0, e.default = void 0;
        var i = r(n(656)), a = r(n(664)), o = r(n(931)), s = n(657), l = r(n(687)), u = r(n(669)), c = new Date(""),
            d = h;

        function h() {
            var t = this;
            if (!(this instanceof h)) return new h;
            i.default.call(this, {type: "date"}), this.withMutation(function () {
                t.transform(function (t) {
                    return this.isType(t) ? t : (t = (0, o.default)(t)) ? new Date(t) : c
                })
            })
        }

        e.default = d, (0, a.default)(h, i.default, {
            _typeCheck: function (t) {
                return e = t, "[object Date]" === Object.prototype.toString.call(e) && !isNaN(t.getTime());
                var e
            }, min: function (t, e) {
                void 0 === e && (e = s.date.min);
                var n = t;
                if (!u.default.isRef(n) && (n = this.cast(t), !this._typeCheck(n))) throw new TypeError("`min` must be a Date or a value that can be `cast()` to a Date");
                return this.test({
                    message: e, name: "min", exclusive: !0, params: {min: t}, test: function (t) {
                        return (0, l.default)(t) || t >= this.resolve(n)
                    }
                })
            }, max: function (t, e) {
                void 0 === e && (e = s.date.max);
                var n = t;
                if (!u.default.isRef(n) && (n = this.cast(t), !this._typeCheck(n))) throw new TypeError("`max` must be a Date or a value that can be `cast()` to a Date");
                return this.test({
                    message: e, name: "max", exclusive: !0, params: {max: t}, test: function (t) {
                        return (0, l.default)(t) || t <= this.resolve(n)
                    }
                })
            }
        }), t.exports = e.default
    }, 931: function (t, e, n) {
        "use strict";
        e.__esModule = !0, e.default = function (t) {
            var e, n, i = [1, 4, 5, 6, 7, 10, 11], a = 0;
            if (n = r.exec(t)) {
                for (var o, s = 0; o = i[s]; ++s) n[o] = +n[o] || 0;
                n[2] = (+n[2] || 1) - 1, n[3] = +n[3] || 1, n[7] = n[7] ? String(n[7]).substr(0, 3) : 0, void 0 !== n[8] && "" !== n[8] || void 0 !== n[9] && "" !== n[9] ? ("Z" !== n[8] && void 0 !== n[9] && (a = 60 * n[10] + n[11], "+" === n[9] && (a = 0 - a)), e = Date.UTC(n[1], n[2], n[3], n[4], n[5] + a, n[6], n[7])) : e = +new Date(n[1], n[2], n[3], n[4], n[5], n[6], n[7])
            } else e = Date.parse ? Date.parse(t) : NaN;
            return e
        };
        var r = /^(\d{4}|[+\-]\d{6})(?:-?(\d{2})(?:-?(\d{2}))?)?(?:[ T]?(\d{2}):?(\d{2})(?::?(\d{2})(?:[,\.](\d{1,}))?)?(?:(Z)|([+\-])(\d{2})(?::?(\d{2}))?)?)?$/;
        t.exports = e.default
    }, 932: function (t, e, n) {
        "use strict";
        var r = n(762), i = n(317);
        e.__esModule = !0, e.default = k;
        var a = i(n(763)), o = i(n(108)), s = i(n(663)), l = i(n(933)), u = i(n(942)), c = i(n(948)), d = i(n(760)),
            h = n(686), f = i(n(656)), p = n(657), g = i(n(949)), v = i(n(951)), m = i(n(664)), b = i(n(765)),
            y = r(n(712));

        function x() {
            var t = (0, a.default)(["", ".", ""]);
            return x = function () {
                return t
            }, t
        }

        function _() {
            var t = (0, a.default)(["", ".", ""]);
            return _ = function () {
                return t
            }, t
        }

        var w = function (t) {
            return "[object Object]" === Object.prototype.toString.call(t)
        };

        function k(t) {
            var e = this;
            if (!(this instanceof k)) return new k(t);
            f.default.call(this, {
                type: "object", default: function () {
                    var t = this;
                    if (this._nodes.length) {
                        var e = {};
                        return this._nodes.forEach(function (n) {
                            e[n] = t.fields[n].default ? t.fields[n].default() : void 0
                        }), e
                    }
                }
            }), this.fields = Object.create(null), this._nodes = [], this._excludedEdges = [], this.withMutation(function () {
                e.transform(function (t) {
                    if ("string" === typeof t) try {
                        t = JSON.parse(t)
                    } catch (e) {
                        t = null
                    }
                    return this.isType(t) ? t : null
                }), t && e.shape(t)
            })
        }

        (0, m.default)(k, f.default, {
            _typeCheck: function (t) {
                return w(t) || "function" === typeof t
            }, _cast: function (t, e) {
                var n = this;
                void 0 === e && (e = {});
                var r = f.default.prototype._cast.call(this, t, e);
                if (void 0 === r) return this.default();
                if (!this._typeCheck(r)) return r;
                var i = this.fields, a = !0 === this._option("stripUnknown", e),
                    l = this._nodes.concat(Object.keys(r).filter(function (t) {
                        return -1 === n._nodes.indexOf(t)
                    })), u = {}, c = (0, o.default)({}, e, {parent: u, __validating: !1}), d = !1;
                return l.forEach(function (t) {
                    var n = i[t], o = (0, s.default)(r, t);
                    if (n) {
                        var l, h = n._options && n._options.strict;
                        if (c.path = (0, b.default)(_(), e.path, t), c.value = r[t], !0 === (n = n.resolve(c))._strip) return void (d = d || t in r);
                        void 0 !== (l = e.__validating && h ? r[t] : n.cast(r[t], c)) && (u[t] = l)
                    } else o && !a && (u[t] = r[t]);
                    u[t] !== r[t] && (d = !0)
                }), d ? u : r
            }, _validate: function (t, e) {
                var n, r, i = this;
                void 0 === e && (e = {});
                var a = e.sync, s = [], l = null != e.originalValue ? e.originalValue : t;
                return n = this._option("abortEarly", e), r = this._option("recursive", e), e = (0, o.default)({}, e, {
                    __validating: !0,
                    originalValue: l
                }), f.default.prototype._validate.call(this, t, e).catch((0, y.propagateErrors)(n, s)).then(function (t) {
                    if (!r || !w(t)) {
                        if (s.length) throw s[0];
                        return t
                    }
                    l = l || t;
                    var u = i._nodes.map(function (n) {
                        var r = (0, b.default)(x(), e.path, n), a = i.fields[n],
                            s = (0, o.default)({}, e, {path: r, parent: t, originalValue: l[n]});
                        return a && a.validate ? (s.strict = !0, a.validate(t[n], s)) : Promise.resolve(!0)
                    });
                    return (0, y.default)({
                        sync: a,
                        validations: u,
                        value: t,
                        errors: s,
                        endEarly: n,
                        path: e.path,
                        sort: (0, v.default)(i.fields)
                    })
                })
            }, concat: function (t) {
                var e = f.default.prototype.concat.call(this, t);
                return e._nodes = (0, g.default)(e.fields, e._excludedEdges), e
            }, shape: function (t, e) {
                void 0 === e && (e = []);
                var n = this.clone(), r = (0, o.default)(n.fields, t);
                if (n.fields = r, e.length) {
                    Array.isArray(e[0]) || (e = [e]);
                    var i = e.map(function (t) {
                        return t[0] + "-" + t[1]
                    });
                    n._excludedEdges = n._excludedEdges.concat(i)
                }
                return n._nodes = (0, g.default)(r, n._excludedEdges), n
            }, from: function (t, e, n) {
                var r = (0, h.getter)(t, !0);
                return this.transform(function (i) {
                    if (null == i) return i;
                    var a = i;
                    return (0, s.default)(i, t) && (a = (0, o.default)({}, i), n || delete a[t], a[e] = r(i)), a
                })
            }, noUnknown: function (t, e) {
                void 0 === t && (t = !0), void 0 === e && (e = p.object.noUnknown), "string" === typeof t && (e = t, t = !0);
                var n = this.test({
                    name: "noUnknown", exclusive: !0, message: e, test: function (e) {
                        return null == e || !t || 0 === function (t, e) {
                            var n = Object.keys(t.fields);
                            return Object.keys(e).filter(function (t) {
                                return -1 === n.indexOf(t)
                            })
                        }(this.schema, e).length
                    }
                });
                return n._options.stripUnknown = t, n
            }, unknown: function (t, e) {
                return void 0 === t && (t = !0), void 0 === e && (e = p.object.noUnknown), this.noUnknown(!t, e)
            }, transformKeys: function (t) {
                return this.transform(function (e) {
                    return e && (0, c.default)(e, function (e, n) {
                        return t(n)
                    })
                })
            }, camelCase: function () {
                return this.transformKeys(u.default)
            }, snakeCase: function () {
                return this.transformKeys(l.default)
            }, constantCase: function () {
                return this.transformKeys(function (t) {
                    return (0, l.default)(t).toUpperCase()
                })
            }, describe: function () {
                var t = f.default.prototype.describe.call(this);
                return t.fields = (0, d.default)(this.fields, function (t) {
                    return t.describe()
                }), t
            }
        }), t.exports = e.default
    }, 933: function (t, e, n) {
        var r = n(764)(function (t, e, n) {
            return t + (n ? "_" : "") + e.toLowerCase()
        });
        t.exports = r
    }, 934: function (t, e) {
        t.exports = function (t, e, n, r) {
            var i = -1, a = null == t ? 0 : t.length;
            for (r && a && (n = t[++i]); ++i < a;) n = e(n, t[i], i, t);
            return n
        }
    }, 935: function (t, e, n) {
        var r = n(936), i = n(668), a = /[\xc0-\xd6\xd8-\xf6\xf8-\xff\u0100-\u017f]/g,
            o = RegExp("[\\u0300-\\u036f\\ufe20-\\ufe2f\\u20d0-\\u20ff]", "g");
        t.exports = function (t) {
            return (t = i(t)) && t.replace(a, r).replace(o, "")
        }
    }, 936: function (t, e, n) {
        var r = n(937)({
            "\xc0": "A",
            "\xc1": "A",
            "\xc2": "A",
            "\xc3": "A",
            "\xc4": "A",
            "\xc5": "A",
            "\xe0": "a",
            "\xe1": "a",
            "\xe2": "a",
            "\xe3": "a",
            "\xe4": "a",
            "\xe5": "a",
            "\xc7": "C",
            "\xe7": "c",
            "\xd0": "D",
            "\xf0": "d",
            "\xc8": "E",
            "\xc9": "E",
            "\xca": "E",
            "\xcb": "E",
            "\xe8": "e",
            "\xe9": "e",
            "\xea": "e",
            "\xeb": "e",
            "\xcc": "I",
            "\xcd": "I",
            "\xce": "I",
            "\xcf": "I",
            "\xec": "i",
            "\xed": "i",
            "\xee": "i",
            "\xef": "i",
            "\xd1": "N",
            "\xf1": "n",
            "\xd2": "O",
            "\xd3": "O",
            "\xd4": "O",
            "\xd5": "O",
            "\xd6": "O",
            "\xd8": "O",
            "\xf2": "o",
            "\xf3": "o",
            "\xf4": "o",
            "\xf5": "o",
            "\xf6": "o",
            "\xf8": "o",
            "\xd9": "U",
            "\xda": "U",
            "\xdb": "U",
            "\xdc": "U",
            "\xf9": "u",
            "\xfa": "u",
            "\xfb": "u",
            "\xfc": "u",
            "\xdd": "Y",
            "\xfd": "y",
            "\xff": "y",
            "\xc6": "Ae",
            "\xe6": "ae",
            "\xde": "Th",
            "\xfe": "th",
            "\xdf": "ss",
            "\u0100": "A",
            "\u0102": "A",
            "\u0104": "A",
            "\u0101": "a",
            "\u0103": "a",
            "\u0105": "a",
            "\u0106": "C",
            "\u0108": "C",
            "\u010a": "C",
            "\u010c": "C",
            "\u0107": "c",
            "\u0109": "c",
            "\u010b": "c",
            "\u010d": "c",
            "\u010e": "D",
            "\u0110": "D",
            "\u010f": "d",
            "\u0111": "d",
            "\u0112": "E",
            "\u0114": "E",
            "\u0116": "E",
            "\u0118": "E",
            "\u011a": "E",
            "\u0113": "e",
            "\u0115": "e",
            "\u0117": "e",
            "\u0119": "e",
            "\u011b": "e",
            "\u011c": "G",
            "\u011e": "G",
            "\u0120": "G",
            "\u0122": "G",
            "\u011d": "g",
            "\u011f": "g",
            "\u0121": "g",
            "\u0123": "g",
            "\u0124": "H",
            "\u0126": "H",
            "\u0125": "h",
            "\u0127": "h",
            "\u0128": "I",
            "\u012a": "I",
            "\u012c": "I",
            "\u012e": "I",
            "\u0130": "I",
            "\u0129": "i",
            "\u012b": "i",
            "\u012d": "i",
            "\u012f": "i",
            "\u0131": "i",
            "\u0134": "J",
            "\u0135": "j",
            "\u0136": "K",
            "\u0137": "k",
            "\u0138": "k",
            "\u0139": "L",
            "\u013b": "L",
            "\u013d": "L",
            "\u013f": "L",
            "\u0141": "L",
            "\u013a": "l",
            "\u013c": "l",
            "\u013e": "l",
            "\u0140": "l",
            "\u0142": "l",
            "\u0143": "N",
            "\u0145": "N",
            "\u0147": "N",
            "\u014a": "N",
            "\u0144": "n",
            "\u0146": "n",
            "\u0148": "n",
            "\u014b": "n",
            "\u014c": "O",
            "\u014e": "O",
            "\u0150": "O",
            "\u014d": "o",
            "\u014f": "o",
            "\u0151": "o",
            "\u0154": "R",
            "\u0156": "R",
            "\u0158": "R",
            "\u0155": "r",
            "\u0157": "r",
            "\u0159": "r",
            "\u015a": "S",
            "\u015c": "S",
            "\u015e": "S",
            "\u0160": "S",
            "\u015b": "s",
            "\u015d": "s",
            "\u015f": "s",
            "\u0161": "s",
            "\u0162": "T",
            "\u0164": "T",
            "\u0166": "T",
            "\u0163": "t",
            "\u0165": "t",
            "\u0167": "t",
            "\u0168": "U",
            "\u016a": "U",
            "\u016c": "U",
            "\u016e": "U",
            "\u0170": "U",
            "\u0172": "U",
            "\u0169": "u",
            "\u016b": "u",
            "\u016d": "u",
            "\u016f": "u",
            "\u0171": "u",
            "\u0173": "u",
            "\u0174": "W",
            "\u0175": "w",
            "\u0176": "Y",
            "\u0177": "y",
            "\u0178": "Y",
            "\u0179": "Z",
            "\u017b": "Z",
            "\u017d": "Z",
            "\u017a": "z",
            "\u017c": "z",
            "\u017e": "z",
            "\u0132": "IJ",
            "\u0133": "ij",
            "\u0152": "Oe",
            "\u0153": "oe",
            "\u0149": "'n",
            "\u017f": "s"
        });
        t.exports = r
    }, 937: function (t, e) {
        t.exports = function (t) {
            return function (e) {
                return null == t ? void 0 : t[e]
            }
        }
    }, 938: function (t, e, n) {
        var r = n(939), i = n(940), a = n(668), o = n(941);
        t.exports = function (t, e, n) {
            return t = a(t), void 0 === (e = n ? void 0 : e) ? i(t) ? o(t) : r(t) : t.match(e) || []
        }
    }, 939: function (t, e) {
        var n = /[^\x00-\x2f\x3a-\x40\x5b-\x60\x7b-\x7f]+/g;
        t.exports = function (t) {
            return t.match(n) || []
        }
    }, 940: function (t, e) {
        var n = /[a-z][A-Z]|[A-Z]{2}[a-z]|[0-9][a-zA-Z]|[a-zA-Z][0-9]|[^a-zA-Z0-9 ]/;
        t.exports = function (t) {
            return n.test(t)
        }
    }, 941: function (t, e) {
        var n = "\\xac\\xb1\\xd7\\xf7\\x00-\\x2f\\x3a-\\x40\\x5b-\\x60\\x7b-\\xbf\\u2000-\\u206f \\t\\x0b\\f\\xa0\\ufeff\\n\\r\\u2028\\u2029\\u1680\\u180e\\u2000\\u2001\\u2002\\u2003\\u2004\\u2005\\u2006\\u2007\\u2008\\u2009\\u200a\\u202f\\u205f\\u3000",
            r = "[" + n + "]", i = "\\d+", a = "[\\u2700-\\u27bf]", o = "[a-z\\xdf-\\xf6\\xf8-\\xff]",
            s = "[^\\ud800-\\udfff" + n + i + "\\u2700-\\u27bfa-z\\xdf-\\xf6\\xf8-\\xffA-Z\\xc0-\\xd6\\xd8-\\xde]",
            l = "(?:\\ud83c[\\udde6-\\uddff]){2}", u = "[\\ud800-\\udbff][\\udc00-\\udfff]",
            c = "[A-Z\\xc0-\\xd6\\xd8-\\xde]", d = "(?:" + o + "|" + s + ")", h = "(?:" + c + "|" + s + ")",
            f = "(?:[\\u0300-\\u036f\\ufe20-\\ufe2f\\u20d0-\\u20ff]|\\ud83c[\\udffb-\\udfff])?",
            p = "[\\ufe0e\\ufe0f]?" + f + ("(?:\\u200d(?:" + ["[^\\ud800-\\udfff]", l, u].join("|") + ")[\\ufe0e\\ufe0f]?" + f + ")*"),
            g = "(?:" + [a, l, u].join("|") + ")" + p,
            v = RegExp([c + "?" + o + "+(?:['\u2019](?:d|ll|m|re|s|t|ve))?(?=" + [r, c, "$"].join("|") + ")", h + "+(?:['\u2019](?:D|LL|M|RE|S|T|VE))?(?=" + [r, c + d, "$"].join("|") + ")", c + "?" + d + "+(?:['\u2019](?:d|ll|m|re|s|t|ve))?", c + "+(?:['\u2019](?:D|LL|M|RE|S|T|VE))?", "\\d*(?:1ST|2ND|3RD|(?![123])\\dTH)(?=\\b|[a-z_])", "\\d*(?:1st|2nd|3rd|(?![123])\\dth)(?=\\b|[A-Z_])", i, g].join("|"), "g");
        t.exports = function (t) {
            return t.match(v) || []
        }
    }, 942: function (t, e, n) {
        var r = n(943), i = n(764)(function (t, e, n) {
            return e = e.toLowerCase(), t + (n ? r(e) : e)
        });
        t.exports = i
    }, 943: function (t, e, n) {
        var r = n(668), i = n(944);
        t.exports = function (t) {
            return i(r(t).toLowerCase())
        }
    }, 944: function (t, e, n) {
        var r = n(945)("toUpperCase");
        t.exports = r
    }, 945: function (t, e, n) {
        var r = n(946), i = n(758), a = n(757), o = n(668);
        t.exports = function (t) {
            return function (e) {
                e = o(e);
                var n = i(e) ? a(e) : void 0, s = n ? n[0] : e.charAt(0), l = n ? r(n, 1).join("") : e.slice(1);
                return s[t]() + l
            }
        }
    }, 946: function (t, e, n) {
        var r = n(947);
        t.exports = function (t, e, n) {
            var i = t.length;
            return n = void 0 === n ? i : n, !e && n >= i ? t : r(t, e, n)
        }
    }, 947: function (t, e) {
        t.exports = function (t, e, n) {
            var r = -1, i = t.length;
            e < 0 && (e = -e > i ? 0 : i + e), (n = n > i ? i : n) < 0 && (n += i), i = e > n ? 0 : n - e >>> 0, e >>>= 0;
            for (var a = Array(i); ++r < i;) a[r] = t[r + e];
            return a
        }
    }, 948: function (t, e, n) {
        var r = n(683), i = n(709), a = n(666);
        t.exports = function (t, e) {
            var n = {};
            return e = a(e, 3), i(t, function (t, i, a) {
                r(n, e(t, i, a), t)
            }), n
        }
    }, 949: function (t, e, n) {
        "use strict";
        var r = n(317);
        e.__esModule = !0, e.default = function (t, e) {
            void 0 === e && (e = []);
            var n = [], r = [];

            function u(t, i) {
                var a = (0, o.split)(t)[0];
                ~r.indexOf(a) || r.push(a), ~e.indexOf(i + "-" + a) || n.push([i, a])
            }

            for (var c in t) if ((0, i.default)(t, c)) {
                var d = t[c];
                ~r.indexOf(c) || r.push(c), s.default.isRef(d) && d.isSibling ? u(d.path, c) : (0, l.default)(d) && d._deps && d._deps.forEach(function (t) {
                    return u(t, c)
                })
            }
            return a.default.array(r, n).reverse()
        };
        var i = r(n(663)), a = r(n(950)), o = n(686), s = r(n(669)), l = r(n(658));
        t.exports = e.default
    }, 950: function (t, e) {
        function n(t, e) {
            var n = t.length, r = new Array(n), i = {}, a = n, o = function (t) {
                for (var e = new Map, n = 0, r = t.length; n < r; n++) {
                    var i = t[n];
                    e.has(i[0]) || e.set(i[0], new Set), e.has(i[1]) || e.set(i[1], new Set), e.get(i[0]).add(i[1])
                }
                return e
            }(e), s = function (t) {
                for (var e = new Map, n = 0, r = t.length; n < r; n++) e.set(t[n], n);
                return e
            }(t);
            for (e.forEach(function (t) {
                if (!s.has(t[0]) || !s.has(t[1])) throw new Error("Unknown node. There is an unknown node in the supplied edges.")
            }); a--;) i[a] || l(t[a], a, new Set);
            return r;

            function l(t, e, a) {
                if (a.has(t)) {
                    var u;
                    try {
                        u = ", node was:" + JSON.stringify(t)
                    } catch (h) {
                        u = ""
                    }
                    throw new Error("Cyclic dependency" + u)
                }
                if (!s.has(t)) throw new Error("Found unknown node. Make sure to provided all involved nodes. Unknown node: " + JSON.stringify(t));
                if (!i[e]) {
                    i[e] = !0;
                    var c = o.get(t) || new Set;
                    if (e = (c = Array.from(c)).length) {
                        a.add(t);
                        do {
                            var d = c[--e];
                            l(d, s.get(d), a)
                        } while (e);
                        a.delete(t)
                    }
                    r[--n] = t
                }
            }
        }

        t.exports = function (t) {
            return n(function (t) {
                for (var e = new Set, n = 0, r = t.length; n < r; n++) {
                    var i = t[n];
                    e.add(i[0]), e.add(i[1])
                }
                return Array.from(e)
            }(t), t)
        }, t.exports.array = n
    }, 951: function (t, e, n) {
        "use strict";

        function r(t, e) {
            var n = 1 / 0;
            return t.some(function (t, r) {
                if (-1 !== e.path.indexOf(t)) return n = r, !0
            }), n
        }

        e.__esModule = !0, e.default = function (t) {
            var e = Object.keys(t);
            return function (t, n) {
                return r(e, t) - r(e, n)
            }
        }, t.exports = e.default
    }, 952: function (t, e, n) {
        "use strict";
        var r = n(762), i = n(317);
        e.__esModule = !0, e.default = void 0;
        var a = i(n(108)), o = i(n(763)), s = i(n(664)), l = i(n(687)), u = i(n(658)), c = i(n(765)), d = i(n(685)),
            h = i(n(656)), f = n(657), p = r(n(712));

        function g() {
            var t = (0, o.default)(["", "[", "]"]);
            return g = function () {
                return t
            }, t
        }

        var v = m;

        function m(t) {
            var e = this;
            if (!(this instanceof m)) return new m(t);
            h.default.call(this, {type: "array"}), this._subType = void 0, this.withMutation(function () {
                e.transform(function (t) {
                    if ("string" === typeof t) try {
                        t = JSON.parse(t)
                    } catch (e) {
                        t = null
                    }
                    return this.isType(t) ? t : null
                }), t && e.of(t)
            })
        }

        e.default = v, (0, s.default)(m, h.default, {
            _typeCheck: function (t) {
                return Array.isArray(t)
            }, _cast: function (t, e) {
                var n = this, r = h.default.prototype._cast.call(this, t, e);
                if (!this._typeCheck(r) || !this._subType) return r;
                var i = !1, a = r.map(function (t) {
                    var r = n._subType.cast(t, e);
                    return r !== t && (i = !0), r
                });
                return i ? a : r
            }, _validate: function (t, e) {
                var n = this;
                void 0 === e && (e = {});
                var r = [], i = e.sync, o = e.path, s = this._subType, l = this._option("abortEarly", e),
                    u = this._option("recursive", e), d = null != e.originalValue ? e.originalValue : t;
                return h.default.prototype._validate.call(this, t, e).catch((0, p.propagateErrors)(l, r)).then(function (t) {
                    if (!u || !s || !n._typeCheck(t)) {
                        if (r.length) throw r[0];
                        return t
                    }
                    d = d || t;
                    var h = t.map(function (n, r) {
                        var i = (0, c.default)(g(), e.path, r),
                            o = (0, a.default)({}, e, {path: i, strict: !0, parent: t, originalValue: d[r]});
                        return !s.validate || s.validate(n, o)
                    });
                    return (0, p.default)({sync: i, path: o, value: t, errors: r, endEarly: l, validations: h})
                })
            }, _isPresent: function (t) {
                return h.default.prototype._cast.call(this, t) && t.length > 0
            }, of: function (t) {
                var e = this.clone();
                if (!1 !== t && !(0, u.default)(t)) throw new TypeError("`array.of()` sub-schema must be a valid yup schema, or `false` to negate a current sub-schema. not: " + (0, d.default)(t));
                return e._subType = t, e
            }, min: function (t, e) {
                return e = e || f.array.min, this.test({
                    message: e,
                    name: "min",
                    exclusive: !0,
                    params: {min: t},
                    test: function (e) {
                        return (0, l.default)(e) || e.length >= this.resolve(t)
                    }
                })
            }, max: function (t, e) {
                return e = e || f.array.max, this.test({
                    message: e,
                    name: "max",
                    exclusive: !0,
                    params: {max: t},
                    test: function (e) {
                        return (0, l.default)(e) || e.length <= this.resolve(t)
                    }
                })
            }, ensure: function () {
                var t = this;
                return this.default(function () {
                    return []
                }).transform(function (e) {
                    return t.isType(e) ? e : null === e ? [] : [].concat(e)
                })
            }, compact: function (t) {
                var e = t ? function (e, n, r) {
                    return !t(e, n, r)
                } : function (t) {
                    return !!t
                };
                return this.transform(function (t) {
                    return null != t ? t.filter(e) : t
                })
            }, describe: function () {
                var t = h.default.prototype.describe.call(this);
                return this._subType && (t.innerType = this._subType.describe()), t
            }
        }), t.exports = e.default
    }, 953: function (t, e, n) {
        "use strict";
        var r = n(317);
        e.__esModule = !0, e.default = void 0;
        var i = r(n(658)), a = function () {
            function t(t) {
                this._resolve = function (e, n) {
                    var r = t(e, n);
                    if (!(0, i.default)(r)) throw new TypeError("lazy() functions must return a valid schema");
                    return r.resolve(n)
                }
            }

            var e = t.prototype;
            return e.resolve = function (t) {
                return this._resolve(t.value, t)
            }, e.cast = function (t, e) {
                return this._resolve(t, e).cast(t, e)
            }, e.validate = function (t, e) {
                return this._resolve(t, e).validate(t, e)
            }, e.validateSync = function (t, e) {
                return this._resolve(t, e).validateSync(t, e)
            }, e.validateAt = function (t, e, n) {
                return this._resolve(e, n).validateAt(t, e, n)
            }, e.validateSyncAt = function (t, e, n) {
                return this._resolve(e, n).validateSyncAt(t, e, n)
            }, t
        }();
        a.prototype.__isYupSchema__ = !0;
        var o = a;
        e.default = o, t.exports = e.default
    }, 954: function (t, e, n) {
        "use strict";
        var r = n(317);
        e.__esModule = !0, e.default = function (t) {
            Object.keys(t).forEach(function (e) {
                Object.keys(t[e]).forEach(function (n) {
                    i.default[e][n] = t[e][n]
                })
            })
        };
        var i = r(n(657));
        t.exports = e.default
    }, 976: function (t, e, n) {
        "use strict";
        Object.defineProperty(e, "__esModule", {value: !0}), e.Intent = {
            NONE: "none",
            PRIMARY: "primary",
            SUCCESS: "success",
            WARNING: "warning",
            DANGER: "danger"
        }
    }, 980: function (t, e, n) {
        var r = n(768);
        (r = "function" === typeof r ? r : window.Chart).Annotation = r.Annotation || {}, r.Annotation.drawTimeOptions = {
            afterDraw: "afterDraw",
            afterDatasetsDraw: "afterDatasetsDraw",
            beforeDatasetsDraw: "beforeDatasetsDraw"
        }, r.Annotation.defaults = {
            drawTime: "afterDatasetsDraw",
            dblClickSpeed: 350,
            events: [],
            annotations: []
        }, r.Annotation.labelDefaults = {
            backgroundColor: "rgba(0,0,0,0.8)",
            fontFamily: r.defaults.global.defaultFontFamily,
            fontSize: r.defaults.global.defaultFontSize,
            fontStyle: "bold",
            fontColor: "#fff",
            xPadding: 6,
            yPadding: 6,
            cornerRadius: 6,
            position: "center",
            xAdjust: 0,
            yAdjust: 0,
            enabled: !1,
            content: null
        }, r.Annotation.Element = n(981)(r), r.Annotation.types = {line: n(982)(r), box: n(983)(r)};
        var i = n(984)(r);
        t.exports = i, r.pluginService.register(i)
    }, 981: function (t, e) {
        t.exports = function (t) {
            var e = t.helpers;
            return t.Element.extend({
                initialize: function () {
                    this.hidden = !1, this.hovering = !1, this._model = e.clone(this._model) || {}, this.setDataLimits()
                }, destroy: function () {
                }, setDataLimits: function () {
                }, configure: function () {
                }, inRange: function () {
                }, getCenterPoint: function () {
                }, getWidth: function () {
                }, getHeight: function () {
                }, getArea: function () {
                }, draw: function () {
                }
            })
        }
    }, 982: function (t, e, n) {
        t.exports = function (t) {
            var e = t.helpers, r = n(688)(t), i = "horizontal", a = "vertical";

            function o(t) {
                var e = (t.x2 - t.x1) / (t.y2 - t.y1), n = t.x1 || 0;
                this.m = e, this.b = n, this.getX = function (r) {
                    return e * (r - t.y1) + n
                }, this.getY = function (r) {
                    return (r - n) / e + t.y1
                }, this.intersects = function (t, e, n) {
                    n = n || .001;
                    var r = this.getY(t), i = this.getX(e);
                    return (!isFinite(r) || Math.abs(e - r) < n) && (!isFinite(i) || Math.abs(t - i) < n)
                }
            }

            return t.Annotation.Element.extend({
                setDataLimits: function () {
                    var t = this._model, e = this.options;
                    t.ranges = {}, t.ranges[e.scaleID] = {min: e.value, max: e.endValue || e.value}
                }, configure: function () {
                    var t, n, s = this._model, l = this.options, u = this.chartInstance, c = u.chart.ctx,
                        d = u.scales[l.scaleID];
                    if (d && (t = r.isValid(l.value) ? d.getPixelForValue(l.value) : NaN, n = r.isValid(l.endValue) ? d.getPixelForValue(l.endValue) : t), !isNaN(t)) {
                        var h = u.chartArea;
                        s.clip = {
                            x1: h.left,
                            x2: h.right,
                            y1: h.top,
                            y2: h.bottom
                        }, this.options.mode == i ? (s.x1 = h.left, s.x2 = h.right, s.y1 = t, s.y2 = n) : (s.y1 = h.top, s.y2 = h.bottom, s.x1 = t, s.x2 = n), s.line = new o(s), s.mode = l.mode, s.labelBackgroundColor = l.label.backgroundColor, s.labelFontFamily = l.label.fontFamily, s.labelFontSize = l.label.fontSize, s.labelFontStyle = l.label.fontStyle, s.labelFontColor = l.label.fontColor, s.labelXPadding = l.label.xPadding, s.labelYPadding = l.label.yPadding, s.labelCornerRadius = l.label.cornerRadius, s.labelPosition = l.label.position, s.labelXAdjust = l.label.xAdjust, s.labelYAdjust = l.label.yAdjust, s.labelEnabled = l.label.enabled, s.labelContent = l.label.content, c.font = e.fontString(s.labelFontSize, s.labelFontStyle, s.labelFontFamily);
                        var f = c.measureText(s.labelContent).width, p = c.measureText("M").width,
                            g = function (t, e, n, r, o) {
                                var s = t.line, l = {}, u = 0, c = 0;
                                switch (!0) {
                                    case t.mode == a && "top" == t.labelPosition:
                                        c = o + t.labelYAdjust, u = e / 2 + t.labelXAdjust, l.y = t.y1 + c, l.x = (isFinite(s.m) ? s.getX(l.y) : t.x1) - u;
                                        break;
                                    case t.mode == a && "bottom" == t.labelPosition:
                                        c = n + o + t.labelYAdjust, u = e / 2 + t.labelXAdjust, l.y = t.y2 - c, l.x = (isFinite(s.m) ? s.getX(l.y) : t.x1) - u;
                                        break;
                                    case t.mode == i && "left" == t.labelPosition:
                                        u = r + t.labelXAdjust, c = -n / 2 + t.labelYAdjust, l.x = t.x1 + u, l.y = s.getY(l.x) + c;
                                        break;
                                    case t.mode == i && "right" == t.labelPosition:
                                        u = e + r + t.labelXAdjust, c = -n / 2 + t.labelYAdjust, l.x = t.x2 - u, l.y = s.getY(l.x) + c;
                                        break;
                                    default:
                                        l.x = (t.x1 + t.x2 - e) / 2 + t.labelXAdjust, l.y = (t.y1 + t.y2 - n) / 2 + t.labelYAdjust
                                }
                                return l
                            }(s, f, p, s.labelXPadding, s.labelYPadding);
                        s.labelX = g.x - s.labelXPadding, s.labelY = g.y - s.labelYPadding, s.labelWidth = f + 2 * s.labelXPadding, s.labelHeight = p + 2 * s.labelYPadding, s.borderColor = l.borderColor, s.borderWidth = l.borderWidth, s.borderDash = l.borderDash || [], s.borderDashOffset = l.borderDashOffset || 0
                    }
                }, inRange: function (t, e) {
                    var n = this._model;
                    return n.line && n.line.intersects(t, e, this.getHeight()) || n.labelEnabled && n.labelContent && t >= n.labelX && t <= n.labelX + n.labelWidth && e >= n.labelY && e <= n.labelY + n.labelHeight
                }, getCenterPoint: function () {
                    return {x: (this._model.x2 + this._model.x1) / 2, y: (this._model.y2 + this._model.y1) / 2}
                }, getWidth: function () {
                    return Math.abs(this._model.right - this._model.left)
                }, getHeight: function () {
                    return this._model.borderWidth || 1
                }, getArea: function () {
                    return Math.sqrt(Math.pow(this.getWidth(), 2) + Math.pow(this.getHeight(), 2))
                }, draw: function () {
                    var t = this._view, n = this.chartInstance.chart.ctx;
                    t.clip && (n.save(), n.beginPath(), n.rect(t.clip.x1, t.clip.y1, t.clip.x2 - t.clip.x1, t.clip.y2 - t.clip.y1), n.clip(), n.lineWidth = t.borderWidth, n.strokeStyle = t.borderColor, n.setLineDash && n.setLineDash(t.borderDash), n.lineDashOffset = t.borderDashOffset, n.beginPath(), n.moveTo(t.x1, t.y1), n.lineTo(t.x2, t.y2), n.stroke(), t.labelEnabled && t.labelContent && (n.beginPath(), n.rect(t.clip.x1, t.clip.y1, t.clip.x2 - t.clip.x1, t.clip.y2 - t.clip.y1), n.clip(), n.fillStyle = t.labelBackgroundColor, e.drawRoundedRectangle(n, t.labelX, t.labelY, t.labelWidth, t.labelHeight, t.labelCornerRadius), n.fill(), n.font = e.fontString(t.labelFontSize, t.labelFontStyle, t.labelFontFamily), n.fillStyle = t.labelFontColor, n.textAlign = "center", n.textBaseline = "middle", n.fillText(t.labelContent, t.labelX + t.labelWidth / 2, t.labelY + t.labelHeight / 2)), n.restore())
                }
            })
        }
    }, 983: function (t, e, n) {
        t.exports = function (t) {
            var e = n(688)(t);
            return t.Annotation.Element.extend({
                setDataLimits: function () {
                    var t = this._model, n = this.options, r = this.chartInstance, i = r.scales[n.xScaleID],
                        a = r.scales[n.yScaleID], o = r.chartArea;
                    if (t.ranges = {}, o) {
                        var s = 0, l = 0;
                        i && (s = e.isValid(n.xMin) ? n.xMin : i.getPixelForValue(o.left), l = e.isValid(n.xMax) ? n.xMax : i.getPixelForValue(o.right), t.ranges[n.xScaleID] = {
                            min: Math.min(s, l),
                            max: Math.max(s, l)
                        }), a && (s = e.isValid(n.yMin) ? n.yMin : a.getPixelForValue(o.bottom), l = e.isValid(n.yMax) ? n.yMax : a.getPixelForValue(o.top), t.ranges[n.yScaleID] = {
                            min: Math.min(s, l),
                            max: Math.max(s, l)
                        })
                    }
                }, configure: function () {
                    var t = this._model, n = this.options, r = this.chartInstance, i = r.scales[n.xScaleID],
                        a = r.scales[n.yScaleID], o = r.chartArea;
                    t.clip = {x1: o.left, x2: o.right, y1: o.top, y2: o.bottom};
                    var s, l, u = o.left, c = o.top, d = o.right, h = o.bottom;
                    i && (s = e.isValid(n.xMin) ? i.getPixelForValue(n.xMin) : o.left, l = e.isValid(n.xMax) ? i.getPixelForValue(n.xMax) : o.right, u = Math.min(s, l), d = Math.max(s, l)), a && (s = e.isValid(n.yMin) ? a.getPixelForValue(n.yMin) : o.bottom, l = e.isValid(n.yMax) ? a.getPixelForValue(n.yMax) : o.top, c = Math.min(s, l), h = Math.max(s, l)), t.left = u, t.top = c, t.right = d, t.bottom = h, t.borderColor = n.borderColor, t.borderWidth = n.borderWidth, t.backgroundColor = n.backgroundColor
                }, inRange: function (t, e) {
                    var n = this._model;
                    return n && t >= n.left && t <= n.right && e >= n.top && e <= n.bottom
                }, getCenterPoint: function () {
                    var t = this._model;
                    return {x: (t.right + t.left) / 2, y: (t.bottom + t.top) / 2}
                }, getWidth: function () {
                    var t = this._model;
                    return Math.abs(t.right - t.left)
                }, getHeight: function () {
                    var t = this._model;
                    return Math.abs(t.bottom - t.top)
                }, getArea: function () {
                    return this.getWidth() * this.getHeight()
                }, draw: function () {
                    var t = this._view, e = this.chartInstance.chart.ctx;
                    e.save(), e.beginPath(), e.rect(t.clip.x1, t.clip.y1, t.clip.x2 - t.clip.x1, t.clip.y2 - t.clip.y1), e.clip(), e.lineWidth = t.borderWidth, e.strokeStyle = t.borderColor, e.fillStyle = t.backgroundColor;
                    var n = t.right - t.left, r = t.bottom - t.top;
                    e.fillRect(t.left, t.top, n, r), e.strokeRect(t.left, t.top, n, r), e.restore()
                }
            })
        }
    }, 984: function (t, e, n) {
        t.exports = function (t) {
            var e = t.helpers, r = n(688)(t), i = n(985)(t), a = t.Annotation.types;

            function o(t) {
                r.decorate(t, "afterDataLimits", function (t, e) {
                    t && t(e), r.adjustScaleRange(e)
                })
            }

            function s(t) {
                return function (e, n) {
                    var i = e.annotation.options.drawTime;
                    r.elements(e).filter(function (e) {
                        return t === (e.options.drawTime || i)
                    }).forEach(function (t) {
                        t.transition(n).draw()
                    })
                }
            }

            return {
                beforeInit: function (t) {
                    var n = t.options, i = t.annotation = {
                        elements: {},
                        options: r.initConfig(n.annotation || {}),
                        onDestroy: [],
                        firstRun: !0,
                        supported: !1
                    };
                    t.ensureScalesHaveIDs(), n.scales && (i.supported = !0, e.each(n.scales.xAxes, o), e.each(n.scales.yAxes, o))
                },
                beforeUpdate: function (t) {
                    var e = t.annotation;
                    if (e.supported) {
                        e.firstRun ? e.firstRun = !1 : e.options = r.initConfig(t.options.annotation || {});
                        var n = [];
                        e.options.annotations.forEach(function (i) {
                            var o = i.id || r.objectId();
                            if (!e.elements[o] && a[i.type]) {
                                var s = new (0, a[i.type])({id: o, options: i, chartInstance: t});
                                s.initialize(), e.elements[o] = s, i.id = o, n.push(o)
                            } else e.elements[o] && n.push(o)
                        }), Object.keys(e.elements).forEach(function (t) {
                            -1 === n.indexOf(t) && (e.elements[t].destroy(), delete e.elements[t])
                        })
                    }
                },
                afterScaleUpdate: function (t) {
                    r.elements(t).forEach(function (t) {
                        t.configure()
                    })
                },
                beforeDatasetsDraw: s("beforeDatasetsDraw"),
                afterDatasetsDraw: s("afterDatasetsDraw"),
                afterDraw: s("afterDraw"),
                afterInit: function (t) {
                    var n = t.annotation.options.events;
                    if (e.isArray(n) && n.length > 0) {
                        var r = t.chart.canvas, a = i.dispatcher.bind(t);
                        i.collapseHoverEvents(n).forEach(function (n) {
                            e.addEvent(r, n, a), t.annotation.onDestroy.push(function () {
                                e.removeEvent(r, n, a)
                            })
                        })
                    }
                },
                destroy: function (t) {
                    for (var e = t.annotation.onDestroy; e.length > 0;) e.pop()()
                }
            }
        }
    }, 985: function (t, e, n) {
        t.exports = function (t) {
            var e = t.helpers, r = n(688)(t);

            function i(t) {
                var e = !1, n = t.filter(function (t) {
                    switch (t) {
                        case"mouseenter":
                        case"mouseover":
                        case"mouseout":
                        case"mouseleave":
                            return e = !0, !1;
                        default:
                            return !0
                    }
                });
                return e && -1 === n.indexOf("mousemove") && n.push("mousemove"), n
            }

            return {
                dispatcher: function (t) {
                    var n = this.annotation, a = r.elements(this), o = e.getRelativePosition(t, this.chart),
                        s = r.getNearestItems(a, o), l = i(n.options.events), u = n.options.dblClickSpeed, c = [],
                        d = r.getEventHandlerName(t.type), h = (s || {}).options;
                    if ("mousemove" === t.type && (s && !s.hovering ? ["mouseenter", "mouseover"].forEach(function (e) {
                        var n = r.getEventHandlerName(e), i = r.createMouseEvent(e, t);
                        s.hovering = !0, "function" === typeof h[n] && c.push([h[n], i, s])
                    }) : s || a.forEach(function (e) {
                        if (e.hovering) {
                            e.hovering = !1;
                            var n = e.options;
                            ["mouseout", "mouseleave"].forEach(function (i) {
                                var a = r.getEventHandlerName(i), o = r.createMouseEvent(i, t);
                                "function" === typeof n[a] && c.push([n[a], o, e])
                            })
                        }
                    })), s && l.indexOf("dblclick") > -1 && "function" === typeof h.onDblclick) {
                        if ("click" === t.type && "function" === typeof h.onClick) return clearTimeout(s.clickTimeout), s.clickTimeout = setTimeout(function () {
                            delete s.clickTimeout, h.onClick.call(s, t)
                        }, u), t.stopImmediatePropagation(), void t.preventDefault();
                        "dblclick" === t.type && s.clickTimeout && (clearTimeout(s.clickTimeout), delete s.clickTimeout)
                    }
                    s && "function" === typeof h[d] && 0 === c.length && c.push([h[d], t, s]), c.length > 0 && (t.stopImmediatePropagation(), t.preventDefault(), c.forEach(function (t) {
                        t[0].call(t[2], t[1])
                    }))
                }, collapseHoverEvents: i
            }
        }
    }, 991: function (t, e, n) {
        "use strict";
        (function (t) {
            n.d(e, "a", function () {
                return ct
            });
            var r = n(2), i = n.n(r), a = n(992), o = n.n(a), s = n(716), l = n.n(s), u = n(0), c = n.n(u), d = n(168),
                h = n.n(d), f = "bodyAttributes", p = "htmlAttributes", g = "titleAttributes", v = {
                    BASE: "base",
                    BODY: "body",
                    HEAD: "head",
                    HTML: "html",
                    LINK: "link",
                    META: "meta",
                    NOSCRIPT: "noscript",
                    SCRIPT: "script",
                    STYLE: "style",
                    TITLE: "title"
                }, m = (Object.keys(v).map(function (t) {
                    return v[t]
                }), "charset"), b = "cssText", y = "href", x = "http-equiv", _ = "innerHTML", w = "itemprop", k = "name",
                M = "property", F = "rel", S = "src", C = "target", A = {
                    accesskey: "accessKey",
                    charset: "charSet",
                    class: "className",
                    contenteditable: "contentEditable",
                    contextmenu: "contextMenu",
                    "http-equiv": "httpEquiv",
                    itemprop: "itemProp",
                    tabindex: "tabIndex"
                }, T = "defaultTitle", P = "defer", D = "encodeSpecialCharacters", O = "onChangeClientState",
                E = "titleTemplate", I = Object.keys(A).reduce(function (t, e) {
                    return t[A[e]] = e, t
                }, {}), R = [v.NOSCRIPT, v.SCRIPT, v.STYLE],
                L = "function" === typeof Symbol && "symbol" === typeof Symbol.iterator ? function (t) {
                    return typeof t
                } : function (t) {
                    return t && "function" === typeof Symbol && t.constructor === Symbol && t !== Symbol.prototype ? "symbol" : typeof t
                }, N = function (t, e) {
                    if (!(t instanceof e)) throw new TypeError("Cannot call a class as a function")
                }, j = function () {
                    function t(t, e) {
                        for (var n = 0; n < e.length; n++) {
                            var r = e[n];
                            r.enumerable = r.enumerable || !1, r.configurable = !0, "value" in r && (r.writable = !0), Object.defineProperty(t, r.key, r)
                        }
                    }

                    return function (e, n, r) {
                        return n && t(e.prototype, n), r && t(e, r), e
                    }
                }(), z = Object.assign || function (t) {
                    for (var e = 1; e < arguments.length; e++) {
                        var n = arguments[e];
                        for (var r in n) Object.prototype.hasOwnProperty.call(n, r) && (t[r] = n[r])
                    }
                    return t
                }, V = function (t, e) {
                    var n = {};
                    for (var r in t) e.indexOf(r) >= 0 || Object.prototype.hasOwnProperty.call(t, r) && (n[r] = t[r]);
                    return n
                }, B = function (t, e) {
                    if (!t) throw new ReferenceError("this hasn't been initialised - super() hasn't been called");
                    return !e || "object" !== typeof e && "function" !== typeof e ? t : e
                }, W = function (t) {
                    return !1 === (!(arguments.length > 1 && void 0 !== arguments[1]) || arguments[1]) ? String(t) : String(t).replace(/&/g, "&amp;").replace(/</g, "&lt;").replace(/>/g, "&gt;").replace(/"/g, "&quot;").replace(/'/g, "&#x27;")
                }, H = function (t) {
                    var e = X(t, v.TITLE), n = X(t, E);
                    if (n && e) return n.replace(/%s/g, function () {
                        return Array.isArray(e) ? e.join("") : e
                    });
                    var r = X(t, T);
                    return e || r || void 0
                }, U = function (t) {
                    return X(t, O) || function () {
                    }
                }, Y = function (t, e) {
                    return e.filter(function (e) {
                        return "undefined" !== typeof e[t]
                    }).map(function (e) {
                        return e[t]
                    }).reduce(function (t, e) {
                        return z({}, t, e)
                    }, {})
                }, q = function (t, e) {
                    return e.filter(function (t) {
                        return "undefined" !== typeof t[v.BASE]
                    }).map(function (t) {
                        return t[v.BASE]
                    }).reverse().reduce(function (e, n) {
                        if (!e.length) for (var r = Object.keys(n), i = 0; i < r.length; i++) {
                            var a = r[i].toLowerCase();
                            if (-1 !== t.indexOf(a) && n[a]) return e.concat(n)
                        }
                        return e
                    }, [])
                }, $ = function (t, e, n) {
                    var r = {};
                    return n.filter(function (e) {
                        return !!Array.isArray(e[t]) || ("undefined" !== typeof e[t] && Q("Helmet: " + t + ' should be of type "Array". Instead found type "' + L(e[t]) + '"'), !1)
                    }).map(function (e) {
                        return e[t]
                    }).reverse().reduce(function (t, n) {
                        var i = {};
                        n.filter(function (t) {
                            for (var n = void 0, a = Object.keys(t), o = 0; o < a.length; o++) {
                                var s = a[o], l = s.toLowerCase();
                                -1 === e.indexOf(l) || n === F && "canonical" === t[n].toLowerCase() || l === F && "stylesheet" === t[l].toLowerCase() || (n = l), -1 === e.indexOf(s) || s !== _ && s !== b && s !== w || (n = s)
                            }
                            if (!n || !t[n]) return !1;
                            var u = t[n].toLowerCase();
                            return r[n] || (r[n] = {}), i[n] || (i[n] = {}), !r[n][u] && (i[n][u] = !0, !0)
                        }).reverse().forEach(function (e) {
                            return t.push(e)
                        });
                        for (var a = Object.keys(i), o = 0; o < a.length; o++) {
                            var s = a[o], l = h()({}, r[s], i[s]);
                            r[s] = l
                        }
                        return t
                    }, []).reverse()
                }, X = function (t, e) {
                    for (var n = t.length - 1; n >= 0; n--) {
                        var r = t[n];
                        if (r.hasOwnProperty(e)) return r[e]
                    }
                    return null
                }, G = function () {
                    var t = Date.now();
                    return function (e) {
                        var n = Date.now();
                        n - t > 16 ? (t = n, e(n)) : setTimeout(function () {
                            G(e)
                        }, 0)
                    }
                }(), K = function (t) {
                    return clearTimeout(t)
                },
                Z = "undefined" !== typeof window ? window.requestAnimationFrame && window.requestAnimationFrame.bind(window) || window.webkitRequestAnimationFrame || window.mozRequestAnimationFrame || G : t.requestAnimationFrame || G,
                J = "undefined" !== typeof window ? window.cancelAnimationFrame || window.webkitCancelAnimationFrame || window.mozCancelAnimationFrame || K : t.cancelAnimationFrame || K,
                Q = function (t) {
                    return console && "function" === typeof console.warn && console.warn(t)
                }, tt = null, et = function (t, e) {
                    var n = t.baseTag, r = t.bodyAttributes, i = t.htmlAttributes, a = t.linkTags, o = t.metaTags,
                        s = t.noscriptTags, l = t.onChangeClientState, u = t.scriptTags, c = t.styleTags, d = t.title,
                        h = t.titleAttributes;
                    it(v.BODY, r), it(v.HTML, i), rt(d, h);
                    var f = {
                        baseTag: at(v.BASE, n),
                        linkTags: at(v.LINK, a),
                        metaTags: at(v.META, o),
                        noscriptTags: at(v.NOSCRIPT, s),
                        scriptTags: at(v.SCRIPT, u),
                        styleTags: at(v.STYLE, c)
                    }, p = {}, g = {};
                    Object.keys(f).forEach(function (t) {
                        var e = f[t], n = e.newTags, r = e.oldTags;
                        n.length && (p[t] = n), r.length && (g[t] = f[t].oldTags)
                    }), e && e(), l(t, p, g)
                }, nt = function (t) {
                    return Array.isArray(t) ? t.join("") : t
                }, rt = function (t, e) {
                    "undefined" !== typeof t && document.title !== t && (document.title = nt(t)), it(v.TITLE, e)
                }, it = function (t, e) {
                    var n = document.getElementsByTagName(t)[0];
                    if (n) {
                        for (var r = n.getAttribute("data-react-helmet"), i = r ? r.split(",") : [], a = [].concat(i), o = Object.keys(e), s = 0; s < o.length; s++) {
                            var l = o[s], u = e[l] || "";
                            n.getAttribute(l) !== u && n.setAttribute(l, u), -1 === i.indexOf(l) && i.push(l);
                            var c = a.indexOf(l);
                            -1 !== c && a.splice(c, 1)
                        }
                        for (var d = a.length - 1; d >= 0; d--) n.removeAttribute(a[d]);
                        i.length === a.length ? n.removeAttribute("data-react-helmet") : n.getAttribute("data-react-helmet") !== o.join(",") && n.setAttribute("data-react-helmet", o.join(","))
                    }
                }, at = function (t, e) {
                    var n = document.head || document.querySelector(v.HEAD),
                        r = n.querySelectorAll(t + "[data-react-helmet]"), i = Array.prototype.slice.call(r), a = [],
                        o = void 0;
                    return e && e.length && e.forEach(function (e) {
                        var n = document.createElement(t);
                        for (var r in e) if (e.hasOwnProperty(r)) if (r === _) n.innerHTML = e.innerHTML; else if (r === b) n.styleSheet ? n.styleSheet.cssText = e.cssText : n.appendChild(document.createTextNode(e.cssText)); else {
                            var s = "undefined" === typeof e[r] ? "" : e[r];
                            n.setAttribute(r, s)
                        }
                        n.setAttribute("data-react-helmet", "true"), i.some(function (t, e) {
                            return o = e, n.isEqualNode(t)
                        }) ? i.splice(o, 1) : a.push(n)
                    }), i.forEach(function (t) {
                        return t.parentNode.removeChild(t)
                    }), a.forEach(function (t) {
                        return n.appendChild(t)
                    }), {oldTags: i, newTags: a}
                }, ot = function (t) {
                    return Object.keys(t).reduce(function (e, n) {
                        var r = "undefined" !== typeof t[n] ? n + '="' + t[n] + '"' : "" + n;
                        return e ? e + " " + r : r
                    }, "")
                }, st = function (t) {
                    var e = arguments.length > 1 && void 0 !== arguments[1] ? arguments[1] : {};
                    return Object.keys(t).reduce(function (e, n) {
                        return e[A[n] || n] = t[n], e
                    }, e)
                }, lt = function (t, e, n) {
                    switch (t) {
                        case v.TITLE:
                            return {
                                toComponent: function () {
                                    return function (t, e, n) {
                                        var r, i = ((r = {key: e})["data-react-helmet"] = !0, r), a = st(n, i);
                                        return [c.a.createElement(v.TITLE, a, e)]
                                    }(0, e.title, e.titleAttributes)
                                }, toString: function () {
                                    return function (t, e, n, r) {
                                        var i = ot(n), a = nt(e);
                                        return i ? "<" + t + ' data-react-helmet="true" ' + i + ">" + W(a, r) + "</" + t + ">" : "<" + t + ' data-react-helmet="true">' + W(a, r) + "</" + t + ">"
                                    }(t, e.title, e.titleAttributes, n)
                                }
                            };
                        case f:
                        case p:
                            return {
                                toComponent: function () {
                                    return st(e)
                                }, toString: function () {
                                    return ot(e)
                                }
                            };
                        default:
                            return {
                                toComponent: function () {
                                    return function (t, e) {
                                        return e.map(function (e, n) {
                                            var r, i = ((r = {key: n})["data-react-helmet"] = !0, r);
                                            return Object.keys(e).forEach(function (t) {
                                                var n = A[t] || t;
                                                if (n === _ || n === b) {
                                                    var r = e.innerHTML || e.cssText;
                                                    i.dangerouslySetInnerHTML = {__html: r}
                                                } else i[n] = e[t]
                                            }), c.a.createElement(t, i)
                                        })
                                    }(t, e)
                                }, toString: function () {
                                    return function (t, e, n) {
                                        return e.reduce(function (e, r) {
                                            var i = Object.keys(r).filter(function (t) {
                                                return !(t === _ || t === b)
                                            }).reduce(function (t, e) {
                                                var i = "undefined" === typeof r[e] ? e : e + '="' + W(r[e], n) + '"';
                                                return t ? t + " " + i : i
                                            }, ""), a = r.innerHTML || r.cssText || "", o = -1 === R.indexOf(t);
                                            return e + "<" + t + ' data-react-helmet="true" ' + i + (o ? "/>" : ">" + a + "</" + t + ">")
                                        }, "")
                                    }(t, e, n)
                                }
                            }
                    }
                }, ut = function (t) {
                    var e = t.baseTag, n = t.bodyAttributes, r = t.encode, i = t.htmlAttributes, a = t.linkTags,
                        o = t.metaTags, s = t.noscriptTags, l = t.scriptTags, u = t.styleTags, c = t.title,
                        d = void 0 === c ? "" : c, h = t.titleAttributes;
                    return {
                        base: lt(v.BASE, e, r),
                        bodyAttributes: lt(f, n, r),
                        htmlAttributes: lt(p, i, r),
                        link: lt(v.LINK, a, r),
                        meta: lt(v.META, o, r),
                        noscript: lt(v.NOSCRIPT, s, r),
                        script: lt(v.SCRIPT, l, r),
                        style: lt(v.STYLE, u, r),
                        title: lt(v.TITLE, {title: d, titleAttributes: h}, r)
                    }
                }, ct = function (t) {
                    var e, n;
                    return n = e = function (e) {
                        function n() {
                            return N(this, n), B(this, e.apply(this, arguments))
                        }

                        return function (t, e) {
                            if ("function" !== typeof e && null !== e) throw new TypeError("Super expression must either be null or a function, not " + typeof e);
                            t.prototype = Object.create(e && e.prototype, {
                                constructor: {
                                    value: t,
                                    enumerable: !1,
                                    writable: !0,
                                    configurable: !0
                                }
                            }), e && (Object.setPrototypeOf ? Object.setPrototypeOf(t, e) : t.__proto__ = e)
                        }(n, e), n.prototype.shouldComponentUpdate = function (t) {
                            return !l()(this.props, t)
                        }, n.prototype.mapNestedChildrenToProps = function (t, e) {
                            if (!e) return null;
                            switch (t.type) {
                                case v.SCRIPT:
                                case v.NOSCRIPT:
                                    return {innerHTML: e};
                                case v.STYLE:
                                    return {cssText: e}
                            }
                            throw new Error("<" + t.type + " /> elements are self-closing and can not contain children. Refer to our API for more information.")
                        }, n.prototype.flattenArrayTypeChildren = function (t) {
                            var e, n = t.child, r = t.arrayTypeChildren, i = t.newChildProps, a = t.nestedChildren;
                            return z({}, r, ((e = {})[n.type] = [].concat(r[n.type] || [], [z({}, i, this.mapNestedChildrenToProps(n, a))]), e))
                        }, n.prototype.mapObjectTypeChildren = function (t) {
                            var e, n, r = t.child, i = t.newProps, a = t.newChildProps, o = t.nestedChildren;
                            switch (r.type) {
                                case v.TITLE:
                                    return z({}, i, ((e = {})[r.type] = o, e.titleAttributes = z({}, a), e));
                                case v.BODY:
                                    return z({}, i, {bodyAttributes: z({}, a)});
                                case v.HTML:
                                    return z({}, i, {htmlAttributes: z({}, a)})
                            }
                            return z({}, i, ((n = {})[r.type] = z({}, a), n))
                        }, n.prototype.mapArrayTypeChildrenToProps = function (t, e) {
                            var n = z({}, e);
                            return Object.keys(t).forEach(function (e) {
                                var r;
                                n = z({}, n, ((r = {})[e] = t[e], r))
                            }), n
                        }, n.prototype.warnOnInvalidChildren = function (t, e) {
                            return !0
                        }, n.prototype.mapChildrenToProps = function (t, e) {
                            var n = this, r = {};
                            return c.a.Children.forEach(t, function (t) {
                                if (t && t.props) {
                                    var i = t.props, a = i.children, o = function (t) {
                                        var e = arguments.length > 1 && void 0 !== arguments[1] ? arguments[1] : {};
                                        return Object.keys(t).reduce(function (e, n) {
                                            return e[I[n] || n] = t[n], e
                                        }, e)
                                    }(V(i, ["children"]));
                                    switch (n.warnOnInvalidChildren(t, a), t.type) {
                                        case v.LINK:
                                        case v.META:
                                        case v.NOSCRIPT:
                                        case v.SCRIPT:
                                        case v.STYLE:
                                            r = n.flattenArrayTypeChildren({
                                                child: t,
                                                arrayTypeChildren: r,
                                                newChildProps: o,
                                                nestedChildren: a
                                            });
                                            break;
                                        default:
                                            e = n.mapObjectTypeChildren({
                                                child: t,
                                                newProps: e,
                                                newChildProps: o,
                                                nestedChildren: a
                                            })
                                    }
                                }
                            }), e = this.mapArrayTypeChildrenToProps(r, e)
                        }, n.prototype.render = function () {
                            var e = this.props, n = e.children, r = V(e, ["children"]), i = z({}, r);
                            return n && (i = this.mapChildrenToProps(n, i)), c.a.createElement(t, i)
                        }, j(n, null, [{
                            key: "canUseDOM", set: function (e) {
                                t.canUseDOM = e
                            }
                        }]), n
                    }(c.a.Component), e.propTypes = {
                        base: i.a.object,
                        bodyAttributes: i.a.object,
                        children: i.a.oneOfType([i.a.arrayOf(i.a.node), i.a.node]),
                        defaultTitle: i.a.string,
                        defer: i.a.bool,
                        encodeSpecialCharacters: i.a.bool,
                        htmlAttributes: i.a.object,
                        link: i.a.arrayOf(i.a.object),
                        meta: i.a.arrayOf(i.a.object),
                        noscript: i.a.arrayOf(i.a.object),
                        onChangeClientState: i.a.func,
                        script: i.a.arrayOf(i.a.object),
                        style: i.a.arrayOf(i.a.object),
                        title: i.a.string,
                        titleAttributes: i.a.object,
                        titleTemplate: i.a.string
                    }, e.defaultProps = {defer: !0, encodeSpecialCharacters: !0}, e.peek = t.peek, e.rewind = function () {
                        var e = t.rewind();
                        return e || (e = ut({
                            baseTag: [],
                            bodyAttributes: {},
                            encodeSpecialCharacters: !0,
                            htmlAttributes: {},
                            linkTags: [],
                            metaTags: [],
                            noscriptTags: [],
                            scriptTags: [],
                            styleTags: [],
                            title: "",
                            titleAttributes: {}
                        })), e
                    }, n
                }(o()(function (t) {
                    return {
                        baseTag: q([y, C], t),
                        bodyAttributes: Y(f, t),
                        defer: X(t, P),
                        encode: X(t, D),
                        htmlAttributes: Y(p, t),
                        linkTags: $(v.LINK, [F, y], t),
                        metaTags: $(v.META, [k, m, x, M, w], t),
                        noscriptTags: $(v.NOSCRIPT, [_], t),
                        onChangeClientState: U(t),
                        scriptTags: $(v.SCRIPT, [S, _], t),
                        styleTags: $(v.STYLE, [b], t),
                        title: H(t),
                        titleAttributes: Y(g, t)
                    }
                }, function (t) {
                    tt && J(tt), t.defer ? tt = Z(function () {
                        et(t, function () {
                            tt = null
                        })
                    }) : (et(t), tt = null)
                }, ut)(function () {
                    return null
                }));
            ct.renderStatic = ct.rewind
        }).call(this, n(92))
    }, 992: function (t, e, n) {
        "use strict";
        var r, i = n(0), a = (r = i) && "object" === typeof r && "default" in r ? r.default : r;

        function o(t, e, n) {
            return e in t ? Object.defineProperty(t, e, {
                value: n,
                enumerable: !0,
                configurable: !0,
                writable: !0
            }) : t[e] = n, t
        }

        var s = !("undefined" === typeof window || !window.document || !window.document.createElement);
        t.exports = function (t, e, n) {
            if ("function" !== typeof t) throw new Error("Expected reducePropsToState to be a function.");
            if ("function" !== typeof e) throw new Error("Expected handleStateChangeOnClient to be a function.");
            if ("undefined" !== typeof n && "function" !== typeof n) throw new Error("Expected mapStateOnServer to either be undefined or a function.");
            return function (r) {
                if ("function" !== typeof r) throw new Error("Expected WrappedComponent to be a React component.");
                var l, u = [];

                function c() {
                    l = t(u.map(function (t) {
                        return t.props
                    })), d.canUseDOM ? e(l) : n && (l = n(l))
                }

                var d = function (t) {
                    var e, n;

                    function i() {
                        return t.apply(this, arguments) || this
                    }

                    n = t, (e = i).prototype = Object.create(n.prototype), e.prototype.constructor = e, e.__proto__ = n, i.peek = function () {
                        return l
                    }, i.rewind = function () {
                        if (i.canUseDOM) throw new Error("You may only call rewind() on the server. Call peek() to read the current state.");
                        var t = l;
                        return l = void 0, u = [], t
                    };
                    var o = i.prototype;
                    return o.UNSAFE_componentWillMount = function () {
                        u.push(this), c()
                    }, o.componentDidUpdate = function () {
                        c()
                    }, o.componentWillUnmount = function () {
                        var t = u.indexOf(this);
                        u.splice(t, 1), c()
                    }, o.render = function () {
                        return a.createElement(r, this.props)
                    }, i
                }(i.PureComponent);
                return o(d, "displayName", "SideEffect(" + function (t) {
                    return t.displayName || t.name || "Component"
                }(r) + ")"), o(d, "canUseDOM", s), d
            }
        }
    }
}]);
//# sourceMappingURL=5.7a8ceb89.chunk.js.map