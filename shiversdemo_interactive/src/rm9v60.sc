;;; Sierra Script 1.0 - (do not remove this comment)
(script# 9600)
(include sci.sh)
(use Main)
(use Procs)
(use Motion)
(use Actor)

(public
	rm9v60 0
)

(instance rm9v60 of ShiversRoom
	(properties
		picture 9600
	)
	
	(method (init)
		(efExitLeft init: 7)
		(efExitRight init: 6)
		(efExitForward init: 9)
		(if (proc951_5 42)
			((Prop new:)
				view: 9600
				setPri: 5 1
				loop: 1
				cycleSpeed: 12
				setCycle: Fwd
				init:
			)
		else
			((Prop new:) view: 9600 setPri: 5 1 loop: 0 init:)
		)
		(if (>= prevRoomNum 11000)
			(proc951_7 20903)
			(proc951_9 20903)
			(sounds play: 20903 -1 0 0)
			(sounds fade: 20903 42 15 5 0 0)
		)
		(super init: &rest)
		(if (or (>= prevRoomNum 11000) (proc951_5 43))
			(sounds play: 11105 0 32 0)
			(proc951_4 43)
		)
	)
	
	(method (newRoom n)
		(proc951_1 6)
		(super newRoom: n &rest)
	)
)

(instance efExitLeft of ExitFeature
	(properties
		nextRoom 9590
	)
)

(instance efExitRight of ExitFeature
	(properties
		nextRoom 9590
	)
)

(instance efExitForward of ExitFeature
	(properties
		nextRoom 9130
	)
	
	(method (init)
		(self createPoly: 61 142 69 86 201 86 218 140)
		(super init: &rest)
	)
)
