;;; Sierra Script 1.0 - (do not remove this comment)
(script# 82)
(include sci.sh)
(use Main)
(use Kq6Procs)
(use Inset)
(use Motion)
(use Actor)
(use System)

(public
	keyHoleScr 0
	keyHoleView 1
)

(local
	egoLoop
)
(instance keyHoleScr of Script
	(properties)
	
	(method (init)
		(Bset 49)
		(super init: &rest)
	)
	
	(method (dispose)
		(Bclr 49)
		(= register 0)
		(super dispose:)
		(theGame handsOn:)
		(DisposeScript 82)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= egoLoop (ego loop?))
				(ego
					view: 701
					normal: 0
					cycleSpeed: 8
					loop:
						(cond 
							(
							(and (<= 225 (ego heading?)) (<= (ego heading?) 315)) 1)
							(
							(and (<= 45 (ego heading?)) (<= (ego heading?) 135)) 0)
							(else 2)
						)
					cel: 0
					setCycle: EndLoop self
				)
			)
			(1
				(theGame handsOn:)
				(theIconBar disable: 0 1 3 4 5 6)
				(keyHole init: 0 curRoom)
				(if register
					(self setScript: register self)
				else
					(= seconds 5)
				)
			)
			(2
				(theGame handsOff:)
				(if (not register) (keyHole dispose:))
				(= cycles 1)
			)
			(3 (ego setCycle: BegLoop self))
			(4
				(theGame handsOn:)
				(theIconBar enable: 6)
				(ego reset: egoLoop)
				(self dispose:)
			)
		)
	)
)

(instance keyHole of Inset
	(properties
		view 796
		priority 15
		disposeNotOnMe 1
	)
	
	(method (init)
		(ego stopUpd:)
		(= x (- 160 (/ (CelWide view loop cel) 2)))
		(= y (- 100 (/ (CelHigh view loop cel) 2)))
		(super init: &rest)
	)
	
	(method (doit)
		(if script (script doit:))
	)
	
	(method (dispose)
		(keyHoleView dispose:)
		((keyHoleScr script?) dispose:)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(if
			(not
				(if (keyHoleView actions?)
					((keyHoleView actions?) doVerb: theVerb)
				)
			)
			(super doVerb: theVerb)
		)
	)
)

(instance keyHoleView of View
	(properties)
	
	(method (init theView)
		(= x (+ (keyHole x?) 92))
		(= y (+ (keyHole y?) 54))
		(= view [theView (= loop (= cel 0))])
		(if (> argc 1)
			(= loop [theView 1])
			(if (> argc 2)
				(= cel [theView 2])
				(if (> argc 3)
					(= x (+ [theView 3] (keyHole x?)))
					(if (> argc 4) (= y (+ [theView 4] (keyHole y?))))
				)
			)
		)
		(super init: &rest)
		(keyHole noun: noun)
		(self setPri: 13 sightAngle: 180 stopUpd:)
	)
)
