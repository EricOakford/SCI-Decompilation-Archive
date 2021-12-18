;;; Sierra Script 1.0 - (do not remove this comment)
(script# 6020)
(include sci.sh)
(use Main)
(use Procs)
(use System)

(public
	rm6v020 0
)

(instance rm6v020 of ShiversRoom
	(properties
		picture 6020
	)
	
	(method (init)
		(super init: &rest)
		(efExitLeft init: 2)
		(efExitRight init: 1)
		(efExitForward init: 3)
		(if
		(and (>= 10000 prevRoomNum) (>= prevRoomNum 9000))
			(curRoom setScript: sEnter)
			(sounds play: 10614 0 40 0)
		)
		(if (== prevRoomNum -27416)
			(sounds play: 10620 0 40 0)
			(if (proc951_11 2 6000)
				(proc951_7 20603)
				(proc951_9 20603)
				(sounds play: 20603 -1 32 0)
				(sounds fade: 20601 0 5 16 1 0)
				(sounds fadeChain:)
			)
		)
	)
	
	(method (newRoom n)
		(proc951_1 6)
		(super newRoom: n &rest)
	)
)

(instance efExitLeft of ExitFeature
	(properties
		nextRoom 6040
	)
)

(instance efExitRight of ExitFeature
	(properties
		nextRoom 6060
	)
)

(instance efExitForward of ExitFeature
	(properties
		nextRoom 6260
	)
	
	(method (init)
		(self createPoly: 143 140 143 42 202 42 202 139)
		(super init: &rest)
	)
)

(instance sEnter of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(sounds fade: 20903 0 1 30 1 self)
			)
			(1
				(proc951_10 20903)
				(= cycles 1)
			)
			(2
				(if (proc951_11 2 6000)
					(proc951_7 20603)
					(proc951_9 20603)
					(sounds play: 20603 -1 0 0)
					(sounds fade: 20603 32 1 30 0 0)
				else
					(proc951_7 20601)
					(proc951_9 20601)
					(sounds play: 20601 -1 0 0)
					(sounds fade: 20601 10 1 30 0 0)
					(sounds playChain: -1 -4 20602 -1)
					(sounds adjChainVol: 34)
				)
				(theGame handsOn:)
				(= cycles 1)
			)
			(3 (self dispose:))
		)
	)
)
