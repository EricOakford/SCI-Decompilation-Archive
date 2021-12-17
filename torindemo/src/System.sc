;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	SYSTEM.SC
;;;;
;;;;	(c) Sierra On-Line, Inc, 1988
;;;;
;;;;	Author: 	Jeff Stephenson
;;;;	Updated: Brian K. Hughes
;;;;
;;;;	General purpose 'system' classes, presumably not adventure-game
;;;;	specific.  Defines the base class, Object, and all Collection
;;;;	classes.  Probably should be broken up into several modules.
;;;;
;;;;	Classes:
;;;;		Object
;;;;		Code
;;;;		Collection
;;;;		List
;;;;		Set
;;;;		EventHandler
;;;;		Cast
;;;;		Script
;;;;		Event
;;;;		Cue
;;;;
;;;;	Procedures:
;;;;		sign
;;;;		umod
;;;;		Min
;;;;		Max
;;;;		InRect
;;;;		OneOf
;;;;		Eval

(script# SYSTEM)
(include game.sh)
(use Main)

;;;(procedure 
;;;	sign
;;;	umod
;;;	Min
;;;	Max
;;;	InRect
;;;	OneOf
;;;	Eval
;;;)

(public
	sign			0
	umod			1
	Min			2
	Max			3
	InRect		4
	OneOf			5
	Eval			6
)


(procedure (sign x)
	;
	; Return +1 if arg is positive, -1 if negative, 0 if 0

	(return 
		(if (< x 0) -1 else (> x 0))
	)
)

(procedure (umod n modN)
	;
	; Unsigned mod function

	(-= n (* modN (/ n modN)))
	(if (< n 0) (+= n modN))
	(return n)
)

(procedure (Min nums &tmp otherMin)
	;
	; Find parameter that is the smallest
	(return
		(if (or 	(== argc 1)
					(< nums (= otherMin (Min &rest)))
			)
			nums
		else 
			otherMin
		)
	)
)

(procedure (Max nums &tmp otherMax)
	;
	; Find parameter that is the largest
	(return
		(if (or 	(== argc 1)
					(> nums (= otherMax (Max &rest)))
			)
			nums
		else
			otherMax
		)
	)
)

(procedure (InRect lEdge tEdge rEdge bEdge ptxOrObj pty)
	;
	; Determine if a point or object is in a rectangle

	(return 
		(and 
			(<= lEdge (if (< argc 6) (ptxOrObj x?) else ptxOrObj)	rEdge)	
			(<= tEdge (if (< argc 6) (ptxOrObj y?) else pty	)		bEdge)	
		)
	)
)

(procedure (OneOf what things &tmp i)
	;
	; Determine if what is one of things

	(for
		(	(= i 0)	)
		(< i (- argc 1))
		(	(++ i)	)
		
		(if (== what [things i]) (return (or what TRUE)))
	)
	(return FALSE)
)

(procedure (Eval obj sel)
	;
	; Way of delaying binding until runtime

	(return (obj sel: &rest))
)



; RootObj - The Basis of All Life


;(class RootObj
;	RootObj is defined in the kernel/compiler and contains only that
;	which is necessary for an object to be recognized as such, having
;	no behavior of its own.
;
;	(properties
;		-objID-
;			This is always set to $1234, which helps identify (though not
;			conclusively!) an object in memory.  Don't change it or
;			save/restore, debug, etc. will probably stop working.
;
;		-size-
;			This is the number of properties in the object.  Don't change
;			it or property lookup will no longer work.
;
;		-propDict-
;			This is a pointer to the property dictionary (contained
;			in the defining class) of an object.  Thus if two
;			objects have equal -propDict- properties, they are both
;			instances of the same class.  Do not change this property,
;			or the object will stop working.
;
;		-methDict-
;			This is a pointer to this object's method dictionary.  Don't
;			change it or method lookup will no longer work.
;
;		-script-
;			This is a pointer to the interpreter's internal script node
;			for the script in which the object was defined.  Change it
;			and die.
;
;		-super-
;			This is a pointer to the object's superclass, and is used to
;			look up a method which is not defined locally.  Don't change
;			it, or the object will cease functioning.
;
;		-info-
;			Bit-mapped information about the object.  Bits are:
;				CLONED		0 = object is static
;								1 = object was created with new:
;
;				NODISPOSE	0 = dispose: object if CLONED
;								1 = don't dispose: object
;
;				NODISPLAY	0 = display object in debugger's object display
;								1 = don't display object in debugger's object display
;
;				CLASS			0 = object is an instance of a class
;								1 = object is a class
;	)
;)

(class Object
	;;; Class Object is the superclass of all other Script objects.  It
	;;; defines the behavior which is expected of all objects, ensuring
	;;; that all objects will respond to a certain set of selectors.
	
	(properties
		name "Obj"			;print name of the object
		scratch	0			;Do whatever you want with it!

		; Adding properties requires changing the xxxx define
	)
;;;	(methods
;;;		new					;create a new instance of the class/object
;;;		init					;initialize the object
;;;		doit					;do your thing
;;;		dispose				;dispose of this instance
;;;		show					;display myself (in derived classes)
;;;		perform				;execute code in your environment
;;;		isClass				;is object a class
;;;		isKindOf				;is object/class a subclass of something?
;;;		isMemberOf			;is object an instance of something?
;;;		respondsTo			;does object respond to a message?
;;;		yourself				;answer your object ID
;;;	)
	
	(method (new)
		(return (Clone self))
	)
	
	(method (init)
	)
	
	(method (doit)
		(return self)
	)
	
	(method (dispose)
		(DisposeClone self)
	)
	
	(method (perform theCode)
		(return (theCode doit: self &rest))
	)
	
	(method (respondsTo selector)
		(return (RespondsTo self selector))
	)
	
	(method (isClass)
		(return (& -info- CLASS))
	)

	(method (isMemberOf what)
		;
		; Are we an instance of what?

		(return
			(or
				(== what self)
				(and
					(& (what -info-?) CLASS)
					(not (& -info- CLASS))
					(== -propDict- (what -propDict-?))
				)
			)
		)
	)
	
	(method (isKindOf what &tmp theSuper)
		;
		; Are we decended from what?

		(return
			(or
				(and
					(== -propDict- (what -propDict-?))
					(== -classScript- (what -classScript-?))
				)
				(and
					(= theSuper (self -super-?))
					theSuper
					(theSuper isKindOf: what)
				)
			)
		)
	)
	
	(method (yourself)
		(return self)
	)
)

(class Code kindof Object
	;;; Class Code is used to attach code to various objects (as in
	;;; the 'viewer' property of an Actor) or for use in the perform:
	;;; method of Object.  In either case, it is only the doit: method
	;;; of the class which is important.
	
	(method (doit)
	)
)