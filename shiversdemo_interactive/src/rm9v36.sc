;;; Sierra Script 1.0 - (do not remove this comment)
(script# 9360)
(include sci.sh)
(use Main)
(use Procs)
(use Motion)
(use Actor)

(public
	rm9v36 0
)

(instance rm9v36 of ShiversRoom
	(properties
		picture 9360
	)
	
	(method (init)
		(efExitLeft init: 2)
		(efExitRight init: 6)
		(efExitForward init: 9)
		(efExitPuzzle init: 9)
		(efExitDoor init: 9)
		(pFountain init:)
		(if (proc951_5 42)
			(if (== prevRoomNum 9320)
				(MonoOut {fade to 26})
				(sounds fade: 10908 26 15 8 0 0)
			)
			(if (proc951_11 0 9000)
				(sounds fade: 20903 0 10 16 1 0)
				(sounds stop: 20908)
				(proc951_9 20908)
				(sounds play: 20908 -1 50 0)
			)
			((Prop new:)
				view: 9360
				setPri: 5 1
				loop: 1
				cycleSpeed: 12
				setCycle: Fwd
				init:
			)
		else
			((Prop new:) view: 9360 setPri: 5 1 loop: 0 init:)
		)
		(super init: &rest)
	)
	
	(method (newRoom n)
		(if
		(and (!= n 9390) (proc951_5 42) (proc951_11 0 9000))
			(sounds fade: 20908 0 10 16 1 0)
			(sounds stop: 20903)
			(proc951_9 20903)
			(sounds play: 20903 -1 0 0)
			(sounds fade: 20903 42 10 16 0 0)
		)
		(super newRoom: n &rest)
	)
)

(instance efExitLeft of ExitFeature
	(properties
		nextRoom 9350
	)
)

(instance efExitRight of ExitFeature
	(properties
		nextRoom 9340
	)
	
	(method (init)
		(self
			createPoly:
				240
				0
				240
				30
				264
				30
				264
				114
				240
				114
				240
				144
				265
				144
				265
				0
				240
				0
		)
		(super init: &rest)
	)
)

(instance efExitForward of ExitFeature
	(properties
		nextRoom 9390
	)
	
	(method (init)
		(self
			createPoly:
				175
				104
				175
				82
				232
				35
				263
				35
				263
				118
				201
				118
				222
				141
				130
				141
				141
				118
		)
		(super init: &rest)
	)
)

(instance efExitPuzzle of ExitFeature
	(properties
		nextRoom 9420
	)
	
	(method (init)
		(self createPoly: 84 87 83 33 157 34 156 103 118 117)
		(super init: &rest)
	)
)

(instance efExitDoor of ExitFeature
	(properties
		nextRoom 9470
	)
	
	(method (init)
		(self createPoly: 13 92 13 34 53 34 53 91)
		(super init: &rest)
	)
)

(instance pFountain of ShiversProp
	(properties
		priority 3
		fixPriority 1
		view 9360
	)
)
