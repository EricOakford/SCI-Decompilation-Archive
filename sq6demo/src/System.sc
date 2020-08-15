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

;EO: All instances of "KList" were changed to "List" to allow the script to compile.
;They will be restored to their original name when SCICompanion properly supports SCI2.1.

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

	(if (or 	(== argc 1)
				(< nums (= otherMin (Min &rest)))
		)
		nums
	else 
		otherMin
	)
)

(procedure (Max nums &tmp otherMax)
	;
	; Find parameter that is the largest

	(if (or 	(== argc 1)
				(> nums (= otherMax (Max &rest)))
		)
		nums
	else
		otherMax
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



(class Collection kindof Object
	;;; The Collection class provides the ability to manipulate
	;;; collections of objects.  Objects which belong to a Collection
	;;; are said to be elements or members of it.  The Collection class
	;;; has no particular order defined for its elements, so it should
	;;; not be used for situations in which the objects should be
	;;; ordered -- use a List instead.
	
	(properties
		name 		"Collect"
		elements	0			;pointer to a Kernal List (KList) containing elements
		size 		0		 	;the number of elements in the list
		nextNode	0			;next node for use in eachElementDo, etc.
	)
;;;	(methods
;;;		doit
;;;		add					;add elements
;;;		delete				;delete elements
;;;		eachElementDo		;invoke a method in each element
;;;		firstTrue			;return first element satisfying a condition
;;;		allTrue				;return TRUE if all elements satisfy a condition
;;;		contains				;return TRUE if an object is in the Collection
;;;		isEmpty				;return TRUE if the list is empty (size == 0)
;;;		first					;return first KNode of the Collection
;;;		next					;return next KNode of the Collection
;;;		release				;delete elements from list to deallocate all nodes
;;;		isDuplicate			;return object ID if already in Collection, else FALSE
;;;		value					;return the value of a node
;;;	)

	(method (new)
		(return
			((super new:)
				elements: (List LNew),
				yourself:
			)
		)
	)

	(method (doit)
		(self eachElementDo: #doit: &rest)
	)

	(method (add item &tmp obj n node)
		;If the Collection does not have a KList, create one for it.
		(if (not elements)
			(= elements (List LNew))
			;EO: was "KList"
		)
		
		;Loop over the arguments, adding each to the Collection.
		(for	((= n 0))
				(< n argc)
				((++ n))
			(if (not (self isDuplicate: [item n]))
				(List LAddToEnd elements (List LNewNode [item n]) [item n])
				;EO: was "KList"
				(++ size)
			)
		)
		(return self)
	)

	(method (delete item &tmp n)
		;Loop over arguments, deleting each from the Collection.
		(for	((= n 0))
				(< n argc)
				((++ n))
			; If we're going to delete our nextNode, make sure we get
			;	another nextNode
			(if (and nextNode
						(== [item n] (List LNodeValue nextNode))
						;EO: was "KList"
				)
				(= nextNode (List LNextNode (List LFindKey elements [item n])))
					;EO: was "KList"
			)
			(if (List LDeleteKey elements [item n])
				;EO: was "KList"
				(-- size)
			)
		)
		(return self)
	)

	(method (dispose)
		(if elements
			(self eachElementDo: #dispose:)
			(List LDispose elements)
			;EO: was "KList"
		)
		(= elements NULL)
		(= size 0)
		(super dispose:)
	)

	(method (first)
		(return (List LFirstNode elements))
		;EO: was "KList"
	)

	(method (next node)
		(return (List LNextNode node))
		;EO: was "KList"
	)

	(method (isEmpty)
		(return (or (== elements NULL) (List LEmpty elements)))
		;EO: was "KList"
	)

	(method (contains anObject)
		(return (List LFindKey elements anObject))
		;EO: was "KList"
	)

	(method (eachElementDo action &tmp node obj)
		(List LEachElementDo elements action &rest)
		;EO: was "KList"
		
;		(for	((= node (KList LFirstNode elements)))
;				node
;				((= node nextNode))
;			(= nextNode (KList LNextNode node))
;			(= obj (KList LNodeValue node))
;			(obj action &rest)
;		)
	)

	(method (firstTrue action &tmp node obj)
		(List LFirstTrue elements action &rest)
		;EO: was "KList"
		
;		(for	((= node (KList LFirstNode elements)))
;				node
;				((= node nextNode))
;			(= nextNode (KList LNextNode node))
;			(= obj (KList LNodeValue node))
;			(if (obj action &rest)
;				(return obj)
;			)
;		)
;		(return NULL)
	)

	(method (allTrue action &tmp node obj)
		(List LAllTrue elements action &rest)
		;EO: was "KList"
		
;		(for	((= node (KList LFirstNode elements)))	;(for	((= node (self first:)))
;				node
;				((= node nextNode))
;			(= nextNode (KList LNextNode node))			;(= nextNode (self next:node))
;			(= obj (KList LNodeValue node))
;			(if (not (obj action &rest))
;				(return FALSE)
;			)
;		)
;		(return TRUE)
	)

	(method (release &tmp node)
		;
		; Delete all elements from collection to deallocate list nodes

		(for	((= node (List LFirstNode elements)))	;EO: was "KList"
				node
				((= node nextNode))
			(= nextNode (List LNextNode node))		;EO: was "KList"
			(self delete: (List LNodeValue node))	;EO: was "KList"
		)
	)

	(method (isDuplicate obj)
		;
		; Return FALSE (i.e. obj not already in list), since for class Collection
		;	and List we don't care about duplicates.  For class Set, this method
		;	will be over-ridden to determine if obj is already in the Set.

		(return FALSE)
	)

	(method (value n)
		(return (List LNodeValue n) )		;EO: was "KList"
	)
)

(class List kindof Collection
	;;; A List is a Collection which has a specified order to its
	;;; elements.  You will probably use the Set class (below) more
	;;; than List, as Set automatically weeds out duplicates.
	
;;;	(methods
;;;		at					;return the object at a given position in the List
;;;		last				;return the last KNode of the List
;;;		prev				;return previous KNode
;;;		addToFront		;add object to front of List
;;;		addToEnd			;add object to end of List
;;;		addAfter			;add object after an element of List
;;;		indexOf			;return index of an object in a List
;;;		sort				;sort list by property or id
;;;	)

	(method (at n &tmp node)
		(return (List LAt elements n))	;EO: was "KList"
;		(for	((= node (KList LFirstNode elements)))		;(for	((= node (self first:)))
;				(and n node)
;				((-- n) (= node (KList LNextNode node)))	;((-- n) (= node (self next: node)))
;		)
;		(return
;			(if node
;				(KList LNodeValue node)
;			else
;				0
;			)
;		)
	)

	(method (last)
		(return (List LLastNode elements))	;EO: was "KList"
	)

	(method (prev node)
		(return (List LPrevNode node))		;EO: was "KList"
	)
	
	(method (addToFront obj &tmp n)
		(if (not elements)
			(= elements (List LNew))		;EO: was "KList"
		)
		
		(for	((= n (- argc 1)))
				(<= 0 n)
				((-- n))
			(if (not (self isDuplicate: [obj n]))
				(List LAddToFront elements (List LNewNode [obj n]) [obj n])		;EO: was "KList"
				(++ size)
			)
		)
		(return self)
	)

	(method (addToEnd obj &tmp n)
		(if (not elements)
			(= elements (List LNew))		;EO: was "KList"
		)
		
		(for	((= n 0))
				(< n argc)
				((++ n))
			(if (not (self isDuplicate: [obj n]))
				(List LAddToEnd elements (List LNewNode [obj n]) [obj n])		;EO: was "KList"
				(++ size)
			)
		)
		(return self)
	)

	(method (addAfter el obj &tmp n num insertNode)
		(if (= insertNode (List LFindKey elements el))		;EO: was "KList"
			(-- argc)
			(for	((= n 0))
					(< n argc)
					((++ n))
				(if (not (self isDuplicate: [obj n]))
					(= insertNode
						(List LAddAfter elements insertNode (List LNewNode [obj n]) [obj n])		;EO: was "KList"
					)
					(++ size)
				)
			)
		)
		(return self)
	)

	(method (indexOf obj &tmp n node)
		(return (List LIndexOf elements obj))		;EO: was "KList"
;		(for	((= n 0) (= node (KList LFirstNode elements)))
;				node
;				((++ n) (= node (KList LNextNode node)))
;			(if (== obj (KList LNodeValue node))
;				(return n)
;			)
;		)
;		(return -1)
	)

	(method (sort theProp doDescending &tmp v des)
		(= v (if argc theProp else -1))
		(= des (and (> argc 1) doDescending))
		(List LSort elements v des)		;EO: was "KList"
	)
)

(class Set kindof List
	;;; A Set is a kind of List which does not contain duplicate
	;;; elements: adding an object to a Set which already contains the
	;;; object does not change the Set.  This eliminates the
	;;; possibility of a whole class of errors based on multiple
	;;; occurances of an object in a collection.  Most system
	;;; collections (the cast, etc.) are Sets.
	
	(method (isDuplicate obj)
		(return (self contains: obj))
	)
)



(class EventHandler kindof Set
	;;; An EventHandler is a Set that passes events to its elements.

;;;	(methods
;;;		handleEvent
;;;	)

	(method (handleEvent event &tmp node evtClone ret obj)
		(= evtClone (Clone event))
		(for	((= node (List LFirstNode elements)))		;EO: was "KList"
				(and node (not (evtClone claimed?)))
				((= node nextNode))
			(= nextNode (List LNextNode node))		;EO: was "KList"
			(= obj (List LNodeValue node))		;EO: was "KList"
			(obj handleEvent: evtClone)
		)
		(= ret (evtClone claimed?))
		(evtClone dispose:)
		(return ret)
	)
)

(class Cast kindof EventHandler
	(properties
		plane		0		; plane we belong to
	)
	(method (dispose)
		(if plane
			((plane casts?) delete: self)
			(= plane 0)
		)
		(super dispose:)
	)
)


(class Script kindof Object
	;; A Script is a kind of Object which has a state, methods to
	;; change that state, and code to execute when the state changes.
	;; It is used to model a sequence of actions which should be
	;; executed by an object, such as an Actor walking to the base of
	;; some stairs, walking up the stairs, and opening a door.
	
	(properties
		client 		0 		;the Object whose actions are controlled by this Script
		state 		-1		;the current state of the script
		start 		0 		;the starting state of the script
		timer 		0 		;the ID of a timer whose client is this Script
		cycles 		0 		;the number of cycles to wait before changing state
		seconds 		0		;the number of seconds to wait before changing state
		lastSeconds 0		;<private>
		ticks			0		;the number of ticks to wait before changing state
		lastTicks 	0		;<private>
		register		0		;open architecture property, can be anything the
								;programmer wants it to be, including a list
		script		0		;sub-scripts provide subroutine-style functionality
		caller		0		;who should we cue when we're disposed
		next			0		;who should we CHAIN to when we're disposed
								;can be pointer to an instance of Script or
								;number of file that has a Script as public zero
	)
;;;	(methods
;;;		changeState		;change to new state
;;;		cue				;change to next state
;;;		handleEvent		;handle events passed to this Script
;;;		setScript		;set new sub-script
;;;	)

	(method (doit &tmp thisSeconds)
		(if script
			(script doit:)
		)
		(cond
			(cycles
				(if (not (-- cycles))
					(self cue:)
				)
			)
			(seconds
				(= thisSeconds (GetTime SysTime1))
				(if (!= lastSeconds thisSeconds)
					(= lastSeconds thisSeconds)
					(if (not (-- seconds))
						(self cue:)
					)
				)
			)
			(ticks
				(if (<= (-= ticks	(Abs (- gameTime	lastTicks))) 0)
					(= ticks 0)
					(= lastTicks gameTime)
					(self cue:)
				else
					(= lastTicks gameTime)
				)
			)
		)
		
		; Note!  No code can be added below the above cond because a clone
		;	may be disposed of before this point.
	)

	(method (init who whoCares reg)
		(= lastTicks gameTime)
		(if (>= argc 1)			((= client who) script: self)	;double link!
			(if (>= argc 2) 		(= caller whoCares)
				(if (>= argc 3) 	(= register reg)
				)
			)
		)
		(= state (- start 1))
		(self cue:)
	)

	(method (dispose &tmp theNextScript)
		(if script
			(script dispose:)
		)
		(if timer
			(timer dispose:)
		)
		(if client
			(client script: (= theNextScript next))

			(cond
				((not theNextScript)					;no next, nothing to do
					FALSE
				)
				((== newRoomNum curRoomNum)		;have next, no room change
					(theNextScript init: client)
				)
				(else										;room change
					(theNextScript dispose:)		;clean up clones!
				)
			)
		)

		;;NOTE: client's script property MUST be changed BEFORE cue'ing caller
		;;      We DON'T cue caller on room changes.
		;;
		(if (and	caller (== newRoomNum curRoomNum))
			(caller cue: register)	;return register value
		)
		
		;;the following cleanup statement allow a "disposed" static script 
		;;to be reused, even though you shouldn't do it! More proper is to 
		;;Clone a script if it will be used many times.
		;;
		(= script (= timer (= client (= next (= caller 0)))))
		(= seconds (= cycles (= ticks 0)))
		
		(super dispose:)
	)

	(method (changeState newState)
		(= state newState)
	)

	(method (cue)
		(if client			;; Don't changeState if we're an orphan script
			(= lastTicks gameTime)
			(self changeState: (+ state 1) &rest)
		)
	)

	(method (setScript newScript)
		(if script
			(script dispose:)
		)
		
		(if newScript
			(newScript init: self &rest)	;init sets our script property!
		)
	)

	(method (handleEvent event)
		(if script
			(script handleEvent: event)
		)
		(return (event claimed?))
	)
)



(class Event kindof Object
	;;; The Event class is the class of user input events (key presses,
	;;; mouse clicks, etc.)
	
	(properties
		type 			0 		;the type of event (Event types in system.sh)
		message 		0		;the value of the event (e.g. ASCII value of key)
		modifiers 	0		;modifiers of event (shiftDown, ctrlDown, altDown)
		x 				0		;x coord of mouse when event occurred
		y 				0		;y coord of mouse when event occurred
		z				0		;z coord of mouse		\
		yaw			0		;yaw value of mouse   \
		pitch			0		;pitch value of mouse  > 6-way mice only
		roll			0		;roll value of mouse  /
		claimed 		0		;TRUE if the event has been responded to
		plane			0		;current plane to which we are local (0 = global)
	)
;;;	(methods
;;;		new				; get cloned new event and call kernel to fill it in
;;;		localize			; make X/Y point into local coords
;;;		globalize		; make X/Y point into global coords
;;;	)
	
	(method (new mask &tmp event)
		(= event (super new:))
		(GetEvent
			(if argc	mask else allEvents)
			event
		)
		(return event)
	)

	(method (localize toPlane)
		;
		; Make the mouse coordinates relative to the plane specified

		(if plane
			(LocalToGlobal self plane)
		)
		(GlobalToLocal self (= plane toPlane))
		(return self)
	)

  	(method (globalize)
		;
		; Make the mouse coordinates global to the screen

		(if plane
			(LocalToGlobal self plane)
			(= plane 0)
		)
		(return self)
	)
)


(class Cue kindof Object
	;;; This class provides a way of delaying a cue until the beginning of the
	;;;	next cycle.  Dynamic instances of Cue can be put on the cuees list

	(properties
		cuee		0		; who to cue
		cuer		0		; who's doin' the cuein'
		register	0		; value to pass to cue
	)
	(method (doit)
		(cuees delete: self)
		(cuee cue: register cuer)
		(self dispose:)
	)
)