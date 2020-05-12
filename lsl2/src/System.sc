;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	SYSTEM.SC
;;;;	(c) Sierra On-Line, Inc, 1988
;;;;
;;;;	Author: Jeff Stephenson
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
;;;;		Script
;;;;		Event
;;;;		Timer
;;;;		TimeOut



(script# SYSTEM)
(include game.sh)
(use Main)
(use Intrface)

(define	ticksPerSec		60)
(define	ticksPerMin		3600)
(define	minPerHr			60)

;(class RootObj
;	;;; RootObj is defined in the kernel and contains only that which
;	;;; is necessary for an object to be recognized as such, having no
;	;;; behavior of its own.
;
;	(properties
;		species
;			;This is a pointer to the property dictionary (contained
;			;in the defining class) of an object.  Thus if two
;			;objects have equal species properties, they are both
;			;instances of the same class.  Do not change this property,
;			;or the object will stop working.
;
;		superClass
;			;This is a pointer to the method dictionary of the object's
;			;superclass, and is used to look up a method which is not
;			;defined locally.  Don't change it, or the object will cease
;			;functioning.
;
;		-info-
;			;Bit-mapped information about the object.  Bits are:
;			;	CLONED		0 = object is static
;			;					1 = object was created with new:
;			;	NODISPOSE	0 = dispose: object if CLONED
;			;					1 = don't dispose: object
;			;	NODISPLAY	0 = display object in debugger's object display
;			;					1 = don't display object in debugger's object
;			;						 display
;			;	CLASS			0 = object is an instance of a class
;			;					1 = object is a class
;	)
;)




(class Object ;kindof RootObj
	;;; Class Object is the superclass of all other Script objects.  It
	;;; defines the behavior which is expected of all objects, ensuring
	;;; that all objects will respond to a certain set of selectors.

	(properties
		name "Obj"			;print name of the object
	)

;;;	(methods
;;;		new					;create a new instance of the class/object
;;;		init					;initialize the object
;;;		doit					;do your thing
;;;		dispose				;dispose of this instance
;;;		showStr				;produce a string which shows me
;;;		showSelf				;show who I am
;;;		perform				;execute code in your environment
;;;		isKindOf				;is object/class a subclass of something?
;;;		isMemberOf			;is object an instance of something?
;;;		respondsTo			;does object responds to a message?
;;;		yourself				;answer your object ID
;;;	)


	(method (new)
		;; Returns the ID of a new instance of the receiver.  This new
		;; instance has the same methods and property values as the
		;; receiver.  The property values can be changed, the methods
		;; cannot.

		(return (Clone self))
	)


	(method (init)
		;; Initializes the receiver.  If the object is to show up on the
		;; screen (Views, Props, Actors and Egos), this is the message
		;; which will make the it show itself.  The default does nothing.
	)

	(method (doit)
		;; Do your thing, whatever that may be.  Sometimes this is sent
		;; when the object is selected, sometimes (as for Actors) it is
		;; sent for each animation cycle.  The default does nothing but
		;; return the object ID.

		(return self)
	)


	(method (dispose)
		;; Dispose of the receiver.  If the receiver was created using
		;; new:, this reclaims the memory occupied by that object.  In
		;; this case it is an error to refer to the object ID being
		;; disposed of once the dispose: message has been sent.
		;; Subclasses of Object should make sure that they dispose of
		;; any objects which they created before doing a (super dispose:).

		(DisposeClone self)
	)


	(method (showStr where)
		;; Format a string describing the receiver at the storage
		;; pointed to by where.  The default string is the object name.

		(return (StrCpy where name))
	)


	(method (showSelf &tmp [str 200])
		;; Displays the receiver in a meaningful way.  This is primarily
		;; used for debugging, in order to find out which objects are
		;; present.  The default is to print the string produced by
		;; showStr:.

		(Print (self showStr:@str))
	)


	(method (perform theCode)
		;; Executes the doit: method of theCode with the first parameter
		;; of the method being the object ID of the receiver of perform:.
		;; Any number of parameters may be passed.  Returns the result.

		(return (theCode doit: self &rest))
	)


	(method (respondsTo selector)
		;; Returns TRUE if the receiver has a method corresponding to
		;; selector, FALSE otherwise.

		(return (RespondsTo self selector))
	)


	(method (isMemberOf what)
		;; Returns TRUE if the receiver is an instance of what, FALSE
		;; otherwise.

		(return
			(and
				(& (what -info-?) CLASS)
				(not (& -info- CLASS))
				(== species (what species?))
			)
		)
	)
	

	(method (isKindOf what &tmp theSuper)
		;; Returns TRUE if the receiver is a subclass (or an instance of
		;; a subclass) of class, FALSE otherwise.

		(= theSuper (self superClass?))
		(return
			(or
				(== species (what species?))
				(and
					(IsObject theSuper)
					(theSuper isKindOf: what)
				)
			)
		)
	)


	(method (yourself)
		;; Returns the object ID of the receiver.  This is useful as the
		;; last in a series of messages to an object to force the entire
		;; send to return the ID of the object.

		(return self)
	)
)



(class Code kindof Object
	;;; Class Code is used to attach code to various objects (as in
	;;; the 'viewer' property of an Actor) or for use in the perform:
	;;; method of Object.  In either case, it is only the doit: method
	;;; of the class which is important.

	(method (doit)
		;; This is the code which you want executed.  Calling conventions
		;; will be dictated by the use of this class.
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
		name "Collect"
		elements 0			;pointer to a Kernal List (KList) containing
								;the elements
		size 0				;the number of elements in the list
	)

;;;	(methods
;;;		add					;add elements
;;;		delete				;delete elements
;;;		eachElementDo		;invoke a method in each element
;;;		firstTrue			;return first element satisfying a condition
;;;		allTrue				;return TRUE if all elements satisfy a
;;;								;condition
;;;		contains				;return TRUE if an object is in the Collection
;;;		isEmpty				;return TRUE if the list is empty (size == 0)
;;;		first					;return first KNode of the Collection
;;;		next					;return next KNode of the Collection
;;;	)



	(method (showStr where)
		(return (Format where SYSTEM 0 name size))
	)


	(method (showSelf &tmp [str 40])
		(Print (self showStr:@str))
		(self eachElementDo: #showSelf:)
	)


	(method (add item &tmp obj n node)
		;; Add item[s] to the Collection.

		;If the Collection does not have a KList, create one for it.
		(if (not elements)
			(= elements (NewList))
		)
		
		;Loop over the arguments, adding each to the Collection.
		(for	((= n 0))
				(< n argc)
				((++ n))
			(AddToEnd elements (NewNode [item n] [item n]))
			(++ size)
		)

		;Return the ID of the Collection.
		(return self)
	)


	(method (delete item &tmp n)
		;; Delete item[s] from the Collection.

		;Loop over arguments, deleting each from the Collection.
		(for	((= n 0))
				(< n argc)
				((++ n))

			(if (DeleteKey elements [item n])
				(-- size)
			)
		)

		;Return the ID of the Collection.
		(return self)
	)


	(method (dispose)
		;; Modifies the dispose: of Object to dispose each element of
		;; the list and the KList itself as well as the Collection
		;; proper.

		(if elements
			(self eachElementDo: #dispose:)
			(DisposeList elements)
		)
		(= elements NULL)
		(= size 0)
		(super dispose:)
	)


	(method (first)
		;; Return the first KNode in the KList which represents the
		;; collection. Use the (NodeValue) kernel call to get the actual
		;; object corresponding to this KNode.

		(return (FirstNode elements))
	)


	(method (next node)
		;; Return the KNode following 'node' in the KList which
		;; represents the collection.  Use the (NodeValue) kernel call
		;; to get the actual object corresponding to this KNode.

		(return (NextNode node))
	)


	(method (isEmpty)
		;; Returns TRUE if the receiver has no elements, FALSE otherwise.

		(return (or (== elements NULL) (EmptyList elements)))
	)


	(method (contains anObject)
		;; Returns TRUE if anObject is an element of the receiver,
		;; FALSE otherwise.

		(return (FindKey elements anObject))
	)


	(method (eachElementDo action &tmp node nextNode obj)
		;; Invoke the 'action' method of each element of the Collection
		;; with an arbitrary number of parameters.

		(for	((= node (FirstNode elements)))	;(for	((= node (self first:)))
				node
				((= node nextNode))

			(= nextNode (NextNode node))			;(= nextNode (self next:node))
			(if (not (IsObject (= obj (NodeValue node)))) (return))
			(obj action: &rest)
		)
	)


	(method (firstTrue action &tmp node nextNode obj)
		;; Invoke the 'action' method of each element of the Collection
		;; with an arbitrary number of parameters.  When an element
		;; replies a non-zero value, return the object ID of that
		;; element and do not send the message to any more objects.

		(for	((= node (FirstNode elements)))	;(for	((= node (self first:)))
				node
				((= node nextNode))

			(= nextNode (NextNode node))			;(= nextNode (self next:node))
			(= obj (NodeValue node))
			(if (obj action: &rest)
				(return obj)
			)
		)
		(return NULL)
	)


	(method (allTrue action &tmp node nextNode obj)
		;; Returns TRUE if all objects in the receiver reply a non-zero
		;; value to the method 'action' (with an arbitrary number of
		;; parameters), FALSE otherwise.

		(for	((= node (FirstNode elements)))	;(for	((= node (self first:)))
				node
				((= node nextNode))

			(= nextNode (NextNode node))			;(= nextNode (self next:node))
			(= obj (NodeValue node))
			(if (not (obj action: &rest))
				(return FALSE)
			)
		)
		(return TRUE)
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
;;;	)


	(method (showStr where)
		(return (Format where SYSTEM 1 name size))
	)


	(method (at n &tmp node)
		;; Returns the object (not the KNode) at position n in the List.

		(for	((= node (FirstNode elements)))		;(for	((= node (self first:)))
				(and n node)
				((-- n) (= node (NextNode node)))	;((-- n) (= node (self next: node)))
		)
		(return (NodeValue node))
	)


	(method (last)
		;; Returns the KNode of the last object in the list.

		(return (LastNode elements))
	)


	(method (prev node)
		;; Returns the KNode of the node which comes before 'node' in
		;; the List, or NULL if 'node' is the first node in the List.

		(return (PrevNode node))
	)


	(method (addToFront obj &tmp n)
		;; Add 'obj' (and following parameters) to the front of the List
		;; in the order presented in the message.

		(if (not elements)
			(= elements (NewList))
		)

		(for	((= n (- argc 1)))
				(<= 0 n)
				((-- n))
			(AddToFront elements (NewNode [obj n] [obj n]))
			(++ size)
		)
		(return self)
	)


	(method (addToEnd obj &tmp n)
		;; Add 'obj' (and following parameters) to the end of the List
		;; in the order presented in the message.

		(if (not elements)
			(= elements (NewList))
		)

		(for	((= n 0))
				(< n argc)
				((++ n))
			(AddToEnd elements (NewNode [obj n] [obj n]))
			(++ size)
		)
		(return self)
	)
	
	
	(method (addAfter el obj &tmp n num insertNode)
		;; Add 'obj' (and following parameters, in the order presented
		;; in the message) to the List following the object 'el'.

		(if (= insertNode (FindKey elements el))
			(-- argc)
			(for	((= n 0))
					(< n argc)
					((++ n))
				(= insertNode
					(AddAfter elements insertNode (NewNode [obj n] [obj n]))
				)
				(++ size)
			)
		)
		(return self)
	)
	

	(method (indexOf obj &tmp n node)
		;; Return the index of 'obj' in the List.  If 'obj' is not in
		;; the List, return -1.

		(for	((= n 0) (= node (FirstNode elements)))	;(= node (self first:))
				node
				((++ n) (= node (NextNode node)))			;(= node (self next:node))

			(if (== obj (NodeValue node))
				(return n)
			)
		)
		(return -1)
	)
)





(class Set kindof List
	;;; A Set is a kind of List which does not contain duplicate
	;;; elements: adding an object to a Set which already contains the
	;;; object does not change the Set.  This eliminates the
	;;; possibility of a whole class of errors based on multiple
	;;; occurances of an object in a collection.  Most system
	;;; collections (the cast, etc.) are Sets.

	(method (showStr where)
		(return (Format where SYSTEM 2 name size))
	)


	(method (add item &tmp obj n anItem)
		;; Adds each item to the receiver only if it is not already
		;; a member (i.e. the receiver does not contain: the element).

		(if (not elements)
			(= elements (NewList))
		)
		
		(for	((= n 0))
				(< n argc)
				((++ n))

			(= anItem [item n])
			(if (not (self contains: anItem))
				(AddToEnd elements (NewNode anItem anItem))
				(++ size)
			)
		)
	)
)




(class Script kindof Object
	;;; A Script is a kind of Object which has a state, methods to
	;;; change that state, and code to execute when the state changes.
	;;; It is used to model a sequence of actions which should be
	;;; executed by an object, such as an Actor walking to the base of
	;;; some stairs, walking up the stairs, and opening a door.

	(properties
		client 0			;the Object whose actions are controlled by this
							;Script
		state -1			;the current state of the script
		start 0			;the starting state of the script
		timer 0			;the ID of a timer whose client is this Script
		cycles 0			;the number of cycles to wait before changing
							;state
		seconds 0		;the number of seconds to wait before changing
							;state
		lastSeconds 0	;private variable
	)

;;;	(methods
;;;		changeState		;change to new state
;;;		cue				;change to next state
;;;		handleEvent		;handle events passed to this Script
;;;	)


	(method (doit &tmp thisSeconds)
		;; Check to see whether it is time to change to a new state.

		(if cycles
			(if (not (-- cycles))
;				(client cue:)
				(self cue:)
			)
		else
			(if seconds
				(= thisSeconds (GetTime 1))
				(if (!= lastSeconds thisSeconds)
					(= lastSeconds thisSeconds)
					(if (not (-- seconds))
;						(client cue:)
						(self cue:)
					)
				)
			)
		)
	)


	(method (init who)
		;; Remember who our client is and enter the starting state.

		(= client who)
		(self changeState:start)
	)


	(method (dispose)
		;; Toss any timer which we're using and detach ourselves from
		;; our client.

		(if (IsObject timer)
			(timer dispose:)
		)
		(if (IsObject client)
			(client script:0)
		)
		(super dispose:)
	)


	(method (changeState newState)
		;; The default method for changeState: does nothing more than
		;; set the new state.  Instances of script will have a switch
		;; which executes the code appropriate to the target state.

		(= state newState)
	)


	(method (cue)
		;; Advance to the next state.  This should be the way that state
		;; changes occur in general, rather than an explicit changeState:.

		(self changeState: (+ state 1))
	)


	(method (handleEvent event)
		;; If a Script is attached to a Region or Actor, it will receive
		;; the handleEvent: message wheneever an event occurs.  Default
		;; does nothing.
	)
)




(class Event kindof Object
	;;; The Event class is the class of user input events (key presses,
	;;; mouse clicks, etc.)

	(properties
		type 0			;the type of event (Event types in kernel.sh)
		message 0		;the value of the event (e.g. ASCII value of
							;keypress)
		modifiers 0		;modifiers of event (shiftDown, ctrlDown, altDown)
		y 0				;y coord of mouse when event occurred
		x 0				;x coord of mouse when event occurred
		claimed 0		;TRUE if the event has been responded to
	)


	(method (new mask &tmp event)
		;; If an input event of type 'mask' (see kernel.sh for Event
		;; types) is in the event queue, return it.  Otherwise, return
		;; NULL.

		(= event (super new:))
		(GetEvent
			(if argc	mask else allEvents)
			event
		)
		(return event)
	)
)




(class Timer kindof Object
	;;; The Timer class implements the concept of an alarm clock,
	;;; cue:ing another object after a certain interval.  Timers
	;;; dispose: of themselves after cue:ing their client, and always
	;;; check to see if their client is still present before cue:ing it.

	;;; An important concept relating to Timers is that of game time
	;;; versus real time.  Real time is just what it sounds like --
	;;; real time in real seconds.  Game time is time adjusted to the
	;;; performance of the user's computer -- it is the same as real
	;;; time on a computer which is able to keep up with the animation
	;;; demands of the game, but slows down in proportion to the speed
	;;; of the user's computer when it is not able to keep up.

	;;; An example may help clarify this.  Say that you're writing a
	;;; game in which ego has only five seconds to leave a room before
	;;; a bomb blows up.  If you set this time interval as real time,
	;;; it may give you just enough time to get out on your nice fast
	;;; 286 or 386 machine.  But on a Tandy 1000, where it takes 1/5th
	;;; of a second to complete an animation cycle instead of the
	;;; standard 1/10th of a second, the user will only be able to go
	;;; half as far and thus has no chance of leaving the room before
	;;; the bomb blows.  The time interval should really have been set
	;;; in game time, which would have given the user the same number
	;;; of animation cycles to get out.

	;;; As a rule of thumb, time intervals which are meant to be a
	;;; constraint on how long the user has to do something should be
	;;; set in game time, whereas time intervals which are just meant
	;;; to be a delay between two events should be real time.  A user
	;;; with a slow machine has no desire to watch a banner screen
	;;; twice as long as one with a fast machine.

	(properties
		cycleCnt -1			;number of animation cycles left before done
		seconds -1			;number of seconds left before done
		lastTime -1			;private
		client 0				;who to cue: when done
	)

;;;	(methods
;;;		set					;set number of game-time seconds
;;;		setCycle				;set number of animation cycles
;;;		setReal				;set number of real-time seconds
;;;		delete				;do actual disposal of the Time
;;;	)


	(procedure (CueClient &tmp who)
		;; A helper procedure.  Timer has expired, so detach from client
		;; and cue: it.

		;Detach ourselves from our client, but remember who it was.
		(= who client)
		(= client 0)

		(if (IsObject who)
			(if (who respondsTo:#timer:)
				(who timer:0)
			)
			(if (who respondsTo:#cue:)
				(who cue:)
			)
		)
	)


	(method (new)
		;; Create a new timer.  If the class itself is being asked to
		;; perform new:, return a new instance.  Otherwise, return the
		;; instance that is being asked.

		(return
			(if (== self Timer)
				(super new:)
			else
				self
			)
		)
	)
		

	(method (init caller)
		;; Set this timer's client to caller, add it to the master game
		;; timer list, and attach to the client.

		;If the caller has a timer already attached, dispose that timer.
		(if (IsObject (caller timer?))
			((caller timer?) dispose:)
		)

		;Set our client.
		(= client caller)

		;Add ourselves to the timer list.
		(timers add: self)

		;Tell the caller who we are.
		(caller timer:self)
	)


	(method (doit &tmp theTime)
		;; See if this timer has timed out.  If so, cue the client and
		;; dispose: the timer.

		(if (!= cycleCnt -1)
			;This is a cycle counter.
			(if (not (-- cycleCnt)) (CueClient))
		else
			;This is a real-time counter.
			(if (!= lastTime (= theTime (GetTime TRUE)))
				(= lastTime theTime)
				(if (not (-- seconds)) (CueClient))
			)
		)
	)


	(method (dispose)
		;; Simply unhook the timer from its client -- the actual
		;; disposal will be done by the main game loop invoking the
		;; delete: method of all timers without clients.

		(if (and (IsObject client) (client respondsTo:#timer:))
			(client timer:0)
		)
		(= client 0)
	)


	(method (delete)
		;; Do the actual deletion of the Timer.

		(if (== client 0)
			(timers delete: self)
			(super dispose:)
		)
	)


	(method (setCycle caller cycles &tmp aTimer)
		;; Set the timer to go off after a certain number of animation
		;; cycles.

		(= aTimer (if (& -info- CLASS) (self new:) else self))
		(aTimer init:caller, cycleCnt:cycles)
		(return aTimer)
	)


	(method (set caller sec min hr &tmp aTimer ticks theSpeed [str 50])
		;; Set the timer to go off after a certain number of seconds of
		;; game time (real time on fast machines, slower on slow
		;; machines).

;;;		(define	ticksPerSec		60)
;;;		(define	ticksPerMin		3600)
;;;		(define	minPerHr			60)

		(= theSpeed speed)
		(if (== theSpeed 0) (= theSpeed 1))
		(= ticks (/ (* sec ticksPerSec) theSpeed))
		(if (> argc 2)
			(+= ticks (/ (* min ticksPerMin) theSpeed))
		)
		(if (> argc 3)
			;This is a bit funny in order to avoid overflow.
			(+= ticks (* (/ (* hr ticksPerMin) theSpeed) minPerHr))
		)
		(= aTimer (if (& -info- CLASS) (self new:) else self))
		(aTimer init:caller, cycleCnt:ticks)
		(return aTimer)
	)


	(method (setReal caller sec min hr &tmp aTimer secs)
		;; Set the timer to go off after a certain period of real time.

		(= secs sec)
		(if (> argc 2)
			(+= secs (* min 60))
		)
		(if (> argc 3)
			(+= secs (* hr 3600))
		)
		(= aTimer (if (& -info- CLASS) (self new:) else self))
		(aTimer init:caller, seconds:secs)
		(return aTimer)
	)
)




(class TimeOut of Object
	; static timer counts down elapsed cycles only
	; intended to be interrogated by interested parties
	; recommended that rm000 include kindofs for visibility

	(properties
		name {TO}
		timeLeft 0
	)

;;;	(methods
;;;		set
;;;	)

	(method (set cToCount)
		(= timeLeft cToCount)
	)

	(method (doit)
		(if timeLeft
			(-- timeLeft)
		)
	)
)