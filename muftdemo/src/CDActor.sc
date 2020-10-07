;;; Sierra Script 1.0 - (do not remove this comment)
(script# CDACTOR)
(include game.sh)
(use Main)
(use Tactor)
(use Motion)
(use Actor)
(use System)


(class Head of Prop
	(properties
		cycleSpeed 42
		client 0
		cycleCnt 0
		moveHead TRUE
		headCel {15372406}
	)
	
	(method (init obj &tmp temp0)
		;EO: this bit of code was taken from PQ3, as Mixed-Up Fairy Tales' 
		;Head init: did not decompile correctly, causing graphical glitches!
		(self
			view: (obj view?)
			client: obj
			ignoreActors: TRUE
		)
		(= loop (- (NumLoops self) 2))		
;;;		(= view (obj view?))
;;;		(self
;;;			client: obj
;;;			ignoreActors: TRUE
;;;			posn:
;;;				(obj x?)
;;;				(obj y?)
;;;				(CelHigh view (obj loop?) (obj cel?))
;;;		)
;;;		(= temp0 (== (client loop?) 5))
;;;		(if (& (obj signal?) fixPriOn)
;;;			(self setPri: (obj priority?))
;;;		)
		(obj head: self)
		(super init:)
		(if (or (not temp0) (not (obj normal?)))
			(self hide:)
		)
		(if moveHead
			(self cue: look:)
		)
	)
	
	(method (doit &tmp temp0 clientNormal)
		(= clientNormal (client normal?))
		(= temp0 (== (client loop?) 5))
		(cond 
			((or (not clientNormal) (not temp0)) (self hide:))
			((and (& signal actorHidden) temp0) (self show:))
			(
			(and (not (& signal actorHidden)) (not (client isStopped:))) (self hide:))
		)
		(if moveHead
			(self setPri: (client priority?) look:)
			(if (!= (self view?) (client view?))
				(self view: (client view?) loop: 4)
			)
			(= x (client x?))
			(= y (client y?))
			(= z
				(CelHigh (client view?) (client loop?) (client cel?))
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(if script
			(script caller: 0)
		)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(client doVerb: theVerb)
	)
	
	(method (cue &tmp temp0)
		(if (and (not (curRoom script?)) moveHead)
			(client look: (- (Random 0 2) 1))
		)
		(self setScript: (HeadScript new:) self)
	)
	
	(method (look &tmp cLoop cDir)
		(if
			(==
				(= cLoop (client loop?))
				(- (NumLoops client) 1)
			)
			(= cLoop (client cel?))
			(= cDir (client lookingDir?))
		else
			(= cDir 0)
		)
		(= cel
			(+
				(& (StrAt headCel cLoop) $000f)
				cDir
			)
		)
	)
)

(instance HeadScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= ticks (Random 60 150)))
			(1 (self dispose:))
		)
	)
)

(class Tail of Prop
	(properties
		cycleSpeed 42
		client 0
		cycleCnt 0
		moveTail TRUE
	)
	
	(method (init obj)
		(= view (obj view?))
		(self client: obj ignoreActors: TRUE setCycle: Forward)
		(if (& (obj signal?) fixPriOn)
			(self setPri: (obj priority?))
		)
		(obj head: self posnTail: self)
		(super init:)
	)
	
	(method (doit &tmp clientPriority)
		(if (!= view (client view?)) (= view (client view?)))
		(cond 
			((and (not (client normal?)) (not (& signal actorHidden)))
				(self hide:)
			)
			(
				(and
					(not (& signal actorHidden))
					(!= loop (+ (client cel?) 4))
				)
				(self hide:)
				(client posnTail: self)
			)
			((and (not (& signal actorHidden)) (not (client isStopped:)))
				(self hide:))
			(
				(and
					(& signal actorHidden)
					(client isStopped:)
					(client normal?)
				)
				(client posnTail: self)
				(self show:)
			)
		)
		(if (and moveTail (not (& signal actorHidden)))
			(if (!= priority (= clientPriority (client priority?)))
				(self setPri: (client priority?))
			)
			(switch (Random 0 18)
				(0
					(self setCel: (Random 0 (NumCels self)))
				)
			)
		)
		(super doit:)
	)
)

(class CDActor of Tactor
	(properties
		head 0
		lookingDir 1
		normal 1
		isAnimal 0
	)
	
	(method (init)
		(super init:)
		(if (not head)
			((= head ((if isAnimal Tail else Head) new:))
				init: self
			)
		)
		(if (not normal) (head hide:))
	)
	
	(method (dispose)
		(if head
			(head setCel: 0 setCycle: 0 dispose:)
			(= head 0)
		)
		(super dispose:)
	)
	
	(method (cue)
		(if head (head setCel: 0 setCycle: 0))
	)
	
	(method (look theLookingDir)
		(= lookingDir theLookingDir)
	)
)

(class CDAnimal of CDActor
	(properties
		head 0
		lookingDir 1
		normal 1
		isAnimal 1
		animRightX 0
		animRightY 0
		animLeftX 0
		animLeftY 0
		animFrontX 0
		animFrontY 0
		animBackX 0
		animBackY 0
	)
	
	(method (posnTail obj)
		(obj setLoop: (+ cel 4))
		(switch cel
			(0
				(obj posn: (+ x animRightX) y (- animRightY))
			)
			(1
				(obj posn: (+ x animLeftX) y (- animLeftY))
			)
			(2
				(obj posn: (+ x animFrontX) (+ y animFrontY) 0)
			)
			(3
				(obj posn: (+ x animBackX) y (- animBackY))
			)
		)
	)
)

(class Body of Tego
	(properties
		head 0
		caller 0
		lookingDir 1
		normal 1
	)
	
	(method (init)
		(super init:)
		(if (not head)
			((= head (Head new:)) init: self)
		)
	)
	
	(method (dispose)
		(= head 0)
		(super dispose:)
	)
	
	(method (cue)
		(if head (head setCel: 0 setCycle: 0))
	)
	
	(method (look theLookingDir)
		(= lookingDir theLookingDir)
	)
)
