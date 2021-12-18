;;; Sierra Script 1.0 - (do not remove this comment)
(script# 9020)
(include sci.sh)
(use Main)
(use Procs)
(use Motion)
(use Actor)
(use System)

(public
	rm9v02 0
)

(instance rm9v02 of ShiversRoom
	(properties
		picture 9020
	)
	
	(method (init)
		(efExitLeft init: 2)
		(efExitRight init: 1)
		(efExitForward init: 9)
		(if (proc951_5 42)
			((Prop new:) view: 9020 setPri: 10 1 loop: 0 init:)
			((Prop new:)
				view: 9020
				setPri: 20 1
				loop: 1
				cycleSpeed: 12
				setCycle: Fwd
				init:
			)
		else
			((Prop new:) view: 9020 setPri: 10 1 loop: 0 init:)
		)
		(super init: &rest)
		(if
		(and (< 1000 prevRoomNum) (< prevRoomNum 9000))
			(curRoom setScript: sEnter)
		)
	)
	
	(method (newRoom)
		(proc951_1 6)
		(proc951_1 5)
		(super newRoom: &rest)
	)
)

(instance efExitLeft of ExitFeature
	(properties
		nextRoom 9040
	)
)

(instance efExitRight of ExitFeature
	(properties
		nextRoom 9060
	)
)

(instance efExitForward of ExitFeature
	(properties
		nextRoom 9100
	)
	
	(method (init)
		(self
			createPoly: 97 143 62 121 62 92 176 92 191 102 198 142
		)
		(super init: &rest)
	)
)

(instance sEnter of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (proc951_11 2 6000)
					(sounds fade: 20603 0 1 30 1 self)
				else
					(sounds fade: 20601 0 1 30 1 self)
				)
			)
			(1
				(proc951_10 20601)
				(proc951_10 20603)
				(sounds play: 10918 0 40 0)
				(= cycles 1)
			)
			(2
				(proc951_7 20903)
				(proc951_9 20903)
				(sounds play: 20903 -1 0 0)
				(sounds fade: 20903 42 5 16 0 0)
				(theGame handsOn:)
				(= cycles 1)
			)
			(3 (self dispose:))
		)
	)
)
