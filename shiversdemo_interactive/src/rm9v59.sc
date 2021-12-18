;;; Sierra Script 1.0 - (do not remove this comment)
(script# 9590)
(include sci.sh)
(use Main)
(use Procs)
(use Motion)
(use Actor)
(use System)

(public
	rm9v59 0
)

(local
	local0
)
(instance rm9v59 of ShiversRoom
	(properties
		picture 9590
	)
	
	(method (init)
		(efExitLeft init: 7)
		(efExitRight init: 6)
		(hsDoor init:)
		(super init: &rest)
	)
	
	(method (newRoom n)
		(cond 
			((>= n 11000) (sounds fade: 20903 0 10 21 1 0))
			(local0 (proc951_3 43))
		)
		(super newRoom: n &rest)
	)
)

(instance efExitLeft of ExitFeature
	(properties
		nextRoom 9600
	)
)

(instance efExitRight of ExitFeature
	(properties
		nextRoom 9600
	)
)

(instance efExitForward of ExitFeature
	(properties
		nextRoom 11020
	)
	
	(method (init)
		(self
			createPoly: 76 111 76 27 159 27 160 112 167 140 58 140
		)
		(super init: &rest)
	)
)

(instance pDoor of Prop
	(properties
		view 9590
		cycleSpeed 18
	)
)

(instance hsDoor of HotSpot
	(properties)
	
	(method (init)
		(self
			createPoly: 76 111 76 27 159 27 160 112 167 140 58 140
		)
		(super init: &rest)
	)
	
	(method (doVerb)
		(curRoom setScript: sDoorOpens)
	)
)

(instance sDoorOpens of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(pDoor init: setPri: 25 1)
				(proc951_7 10917)
				(= cycles 1)
			)
			(1
				(pDoor setCycle: End)
				(sounds play: 10917 0 40 self)
			)
			(2
				(theGame handsOn:)
				(efExitForward init: 3)
				(= local0 1)
				(hsDoor dispose:)
				(self dispose:)
			)
		)
	)
)
